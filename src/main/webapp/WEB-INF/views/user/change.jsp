<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=5"
	type="text/javascript"></script>
<script>
	var formObj = $("form[role='form']");
	$(document).ready(function() {
		$('#pwd').keyup(function() {
			$('font[name=check]').text('');
		}); //#user_pass.keyup

		$('#pwdok').keyup(function() {
			if ($('#pwd').val() != $('#pwdok').val()) {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 틀립니다.");
			} else {
				$('font[name=check]').text('');
			}
		}); //#chpass.keyup

		$('#submit').click(function() {
			if ($('#pwd').val() == $('#pwdok').val()) {
				formObj.attr("action", "/user/modify");
				formObj.attr("method", "post");
				formObj.submit();
			} else {
				alert("비밀번호 확인을 제대로 입력해주세요");
			}

		});

	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="container">
					<div class="row">

						<div class="col-md-8 col-md-offset-2" style="margin-left: 0px;">

							<form role="form" method="POST" action="update">
								<div class="col-lg-12"
									style="margin-bottom: 15px; margin-top: 15px;">
									<h2 class="page-header" style="font-weight: bold;">User
										Modify</h2>
								</div>

								<div class="form-group col-md-6">
									<label>아이디</label> <input type="text" class="form-control"
										name="id" id="id" readonly="readonly" value="${dto.id}">
								</div>

								<div class="form-group col-md-6">
									<label>비밀번호</label> <input type="password" class="form-control"
										name="pwd" id="pwd" placeholder="비밀번호" value="${dto.pwd}">
								</div>
								<div class="form-group col-md-6">
									<label>이름</label> <input type="text" class="form-control"
										name="name" id="name" value="${dto.name}">
								</div>
								<div class="form-group col-md-6">
									<label>비밀번호 확인</label> <input type="password"
										class="form-control" maxlength="30" id="pwdok" value="${dto.pwd}"> <font
										name="check" size="2" color="red"></font>
								</div>
								<div class="form-group col-md-6">
									<label>권한</label> <input type="text" class="form-control"
										name="role" id="role" value="${dto.role}" readonly="readonly">
								</div>

								<!-- <div class="form-group col-md-6">
										<label>권한</label> <select class="form-control" name="role"
											id="role">
											<option value="1">사용자</option>
											<option value="0">관리자</option>
										</select>
									</div> -->


								<div class="form-group col-md-6">
									<label>전화번호</label> <input type="text" class="form-control"
										name="telno" id="telno" value="${dto.telno}">
								</div>



								<div class="form-group col-md-6">
									<label>이메일</label> <input type="email" class="form-control"
										name="email" id="email" value="${dto.email}">
								</div>

								<div class="controls">
									<button id="submit" type="submit"
										class="btn btn-primary input-medium pull-right">정보수정</button>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>