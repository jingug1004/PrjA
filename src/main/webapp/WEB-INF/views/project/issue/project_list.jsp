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

	function project_close(name,projid) {
	 	opener.document.getElementById("projid").value = projid;
		opener.document.getElementById("proj_name").value = name; 
 		window.close();
		
	};
</script>

</head>
<body style="margin-top: 5px;">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">Project 목록</h2>
			</div>
		</div>

		<form role="form">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>Project Name</th>
											<th>Abbr</th>
											<th>생성일</th>
											<th>Last test</th>
											<th>단계</th>
											<th>CASE</th>
											<th>ISSUE</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${list}" var="projectVO">
											<tr>
												<td><a href="#"
													onclick="project_close('${projectVO.name}',${projectVO.projid})">${projectVO.name}</a></td>
												<td>${projectVO.abbr}</td>
												<td>${projectVO.adddate}</td>
												<td>${projectVO.upddate}</td>
												<td><c:if test="${projectVO.phase ne null}">${projectVO.phase}</c:if>
													<c:if test="${projectVO.phase eq null}">N/A</c:if></td>
												<td>${projectVO.case1}</td>
												<td>${projectVO.issue}</td>
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
									href="project_list${pageMaker.makeListSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
								<!-- 왼쪽화살표 -->
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="project_list${pageMaker.makeListSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<!-- 오른쪽화살표 -->
								<li><a
									href="project_list${pageMaker.makeListSearch(pageMaker.endPage +1)}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>

				</div>

			</div>
		</form>
	</div>
</body>
</html>