package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {

		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();

		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
		int year = todayDate.getYear(); // 現在の年を取得
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後mでリストに追加
		for (int i = year - 10;i < year + 1; i ++){
			entYearSet.add(i);
		}

		List<String> classea = cNumDao.filter(teacher.getSchool());


		List<Subject> subList = subDao.filter(teacher.getSchool());
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classea);
		req.setAttribute("subject_set", subList);

		req.getRequestDispatcher("test_list.jsp").forward(req,res);
		}
	}

}
