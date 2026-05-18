<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目管理
                <a href="SubjectCreate.action" class="float-end text-decoration-none">新規登録</a>
            </h2>

            <table class="table table-hover mt-3 w-75 mx-auto">
                <thead class="table-light">
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th class="text-center">変更</th>
                        <th class="text-center">削除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subject_list}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                            <td class="text-center">
                                <a href="SubjectUpdate.action?cd=${subject.cd}" class="text-decoration-none">変更</a>
                            </td>
                            <td class="text-center">
                                <a href="SubjectDelete.action?cd=${subject.cd}" class="text-decoration-none">削除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </section>
    </c:param>
</c:import>
