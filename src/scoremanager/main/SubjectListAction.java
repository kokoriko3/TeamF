package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	public void execute(
			HttpServletRequest request, HttpServletResponse response
		)throws Exception {

		// ログイン中の教員の情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 「SubjectDao」を生成
		SubjectDao subjectDao = new SubjectDao();

		// 教員が所属する学校の科目を取得
		List<Subject> subjects = subjectDao.filter(teacher.getSchool());

		System.out.println(subjects);

		// JSPに渡すためにセット
		request.setAttribute("subjects", subjects);

		// 「subject_list.jsp」にフォワード
		request.getRequestDispatcher("subject_list.jsp").forward(request, response);
	}
}
