<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目削除確認</title>
</head>
<body>

<h2>科目削除確認</h2>

<p>以下の科目を削除します。よろしいですか？</p>

<table border="1">
    <tr>
        <th>科目コード</th>
        <td>${subject.cd}</td>
    </tr>
    <tr>
        <th>科目名</th>
        <td>${subject.name}</td>
    </tr>
</table>

<form action="SubjectDeleteExecute.action" method="post">

    <!-- 削除対象の科目コード -->
    <input type="hidden" name="cd" value="${subject.cd}">

    <!-- 学校コード（hidden） -->
    <input type="hidden" name="schoolCd" value="${subject.schoolCd}">

    <div>
        <input type="submit" value="削除">
        <a href="SubjectList.action">戻る</a>
    </div>

</form>

</body>
</html>

