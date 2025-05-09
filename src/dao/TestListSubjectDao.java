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
        		+ "student.no = student_no "
        		+ "where student.ent_year = ? and test.class_num = ? and test.subject_cd = ? and test.school_cd = ? "
        		+ "order by test.class_num,student.no,test.point   ";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try  {

            statement.setInt(1, entYear);
            statement.setString(2,classNum );
            statement.setString(3, subject.getCd());
            statement.setString(4, school.getCd());

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
    		// TestListSubjctを初期化
    		TestListSubject tls = new TestListSubject();
    		// 比較用のデータを取り出す
    		String classNum = rs.getString("student.class_num");
    		String studentNo = rs.getString("student_no");
    		System.out.println("studentNo:"+studentNo);
    		// 2回目の点数が存在するかの判断に使う
    		if (list.size() != 0) {
	    		int index = list.size() -1;
	    		System.out.println("index = "+index);
	    		// listの最後の学生番号と新たに追加したい学生番号を比べ同じならば2回目の点数として追加する
	    		if (list.get(index).getClassNum().equals(classNum) &&  list.get(index).getStudentNo().equals(studentNo)){
	    			System.out.println("同じです");
	    			// 新しいリストのデータを取り出す
	    			tls = list.get(index);
	    			// 得点の2回目を書き換える
	    			tls.putPoints(2, rs.getInt("point"));
	    			// リストに格納
	    			list.set(index, tls);
	    			System.out.println("1:"+tls.getPoints(1)+" 2:"+tls.getPoints(2));
	    		}else {
	    			// リストに格納
		    		tls.setClassNum(classNum);
		    		tls.setEntYear(rs.getInt("ent_year"));
		    		tls.setStudentName(rs.getString("name"));
		    		tls.setStudentNo(studentNo);
		    		tls.putPoints(1, rs.getInt("point"));
		    		list.add(tls);
	    		}
    		} else {
    			// リストに格納
	    		tls.setClassNum(classNum);
	    		tls.setEntYear(rs.getInt("ent_year"));
	    		tls.setStudentName(rs.getString("name"));
	    		tls.setStudentNo(studentNo);
	    		int point = rs.getInt("point");
	    		tls.putPoints(1, point);
	    		list.add(tls);
    		}
    	}
        return list;
    }
}
