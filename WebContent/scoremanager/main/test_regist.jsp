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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

					<div class="col-3">
						<label class="form-label" for="test-f1-select">入学年度</label>
						<select class="form-select" id="test-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-3">
						<label class="form-label" for="test-f2-select">クラス</label>
						<select class="form-select" id="test-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-3">
						<label class="form-label" for="test-f3-select">科目</label>
						<select class="form-select" id="test-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_set}">
								<option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-3">
						<label class="form-label" for="test-f4-select">回数</label>
						<select class="form-select" id="test-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="test" items="${test_set}">
								<option value="${test.id}" <c:if test="${test.id == f4}">selected</c:if>>${test.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2 text-center mt-3">
						<button class="btn btn-secondary">検索</button>
					</div>
				</div>
			</form>

			<c:if test="${not empty testList}">
				<form method="post" action="TestRegistDone.action">
					<p class="ps-3">科目: ${subjectName}（${f4}回）</p>
					<table class="table table-bordered">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>
						<c:forEach var="test" items="${testList}" varStatus="status">
							<tr>
								<td>${test.entYear}</td>
								<td>${test.classNum}</td>
								<td>${test.no}</td>
								<td>${test.name}</td>
								<td>
									<input type="number" name="testList[${status.index}].point" value="${test.point}" class="form-control" />
									<input type="hidden" name="testList[${status.index}].no" value="${test.no}" />
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="text-end pe-3">
						<input type="submit" value="登録して終了" class="btn btn-primary" />
					</div>
				</form>
			</c:if>
		</section>
	</c:param>
</c:import>
