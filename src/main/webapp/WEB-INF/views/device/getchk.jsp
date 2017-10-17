<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.JsonElement"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="kr.co.comes.projectA.controller.SocketHandler"%>
<%@page import="java.util.ArrayList"%>
	<%
		/* Get Device 작업 부분 */
		Object obj1, obj2;
		JSONObject jobj1, jobj2;
		ArrayList<String> list = new ArrayList<String>();
		String param = request.getParameter("param");
		String ipraddr = request.getRemoteAddr().toString();

		System.out.println("시작");
		JSONParser parser = new JSONParser();
		System.out.println(param);

		obj1 = parser.parse(param);
		System.out.println(obj1);

		jobj1 = (JSONObject) obj1;
		System.out.println(jobj1);

		String code = (String) jobj1.get("code");
		String codec = "OK";
		String omes = "연결에 성공했습니다.";
		String emes = "연결에 실패했습니다.";
		String dmes = "이미 등록된 기기가 존재합니다.";

		SocketHandler socket = new SocketHandler();
		
		
		System.out.println("if문 시작 전");
		if (code.equals(codec)) {
		System.out.println("if문 시작 후");
			JSONArray payload = (JSONArray) jobj1.get("payload");
			System.out.println(payload);

		System.out.println("for문 시작 전");
			for(int i= 1; i-1 < payload.size(); i++){
				JSONObject jo = (JSONObject)payload.get(i-1);
				String temp = (String)jo.get("serial" + i + "");
				System.out.println(temp);
				list.add(temp);
			}
		System.out.println("for문 종료");
			String seriList2 = list.toString();
		String seriList1 = seriList2.replace("[", "");
			String seriList = seriList1.replace("]", "");
			socket.doMessage(ipraddr, seriList);			
		}else{
			socket.doMessage(ipraddr, emes);
		}
	%>
	<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Device</title>
</head>
<body>
</body>
</html>