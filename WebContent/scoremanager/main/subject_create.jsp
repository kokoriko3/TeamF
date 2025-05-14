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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			<form method="post" action="SubjectCreateExecute.action">
					<div class="mb-2 mx-2">
						<label class="form-label" for="subject-f2-select">科目コード</label>
						<input type="text"  class="form-control" maxlength="10" required
						name="no" placeholder="科目コードを入力してください" value="${no}">
						<label class="form-label text-warning" for="entyear-select">${errorNo}</label>
					</div>
					<div class="mb-2 mx-2">
						<label class="form-label" for="subject-f2-select">科目名</label>
						<input type="text"  class="form-control" maxlength="30" required
						name="name" placeholder="科目名を入力してください" value="${name}">
					</div>
					<div class="col-md-2 mb-4 ">
						<button type="submit" class="btn btn-secondary" id="filter-button">登録</button>
					</div>
					<a href="SubjectList.action">戻る</a>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
			</form>
		</section>
	</c:param>

</c:import>