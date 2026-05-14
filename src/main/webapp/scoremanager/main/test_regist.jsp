<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <!-- フィルタフォーム -->
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-3 align-items-end rounded" id="filter">
                    <div class="col">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_list}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subject_list}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4">
                            <option value="0">--------</option>
                            <option value="1" <c:if test="${f4 == 1}">selected</c:if>>1回目</option>
                            <option value="2" <c:if test="${f4 == 2}">selected</c:if>>2回目</option>
                        </select>
                    </div>

                    <div class="col text-center">
                        <label class="form-label invisible">検索</label>
                        <button type="submit" class="btn btn-primary w-50">検索</button>
                    </div>
                </div>
            </form>

            <!-- 科目と回数の表示 -->
            <c:if test="${subject != null}">
                <div class="px-4 mb-3">
                    科目：${subject.name}（${f4}回）
                </div>
            </c:if>

           
            <form method="get" action="TestRegistExecute.action">

                <c:choose>
                    <c:when test="${students.size() > 0}">
                        <table class="table table-hover">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th class="text-center">点数</th>
                            </tr>

                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td class="text-center">
                                        <input type="text"
                                               name="point_${student.no}"
                                               class="form-control text-center"
                                               value="${param['point_'.concat(student.no)]}"
                                               style="width: 6rem; display:inline-block;" />

                                        
                                        <c:if test="${not empty errors and errors[student.no] != null}">
                                            <div class="text-warning small mt-1">
                                                ${errors[student.no]}
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        <div class="text-end mt-3">
                            <button type="submit" class="btn btn-secondary">登録して終了</button>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div>登録対象の学生情報が存在しませんでした。</div>
                    </c:otherwise>
                </c:choose>
            </form>

        </section>
    </c:param>
</c:import>
