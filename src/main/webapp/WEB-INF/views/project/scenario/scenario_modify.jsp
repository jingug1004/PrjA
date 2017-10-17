<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>Modify Test Case Scenario Information</title>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<%@ include file="/include/upload.jsp"%>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=1"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
<li style="list-style-type : none">
  <span class="mail-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mail-attachment-info">
	<a style='cursor:pointer' onclick=read("{{getLink}}","{{fullName}}") class="mailbox-attachment-name">{{fileName}}</a>
	<a style='cursor:pointer' onclick=del("{{fullName}}") class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
  </div>
</li>               
</script>
<script>
	var msg;
	var template = Handlebars.compile($("#template").html());
	var save = false;
	var delete_status = false;
	var readfile = "";

	function closePage() {
		if (save == false) {//수정 버튼을 클릭하지 않은상태에서 페이지가 재로드 된 경우
			if ($("#prefile").val() != "") { //파일을 추가한경우
				deleteFile($("#prefile").val(), event_path); //추가한 파일 삭제
			}
		}
	}

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") { //파일을 추가 한 경우

			var ext = file_value.slice(file_value.lastIndexOf(".") + 1)
					.toLowerCase();

			if (!(ext == "gif" || ext == "jpg" || ext == "png")) { //이미지 아닐경우 
				alert("이미지파일 (.jpg, .png, .gif ) 만 업로드 가능합니다.");
				$('#file').val("");
				return false;
			}

			$('#drop_string').hide();

			if ($('#fileadd').val() == 'true') { //이전 파일이 추가 되어있을경우 삭제
				deleteFile($("#prefile").val(), event_path); //이전파일 삭제
			} else if ($('#seq').val() != "") {
				if (readfile != "") {
					deleteFile(readfile, event_path);
				}
			}
			var file = event.files[0];

			var formData = new FormData();

			formData.append("file", file);
			formData.append("uploadPath", event_path);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					var fileInfo = getFileInfo(data, "event");
					var html = template(fileInfo);
					$(".uploadedList").html(html);
					$('#fileadd').val('true');
					$("#prefile").val(fileInfo.fullName);
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}

			});
		} else {
			if ($('#fileadd').val() == 'false') {
				$('#drop_string').show();
			}
		}
	}
	
	function del(fullName) {
		var seq = $('#seq').val();
		var tr = $("tr[name=" + seq + "]");

		if ($('#fileadd').val() == 'true') {
			deleteFile(fullName, event_path);
		} else {
			delete_status = true;
		}

		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
	}
	
	function read(fileLink) {

		location.href = fileLink;
	}

	function evt_read(seq, image) {
		$('#alert').html(""); //액션클릭시 선택해달라는 알림 메시지 삭제
		if ($('#fileadd').val() == 'true') { 
			deleteFile($("#prefile").val(), event_path);
		}

		$("#prefile").val("");
		var save = false;
		$('#fileadd').val() == 'false';
		var delete_status = false;

		$(".uploadedList").html("");
		$("#file").val("");
		var tr = $("tr[name=" + seq + "]");
		var evtdesc = tr.children().eq(5).text();
		$("#image").val(image);

		if (image != "") {
			$('#drop_string').hide();
			var fileInfo = getFileInfo(image, "event");
			var html = template(fileInfo);
			$(".uploadedList").html(html);
			readfile = image;
		}
		$('#seq').val(seq);
		$('#evtdesc').val(evtdesc);
	}

	$(document)
			.ready(
					function() {
						msg = '${resultmsg}';

						if ('${resultmsg}' != "") {
							msg = msg.replace(/다/g, "다\n");
							alert(msg);
							msg = msg.replace(/\n/g, "<br>");
							$('#alert').html(msg);
						}

						$(".fileDrop").on("dragenter dragover",
								function(event) {
									event.preventDefault();
								});

						$(".fileDrop")
								.on(
										"drop",
										function(event) {
											event.preventDefault();
											var files = event.originalEvent.dataTransfer.files;

											var file = files[0];

											var file_value = file.name;

											var ext = file_value
													.slice(
															file_value
																	.lastIndexOf(".") + 1)
													.toLowerCase();

											if (!(ext == "gif" || ext == "jpg" || ext == "png")) { //이미지 아닐경우 
												alert("이미지파일 (.jpg, .png, .gif ) 만 업로드 가능합니다.");
												$('#file').val("");
												return false;
											}

											$('#drop_string').hide();
											$('#file').val("");
											if ($('#fileadd').val() == 'true') { //파일이 추가 되어있을경우 삭제
												deleteFile($("#prefile").val(), event_path);
											} else if ($('#image').val() != "") {
												delete_status = true;
											}

											//console.log(file);

											var formData = new FormData();

											formData.append("file", file);
											formData.append("uploadPath",
													event_path);

											$
													.ajax({
														url : '/uploadAjax',
														data : formData,
														dataType : 'text',
														processData : false,
														contentType : false,
														type : 'POST',
														success : function(data) {
															var fileInfo = getFileInfo(
																	data,
																	"event");
															var html = template(fileInfo);
															$(".uploadedList")
																	.html(html);
															$('#fileadd').val('true');
															$("#prefile").val(fileInfo.fullName);
															$('#attach')
																	.val(
																			fileInfo.fullName);
														},
														error : function(
																request,
																status, error) {
															alert("code:"
																	+ request.status
																	+ "\n"
																	+ "message:"
																	+ request.responseText
																	+ "\n"
																	+ "error:"
																	+ error);
														}

													});
										});

						var formObj = $("form[role='form']");

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

						$("#modify")
								.click(
										function() {
											if ($('#seq').val() == "") {
												msg = "액션을 선택해주세요"
												alert(msg);
												$('#alert').html(msg);
											} else {
												if (delete_status == true) {
													deleteFile($("#image").val(), event_path);
													$("#image").val("");
													$('#drop_string').show();
												}
												if ($('#fileadd').val() == 'true') {
													$("#image").val($("#prefile").val());
												}
												formObj
														.attr("action",
																"/project/scenario/scenario_modify");
												formObj.attr("method", "post");
												formObj.submit();
												save = true;
											}
										});

						$("#cancel").click(function() {
							window.close();
						});
					});

	
