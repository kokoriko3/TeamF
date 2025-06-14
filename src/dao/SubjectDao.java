package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{
	public Subject get(String cd,School school) throws Exception {
		// 科目インスタンスを初期化
		Subject subject = new Subject();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from subject where cd = ? and school_cd = ?");
			// プリペアードステートメントに科目コードと学校コードをバインド
			statement.setString(1, cd);
			statement.setString(2, school.getCd());
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();

			// リザルトセットを全精査
			if (rSet.next()) {
				// 科目インスタンスに結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				// 学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				// リザルトセットが存在しない場合
				subject = null;
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
		return subject;
	}

	public List<Subject> filter(School school) throws Exception {
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from subject where school_cd = ? and is_delete = false order by cd asc");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントをじっこう
			rSet = statement.executeQuery();
			// リザルトセットを全精査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Subject subject = new Subject();
				// 科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				// リストに追加
				list.add(subject);
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

		return list;

	}

	public List<Subject> filter(String subject_cd,School school) throws Exception {
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		String cd = "%"+ subject_cd + "%";
		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from subject where cd like ? and school_cd = ? and is_delete = false order by cd asc");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, cd);			// プリペアードステートメントをじっこう
			statement.setString(2, school.getCd());
			rSet = statement.executeQuery();
			// リザルトセットを全精査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Subject subject = new Subject();
				// 科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				// リストに追加
				list.add(subject);
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

		return list;

	}

	public boolean save(Subject subject) throws Exception {
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから学生を取得
			Subject old = get(subject.getCd(),subject.getSchool());
			if (old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメントにinsertをセット
				statement = connection.prepareStatement(
						"insert into subject(cd,name, school_cd) values(?,?,?)");
				// プリペアードステートメントにバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool().getCd());
			} else {
				// 学生が存在した場合
				// プリペアードステートメントにupdateをセット
				statement = connection.prepareStatement(
						"update subject set name=?, school_cd = ? where cd=? ");
				// プリペアードステートメントにバインド
				statement.setString(3, subject.getCd());
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getSchool().getCd());
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
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
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

	public boolean delete(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("update subject set is_delete = true where cd=? and school_cd = ?");
			statement.setString(1, subject.getCd());
			statement.setString(2, subject.getSchool().getCd());

			count = statement.executeUpdate();

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return count > 0;
	}

	}
