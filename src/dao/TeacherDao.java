package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao{
	public Teacher get(String id) throws Exception {
		// 学生インスタンスを初期化
		 Teacher teacher= new Teacher();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from teacher where id = ?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, id);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();

			// リザルトセットを全精査
			if (rSet.next()) {
				// 先生インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setName(rSet.getString("name"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				// リザルトセットが存在しない場合
				teacher = null;
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
		return teacher;
	}
	public Teacher login(String id,String password) throws Exception {
		 Teacher teacher= new Teacher();
		 teacher = get(id);
		 if (teacher != null) {
			 if (teacher.getPassword().equals(password))
			 return teacher;
		 }
		 return null;
	}
}
