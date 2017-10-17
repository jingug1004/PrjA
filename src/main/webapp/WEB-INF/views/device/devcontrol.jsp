<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script src="http://code.jquery.com/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=5"
	type="text/javascript"></script>
<script type="text/javascript">
	function connect() {
		var id = "<%=(String) session.getAttribute("id")%>";
		/* 끝에 true를 인자값으로 넘기면 디바이스의 전체 정보를 갖고 온다. */
		/* false가 들어가면 해당 디바이스의 serial값만 가져온다(연결된 디바이스 확인하는 용도로 사용 예정) */
		location.href = "kr.co.comes.projecta:// 1 " + id + " true";

	}
	
	function chk() {
		var id = "<%=(String) session.getAttribute("id")%>";
		/* 끝에 true를 인자값으로 넘기면 디바이스의 전체 정보를 갖고 온다. */
		/* false가 들어가면 해당 디바이스의 serial값만 가져온다(연결된 디바이스 확인하는 용도로 사용 예정) */
		location.href = "kr.co.comes.projecta:// 1 " + id + " false";

	}

	function wc(val) {
		var id = "<%=(String) session.getAttribute("id")%>";
		location.href = "kr.co.comes.projecta:// 2 " + id + " " + val;
	}
	
	function send(devid,seri) {

		$('#defa').val(devid);
		$('#se').val(seri);

		if (confirm("기본 기기로 설정하시겠습니까?")) {
			document.list.action = "${path}/device/devdefault";
			document.list.submit();
		}
	}
	
	$(document).ready(function() {
		
		Sock = new SockJS("/echo-ws");
		Sock.onopen = function() {

		};
		Sock.onmessage = function(evt) {
			
			var data = evt.data;
			
			var getd1 = "연결에 성공했습니다.";
			var getd2 = "연결에 실패했습니다.";
			var getd3 = "이미 등록된 기기가 존재합니다.";
			var getw1 = "무선연결에 성공했습니다.";
			var getw2 = "무선연결에 실패했습니다.";
			var getw3 = "이미 무선 등록된 기기입니다.";
			var getf1 = "디바이스 연결상태를 확인해주세요.";
			var getf2 = "무선연결에 실패했습니다.";
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
	            data = data.replace(/ /g,'');
	            var serial = data.split(',');
	            for(var i in serial){
	               if(serial != null){
	               $("#Z"+serial[i]).css("background-color","#A6A6A6");
	               }
	            }
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
						<h1 class="page-header">Device Control</h1>

					</div>
				</div>
				<input type="button" class="btn btn-default" id="chk"
					value="chk" onclick="chk()"
					style="border-left-width: 1px; margin-left: 1000px;">
				<input type="button" class="btn btn-default" id="con"
					value="connect" onclick="connect()"
					style="border-left-width: 1px; margin-left: 1150px;">
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-6" style="width: 830px;">
						<form method="post" name="list">
							<input type="hidden" name="defa" id="defa"> <input
								type="hidden" name="se" id="se">
							<div class="table-responsive" style="width: 1300px;">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
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
											<th>Default</th>
											<th>Wireless Connect</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${list}" varStatus="status">
											<tr id="Z${row.serial}">
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
												<td><button name="def"
														onclick="send(${row.devid},'${row.serial}')"
														class="btn btn-default">Select</button></td>
												<td><input type="button" class="btn btn-default"
													id="wconnect" value="wconnect"
													onclick="wc('${row.serial}');"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

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