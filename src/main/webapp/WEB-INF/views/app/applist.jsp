<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script type="text/javascript">
	function allChk(obj) {
		var chkObj = document.getElementsByName("RowCheck");
		var rowCnt = chkObj.length - 1;
		var check = obj.checked;
		if (check) {
			for (var i = 0; i <= rowCnt; i++) {
				if (chkObj[i].type == "checkbox")
					chkObj[i].checked = true;
			}
		} else {
			for (var i = 0; i <= rowCnt; i++) {
				if (chkObj[i].type == "checkbox") {
					chkObj[i].checked = false;
				}
			}
		}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Application List</h1>

					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<p style="float: right">
					<form action="applist_del" method="post">
						<input type="submit" class="btn btn-default" id="delete"
							value="Delete">
						</p>
						<div class="col-lg-6" style="width: 830px;">
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>File Name</th>
											<th>Package Name</th>
											<th>Version</th>
											<th>AddDate</th>
											<th>AddUser</th>
											<th><input id="allCheck" type="checkbox"
												onclick="allChk(this)"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list}">
											<tr>
												<td>${row.filename}</td>
												<td>${row.packagenm}</td>
												<td>${row.version}</td>
												<td>${row.adddate}</td>
												<td>${row.adduser}</td>
												<td><input name="RowCheck" type="checkbox"
													value="${row.packagenm}"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</form>
					<!-- /.row -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>