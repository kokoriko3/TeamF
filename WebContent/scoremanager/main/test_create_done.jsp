<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
        得点管理システム
    </c:param>

	<c:param name="content">
		<section class="me-4">
			<div class="row mx-5 mb-1 align-items-center" id="filter">
				<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-center">成績登録</h2>
				<p class="mb-3 bg-green py-2 px-4 text-center">登録が完了しました</p>
				<div class="col-3">
					<a class="col-auto m-4" href="TestCreate.action">もう一度登録</a>
					<a class="col-auto" href="ScoreList.action">成績一覧</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>
