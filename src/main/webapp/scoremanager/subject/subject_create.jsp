<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目登録</title>
</head>
<body>

<h2>科目登録</h2>

<form action="subject_create_done.jsp" method="post">

    <label>科目コード：</label>
    <input type="text" name="subject_cd" maxlength="10" required>
    <br><br>

    <label>科目名：</label>
    <input type="text" name="subject_name" maxlength="30" required>
    <br><br>

    <input type="submit" value="登録する">

</form>

<br>
<a href="subject_list.jsp">科目一覧に戻る</a>

</body>
</html>
