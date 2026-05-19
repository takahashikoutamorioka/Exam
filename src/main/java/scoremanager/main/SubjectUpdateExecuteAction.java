package scoremanager.main;

import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        req.setCharacterEncoding("UTF-8");

        User user = (User) req.getSession().getAttribute("user");
        String schoolCd = user.getSchool().getCd();

        String cd   = req.getParameter("cd");
        String name = req.getParameter("name");

        // 科目名未入力エラー
        if (name == null || name.isBlank()) {
            Subject s = new Subject();
            s.setCd(cd);
            s.setName("");
            s.setSchoolCd(schoolCd);

            req.setAttribute("error", "科目名を入力してください");
            req.setAttribute("subject", s);
            return "subject_update_error.jsp";
        }

        SubjectDao dao = new SubjectDao();

        // 変更中に削除された場合
        Subject current = dao.find(cd, schoolCd);
        if (current == null) {
            req.setAttribute("error", "科目が存在していません");
            return "subject_update_error.jsp";
        }

        Subject s = new Subject();
        s.setCd(cd);
        s.setName(name);
        s.setSchoolCd(schoolCd);

        dao.update(s);

        return "subject_update_complete.jsp";
    }
}

