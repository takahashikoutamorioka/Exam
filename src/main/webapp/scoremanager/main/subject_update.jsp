<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
    <section class="me-4">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            科目情報変更
        </h2>

        <div class="w-75 mx-auto mt-4">

            <c:if test="${not empty error}">
                <p class="text-danger mb-3">${error}</p>
            </c:if>


            <form action="SubjectUpdateExecute.action" method="post">

                <!-- 科目コード（変更不可） -->
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input type="text" name="cd" value="${subject.cd}" class="form-control" readonly>
                </div>

                <!-- 科目名（変更可能） -->
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" name="name" value="${subject.name}"
                           class="form-control" placeholder="科目名を入力してください" required>
                </div>

                <button type="submit" class="btn btn-primary px-4">
                    変更
                </button>

                <a href="SubjectList.action" class="btn btn-secondary ms-3">
                    戻る
                </a>

            </form>

        </div>

    </section>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="科目情報変更" />
    <c:param name="content" value="${content}" />
</c:import>
