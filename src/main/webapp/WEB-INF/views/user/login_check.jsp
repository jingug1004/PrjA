<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="kr.co.comes.projectA.util.AES256"%>
<%@ page import="org.apache.commons.codec.net.URLCodec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login_check</title>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		formObj.attr("action", "/");
		formObj.attr("method", "post");
		formObj.submit();
	});
</script>
</head>
<body>
	<%
		Connection con;
		String key = "kr.co.comes.projecta";
		AES256 a256 = new AES256(key);
		URLCodec codec = new URLCodec();
	
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor", "postgres",
					"comes");

			String user_id = request.getParameter("id");
			String user_pwd = request.getParameter("pwd");
			String encpwd = codec.encode(a256.encrypt(""+user_pwd));
			String name = null;
			String decname = null;
			String role = null;
			Integer clntid = null;
			String telno = null;
			String email = null;
			Date adddate = null;
			Date upddate = null;
			String adduser = null;
			String upduser = null;

			Statement st = con.createStatement();

			String sql = "SELECT * FROM \"user\" WHERE id ='" + user_id + "'AND pwd = '" + encpwd + "'";

			st.executeQuery(sql);
			ResultSet rs = st.executeQuery(sql);

			Boolean isLogin = false;
			while (rs.next()) {
				isLogin = true;
				name = rs.getString("name");
				decname = a256.decrypt(codec.decode(name));
				role = rs.getString("role");
				clntid = rs.getInt("clntid");
				telno = rs.getString("telno");
				email = rs.getString("email");
				adddate = rs.getDate("adddate");
				upddate = rs.getDate("upddate");
				adduser = rs.getString("adduser");
				upduser = rs.getString("upduser");
			}

			if (isLogin) {
				session.setAttribute("id", user_id);
				session.setAttribute("name", decname);
				session.setAttribute("role", role);
				session.setAttribute("clntid", clntid);
				session.setAttribute("telno", telno);
				session.setAttribute("email", email);
				session.setAttribute("adddate", adddate);
				session.setAttribute("upddate", upddate);
				session.setAttribute("adduser", adduser);
				session.setAttribute("upduser", upduser);

				System.out.println(role);
				Object dest = session.getAttribute("dest");

				File file = new File("C:\\Users\\comes\\projecta.jar");
				// test.txt 파일이 있는지 확인
			/* 	if (!file.isFile()) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter outt = response.getWriter();
					outt.println("<script src='http://code.jquery.com/jquery-1.11.3.js'></script>"
							+ "<script>$(document).ready(function() {"
							+ "alert('projecta.jar 파일이 없습니다. Controller를 다운로드 해주세요.');"
							+ "location.href='/download';});" + "</script>" + "");
					outt.flush();
				}  else {*/
					if (role.equals("0")) {
						response.sendRedirect(dest != null ? (String) dest : "/main");
					} else {
						response.sendRedirect(dest != null ? (String) dest : "/project/list");
					}
				/* } */

			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter outt = response.getWriter();
				outt.println("<script src='http://code.jquery.com/jquery-1.11.3.js'></script>"
						+ "<script>$(document).ready(function() {" + "var formObj = $('form[role='form']');"
						+ "formObj.attr('action', '/');" + "formObj.attr('method', 'post');"
						+ "formObj.submit(); </script>" + "});"
						+ "<form role='form'><input type='hidden' class='form-control'"
						+ "name='resultmsg' value='로그인에 실패했습니다 아이디 또는 비밀번호를 확인해주세요'>"
						+ "</form>");
				outt.flush();

			}

		} catch (SQLException e) {
			System.out.println(e);
			out.println("DB접속 실패");
		}
	%>
</body>
</html>