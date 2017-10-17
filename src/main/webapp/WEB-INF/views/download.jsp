<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<%@ include file="/include/upload.jsp"%>
<style>
#center {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 300px;
	height: 200px;
	overflow: hidden;
	margin-top: -150px;
	margin-left: -100px;
}
</style>

<script>
	$(document)
			.ready(
					function() {
						$('#j_down').attr("href","/displayFile?fileName=jre-8u131-windows-x64.exe&uploadPath=download");
						

						$('#c_down')
								.attr("href",
										"/displayFile?fileName=controllerSetup.msi&uploadPath=download");
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
						<h2 style="padding-top: 20px; font-weight: bold;">Install
							Device Controller</h2>
					</div>

					<div class="col-lg-12">

						<h3
							style="margin-bottom: 50px; text-align: center; font-weight: bold; color: gray;">Download</h3>
						<div class="col-lg-12">
							<div class="col-lg-2"></div>
							<div class="col-lg-4"
								style="text-align: center; border: 1px solid #eee; background-color: #f1f0f0; border-color: black; right: 15px;">
								<h5>
									<a id="j_down" style="color: black;" href="#">JRE Download</a>
								</h5>
							</div>
							<div class="col-lg-4"
								style="text-align: center; border: 1px solid #eee; background-color: #f1f0f0; border-color: black; left: 15px;">
								<h5>
									<a id="c_down" style="color: black;" href="#">Controller
										Download</a>
								</h5>
							</div>
							<div class="col-lg-2"></div>
						</div>

					</div>
					<div class="col-lg-12">
						<br> <br> JRE 설치 안내<br> <br> &nbsp; &nbsp;- PC
						에 Java 1.8 이전 버전이 설치된 경우에 다운로드 받아서설치합니다.<br> <br> &nbsp;
						&nbsp;- 1.8 이후 버전인 경우에는 설치할 필요가 없습니다.<br> <br> <br>
						Controller 설치 안내<br> <br> &nbsp; &nbsp;- Java 를 설치해야 실행이
						가능합니다. (Controller 를 먼저 설치하고 Java 를 설치해도 무방)<br> <br>
						&nbsp; &nbsp;- Controller는 C;\Users\comes 밑에 자동으로 설치됩니다.<br>
						<br> &nbsp; &nbsp;- Controller는 디바이스를 가지고 테스트를 하는 경우에 필요하며
						단순히 테스트 결과를 보는 사용자인 경우에는 설치할 필요가 없습니다.<br> <br> <br>
						지원 브라우저<br> &nbsp; &nbsp;- chrome<br>&nbsp;
						&nbsp;- Internet Explorer11 .net4<br>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>