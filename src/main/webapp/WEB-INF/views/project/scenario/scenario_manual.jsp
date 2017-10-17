<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>

<html>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>Create Test Case Scenario( Manual )</title>

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
	{{fileName}}
	<a style='cursor:pointer' onclick=del("{{fullName}}") class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
  </div>
</li>               
</script>
<%
	int i = 0;
%>
<script>
	var i = 0;
	var ttt;
	var template = Handlebars.compile($("#template").html());
	var msg;
	var fileadd = false; //파일이 추가 되어있는 경우 true (파일이 추가 되어있는 상태에서 다시 파일을 추가했을 경우 그 전 파일 삭제하기 위해 선언된 변수)
	var prefile = ""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해)
	var addfile = ""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해)
	var save = false; //onbeforeunload로 폼값 전송시에도 이미지삭제가 발생하여 상태변수로 둠. 생성버튼 클릭시 true
	var modify = false;
	var delete_status = false;
	var read = false;

	function closePage() {
		if (save == false) {//수정 버튼을 클릭하지 않은상태에서 페이지가 재로드 된 경우
			if (prefile != "") { //파일을 추가한경우
				deleteFile(prefile, event_path); //추가한 파일 삭제
			}

			var tr = $('#tbody');
			var length = $('#event tbody tr').length;

			if (length > 0) {
				for (var i = 0; i < length; i++) {

					var path = tr.children().eq(i).children().eq(1).val();
					deleteFile(path, event_path);

				}

			}
		}
	}
	function del(fullName) {
		var seq = $('#seq').val();
		var tr = $("tr[name=" + seq + "]");
		var add = tr.children().eq(2).val();

		if (add == "add") {
			delete_status = true;
			addfile = fullName;
		} else {
			deleteFile(fullName, event_path);
		}

		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
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

			if (fileadd == true) { //이전 파일이 추가 되어있을경우 삭제
				deleteFile(prefile, event_path); //이전파일 삭제
			} else if ($('#seq').val() != "") {
				if (prefile != "") {
					deleteFile(prefile, event_path);
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
				deleteFile(prefile, event_path);
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

						ttt = '0';

						var event = {};
						var formObj = $("form[role='form']");
						var Sock;
						var myObject = new Object();
						var arr_val = new Array();
						var imagePath = "";

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
											if (fileadd == true) { //파일이 추가 되어있을경우 삭제
												deleteFile(prefile, event_path);
											} else if ($('#seq').val() != "") {
												if (prefile != "") {
													deleteFile(prefile,
															event_path);
												}
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

						$('#cancel').click(function() {
							window.close();
						});

						$('#add')
								.click(
										function() {
											if ($('#evtact').val() != "") {
												i++;
												$('#seq').val(i);
												$('#tbody')
														.append(
																"<tr id='tr' name='"
																		+ i
																		+ "' ><td></td><td></td><td></td></tr>");

												var evtact = $('#evtact').val();
												var evtdesc = $('#evtdesc')
														.val();

												var tr = $("tr[name=" + i + "]");
												tr.children().eq(0)
														.text(evtact);
								
												if (prefile != "") {
													tr.children().eq(2).text(
															evtdesc);
													tr.children().eq(1).text("O");
													tr.children().eq(1)
															.val(prefile);
													tr.children().eq(2).val("add");
													var formData = new FormData();

												}

												$('#evtact').val("");
												$('#evtdesc').val("");
												$(".uploadedList").html("");
												prefile = "";
												$('#seq').val("");
												$('#drop_string').show();
												delete_status = false;
											}
										});

						$('#ok').click(
								function() {
											var tr = $('#tbody');
											var length = $('#event tbody tr').length;

											if (length > 0) {
												for (var i = 0; i < length; i++) {
													event.evtact = tr
															.children().eq(i)
															.children().eq(0)
															.text();
													event.image = tr.children()
															.eq(i).children()
															.eq(1).val();
													event.evtdesc = tr
															.children().eq(i)
															.children().eq(2)
															.text();
													arr_val.push(JSON
															.stringify(event));
												}
												myObject.value = arr_val;
												var str = myObject.value
														.toString();
												str = str.replace(/,/g, '!');
												str = str
														.replace(/}!{/g, '}@{');
												$('input[name=arr]').val(str);
												formObj
														.attr("action",
																"/project/scenario/scenario_manual");
												formObj.attr("method", "post");
												ttt = '1';
												formObj.submit();
												save = true;
											} else {
												alert("시나리오를 생성해주세요.");
												$('#alert').html(
														"시나리오를 생성해주세요.");
											}
										});

					});

	/* function evt_read(seq) {
		$("#file").val("");
		var tr = $("tr[name=" + seq + "]");
		var evtact = tr.children().eq(0).text();
		var image = tr.children().eq(1).text();
		var evtdesc = tr.children().eq(2).text();
		var path = tr.children().eq(1).val();

		if (path != "") {
			$('#drop_string').hide();
			var fileInfo = getFileInfo(path, "event");
			var html = template(fileInfo);
			$(".uploadedList").html(html);
			prefile = path;
		}

		$('#seq').val(seq);
		$('#evtact').val(evtact);
		$('#evtdesc').val(evtdesc);
		delete_status = false;
	} */
</script>


</head>

<body style="margin-top: 15px;" onbeforeunload="closePage()">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding: 30px">
						<span id="alert" style='color: red'></span>
						<div class="row">
							<div class="col-lg-12">

								<form role="form">
									<input type="hidden" name="projid" value="${eventVO.projid}">
									<input type="hidden" name="senid" value="${eventVO.senid}">
									<input type="hidden" id="seq"> <input type="hidden"
										name="arr">
								</form>
								<div class="form-group input-group">
									<span class=input-group-addon>Action</span> <input type="text"
										class="form-control" id="evtact"><span
										id="limitingevtact" style="color: darkgray"></span>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading"
										style="padding-top: 6px; padding-bottom: 6px">
										<input type="file" name="upload" id="file"
											onchange="fileUpload(this)"><input type="hidden"
											id="attach" name="attach" value="${projectVO.attach}">
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
										type="text" class="form-control" name="evtdesc" id="evtdesc"><span
										id="limitingevtdesc" style="color: darkgray"></span>
								</div>
								<p style="text-align: center;">
									<button class="btn btn-default" type="button" id="add">추가</button>
								</p>
								<div>
									<table class="table table-bordered table-hover table-striped"
										id="event">
										<thead>
											<tr>
												<th style="vertical-align: middle; text-align: center;">action</th>
												<th style="vertical-align: middle; text-align: center;">image</th>
												<th style="vertical-align: middle; text-align: center;">desc</th>
											</tr>
										</thead>
										<tbody id="tbody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12" style="text-align: right; margin-right: 20px">
				<p>
					<button class="btn btn-default" type="button" id="ok">완료</button>
					<button class="btn btn-default" type="button" id="cancel">닫기</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>