<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
    <section class="me-4">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報登録
        </h2>

        <div class="w-75 mx-auto mt-4">

            <p class="fs-5 mb-4">登録が完了しました。</p>

            <div class="d-flex justify-content-between mt-4">
                <a href="SubjectCreate.action" class="btn btn-secondary">戻る</a>
                <a href="SubjectList.action" class="btn btn-primary">科目一覧</a>
            </div>

        </div>

    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content" value="${content}" />
</c:import>
