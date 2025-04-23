<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<form method="post" action="SubjectUpdateExecute.action">
				<%-- 科目コード（表示＋送信用 hidden） --%>
				<div class="mb-2 mx-2">
					<label class="form-label" for="subject-code">科目コード</label>
					<input type="text" id="subject-code" class="form-control" value="${subject.cd}" readonly>
					<input type="hidden" name="cd" value="${subject.cd}">
				</div>

				<%-- 科目名 --%>
				<div class="mb-2 mx-2">
					<label class="form-label" for="subject-name">科目名</label>
					<input type="text" id="subject-name" class="form-control" name="name" required
						maxlength="50" placeholder="科目名を入力してください" value="${subject.name}">
				</div>

				<%-- 送信ボタン --%>
				<div class="col-2 mb-4 text-center">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>

				<a href="SubjectList.action">戻る</a>
				<div class="mt-2 text-warning">${errors.get("f1") }</div>
			</form>
		</section>
	</c:param>
</c:import>
