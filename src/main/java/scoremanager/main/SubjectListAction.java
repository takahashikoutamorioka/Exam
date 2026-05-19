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

        // ★ subject テーブルの全科目コードを取得
        List<String> subjectCodes = new ArrayList<>();

        Connection con = sDao.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT cd FROM subject WHERE school_cd = ? ORDER BY cd"
        );
        ps.setString(1, school.getCd());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            subjectCodes.add(rs.getString("cd"));
        }

        rs.close();
        ps.close();
        con.close();

        // ★ 全科目を get() で取得
        List<Subject> subjectList = new ArrayList<>();
        for (String cd : subjectCodes) {
            Subject s = sDao.get(cd, school);
            if (s != null) subjectList.add(s);
        }

        // JSP に渡す
        request.setAttribute("subject_list", subjectList);
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
