package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログイン中の教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入力値取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");

        // 入力値の検証と変換
        Map<String, String> errors = new HashMap<>();
        int entYear = 0;

        if (entYearStr == null || entYearStr.equals("0") ||
            classNum == null || classNum.equals("0")) {
            errors.put("f1", "入学年度とクラスを選択してください。");

            // セレクトボックス用データ再取得
            SchoolDao schoolDao = new SchoolDao();
            School school = schoolDao.get(teacher.getSchool().getCd());

            ClassNumDao classNumDao = new ClassNumDao();
            req.setAttribute("class_num_set", classNumDao.filter(school));

            TestListSubjectDao testDao = new TestListSubjectDao();
            req.setAttribute("ent_year_set", testDao.getEntYearList(school));

            // 入力値保持
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("errors", errors);

            // エラー時の画面に戻る
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 正常処理：学校情報取得
        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(teacher.getSchool().getCd());
        entYear = Integer.parseInt(entYearStr);

        // 成績データ取得
        TestListSubjectDao testDao = new TestListSubjectDao();
        List<TestListSubject> testList = testDao.filter(school, entYear, classNum);

        // セレクトボックス表示用
        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("class_num_set", classNumDao.filter(school));
        req.setAttribute("ent_year_set", testDao.getEntYearList(school));

        // 入力値保持
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("students", testList);

        // 表示JSPへ遷移
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}
