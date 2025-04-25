package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		ClassNumDao cNumDao = new ClassNumDao(); //クラス番号Dao
		SubjectDao sDao = new SubjectDao(); // 科目dao
		TestDao tDao = new TestDao(); // 得点dao
		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
		int year = todayDate.getYear(); // 現在の年を取得
		String entYearStr = "";
		String classNum = "";
		String subjectCd = "";
		String numStr = "";

		// 値を受け取る
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");
		numStr = req.getParameter("f4");

		// DBからデータを取得
		// subjectの情報を受け取る
		Subject subject = sDao.get(subjectCd, teacher.getSchool());
		// 一覧表示に使うデータ
		List<Test> tList = tDao.filter(
				Integer.parseInt(entYearStr), classNum,  subject,
				Integer.parseInt(numStr), teacher.getSchool());
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
		req.setAttribute("f1", entYearSet);
		req.setAttribute("f2", classList);
		req.setAttribute("f3", subjectList);

		req.setAttribute("list", tList);

		req.getRequestDispatcher("test_regist.jsp").forward(req,res);;
	}
}
