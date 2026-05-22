<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績参照" />

    <c:param name="content">
        <div class="container mt-4">
            <section class="me-4">
                <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                    成績参照
                </h2>

                <!-- 科目検索フォーム -->
                <form action="TestListSubjectExecute.action" method="get">
                    <div class="border mx-3 mb-2 py-3 rounded">
                        <div class="row align-items-end">
                            <div class="col">
                                <label class="form-label">入学年度</label>
                                <select class="form-select" name="f1">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">クラス</label>
                                <select class="form-select" name="f2">
                                    <option value="0">--------</option>
                                    <c:forEach var="num" items="${class_num_list}">
                                        <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label">科目</label>
                                <select class="form-select" name="f3">
                                    <option value="0">--------</option>
                                    <c:forEach var="subject" items="${subject_list}">
                                        <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>
                                            ${subject.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col d-flex align-items-end">
                                <button type="submit" class="btn btn-primary px-4">検索</button>
                            </div>
                        </div>
                    </div>
                </form>
                <c:if test="${f4 == null or f4 == ''}">
				            <c:if test="${f1 == '0' or f2 == '0' or f3 == '0'}">
				                <div class="fw-bold ms-3 mt-3" style="color:red;">
				                    入学年度・クラス・科目を入力してください
				                </div>
				            </c:if>
		        </c:if>

                <!-- 学生番号検索フォーム -->
                <form action="TestListStudentExecute.action" method="get">
                    <div class="border mx-3 mb-2 py-3 rounded">
                        <div class="row align-items-end">
                            <div class="col">
                                <label class="form-label">学生番号</label>
                                <input type="text" name="f4" class="form-control"
                                       value="${f4}" placeholder="学生番号を入力してください" required/>
                            </div>
                            <div class="col d-flex align-items-end">
                                <button type="submit" class="btn btn-secondary px-4">検索</button>
                            </div>
                        </div>
                    </div>
                </form>

                <!-- ▼ 検索結果 -->
                <c:choose>

				    <c:when test="${not empty students}">
				        <div class="mx-3 mt-4">
				            <h5>検索結果</h5>
				            <table class="table table-bordered mt-3">
				                <thead class="table-light">
				                    <tr>
				                        <th>学生番号</th>
				                        <th>氏名</th>
				                        <th>科目</th>
				                        <th>回</th>
				                        <th>点数</th>
				                    </tr>
				                </thead>
				                <tbody>
				                    <c:forEach var="t" items="${tests}">
				                        <tr>
				                            <td>${t.student.no}</td>
				                            <td>${t.student.name}</td>
				                            <td>${t.subject.name}</td>
				                            <td>${t.no}</td>
				                            <td>${t.point}</td>
				                        </tr>
				                    </c:forEach>
				                </tbody>
				            </table>
				        </div>
				    </c:when>
				
				    <c:otherwise>
				
				        <c:if test="${not empty f4 and empty students}">
				            <div class="fw-bold ms-3 mt-3" style="color:black;">
				                学生情報が存在しませんでした。
				            </div>
				        </c:if>
				
				    </c:otherwise>
				
				</c:choose>


            </section>
        </div>
    </c:param>
</c:import>
