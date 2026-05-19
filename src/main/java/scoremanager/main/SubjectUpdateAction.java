package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 未ログイン
        if (teacher == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 学校コード取得
        School school = teacher.getSchool();
        String cd = request.getParameter("cd");

        // 科目取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, school);

        // 科目が存在しない場合
        if (subject == null) {
            request.setAttribute("error", "科目が存在していません");
            request.getRequestDispatcher("/scoremanager/main/subject_update_error.jsp")
                   .forward(request, response);
            return;
        }

        // JSP に渡す
        request.setAttribute("subject", subject);

        // 変更画面へ
        request.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(request, response);
    }
}



