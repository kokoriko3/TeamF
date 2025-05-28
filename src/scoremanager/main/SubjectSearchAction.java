package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectSearchAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ログイン中の教員の情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = "";

		cd = req.getParameter("cd");

		if (cd.length() > 3) {
			req.setAttribute("errorNo", "科目コードは3文字以内で入力してください");
			req.setAttribute("cd", cd);
			req.getRequestDispatcher("subject_list.jsp").forward(req, res);
			return;
		}

		SubjectDao subDao = new SubjectDao();
		List<Subject> list = subDao.filter(cd,teacher.getSchool());

		System.out.println(list);

		req.setAttribute("subjects", list);
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);

	}
}
