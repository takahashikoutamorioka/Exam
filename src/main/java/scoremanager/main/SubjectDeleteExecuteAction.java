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

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");

        // 削除処理（DAOを使う）
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);
        sDao.delete(subject);

        // 削除完了メッセージ
        request.setAttribute("message", "削除が完了しました");

        // 一覧再取得（is_trueを使わず、全件取得）
        List<Subject> subjectList = new ArrayList<>();
        Connection con = sDao.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT cd, name FROM subject WHERE school_cd = ? ORDER BY cd"
        );
        ps.setString(1, school.getCd());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Subject s = new Subject();
            s.setCd(rs.getString("cd"));
            s.setName(rs.getString("name"));
            s.setSchool(school);
            subjectList.add(s);
        }

        rs.close();
        ps.close();
        con.close();

        // JSPへ渡す
        request.setAttribute("subject_list", subjectList);
        request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
               .forward(request, response);
    }
}
