<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<%@ include file="/include/upload.jsp"%>
<script>
	$(document).ready(function() {
		$('#view1').click(function() {
			alert("not support");
		});
		$('#view2').click(function() {
			alert("not support");
		});
		$('#view3').click(function() {
			alert("not support");
		});

	});
	
	function download(fileName){
		location.href = "/displayFile?fileName="+fileName+"&uploadPath=download";
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
						<h2
							style="margin-bottom: 40px; padding-top: 20px; font-weight: bold;">Help/Support</h2>

						<h3 style="padding-top: 20px; font-weight: bold; color: gray;">Help</h3>
						<div class="col-lg-12" style="margin-bottom: 40px;">
							Adobe Reader
							<button id="download"
								onclick="download('adobeReaderInstall.exe')">설치</button>
							<br> <br> 운영자 가이드
							<button id="view1">View</button>
							<br> <br> 사용자 가이드
							<button id="view2">View</button>
							<br> <br> Trouble Shooting
							<button id="view3">View</button>
						</div>

						<h3 style="font-weight: bold; color: gray;">Support</h3>
						<div class="col-lg-12">
							개발 / 기술 지원 담당 : ㈜ 컴즈<br> <br> Tel : 070-7008-8205<br>
							<br> Fax : 070-7008-8208<br> <br> E-mail :
							comes@comes.co.kr<br> <br> Address : 서울시 구로구 디지털로 34길
							27. 502호 (대륭포스트타워 3차)<br> <br>
						</div>

					</div>
				</div>


			</div>
		</div>
	</div>
</body>

</html>