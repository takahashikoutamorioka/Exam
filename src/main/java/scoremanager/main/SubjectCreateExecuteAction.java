package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.School;
import bean.Teacher;
import dao.Dao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // DB に直接 INSERT（DAO.save() は使わない）
        Connection con = new Dao().getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO subject (school_cd, cd, name) VALUES (?, ?, ?)"
        );
        ps.setString(1, school.getCd());
        ps.setString(2, cd);
        ps.setString(3, name);
        ps.executeUpdate();

        ps.close();
        con.close();

        request.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp")
               .forward(request, response);
    }
}
