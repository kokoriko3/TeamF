<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

			<!-- 検索フォーム -->
			<form method="get" action="TestRegist.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">

					<!-- 入学年度 -->
					<div class="col-md-2">
						<label class="form-label" for="test-f1-select">入学年度</label> <select
							class="form-select" id="test-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}"
									<c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<!-- クラス -->
					<div class="col-md-2">
						<label class="form-label" for="test-f2-select">クラス</label> <select
							class="form-select" id="test-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 科目 -->
					<div class="col-md-3">
						<label class="form-label" for="test-f3-select">科目</label> <select
							class="form-select" id="test-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_set}">
								<option value="${subject.cd}"
									<c:if test="${subject.name == f3.name}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 回数（固定1〜5） -->
					<div class="col-md-2">
						<label class="form-label" for="test-f4-select">回数</label> <select
							class="form-select" id="test-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="i" begin="1" end="2">
								<option value="${i}" <c:if test="${i == f4}">selected</c:if>>${i}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2 text-center mt-4">
						<button type="submit" class="btn btn-secondary">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>

				</div>
			</form>

			<!-- 得点登録フォーム（検索結果表示） -->
			<c:choose>
				<c:when test="${not empty list}">
					<form method="post" action="TestRegistExecute.action">
					<input type="hidden" name="count" value="${ list.size()}" />
						<p class="ps-3">科目:${f3.name} (${f4 }回)</p>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>点数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${list}" varStatus="status">
									<tr>
										<td>${test.getStudent().getEntYear()}</td>
										<td>${test.classNum}</td>
										<td>${test.getStudent().getNo()}</td>
										<td>${test.getStudent().getName()}</td>
										<td>

											<input type="number"
											name="testList[${status.index}].point" placeholder="0～100の中から入力してください" class="form-control"
											<c:if  test="${test.point >= 0 }">value ="${test.point }"</c:if> />
											<c:if test="${test.point  == -2}"><label class="input-label mt-2 text-warning" >0 ~ 100の範囲で入力してください</label></c:if>
											<input type="hidden" name="testList[${status.index}].student" value="${test.getStudent().getNo()}" />
											<input type="hidden" name="testList[${status.index}].classNum" value="${test.classNum}" />
											<input type="hidden" name="testList[${status.index}].subject"value="${test.getSubject().getCd()}" />
											 <input type="hidden" name="testList[${status.index}].no" value="${test.no}" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-end pe-3">
							 <input type="submit" value="登録して終了" class="btn btn-primary" />
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<div>成績情報が存在していません</div>
				</c:otherwise>
			</c:choose>

		</section>
	</c:param>
</c:import>
