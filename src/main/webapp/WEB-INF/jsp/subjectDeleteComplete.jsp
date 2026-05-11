<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目削除完了</title>

<style>
    body {
        font-family: sans-serif;
        background: #f5f5f5;
    }
    .container {
        width: 500px;
        margin: 100px auto;
        padding: 40px;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 0 10px #ccc;
        text-align: center;
    }
    h1 {
        color: #d9534f;
        margin-bottom: 20px;
    }
    .btn {
        display: inline-block;
        padding: 12px 25px;
        background: #007acc;
        color: #fff;
        text-decoration: none;
        border-radius: 6px;
        margin-top: 20px;
    }
</style>

</head>
<body>

<div class="container">
    <h1>科目の削除が完了しました</h1>

    <p>選択された科目は正常に削除されました。</p>

    <a href="menu.action" class="btn">メニューに戻る</a>
</div>

</body>
</html>
