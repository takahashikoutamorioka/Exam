package scoremanager.main;

import java.io.IOException;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/subject/edit")
public class SubjectEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        School school = (School) session.getAttribute("school");
        String schoolCd = school.getCd();          // プロジェクトに合わせてプロパティ名は調整

        String cd = req.getParameter("cd");

        try {
            SubjectDao dao = new SubjectDao();
            Subject subject = dao.find(cd, schoolCd);

            if (subject == null) {
                // 「科目が存在していません」パターン
                req.setAttribute("error", "科目が存在していません");
                req.getRequestDispatcher("/WEB-INF/subject/subject_edit_error.jsp")
                   .forward(req, resp);
                return;
            }

            req.setAttribute("subject", subject);
            req.getRequestDispatcher("/WEB-INF/subject/subject_edit.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        School school = (School) session.getAttribute("school");
        String schoolCd = school.getCd();

        String cd   = req.getParameter("cd");
        String name = req.getParameter("name");

        // 科目名未入力エラー
        if (name == null || name.isBlank()) {
            Subject s = new Subject();
            s.setCd(cd);
            s.setName("");              // 画面はプレースホルダ「科目名を入力してください」
            s.setSchoolCd(schoolCd);

            req.setAttribute("error", "科目名を入力してください");
            req.setAttribute("subject", s);
            req.getRequestDispatcher("/WEB-INF/subject/subject_edit_error.jsp")
               .forward(req, resp);
            return;
        }

        try {
            SubjectDao dao = new SubjectDao();

            // 変更中に別画面から削除された場合のチェック
            Subject current = dao.find(cd, schoolCd);
            if (current == null) {
                req.setAttribute("error", "科目が存在していません");
                req.getRequestDispatcher("/WEB-INF/subject/subject_edit_error.jsp")
                   .forward(req, resp);
                return;
            }

            Subject s = new Subject();
            s.setCd(cd);
            s.setName(name);
            s.setSchoolCd(schoolCd);

            dao.update(s);

            // 変更完了画面へ
            req.getRequestDispatcher("/WEB-INF/subject/subject_edit_complete.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
