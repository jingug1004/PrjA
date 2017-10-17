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
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=5" type="text/javascript"></script>
<script type="text/javascript">
	function connect() {
		var seri = "<%=(String) session.getAttribute("serial")%>";
		location.href = "kr.co.comes.projecta:// 8 " + seri;

	}
	function appinfo() {
		var seri = "<%=(String) session.getAttribute("serial")%>";
		var id = "<%=(String) session.getAttribute("id") %>";
		location.href = "kr.co.comes.projecta:// 9 "+ id + " " + seri + " " + $("#selec").val();

	}
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
	function send(appid){
		
		$('#defa').val(appid);
		
		if (confirm("기본 앱으로 설정하시겠습니까?")) {
			document.list.action = "${path}/app/appdefault";
			document.list.submit();
		}
	}
	
/* 	$(document).ready(function() {
		$("input[name=def]").click(function() {
			// 확인 대화상자 출력
			if (confirm("기본 앱으로 설정하시겠습니까?")) {
				document.list.action = "${path}/app/appdefault";
				document.list.submit();
			}
		});
	}); */
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);
		//formObj.submit();

		$('#search').click(function() {
			formObj.attr("action", "/app/appmain");
			formObj.submit();
		});
		
		$("#delete").click(function() {
			// 확인 대화상자 출력
			if (confirm("삭제하시겠습니까?")) {
				document.list.action = "${path}/app/delapp";
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
			
			var getd1 = "연결에 성공했습니다.";
			var getd2 = "연결에 실패했습니다.";
			var getd3 = "이미 등록된 기기가 존재합니다.";
			switch(data){
			case getd1:
				alert(getd1);
				break;
			case getd2:
				alert(getd2);
				break;
			case getd3:
				alert(getd3);
				break;
			case getw1:
				alert(getw1);
				break;
			case getw2:
				alert(getw2);
				break;
			case getw3:
				alert(getw3);
				break;
			case getf1:
				alert(getf1);
				break;
			case getf2:
				alert(getf2);
				break;
			default:
			var stag = document.getElementById('selec');
			var opt = document.createElement('option');
			var seltext = data;
			var selopt = data;
			opt.value = selopt;
			opt.text = seltext;
			stag.add(opt);
				break;
			}
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
						<h1 class="page-header">App Controller</h1>

					</div>
				</div>
				<!-- /.row -->
				<!-- 앱 total 확인 영역 -->
				<div class="row">
					<div class="col-lg-6" style="width: 1000px;">
						<h2 style="margin-bottom: 20px; padding-top: 20px;">App
							Summary</h2>
						<div class="col-lg-2"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${count}</b> 개
						</div>
					</div>
				</div>
				<h1></h1>
				<div class="row">
					<div class="col-lg-9" style="padding-right: 0px;">
						<select id="selec" name="selec" class="form-control"></select>
					</div>
					<div class="col-lg-3">
						<input type="button" class="btn btn-default" value="packagelist"
							id="con" onclick="connect()"> <input type="button"
							class="btn btn-default" value="appinfo" id="info"
							onclick="appinfo()">
					</div>
				</div>
				<h1></h1>
				<!-- 앱 list 영역 -->
				<div class="row">
					<div class="col-lg-6" style="width: 830px;">
						<form method="post" name="list" role="form">
							<input type="hidden" name="pageNO" /> <input type="hidden"
								name="searchFiled" value="${pageVO.searchFiled}" /> <input
								type="hidden" name="searchValue" value="${pageVO.searchValue}">
							<input type="hidden" name="defa" id="defa">
							<div class="col-lg-12" style="width: 1330px;">
							<div class="form-group input-group">
								<span class=input-group-addon>App Name</span> <input type="text"
									class="form-control" name="keyword" id="keyword" value="${lc.keyword}"><span
									class="input-group-btn"><button class="btn btn-default"
										type="button" id="search">
										검색 <i class="fa fa-search"></i>
									</button></span>
							</div>
						</div>
							<div class="table-responsive" style="width: 1300px;">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th><input id="allCheck" type="checkbox"
												onclick="allChk(this)"></th>
											<th style="width: 120px;">App Name</th>
											<th style="width: 78px;">Package Name</th>
											<th>Version</th>
											<th>AddDate</th>
											<th>AddUser</th>
											<th>Select</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list}" varStatus="status">
											<tr>
												<td><input name="RowCheck" type="checkbox"
													value="${row.appid}"></td>
												<td>${row.appname}</td>
												<td>${row.packagename}</td>
												<td>${row.appversion}</td>
												<td>${row.adddate}</td>
												<td>${row.adduser}</td>
												<td>
													<button name="def" onclick="send(${row.appid})"
														class="btn btn-default">Select</button>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="text-center">
									<ul class="pagination">

										<c:if test="${pageMaker.prev}">
											<li><a
												href="appmain${pageMaker.makeUserSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
											<!-- 왼쪽화살표 -->
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="appmain${pageMaker.makeUserSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<!-- 오른쪽화살표 -->
											<li><a
												href="appmain${pageMaker.makeUserSearch(pageMaker.endPage +1)}">&raquo;</a></li>
										</c:if>

									</ul>
								</div>
								<input type="button" class="btn btn-default" id="delete"
									value="Delete" style="margin-left: 1230px;">
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