<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">
<head>
<title>New Project</title>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
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
	{{fileName}}
	<a style='cursor:pointer' onclick=del("{{fullName}}") class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
  </div>
</li>               
</script>
<script>
	var template = Handlebars.compile($("#template").html());
	var msg;
	var fileadd = false; //파일이 추가 되어있는 경우 true (파일이 추가 되어있는 상태에서 다시 파일을 추가했을 경우 그 전 파일 삭제하기 위해 선언된 변수)
	var prefile = ""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해 선언된 변수)
	var save = false; //onbeforeunload로 폼값 전송시에도 이미지삭제가 발생하여 상태변수로 둠. 생성버튼 클릭시 true

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") {
			$('#drop_string').hide();
			if (fileadd == true) { //파일이 추가 되어있을경우 삭제
				$(".uploadedList").html("");
				deleteFile(prefile, project_path);
			}

			var file = event.files[0];
			var formData = new FormData();
			formData.append("file", file);
			formData.append("uploadPath", project_path);
			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					var fileInfo = getFileInfo(data, "project");
					var html = template(fileInfo);
					$(".uploadedList").append(html);
					fileadd = true;
					prefile = fileInfo.fullName;
					$('#attach').val(fileInfo.fullName);
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}

			});
		} else {
			if (fileadd == false) {
				$('#drop_string').show();
			}
		}
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

											$('#drop_string').hide();
											$('#file').val("");
											if (fileadd == true) { //파일이 추가 되어있을경우 삭제
												$(".uploadedList").html("");
												deleteFile(prefile,
														project_path);
											}

											var files = event.originalEvent.dataTransfer.files;

											var file = files[0];

											//console.log(file);

											var formData = new FormData();

											formData.append("file", file);
											formData.append("uploadPath",
													project_path);

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
																	"project");
															var html = template(fileInfo);
															$(".uploadedList")
																	.append(
																			html);
															fileadd = true;
															prefile = fileInfo.fullName;
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

						$('#charge_btn')
								.click(
										function() {
											var URL = "/project/charge_list";
											window
													.open(URL, "charge_list",
															"width=800, height=600, top=50,left=310,scrollbars=yes");

										});

						$('#paticipant_btn')
								.click(
										function() {
											var URL = "/project/paticipant_list";
											window
													.open(URL, "paticipant_list",
															"width=800, height=600, top=50,left=310,scrollbars=yes");

										});

						var formObj = $("form[role='form']");

						//생성버튼
						$('#ok').click(function() {
							formObj.attr("action", "/project/create");
							formObj.attr("method", "post");
							formObj.submit();
							save = true;
						});

						//취소하기 버튼
						$('#cancel').click(function() {
							window.close();
						});
					});

	//페이지가 재로드 될경우
	function closePage() {
		if (prefile != "") {
			if (save == false)
				deleteFile(prefile, project_path);
		}
	}

	function del(funllName) {
		deleteFile(prefile, project_path);
		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
	}
</script>


</head>

<body style="margin-top: 15px" onbeforeunload="closePage()">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding: 15px">
						<h3 class="panel-title">
							<i class="fa fa-fw fa-check"></i> New Project
						</h3>
					</div>
					<div class="panel-body" style="padding: 30px">
						<div class="row">
							<div class="col-lg-12">
								<form role="form" enctype="multipart/form-data">
									<span id="alert" style='color: red'></span>
									<div class="form-group input-group">
										<span class=input-group-addon>Project Title *</span><input
											type="text" class="form-control" id="name" name="name"
											value="${projectVO.name}"> <input type="hidden"
											name="projid" value="${projectVO.projid}"><span
											id="limitingname" style="color: darkgray"></span> <input
											type="hidden" name="attach" id="attach">
									</div>

									<div class="form-group input-group">
										<span class=input-group-addon>Person in charge *</span> <input
											type="text" class="form-control" id="charge" name="charge"
											value="<%=session.getAttribute("id")%>"><span
											id="limitinguser" style="color: darkgray"></span><span
											class="input-group-btn">
											<button class="btn btn-default" type="button" id="charge_btn">찾기</button>
										</span>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group input-group">
												<span class="input-group-addon">Start *</span> <span
													class="demo-1"> <input type="text"
													class="form-control" name="fromdate"
													value="${projectVO.fromdate}">
												</span>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group input-group">
												<span class=input-group-addon>End</span><span class="demo-2">
													<input type="text" class="form-control" name="todate"
													value="${projectVO.todate}">
												</span>
											</div>
										</div>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Preparation</span><span
											class="demo-3"> <input type="text"
											class="form-control" disabled></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Status</span> <select
											class="form-control" name="status">
											<option value='0'>Ongoing</option>
											<option value='1'>Hold</option>
											<option value='2'>End</option>
										</select>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Project ID</span> <input
											type="text" class="form-control" name="abbr" id="abbr"
											value="${projectVO.abbr}"><span id="limitingabbr"
											style="color: darkgray"></span>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">Project
											description</div>
										<div class="panel-body" style="padding: 0px;">
											<textarea class="form-control" rows="6" name='desc' id='desc'></textarea>
											<span id="limitingdesc" style="color: darkgray"></span>
										</div>
									</div>

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
										<span class=input-group-addon>paticipant</span> <input
											type="text" class="form-control" name="paticipant"
											id="paticipant" value="${projectVO.name}"><span
											class="input-group-btn">
											<button class="btn btn-default" type="button"
												id="paticipant_btn" value="false">찾기</button>
										</span>
										<!-- name,value 값 임시 -->
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<p style="float: right">
					<button class="btn btn-default" type="submit" id="ok">확인</button>
					<button class="btn btn-default" type="button" id="cancel">취소
					</button>
				</p>
			</div>
		</div>
	</div>
	<script src="/js/jquery.minical.js"></script>
	<script>
		$("span.demo-1 :text").minical();
		$("span.demo-2 :text").minical();
		$("span.demo-3 :text").minical();
	</script>
</body>
</html>