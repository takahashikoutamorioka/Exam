package scoremanager.main;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
    throws Exception {

        HttpSession session = req.getSession();

        
        School school = (School) session.getAttribute("school");
        Subject subject = (Subject) session.getAttribute("subject");
        String f4 = (String) session.getAttribute("f4");

        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) session.getAttribute("students");

        Map<String, String> errors = new HashMap<>();
        TestDao dao = new TestDao();
        Connection connection = dao.getConnection();

        for (Student stu : students) {
            String pointStr = req.getParameter("point_" + stu.getNo());

            if (pointStr == null || pointStr.isEmpty()) {
                continue;
            }

            try {
                int point = Integer.parseInt(pointStr);

                if (point < 0 || point > 100) {
                    errors.put(stu.getNo(), "0〜100の範囲で入力してください");
                    continue;
                }

                Test test = new Test();
                test.setStudent(stu);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(Integer.parseInt(f4));
                test.setPoint(point);

                dao.save(test, connection);

            } catch (NumberFormatException e) {
                errors.put(stu.getNo(), "数値を入力してください");
            }
        }

        connection.close();

        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);
            req.setAttribute("students", students);
            req.setAttribute("subject", subject);
            req.setAttribute("f4", f4);

            req.setAttribute("ent_year_set", session.getAttribute("ent_year_set"));
            req.setAttribute("class_num_list", session.getAttribute("class_num_list"));
            req.setAttribute("subject_list", session.getAttribute("subject_list"));
            req.setAttribute("f1", session.getAttribute("f1"));
            req.setAttribute("f2", session.getAttribute("f2"));
            req.setAttribute("f3", session.getAttribute("f3"));

            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
