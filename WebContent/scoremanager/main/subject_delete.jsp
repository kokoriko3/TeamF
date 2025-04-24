<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>


            <p class="text-danger px-4">
                「${subject.getName()}（${subject.getcd()}）」を削除してよろしいですか？
            </p>

            <!-- 削除フォーム -->
            <form method="post" action="SubjectDeleteExecute.action" class="px-4">
                <input type="hidden" name="code" value="${subject.getcd()}">

                <!-- ③ 削除ボタン -->
                <button type="submit" class="btn btn-danger me-3">削除</button>

                <!-- ④ 戻るリンク -->
                <a href="SubjectList.action" class="btn btn-link">戻る</a>
            </form>
        </section>
    </c:param>
</c:import>
