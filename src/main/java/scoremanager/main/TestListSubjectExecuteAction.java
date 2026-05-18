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

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        // 入力チェック
        if (entYearStr == null || entYearStr.equals("0") ||
            classNum == null || classNum.equals("0") ||
            subjectCd == null || subjectCd.equals("0")) {

            req.setAttribute("error", "入学年度・クラス・科目を選択してください。");
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);

        StudentDao sDao = new StudentDao();
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();

        Subject subject = subDao.get(subjectCd, school);
        List<Student> students = sDao.filter(school, entYear, classNum, true);
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
