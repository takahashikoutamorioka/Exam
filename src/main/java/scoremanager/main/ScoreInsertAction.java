package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;
import dao.ScoreDao;

public class ScoreInsertAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] studentIds = request.getParameterValues("studentId");
        String[] points = request.getParameterValues("point");

        int year = Integer.parseInt(request.getParameter("year"));
        int classNum = Integer.parseInt(request.getParameter("classNum"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int times = Integer.parseInt(request.getParameter("times"));

        ScoreDao dao = new ScoreDao();

        for (int i = 0; i < studentIds.length; i++) {

            String p = points[i];

            // ブランクなら保存しない（関連情報）
            if (p == null || p.isEmpty()) {
                continue;
            }

            int point = Integer.parseInt(p);

            // 代替フロー②：0〜100チェック
            if (point < 0 || point > 100) {
                request.setAttribute("error", "0～100の範囲で入力してください");
                return "scoreInput.jsp";
            }

            dao.insert(year, classNum, subjectId, times, Integer.parseInt(studentIds[i]), point);
        }

        return "scoreComplete.jsp";
    }
}