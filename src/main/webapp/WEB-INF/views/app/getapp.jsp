<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="kr.co.comes.projectA.controller.SocketHandler"%>
<%
	Object obj1, obj2;
	JSONObject jobj1, jobj2, packageinfo;

	String param = request.getParameter("param");
	String ipraddr = request.getRemoteAddr().toString();

	JSONParser parser = new JSONParser();

	obj1 = parser.parse(param);

	jobj1 = (JSONObject) obj1;

	String code = (String) jobj1.get("code");
	String codec = "OK";
	String omes = "앱 정보 불러오기에 성공했습니다.";
	String emes = "앱 정보 불러오기에 실패했습니다.";
	String dmes = "이미 등록된 앱입니다.";

	SocketHandler socket = new SocketHandler();

	if (code.equals(codec)) {

		JSONArray payload = (JSONArray) jobj1.get("payload");

		obj2 = payload.get(0);

		jobj2 = (JSONObject) obj2;

		String id = (String) jobj2.get("userId");
		System.out.println(id);

		packageinfo = (JSONObject) jobj2.get("packageInfo");

		jobj2 = (JSONObject) packageinfo;
		String packagename = (String) jobj2.get("packageName");
		String appversion = (String) jobj2.get("appVersion");
		System.out.println(appversion);
		try {
			int rowcount = 0;

			Class.forName("org.postgresql.Driver");
			Connection con1 = DriverManager
					.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor", "postgres", "comes");

			Statement st1 = con1.createStatement();

			String sql1 = "SELECT count(*) FROM app WHERE packagename = '" + packagename + "' and appversion = '" + appversion + "' and adduser = '" + id +"'";

			st1.executeQuery(sql1);

			ResultSet rs1 = st1.executeQuery(sql1);

			if (rs1.next()) {
				rowcount = rs1.getInt(1);
			}
			if (rowcount == 1) {
				socket.doMessage(ipraddr, dmes);

			} else {

				Connection con = DriverManager.getConnection(
						"jdbc:postgresql://192.168.0.140:5432/ResourceMonitor", "postgres", "comes");

				Statement st = con.createStatement();

				String sql = "INSERT INTO app (appname, appversion, packagename, adddate, adduser) VALUES ('"
						+ packagename + "', '" + appversion + "', '" + packagename + "', NOW() ,'" + id + "')";

				st.executeUpdate(sql);

				socket.doMessage(ipraddr, omes);
			}
		} catch (Exception e) {
			out.println("DB접속 실패 및 쿼리문 오류");
			out.println(e);
			e.printStackTrace();
		}
	} else {
		socket.doMessage(ipraddr, emes);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get App</title>
</head>
<body>
</body>
</html>