<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>科目情報変更</h2>

<p style="color:red">${error}</p>

<form action="SubjectUpdateExecute.action" method="post">
    <label>科目コード</label><br>
    <input type="text" name="cd" value="${subject.cd}" readonly><br><br>

    <label>科目名</label><br>
    <input type="text" name="name"
           value="${subject.name}"
           placeholder="科目名を入力してください" required><br><br>

    <button type="submit">変更</button>
    <a href="SubjectList.action">戻る</a>
</form>
