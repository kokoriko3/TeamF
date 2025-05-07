package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	private String baseSql = "select * from test ";
	public Test get(Student student,Subject subject,School school,int no) throws Exception {
		// 得点インスタンスを初期化
		Test test = new Test();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement(baseSql + "where student_no = ? and subject_cd = ?"
					+ " and school_cd = ? and no = ?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1,student.getNo());
			statement.setString(2,subject.getCd());
			statement.setString(3,school.getCd());
			statement.setInt(4,no);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// リザルトセットを全精査
			if (rSet.next()) {
				// 得点インスタンスに格納
				test.setClassNum(rSet.getString("class_num"));
				test.setPoint(rSet.getInt("point"));
				test.setNo(no);
				test.setSchhool(school);
				test.setStudent(student);
				test.setSubject(subject);
			} else {
				// リザルトセットが存在しない場合
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
	}

	private List<Test> postFilter(ResultSet rSet,School school) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			// リザルトセットを全精査
			while (rSet.next()) {
				// 得点インスタンス
				Test test = new Test();
				Student student = new Student();
				student.setNo(rSet.getString("student_no"));
				test.setStudent(student);
				test.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(test);

			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
}
	public List<Test> filter(int entYear,String classNum ,Subject subject,int num,School school) throws Exception {
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// DBの検索結果を格納するリストを初期化
		List<Test> tListDB = new ArrayList<>();
		// リストを初期化
		List<Test> tList = new ArrayList<>();
		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement(baseSql
					+"join student on test.student_no = student.no "
					+ "where ent_year = ? and subject_cd = ? "
					+ "and test.no = ? and test.school_cd = ? and test.class_num = ?");
			// プリペアードステートメントにバインド
			statement.setInt(1,entYear);
			statement.setString(2, subject.getCd());
			statement.setInt(3, num);
			statement.setString(4, school.getCd());
			statement.setString(5, classNum);
			// プリペアードステートメントをじっこう
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			tListDB = postFilter(rSet, school);
			// 入学年度が同じ学生を絞り込み
			List<Student> sList = new ArrayList<>();
			// studentのデータベースからクラスと入学年度が一緒の学生を検索
			StudentDao sDao = new StudentDao();
			sList = sDao.filter(school, entYear,classNum, true);


			// sListで絞り込んだデータと学生番号が同じデータがある場合そのデータに得点を格納
			for (Student student : sList) {
				System.out.println(student.getNo());
				// テストインスタンスを初期化
				Test test = new Test();
				test.setClassNum(student.getClassNum());
				test.setNo(num);
				test.setSchhool(school);
				test.setStudent(student);
				test.setSubject(subject);
				test.setPoint(-1);
				// クラスの生徒のデータとテストテールの学生番号が同じ場所があれば点数を定義
				for (Test t : tListDB) {
					System.out.println("テストの学生番号:"+t.getStudent().getNo()+"クラスの学生番号:"+student.getNo());
					if(t.getStudent().getNo().equals(student.getNo())){
						test.setPoint(t.getPoint());
						break;
					}
				}
				// リストに追加
				tList.add(test);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return tList;
	}

	public boolean save(List<Test> list) throws Exception {
		for (Test test : list){
			// コネクションを確率
			Connection connection = getConnection();
			boolean isSuccese = true;
			if (test.getPoint() != -1) {
				isSuccese = save(test,connection);
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// 実行結果でエラーが起きた場合
			if (!isSuccese) {
				return false;
			}
		}
		// 全ての処理が終了した場合
		return true;
	}

	private boolean save(Test test,Connection connection) throws Exception {
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			Test old = get(test.getStudent(),test.getSubject(),test.getSchhool(),test.getNo());
			if (old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメントにinsertをセット
				statement = connection.prepareStatement(
						"insert into test(student_no,subject_cd,school_cd,no,point,class_num) "
						+ "values(?,?,?,?,?,?)");
				// プリペアードステートメントにバインド
				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchhool().getCd());
				statement.setInt(4, test.getNo());
				statement.setInt(5, test.getPoint());
				statement.setString(6, test.getClassNum());
			} else {
				// 学生が存在した場合
				// プリペアードステートメントにupdateをセット
				statement = connection.prepareStatement(
						"update test set point = ? where "
						+ "student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
				// プリペアードステートメントにバインド
				statement.setInt(1, test.getPoint());
				statement.setString(2,test.getStudent().getNo());
				statement.setString(3,test.getSubject().getCd());
				statement.setString(4,test.getSchhool().getCd());
				statement.setInt(5,test.getNo());
			}
			count = statement.executeUpdate();
		}finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}
}
