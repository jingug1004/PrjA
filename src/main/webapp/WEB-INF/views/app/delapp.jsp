<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DevList Delete</title>
</head>
<body>
	<%
		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor","postgres","comes");
			
			String[] appchk = request.getParameterValues("RowCheck");
			
			Statement st = con.createStatement();
			
			for(int i = 0; i < appchk.length; i++){
				String sql = "DELETE FROM app where appid = " + appchk[i] +"";
				st.executeUpdate(sql);
			}
			
				response.sendRedirect("/app/appmain");
				
		}catch(Exception e){
			out.println("DB접속 실패");
			e.printStackTrace();
		}
	%>

</body>
</html>