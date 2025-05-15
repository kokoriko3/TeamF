package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ログイン中の教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher == null) {
			res.sendRedirect("../Login.action");
		} else {


        // 入力値取得
        String studentNo = req.getParameter("no");

        // 入力値の検証と変換
        Map<String, String> errors = new HashMap<>();
        List<TestListStudent> testList = null;

        if (studentNo == null || studentNo.equals("0")) {
            errors.put("f1", "学生番号を選択してください。");

            // セレクトボックス用データ再取得
            SchoolDao schoolDao = new SchoolDao();
            School school = schoolDao.get(teacher.getSchool().getCd());

            ClassNumDao classNumDao = new ClassNumDao();
            req.setAttribute("class_num_set", classNumDao.filter(school));

    		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
    		int year = todayDate.getYear(); // 現在の年を取得
    		// リストを初期化
    		List<Integer> entYearSet = new ArrayList<>();
    		// 10年前から1年後mでリストに追加
    		for (int i = year - 10;i < year + 1; i ++){
    			entYearSet.add(i);
    		}
            req.setAttribute("ent_year_set",entYearSet );

            // 入力値保持
            req.setAttribute("no", studentNo);
            req.setAttribute("errors", errors);

            // エラー時の画面に戻る
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // 学生情報取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);
        if (student != null ) {
	        // 成績データ取得
	        TestListStudentDao testDao = new TestListStudentDao();
	        testList = testDao.filter(student);
	        // 入力値保持
	        req.setAttribute("students", testList);
        }
        // セレクトボックス表示用
        ClassNumDao cNumDao = new ClassNumDao();
		List<String> classea = cNumDao.filter(teacher.getSchool());

		LocalDate todayDate = LocalDate.now(); //LocalDateインスタンスを取得
		int year = todayDate.getYear(); // 現在の年を取得
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後mでリストに追加
		for (int i = year - 10;i < year + 1; i ++){
			entYearSet.add(i);
		}

		// 科目
		SubjectDao subDao = new SubjectDao();
		List<Subject> subject = subDao.filter(teacher.getSchool());
		req.setAttribute("subject_set", subject);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classea);

		// 入力値の保持
		req.setAttribute("student", student);

        // 表示JSPへ遷移
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
		}
    }
}
