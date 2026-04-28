package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import bean.Score;

public class ScoreDao extends Dao {

    public List<Score> findByCondition(int year, int classNum, int subjectId, int times) throws Exception {
        // 入学年度・クラス・科目・回数で学生一覧＋既存成績を取得するSQLを書く
        // Student, Subject, Score テーブルをJOINするイメージ
        return new ArrayList<>();
    }

    public void saveScores(List<Score> scoreList) throws Exception {
        // point が null のものは INSERT/UPDATE しない
        // 既存レコードがあれば UPDATE、なければ INSERT という形にする
    }
}
