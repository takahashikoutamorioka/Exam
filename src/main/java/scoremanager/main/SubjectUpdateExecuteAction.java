package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        School school = teacher.getSchool();
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, school);

        if (subject == null) {
            request.setAttribute("error", "科目が存在していません");
            request.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
                   .forward(request, response);
            return;
        }

        if (name == null || name.isBlank()) {
            request.setAttribute("error", "科目名を入力してください");
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
                   .forward(request, response);
            return;
        }

        // ★ DAO を変更せずに UPDATE を実行する
        Connection con = dao.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE subject SET name = ? WHERE school_cd = ? AND cd = ?"
        );
        ps.setString(1, name);
        ps.setString(2, school.getCd());
        ps.setString(3, cd);
        ps.executeUpdate();
        ps.close();
        con.close();

        subject.setName(name);
        request.setAttribute("subject", subject);

        request.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
               .forward(request, response);
    }
}



