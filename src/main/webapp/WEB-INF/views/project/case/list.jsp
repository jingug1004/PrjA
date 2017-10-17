<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<script>
	$(document).ready(
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
						
						var proj_abbr = '${proj_abbr}';
						var ph_abbr = '${ph_abbr}';
						var pos1;
						var pos2;
						
						if(proj_abbr != "") {
							pos1 = "Current Position&nbsp;:&nbsp;&nbsp;<a id ='proj_abbr'>"+proj_abbr+"</a>";
						}else {
							pos1 = "Current Position&nbsp;:&nbsp;&nbsp;<a id ='proj_abbr'>&middot;&middot;&middot;</a>";
						}
						
						if(ph_abbr != ""){
							pos2 = "&nbsp;&nbsp;>&nbsp;&nbsp;<a id ='ph_abbr'>"+ph_abbr+"</a>";
						}else {
							pos2 = "&nbsp;&nbsp;>&nbsp;&nbsp;<a id ='ph_abbr'>&middot;&middot;&middot;</a>";
						}
						
						$('#current').html(pos1+pos2);
						
						$('#proj_abbr').click(function() {
							location.href="/project/phase/list?projid="+${lc.projid};
						});
						
						$('#search').click(function() {
							formObj.attr("action", "/project/case/list");
							formObj.submit();
						});

						$('#new').click(function() {
							var URL = "/project/case/create?phid="+${lc.phid}+"&projid="+${lc.projid};
							window.open(URL,"popup","width=800, height=600, top=50,left=310, scrollbars=yes"); 
						});
						
						$('#delete').click(function() {
							if (confirm("정말 삭제하시겠습니까?") == true) {
								formObj.attr("action","/project/case/remove");
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
							 formObj.attr("action", "/project/case/list");
							 formObj.submit();
						 });
						 

					});

	function read(senid) {
		var URL = "/project/case/modify?projid="+${lc.projid}+"&phid="+${lc.phid}+"&senid="+senid;
		window.open(URL,"popup","width=800, height=600, top=50,left=310, scrollbars=yes"); 
	}; 

</script>

</head>
<body>


	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12"
						style="margin-bottom: 0px; margin-top: 15px; padding-bottom: 0px">
						<h2 style="font-weight: bold;">Set of Test Case</h2>
					</div>
					<div class="col-lg-6">
						<h3 class="page-header">Project&nbsp;:&nbsp;${p_name.proj_name}</h3>
					</div>
					<div class="col-lg-6">
						<h3 class="page-header">Phase&nbsp;:&nbsp;${p_name.ph_name}</h3>
					</div>
				</div>

				<form role="form">
					<div class="row">
						<div class="col-lg-10">
							<div class="form-group input-group">
								<span class=input-group-addon>Case</span> <input type="text"
									class="form-control" name="keyword" value="${lc.keyword}"><span
									class="input-group-btn">
									<button class="btn btn-default" type="button" id="search">
										검색 <i class="fa fa-search"></i>
									</button>
									<input type="hidden" name="projid" value="${lc.projid}"><input
									type="hidden" name="phid" value="${lc.phid}">
								</span><span id=insert></span><span id=input></span>
							</div>

						</div>
						<div class="col-lg-2">
							<select class="form-control" id="sort" name="sort"
								style="margin-right: 10px">
								<option>-----</option>
								<option value="name"
									<c:out value="${lc.sort eq 'name' ?'selected':''}"/>>이름</option>
								<option value='abbr'
									<c:out value="${lc.sort eq 'abbr' ?'selected':''}"/>>약어</option>
								<option value='date'
									<c:out value="${lc.sort eq 'date' ?'selected':''}"/>>생성일</option>
							</select>
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
													<th>Case Name</th>
													<th>Case Abbr</th>
													<th>분류</th>
													<th>App</th>
													<th>device</th>
													<th>result</th>
													<th>생성일</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="caseVO">
													<tr>
														<td><input id="check" name="check" type="checkbox"
															value="${caseVO.senid}"></td>
														<td><a href="#" onclick="read(${caseVO.senid})">${caseVO.senaname}</a></td>
														<td>${caseVO.senaabbr}</td>
														<td>${caseVO.s_category}</td>
														<td>${caseVO.app_name}</td>
														<td>${caseVO.dev_name}</td>
														<td><a
															href="/project/result/list?projid=${lc.projid}&phid=${lc.phid}&senid=${caseVO.senid}">${caseVO.result}</a></td>
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
											href="list${pageMaker.makeCaseSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
										<!-- 왼쪽화살표 -->
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makeCaseSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<!-- 오른쪽화살표 -->
										<li><a
											href="list${pageMaker.makeCaseSearch(pageMaker.endPage +1)}">&raquo;</a></li>
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