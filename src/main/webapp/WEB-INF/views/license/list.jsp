<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=5"
	type="text/javascript"></script>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");
		console.log(formObj);
		//formObj.submit();

		$('#refresh').click(function() {
		});

		$('#register').click(function() {
			var URL = "/license/regist";
			location.href=URL;
		});
		
		
	});
</script>

</head>
<body>

	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->

				<div class="row">
					<div class="col-lg-12" style="margin-bottom: 0px;">
						<h2 class="page-header" style="font-weight: bold;">License
							Management</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 " style="margin-bottom: 20px;">
						<h3 style="margin-bottom: 20px;">Licenses Summary</h3>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${total}</b> users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Used</h3>
							<b style="font-size: 50px">${used}</b> users

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Unlicensed User</h3>
							<b style="font-size: 50px">${unlicenseduser}</b> users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Reviewer</h3>
							<b style="font-size: 50px">${reviewer}</b> users
						</div>

					</div>
				</div>

				<form role="form">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<div class="row">
											<div class="col-lg-10" style="padding-top: 10px">
												<i class="fa fa-fw fa-check"></i> License Information
											</div>
											<div class="col-lg-2">

												<div class="form-group input-group"
													style="margin-bottom: 0px;">
													<span class="input-group-btn">
														<button class="btn btn-default" type="button" id="refresh">Refresh</button>
														<button style="left: 5px" class="btn btn-default"
															type="button" id="register">Register</button>
													</span>

												</div>
											</div>
										</div>
									</h3>
								</div>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th>버전</th>
													<th>사용 가능한 사용자 수</th>
													<th>남은일자</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${commercial}</td>
													<td>${user}</td>
													<td>${term}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>