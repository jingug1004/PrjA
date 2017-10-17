<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>

<script>
	$(document)
			.ready(
					function() {
						var msg;
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
							formObj.attr("action", "/project/phase/list");
							formObj.submit();
						});

						$('#new').click(function() {
							var URL = "/project/phase/create?projid="+${lc.projid};
							window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
						});
						
						$('#delete').click(function() {
							if (confirm("정말 삭제하시겠습니까?") == true) {
								formObj.attr("action","/project/phase/remove");
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

					});

	function read(phid,projid) {
		var URL = "/project/phase/modify?phid="+phid+"&projid="+${lc.projid};
		window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
	}; 
	
	function case1(phid) {
		var formObj = $("form[role='form']");
		formObj.attr("action","/project/case/list");
		$('#input').html("<input type='hidden' name = 'phid' value="+phid+">");
		formObj.submit();
	}; 
	
	function issue(phid) {
		var formObj = $("form[role='form']");
		formObj.attr("action","/project/issue/list");
		$('#input').html("<input type='hidden' name = 'phid' value="+phid+">");
		formObj.submit();
	}; 

</script>

</head>
<body>

	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h2 class="page-header" style="font-weight: bold;">Set of
							Phase</h2>
					</div>
				</div>
				<form role="form">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group input-group">
								<span class=input-group-addon>Phase Name</span> <input
									type="text" class="form-control" name="keyword"
									value="${lc.keyword}"><span class="input-group-btn">
									<button class="btn btn-default" type="button" id="search">
										검색 <i class="fa fa-search"></i>
									</button>
								</span><span id=input></span><input type='hidden' name='projid'
									value='${lc.projid}'>
							</div>

						</div>
					</div>
					<!-- /.row -->


					<div class="row">

						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<i class="fa fa-fw fa-check"></i> 목록

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
													<th>Phase Name</th>
													<th>Phase Abbr</th>
													<th>생성일</th>
													<th>case</th>
													<th>Issue</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="phaseVO">
													<tr>
														<td><input id="check" name="check" type="checkbox"
															value="${phaseVO.phid}"></td>
														<td><a href="#" onclick="read(${phaseVO.phid})">${phaseVO.name}</a></td>
														<td>${phaseVO.abbr}</td>
														<td>${phaseVO.adddate}</td>
														<td><a href="#" onclick="case1(${phaseVO.phid})">${phaseVO.case1}</a></td>
														<td><a href="#" onclick="issue(${phaseVO.phid})">${phaseVO.issue}</a></td>
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
											href="list${pageMaker.makePhaseSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
										<!-- 왼쪽화살표 -->
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makePhaseSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<!-- 오른쪽화살표 -->
										<li><a
											href="list${pageMaker.makePhaseSearch(pageMaker.endPage +1)}">&raquo;</a></li>
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