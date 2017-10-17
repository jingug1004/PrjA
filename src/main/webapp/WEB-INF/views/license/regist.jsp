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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#reset").bind('click',function(){
		if(/(MSIE|Trident)/.test(navigator.userAgent)){
			$("#upload").replaceWith($("#upload").clone(true));
		}else{
			$("#upload").val("");
		}
	});
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
						<h1 class="page-header">라이선스 목록</h1>
						<form action="regist_upload" method="post" enctype="multipart/form-data" id="file">
							<div class="form-inline">
								<span class="btn btn-success fileinput-button"> <i
									class="glyphicon glyphicon-plus"></i> <span>파일추가</span> <input
									type="file" name="upload" id="upload">
								</span>
								<button type="button" class="btn btn-primary start" onclick="document.getElementById('file').submit();">
									<i class="glyphicon glyphicon-upload"></i> <span>등록하기</span>
								</button>
								<button type="reset" class="btn btn-danger delete" id="reset" name="reset">
									<i class="glyphicon glyphicon-trash"></i> <span>취소</span>
								</button>
							</div>
						</form>


					</div>
				</div>
				<!-- /.row -->
				<div class="row"></div>
			</div>
		</div>
	</div>

</body>
</html>