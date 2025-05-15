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

		// ログイン中の教員の情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {

		// 入力された「no」「name」を取得
		String no = "";
		String name = "";

		no = req.getParameter("no");
		name = req.getParameter("name");

		System.out.println(no);
		System.out.println(name);

		// 「SubjectDao」を生成
		SubjectDao subDao = new SubjectDao();

		// 受けとった「no」が３文字じゃなかったら
		// 「errorNo」「no」「name」をセットして「subject_create.jsp」に戻す
		if (no.length() != 3) {
			req.setAttribute("errorNo", "科目コードは3文字で入力してください");
			req.setAttribute("name", name);
			req.setAttribute("no", no);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}

		// すでに同じ科目コードの科目が登録されてるか確認
		Subject getSubject = new Subject();
		getSubject = subDao.get(no, teacher.getSchool());

		// 科目コードの重複があったら
		// 「errorNo」「no」「name」をセットして「subject_create.jsp」に戻す
		if (getSubject != null) {
			req.setAttribute("name", name);
			req.setAttribute("no", no);
			req.setAttribute("errorNo", "科目コードが重複しています");
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
		}

		// 入力された内容をもとに新しい科目オブジェクトを作成
		Subject subject = new Subject();
		subject.setCd(no);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // 科目を登録
        boolean result = subDao.save(subject);

        // 保存が成功したら「subject_create_done.jsp」にフォワード
        if (result) {
        	req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
        }
		}
	}
}
