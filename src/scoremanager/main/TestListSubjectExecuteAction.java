package scoremanager.main;

<<<<<<< HEAD
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ログイン中の教員の情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 入力された「f1」「f2」「f3」を取得
		String entYearStr = "";
		String classNum = "";
		int entYear = 0;
		String subject = "";

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");

		// 入力エラーを保持するMap
		Map<String, String> errors = new HashMap<>();

		// 必要な項目が未入力だったら「errors」をセットして「test_list.jsp」にフォワード
		if (entYearStr == null || entYearStr.equals("0") ||
			    classNum == null || classNum.equals("0") ||
			    subject == null || subject.equals("0")) {
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
		 	req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
		}

		// 「TestListSubjectDao」を生成
		TestListSubjectDao tesListSubDao = new TestListSubjectDao();

		// 「etnYearStr」(文字列)を数値に変換
		entYear = Integer.parseInt(entYearStr);

		// 指定した条件で生徒とその成績を取得
		// 取得したデータをセット
		List<TestListSubject> tesListSub = tesListSubDao.filter(teacher.getSchool(), entYear, classNum);
		req.setAttribute("students", tesListSub);

		// 「test_list_subject.jsp」にフォワード
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
>>>>>>> branch 'master' of https://github.com/kokoriko3/TeamF.git
}
