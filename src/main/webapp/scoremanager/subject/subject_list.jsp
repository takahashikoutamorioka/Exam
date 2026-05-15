<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目管理一覧</title>
</head>g
<body>

<h2>科目管理一覧</h2>

<!-- 新規登録リンク -->
<p><a href="subject_create.jsp">新規登録</a></p>

<!-- 科目一覧テーブル -->
<table border="1">
    <tr>
        <th>科目コード</th>
        <th>科目名</th>
        <th>変更</th>
        <th>削除</th>
    </tr>

    <c:forEach var="sub" items="${subjectList}">
        <tr>
            <td>${sub.code}</td>
            <td>${sub.name}</td>
            <td><a href="subject_update.jsp?code=${sub.code}">変更</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
