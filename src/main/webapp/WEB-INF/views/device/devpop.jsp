<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<html>
<head>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>


<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
	var formObj = $("form[role='form']");

	function send(index, devid) {
		opener.document.getElementById("dev_name").value = document
				.getElementById("cdev_" + index).value;
		opener.document.getElementById("devid").value = document
				.getElementById("devid_" + index).value;

		$('#defa').val(devid);

		if (confirm("기본 기기로 설정하시겠습니까?")) {
			document.list.action = "${path}/device/devdefault1";
			document.list.submit();
			window.close();
		}
	}
</script>
</head>
<body style="margin-top: 15px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding: 30px">
						<div class="row">
							<div class="col-lg-12" style="padding-left: 0px;">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-fw fa-check"></i> Device List
									</h3>
									<form method="post" name="list">
										<input type="hidden" id="defa" name="defa">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>Device Name</th>
													<th>OS</th>
													<th>Version</th>
													<th>AddDate</th>
													<th>AddUser</th>
													<th>Select</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${list}" varStatus="status">
													<tr>
														<td>${row.modelno}</td>
														<td>${row.os}</td>
														<td>${row.version}</td>
														<td>${row.adddate}</td>
														<td>${row.adduser}</td>
														<td>
															<button name="def"
																onclick="send('${status.index}',${row.devid})"
																class="btn btn-default">Select</button>

														</td>
													</tr>
													<input type="hidden" id="cdev_${status.index}"
														value="${row.modelno}">
													<input type="hidden" id="devid_${status.index}"
														value="${row.devid}">

												</c:forEach>
											</tbody>
										</table>
										<div class="text-center">
											<ul class="pagination">

												<c:if test="${pageMaker.prev}">
													<li><a
														href="devpop${pageMaker.makeUserSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
													<!-- 왼쪽화살표 -->
												</c:if>

												<c:forEach begin="${pageMaker.startPage }"
													end="${pageMaker.endPage }" var="idx">
													<li
														<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
														<a href="devpop${pageMaker.makeUserSearch(idx)}">${idx}</a>
													</li>
												</c:forEach>

												<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
													<!-- 오른쪽화살표 -->
													<li><a
														href="devpop${pageMaker.makeUserSearch(pageMaker.endPage +1)}">&raquo;</a></li>
												</c:if>

											</ul>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>