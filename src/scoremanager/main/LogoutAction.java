package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class LogoutAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();

		// 空のTeacherを定義
		Teacher teacher = new Teacher();

		// Teacherのフラグをfalseにする
		teacher.setAuthenticated(false);

		// 空のTeacherをセッションに格納
		session.setAttribute("user", teacher);

		//フォワード
		req.getRequestDispatcher("logout.jsp").forward(req, res);
	}

}
