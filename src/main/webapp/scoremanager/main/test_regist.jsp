<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>
            
            <!-- フィルタフォーム -->
            <form method="get">
			    <div class="row border mx-3 mb-3 py-3 align-items-end rounded" id="filter">
			
			        <!-- 入学年度 -->
			        <div class="col">
			            <label class="form-label" for="student-f1-select">入学年度</label>
			            <select class="form-select" id="student-f1-select" name="f1">
			                <option value="0">--------</option>
			                <c:forEach var="year" items="${ent_year_set}">
			                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
			                        ${year}
			                    </option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- クラス -->
			        <div class="col">
			            <label class="form-label" for="student-f2-select">クラス</label>
			            <select class="form-select" id="student-f2-select" name="f2">
			                <option value="0">--------</option>
			                <c:forEach var="num" items="${class_num_list}">
			                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
			                        ${num}
			                    </option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- 科目 -->
			        <div class="col">
			            <label class="form-label" for="student-f3-select">科目</label>
			            <select class="form-select" id="student-f3-select" name="f3">
			                <option value="0">--------</option>
			                <c:forEach var="subject" items="${subject_list}">
			                    <option value="${subject}" <c:if test="${subject == f3}">selected</c:if>>
			                        ${subject}
			                    </option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- 回数 -->
			        <div class="col">
			            <label class="form-label" for="student-f4-select">回数</label>
			            <select class="form-select" id="student-f4-select" name="f4">
			                <option value="0">--------</option>
			                <option value="1" <c:if test="${f4 == 1}">selected</c:if>>1回目</option>
			                <option value="2" <c:if test="${f4 == 2}">selected</c:if>>2回目</option>
			            </select>
			        </div>
			
			        <!-- 検索ボタン -->
			        <div class="col">
			            <label class="form-label invisible">検索</label>
			            <button type="submit" class="btn btn-primary w-50">検索</button>
			        </div>
			
			    </div>
			</form>

            

         </section>
     </c:param>
</c:import>