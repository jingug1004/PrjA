<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script>
	$(document).ready(function() {
		});
</script>

</head>
<body>

	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12" style="margin-bottom: 0px;">
						<h2 class="page-header" style="font-weight: bold;">Main</h2>
					</div>
				</div>
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h2 style="margin-bottom: 20px;">Project
							Summary</h2>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${p_total}</b> Projects
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Ongoing</h3>
							<b style="font-size: 50px">${p_ongoing}</b> Projects

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Hold</h3>
							<b style="font-size: 50px">${p_hold}</b> Projects
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>End</h3>
							<b style="font-size: 50px">${p_end}</b> Projects
						</div>

					</div>
				</div>
				
							<div class="row">
					<div class="col-lg-12">
						<h2 style="margin-bottom: 20px; padding-top: 20px;">User
							Summary</h2>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${u_total}</b> Users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Admin</h3>
							<b style="font-size: 50px">${u_admin}</b> Users

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Test Engineer</h3>
							<b style="font-size: 50px">${u_testenginner}</b> Users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Reviewer</h3>
							<b style="font-size: 50px">${u_reviewer}</b> Users
						</div>

					</div>
				</div>
				
							<div class="row">
					<div class="col-lg-12">
						<h2 style="margin-bottom: 20px; padding-top: 20px;">License
							Summary</h2>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${l_total}</b> Licenses
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Used</h3>
							<b style="font-size: 50px">${l_ongoing}</b> Licenses

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Unlicensed User</h3>
							<b style="font-size: 50px">${l_hold}</b> Licenses
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Reviewer</h3>
							<b style="font-size: 50px">${l_end}</b> Licenses
						</div>

					</div>
				</div>

	

			</div>
		</div>
	</div>

</body>

</html>