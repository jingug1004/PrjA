<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
String id = (String) session.getAttribute("id");
String param = request.getParameter("param");
String seri = request.getParameter("param2");
System.out.println("/error/log -- param is " + param);
File file = null;
try {
	file = new File("/home/apache-tomcat-8.5.16/webapps/projectA/error/error.txt");
} catch (Exception e) {
	System.out.println(e.getMessage());
	return;
}
/*System.out.println("/error/log -- step 1");
System.out.println("/error/log -- file is " + file.getAbsolutePath());*/
try {
	FileWriter pw = new FileWriter(file,true);

	pw.write(param);
	pw.close();
} catch (Exception e) {
	System.out.println(e.getMessage());
}
%>
</body>
</html>