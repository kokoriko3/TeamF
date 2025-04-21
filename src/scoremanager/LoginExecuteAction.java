package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		String id =req.getParameter("id");
		String password = req.getParameter("password");

		Teacher teacher = new Teacher();

		TeacherDao tDao = new TeacherDao();
		teacher = tDao.login(id, password);
		if(teacher != null) {
			teacher.setAuthenticated(true);
			HttpSession session = req.getSession();
			session.setAttribute("user",teacher);
			res.sendRedirect("main/Menu.action");
		} else {
			// エラーメッセジをていぎ
			String message = "ログインに失敗しました、IDまたはパスワードが正しくありません";
			// メッセージをlogin.jspに送信
			req.setAttribute("message", message);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}
}
