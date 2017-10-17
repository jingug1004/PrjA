<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>

<script>
	var msg;

	$(document).ready(function() {
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
			var val = $('[name = "keyword"]').val();
			if(val == "")
				alert("검색어를 입력해 주세요");
			formObj.attr("action", "/user/list");
			formObj.submit();
		});

		$('#new').click(function() {
			var URL = "/user/regist";
			window.open(URL, "popup", "width=800, height=520, scrollbars=yes");
		});

		$('#delete').click(function() {
			if (confirm("정말 삭제하시겠습니까?") == true) {
				formObj.attr("action", "/user/deleteUser");
				formObj.attr("method", "post");
				formObj.submit();
			}
		});

		$('#checkAll').click(function() {
			if ($(this).is(':checked')) {
				$("input[name=check]").prop("checked", true);
			} else {
				$("input[name=check]").prop("checked", false);
			}

		});

		$('#check').click(function() {
			$("input[name=checkAll]").prop("checked", false);
		});

	});

	function read(id) {
		var URL = "/user/modify?id=" + id;
		window.open(URL, "popup", "width=800, height=520, scrollbars=yes");
	};
</script>

</head>
<body>

	<div id="wrapper">
		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->

				<div class="row">
					<div class="col-lg-12" style="margin-bottom: 0px;">
						<h2 class="page-header" style="font-weight: bold;">User
							Management</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 " style="margin-bottom: 20px;">
						<h3 style="margin-bottom: 20px;">Users Summary</h3>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Total</h3>
							<b style="font-size: 50px">${total}</b> users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Admin</h3>
							<b style="font-size: 50px">${admin}</b> users

						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Test Enginner</h3>
							<b style="font-size: 50px">${testenginner}</b> users
						</div>
						<div class="col-lg-3"
							style="text-align: center; border: 1px solid #eee;">
							<h3>Reviewer</h3>
							<b style="font-size: 50px">${reviewer}</b> users
						</div>

					</div>
				</div>

							
				<form role="form" name="sear">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group input-group">
								<span class=input-group-addon>사용자 ID</span> <input type="text"
									class="form-control" name="keyword" value="${lc.keyword}"
									maxlength="20"><span class="input-group-btn"><button
										class="btn btn-default" type="button" id="search">
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
											<div class="col-lg-12" style="padding-top: 10px">
												<i class="fa fa-fw fa-check"></i> User 목록
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
													<th>ID</th>
													<th>Name</th>
													<th>Role</th>
													<th>Client IP Addr</th>
													<th>Tel</th>
													<th>Email</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="userVO">
													<tr>
														<td><input name="check" type="checkbox" id="check"
															value="${userVO.id}" /></td>
														<td><a id="read" href="#"
															onclick="read('${userVO.id}')">${userVO.id}</a></td>
														<td>${userVO.name}</td>
														<td>${userVO.role}</td>
														<td>${userVO.clntid}</td>
														<td>${userVO.telno}</td>
														<td>${userVO.email}</td>
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
											href="list${pageMaker.makeUserSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
										<!-- 왼쪽화살표 -->
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makeUserSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<!-- 오른쪽화살표 -->
										<li><a
											href="list${pageMaker.makeUserSearch(pageMaker.endPage +1)}">&raquo;</a></li>
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