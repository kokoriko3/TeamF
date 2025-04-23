package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザーを取得

        HttpSession session = req.getSession();

        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校情報を取得

        SchoolDao sDao = new SchoolDao();

        School school = sDao.get(teacher.getSchool().getCd());

        // パラメータから科目コード取得

        String cd = req.getParameter("no");

        // 科目情報を取得（cd, school を使う）

        SubjectDao subDao = new SubjectDao();

        Subject subject = subDao.get(cd, school);

        // リクエストにセットして画面に渡す

        req.setAttribute("subject", subject);

        // 遷移

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);

    }

}

