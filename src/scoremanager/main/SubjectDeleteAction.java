package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		if (teacher == null) {
			response.sendRedirect("../Login.action");
		} else {

        String cd = request.getParameter("cd");

        // 教員から学校コードを取得し、学校インスタンスを取得
        SchoolDao schoolDao = new SchoolDao();
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, schoolDao.get(teacher.getSchool().getCd()));

        // 科目情報をリクエストに渡して、確認画面へ
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp").forward(request, response);
    }
    }
}
