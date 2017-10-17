<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="kr.co.comes.projectA.controller.SocketHandler"%>
<%@ page import="kr.co.comes.projectA.dto.EventVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<%!int i = 0;%>
<title>Get Scenario</title>
<%
	String param = request.getParameter("param");
	System.out.println("로그:" + param);

	EventVO vo = new EventVO();
	SocketHandler socket = new SocketHandler();
	String ipaddr = request.getRemoteAddr().toString();
	System.out.println("IP:" + ipaddr);
	Gson gson = new Gson();
	JSONParser parser = new JSONParser();

	//정상적인 json 파싱을 위한 치환
	param = param.replace("\\\"", "\"").replace("§№§", " ");
	System.out.println("변경 후:" + param);

	Object obj1, obj2, obj3;
	JSONObject jobj1, jobj2, jobj3, startPoint, endPoint, element, ex_startPoint, ex_endPoint, ex2_startPoint,
			ex2_endPoint;
	String keyValue = null;
	String x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6;
	String xy = null;

	String code=""; 
	String msg= "";
	String seq="";

	obj1 = parser.parse(param);

	jobj1 = (JSONObject) obj1;

	//code가 날라올 경우 (레코딩이 정상적으로 끝난경우, 오류가 난경우)
	if (jobj1.toString().contains("code")) {
		code = jobj1.get("code").toString();
		System.out.println(code);
		msg = jobj1.get("msg").toString();
		System.out.println(msg);
		//error 난 경우
		if (!code.equals("OK")) {
			System.out.println("doMessage start");
			vo.setMsg(msg);
			String source = gson.toJson(vo);
			System.out.println("get_scenario:\n" + source);
			socket.doMessage(ipaddr, source);
		}else {
		}
	//code가 날라오지 않은 경우(레코딩 중인 경우)
	} else {

		vo.setParam(param);

		seq = jobj1.get("seq").toString();
		System.out.println(seq);

		JSONObject eventInfo = (JSONObject) jobj1.get("eventInfo");

		String eType = eventInfo.get("eType").toString();
		System.out.println(eType);

		if (eType.equals("pressKey")) {
			keyValue = eventInfo.get("keyValue").toString();
			vo.setEvtact(keyValue);
		} else {

			JSONObject position = (JSONObject) eventInfo.get("position");

			startPoint = (JSONObject) position.get("startPoint");
			x1 = startPoint.get("x").toString();
			y1 = startPoint.get("y").toString();

			xy = x1 + "," + y1;

			endPoint = (JSONObject) position.get("endPoint");
			x2 = endPoint.get("x").toString();
			y2 = endPoint.get("y").toString();

			xy = xy + "," + x2 + "," + y2;

			if (eType.equals("swipe")) {

				String direction = eventInfo.get("direction").toString();
			} else if (eType.equals("gesture")) {

				ex_startPoint = (JSONObject) position.get("ex_startPoint");
				x3 = ex_startPoint.get("x").toString();
				y3 = ex_startPoint.get("y").toString();
				xy = xy + "," + x3 + "," + y3;

				ex_endPoint = (JSONObject) position.get("ex_endPoint");
				x4 = ex_endPoint.get("x").toString();
				y4 = ex_endPoint.get("y").toString();
				xy = xy + "," + x4 + "," + y4;

				if (position.get("ex2_startPoint") != null) {
					ex2_startPoint = (JSONObject) position.get("ex2_startPoint");
					x5 = ex_startPoint.get("x").toString();
					y5 = ex_startPoint.get("y").toString();
					xy = xy + "," + x5 + "," + y5;

					ex2_endPoint = (JSONObject) position.get("ex2_endPoint");
					x6 = ex_endPoint.get("x").toString();
					y6 = ex_endPoint.get("y").toString();
					xy = xy + "," + x6 + "," + y6;
				}
			}
			vo.setEvtact(eType);
		}

		vo.setSeq(Integer.parseInt(seq));
		vo.setXy(xy);
		System.out.println("ipaddr:" + ipaddr);
		String source = gson.toJson(vo);
		System.out.println("get_scenario:\n" + source);

		//해당 이벤트 정보 웹소켓으로 전달
		if (eType != null) {
			socket = new SocketHandler();
			System.out.println("doMessage start");
			socket.doMessage(ipaddr, source);
		}
	}
%>

<script type="text/javascript">
	
</script>
</head>
<body>
</body>
</html>