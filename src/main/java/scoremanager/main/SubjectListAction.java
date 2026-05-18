package scoremanager.main;

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

        // ★ DAOのfilter()を使わず、get()で科目一覧を手動作成
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(sDao.get("css", school));
        subjectList.add(sDao.get("eng", school));
        subjectList.add(sDao.get("jav", school));
        subjectList.add(sDao.get("kat", school));
        subjectList.add(sDao.get("kok", school));
        subjectList.add(sDao.get("pyt", school));
        subjectList.add(sDao.get("rik", school));
        subjectList.add(sDao.get("suu", school));

        subjectList.removeIf(s -> s == null);

        request.setAttribute("subject_list", subjectList);


        // nullを除外（存在しない科目コードがあっても安全）
        subjectList.removeIf(s -> s == null);

        // JSPへ渡す
        request.setAttribute("subject_list", subjectList);

        // 科目一覧画面へフォワード
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
