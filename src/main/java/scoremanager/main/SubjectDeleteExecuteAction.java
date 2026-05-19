package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");

        // ★ DAO の getConnection() を使う（DAO は変更しない）
        SubjectDao dao = new SubjectDao();
        String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";

        try (Connection con = dao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);
            ps.setString(2, school.getCd());
            ps.executeUpdate();
        }

        // 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
               .forward(request, response);
    }
}
