<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn1").click(
				function() {
					$.ajax({
						url : "txt/basic.txt",
						dataType : "text",
						timeout : 30000,
						success : function(data) {
							//alert(data);
							$("#textView1").html(data);
						},
						error : function(xhr, message, errorThrown) {
							var msg = xhr.status + " / " + message + " / "
									+ errorThrown;
							//alert(msg);
							$("#textView1").html(msg);
						}
					});
				});
	});
</script>
</head>
<body>
	<input id="btn1" type="button" value="Click Me">

	<div id="textView1"></div>
</body>
</html>