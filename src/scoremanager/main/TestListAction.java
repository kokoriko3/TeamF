package scoremanager.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		StudentDao StudentDao = new StudentDao();

		List<Student> students = StudentDao.filter(teacher.getSchool(), true); // 例：在学中の全生徒
		Set<Integer> entYears = new TreeSet<>(Collections.reverseOrder()); // 重複なし＆降順にしたいならTreeSet使う

		for (Student stu : students) {
			entYears.add(stu.getEntYear());
		}


		List<String> classea = cNumDao.filter(teacher.getSchool());
		List<Subject> subjects = subDao.filter(teacher.getSchool());

		// ★ 科目名だけの Set（重複なし）
		Set<String> subjectNamesSet = new LinkedHashSet<>();
		for (Subject sub : subjects) {
		    subjectNamesSet.add(sub.getName());
		}

		// ★ JSP に渡すために List に変換（順番を保つならこれが無難）
		List<String> subNames = new ArrayList<>(subjectNamesSet);

		req.setAttribute("ent_year_set", entYears);
		req.setAttribute("class_num_set", classea);
		req.setAttribute("subject_string_set", subNames);

		req.getRequestDispatcher("test_list.jsp").forward(req,res);
	}

}
