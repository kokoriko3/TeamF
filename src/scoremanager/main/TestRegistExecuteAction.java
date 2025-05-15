package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {
		String pointStr;
		int point;
		int no; // 回数

		String studentNo ;//学生番号
		String classNum; // クラス番号
		String subjectCd; // 科目番号
		int count = 0; // ループのカウント用
		List<Test> list = new ArrayList<>();
		boolean isErrorPoint  = false;

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
			// データをbeanに変える
			Student student = new Student();
			// データベースから学生情報を取り出す
			StudentDao sDao = new StudentDao();
			student = sDao.get(studentNo);
			Subject subject = new Subject();
			// データベースから科目情報を取り出す
			SubjectDao subDao = new SubjectDao();
			subject = subDao.get(subjectCd, teacher.getSchool());

			// testをインスタンス化する
			Test test = new Test();
			// testにデータを格納
			test.setClassNum(classNum);
			test.setNo(no);
			test.setSchhool(teacher.getSchool());
			test.setStudent(student);
			test.setSubject(subject);
			if (pointStr != "") {
				point = Integer.parseInt(pointStr);
				// 点数が0 ~ 100 でない場合フラグをたて
				// jspでエラーをはだんだんするため点数を-2に設定する
				if (point < 0 || point > 100) {
					isErrorPoint = true;
					point  = -2;
				}
			}else {
				point = -1;
			}
			test.setPoint(point);
				list.add(test);
			}

		// 点数のエラーがあった場合
		// エラーメッセージをtest_regist.jspにフォワード
		if (isErrorPoint) {
			ClassNumDao cNumDao = new ClassNumDao(); //クラス番号Dao
			SubjectDao sDao = new SubjectDao(); // 科目dao
			LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
			int year = todayDate.getYear(); // 現在の年を取得
			classNum = "";
			subjectCd = "";

			int entYear = 0;

			// 値を受け取る
			entYear = list.get(0).getStudent().getEntYear();
			classNum = list.get(0).getClassNum();
			int num = list.get(0).getNo();

			// セレクトボックスに使うデータ
			// ログインユーザの学校コードをもとにクラス番号の一覧を取得
			List<String> classList = cNumDao.filter(teacher.getSchool());
			// 科目データの一覧を取得
			List<Subject> subjectList = sDao.filter(teacher.getSchool());

			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後までリストに追加
			for (int i = year - 10;i < year + 1; i ++){
				entYearSet.add(i);
			}
			// リクエストを設定
			req.setAttribute("ent_year_set", entYearSet);
			req.setAttribute("class_num_set", classList);
			req.setAttribute("subject_set", subjectList);

			req.setAttribute("f1", entYear);
			req.setAttribute("f2", classNum);
			req.setAttribute("f4", num);
			req.setAttribute("f3", list.get(0).getSubject());

			for (Test t : list) {
				System.out.println("点数:"+t.getPoint());
			}
			req.setAttribute("list", list);

			req.getRequestDispatcher("test_regist.jsp").forward(req,res);
		}else {
		// データベースに保存する
		TestDao tDao = new TestDao();
		 boolean succese = tDao.save(list);
		 System.out.println(succese);

		req.getRequestDispatcher("test_update_done.jsp").forward(req, res);
		}
		}
	}

}
