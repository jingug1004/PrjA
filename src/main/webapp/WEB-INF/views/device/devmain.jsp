<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	
	$(document).ready(function() {
		
		var formObj = $("form[role='form']");
		console.log(formObj);
		//formObj.submit();

		$('#search').click(function() {
			formObj.attr("action", "/device/devmain");
			formObj.submit();
		});
		
		
		
		$("#delete").click(function() {
			// 확인 대화상자 출력
			if (confirm("삭제하시겠습니까?")) {
				document.list.action = "${path}/device/deldevice";
				document.list.submit();
			}
		});
	});
	$(document).ready(function() {
		
		Sock = new SockJS("/echo-ws");
		Sock.onopen = function() {

		};
		Sock.onmessage = function(evt) {
			var data = evt.data;
			alert(data);
			history.go(0);
		}
		Sock.onclose = function() {
		}
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
						<h1 class="page-header">Device Management</h1>

					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-6" style="width: 1000px;">
						<h2 style="margin-bottom: 20px; padding-top: 20px;">Device
							Summary</h2>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${count}</b> 개
						</div>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>SKT</h3>
							<b style="font-size: 50px">${skt_c}</b> 개
						</div>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>KT</h3>
							<b style="font-size: 50px">${kt_c}</b> 개
						</div>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>LGT</h3>
							<b style="font-size: 50px">${lg_c}</b> 개
						</div>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>기타</h3>
							<b style="font-size: 50px">${etc_c}</b> 개
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6" style="width: 1000px;">
						<h1 style="margin-bottom: 10px; padding-top: 10px;">검색 조건</h1>
						<div class="form-group input-group">
							<form role="form">
								<h4>
									<span class="label label-default">통신사</span> <input
										type="checkbox" name="company" value="skt" checked="checked">SKT
									<input type="checkbox" name="company" value="kt"
										checked="checked">KT <input type="checkbox"
										name="company" value="lg" checked="checked">LGT <input
										type="checkbox" name="company" value="etc" checked="checked">기타
								</h4>
								<h4>
									<span class="label label-default">버전</span> <input
										type="checkbox" name="version" value="5" checked="checked">5.x
									<input type="checkbox" name="version" value="6"
										checked="checked">6.x <input type="checkbox"
										name="version" value="7" checked="checked">7.x
								</h4>
								<div class="row">
						<div class="col-lg-12" style="width: 1330px;">
							<div class="form-group input-group">
								<span class=input-group-addon>Device Name</span> <input type="text"
									class="form-control" name="keyword" id="keyword" value="${lc.keyword}"><span
									class="input-group-btn"><button class="btn btn-default"
										type="button" id="search">
										검색 <i class="fa fa-search"></i>
									</button></span>
							</div>

						</div>
					</div>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6" style="width: 830px;">
						<form method="post" name="list">
							<input type="hidden" name="defa" id="defa">
							<input type="hidden" name="se" id="se">
							<div class="table-responsive" style="width: 1300px;">
							<input type="button" class="btn btn-default" id="delete" value="Delete" style="margin-left: 1232px;">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th><input id="allCheck" type="checkbox"
												onclick="allChk(this)"></th>
											<th style="width: 120px;">Device Name</th>
											<th style="width: 78px;">Serial No</th>
											<th>Ram Size</th>
											<th>OS</th>
											<th>Version</th>
											<th>Company</th>
											<th>Network</th>
											<th>ModelNumber</th>
											<th>Resolution</th>
											<th>Status</th>
											<th>AddDate</th>
											<th>AddUser</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list}" varStatus="status">
											<tr>
												<td><input name="RowCheck" type="checkbox"
													value="${row.devid}"></td>
												<td>${row.modelno}</td>
												<td>${row.serial}</td>
												<td>${row.ramsize}</td>
												<td>${row.os}</td>
												<td>${row.version}</td>
												<td>${row.company}</td>
												<td>${row.networkstandard}</td>
												<td>${row.modelno}</td>
												<td>${row.width}*${row.height}</td>
												<td>${row.status}</td>
												<td>${row.adddate}</td>
												<td>${row.adduser}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="text-center">
									<ul class="pagination">

										<c:if test="${pageMaker.prev}">
											<li><a
												href="devmain${pageMaker.makeUserSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
											<!-- 왼쪽화살표 -->
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="devmain${pageMaker.makeUserSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<!-- 오른쪽화살표 -->
											<li><a
												href="devmain${pageMaker.makeUserSearch(pageMaker.endPage +1)}">&raquo;</a></li>
										</c:if>

									</ul>
								</div>
							</div>
						</form>
					</div>
					<!-- /.row -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>