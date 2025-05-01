package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String pointStr;
		int no; // 回数
		String studentNo ;//学生番号
		String classNum; // クラス番号
		String subjectCd; // 科目番号
		int count = 0; // ループのカウント用
		List<Test> list = new ArrayList<>();

		// 値を受け取りリストに格納する
		count = Integer.parseInt(req.getParameter("count"));
		for (int i = 0 ;i < count ; i++) {
			// 値を受け取りリストに格納する
			pointStr = req.getParameter("testList["+i+"].point");
			classNum = req.getParameter("testList["+i+"].classNum");
			no = Integer.parseInt(req.getParameter("testList["+i+"].no"));
			studentNo = req.getParameter("testList["+i+"].student");
			subjectCd = req.getParameter("testList["+i+"].subject");
			System.out.println("学生番号:"+studentNo+"科目番号:"+subjectCd+"回数:"+no+"点数:"+pointStr+"");
			if (pointStr == "") {
				continue;
			} else {
				// データをbeanに変える
				Student student = new Student();
				student.setNo(studentNo);
				Subject subject = new Subject();
				subject.setCd(subjectCd);


				// testをインスタンス化する
				Test test = new Test();
				// testにデータを格納
				test.setClassNum(classNum);
				test.setNo(no);
				test.setPoint(Integer.parseInt(pointStr));
				test.setSchhool(teacher.getSchool());
				test.setStudent(student);
				test.setSubject(subject);

				list.add(test);
			}
		}
		// データベースに保存する
		TestDao tDao = new TestDao();
		 boolean succese = tDao.save(list);
		 System.out.println(succese);

		req.getRequestDispatcher("test_update_done.jsp").forward(req, res);
	}

}
