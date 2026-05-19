package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = new ArrayList<>();

        // ★ SubjectDao の getConnection() を使う
        String sql = "SELECT cd FROM subject WHERE school_cd = ? ORDER BY cd";

        try (Connection con = sDao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cd = rs.getString("cd");
                Subject s = sDao.get(cd, school);
                if (s != null) {
                    subjectList.add(s);
                }
            }
        }

        request.setAttribute("subject_list", subjectList);
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
