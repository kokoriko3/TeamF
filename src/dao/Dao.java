package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;

	// getConnection() メソッド：接続先のデータベースを設定
	public Connection getConnection() throws Exception {
		if (ds==null) {
			InitialContext ic =new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/point");
		}
		return ds.getConnection();
	}
}
