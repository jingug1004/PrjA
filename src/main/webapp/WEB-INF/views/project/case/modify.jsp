<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>View or Modify Test Case</title>

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
	var attach = '${caseVO.attach}';
	var fileadd = false; //파일이 추가 되어있는 경우 true (파일이 추가 되어있는 상태에서 다시 파일을 추가했을 경우 그 전 파일 삭제하기 위해 선언된 변수)
	var prefile = ""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해)
	var save = false; //onbeforeunload로 폼값 전송시에도 이미지삭제가 발생하여 상태변수로 둠. 생성버튼 클릭시 true

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") { //파일을 추가 한 경우
			$('#drop_string').hide();
			$(".uploadedList").html("");

			if (fileadd == true) { //이전 파일이 추가 되어있을경우 삭제
				deleteFile(prefile, case_path); //이전파일 삭제
			}
			if (attach != "") { //db에 먼저 저장되어있는 파일이 있는 경우
				delete_status = true;
			}

			var file = event.files[0];

			var formData = new FormData();

			formData.append("file", file);
			formData.append("uploadPath", case_path);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					var fileInfo = getFileInfo(data, "case");
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
				deleteFile(prefile, case_path); //추가한 파일 삭제
			}
		}
	}

	function del(fullName) {
		if (fullName == attach) {
			delete_status = true; //디비에있는 항목 삭제했을경우(수정 버튼 눌렀을 경우에만 삭제해야함)
		} else { //디비에 있는 파일 말고 다른 파일 del 버튼 클릭시
			deleteFile(prefile, case_path);
		}

		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
	}

	function read(fileLink) {

		location.href = fileLink;
	}

	function popupdev() {
		var url = "/device/devpop";
		var name = "devicelist";

		window
				.open(
						url,
						name,
						"width=1000 , height=700, toolbar=no, status=no, scrollbars=auto, menubar=no, resizeable=yes");
	}
	function popupapp() {
		var url = "/app/apppop";
		var name = "applist";

		window
				.open(
						url,
						name,
						"width=1000 , height=700, toolbar=no, status=no, scrollbars=auto, menubar=no, resizeable=yes");
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
												deleteFile(prefile, case_path);
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
													case_path);

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
																	"case");
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
							var fileInfo = getFileInfo(attach, "case");
							var html = template(fileInfo);
							$(".uploadedList").append(html);
							$('#drop_string').hide();
						}

						var formObj = $("form[role='form']");

						//생성버튼
						$('#ok').click(function() {
							save = true;

							//기존파일 삭제상태 일 경우 서버 파일 삭제
							if (delete_status == true) {
								deleteFile(attach, case_path);
								$('#attach').val("");

							}
							if (prefile != "") { //삭제하고 새로이 추가한 파일이 있는 경우
								$('#attach').val(prefile);
							}
							formObj.attr("action", "/project/case/modify");
							formObj.attr("method", "post");
							formObj.submit();
						});

						//카피버튼
						$('#copy_ok')
								.click(
										function() {
											$('#insert').val("copy");
											var copy = confirm("Test Case Scenario 도 함께 복사하시겠습니까? \n ( Do you want to copy Test Case Scenario too? )");
											if (copy == true) {
												$('#copy').val("ok");
												formObj.attr("action",
														"/project/case/create");
												formObj.attr("method", "post");
												formObj.submit();
											} else if (copy == false) {
												formObj.attr("action",
														"/project/case/create");
												formObj.attr("method", "post");
												formObj.submit();
											}
										});

						//생성버튼
						$('#s_create')
								.click(
										function() {
											var check = $("input:radio[name=s_category]:checked").val();
											if ($('#app_name').val() == "") {
												alert("App을 선택해주세요");
											} else if ($('#dev_name').val() == "") {
												alert("Device를 선택해주세요");
											} else {
												if (check == '1') {
													var URL = "/project/scenario/scenario_manual?projid="
															+ ${caseVO.projid}+"&senid=" + ${caseVO.senid};
													window.open(URL,"create_popup","width=800, height=600, top=50,left=310,scrollbars=yes");
												} else {
													var URL = "/project/scenario/scenario_auto?projid="
															+ ${caseVO.projid}+"&senid=" + ${caseVO.senid}+"&devid=" + ${caseVO.devid}
													+ "&appid=" + ${caseVO.appid};
													window.open(URL,"create_popup","width=800, height=600, top=50,left=310,scrollbars=yes");
												}
											}
										});

						//시나리오 보기
						$('#s_read')
								.click(
										function() {
											var URL = "/project/scenario/scenario_modify?projid="
													+ ${caseVO.projid}+"&senid=" + ${caseVO.senid};
											window.open(URL, "scenario_pop",
															"width=800, height=600, top=50,left=310,scrollbars=yes");
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
									$('#file').val("");
								});

					});
