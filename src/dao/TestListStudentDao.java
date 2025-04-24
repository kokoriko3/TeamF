package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	private String baseSql =
		"SELECT subject.name AS subject_name, subject.cd AS subject_cd, test.no, test.point " +
		"FROM test " +
		"JOIN subject ON test.subject_cd = subject.cd " +
		"WHERE test.student_no = ?";

	// DBの結果をJavaオブジェクトのListに変換
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		List<TestListStudent> list = new ArrayList<>();

		while (rSet.next()) {
			TestListStudent tls = new TestListStudent();
			tls.setSubjectName(rSet.getString("subject_name"));
			tls.setSubjectCd(rSet.getString("subject_cd"));
			tls.setNum(rSet.getInt("no"));
			tls.setPoint(rSet.getInt("point"));
			list.add(tls);
		}

		return list;
	}

	// 特定の学生のテスト一覧を取得
	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list = new ArrayList<>();

		Connection con = getConnection();
		PreparedStatement stmt = null;
		ResultSet rSet = null;

		try {
			stmt = con.prepareStatement(baseSql);
			stmt.setString(1, student.getNo());
			rSet = stmt.executeQuery();
			list = postFilter(rSet);
		} finally {
			if (rSet != null) rSet.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}

		return list;
	}
}
