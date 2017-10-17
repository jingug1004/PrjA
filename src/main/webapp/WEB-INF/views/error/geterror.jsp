<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<%@ page import="org.springframework.web.multipart.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%	
	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
	String filename = "";
	MultipartFile file = mpRequest.getFile(filename);
	File f = new File("C:\\project\\ErrorLog.txt");
	file.transferTo(f);
	FileReader fr = null;
	BufferedReader br = null;

	String read = null;

	fr = new FileReader(f);
	br = new BufferedReader(fr);

	while ((read = br.readLine()) != null) {
		System.out.println(read);
	}
	if (fr != null)
		fr.close();
	if (br != null)
		br.close();
%>
<script type="text/javascript">
	
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="textView1"></div>
</body>
</html>