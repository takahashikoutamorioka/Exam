package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    HttpSession session = request.getSession();
	    Teacher teacher = (Teacher)session.getAttribute("user");

	    String entYearStr = request.getParameter("f1");
	    String classNum = request.getParameter("f2");
	    String isAttendStr = request.getParameter("f3");

	    int entYear = 0;
	    boolean isAttend = false;

	    if (entYearStr != null && !entYearStr.equals("0")) {
	        entYear = Integer.parseInt(entYearStr);
	    }

	    if (isAttendStr != null) {
	        isAttend = true;
	    }

	    StudentDao sDao = new StudentDao();
	    ClassNumDao cNumDao = new ClassNumDao();

	    List<Student> students;

	    if (entYear != 0 && !classNum.equals("0")) {
	        students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
	    } else if (entYear != 0) {
	        students = sDao.filter(teacher.getSchool(), entYear, isAttend);
	    } else {
	        students = sDao.filter(teacher.getSchool(), isAttend);
	    }

	    // 入学年度リストを作成
	    LocalDate today = LocalDate.now();
	    int year = today.getYear();
	    List<Integer> entYearSet = new ArrayList<>();
	    for (int i = year - 10; i <= year; i++) {
	        entYearSet.add(i);
	    }

	    request.setAttribute("f1", entYear);
	    request.setAttribute("f2", classNum);
	    request.setAttribute("f3", isAttendStr);

	    request.setAttribute("students", students);
	    request.setAttribute("class_num_list", cNumDao.filter(teacher.getSchool().getCd()));
	    request.setAttribute("ent_year_set", entYearSet);

	    request.getRequestDispatcher("student_list.jsp").forward(request, response);
	}

}
