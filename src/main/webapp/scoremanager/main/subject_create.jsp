<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
    <section class="me-4">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目新規登録
        </h2>

        <form action="SubjectCreateExecute.action" method="post" class="w-75 mx-auto">

            <div class="mb-3">
                <label class="form-label">科目コード</label>
                <input type="text" name="cd" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">科目名</label>
                <input type="text" name="name" class="form-control" required>
            </div>

            <div class="d-flex justify-content-between mt-4">
                <button type="submit" class="btn btn-primary">登録</button>
                <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
            </div>

        </form>

    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content" value="${content}" />
</c:import>
