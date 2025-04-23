package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = request.getParameter("cd"); // 削除対象の科目コードを取得

        // 学校情報の取得（教員に紐づく学校）
        SchoolDao schoolDao = new SchoolDao();
        SubjectDao subjectDao = new SubjectDao();

        Subject subject = subjectDao.get(cd, schoolDao.get(teacher.getSchool().getCd()));

        if (subject != null) {
            subjectDao.delete(subject);
        }

        // 削除後、科目一覧へリダイレクト
        response.sendRedirect("SubjectList.action");
    }
}
