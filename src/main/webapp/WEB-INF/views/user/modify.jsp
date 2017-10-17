<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var msg;

	$(document).ready(function() {
		msg = '${resultmsg}';

		if ('${resultmsg}' != "") {
			msg = msg.replace(/다/g, "다\n");
			alert(msg);
			msg = msg.replace(/\n/g, "<br>");
			$('#alert').html(msg);
		}
		var formObj = $("form[role='form']");
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
<head>

</head>
<body>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2" style="margin-left: 0px;">
					<form role="form">
						<div class="form-group col-md-6">
							<label>아이디</label> <input type="text" class="form-control"
								name="id" id="id" readonly="readonly" value="${userVO.id}">
						</div>

						<div class="form-group col-md-6">
							<label>비밀번호</label> <input type="password" class="form-control"
								name="pwd" id="pwd" value="${userVO.pwd}"> <span
								id="limitingpwd"></span>
						</div>
						<div class="form-group col-md-6">
							<label>비밀번호 확인 *</label> <input type="password"
								class="form-control" maxlength="30" id="pwdok"
								value="${userVO.pwd}"> <font name="check" size="2"
								color="red"></font>
						</div>

						<div class="form-group col-md-6">
							<label>이름</label> <input type="text" class="form-control"
								name="name" id="user" value="${userVO.name}"><span
								id="limitinguser"></span>
						</div>

						<div class="form-group col-md-6">
							<label>권한</label> <input type="text" class="form-control"
								name="role" id="role" value="${userVO.role}" readonly="readonly">
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
								name="telno" id="telno" value="${userVO.telno}" maxlength="11">
						</div>



						<div class="form-group col-md-6">
							<label>이메일</label> <input type="email" class="form-control"
								name="email" id="email" value="${userVO.email}" maxlength="30">
						</div>

						<div class="controls">
							<button id="submit"
								class="btn btn-primary input-medium pull-right">정보수정</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>