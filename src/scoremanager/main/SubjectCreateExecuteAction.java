package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String no = "";
		String name = "";

		no = req.getParameter("no");
		name = req.getParameter("name");

		System.out.println(no);
		System.out.println(name);


		SubjectDao subDao = new SubjectDao();

		if (no.length() != 3) {
			req.setAttribute("errorNo", "科目コードは3文字で入力してください");
			req.setAttribute("name", name);
			req.setAttribute("no", no);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
		}


		Subject getSubject = new Subject();
		getSubject = subDao.get(no, teacher.getSchool());

		if (getSubject != null) {
			req.setAttribute("name", name);
			req.setAttribute("no", no);
			req.setAttribute("errorNo", "科目コードが重複しています");
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
		}

		// 登録
		Subject subject = new Subject();
		subject.setCd(no);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        boolean result = subDao.save(subject);

        if (result) {
        	req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
        }

	}
}
