package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends Dao {

    // クラス番号一覧を取得
    public List<String> filter(String schoolCd) throws Exception {

        List<String> list = new ArrayList<>();

        Connection connection = getConnection();

        String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, schoolCd);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        rs.close();
        statement.close();
        connection.close();

        return list;
    }
}
