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
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4 text-center">
					科目情報変更
				</h2>
				<p class="mb-3 bg-success text-white py-2 px-4 text-center">
					科目情報の変更が完了しました
				</p>
				<div class="col-3">
					<a href="SubjectList.action">
						科目一覧
					</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>
