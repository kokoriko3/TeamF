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
			<section class="me-4">
			 <div class="container border mx-3 mb-3 py-2 align-items-center rounded">
				<form method="get" action="SubjectSearch.action">
                	    <div class="row align-items-end mt-3">
                    	    <div class="col-md-2 text-center mb-3">
                        	    <p class="mb-2">科目検索</p>
                        	</div>
                        	<div class="col-md-3 mb-2">
                            	<input type="text" class="form-control" maxlength="10" required
                                	   id="subject_cd" name="cd" placeholder="科目コード" value="${cd}">

                        	</div>
                        	<div class="col-md-auto mb-2 d-flex align-items-end">
                            	<button class="btn btn-secondary" type="submit">検索</button>
                        	</div>
                        	<label class="mt-2 text-warning">${errorNo }</label>
                        	<div class="mt-2 text-warning">${errors.get("f1")}</div>
                    	</div>
                	</form>
                </div>
         	</section>




				<table class="table table-hover">
					<tr>
						<th>科目コード</th>
						<th>科目名</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="subject" items="${subjects }">
					<tr>

						<%-- subjectdao完成後に作る --%>
						<td>${subject.cd }</td>
						<td>${subject.name }</td>
						<td><a href="SubjectUpdate.action?no=${subject.cd }">変更</a></td>
						<td><a href="SubjectDelete.action?cd=${subject.cd }">削除</a></td>
					</tr>
					</c:forEach>
				</table>
		</section>
	</c:param>

</c:import>