</script>


</head>

<body style="margin-top: 15px;" onbeforeunload="closePage()">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding: 30px">
						<div class="row">
							<div class="col-lg-12">

								<form role="form" enctype="multipart/form-data">
									<span id="alert" style='color: red'></span> <input
										type="hidden" name="projid" value="${eventVO.projid}">
									<input type="hidden" name="senid" value="${eventVO.senid}">
									<input type="hidden" id="seq" name="seq"> <input type="hidden"
										id="image" name="image" > <input type="hidden"
										id="fileadd" value="false"><input type="hidden"
										id="prefile">
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">
											<input type="file" name="upload" id="file"
												onchange="fileUpload(this)">
										</div>

										<div id="fileDrop" class="fileDrop" style="padding: 0px;">
											<ul style="margin-top: 5px"
												class="mailbox-attachments clearfix uploadedList">
											</ul>
											<h1 id="drop_string"
												style="text-align: center; margin-top: 40px; color: darkgrey;">
												File DROP Here</h1>
										</div>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Description</span> <input
											type="text" class="form-control" name="evtdesc" id="evtdesc"
											value="${eventVO.evtdesc}">
									</div>

									<p style="text-align: center;">
										<button class="btn btn-default" type="button" id="modify">수정</button>
									</p>

									<div>
										<table
											class="table-layout:fixed table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th><input id="checkAll" name="checkAll"
														type="checkbox" /></th>
													<th
														style="vertical-align: middle; text-align: center; word-break: break-all; white-space: pre-line;">action</th>
													<th
														style="vertical-align: middle; text-align: center; word-break: break-all; white-space: pre-line;">coordinate</th>
													<th
														style="vertical-align: middle; text-align: center; word-break: break-all; white-space: pre-line;">Objectid</th>
													<th style="vertical-align: middle; text-align: center;">image</th>
													<th
														style="vertical-align: middle; text-align: center; word-break: break-all; white-space: pre-line;">desc</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${list}" var="EventVO">
													<tr id="tr" name="${EventVO.seq}" style="cursor: pointer"
														onclick="evt_read('${EventVO.seq}','${EventVO.image}')">
														<td style="word-break: break-all; white-space: pre-line;"><input
															id="check" name="check" type="checkbox"
															value="${EventVO.seq}"></td>
														<td style="word-break: break-all; white-space: pre-line;">${EventVO.evtact}</td>
														<td style="word-break: break-all; white-space: pre-line;">${EventVO.xy}</td>
														<td style="word-break: break-all; white-space: pre-line;">${EventVO.objid}</td>
														<td id='${EventVO.seq}'
															style="word-break: break-all; white-space: pre-line;"><c:if
																test="${EventVO.image ne ''}">O</c:if></td>
														<td style="word-break: break-all; white-space: pre-line;">${EventVO.evtdesc}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12" style="text-align: right; margin-right: 20px">
				<p>
					<button class="btn btn-default" type="button" id="cancel">닫기</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>