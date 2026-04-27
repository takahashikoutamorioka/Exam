package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao{
	private String baseSql = "select * from student where school_cd=?";

//	private String baseSql = "select * from student where school_cd=?";
	
	public Student get(String no) throws Exception{
		// 学生インスタンスを初期化
	    Student student = new Student();
	    // データベースへのコネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement("select * from student where no=?");
	        // プリペアードステートメントに学生番号をバインド
	        statement.setString(1, no);
	        // プリペアードステートメントを実行
	        ResultSet rSet = statement.executeQuery();

	        // 学校Daoを初期化
	        SchoolDao schoolDao = new SchoolDao();

	        if (rSet.next()) {
	            // データがセットされている場合
	            // 学生インスタンスに検索結果をセット
	            student.setNo(rSet.getString("no"));
	            student.setName(rSet.getString("name"));
	            student.setEntYear(rSet.getInt("ent_year"));
	            student.setClassNum(rSet.getString("class_num"));
	            student.setAttend(rSet.getBoolean("is_attend"));
	            // 学校フィールドには学校Daoで検索した学校インスタンスをセット
	            student.setSchool(schoolDao.get(rSet.getString("school_cd")));
	        } else {
	            // リザルトセットが存在しない場合
	            // 学生インスタンスにnullをセット
	            student = null;
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    return student;
	}

	
	private List<Student> postFilter (ResultSet rSet, School school) throws Exception{
		// リストを初期化
	    List<Student> list = new ArrayList<>();
	    try {
	        // リザルトセットを全権走査
	        while (rSet.next()) {
	            // 学生インスタンスを初期化
	            Student student = new Student();
	            // 学生インスタンスに検索結果をセット
	            student.setNo(rSet.getString("no"));
	            student.setName(rSet.getString("name"));
	            student.setEntYear(rSet.getInt("ent_year"));
	            student.setClassNum(rSet.getString("class_num"));
	            student.setAttend(rSet.getBoolean("is_attend"));
	            student.setSchool(school);
	            // リストに追加
	            list.add(student);
	        }
	    } catch (Exception e) {
	    	throw e;
	    }

	    return list;
	}
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		// リストを初期化
	    List<Student> list = new ArrayList<>();

	    // 接続を確認
	    Connection connection = getConnection();

	    if (connection == null) {
	        throw new Exception("connection failed");
	    }

	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        // SQL文を準備
	        String condition = "and ent_year=? and class_num=?";
	        String order = "order by no asc";
	        String conditionIsAttend = "";

	        if (isAttend) {
	            conditionIsAttend = "and is_attend=true";
	        }

	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

	        // バインド
	        statement.setString(1, school.getCd());
	        statement.setInt(2, entYear);
	        statement.setString(3, classNum);

	        // SQL実行
	        rSet = statement.executeQuery();

	        // 結果をリストに変換
	        list = postFilter(rSet, school);

	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
    	List<Student> list = new ArrayList<>();

    	Connection connection = getConnection();
        // 接続を確認
        if (connection == null) {
            throw new Exception("connection failed");
        }

        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // SQL文を準備
            String condition = "and ent_year=?";
            String order = "order by no asc";
            String conditionIsAttend = "";

            if (isAttend) {
                conditionIsAttend = "and is_attend=true";
            }

            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

            // バインド
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);

            // SQL実行
            rSet = statement.executeQuery();

            // リストへの格納処理を実行
            list = postFilter(rSet, school);

        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
    	// リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String order = " order by no asc";
        // SQL文の在学フラグ
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            // プリペアードステートメントに学園IDをバインド
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            // SQLの結果処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    	public boolean save(Student student) throws Exception {
    	    // コネクションを確立
    	    Connection connection = getConnection();
    	    // プリペアードステートメント
    	    PreparedStatement statement = null;
    	    // 実行件数
    	    int count = 0;

    	    try {
    	        // データベースから学生を取得
    	        Student old = get(student.getNo());
    	        if (old == null) {
    	            // 学生が存在しなかった場合
    	            // プリペアードステートメントにINSERT文をセット
    	        	statement = connection.prepareStatement(
    	        		    "update student set name=?, ent_year=?, class_num=?, is_attend=?, school_cd=? where no=?"
    	        		);

    	        		statement.setString(1, student.getName());
    	        		statement.setInt(2, student.getEntYear());
    	        		statement.setString(3, student.getClassNum());
    	        		statement.setBoolean(4, student.isAttend());
    	        		statement.setString(5, student.getSchool().getCd()); // ← 正しい
    	        		statement.setString(6, student.getNo());             // ← where no=?

    	        } else {
    	            // 学生が存在した場合
    	            // プリペアードステートメントにUPDATE文をセット
    	            statement = connection.prepareStatement(
    	            		"update student set name=?, ent_year=?, class_num=?, is_attend=?, school_cd=? where no=?"
    	            );
    	            statement.setString(1, student.getName());
    	            statement.setInt(2, student.getEntYear());
    	            statement.setString(3, student.getClassNum());
    	            statement.setBoolean(4, student.isAttend());
    	            statement.setString(5, student.getNo());
    	        }

    	        // プリペアードステートメントを実行
    	        count = statement.executeUpdate();

    	    } catch (Exception e) {
    	        throw e;
    	    } finally {
    	        // プリペアードステートメントを閉じる
    	        if (statement != null) {
    	            try {
    	                statement.close();
    	            } catch (SQLException sqle) {
    	                throw sqle;
    	            }
    	        }

    	        // コネクションを閉じる
    	        if (connection != null) {
    	            try {
    	                connection.close();
    	            } catch (SQLException sqle) {
    	                throw sqle;
    	            }
    	        }
    	    }

    	    if (count > 0) {
    	        // 実行件数が1件以上ある場合
    	        return true;
    	    } else {
    	        // 実行件数が0件の場合
    	        return false;
    	    }
    }
}
