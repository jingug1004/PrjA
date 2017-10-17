<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>


<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>SKT Test Automation</title>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>

<script>
	var arr = new Array();

	$(document)
			.ready(
					function() {

						$('#add')
								.click(
										function() {
											opener.document
													.getElementById("paticipant").value = $(
													'#participant').text();
											window.close();
										});

					});

	function choice(id) {
		var p = $('#participant').text();
		var result = p.indexOf(id);
		if (result == -1) {
			arr.push(id);
		}

		$('#participant').text(arr);

	};
</script>

</head>
<body style="margin-top: 5px;">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">User 목록</h2>
			</div>
		</div>

		<form role="form">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Role</th>
											<th>Client IP Addr</th>
											<th>Tel</th>
											<th>Email</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${list}" var="userVO">
											<tr>
												<td><a href="#" onclick="choice('${userVO.id}')">${userVO.id}</a></td>
												<td>${userVO.name}</td>
												<td>${userVO.role}</td>
												<td>${userVO.clntid}</td>
												<td>${userVO.telno}</td>
												<td>${userVO.email}</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					참여자:<span id="participant"></span>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<p style="float: right">
						<button class="btn btn-default" type="button" id="add">추가</button>
					</p>
				</div>
			</div>
		</form>
	</div>
</body>
</html>