</script>


</head>

<body style="margin-top: 15px;">


	<div class="container-fluid">





		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding: 30px">
						<div class="row">
							<div class="col-lg-12">

								<form role="form" enctype="multipart/form-data">
									<span id="alert" style='color: red'></span> <input
										type="hidden" name="phid" value="${caseVO.phid}"> <input
										type="hidden" name="projid" value="${caseVO.projid}">
									<input type="hidden" name="senid" value="${caseVO.senid}">
									<input type="hidden" name="copy" id="copy"> <input
										type="hidden" name="insert" id="insert">

									<div class="form-group input-group">
										<span class=input-group-addon>Project Title</span> <input
											type="text" class="form-control" id="name" name="proj_name"
											value="${caseVO.proj_name}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Phase Title</span> <input
											type="text" class="form-control" id="name" name="ph_name"
											value="${caseVO.ph_name}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Test Case abbr *</span> <input
											type="text" class="form-control" name="senaabbr"
											id="senaabbr" value="${caseVO.senaabbr}"><span
											id="limitingsenaabbr" style="color: darkgray"></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Test Case Title</span> <input
											type="text" class="form-control" name="senaname" id="name"
											value="${caseVO.senaname}"><span id="limitingname"
											style="color: darkgray"></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Registerer</span> <input
											type="text" class="form-control"
											value="<%=session.getAttribute("id")%>" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Registered Date</span><input
											type="text" class="form-control" name="adddate" disabled
											value="${caseVO.adddate}">
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Modification</span> <input
											type="text" class="form-control" name="upddate"
											value="${caseVO.upddate}" disabled>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Test Case Category *</span> <label
											class="radio-inline" style="margin-left: 5px;"> <input
											type="radio" name="s_category" id="s_category" value="0" 
											<c:out value="${caseVO.s_category eq 'Automation Test' ? 'checked':''}"/>>
											Automation Test
										</label> <label class="radio-inline"> <input type="radio"
											name="s_category" id="s_category" value="1"
											<c:out value="${caseVO.s_category eq 'Resource Monitoring' ? 'checked':''}"/>
											>Resource Monitoring
										</label> <label class="radio-inline"> <input type="radio"
											name="s_category" id="s_category" value="2"
											<c:out value="${caseVO.s_category eq 'AutoTest + Monitoring' ? 'checked':''}"/>
											>AutoTest & Monitoring
										</label>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">Case
											Description</div>
										<div class="panel-body" style="padding: 0px;">
											<textarea class="form-control" rows="6" id='senadesc'
												name='senadesc'>${caseVO.senadesc}</textarea>
											<span id="limitingsenadesc" style="color: darkgray"></span>
										</div>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Device</span> <input type="text"
											class="form-control" id="dev_name" name="dev_name" readonly
											value="${caseVO.dev_name}"><input type="hidden"
											id="devid" name="devid" value="${caseVO.devid}"> <span
											class="input-group-btn"><button
												class="btn btn-default" type="button" onclick="popupdev()">
												검색<i class="fa fa-search"></i>
											</button></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>App</span> <input type="text"
											class="form-control" id="app_name" name="app_name" readonly
											value="${caseVO.app_name}"><input type="hidden"
											id="appid" name="appid" value="${caseVO.appid}"> <span
											class="input-group-btn"><button
												class="btn btn-default" type="button" onclick="popupapp()">
												검색<i class="fa fa-search"></i>
											</button></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Expected Result</span> <input
											type="text" class="form-control" id="expect"
											value="${caseVO.expect}"><span id="limitingexpect"
											style="color: darkgray"></span>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">
											<input type="file" name="upload" id="file"
												onchange="fileUpload(this)"><input type="hidden"
												id="attach" name="attach" value="${caseVO.attach}">
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
			<div class="col-lg-12" style="text-align: center;">
				<p>
					<button class="btn btn-default" type="submit" id="ok">확인</button>
					<button class="btn btn-default" type="submit" id="copy_ok">새로운
						Case로</button>
					<button class="btn btn-default" type="button" id="cancel">취소</button>
				</p>
				<p>
					<button class="btn btn-default" type="submit" id="s_create">Scenario
						새로 생성</button>
					<button class="btn btn-default" type="button" id="s_read">Scenario
						보기</button>
				</p>
			</div>
		</div>

	</div>
</body>
</html>