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
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
    throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // ▼ パラメータ取得
        String entYearStr = req.getParameter("f1");
        String classNum   = req.getParameter("f2");
        String subjectCd  = req.getParameter("f3");
        String noStr      = req.getParameter("f4");

        int entYear = 0;
        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        // ▼ DAO
        StudentDao sDao = new StudentDao();
        SubjectDao subDao = new SubjectDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // ▼ 科目一覧
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

        // ▼ 選択された科目
        Subject subject = null;
        if (subjectCd != null && !subjectCd.equals("0")) {
            subject = subDao.get(subjectCd, school);
        }

        // ▼ 学生リスト
        List<Student> students = new ArrayList<>();
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = sDao.filter(school, entYear, classNum, true);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(school, entYear, true);
        } else {
            students = sDao.filter(school, true);
        }

        // ▼ 入学年度リスト
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // ▼ クラス番号リスト
        List<String> classNumList = cNumDao.filter(school);

        // ▼ JSP に渡す
        req.setAttribute("students", students);
        req.setAttribute("subject", subject);
        req.setAttribute("subject_list", subjectList);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", noStr);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_list", classNumList);

        // ▼ ★★★ ExecuteAction が必要とする値を session に保存 ★★★
        session.setAttribute("students", students);
        session.setAttribute("subject", subject);
        session.setAttribute("f4", noStr);
        session.setAttribute("school", school);

        session.setAttribute("ent_year_set", entYearSet);
        session.setAttribute("class_num_list", classNumList);
        session.setAttribute("subject_list", subjectList);
        session.setAttribute("f1", entYearStr);
        session.setAttribute("f2", classNum);
        session.setAttribute("f3", subjectCd);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
