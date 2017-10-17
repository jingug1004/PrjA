<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/include/home_head.jsp"%>
<%
	String menu_role = (String) session.getAttribute("role");
	String id = (String) session.getAttribute("id");
%>
<html>
<head>
<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<!-- Navigation -->

<script>
	$(document).ready(function() {
						$('#issue_create')
								.click(
										function() {
											var URL = "/project/issue/create";
											window
													.open(URL, "issueCreate",
															"width=800, height=520, top=50,left=310");
										});

						$('#download').click(function() {
							location.href = "/download";
						});
						
						var menu_role = '<%=menu_role%>';
				if (menu_role != 0) {
					$('#user_menu').hide();
					$('#license_menu').hide();
					$('#main_menu').hide();
					if (menu_role == "null") {
						$('#side_menu').hide();
						$('#top_menu').hide();
					}
				}

			});
</script>
</head>
<body style="overflow:hidden; margin-top:0px">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"
		style="height: 70px;">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="padding: 0px;"><img
				alt="" src="/images/logo2.png"></a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-left top-nav" style="padding-top: 10px;">
			<li><a href="#" data-toggle="dropdown" id="current"> </a></li>

		</ul>
		<ul id="top_menu" class="nav navbar-right top-nav"
			style="padding-top: 10px;">
			<li class="dropdown"><a href="#" id="top_name"
				class="dropdown-toggle" data-toggle="dropdown"><i
					class="fa fa-user"></i> <%=session.getAttribute("name")%>( <%
 	if (menu_role != null) {
 		if (menu_role.equals("0")) {
 %>Administrator<%
 	} else {
 %>User<%
 	}
 	}
 %> )<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="/user/change?id=<%=id%>"><i
							class="fa fa-fw fa-user"></i> My Information</a></li>
					<li><a href="#" id="download"><i class="fa fa-fw fa-gear"></i>
							Download</a></li>
					<li class="divider"></li>
					<li><a href="/logout"><i class="fa fa-fw fa-power-off"></i>
							Log out</a></li>
				</ul></li>

		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">

			<ul id="side_menu" class="nav navbar-nav side-nav" style="top: 70px;">
				<c:set var="path" value="${pageContext.request.contextPath}" />
				<li id="main_menu"><a href="/main" data-toggle="collapse"
					data-target="#main"><i class="fa fa-fw fa-dashboard"></i> Main
				</a></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#project_menu"><i class="fa fa-fw fa-edit"></i>
						Project <i class="fa fa-fw fa-caret-down"></i> </a>
					<ul id="project_menu" class="collapse">
						<li><a href="${path}/project/list"><span>Main</span></a></li>
						<li><a href="${path}/project/report">Report</a></li>
					</ul></li>
				<li id="user_menu"><a href="${path}/user/list"
					data-toggle="collapse" data-target="#user"><i
						class="fa fa-fw fa-user"></i> User Management</a></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#device_menu"><i class="fa fa-fw fa-edit"></i>
						Device <i class="fa fa-fw fa-caret-down"></i> </a>
					<ul id="device_menu" class="collapse">
						<li><a href="${path}/device/devmain"><span>Management</span></a></li>
						<li><a href="${path}/device/devcontrol">Control</a></li>
					</ul></li>
				<li><a href="/app/appmain" data-toggle="collapse"
					data-target="#App"><i class="fa fa-fw fa-edit"></i> App
						Management</a></li>
				<li id="license_menu"><a href="${path}/license/list"
					data-toggle="collapse" data-target="#license_ul_menu"><i
						class="fa fa-fw fa-edit"></i> License Management</a>
				<li><a href="/help_support" data-toggle="collapse"
					data-target="#helf"><i class="fa fa-fw fa-edit"></i> Help /
						Support </a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
<!-- 	<iframe src="/project/list2_top"
		style="position: absolute; width: 100%; height: 100%;"></iframe> -->
	<iframe src="/project/list2"
		style="position: absolute; width: 100%; height: 100%;"></iframe>

</body>

</html>