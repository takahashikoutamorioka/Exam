package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // ▼ 成績保存（PostgreSQL対応）
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
            statement.setString(6, test.getStudent().getClassNum());

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
        }

        return count > 0;
    }

    // ▼ 成績取得（TestListAction が必要とする get()）
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        Test test = null;

        try {
            statement = connection.prepareStatement(
                "SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?"
            );

            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);

            rSet = statement.executeQuery();

            if (rSet.next()) {
                test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(no);
                test.setPoint(rSet.getInt("point"));
            }

        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return test;
    }
}
