package scoremanager.subject;

import java.io.IOException;
import java.util.List;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/scoremanager/main/subject/subject_list")
public class SubjectListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            School school = (School) session.getAttribute("school");

            if (school == null) {
                school = new School();
                school.setCd("s001");
                session.setAttribute("school", school);
            }

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school, true);

            request.setAttribute("subjectList", subjectList);

            request.getRequestDispatcher("/scoremanager/main/subject/subject_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

