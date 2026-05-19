
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{
	private String baseSql = "select ent_year,student_no,student_name,class_num,subject_id,point FROM test_list_subject";
	
	private List<TestListSubject> postFilter (ResultSet rSet) throws Exception{
		List<TestListSubject> list = new ArrayList<>();
		
		while (rSet.next()) {
            TestListSubject bean = new TestListSubject();
            bean.setEntYear(rSet.getInt("ent_year"));
            bean.setStudentNo(rSet.getString("student_no"));
            bean.setStudentName(rSet.getString("student_name"));
            bean.setClassNum(rSet.getString("class_num"));
            bean.putPoint(rSet.getInt("subject_id"), rSet.getInt("point")); // ←ここがポイント！

            list.add(bean);
        }
        return list;
    }

    // 条件付き検索
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
	    List<TestListSubject> list = new ArrayList<>();
	    Connection connection = getConnection();

	    if (connection == null) {
	        throw new Exception("connection failed");
	    }

	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        String sql = "SELECT ent_year, student_no, student_name, class_num, subject_id, point "
	                   + "FROM test_list_subject "
	                   + "WHERE ent_year = ? AND class_num = ? AND school_cd = ? AND subject_id = ? "
	                   + "ORDER BY student_no ASC";

	        statement = connection.prepareStatement(sql);

	        // バインド
	        statement.setInt(1, entYear);
	        statement.setString(2, classNum);
	        statement.setString(3, school.getCd());
	        statement.setString(4, subject.getCd()); // ← 修正ポイント！

	        rSet = statement.executeQuery();

	        list = postFilter(rSet);

	    } finally {
	        if (rSet != null) rSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return list;
	}

}
