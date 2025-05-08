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
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ログイン中の教員の情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// 入力された「f1」「f2」「f3」を取得
		String entYearStr = "";
		String classNum = "";
		int entYear = 0;
		String subjectCd = "";

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");

		System.out.println("entYearStr:" +entYearStr + " classNum:" + classNum + " subjectCd:"+subjectCd);
		// 入力エラーを保持するMap
		Map<String, String> errors = new HashMap<>();

		// 必要な項目が未入力だったら「errors」をセットして「test_list.jsp」にフォワード
		if (entYearStr == null || entYearStr.equals("0") ||
			    classNum == null || classNum.equals("0") ||
			    subjectCd == null || subjectCd.equals("0")) {
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
		 	req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
		}

		// 「TestListSubjectDao」を生成
		TestListSubjectDao tesListSubDao = new TestListSubjectDao();

		// 「etnYearStr」(文字列)を数値に変換
		entYear = Integer.parseInt(entYearStr);

		// subjectのデータを取得
		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(subjectCd, teacher.getSchool());
		System.out.println(subject.getCd());
		// 指定した条件で生徒とその成績を取得
		// 取得したデータをセット
		List<TestListSubject> tesListSub = tesListSubDao.filter(teacher.getSchool(), entYear, classNum,subject);
		System.out.println(tesListSub);
		req.setAttribute("tesListSub", tesListSub);

		// selectに使うデータをdata baseからとりだす
		ClassNumDao cNumDao = new ClassNumDao(); //クラス番号Dai
		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
		int year = todayDate.getYear(); // 現在の年を取得
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後mでリストに追加
		for (int i = year - 10;i < year + 1; i ++){
			entYearSet.add(i);
		}
		List<Subject> sList = sDao.filter(teacher.getSchool());
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("subject_set", sList);
		// 「test_list_subject.jsp」にフォワード
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
