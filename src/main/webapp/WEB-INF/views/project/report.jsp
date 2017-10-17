<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	$(document).ready(
			function() {
				$('#project').click(
						function() {
							var URL = "/project/issue/project_list";
							window.open(URL, "project_list",
									"width=800, height=600, top=50,left=310,scrollbars=yes");

						});
				
				$('#report').click(
						function() {
							var URL = "/project/report_result";
							window.open(URL, "report_result",
									"width=800, height=600, top=50,left=310,scrollbars=yes");

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
					<div class="col-lg-12">
						<h2 class="page-header"
							style="font-weight: bold; margin-bottom: 25px">Submit Report</h2>
						<div class="row">
							<div class="col-lg-5">
								<div class="form-group input-group">
									<span class=input-group-addon>Project Title</span><input
											type="hidden" name="projid" id="projid"> <input
										type="text" class="form-control" name="proj_name"
										id="proj_name"> <span class="input-group-btn">
										<button class="btn btn-default" type="button" id="project">찾기</button>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-5"
								style="text-align: center; margin-top: 15px;">
								<button class="btn btn-default" type="button" id="report">Create
									Report</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>

</body>
</html>