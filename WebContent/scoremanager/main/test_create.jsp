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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録</h2>
			<form method="post" action="TestCreateExecute.action">
				<div class="mb-2 mx-2">
					<label class="form-label">学生</label>
					<select class="form-select" name="student_no">
						<option value="">--------</option>
						<c:forEach var="student" items="${studentList}">
							<option value="${student.no}" <c:if test="${student.no == f1}">selected</c:if>>${student.name}</option>
						</c:forEach>
					</select>
					<label class="form-label text-warning">${errorStudent}</label>
				</div>
				<div class="mb-2 mx-2">
					<label class="form-label">テスト</label>
					<select class="form-select" name="test_id">
						<option value="">--------</option>
						<c:forEach var="test" items="${testList}">
							<option value="${test.id}" <c:if test="${test.id == f2}">selected</c:if>>${test.name}</option>
						</c:forEach>
					</select>
					<label class="form-label text-warning">${errorTest}</label>
				</div>
				<div class="mb-2 mx-2">
					<label class="form-label">得点</label>
					<input type="number" class="form-control" name="score" min="0" max="100"
						value="${score}" required>
					<label class="form-label text-warning">${errorScore}</label>
				</div>
				<div class="col-2 mb-4 text-center">
					<button type="submit" class="btn btn-secondary">登録して終了</button>
				</div>
				<a href="ScoreList.action">戻る</a>
				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</form>
		</section>
	</c:param>
</c:import>
