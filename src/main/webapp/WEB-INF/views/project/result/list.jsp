<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<script src="/js/jquery.minical.js"></script>
<script>
	var msg;

	function read(resid) {
		var URL = "/project/result/modify?projid="+${lc.projid}+"&senid="+${lc.senid}+"&phid="+${lc.phid}+"&resid="+resid;
		window.open(URL,'popup','scrollbars=yes,height=' + screen.height + ',width=' + screen.width + 'fullscreen=yes'); 
	}

	$(document).ready(
			function() {
				var formObj = $("form[role='form']");
				msg = '${resultmsg}';

				if ('${resultmsg}' != "") {
					msg = msg.replace(/다/g, "다\n");
					alert(msg);
					msg = msg.replace(/\n/g, "<br>");
					$('#alert').html(msg);
				}
				
						$('#sort').change(function() {
					 		formObj.attr("action", "/project/result/list");
					 		formObj.submit();
				 		});

						//$('#current').html("Current : ${name.proj_abbr} > ${name.ph_abbr} > ${name.s_abbr}");
						
						
						var proj_abbr = '${proj_abbr}';
						var ph_abbr = '${ph_abbr}';
						var senaabbr = '${senaabbr}';
						var pos1,pos2,pos3;
						
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
						
						if(senaabbr != ""){
							pos3 = "&nbsp;&nbsp;>&nbsp;&nbsp;<a id ='senaabbr'>"+senaabbr+"</a>";
						}else {
							pos3 = "&nbsp;&nbsp;>&nbsp;&nbsp;<a id ='senaabbr'>&middot;&middot;&middot;</a>";
						}
						
						$('#current').html(pos1+pos2+pos3);
						
						$('#dev_search').click(function() {
							
						});
						
						$('#proj_abbr').click(function() {
							location.href="/project/phase/list?projid="+${lc.projid};
						});
						
						$('#ph_abbr').click(function() {
							location.href="/project/case/list?projid="+${lc.projid}+"&phid="+${lc.phid};
						});
						
						$('#search').click(function() {
							formObj.attr("action", "/project/result/list");
							formObj.submit();
						});
						
						$('#del').click(function() {
							if (confirm("정말 삭제하시겠습니까?") == true) {
								formObj.attr("action","/project/result/remove");
								formObj.attr("method", "post");
								formObj.submit();
							}
						});
						
						$('#test').click(function() {
							var URL = "/project/result/test?projid="+${lc.projid}+"&phid="+${lc.phid}+"&senid="+${lc.senid};
							window.open(URL,'popup','scrollbars=yes,height=' + screen.height + ',width=' + screen.width + 'fullscreen=yes'); 
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
						 
						 $("span.demo-1 :text").minical();
						 $("span.demo-2 :text").minical();

					});

</script>

</head>
<body>

	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12"
						style="margin-bottom: 15px; margin-top: 15px;">
						<h2>Set of Test Result</h2>
					</div>
					<div class="col-lg-6">
						<h4>Project&nbsp;:&nbsp;${name.proj_name}</h4>
					</div>
					<div class="col-lg-6">
						<h4>Phase&nbsp;:&nbsp;${name.ph_name}</h4>
					</div>
					<div class="col-lg-12" style="margin-bottom: 15px">
						<h4>Case&nbsp;:&nbsp;${name.s_name}</h4>
					</div>
				</div>
				<form role="form">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group input-group">
								<input type="hidden" name="projid" value="${lc.projid}">
								<input type="hidden" name="phid" value="${lc.phid}"> <input
									type="hidden" name="senid" value="${lc.senid}"> <span
									class=input-group-addon>Device</span> <input type="text"
									class="form-control" name="keyword" id="dev_name"
									value="${lc.keyword}"><span class="input-group-btn">
									<button class="btn btn-default" type="button" id="dev_search">Find</button>
								</span> <span class=input-group-addon>일자</span><span class="demo-1">
									<input type="text" class="form-control" name="fromdate"
									value="${lc.fromdate}">
								</span><span class=input-group-addon>~</span> <span class="demo-2"><input
									type="text" class="form-control" name="todate"
									value="${lc.todate}"></span><span class="input-group-btn">
								</span><select class="form-control" name="result"
									style="margin-right: 10px">
									<option value='2'
										<c:out value="${lc.result eq '2' ?'selected':''}"/>>전체</option>
									<option value='0'
										<c:out value="${lc.result eq '0' ?'selected':''}"/>>성공</option>
									<option value='1'
										<c:out value="${lc.result eq '1' ?'selected':''}"/>>실패</option>
								</select> <span class="input-group-btn" style="margin-left: 5px">
									<button class="btn btn-default" type="button" id="search">
										Search <i class="fa fa-search"></i>
									</button>
								</span>
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
											<div class="col-lg-9" style="padding-top: 10px">
												<i class="fa fa-fw fa-check"></i> 목록
											</div>
											<div class="col-lg-3">
												<select class="form-control" id="sort" name="sort"
													style="margin-right: 10px">
													<option value=''>-----</option>
													<option value='dev'
														<c:out value="${lc.sort eq 'dev' ?'selected':''}"/>>Device</option>
													<option value='app'
														<c:out value="${lc.sort eq 'app' ?'selected':''}"/>>App</option>
													<option value='date'
														<c:out value="${lc.sort eq 'date' ?'selected':''}"/>>점검일시</option>
												</select>
											</div>
										</div>
									</h3>

								</div>


								<div class="panel-body">
									<div class="row">
										<div class="col-lg-12">
											<span id="alert" style='color: red'></span>
											<p style="float: right">
												<input type="button" class="btn btn-default" id="del"
													value="del"> <input type="button"
													class="btn btn-default" id="test" value="Run Test">
											</p>
										</div>
									</div>
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th><input id="checkAll" name="checkAll"
														type="checkbox" /></th>
													<th>Device 명</th>
													<th>App 명</th>
													<th>점검일</th>
													<th>시작시간</th>
													<th>종료시간</th>
													<th>result</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="resultVO">
													<tr>
														<td><input id="check" name="check" type="checkbox"
															value="${resultVO.resid}"></td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.dev_name}</td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.app_name}</td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.adddate}</td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.starttime}</td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.endtime}</td>
														<td style='cursor: pointer'
															onclick="read(${resultVO.resid})">${resultVO.result}</td>
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
											href="list${pageMaker.makeResultSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
										<!-- 왼쪽화살표 -->
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makeResultSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<!-- 오른쪽화살표 -->
										<li><a
											href="list${pageMaker.makeResultSearch(pageMaker.endPage +1)}">&raquo;</a></li>
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