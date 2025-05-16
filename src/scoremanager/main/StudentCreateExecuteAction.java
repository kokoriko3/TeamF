package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect("../Login.action");
            return;
        }

        // リクエストパラメータ取得
        String entYearStr = req.getParameter("ent_year");
        String noStr = req.getParameter("no");
        String name = req.getParameter("name");
        String classNumStr = req.getParameter("class_num");

        // Dao 初期化
        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();

        // 入学年度・クラスリストの再取得（エラー時用）
        List<String> classNumSet = cDao.filter(teacher.getSchool());
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i < year + 10; i++) {
            entYearSet.add(i);
        }

        // バリデーション
        if (entYearStr.equals("0")) {
            req.setAttribute("errorEntYear", "入学年度を選択してください");
            req.setAttribute("no", noStr);
            req.setAttribute("name", name);
            req.setAttribute("class_num_set", classNumSet);
            req.setAttribute("ent_year_set", entYearSet);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        if (sDao.get(noStr) != null) {
            req.setAttribute("errorNo", "学生番号が重複しています");
            req.setAttribute("no", noStr);
            req.setAttribute("name", name);
            req.setAttribute("class_num_set", classNumSet);
            req.setAttribute("ent_year_set", entYearSet);
            req.getRequestDispatcher("student_create.jsp").forward(req, res);
            return;
        }

        // 登録処理
        Student student = new Student();
        student.setNo(noStr);
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(classNumStr);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        sDao.save(student);
        req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
    }
}
