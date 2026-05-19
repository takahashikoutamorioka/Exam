<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
    <section class="me-4">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報変更
        </h2>

        <div class="w-75 mx-auto mt-4">

            <div class="alert alert-success">
                「${subject.name}（${subject.cd}）」の変更が完了しました。
            </div>

            <a href="SubjectList.action" class="btn btn-secondary mt-3">
                科目一覧へ戻る
            </a>

        </div>

    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="科目情報変更完了" />
    <c:param name="content" value="${content}" />
</c:import>



