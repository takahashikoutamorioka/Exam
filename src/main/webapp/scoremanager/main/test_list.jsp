<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績参照" />

    <c:param name="content">
        <div class="container mt-4">


            <!-- ① 科目検索フォーム -->
            <form action="TestListSubjectExecute.action" method="get">
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
            </form>


            <!-- ② 学生番号検索フォーム -->
            <form action="TestListStudentExecute.action" method="get">
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


            <!-- ▼ 検索結果表示（必要ならここに表を置く） -->
            <c:if test="${not empty students}">
                <div class="mx-3">
                    <h5>検索結果</h5>
                    <!-- ここに結果テーブルを入れる -->
                </div>
            </c:if>

        </div>
    </c:param>
</c:import>
