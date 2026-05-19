<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
    <section class="me-4">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報削除
        </h2>


        <div class="w-75 mx-auto mt-4">

            <p class="fs-5 mb-4">
                「${subject.name}（${subject.cd}）」を削除してもよろしいですか？
            </p>

            <form action="SubjectDeleteExecute.action" method="post">
                <input type="hidden" name="cd" value="${subject.cd}">

                <button type="submit" class="btn btn-danger px-4">
                    削除
                </button>

                <a href="SubjectList.action" class="btn btn-secondary ms-3">
                    戻る
                </a>
            </form>

        </div>

    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="科目情報削除" />
    <c:param name="content" value="${content}" />
</c:import>

