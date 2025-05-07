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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
			<form method="post" action="StudentUpdateExecute.action">
			<input type="hidden" value="${student.getEntYear()}" name="ent_year">
			<input type="hidden" value="${student.getNo()}" name="no">
					<div class="mb-2 mx-2">
						<label class="form-label" for="entyear-select">入学年度</label>
						<input type="text"  id="disabledTextInput" class="form-control" name="ent_year"
						 value="${student.getEntYear()}"disabled>
					</div>
					<div class="mb-2 mx-2">
						<label class="form-label" for="student-f2-select">学生番号</label>
						<input type="text"  id="disabledTextInput" class="form-control" name="no"
						value="${student.getNo()}" disabled>
					</div>
					<div class="mb-2 mx-2">
						<label class="form-label" for="student-f2-select">氏名</label>
						<input type="text"  class="form-control" maxlength="30" required
						name="name" placeholder="氏名を入力してください" value="${student.getName()}">
					</div>
					<div class="mb-4 mx-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="class_num">
							<c:forEach var="num" items="${class_num_set }">
								<option value="${num }" <c:if test="${num==student.getClassNum() }">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="my-3 col-2 form-check ">
						<label class="form-check-label" for="student-f3-check">在学中
							<input class="form-check-input" type="checkbox" id="student-f3-check" name="isAttend" value="t"
							<c:if test="${student.isAttend()}">checked</c:if>/>
						</label>
					</div>
					<div class="col-2 mb-4 ">
						<button type="submit" class="btn btn-primary" id="filter-button">変更</button>
					</div>
					<a href="StudentList.action">戻る</a>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
			</form>
		</section>
	</c:param>

</c:import>