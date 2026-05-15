<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目削除</title>
</head>
<body>

<h2>科目削除</h2>

<p>以下の科目を削除します。よろしいですか？</p>

<table border="1">
    <tr>
        <th>科目コード</th>
        <td>${subject.subject_cd}</td>
    </tr>
    <tr>
        <th>科目名</th>
        <td>${subject.subject_name}</td>
    </tr>
</table>

<br>

<form action="subject_delete_done" method="post">
    <!-- 削除対象の科目コードを送る -->
    <input type="hidden" name="subject_cd" value="${subject.subject_cd}">
    <button type="submit">削除する</button>
</form>

<br>
<a href="subject_list.jsp">科目一覧に戻る</a>

</body>
</html>
