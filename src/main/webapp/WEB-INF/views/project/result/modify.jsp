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
<script src="/js/FileSaver.js"></script>
<script src="/js/jquery.wordexport.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
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
	var attach = '${resultVO.attach}';
	var fileadd = false; //파일이 추가 되어있는 경우 true (파일이 추가 되어있는 상태에서 다시 파일을 추가했을 경우 그 전 파일 삭제하기 위해 선언된 변수)
	var prefile = ""; //파일을 업로드 한 이후 다시 파일을 업로드 한 경우 그 전의 파일 이름을 저장하는 변수 (파일 삭제를 위해)
	var save = false; //onbeforeunload로 폼값 전송시에도 이미지삭제가 발생하여 상태변수로 둠. 생성버튼 클릭시 true

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") { //파일을 추가 한 경우
			$('#drop_string').hide();
			$(".uploadedList").html("");

			if (fileadd == true) { //이전 파일이 추가 되어있을경우 삭제
				deleteFile(prefile, result_path); //이전파일 삭제
			}
			if (attach != "") { //db에 먼저 저장되어있는 파일이 있는 경우
				delete_status = true;
			}

			var file = event.files[0];

			var formData = new FormData();

			formData.append("file", file);
			formData.append("uploadPath", result_path);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					var fileInfo = getFileInfo(data, "result");
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
				deleteFile(prefile, result_path); //추가한 파일 삭제
			}
		}
	}

	function del(fullName) {
		if (fullName == attach) {
			delete_status = true; //디비에있는 항목 삭제했을경우(수정 버튼 눌렀을 경우에만 삭제해야함)
		} else { //디비에 있는 파일 말고 다른 파일 del 버튼 클릭시
			deleteFile(prefile, result_path);
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
						var replay;
						var obj;

						var evtlength = '${eventlength}';
						var repeatnum = '${repeatnum}';
						var projid = '${resultVO.projid}';
						var senid = '${resultVO.senid}';
						var resid = '${resultVO.resid}';
						if (repeatnum > 0) {
							for (var i = 0; i < repeatnum; i++) {
								$('#tbody')
										.append(
												'<tr name="'+i+'">'
														+ '<th style="vertical-align: middle; text-align: center;">RepeatCount'
														+ (i + 1) + '</th>'
														+ '</tr>');
								createtd(i, projid, senid, resid);
							}
						} else {
							$("#event").hide();
						}

						$("a.word-export").click(function(event) {
							$("#export-content").wordExport();
						});

						if ('${json}' == "true") {
							googleChart();
						} else {
							$('#div_resource').hide();
						}

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
												deleteFile(prefile, result_path);
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
													result_path);

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
																	"result");
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
							var fileInfo = getFileInfo(attach, "result");
							var html = template(fileInfo);
							$(".uploadedList").append(html);
							$('#drop_string').hide();
						}

						var formObj = $("form[role='form']");
						var result = $('#result').val();
						if (result != '1') {
							$('#span').hide();
						} else {
							$('#span').show();
						}

						$('#modify').click(function() {
							save = true;

							//기존파일 삭제상태 일 경우 서버 파일 삭제
							if (delete_status == true) {
								deleteFile(attach, result_path);
								$('#attach').val("");

							}
							if (prefile != "") { //삭제하고 새로이 추가한 파일이 있는 경우
								$('#attach').val(prefile);
							}

							result = $('#result').val();
							if ($('#span').hide()) {
								$('#seriousness').val("");
								$('#procedure').text("");
								$('#reason').val("");
								$('#status').val("");
							}
							formObj.attr("action", "/project/result/modify");
							formObj.attr("method", "post");
							formObj.submit();
						});

						//취소하기 버튼
						$('#cancel').click(function() {
							window.close();
						});

						$('#result').change(function() {
							result = $('#result').val();
							if (result == '1') {
								$('#span').show();
							} else {
								$('#span').hide();
							}

						});

						$(".uploadedList").on("click", ".delbtn",
								function(event) {

									event.preventDefault();

									var that = $(this);

									that.closest("li").remove();
									$('#file_data').val("nulldata");
									$('#file').val("");
								});
						
						 $('#cpu_chk').change(function() {
							 if($('#cpu_chk').is(':checked')){
								 $('#c').show();
							 }else{
								 $('#c').hide();
							 }
						 });
						 $('#memory_chk').change(function() {
							 if($('#memory_chk').is(':checked')){
								 $('#m').show();
							 }else{
								 $('#m').hide();
							 }
						 });
						 $('#battery_chk').change(function() {
							 if($('#battery_chk').is(':checked')){
								 $('#b').show();
							 }else{
								 $('#b').hide();
							 }
						 });
						 $('#network_chk').change(function() {
							 if($('#network_chk').is(':checked')){
								 $('#n').show();
							 }else{
								 $('#n').hide();
							 }
						 });

					});

	function createtd(i, projid, senid, resid) {
		$.ajax({
			url : '/project/result/table',
			data : {
				projid : projid,
				senid : senid,
				resid : resid,
				repeatnum : i + 1,
			},
			type : 'POST',
			success : function(data) {
				var arr = new Array();
				arr = data;
				//var arr = data.split(',');
				for ( var ar in arr) {
					$('[name="' + i + '"]').append("<td>" + arr[ar] + "</td>");
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}

		});
	}

	function googleChart() {
		if ('${c_json}' != "" || '${b_json}' != "" || '${m_json}' != ""
				|| '${n_json}' != "") {
			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});

			var memory_max = '${memory_max}';

			google.charts.setOnLoadCallback(drawChart);

			function drawChart() {

				var data = new google.visualization.DataTable('${c_json}');
				var options = {
					title : 'CPU(%)',
					vAxis : {
						viewWindowMode : 'explicit',
						viewWindow : {
							min : 0,
							max : 100,
						},
						ticks : [ 0, 25, 50, 75, 100 ],
					},
					hAxis : {
						gridlines: {
							count : 3,
						}
					},
					curveType : 'function',
					legend : {
						position : 'bottom'
					}
				};
				var chart = new google.visualization.LineChart(document
						.getElementById('chart_c'));

				chart.draw(data, options);

				var data_a = new google.visualization.DataTable('${a_json}');
				var options_a = {
					title : 'Action',
					curveType : 'function',
					tooltip : {
						isHtml : true
					},
					legend : {
						position : 'none'
					},
					vAxis : {
						textPosition : 'none'
					},
					
				};
				var chart_a = new google.visualization.ScatterChart(document
						.getElementById('chart_a'));

				chart_a.draw(data_a, options_a);

				var data_m = new google.visualization.DataTable('${m_json}');
				var options_m = {
					title : 'Memory',
					curveType : 'function',
					vAxis : {
						viewWindowMode : 'explicit',
						viewWindow : {
							min : 0,
							max : memory_max
						},
						format : 'short'
					},
					legend : {
						position : 'bottom'
					}
				};
				var chart_m = new google.visualization.LineChart(document
						.getElementById('chart_m'));

				chart_m.draw(data_m, options_m);

				var data_b = new google.visualization.DataTable('${b_json}');
				var options_b = {
					title : 'Battery(%)',
					vAxis : {
						viewWindowMode : 'explicit',
						viewWindow : {
							min : 0,
							max : 100,
						},
						ticks : [ 0, 25, 50, 75, 100 ],
					},
					curveType : 'function',
					legend : {
						position : 'bottom'
					}
				};
				var chart_b = new google.visualization.LineChart(document
						.getElementById('chart_b'));

				chart_b.draw(data_b, options_b);

				var data_n = new google.visualization.DataTable('${n_json}');
				var options_n = {
					title : 'Network',
					curveType : 'function',
					vAxis : {
						viewWindowMode : 'explicit',
						viewWindow : {
							min : 0
						},
						format : 'short'
					},
					legend : {
						position : 'bottom'
					}
				};
				var chart_n = new google.visualization.LineChart(document
						.getElementById('chart_n'));

				chart_n.draw(data_n, options_n);
			}
		}
	}
