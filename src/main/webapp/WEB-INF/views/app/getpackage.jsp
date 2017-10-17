<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.JsonElement"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="kr.co.comes.projectA.controller.SocketHandler"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%
	Object obj1, obj2;
	JSONObject jobj1, jobj2, devinfo;
	String param = request.getParameter("param");
	String ipraddr = request.getRemoteAddr().toString();

	JSONParser parser = new JSONParser();

	obj1 = parser.parse(param);
	System.out.println(obj1);
	
	jobj1 = (JSONObject) obj1;
	System.out.println(jobj1);

	String code = (String) jobj1.get("code");
	String codec = "OK";
	String emes = "패키지 정보 로드에 실패했습니다.";
	
	SocketHandler socket = new SocketHandler();
	if (code.equals(codec)) {
		JSONArray payload = (JSONArray) jobj1.get("payload");

		obj2 = payload.get(0);

		jobj2 = (JSONObject) obj2;
		
		JSONArray pkgList = (JSONArray) jobj2.get("pkgList");
		System.out.println(pkgList);
		
		
		for (int i = 0; i < pkgList.size(); i++) {
			JSONObject jo = (JSONObject)pkgList.get(i);
			System.out.println(jo);
			String temp = (String)jo.get("" + i + "");
			System.out.println("temp : " + temp);
			socket.doMessage(ipraddr, temp);
		}
	}
		
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.js"></script>
<script type="text/javascript">
</script>
<title>Get Package</title>
</head>
<body>
		
</body>
</html>