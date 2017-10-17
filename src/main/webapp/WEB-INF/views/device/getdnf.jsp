<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="kr.co.comes.projectA.controller.SocketHandler"%>

<%
	String param = request.getParameter("param");
	String ipraddr = request.getRemoteAddr().toString();

	String codec = "OK";
	String omes = "디바이스 연결상태를 확인해주세요.";
	String emes = "무선연결에 실패했습니다.";

	SocketHandler socket = new SocketHandler();

	try {
		int rowcount = 0;
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor",
				"postgres", "comes");
		Statement st = con.createStatement();

		String sql = "SELECT count(*) FROM device WHERE serial = " + param + " and status = 0";

		st.executeQuery(sql);

		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			rowcount = rs.getInt(1);
		}
		if (rowcount == 1) {
			socket.doMessage(ipraddr, omes);
		} else {
			Class.forName("org.postgresql.Driver");
			Connection con1 = DriverManager
					.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor", "postgres", "comes");
			Statement st1 = con1.createStatement();
			
			String sql1 = "DELETE FROM device WHERE serial = " + param + "and status = 1";
			
			socket.doMessage(ipraddr, omes);
		}

	} catch (Exception e) {
		out.println("DB접속 실패 및 쿼리문 오류");
		out.println(e);
		e.printStackTrace();
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