</script>


</head>

<body style="margin-top: 15px;">
	<div id="export-content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading" style="padding: 15px;">
							<h3 class="panel-title">
								<i class="fa fa-fw fa-check"></i> Detail View or Modify Test
								Case Result
							</h3>
						</div>
						<div class="panel-body" style="padding: 30px">
							<span id="alert" style='color: red'></span>
							<div class="row">
								<div class="col-lg-12">

									<form role="form" enctype="multipart/form-data">
										<input type="hidden" name="projid" value="${resultVO.projid}">
										<input type="hidden" name="phid" value="${resultVO.phid}">
										<input type="hidden" name="senid" value="${resultVO.senid}">
										<input type="hidden" name="resid" value="${resultVO.resid}">
										<input type="hidden" id="file_data" name="file_data">
										<div class="form-group input-group">
											<span class=input-group-addon>Project Title</span> <input
												type="text" class="form-control" name="proj_name"
												value="${resultVO.proj_name}" readonly>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Phase Title</span> <input
												type="text" class="form-control" name="ph_name"
												value="${resultVO.ph_name}" readonly>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Case Title</span> <input
												type="text" class="form-control" name="s_name"
												value="${resultVO.s_name}" readonly>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Result Title</span> <input
												type="text" class="form-control" id="name" name="resname"
												value="${resultVO.resname}"><span id="limitingname"
												style="color: darkgray"></span>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Registerer</span> <input
												type="text" style="right: 5px;" class="form-control"
												name="user" value="${resultVO.adduser}" readonly> <span
												class=input-group-addon>Registered Date</span> <input
												type="text" class="form-control" name="adddate"
												value="${resultVO.adddate}" readonly>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Device</span> <input
												type="text" style="right: 5px;" class="form-control"
												name="device" value="${resultVO.dev_name}" readonly>
											<span class=input-group-addon>App</span> <input type="text"
												class="form-control" name="app" value="${resultVO.app_name}"
												readonly>
										</div>

										<div class="form-group input-group">
											<span class=input-group-addon>Monitoring Time</span> <input
												type="text" class="form-control" name="starttime"
												value="${resultVO.starttime}" readonly> <span
												class=input-group-addon>~</span> <input type="text"
												class="form-control" name="endtime"
												value="${resultVO.endtime}" readonly>
										</div>
										<div class="form-group input-group">
											<span class=input-group-addon>Result</span> <select
												class="form-control" name="result" id="result">
												<option value='0'
													<c:out value="${resultVO.result eq '0' ? 'selected':''}"/>>Pass</option>
												<option value='1'
													<c:out value="${resultVO.result eq '1' ? 'selected':''}"/>>Fail</option>
												<option value='2'
													<c:out value="${resultVO.result eq '2' ? 'selected':''}"/>>N/A</option>
												<option value='3'
													<c:out value="${resultVO.result eq '3' ? 'selected':''}"/>>N/I</option>
											</select>
										</div>
										<span id="span">
											<div class='form-group input-group'>
												<span class=input-group-addon>Error Status Result
													Seriousness</span> <select class='form-control' name='seriousness'>
													<option value='0'
														<c:out value="${resultVO.seriousness eq '0' ? 'selected':''}"/>>Critical</option>
													<option value='1'
														<c:out value="${resultVO.seriousness eq '1' ? 'selected':''}"/>>Major</option>
													<option value='2'
														<c:out value="${resultVO.seriousness eq '2' ? 'selected':''}"/>>Minor</option>
												</select>
											</div>
											<div class='panel panel-default'>
												<div class='panel-heading'
													style='padding-top: 6px; padding-bottom: 6px'>Test
													Procedure</div>
												<div class='panel-body' style='padding: 0px;'>
													<textarea class='form-control' rows='6' id='procedure'
														name='procedure'>${resultVO.procedure}</textarea>
													<span id="limitingprocedure" style="color: darkgray"></span>
												</div>
											</div>
											<div class='form-group input-group'>
												<span class=input-group-addon>Reason</span> <input
													type='text' class='form-control' id='reason' name='reason'
													value="${resultVO.reason}"><span
													id="limitingreason" style="color: darkgray"></span>
											</div>
											<div class='form-group input-group'>
												<span class=input-group-addon>Result Status</span> <select
													class='form-control' name='status'>
													<option value='0'
														<c:out value="${resultVO.status eq '0' ? 'selected':''}"/>>New</option>
													<option value='1'
														<c:out value="${resultVO.status eq '1' ? 'selected':''}"/>>Open</option>
													<option value='2'
														<c:out value="${resultVO.status eq '2' ? 'selected':''}"/>>Assigned</option>
													<option value='3'
														<c:out value="${resultVO.status eq '3' ? 'selected':''}"/>>Resolved</option>
													<option value='4'
														<c:out value="${resultVO.status eq '4' ? 'selected':''}"/>>Closed</option>
													<option value='5'
														<c:out value="${resultVO.status eq '5' ? 'selected':''}"/>>Reopened</option>
												</select>
											</div>
										</span>
										<div class="panel panel-default">
											<div class="panel-heading"
												style="padding-top: 6px; padding-bottom: 6px">
												<input type="file" name="upload" id="file"
													onchange="fileUpload(this)"><input type="hidden"
													id="attach" name="attach" value="${resultVO.attach}">
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
										<div class="panel panel-default" id="div_resource">
											<div class="panel-heading">Action Status</div>
											<div class="panel-body">
												<div class="col-lg-12">
													<div id="chart_a"></div>
												</div>
											</div>
										</div>
										<div class="panel panel-default" id="div_resource">
											<div class="panel-heading">
												<label style="margin-right: 15px">Resource Status</label> <label
													class="checkbox-inline"> <input type="checkbox"
													id="cpu_chk" name="cpu_chk" checked>CPU
												</label> <label class="checkbox-inline"> <input
													type="checkbox" id="memory_chk" name="memory_chk" checked>Memory
												</label> <label class="checkbox-inline"> <input
													type="checkbox" id="battery_chk" name="battery_chk" checked>Battery
												</label><label class="checkbox-inline"> <input
													type="checkbox" id="network_chk" name="network_chk" checked>Network
												</label>
											</div>
											<div class="panel-body">
												<div class="col-lg-12" id="c">
													<div id="chart_c"></div>
												</div>
												<div class="col-lg-12" id="m">
													<div id="chart_m"></div>
												</div>
												<div class="col-lg-12" id="b">
													<div id="chart_b"></div>
												</div>
												<div class="col-lg-12" id="n">
													<div id="chart_n"></div>
												</div>
											</div>
										</div>
										<table class="table table-bordered table-hover "
											style="font-size: 12px;" id="event">
											<tr>
												<th style="vertical-align: middle; text-align: center;">Seq</th>
												<c:forEach var="event" items="${event}" varStatus="status">
													<td>${event.seq}</td>
												</c:forEach>
											</tr>
											<tr>

												<th style="vertical-align: middle; text-align: center;">Action</th>
												<c:forEach var="event" items="${event}" varStatus="status">
													<td>${event.evtact}</td>
												</c:forEach>
											</tr>
											<tr>
												<th style="vertical-align: middle; text-align: center;">Desc</th>
												<c:forEach var="event" items="${event}" varStatus="status">
													<td>${event.evtdesc}</td>
												</c:forEach>
											</tr>
											<tbody id="tbody">
											</tbody>
										</table>
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
						<button class="btn btn-default" type="button" id="modify">수정</button>
						<button class="btn btn-default" type="button" id="cancel">닫기</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	<a class="word-export" href="javascript:void(0)"> Export as .doc </a>
	<script src="/js/jquery.minical.js"></script>
</body>
</html>