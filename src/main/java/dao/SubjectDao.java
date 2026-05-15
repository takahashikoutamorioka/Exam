package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // 1件取得
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject();
        subject.setSchool(school);

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "SELECT * FROM subject WHERE school_id = ? AND cd = ?"
            );
            statement.setString(1, school.getCd());
            statement.setString(2, cd);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
            } else {
                subject = null;
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return subject;
    }

    // 一覧取得
    public List<Subject> filter(School school, boolean filter) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(
                "SELECT * FROM subject WHERE school_id = ? AND delete_flag = ?"
            );
            statement.setString(1, school.getCd());
            statement.setBoolean(2, filter);

            rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    // 保存（INSERT or UPDATE）
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存チェック
            PreparedStatement check = connection.prepareStatement(
                "SELECT cd FROM subject WHERE school_id = ? AND cd = ?"
            );
            check.setString(1, subject.getSchool().getCd());
            check.setString(2, subject.getCd());
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                // UPDATE
                statement = connection.prepareStatement(
                    "UPDATE subject SET name = ?, delete_flag = ? WHERE school_id = ? AND cd = ?"
                );
                statement.setString(1, subject.getName());
                statement.setBoolean(2, true);
                statement.setString(3, subject.getSchool().getCd());
                statement.setString(4, subject.getCd());

            } else {
                // INSERT
                statement = connection.prepareStatement(
                    "INSERT INTO subject (school_id, cd, name, delete_flag) VALUES (?, ?, ?, ?)"
                );
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
                statement.setBoolean(4, true);
            }

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    // 削除（フラグ変更）
    public boolean delete(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "UPDATE subject SET delete_flag = ? WHERE school_id = ? AND cd = ?"
            );
            statement.setBoolean(1, false);
            statement.setString(2, subject.getSchool().getCd());
            statement.setString(3, subject.getCd());

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    // 復元
    public boolean change(String cd, boolean back) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "UPDATE subject SET delete_flag = true WHERE cd = ?"
            );
            statement.setString(1, cd);
            statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return true;
    }
}
