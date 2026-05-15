<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目変更</title>
</head>
<body>

<h2>科目変更</h2>

<form action="subject_update_done" method="post">

    <!-- 科目コード（変更不可） -->
    <label>科目コード：</label>
    <input type="text" name="subject_cd" value="${subject.subject_cd}" readonly><br><br>

    <!-- 科目名（変更可能） -->
    <label>科目名：</label>
    <input type="text" name="subject_name" value="${subject.subject_name}" required><br><br>

    <button type="submit">変更する</button>
</form>

<br>
<a href="subject_list.jsp">科目一覧に戻る</a>

</body>
</html>
