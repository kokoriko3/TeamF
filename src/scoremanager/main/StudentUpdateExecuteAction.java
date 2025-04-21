package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		// 変数の定義
		String entYear = "";
		String no = "";
		String name = "";
		String classNum = "";
		String isAttendStr = "";
		boolean isAttend = false;

		// リクエストされた値を受け取る
		entYear = req.getParameter("ent_year");
		no = req.getParameter("no");
		name= req.getParameter("name");
		classNum = req.getParameter("class_num");
		isAttendStr = req.getParameter("isAttend");
		// 受け取ったデータをコンソールに表示
		System.out.println("入力された値:entYear-"+entYear+" :no-"+no+ " :name-"+name+" :classNum-"+classNum+"isAttend:"+isAttend);
		// isAttendをbooleanにする
		if (isAttendStr.equals("t")) {
			isAttend = true;
		}

		Student student = new Student();

		// studentにデータを格納する
		student.setAttend(isAttend);
		student.setClassNum(classNum);
		student.setEntYear(Integer.parseInt(entYear));
		student.setName(name);
		student.setNo(no);

		// 結果をデータベースに保存
		StudentDao sDao = new StudentDao();
		sDao.save(student);

		// 画面遷移
		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}

}
