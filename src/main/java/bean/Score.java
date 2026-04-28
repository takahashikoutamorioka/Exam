package bean;

public class Score {
    private int studentId;
    private int subjectId;
    private int times;      // 回数
    private Integer point;  // 得点（null許容：ブランク対応）

    // 必要なら学生名や科目名も持たせてもOK（JOIN結果用）
    private String studentName;
    private String subjectName;

    // getter / setter
}
