package scoremanager.main;

import java.io.IOException;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SubjectDeleteExecuteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            // ログインユーザ取得
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("user");

            if (teacher == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // 学校コード取得（Teacher → School → cd）
            School school = teacher.getSchoolCd();
            String schoolCd = school.getCd();

            // 削除対象の科目コード取得
            String cd = request.getParameter("cd");

            // DAO を使って削除
            SubjectDao dao = new SubjectDao();
            dao.delete(cd, schoolCd);

            // 完了画面へ
            request.getRequestDispatcher("subject/subject_delete_done.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}