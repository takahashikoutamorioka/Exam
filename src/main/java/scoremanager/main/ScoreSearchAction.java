package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import bean.Score;
import bean.dao.ScoreDao;
import tool.Action;

public class ScoreSearchAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String yearStr   = request.getParameter("year");
        String classStr  = request.getParameter("classNum");
        String subjectStr= request.getParameter("subjectId");
        String timesStr  = request.getParameter("times");

        // 代替フロー①：いずれか未入力
        if (yearStr == null || yearStr.isEmpty()
         || classStr == null || classStr.isEmpty()
         || subjectStr == null || subjectStr.isEmpty()
         || timesStr == null || timesStr.isEmpty()) {

            request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            return "scoreSearch.jsp"; // 基本フロー③へ戻るイメージ
        }

        int year   = Integer.parseInt(yearStr);
        int classNum = Integer.parseInt(classStr);
        int subjectId = Integer.parseInt(subjectStr);
        int times  = Integer.parseInt(timesStr);

        ScoreDao dao = new ScoreDao();
        List<Score> list = dao.findByCondition(year, classNum, subjectId, times);

        request.setAttribute("scoreList", list);
        request.setAttribute("year", year);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("times", times);

        return "scoreInput.jsp"; // 基本フロー④
    }
}
