<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生管理
            </h2>

            <form action="StudentCreateExcite.action" method="post" class="mt-3">

                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <input type="text" name="entYear" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="no" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <input type="text" name="classNum" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">在学中</label>
                    <select name="isAttend" class="form-select">
                        <option value="1">○</option>
                        <option value="0">×</option>
                    </select>
                </div>

                <button class="btn btn-primary">登録して終了</button>

            </form>

        </section>
    </c:param>
</c:import>
