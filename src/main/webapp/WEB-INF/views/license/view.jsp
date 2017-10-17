<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<%
	try {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor",
				"postgres", "comes");

		Integer serverid = null;
		String commercial = null;
		Date enddate = null;
		Integer maxuser = null;
		Integer maxdevice = null;
		Date adddate = null;

		Statement st = con.createStatement();

		String sql = "SELECT serverid, case when commercial = '0' then '상용' else '체험판' end as commercial, enddate, maxuser, maxdevice, adddate FROM \"license\"";

		st.executeQuery(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			serverid = rs.getInt("serverid");
			commercial = rs.getString("commercial");
			enddate = rs.getDate("enddate");
			maxuser = rs.getInt("maxuser");
			maxdevice = rs.getInt("maxdevice");
			adddate = rs.getDate("adddate");
%>
</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="container">
					<div class="row">
						<div class="col-lg-12" style="margin-bottom: 15px">
							<h2 style="font-weight: bold;">Detailed
								View</h2>
						</div>
						<div class="col-md-8 col-md-offset-2" style="margin-left: 0px;">
							<fieldset>
								<legend>라이선스 정보</legend>


								<div class="form-group col-md-6">
									<label>버전</label> <input type="text" class="form-control"
										name="commercial" id="commercial" readonly="readonly"
										value="<%=commercial%>">
								</div>

								<div class="form-group col-md-6">
									<label>만료일자</label> <input type="text" class="form-control"
										name="enddate" id="enddate" value="<%=enddate%>"
										readonly="readonly">
								</div>


								<div class="form-group col-md-6">
									<label>사용가능한 사용자 수</label> <input type="text"
										class="form-control" name="maxuser" id="maxuser"
										value="<%=maxuser%>" readonly="readonly">
								</div>

								<div class="form-group col-md-6">
									<label>사용 가능한 디바이스 수</label> <input type="text"
										class="form-control" name="maxdevice" id="maxdevice"
										value="<%=maxdevice%>" readonly="readonly">
								</div>

								<div class="form-group col-md-6">
									<label>등록일자</label> <input type="text" class="form-control"
										name="adddate" id="adddate" value="<%=adddate%>"
										readonly="readonly">
								</div>


								<!-- <div class="controls">
									<button id="submit" type="submit"
										class="btn btn-primary input-medium pull-right" onclick="">뒤로가기</button>
								</div> -->

							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
		} catch (Exception e) {
			out.println("DB접속 실패");
			e.printStackTrace();
		}
	%>
</body>
</html>