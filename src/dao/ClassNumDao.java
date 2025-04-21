package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{
	public ClassNum get(String class_num, School school) throws Exception {
		ClassNum classNum = new ClassNum();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from class_num where class_num=? and school_cd=?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, class_num);
			statement.setString(2, school.getCd());
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();

			// リザルトセットを全精査
			if (rSet.next()) {
				// 学生インスタンスに検索結果をセット
				classNum.setClass_num(rSet.getString("class_num"));
				classNum.setSchool(schoolDao.get(rSet.getString("scool_cd")));
			} else {
				// リザルトセットが存在しない場合
				classNum = null;
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
		return classNum;
	}

	public List<String> filter(School school) throws Exception{
		// リストを初期化
		List<String> list = new ArrayList<>();
		// コネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		try {
			// プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select class_num from class_num where school_cd=? order by class_num");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントをじっこう
			ResultSet rSet = statement.executeQuery();

			while(rSet.next()) {
				// リストにクラス番号を追加
				list.add(rSet.getString("class_num"));
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

	public boolean save(ClassNum classNum)throws Exception{
		return true;

	}

	public boolean save(ClassNum classNum, String newClassNum)throws Exception{
		return true;

	}
}