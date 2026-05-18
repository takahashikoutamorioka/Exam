package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	    School school = teacher.getSchool();

	    String studentNo = req.getParameter("f4");

	    StudentDao sDao = new StudentDao();
	    SubjectDao subDao = new SubjectDao();
	    TestDao tDao = new TestDao();

	    
	    // ★ 学生番号が入力されている場合 → 学生別成績一覧
	    if (studentNo != null && !studentNo.trim().isEmpty()) {

	        Student student = sDao.get(studentNo);

	        
	        // ★ filter() を使わず、科目一覧を get() で作る
	        List<Subject> subjects = new ArrayList<>();
	        subjects.add(subDao.get("css", school));
	        subjects.add(subDao.get("eng", school));
	        subjects.add(subDao.get("jav", school));
	        subjects.add(subDao.get("kat", school));
	        subjects.add(subDao.get("kok", school));
	        subjects.add(subDao.get("pyt", school));
	        subjects.add(subDao.get("rik", school));
	        subjects.add(subDao.get("suu", school));


	        List<Test> tests = new ArrayList<>();
	        for (Subject subject : subjects) {
	            if (subject == null) continue;

	            Test t1 = tDao.get(student, subject, school, 1);
	            Test t2 = tDao.get(student, subject, school, 2);
	            if (t1 != null) tests.add(t1);
	            if (t2 != null) tests.add(t2);
	        }

	        req.setAttribute("students", List.of(student));
	        req.setAttribute("tests", tests);
	        req.setAttribute("subject_list", subjects);
	        req.setAttribute("f4", studentNo);

	        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	        return;
	    }

	    // ★ 学生番号が空の場合 → 科目別成績一覧（こちらは filter() を使わない）
	    String subjectCd = req.getParameter("f3");
	    Subject subject = subDao.get(subjectCd, school);

	    List<Student> students = sDao.filter(school, true); // これは filter() でOK
	    List<Test> tests = new ArrayList<>();

	    for (Student s : students) {
	        Test t1 = tDao.get(s, subject, school, 1);
	        Test t2 = tDao.get(s, subject, school, 2);
	        if (t1 != null) tests.add(t1);
	        if (t2 != null) tests.add(t2);
	    }

	    req.setAttribute("students", students);
	    req.setAttribute("tests", tests);
	    req.setAttribute("subject_list", List.of(subject));

	    req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
