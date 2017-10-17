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
		JSONObject jobj1, jobj2, devinfo;
		String param = request.getParameter("param");
		String ipraddr = request.getRemoteAddr().toString();
		String status = "1";
	
		JSONParser parser = new JSONParser();

		obj1 = parser.parse(param);

		jobj1 = (JSONObject) obj1;

		String code = (String) jobj1.get("code");
		String codec = "OK";
		String omes = "무선연결에 성공했습니다.";
		String emes = "무선연결에 실패했습니다.";	
		String dmes = "이미 무선 등록된 기기입니다.";	
	
		SocketHandler socket = new SocketHandler();
		
		if (code.equals(codec)) {
			
			JSONArray payload = (JSONArray) jobj1.get("payload");

			obj2 = payload.get(0);

			jobj2 = (JSONObject) obj2;
			
			String devseri = (String)jobj2.get("title");
			String id = (String)jobj2.get("userId");
			
			devinfo = (JSONObject) jobj2.get("deviceInfo");

			jobj2 = (JSONObject) devinfo;
			
			String modelno = (String) jobj2.get("model");
			String serial = (String) jobj2.get("serial");
			String os = (String) jobj2.get("osType");
			String version = (String) jobj2.get("build");
			String ramsize = (String) jobj2.get("ramSize");
			String company = (String) jobj2.get("netCompany");
			String networkstandard = (String) jobj2.get("networkStandard");
			String ipaddr = (String) jobj2.get("ipAddr");
			String sport = (String) jobj2.get("portNum");
			int port = Integer.parseInt(sport);
			Long width = (Long) jobj2.get("width");
			Long height = (Long) jobj2.get("height");
			
			
			try {
				int rowcount = 0;
				
				Class.forName("org.postgresql.Driver");
				Connection con1 = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor",
						"postgres", "comes");
				Statement st1 = con1.createStatement();
				
				String sql1 = "SELECT count(*) FROM device WHERE modelno = '" + modelno + "' and \"serial\" = '" + serial + "' and adduser = '" + id + "'";
				
				st1.executeQuery(sql1);
				
				ResultSet rs1 = st1.executeQuery(sql1);
				
				if(rs1.next()){
					rowcount = rs1.getInt(1);
				}
				if(rowcount == 1){
					socket.doMessage(ipraddr, dmes);
					
				}else{
				
				Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor",
						"postgres", "comes");
				Statement st = con.createStatement();

				String sql = "INSERT INTO device (name, ramsize, os, version, company, networkstandard, ipaddr, port, modelno, adddate, adduser, serial, width, height, status) VALUES ('"
						+ modelno + "', '" + ramsize + "', '" + os + "', '" + version + "', '" + company + "', '"
						+ networkstandard + "', '" + ipaddr + "', '" + port + "', '" + modelno + "', NOW() ,'" + id
						+ "', '" + serial + "', '" + width + "', '" + height + "','" +status + "')";

				st.executeUpdate(sql);
				
				socket.doMessage(ipraddr, omes);
				}
				
			} catch (Exception e) {
				out.println("DB접속 실패 및 쿼리문 오류");
				out.println(e);
				e.printStackTrace();
			}
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