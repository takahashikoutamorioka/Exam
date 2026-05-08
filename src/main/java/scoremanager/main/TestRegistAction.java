package scoremanager.main;

import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		
		String f1 = req.getParameter("f1");
		String f2 = req.getParameter("f2");
		String f3 = req.getParameter("f3");
		String f4 = req.getParameter("f4");
		
		//null対策
		if (f1 == null) f1 = "0";
		if (f2 == null) f2 = "0";
		if (f3 == null) f3 = "0";
		if (f4 == null) f4 = "0";
		
		TestListSubjectDao dao = new TestListSubjectDao();
	}
}
