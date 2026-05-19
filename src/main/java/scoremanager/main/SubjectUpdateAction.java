package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ログインユーザ取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        if (teacher == null) {
            return "login.jsp";
        }

        // 学校コード取得
        School school = teacher.getSchoolCd();
        String schoolCd = school.getCd();

        // パラメータ（科目コード）
        String cd = request.getParameter("cd");

        // 科目取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.find(cd, schoolCd);

        if (subject == null) {
            request.setAttribute("error", "科目が存在していません");
            return "subject_update_error.jsp";
        }

        // JSP に渡す
        request.setAttribute("subject", subject);

        // ここが重要！！ forward ではなく return
        return "subject_update.jsp";
    }
}


