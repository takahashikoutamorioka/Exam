package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Test;

public class TestDao extends Dao{
	public boolean save(Test test, Connection connection) throws Exception {
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        statement = connection.prepareStatement(
	            "INSERT INTO test(student_no, subject_cd, school_cd, no, point, class_num) " +
	            "VALUES (?, ?, ?, ?, ?, ?) " +
	            "ON CONFLICT (student_no, subject_cd, school_cd, no) " +
	            "DO UPDATE SET point = EXCLUDED.point, class_num = EXCLUDED.class_num"
	        );

	        statement.setString(1, test.getStudent().getNo());
	        statement.setString(2, test.getSubject().getCd());
	        statement.setString(3, test.getSchool().getCd());
	        statement.setInt(4, test.getNo());
	        statement.setInt(5, test.getPoint());
	        statement.setString(6, test.getStudent().getClassNum()); // ← class_numを更新対象に追加

	        count = statement.executeUpdate();

	    } finally {
	        if (statement != null) statement.close();
	    }

	    return count > 0;
	}

}
