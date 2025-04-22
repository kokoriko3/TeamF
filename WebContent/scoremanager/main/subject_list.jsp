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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>

			<c:choose>
				<c:when test="${subject.size()>0 }">
					<div>検索結果:${subject.size() }件</div>
					<table class="table table-hover">
						<tr>
							<th>科目コード</th>
							<th>科目名</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="subject" items="${subject }">
						<tr>

							<%-- subjectdao完成後に作る --%>
							<td>${subject.entYear }</td>
							<td>${subject.no }</td>
							<td>${subject.name }</td>
							<td>${subject.classNum }</td>
							<td><a href="SubjectUpdate.action?no=${subject.no }">変更</a></td>
						</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>       </div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>

</c:import>