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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績情報変更</h2>
			<form method="post" action="TestUpdateExecute.action">
				<input type="hidden" name="ent_year" value="${test.getEntYear()}">
				<input type="hidden" name="no" value="${test.getNo()}">
				<input type="hidden" name="subject_cd" value="${test.getSubjectCd()}">



				<div class="mb-2 mx-2">
					<label class="form-label">学生番号</label>
					<input type="text" class="form-control" value="${test.getNo()}" disabled>
				</div>

				<div class="mb-2 mx-2">
					<label class="form-label">氏名</label>
					<input type="text" class="form-control" value="${test.getStudentName()}" disabled>
				</div>

				<div class="mb-2 mx-2">
					<label class="form-label">科目名</label>
					<input type="text" class="form-control" value="${test.getSubjectName()}" disabled>
				</div>

				<div class="mb-4 mx-2">
					<label class="form-label">点数</label>
					<input type="number" class="form-control" name="point" value="${test.getPoint()}" min="0" max="100" required>
				</div>

				<div class="col-2 mb-4 text-center">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>
				<a href="TestList.action">戻る</a>
				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</form>
		</section>
	</c:param>
</c:import>
