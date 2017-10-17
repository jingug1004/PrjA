<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

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
						<h2 class="page-header" style="font-weight: bold;">Set of
							Project</h2>
					</div>
				</div>
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12" style="margin-bottom: 20px;">
						<h2 style="margin-bottom: 20px;">Project Summary</h2>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${total}</b> Projects
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Ongoing</h3>
							<b style="font-size: 50px">${ongoing}</b> Projects

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Hold</h3>
							<b style="font-size: 50px">${hold}</b> Projects
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>End</h3>
							<b style="font-size: 50px">${end}</b> Projects
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>