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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
			<form method="post" action="StudentCreateExecute.action">
					<div class="mb-1">
						<label class="form-label" for="entyear-select">入学年度</label>
						<select class="form-select" id="entyear-select" name="ent_year">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set }">
								<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>
						<label class="form-label text-warning" for="entyear-select">${errorEntYear}</label>
					</div>
					<div class="mb-1">
						<label class="form-label" for="student-f2-select">学生番号</label>
						<input type="text"  class="form-control" maxlength="10" required
						name="no" placeholder="学生番号を入力してください" value="${no}">
						<label class="form-label text-warning" for="entyear-select">${errorNo}</label>
					</div>
					<div class="mb-1">
						<label class="form-label" for="student-f2-select">氏名</label>
						<input type="text"  class="form-control" maxlength="30" required
						name="name" placeholder="氏名を入力してください" value="${name}">
					</div>
					<div class="mb-3">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="class_num">
							<c:forEach var="num" items="${class_num_set }">
								<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="my-3">
						<button type="submit" class="btn btn-secondary" id="filter-button">登録して終了</button>
					</div>
					<a href="StudentList.action">戻る</a>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
			</form>
		</section>
	</c:param>

</c:import>