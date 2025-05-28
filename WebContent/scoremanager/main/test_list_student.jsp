<%--城製作途中 --%>



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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧(学生)</h2>
            <div class="container border mx-3 mb-3 py-2 align-items-center rounded">
                <!-- 科目情報検索フォーム -->
                <form method="get" action="TestListSubjectExecute.action">
                    <div class="row border-bottom align-items-end" id="filter">
                        <div class="col-md-2 text-center mb-3">
                            <p class="mb-2">科目情報</p>
                        </div>
                        <div class="col-md-auto mb-2">
                            <label class="form-label" for="subject-f1-select">入学年度</label>
                            <select class="form-select" id="subject-f1-select" name="f1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-auto mb-2">
                            <label class="form-label" for="student-f2-select">クラス</label>
                            <select class="form-select" id="student-f2-select" name="f2">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4 mb-2">
                            <label class="form-label" for="subject-f3-select">科目</label>
                            <select class="form-select" id="subject-f3-select" name="f3">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subject_set}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-auto mb-2 d-flex align-items-end">
                            <button class="btn btn-secondary" id="filter-button" type="submit">検索</button>
                        </div>
                        <div class="mt-2 text-warning">${errors.get("f1")}</div>
                    </div>
                </form>

                <!-- 学生番号検索フォーム -->
                <form method="get" action="TestListStudentExecute.action">
                    <div class="row align-items-end mt-3">
                        <div class="col-md-2 text-center mb-3">
                            <p class="mb-2">学生情報</p>
                        </div>
                        <div class="col-md-3 mb-2">
                            <label class="form-label" for="student-no">学生番号</label>
                            <input type="text" class="form-control" maxlength="10" required
                                   id="student-no" name="no" placeholder="学生番号を入力してください" value="${student.no}">
                        </div>
                        <div class="col-md-auto mb-2 d-flex align-items-end">
                            <button class="btn btn-secondary" type="submit">検索</button>
                        </div>
                        <div class="mt-2 text-warning">${errors.get("f1")}</div>
                    </div>
                </form>
            </div>
			<c:choose>
				<c:when test="${students.size()>0 }">
					<div>氏名:${student.name }(${student.no })</div>
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="student" items="${students }">
						<tr>
							<td>${student.subjectName }</td>
							<td>${student.subjectCd }</td>
							<td>${student.num }</td>
							<td>${student.point }</td>
						</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:when test="${not empty student}">
					<div>氏名:${student.name }(${student.no })</div>
					<div>成績情報が存在しませんでした</div>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>