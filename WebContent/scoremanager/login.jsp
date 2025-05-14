<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
        得点管理システム
    </c:param>

	<c:param name="scripts">
		<script type="text/javascript">
			document.addEventListener('DOMContentLoaded', function() {
				const passwordInput = document
						.querySelector('input[name="password"]');
				const showPasswordCheckbox = document
						.querySelector('input[name="chk_d_ps"]');

				showPasswordCheckbox.addEventListener('change', function() {
					if (this.checked) {
						passwordInput.type = 'text';
					} else {
						passwordInput.type = 'password';
					}
				});
			});
		</script>
	</c:param>

	<c:param name="content">
		<section class="me-4">
			<div class="row border mx-5 mb-1  align-items-center " id="filter">
				<h2
					class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4 text-center">ログイン</h2>
				<form method="post" action="LoginExecute.action">
				<p class="mb-2 mx-auto">${message}</p>
					<div class="form-floating mb-2 mx-4">
						<input type="text" class="form-control" id="floatingInputId"
							value="admin1" name="id" maxlength="30" required> <label
							for="floatingInputId">ID</label>
					</div>
					<div class="form-floating mb-2 mx-4">
						<input type="password" class="form-control"
							id="floatingInputPassword" value="password"
							name="password" maxlength="30" required> <label for="floatingInputPassword">パスワード</label>
					</div>
					<div class="col-3 form-check py-2 mx-auto">
						<input class="form-check-input" type="checkbox" name="chk_d_ps"
							id="showPassword">
							<label class="form-check-label" for="showPassword">パスワードを表示</label>
					</div>
					<div class="d-grid gap-2 col-6 mx-auto mb-3">
						<input class="btn btn-primary" type="submit" name="login"
							value="ログイン">
					</div>
				</form>
			</div>
		</section>
	</c:param>

</c:import>