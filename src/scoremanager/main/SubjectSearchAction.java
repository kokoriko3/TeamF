package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectSearchAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


		String cd = "";

		cd = req.getParameter("cd");

		if (cd.length() != 3) {
			req.setAttribute("errorNo", "科目コードは3文字で入力してください");
			req.setAttribute("cd", cd);
			req.getRequestDispatcher("subject_list.jsp").forward(req, res);
			return;
		}

		SubjectDao subDao = new SubjectDao();
		List<Subject> list = subDao.filter(cd);

		System.out.println(list);

		req.setAttribute("subjects", list);
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);

	}
}
