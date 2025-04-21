package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 変数を定義
		String entYearStr = "";
		String noStr = "";
		String name = "";
		String classNumStr = "";
		// studentDaoをインスタンス科
		StudentDao sDao = new StudentDao();

		// リクエストされたデータを取り出す
		entYearStr = req.getParameter("ent_year");
		noStr = req.getParameter("no");
		name = req.getParameter("name");
		classNumStr = req.getParameter("class_num");

		System.out.println("入力された値:entYear-"+entYearStr+" :noStr-"+noStr+ " :name-"+name+" :classNum-"+classNumStr);


		// 学生番号が存在しているか調べる
		Student getStudent = new Student();
		// 空の場合はエラーメッセージを定義してフォワード
		getStudent  = sDao.get(noStr);
		System.out.println(getStudent);
		// 1つでも空のデータがある場合はcreate.jspを表示
		if (entYearStr.equals("0")) {
			// エラーメッセージ
			req.setAttribute("errorEntYear", "入学年度を選択してください");
			// 入力した文字を出す
			req.setAttribute("no", noStr);
			req.setAttribute("name", name);
			req.getRequestDispatcher("student_create.jsp").forward(req, res);

		}
		else if(getStudent != null){
			req.setAttribute("errorNo", "学生番号が重複しています");
			req.getRequestDispatcher("student_create.jsp").forward(req, res);

		}
		else {
			Student student = new Student();

			// 取り出したデータをstudentにセットする
			student.setNo(noStr);
			student.setName(name);
			student.setEntYear(Integer.parseInt(entYearStr));
			student.setClassNum(classNumStr);
			student.setAttend(true);
			student.setSchool(teacher.getSchool());

			// データベースに情報を保存
			sDao.save(student);

			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);;
		}
	}

}
