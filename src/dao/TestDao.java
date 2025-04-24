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
			SchoolDao schoolDao = new SchoolDao();

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
		return student;
	}

	private List<Test> postFilter(ResultSet rSet,School school) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		try {
			// リザルトセットを全精査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Student student = new Student();
				// 学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_Attend"));
				// リストに追加
				list.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;

	public List<Test> filter(int entYear,Subject subject,int num,School school) throws Exception {
		// 入学年度が同じ学生を絞り込み
		List<Student> sList = new ArrayList<>();
		// sDaoから絞り込み
		StudentDao sDao = new StudentDao();
		sList = sDao.filter(school, entYear, true);

		// リストを初期化
		List<Test> tList = new ArrayList<>();
		for (Student student : sList) {
			// テストインスタンスを初期化
			Test test = new Test();
			test.setClassNum(student.getClassNum());
			test.setNo(num);
			test.setSchhool(school);
			test.setStudent(student);
			test.setSubject(subject);
			// リストに追加
			tList.add(test);
		}

		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// DBの検索結果を格納するリストを初期化
		List<Test> tListDB = new ArrayList<>();
		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement(baseSql
					+"join student on test.student_no = student.no "
					+ "");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントに入学年度をバインド
			statement.setInt(2, entYear);
			// プリペアードステートメントをじっこう
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			tListDB = postFilter(rSet, school);
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

		return list;
	}

	public boolean save(List<Test> list) throws Exception {

	}

	private boolean save(Test test,Connection connection) throws Exception {

	}
}
