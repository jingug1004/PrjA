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

	var msg;
	$(document).ready(
					function() {
						msg = '${resultmsg}';

						if ('${resultmsg}' != "") {
							msg = msg.replace(/다/g, "다\n");
							alert(msg);
							msg = msg.replace(/\n/g, "<br>");
							$('#alert').html(msg);
						}

						var formObj = $("form[role='form']");
						console.log(formObj);
						//formObj.submit();

						$('#search').click(function() {
							formObj.attr("action", "/project/list");
							formObj.submit();
						});

						$('#new').click(function() {
							var URL = "/project/create";
							window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
						});	

						$('#asc').click(function() {
							formObj.attr("action","/project/list");
							$('#sort_value').html("<input type='hidden' name = 'sort' value='asc' >");
							formObj.submit();
						});

						$('#desc').click(function() {
							formObj.attr("action","/project/list");
							$('#sort_value').html("<input type='hidden' name = 'sort' value='desc' >");
							formObj.submit();
						});
						
						$('#delete').click(function() {
							if (confirm("정말 삭제하시겠습니까?") == true) {
								formObj.attr("action","/project/remove");
								formObj.submit();
							}
						});
						
						
						 $('#checkAll').click(function(){
					            if ($(this).is(':checked')) {
					                $("input[name=check]").prop("checked", true);
					            }
					            else {
					                $("input[name=check]").prop("checked", false);
					            } 
					             
					        });
						  
						 $('#check').click(function(){
					         $("input[name=checkAll]").prop("checked", false);
					     });
						
						 $('#sort').change(function() {
							 formObj.attr("action", "/project/list");
							 formObj.submit();
						 });

					});

	function read(projid) {
		var URL = "/project/modify?projid="+projid;
		window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
	}; 
	
	function phase(projid) {
		var formObj = $("form[role='form2']");
		formObj.attr("action","/project/phase/list");
		$('#input').html("<input type='hidden' name = 'projid' value="+projid+">");
		formObj.submit();
	}; 
	
	function case1(projid,phid) {
		var formObj = $("form[role='form2']");
		formObj.attr("action","/project/case/list");
		$('#input').html("<input type='hidden' name = 'projid' value="+projid+">"+"<input type='hidden' name = 'phid' value="+phid+">");
		formObj.submit();
	}; 
	
	function issue(projid,phid) {
		var formObj = $("form[role='form2']");
		formObj.attr("action","/project/issue/list");
		$('#input').html("<input type='hidden' name = 'projid' value="+projid+">");
		formObj.submit();
	}; 

</script>

</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">
				<form role="form">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group input-group">
								<span class=input-group-addon>프로젝트명</span> <input type="text"
									class="form-control" name="keyword" value="${lc.keyword}"><span
									class="input-group-btn"><button class="btn btn-default"
										type="button" id="search">
										검색 <i class="fa fa-search"></i>
									</button></span>
							</div>

						</div>
					</div>
					<!-- /.row -->


					<div class="row">

						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">

										<div class="row">
											<div class="col-lg-8" style="padding-top: 10px">
												<i class="fa fa-fw fa-check"></i> 목록
											</div>
											<div class="col-lg-4">

												<div class="form-group input-group"
													style="margin-bottom: 0px;">
													<span class="input-group-addon"
														style="border: none; background-color: #f5f5f5; font-weight: bold">정렬${lc.category}</span>
													<!-- <span class=input-group-addon>정렬</span> -->
													<select class="form-control" id="sort" name="sort"
														style="margin-right: 10px">
														<option value="-"
															<c:out value="${lc.sort eq '-' ?'selected':''}"/>>---</option>
														<option value="n"
															<c:out value="${lc.sort eq 'n' ?'selected':''}"/>>Project
															Name</option>
														<option value="d"
															<c:out value="${lc.sort eq 'd' ?'selected':''}"/>>생성일</option>
														<option value="t"
															<c:out value="${lc.sort eq 't' ?'selected':''}"/>>Last
															test</option>
													</select>
												</div>
											</div>
										</div>
									</h3>

								</div>


								<div class="panel-body">
									<div class="row">
										<div class="col-lg-12">
											<span id="alert" style='color: red'></span>
											<p style="float: right">
												<input type="button" class="btn btn-default" id="delete"
													value="del"> <input type="button"
													class="btn btn-default" id="new" value="new">
											</p>
										</div>
									</div>
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th><input id="checkAll" name="checkAll"
														type="checkbox" /></th>
													<th>Project Name</th>
													<th>Abbr</th>
													<th>생성일</th>
													<th>Last test</th>
													<th>단계</th>
													<th>등록(case)</th>
													<th>ISSUE</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="projectVO">
													<tr>
														<td><input name="check" type="checkbox" id="check"
															value="${projectVO.projid}" /></td>
														<td><a id="read" href="#"
															onclick="read(${projectVO.projid})">${projectVO.name}</a></td>
														<td>${projectVO.abbr}</td>
														<td>${projectVO.adddate}</td>
														<td>${projectVO.upddate}</td>
														<td><c:if test="${projectVO.phase ne null}">
																<a href="#" onclick="phase(${projectVO.projid})">${projectVO.phase}</a>
															</c:if> <c:if test="${projectVO.phase eq null}">
																<a href="#" onclick="phase(${projectVO.projid})">N/A</a>
															</c:if></td>
														<td><a href="#"
															onclick="case1(${projectVO.projid},${projectVO.phid})">${projectVO.case1}</a></td>
														<td><a href="#"
															onclick="issue(${projectVO.projid},${projectVO.phid})">${projectVO.issue}</a></td>
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
											href="list${pageMaker.makeListSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
										<!-- 왼쪽화살표 -->
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makeListSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<!-- 오른쪽화살표 -->
										<li><a
											href="list${pageMaker.makeListSearch(pageMaker.endPage +1)}">&raquo;</a></li>
									</c:if>

								</ul>
							</div>

						</div>

					</div>
				</form>

			</div>
		</div>
	</div>
</body>

</html>