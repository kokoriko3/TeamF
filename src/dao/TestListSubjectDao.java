package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(School school, int entYear, String classNum,Subject subject) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        String sql = ""
        		+ "select * from test join student on "
        		+ "student.no = student_no ";
//        		+ "where student.ent_year = ? and test.class_num = ? and test.subject_cd = ? and test.school_cd = ? "
//        		+ "order by student.no  ";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try  {

//            statement.setInt(1, entYear);
//            statement.setString(2,classNum );
//            statement.setString(3, subject.getCd());
//            statement.setString(4, school.getCd());

            ResultSet rs = statement.executeQuery();
            list = postFilter(rs);

        } catch (Exception e) {
            throw e;
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

        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
    	List<TestListSubject> list = new ArrayList<>();

    	while (rs.next()) {
    		TestListSubject tls = new TestListSubject();
    		tls.setClassNum(rs.getString("student.class_num"));
    		tls.setEntYear(rs.getInt("ent_year"));
    		tls.setStudentName("name");
    	}
        return list;
    }
}
