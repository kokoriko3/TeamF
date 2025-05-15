<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
			<form method="post" action="StudentUpdateExecute.action">
				<input type="hidden" name="ent_year" value="${student.entYear}" />
				<input type="hidden" name="no" value="${student.no}" />

				<div class="mb-2 mx-2">
					<label class="form-label">入学年度</label>
					<input type="text" class="form-control-plaintext" readonly value="${student.entYear}" />
				</div>

				<div class="mb-2 mx-2">
					<label class="form-label">学生番号</label>
					<input type="text" class="form-control-plaintext" readonly value="${student.no}" />
				</div>

				<div class="mb-2 mx-2">
					<label class="form-label">氏名</label>
					<input type="text" class="form-control" maxlength="30" required
						name="name" placeholder="氏名を入力してください" value="${student.name}" />
				</div>

				<div class="mb-4 mx-2">
					<label class="form-label">クラス</label>
					<select class="form-select" name="class_num">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3 mx-2 d-flex align-items-center">
    				<label class="me-2">在学中</label>
    				<input class="form-check-input" type="checkbox" id="isAttend" name="isAttend" value="t"
       			 	<c:if test="${student.attend}">checked</c:if> />
				</div>

				<div class="mb-2 mx-2">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>

				<div class="mx-2">
					<a href="StudentList.action">戻る</a>
				</div>

				<div class="mt-2 text-warning mx-2">${errors.get("f1")}</div>
			</form>
		</section>
	</c:param>
</c:import>
