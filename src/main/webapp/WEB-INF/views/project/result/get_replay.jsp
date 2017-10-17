<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<title>/device/replay</title>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[role='form']");

		$('#btn').click(function() {
			formObj.attr("action", "/device/replay");
			formObj.attr("method", "post");
			formObj.submit();
		});
	});
</script>
</head>
<body>
	<form role="form">
		<button id="btn">fff</button>
		<input type="text" id="text" name="param">
	</form>
</body>
</html>