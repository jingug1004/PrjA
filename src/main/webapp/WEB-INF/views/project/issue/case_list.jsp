<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>


<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>SKT Test Automation</title>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>

<script>

$(document).ready(function() {

});

	function case_close(name,senid) {
	 	opener.document.getElementById("senid").value = senid;
		opener.document.getElementById("sena_name").value = name; 
 		window.close();
		
	};
</script>

</head>
<body style="margin-top: 5px;">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">Scenario list</h2>
			</div>
		</div>
		<form role="form2"></form>
		<form role="form">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-fw fa-check"></i> 목록

							</h3>

						</div>

						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>Scenario Name</th>
											<th>Scenario Abbr</th>
											<th>Scenario Desc</th>
											<th>생성일</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${list}" var="caseVO">
											<tr>
												<td><a href="#" onclick="case_close('${caseVO.senaname}',${caseVO.senid})">${caseVO.senaname}</a></td>
												<td>${caseVO.senaabbr}</td>
												<td>${caseVO.senadesc}</td>
												<td>${caseVO.adddate}</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>


						</div>
					</div>
					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="case_list${pageMaker.makePhaseSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
								<!-- 왼쪽화살표 -->
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="case_list${pageMaker.makePhaseSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<!-- 오른쪽화살표 -->
								<li><a
									href="case_list${pageMaker.makePhaseSearch(pageMaker.endPage +1)}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>

				</div>

			</div>

		</form>
	</div>
</body>
</html>