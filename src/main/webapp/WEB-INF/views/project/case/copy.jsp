<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>Copy Test Case</title>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>

<script>
	$(document).ready(function() {

		//예버튼
		$('#ok').click(function() {
			alert("test");
			opener.document.getElementById("projid").value;
			//opener.$("form[role='form']");
			//window.close();
		});

		//아니오 버튼
	/* 	$('#cancel').click(function() {
			opener.document.getElementById("copy").value = "none";
			window.close();
		}); */

	});
</script>
<body style="margin-top: 15px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12" style="text-align: center;">
				Test Case Scenario 도 함께 복사하시겠습니다? <br> ( Do you want to copy
				Test Case Scenario too? ) <br>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12" style="text-align: center; margin-top: 20px;">
				<p>
					<button class="btn btn-default" type="submit" id="ok">예</button>
					<button class="btn btn-default" type="button" id="cancel">아니오</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
