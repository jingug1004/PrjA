<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<input type="hidden" name="url">
	<textarea rows="50" cols="80" id="taa"></textarea>
	<div style="display: none">
		<textarea rows="50" cols="80" id="ta"></textarea>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		var url = opener.document.getElementById('url').value;
		var msg = opener.document.getElementById('msg').value;

		location.href = url;

		setInterval(function() {
			/* $("#input").load("error.txt");
			$("#input").toggle();
			$("#ta").val(input.value); */
			if (msg != "") {
				window.close();
			} else {
				$("#ta").load("error.txt");
				$("#ta").toggle();
				$("#taa").val($("#ta").val());
			}
		}, 2000);

	});
</script>
</html>