<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%@ include file="/include/upload.jsp"%>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=1"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
<li style="list-style-type : none">
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a style='cursor:pointer' onclick=read("{{getLink}}","{{fullName}}") class="mailbox-attachment-name">{{fileName}}</a>
	<a style='cursor:pointer' onclick=del("{{fullName}}") 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>
<script>
	var template = Handlebars.compile($("#template").html());
	var msg;
	var delete_status = false; //디비에 저장되어있는 파일이 
	var attach = '${issueVO.attach}';
	var fileadd = false; //파일이 추가 되어있는 경우 true (파일이 추가 되어있는 상태에서 다시 파일을 추가했을 경우 그 전 파일 삭제하기 위해 선언된 변수)
	var prefile=""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해)
	var save = false; //onbeforeunload로 폼값 전송시에도 이미지삭제가 발생하여 상태변수로 둠. 생성버튼 클릭시 true

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") { //파일을 추가 한 경우
			$('#drop_string').hide();
			$(".uploadedList").html("");

			if (fileadd == true) { //이전 파일이 추가 되어있을경우 삭제
				deleteFile(prefile, issue_path); //이전파일 삭제
			}
			if (attach != "") { //db에 먼저 저장되어있는 파일이 있는 경우
				delete_status = true;
			}

			var file = event.files[0];

			var formData = new FormData();

			formData.append("file", file);
			formData.append("uploadPath", issue_path);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					var fileInfo = getFileInfo(data, "issue");
					var html = template(fileInfo);
					$(".uploadedList").append(html);
					fileadd = true;
					prefile = fileInfo.fullName;
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

	function closePage() {
		if (save == false) {//수정 버튼을 클릭하지 않은상태에서 페이지가 재로드 된 경우
			if (prefile != "") { //파일을 추가한경우(DB와는 다른 파일)
				deleteFile(prefile, issue_path); //추가한 파일 삭제
			}
		}
	}

	function del(fullName) {
		if (fullName == attach) {
			delete_status = true; //디비에있는 항목 삭제했을경우(수정 버튼 눌렀을 경우에만 삭제해야함)
		} else { //디비에 있는 파일 말고 다른 파일 del 버튼 클릭시
			deleteFile(prefile, issue_path);
		}

		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
	}

	function read(fileLink) {

		location.href = fileLink;
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
						var formObj = $("form[role='form']");

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
														issue_path);
											}
											if (attach != "") { //db에 먼저 저장되어있는 파일이 있는 경우
												delete_status = true;
											}

											var files = event.originalEvent.dataTransfer.files;

											var file = files[0];

											//console.log(file);

											var formData = new FormData();

											formData.append("file", file);
											formData.append("uploadPath",
													issue_path);

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
																	"issue");
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

						if (attach != "") {
							var fileInfo = getFileInfo(attach, "issue");
							var html = template(fileInfo);
							$(".uploadedList").append(html);
							$('#drop_string').hide();
						}

						//생성버튼
						$('#ok').click(function() {
							save = true;

							//기존파일 삭제상태 일 경우 서버 파일 삭제
							if (delete_status == true) {
								deleteFile(attach, issue_path);
								$('#attach').val("");

							}
							if (prefile != "") { //삭제하고 새로이 추가한 파일이 있는 경우
								$('#attach').val(prefile);
							}
							formObj.attr("action", "/project/issue/modify");
							formObj.attr("method", "post");
							formObj.submit();
						});

						//취소하기 버튼
						$('#cancel').click(function() {
							window.close();
						});

						$(".uploadedList").on("click", ".delbtn",
								function(event) {

									event.preventDefault();

									var that = $(this);

									that.closest("li").remove();
									$('#file_data').val("nulldata");
									$('#file').val("");
								});

						if ($('#category').val() == 2) {
							$('#seriousness').show();
							$('#status').show();
						} else {
							$('#seriousness').hide();
							$('#status').hide();
						}

						$('#category').change(function() {
							if ($('#category').val() == 2) {
								$('#seriousness').show();
								$('#status').show();
							} else {
								$('#seriousness').hide();
								$('#status').hide();
							}
						});

					});
</script>


</head>

