<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>科目情報削除</h2>

<p>『${subject.name}（${subject.cd}）』を削除してもよろしいですか</p>

<form action="SubjectDeleteExecute.action" method="post">
    <input type="hidden" name="cd" value="${subject.cd}">
    <button type="submit" style="background:red;color:white;">削除</button>
    <a href="SubjectList.action">戻る</a>
</form>

