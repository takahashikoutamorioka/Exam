<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目登録完了</title>
    <link rel="stylesheet" href="../../../../common/style.css">
</head>
<body>

<jsp:include page="../../../../common/header.jsp" />
<jsp:include page="../../../../common/sidebar.jsp" />

<main>
    <h2>科目登録完了</h2>

    <p>科目の登録が完了しました。</p>

    <div class="link-area">
        <a href="subject_create.jsp">科目登録画面に戻る</a><br>
        <a href="subject_list.jsp">科目一覧へ戻る</a>
    </div>
</main>

<jsp:include page="../../../../common/footer.jsp" />

</body>
</html>
