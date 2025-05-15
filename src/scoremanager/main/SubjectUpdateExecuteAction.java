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

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログインユーザーから学校情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {
        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 学校情報を取得
        SchoolDao sDao = new SchoolDao();
        School school = sDao.get(teacher.getSchool().getCd());

        // ログ（デバッグ）
        System.out.println("入力された値: cd = " + cd + " | name = " + name + " | school = " + school.getCd());

        // Subject オブジェクト生成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // 更新
        SubjectDao subDao = new SubjectDao();
        boolean result = subDao.save(subject);

        // 画面遷移
        if (result) {
            req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "更新に失敗しました");
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        }
        }
    }
}