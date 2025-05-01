package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Test> tList = new ArrayList<>();
		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
		int year = todayDate.getYear(); // 現在の年を取得
		String entYearStr = "";
		String classNum = "";
		String subjectCd = "";
		String numStr = "";
		int entYear = 0;
		Map<String, String>errors = new HashMap<>(); // エラーメッセージ

		// 値を受け取る
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");
		numStr = req.getParameter("f4");

		// 絞り込み用
		if (entYearStr != null) {
			/// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// DBからデータを取得
		if (subjectCd != null && entYearStr != null && classNum != null && numStr != null) {
			// subjectの情報を受け取る
			Subject subject = sDao.get(subjectCd, teacher.getSchool());
			// 一覧表示に使うデータ
			tList = tDao.filter(
					entYear,classNum,  subject,
					Integer.parseInt(numStr), teacher.getSchool());
			req.setAttribute("f3", subject);
		} else {
			errors.put("f1","入学年度とクラスと科目と回数のすべてを入力してください");
			req.setAttribute("errors", errors);
		}
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
		req.setAttribute("f4", numStr);

		for (Test t : tList) {
			System.out.println("点数:"+t.getPoint());
		}
		req.setAttribute("list", tList);

		req.getRequestDispatcher("test_regist.jsp").forward(req,res);
	}
}
