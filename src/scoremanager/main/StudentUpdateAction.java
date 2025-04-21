package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		// 変数の定義
		String no = ""; // 学生番号
		StudentDao sDao =new StudentDao();
		Student student = new Student();

		// データを受け取る
		no = req.getParameter("no");

		// データベースから学生番号で指定された値の詳細データを受け取る
		student = sDao.get(no);

		// クラス番号をセレクトに表示するためにデータベースから受け取る
		ClassNumDao cNumDao =new ClassNumDao();
		// クラス番号を取得
		List<String> list = cNumDao.filter(student.getSchool());


		// リクエストするデータを定義
		req.setAttribute("student", student);
		req.setAttribute("class_num_set", list);

		req.getRequestDispatcher("student_update.jsp").forward(req, res);;
	}

}
