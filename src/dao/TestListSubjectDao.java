package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(School school, int entYear, String classNum) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        String sql = ""
            + "SELECT s.no, s.name, s.ent_year, s.class_num, r.subject_cd, r.point "
            + "FROM student s "
            + "LEFT JOIN result r ON s.no = r.student_no "
            + "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? "
            + "ORDER BY s.no, r.subject_cd";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);

            ResultSet rs = statement.executeQuery();
            list = postFilter(rs);

        } catch (Exception e) {
            throw e;
        }

        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        String currentNo = null;
        TestListSubject current = null;

        while (rs.next()) {
            String studentNo = rs.getString("no");
            if (!studentNo.equals(currentNo)) {
                current = new TestListSubject();
                current.setStudentNo(studentNo);
                current.setStudentName(rs.getString("name"));
                current.setEntYear(rs.getInt("ent_year"));
                current.setClassNum(rs.getString("class_num"));
                current.setPoints(new HashMap<>());
                list.add(current);
                currentNo = studentNo;
            }

            int subjectCd = rs.getInt("subject_cd");
            int point = rs.getInt("point");
            current.getPoints().put(subjectCd, point);
        }

        return list;
    }

    public List<Integer> getEntYearList(School school) throws Exception {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT ent_year FROM student WHERE school_cd = ? ORDER BY ent_year ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, school.getCd());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("ent_year"));
            }
        }
        return list;
    }

}
