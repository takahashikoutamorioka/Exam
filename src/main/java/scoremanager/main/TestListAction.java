package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

    	
    	
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // DAO
        ClassNumDao cDao = new ClassNumDao();
        StudentDao sDao = new StudentDao();
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();

        // パラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // 学生番号

        // ▼ 入学年度リスト（TestRegistAction と同じ方式）
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // ▼ 科目一覧（SubjectDao.filter() は is_true で落ちるため使わない）
        List<Subject> subjectList = new ArrayList<>();
        Connection con = subDao.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "select cd from subject where school_cd=? order by cd"
        );
        ps.setString(1, school.getCd());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String cd = rs.getString("cd");
            Subject s = subDao.get(cd, school);
            subjectList.add(s);
        }
        rs.close();
        ps.close();
        con.close();

        // ▼ 初期表示
        if (f1 == null && f2 == null && f3 == null && f4 == null) {

            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_num_list", cDao.filter(school));
            req.setAttribute("subject_list", subjectList);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // ▼ 学生別成績照会
        if (f4 != null && !f4.isEmpty()) {

            Student student = sDao.get(f4);

            if (student == null) {
                req.setAttribute("error", "学生番号が存在しません。");
            } else {
                List<Test> tests = new ArrayList<>();

                for (Subject subject : subjectList) {
                    Test t1 = tDao.get(student, subject, school, 1);
                    Test t2 = tDao.get(student, subject, school, 2);
                    if (t1 != null) tests.add(t1);
                    if (t2 != null) tests.add(t2);
                }

                req.setAttribute("students", List.of(student));
                req.setAttribute("tests", tests);
            }

            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_num_list", cDao.filter(school));
            req.setAttribute("subject_list", subjectList);
            req.setAttribute("f4", f4);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // ▼ 科目別成績照会
        if (!"0".equals(f1) && !"0".equals(f2) && !"0".equals(f3)) {

            int entYear = Integer.parseInt(f1);
            String classNum = f2;
            String subjectCd = f3;

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

            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_num_list", cDao.filter(school));
            req.setAttribute("subject_list", subjectList);

            req.setAttribute("f1", f1);
            req.setAttribute("f2", f2);
            req.setAttribute("f3", f3);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // ▼ 入力不足
        req.setAttribute("error", "入学年度・クラス・科目 または 学生番号を入力してください。");

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_list", cDao.filter(school));
        req.setAttribute("subject_list", subjectList);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
