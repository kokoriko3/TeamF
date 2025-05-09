<%--城製作途中 --%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>
			<div class="container border mx-3 mb-3 py-2 align-items-center rounded">
			<form method="get">
				<div class="row border-bottom align-items-end" id="filter">
					<div class="col-2 text-center p-4">
						<p class="mb-2">科目情報</p>
					</div>
					<div class="col-auto">
						<label class="form-label" for="subject-f1-select">入学年度</label>
						<select class="form-select" id="subject-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set }">
								<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set }">
								<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select" id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subjects" items="${subject_set }">
								<option value="${subjects }" <c:if test="${subjects.cd==f3 }">selected</c:if>>${subjects}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto d-flex align-items-end">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>
			</form>
			<form method="get">
				<div class="row align-items-end mt-3">
					<div class="col-2 text-center p-4">
						<p>学生情報</p>
					</div>
					<div class="col-3">
						<label class="form-label" for="student-f3-select">学生番号</label>
						<input type="text"  class="form-control" maxlength="10" required
						name="no" placeholder="学生番号を入力してください" value="${no}">
					</div>
					<div class="col-auto d-flex align-items-end">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
				</div>
			</form>
			</div>
			<c:choose>
				<c:when test="${students.size()>0 }">
					<div>検索結果:${students.size() }件</div>
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="student" items="${students }">
						<tr>
							<td>${student.subjectName }</td>
							<td>${student.subjectCd }</td>
							<td>${student.num }</td>
							<td>${student.point }</td>
						</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>