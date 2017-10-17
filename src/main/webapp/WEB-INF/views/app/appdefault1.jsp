<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>App Defult</title>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		out.println(id);
		String appsid = request.getParameter("defa");
		out.println(appsid);
		int appid = Integer.parseInt(appsid);
		out.println(appid);

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor",
					"postgres", "comes");

			Statement st = con.createStatement();

			String sql = "UPDATE \"user\" SET appid = '" + appid + "' WHERE id = '" + id + "'";
			st.executeUpdate(sql);

			response.sendRedirect("/app/apppop");

		} catch (Exception e) {
			out.println("DB접속 실패");
			e.printStackTrace();
		}
	%>

</body>
</html>