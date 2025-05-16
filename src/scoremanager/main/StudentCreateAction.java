package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		System.out.println("session:" +teacher);
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {

		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear(); // 現在の年を取得

		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		// 10年前から10年後までの年リスト　
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後mでリストに追加
		for (int i = year - 10;i < year + 10; i ++){
			entYearSet.add(i);
		}
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		
		

		req.getRequestDispatcher("student_create.jsp").forward(req, res);
		}
	}
}
