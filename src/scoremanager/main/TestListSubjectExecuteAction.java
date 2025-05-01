package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListSubject;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr = "";
		String classNum = "";
		int entYear = 0;
		String subject = "";

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");

		Map<String, String> errors = new HashMap<>();

		if (entYearStr == null || entYearStr.equals("0") ||
			    classNum == null || classNum.equals("0") ||
			    subject == null || subject.equals("0")) {
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
		 	req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
		}

		TestListSubjectDao tesListSubDao = new TestListSubjectDao();

		entYear = Integer.parseInt(entYearStr);


		List<TestListSubject> tesListSub = tesListSubDao.filter(teacher.getSchool(), entYear, classNum);
		req.setAttribute("students", tesListSub);

		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
