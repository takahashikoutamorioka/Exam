package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションからログインユーザ取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");

        if (teacher == null) {
            return "login.jsp";   // 未ログインならログイン画面へ
        }

        // 学校コード取得（Teacher → School → cd）
        School school = teacher.getSchoolCd();
        String schoolCd = school.getCd();

        // パラメータ（科目コード）取得
        String cd = request.getParameter("cd");

        // DAO で科目情報を取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.find(cd, schoolCd);

        // 科目が存在しない場合（画面設計書のエラー画面へ）
        if (subject == null) {
            request.setAttribute("error", "科目が存在していません");
            return "subject_delete_error.jsp";
        }

        // JSP に渡す
        request.setAttribute("subject", subject);

        // 削除確認画面へ
        return "subject_delete.jsp";
    }
}


