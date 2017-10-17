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
						
						$('#sort').change(function() {
					 		formObj.attr("action", "/project/issue/list");
					 		formObj.submit();
				 		});

						$('#search').click(function() {
							formObj.attr("action", "/project/issue/list");
							formObj.submit();
						});
						
						$('#search2').click(function() {
							formObj.attr("action", "/project/issue/list");
							formObj.submit();
						});
						
						$('#search3').click(function() {
							formObj.attr("action", "/project/issue/list");
							formObj.submit();
						});

						$('#new').click(function() {
							var URL = "/project/issue/create";
							window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
						});	

						$('#delete').click(function() {
							if (confirm("정말 삭제하시겠습니까?") == true) {
								formObj.attr("action","/project/issue/remove");
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
						 
						 if($('#defect').is(':checked')){
							 $('#seriousness').show();
							 $('#status').show();
						 }else {
							 $('#seriousness').hide();
							 $('#status').hide();
						 }
						 $('#defect').change(function() {
							 if($('#defect').is(':checked')){
								 $('#seriousness').show();
								 $('#status').show();
							 }else {
								 $('#seriousness').hide();
								 $('#status').hide();
							 }
						 });

					});

	function read(projid,seq,phid) {
		var URL = "/project/issue/modify?projid="+projid+"&phid="+phid+"&seq="+seq;
		window.open(URL,"popup","width=800, height=520, top=50,left=310,scrollbars=yes"); 
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
							Note</h2>
					</div>
				</div>
				<!-- Page Heading -->
				<div class="row" style="margin-bottom: 15px">
					<div class="col-lg-12">
						<h2 style="margin-bottom: 20px;">Note Summary</h2>
						<div class="col-lg-4"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Note</h3>
							<b style="font-size: 50px">${note}</b> 개

						</div>
						<div class="col-lg-4"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Issue</h3>
							<b style="font-size: 50px">${issue}</b> 개
						</div>
						<div class="col-lg-4"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Defect</h3>
							<b style="font-size: 50px">${defect}</b> 개
						</div>

					</div>
				</div>

				<form role="form2">
					<span id=input></span>
				</form>
				<form role="form">
					<div class="row">
						<div class="col-lg-12">
							<input type="hidden" name="projid" value="${lc.projid}">
							<div class="form-group">
								<label>Category</label> <label class="checkbox-inline">
									<input type="checkbox" name="category" value="0"
									<c:out value="${lc.category[0] eq '0' ? 'checked':''}"/>>Note
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="category" value="1"
									<c:out value="${lc.category[0] eq '1' ?'checked':''}"/>
									<c:out value="${lc.category[1] eq '1' ?'checked':''}"/>
									>Issue
								</label> <label class="checkbox-inline"> <input type="checkbox"
									id="defect" name="category" value="2"
									<c:out value="${lc.category[0] eq '2' ?'checked':''}"/>
									<c:out value="${lc.category[1] eq '2' ?'checked':''}"/>
									<c:out value="${lc.category[2] eq '2' ?'checked':''}"/>
									>Defect
								</label>
							</div>
							<div class="form-group" id="seriousness">
								<label>seriousness</label> <label class="checkbox-inline">
									<input type="checkbox" name="seriousness" value="0"
									<c:out value="${lc.seriousness[0] eq '0' ?'checked':''}"/>>Critical
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="seriousness" value="1"
									<c:out value="${lc.seriousness[0] eq '1' ?'checked':''}"/>
									<c:out value="${lc.seriousness[1] eq '1' ?'checked':''}"/>
									>Major
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="seriousness" value="2"
									<c:out value="${lc.seriousness[0] eq '2' ?'checked':''}"/>
									<c:out value="${lc.seriousness[1] eq '2' ?'checked':''}"/>
									<c:out value="${lc.seriousness[2] eq '2' ?'checked':''}"/>
									>Minor
								</label>
							</div>
							<div class="form-group" id="status">
								<label>Status</label> <label class="checkbox-inline"> <input
									type="checkbox" name="status" value="0"
									<c:out value="${lc.status[0] eq '0' ?'checked':''}"/>>New
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="status" value="1"
									<c:out value="${lc.status[0] eq '1' ?'checked':''}"/>
									<c:out value="${lc.status[1] eq '1' ?'checked':''}"/>
									>Open
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="status" value="2"
									<c:out value="${lc.status[0] eq '2' ?'checked':''}"/>
									<c:out value="${lc.status[1] eq '2' ?'checked':''}"/>
									<c:out value="${lc.status[2] eq '2' ?'checked':''}"/>
									>Assigned
								</label><label class="checkbox-inline"> <input type="checkbox"
									name="status" value="3"
									<c:out value="${lc.status[0] eq '3' ?'checked':''}"/>
									<c:out value="${lc.status[1] eq '3' ?'checked':''}"/>
									<c:out value="${lc.status[2] eq '3' ?'checked':''}"/>
									<c:out value="${lc.status[3] eq '3' ?'checked':''}"/>
									>status
								</label><label class="checkbox-inline"> <input type="checkbox"
									name="status" value="4"
									<c:out value="${lc.status[0] eq '4' ?'checked':''}"/>
									<c:out value="${lc.status[1] eq '4' ?'checked':''}"/>
									<c:out value="${lc.status[2] eq '4' ?'checked':''}"/>
									<c:out value="${lc.status[3] eq '4' ?'checked':''}"/>
									<c:out value="${lc.status[4] eq '4' ?'checked':''}"/>
									>Closed
								</label><label class="checkbox-inline"> <input type="checkbox"
									name="status" value="5"
									<c:out value="${lc.status[0] eq '5' ?'checked':''}"/>
									<c:out value="${lc.status[1] eq '5' ?'checked':''}"/>
									<c:out value="${lc.status[2] eq '5' ?'checked':''}"/>
									<c:out value="${lc.status[3] eq '5' ?'checked':''}"/>
									<c:out value="${lc.status[4] eq '5' ?'checked':''}"/>
									<c:out value="${lc.status[5] eq '5' ?'checked':''}"/>
									>Reopened
								</label>
							</div>
						</div>
						<div class="col-lg-10">
							<div class="form-group input-group">
								<span class=input-group-addon>note/descript</span> <input
									type="text" class="form-control" name="n_keyword"
									value="${lc.n_keyword}"><span class="input-group-btn"><button
										class="btn btn-default" type="button" id="search">Find
									</button></span>
							</div>

						</div>
						<div class="col-lg-2">
							<div class="form-group input-group" style="margin-bottom: 0px;">
								<select class="form-control" id="sort" name="sort"
									style="margin-right: 10px">
									<option value="">----</option>
									<option value='issue'
										<c:out value="${lc.sort eq 'issue' ?'selected':''}"/>>Issue
										name</option>
									<option value='date'
										<c:out value="${lc.sort eq 'date' ?'selected':''}"/>>Registered
										date</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group input-group">
								<span class=input-group-addon>Phase</span> <input type="text"
									class="form-control" name="ph_keyword" value="${lc.ph_keyword}"><span
									class="input-group-btn"><button class="btn btn-default"
										type="button" id="search3">Find</button></span>
							</div>

						</div>
					</div>
					<!-- /.row -->


					<div class="row">

						<div class="col-lg-12">
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
											<th><input name="checkAll" id="checkAll" type="checkbox" /></th>
											<th>Proj Name</th>
											<th>Phase Name</th>
											<th>Issue name</th>
											<th>category</th>
											<th>seriousness</th>
											<th>status</th>
											<th>registerer</th>
											<th>Registered date</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${list}" var="IssueVO">
											<tr>
												<td><input name="check" type="checkbox" id="check"
													value="${IssueVO.seq}^${IssueVO.projid}"></td>
												<td>${IssueVO.proj_name}</td>
												<td>${IssueVO.ph_name}</td>
												<td><a href="#"
													onclick="read(${IssueVO.projid},${IssueVO.seq},${IssueVO.phid})">${IssueVO.isuname}</a></td>
												<td>${IssueVO.category}</td>
												<td>${IssueVO.seriousness}</td>
												<td>${IssueVO.status}</td>
												<td>${IssueVO.adduser}</td>
												<td>${IssueVO.adddate}</td>
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
									href="list${pageMaker.makeIssueSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
								<!-- 왼쪽화살표 -->
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="list${pageMaker.makeIssueSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<!-- 오른쪽화살표 -->
								<li><a
									href="list${pageMaker.makeIssueSearch(pageMaker.endPage +1)}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>