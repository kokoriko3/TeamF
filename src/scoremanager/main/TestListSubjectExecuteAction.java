package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String schoolCd = teacher.getSchool().getCd();
        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(schoolCd);

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");

        Integer entYear = null;
        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        List<String> classNumSet = new ClassNumDao().filter(school);
        request.setAttribute("class_num_set", classNumSet);

        List<Integer> entYearSet = new TestListSubjectDao().getEntYearList(school);
        request.setAttribute("ent_year_set", entYearSet);

        List<TestListSubject> results = null;
        if (entYear != null && classNum != null && !classNum.equals("0")) {
            TestListSubjectDao dao = new TestListSubjectDao();
            results = dao.filter(school, entYear, classNum);
        }

        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("test_list_subjects", results);

        request.getRequestDispatcher("/scoremanager/main/test_list_subject.jsp").forward(request, response);
    }
}
