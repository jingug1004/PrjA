<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>SKT Test Automation</title>

<head>
<%@ include file="/include/home_menu.jsp"%>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>


<script>
	$(document)
			.ready(
					function() {

						$('#project')
								.click(
										function() {
											var URL = "/project/issue/project_list";
											window
													.open(URL, "project_list",
															"width=800, height=600, top=50,left=310,scrollbars=yes");

										});

						$('#phase')
								.click(
										function() {
											var projid = $('#projid').val();
											var URL = "/project/issue/phase_list?projid="
													+ projid;
											window
													.open(URL, "project_list",
															"width=800, height=600, top=50,left=310,scrollbars=yes");

										});

						$('#sena')
								.click(
										function() {
											var projid = $('#projid').val();
											var phid = $('#phid').val();
											var URL = "/project/issue/case_list?phid="
													+ phid + "&projid=" + projid;
											window
													.open(URL, "project_list",
															"width=800, height=600, top=50,left=310,scrollbars=yes");
										});
					});
	function connect() {
		var formObj = $("form[role='form']");

		formObj.attr("action", "/project/report_result");
		formObj.attr("method", "post");
		formObj.submit();

	}
</script>


</head>
<body>
	<div class="row">
		<div class="col-lg-12" style="margin-left: 250px;">
			<h1 class="page-header">Report Select</h1>

		</div>
	</div>
	<div class="panel-body" style="padding: 30px">
		<div class="row">
			<div class="col-lg-12" style="width: 1030px; margin-left: 220px;">
				<form role="form">
					<div class="form-group input-group">
						<span class=input-group-addon>Project Select</span> <input
							type="hidden" name="projid" id="projid"> <input
							type="text" class="form-control" name="proj_name" id="proj_name">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" id="project">찾기</button>
						</span>
					</div>
					<div class="form-group input-group">
						<span class=input-group-addon>Phase Select</span> <input
							type="hidden" name="phid" id="phid"> <input type="text"
							class="form-control" name="ph_name" id="ph_name"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button" id="phase">찾기</button>
						</span>
					</div>
					<div class="form-group input-group">
						<span class=input-group-addon>Case Select</span> <input
							type="hidden" name="senid" id="senid"> <input type="text"
							class="form-control" name="sena_name" id="sena_name"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button" id="sena">찾기</button>
						</span>
					</div>
					<button onclick="connect()" class="btn btn-default"
						style="margin-left: 903px;">리포트 확인</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>