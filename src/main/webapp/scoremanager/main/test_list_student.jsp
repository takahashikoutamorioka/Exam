<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">



            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧
                <c:choose>
                    <c:when test="${not empty f4}">（学生）</c:when>
                    <c:otherwise>（科目）</c:otherwise>
                </c:choose>
            </h2>

            <%-- 検索フォーム --%>
            <form action="TestList.action" method="get" class="border mx-3 mb-3 py-3 rounded">
            	<div class="border mx-3 mb-3 py-3 rounded">
                    <div class="row align-items-end">

                        <!-- 入学年度 -->
                        <div class="col">
                            <label class="form-label" for="f1">入学年度</label>
                            <select class="form-select" id="f1" name="f1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- クラス -->
                        <div class="col">
                            <label class="form-label" for="f2">クラス</label>
                            <select class="form-select" id="f2" name="f2">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_list}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- 科目 -->
                        <div class="col">
                            <label class="form-label" for="f3">科目</label>
                            <select class="form-select" id="f3" name="f3">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subject_list}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- 科目検索ボタン -->
                        <div class="col d-flex align-items-end justify-content-start">
			                <button type="submit"
			                        class="btn btn-primary w-30"
			                        style="margin-left:-10px;">
			                    検索
			                </button>
			            </div>

                    </div>
                </div>
                <div class="border mx-3 mb-3 py-3 rounded">
                    <div class="row align-items-end">

                        <!-- 学生番号 -->
                        <div class="col">
                            <label class="form-label" for="f4">学生番号</label>
                            <input type="text" id="f4" name="f4" class="form-control"
                                   value="${f4}" placeholder="学生番号を入力してください" />
                        </div>

                        <!-- 学生検索ボタン -->
                        <div class="col d-flex align-items-end justify-content-start">
                            <button type="submit"
                                    class="btn btn-secondary w-20 py-2 fs-6"
                                    style="margin-left:-10px;">
                                検索
                            </button>
                        </div>

                    </div>
                </div>
            </form>

            <%-- ▼ 表示切り替え --%>
            <c:choose>

                <%-- 学生別成績一覧 --%>
                <c:when test="${not empty f4}">
                    <h5 class="px-4 mb-3">氏名：${students[0].name}（${students[0].no}）</h5>

                    <table class="table table-hover mt-3">
                        <thead class="table-light">
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.subject.name}</td>
                                    <td>${test.subject.cd}</td>
                                    <td>${test.no}</td>
                                    <td>${test.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <%-- 科目別成績一覧 --%>
                <c:otherwise>
                    <h5 class="px-4 mb-3">科目：${subject_list[0].name}</h5>

                    <table class="table table-hover mt-3">
                        <thead class="table-light">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>

                                    <td>
                                        <c:forEach var="test" items="${tests}">
                                            <c:if test="${(test.student.no eq student.no) and (test.no eq 1)}">
                                                ${test.point}
                                            </c:if>
                                        </c:forEach>
                                    </td>

                                    <td>
                                        <c:forEach var="test" items="${tests}">
                                            <c:if test="${(test.student.no eq student.no) and (test.no eq 2)}">
                                                ${test.point}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>

            </c:choose>

        </section>
    </c:param>
</c:import>
