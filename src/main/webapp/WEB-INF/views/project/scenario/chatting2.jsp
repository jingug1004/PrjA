<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chat</title>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {

		var Sock;
		
		Sock = new SockJS("/echo-ws");
		
		//소켓 시작
		Sock.onopen = function() {
			//Sock.send(JSON.stringify(event));
			<%System.out.println("Sock.onopen");%>
		};

		Sock.onmessage = function(evt) {
			<%System.out.println("Sock.onmessage");%>
			var data = evt.data;
			try {
				var obj = JSON.parse(data);
			}catch(e){  
				alert(data);
			}finally{
				
			}
			
			$("#chatMessage").append(data);
			$("#chatMessage").append("<br />");
			$("#chatMessage").scrollTop(99999999);
		};
		
		//소켓 끝
		Sock.onclose = function() {
		}

	});
</script>
</head>
<body>
	<div id="chatMessage" style="overFlow: auto; max-height: 500px;"></div>
</body>
</html>
