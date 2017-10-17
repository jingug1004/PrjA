<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=1"
	type="text/javascript"></script>

<script>
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
		//생성버튼
		$('#ok').click(function() {
			if ($('#pwd').val() == $('#pwdok').val()) {
				formObj.attr("action", "/user/regist_insert");
				formObj.attr("method", "post");
				formObj.submit();
			} else {
				alert("패스워드 확인을 제대로 입력해주세요");
			}
		});

		$('#pwd').keyup(function() {
			$('font[name=check]').text('');
		}); //#user_pass.keyup

		$('#pwdok').keyup(function() {
			if ($('#pwd').val() != $('#pwdok').val()) {
				$('font[name=check]').text('');
				$('font[name=check]').html("패스워드가 틀립니다.");
			} else {
				$('font[name=check]').text('');
			}
		}); //#chpass.keyup

	});
</script>
<title>New User</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row">

			<div class="col-md-8 col-md-offset-2" style="margin-left: 0px;">
				<form role="form">
					<fieldset>
						<div class="form-group col-md-6">
							<span id="alert" style='color: red'></span>
						</div>
						<div class="form-group col-md-6">
							<label>ID *</label> <input type="text" class="form-control"
								name="id" id="id" maxlength="20"> <span id="limitingid"
								style="color: darkgray"></span>
						</div>
						<div class="form-group col-md-6">
							<label>Password *</label> <input type="password"
								class="form-control" name="pwd" id="pwd" maxlength="30">
							<span id="limitingpwd" style="color: darkgray"></span>
						</div>
						<div class="form-group col-md-6">
							<label>Password 확인 *</label> <input type="password"
								class="form-control" maxlength="30" id="pwdok"> <font
								name="check" size="2" color="red"></font>
						</div>
						<div class="form-group col-md-6">
							<label>Name *</label> <input type="text" class="form-control"
								name="name" id="name" maxlength="20"> <span
								id="limitingname" style="color: darkgray"></span>
						</div>
						<div class="form-group col-md-6">
							<label>Authority</label> <select class="form-control" name="role"
								id="role">
								<option value="1">사용자</option>
								<option value="0">관리자</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label>Phone</label> <input type="text" class="form-control"
								name="telno" id="telno" maxlength="11"> <span
								id="limitingtelno" style="color: darkgray"></span>
						</div>
						<div class="form-group col-md-6">
							<label>E-Mail</label> <input type="email" class="form-control"
								name="email" id="email" maxlength="30"> <span
								id="limitingemail" style="color: darkgray"></span>
						</div>
						<div class="controls">
							<button type="button" id="ok"
								class="btn btn-primary input-medium pull-right">회원등록</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

</body>
</html>