<body style="margin-top: 15px;">


	<div class="container-fluid">





		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding: 15px;">
						<h3 class="panel-title">
							<i class="fa fa-fw fa-check"></i> Note Modify
						</h3>
					</div>
					<div class="panel-body" style="padding: 30px">
						<div class="row">
							<div class="col-lg-12">

								<form role="form" enctype="multipart/form-data">
								<span id= "alert" style='color:red'></span>
									<div class="form-group input-group">
										<span class=input-group-addon>Project Title</span> <input
											type="hidden" name="projid" id="projid"
											value="${IssueVO.projid}"> <input type="text"
											class="form-control" name="proj_name"
											value="${IssueVO.proj_name}" id="proj_name" disabled>
										<input type="hidden" id="file_data" name="file_data">
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Phase Title</span> <input
											type="hidden" name="phid" id="phid" value="${IssueVO.phid}">
										<input type="text" class="form-control" name="ph_name"
											id="ph_name" value="${IssueVO.ph_name}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Note ID</span> <input
											type="text" class="form-control" value="${IssueVO.seq}"
											disabled><input type="hidden" name="seq" id="seq"
											value="${IssueVO.seq}">
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Note Title</span> <input
											type="text" class="form-control" id ="name" name="isuname"
											value="${IssueVO.isuname}"><span id="limitingname"
											style="color: darkgray"></span>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">Note
											Description</div>
										<div class="panel-body" style="padding: 0px;">
											<textarea class="form-control" rows="6" id="desc" name='isudesc'>${IssueVO.isudesc}</textarea><span id="limitingdesc"
											style="color: darkgray"></span>
										</div>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Registerer</span> <input
											type="text" class="form-control" name="adduser"
											value="${IssueVO.adduser}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Registered Date</span> <input
											type="text" class="form-control" name="adddate"
											value="${IssueVO.adddate}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Modification</span> <input
											type="text" class="form-control" name="upddate"
											value="${IssueVO.upddate}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Note Category</span> <select
											class="form-control" name="category" id="category">
											<option value='0'
												<c:out value="${IssueVO.category eq 'note' ? 'selected':''}"/>>note</option>
											<option value='1'
												<c:out value="${IssueVO.category eq 'issue' ? 'selected':''}"/>>issue</option>
											<option value='2'
												<c:out value="${IssueVO.category eq 'defect' ? 'selected':''}"/>>defect</option>
										</select>
									</div>
									<div class="form-group input-group" id="seriousness">
										<span class=input-group-addon>Defect Seriousness</span> <select
											class="form-control" name="seriousness">
											<option value='0'
												<c:out value="${IssueVO.seriousness eq 'Critical' ? 'selected':''}"/>>Critical</option>
											<option value='1'
												<c:out value="${IssueVO.seriousness eq 'Major' ? 'selected':''}"/>>Major</option>
											<option value='2'
												<c:out value="${IssueVO.seriousness eq 'Minor' ? 'selected':''}"/>>Minor</option>
										</select>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">Test
											Procedure</div>
										<div class="panel-body" style="padding: 0px;">
											<textarea class="form-control" rows="6" name='procedure' id='procedure'>${IssueVO.procedure}</textarea><span id="limitingprocedure"
											style="color: darkgray"></span>
										</div>
									</div>
									<div class="form-group input-group" id="status">
										<span class=input-group-addon>Defect Status</span> <select
											class="form-control" name="status">
											<option value='0'
												<c:out value="${IssueVO.status eq 'New' ? 'select21ed':''}"/>>New</option>
											<option value='1'
												<c:out value="${IssueVO.status eq 'Open' ? 'selected':''}"/>>Open</option>
											<option value='2'
												<c:out value="${IssueVO.status eq 'Assigned' ? 'selected':''}"/>>Assigned</option>
											<option value='3'
												<c:out value="${IssueVO.status eq 'Resolved' ? 'selected':''}"/>>Resolved</option>
											<option value='4'
												<c:out value="${IssueVO.status eq 'Closed' ? 'selected':''}"/>>Closed</option>
											<option value='5'
												<c:out value="${IssueVO.status eq 'Reopened' ? 'selected':''}"/>>Reopened</option>
										</select>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">
											<input type="file" name="upload" id="file"
												onchange="fileUpload(this)"><input type="hidden"
												id="attach" name="attach" value="${IssueVO.attach}">
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
					<button class="btn btn-default" type="button" id="cancel">취소</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>