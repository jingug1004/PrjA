<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
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
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<%@ include file="/include/upload.jsp"%>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=2"
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
	var check = 1;
	var replay = {};
	var arr_val = new Array();
	var myObject = new Object();
	var temp = 0;

	function fileUpload(event) {
		var file_value = $('#file').val();

		if (file_value != "") {
			$('#drop_string').hide();
			if (fileadd == true) { //파일이 추가 되어있을경우 삭제
				$(".uploadedList").html("");
				deleteFile(prefile, result_path);
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

	var resource_param;
	var test;
	var front, back;
	var cpu, cpu_min, cpu_max;
	var action;
	var msg;
	var memory, memory_min, memory_max;
	var battery, battery_min, battery_max;
	var network, network_min, network_max;
	var Sock;
	var app = "${app}";
	var device = "${device}";
	var Opt = "";
	var monitoring = "no";
	var filename = "${filename}";
	var repeat;

	function getTimeStamp() {
		var d = new Date();

		var s = leadingZeros(d.getFullYear(), 4) + '-'
				+ leadingZeros(d.getMonth() + 1, 2) + '-'
				+ leadingZeros(d.getDate(), 2) + ' ' +

				leadingZeros(d.getHours(), 2) + ':'
				+ leadingZeros(d.getMinutes(), 2) + ':'
				+ leadingZeros(d.getSeconds(), 2);

		return s;
	}

	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();

		if (n.length < digits) {
			for (i = 0; i < digits - n.length; i++)
				zero += '0';
		}
		return zero + n;
	}

	function popupdev() {
		var url = "/device/devpop";
		var name = "devicelist";

		window
				.open(
						url,
						name,
						"width=1000 , height=700, toolbar=no, status=no, scrollbars=yes, menubar=no, resizeable=yes");
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
						var pos = 0;
						var repeatnum = 0;
						var eventlength = '${eventlength}';
						$('#div_repeat').hide();
						msg = '${resultmsg}';
						var category = $('#s_category').val();

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

						if (category == "Resource Monitoring") {
							$('#div_repeat').hide();
							$('#repeat_div').hide();
							$('#div_interval').hide();
							$('#event').hide();
						} else if (category == "AutoTest + Monitoring") {
						} else if (category == "Automation Test") {
							$('#div_resource').hide();
							$('#check').hide();
						}

						$("input:radio[name=repeat]").change(
								function() {
									check = $(
											"input:radio[name=repeat]:checked")
											.val();
									if (check == 1) {
										repeat = 1;
										$('#div_repeat').hide();
									} else {
										$('#div_repeat').show();
									}
								});

						var formObj = $("form[role='form']");
						Sock = new SockJS("/echo-ws");

						Sock.onopen = function() {
						};

						//데이터 전달 받을 경우
						Sock.onmessage = function(evt) {
							var test = false;
							repeatnum = 0;
							var payload = new Array();
							$('[name="endtime"]').val(getTimeStamp());
							var data = evt.data;
							var obj = JSON.parse(data);
							var payloadobj;
							for ( var key in obj) {
								if (key == "msg") {
									msg = obj[key];
								} else if (category != "Resource Monitoring") {
									if (key == "repeatnum") {
										temp = obj[key];
										repeatnum = obj[key];
										payloadobj = JSON.parse(obj["payload"]);
										for ( var key2 in payloadobj) {
											payload.push(payloadobj[key2]);
										}
										test = true;
									}
								}

								if (category != "Automation Test") {
									if (key == "cpu") {
										cpu = obj[key];
									} else if (key == "memory") {
										memory = obj[key];
									} else if (key == "battery") {
										battery = obj[key];
									} else if (key == "network") {
										network = obj[key];
									} else if (key == "param") {
										resource_param = obj[key];
									} else if (key == "cpu_min") {
										cpu_min = obj[key];
									} else if (key == "cpu_max") {
										cpu_max = obj[key];
									} else if (key == "memory_min") {
										memory_min = obj[key];
									} else if (key == "memory_max") {
										memory_max = obj[key];
									} else if (key == "battery_min") {
										battery_min = obj[key];
									} else if (key == "battery_max") {
										battery_max = obj[key];
									} else if (key == "network_min") {
										network_min = obj[key];
									} else if (key == "network_max") {
										network_max = obj[key];
									} else if (key == "action") {
										action = obj[key];
									}
								}
							}

							if (category != "Automation Test") {
								if (test == false) {
									$('[name="action"]').val(action);
									$('[name="cpu"]').val(cpu);
									$('[name="cpu_min"]').val(cpu_min);
									$('[name="cpu_max"]').val(cpu_max);
									$('[name="memory"]').val(memory);
									$('[name="memory_min"]').val(memory_min);
									$('[name="memory_max"]').val(memory_max);
									$('[name="battery"]').val(battery);
									$('[name="battery_min"]').val(battery_min);
									$('[name="battery_max"]').val(battery_max);
									$('[name="network"]').val(network);
									$('[name="network_min"]').val(network_min);
									$('[name="network_max"]').val(network_max);
									$('[name="param"]').val(resource_param);
									$('[name="endtime"]').val(getTimeStamp());
									chart();
								}
							}
							if (category != "Resource Monitoring") {
								if (repeatnum != 0) {
									$('#tbody')
											.append(
													'<tr id='+temp+' name='+temp+'>'
															+ '<th style="vertical-align: middle; text-align: center;">RepeatCount '
															+ temp + '</th>'
															+ '</tr>');
									for ( var arr in payload) {
										$('[name="' + temp + '"]')
												.append(
														"<td>" + payload[arr]
																+ "</td>");
									}
								}
							}
						}

						//소켓 끝
						Sock.onclose = function() {
						}

						$('#span').hide();

						$('#test')
								.click(
										function() {
											var nullck = false;
											var numck = false;
											if (check == '0') {
												repeat = $('#telno').val()

												if (repeat == "") {
													nullck = true;
												}
												if (parseInt(repeat) == 0) {
													nullck = true;
													numck = true;
												}
											} else {
												repeat = check;
											}

											if (nullck == false) {
												$("#msg").val(msg);

												if (repeat)
													var interval = 0;
												Opt = "";

												if ($('#interval').val() != "") {
													interval = $('#interval')
															.val();
												}
												var obj = $('[name="category"]');
												var length = obj.length;
												for (i = 0; i < length; i++) {
													if (obj[i].checked == true) {
														Opt = Opt + " "
																+ obj[i].value;
													}
												}

												var url;

												if (category == 'Automation Test') {
													url = "kr.co.comes.projecta:// 7 "
															+ device
															+ " "
															+ "false"
															+ " "
															+ app
															+ " "
															+ repeat
															+ " "
															+ interval
															+ " "
															+ filename;
													if (filename == "") {
														alert("시나리오를 생성해주세요.");
													} else {
														/* location.href = url; */
														$("#url").val(url);
														var URL = "/error/log";
														window
																.open(
																		"/error/test",
																		"error_log",
																		"width=500, height=500, top=50,left=30,scrollbars=yes");
													}
												} else if (category == 'Resource Monitoring') {
													url = "kr.co.comes.projecta:// 3 "
															+ device
															+ " "
															+ app + Opt;
													$("#url").val(url);
													window
															.open(
																	"/error/test",
																	"error_log",
																	"width=500, height=500, top=50,left=30,scrollbars=yes");
												} else if (category == 'AutoTest + Monitoring') {
													//alert("category == 'AutoTest + Monitoring'");
													url = "kr.co.comes.projecta:// 7 "
															+ device
															+ " "
															+ "true"
															+ " "
															+ app
															+ " "
															+ repeat
															+ " "
															+ interval
															+ " "
															+ filename + Opt;
													if (filename == "") {
														alert("시나리오를 생성해주세요");
													} else {
														$("#url").val(url);
														window
																.open(
																		"/error/test",
																		"error_log",
																		"width=500, height=500, top=50,left=30,scrollbars=yes");
													}
												}

												$('[name="starttime"]').val(
														getTimeStamp());
											} else {
												if (numck == false) {
													alert("Repeat Count를 입력해주세요");
												} else {
													alert("Repeat Count를 1회 이상 입력해주세요");
												}
											}

										});

						$('#ok')
								.click(
										function() {
											if ($('#interval').val() == "") {
												$('#interval').val(0);
											}

											if (temp != 0) {
												var tr = $('#tbody');
												var leng = $('#tbody tr td').length;
												var length = leng / temp;
												if (length > 0) {
													for (var i = 0; i < temp; i++) {
														for (var j = 1; j < length + 1; j++) {
															replay.seq = j;
															replay.repeatnum = i + 1;
															replay.result = tr
																	.children()
																	.eq(i)
																	.children()
																	.eq(j)
																	.text();
															arr_val
																	.push(JSON
																			.stringify(replay));
														}
													}
													myObject.value = arr_val;
													var str = myObject.value
															.toString();
													str = str
															.replace(/,/g, '!');
													str = str.replace(/}!{/g,
															'}@{');
													$('input[name=arr]').val(
															str);
												}
											}

											formObj.attr("action",
													"/project/result/test");
											formObj.attr("method", "post");
											formObj.submit();
											save = true;
										});

						$('#stop').click(function() {
							$('[name="endtime"]').val(getTimeStamp());
							if (category == "Resource Monitoring") {
								url = "kr.co.comes.projecta:// 4 " + device
							} else {
								url = "kr.co.comes.projecta:// 18"
							}
							location.href = url;
						});

						$('#result').change(function() {
							var result = $('#result').val();
							if (result == '1') {
								$('#span').show();
							} else {
								$('#span').hide();
							}
						})
					});

	function chart() {

		$.ajax({
			url : "/project/result/chart",
			type : "post",
			data : {
				getAction : action,
				getCpu : cpu,
				getMemory : memory,
				getBattery : battery,
				getNetwork : network,
			},
			dataType : "json",
			success : function(result) {
				var arr = new Array();
				arr = result;
				var action = arr[0];
				var cpu = arr[1];
				var memory = arr[2];
				var battery = arr[3];
				var network = arr[4];
				var m_max = arr[5];
				googleChart(action, cpu, memory, battery, network, m_max);
			},
			error : function(info, xhr) {
				if (info.readyState == '4') {
					alert('문제가 발생했습니다.\n상태코드 : ' + info.status + '\n\n'
							+ info.responseText);
				} else {
					alert('문제가 발생했습니다.\n잠시후 다시 시도해 주세요.\n 상태코드 : '
							+ info.status);
				}
			}
		});
	}

	function googleChart(action, cpu, memory, battery, network, m_max) {
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});

		google.charts.setOnLoadCallback(drawChart);

		function drawChart() {

			var data = new google.visualization.DataTable(cpu);
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
				curveType : 'function',
				legend : {
					position : 'bottom'
				}
			};
			var chart = new google.visualization.LineChart(document
					.getElementById('chart_c'));

			chart.draw(data, options);

			var data_a = new google.visualization.DataTable(action);
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

			var data_m = new google.visualization.DataTable(memory);
			var options_m = {
				title : 'Memory',
				curveType : 'function',
				vAxis : {
					viewWindowMode : 'explicit',
					viewWindow : {
						min : 0,
						max : m_max
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

			var data_b = new google.visualization.DataTable(battery);
			var options_b = {
				title : 'Battery(%)',
				curveType : 'function',
				vAxis : {
					viewWindowMode : 'explicit',
					viewWindow : {
						min : 0,
						max : 100,
					},
					ticks : [ 0, 25, 50, 75, 100 ],
				},
				legend : {
					position : 'bottom'
				}
			};
			var chart_b = new google.visualization.LineChart(document
					.getElementById('chart_b'));

			chart_b.draw(data_b, options_b);

			var data_n = new google.visualization.DataTable(network);
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

	//페이지가 재로드 될경우
	function closePage() {
		if (prefile != "") {
			if (save == false)
				deleteFile(prefile, result_path);
		}
	}

	function del(funllName) {
		deleteFile(prefile, result_path);
		$(".uploadedList").html(""); //del 버튼 클릭시 화면 지움
		$("#file").val("");
		$('#drop_string').show();
	}
</script>
</head>
<body style="margin-top: 15px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="padding: 15px;">
						<h3 class="panel-title">
							<i class="fa fa-fw fa-check"></i> Run Test According To Test Case
							Scenario
						</h3>
					</div>
					<div class="panel-body" style="padding: 30px">
						<span id="alert" style='color: red'></span>
						<div class="row">
							<div class="col-lg-12">
								<form role="form" enctype="multipart/form-data">
									<input type="hidden" name="projid" value="${caseVO.projid}">
									<input type="hidden" name="phid" value="${caseVO.phid}">
									<input type="hidden" name="senid" value="${caseVO.senid}">
									<input type="hidden" name="action"> <input
										type="hidden" name="cpu"> <input type="hidden"
										name="memory"> <input type="hidden" name="battery">
									<input type="hidden" name="network"> <input
										type="hidden" name="param"><input type="hidden"
										name="cpu_min"><input type="hidden" name="cpu_max"><input
										type="hidden" name="memory_min"><input type="hidden"
										name="memory_max"><input type="hidden"
										name="battery_min"><input type="hidden"
										name="battery_max"><input type="hidden"
										name="network_min"><input type="hidden"
										name="network_max"><input type="hidden" id="starttime"
										value="0000-00-00 00:00:00" name="starttime"> <input
										type="hidden" name="endtime" id="endtiem"
										value="0000-00-00 00:00:00"> <span id="input"></span><input
										type="hidden" id="url"><input type="hidden" id="msg">
									<input type="hidden" name="arr">
									<div class="form-group input-group">
										<span class=input-group-addon>Project Title</span> <input
											type="text" class="form-control" name="proj_name"
											value="${name.proj_name}" readonly>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Phase Title</span> <input
											type="text" class="form-control" name="ph_name"
											value="${name.ph_name}" readonly>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Case Title</span> <input
											type="text" class="form-control" name="s_name"
											value="${name.s_name}" readonly>

									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Test Case Category</span> <input
											type="text" class="form-control" id="s_category"
											name="c_category" value="${caseVO.s_category}" readonly>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Device</span> <input type="text"
											class="form-control" id="dev_name" name="dev_name"
											value="${caseVO.dev_name}" readonly> <input
											type="hidden" id="devid" name="devid" value="${caseVO.devid}">
										<span class="input-group-btn"><button
												class="btn btn-default" type="button" id="asc"
												onclick="popupdev()">
												검색<i class="fa fa-search"></i>
											</button></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>App</span> <input type="text"
											class="form-control" id="app_name" name="app_name"
											value="${caseVO.app_name}" readonly> <input
											type="hidden" id="appid" name="appid" value="${caseVO.appid}">
										<span class="input-group-btn"><button
												class="btn btn-default" type="button" id="asc"
												onclick="popupapp()">
												검색<i class="fa fa-search"></i>
											</button></span> <span class="input-group-btn"></span>
									</div>
									<div id="repeat_div">
										<div class="form-group input-group">
											<span class=input-group-addon>Repeat</span> <label
												class="radio-inline" style="margin-left: 5px;"> <input
												type="radio" name="repeat" id="repeat" value="1" checked>1회
											</label> <label class="radio-inline"> <input type="radio"
												name="repeat" id="repeat" value="0">반복
											</label>
										</div>
										<div class="form-group input-group" id="div_repeat">
											<span class=input-group-addon>Repeat Count</span> <input
												type="text" class="form-control" id="telno" value=""
												name="repeat_count">
										</div>
										<div class="form-group input-group" id="div_interval">
											<span class=input-group-addon>Monitor Interval</span><input
												type="text" class="form-control" name="interval"
												id="interval">
										</div>
									</div>
									<div class="form-group" id=check>
										<label class="checkbox-inline"> <input type="checkbox"
											name="category" value="cpu" checked>CPU
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="category" value="memory" checked>Memory
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="category" value="battery" checked>Battery
										</label> <label class="checkbox-inline"> <input
											type="checkbox" name="category" value="network" checked>Network
										</label>
									</div>
									<div class="row">
										<div class="col-lg-12" style="text-align: center;">
											<p>
												<button class="btn btn-default" type="button" id="test">Run
													Test</button>
												<button class="btn btn-default" type="button" id="stop">Stop</button>
											</p>
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
										<div class="panel-heading">Resource Status</div>
										<div class="panel-body">
											<div class="col-lg-12">
												<div id="chart_c"></div>
											</div>
											<div class="col-lg-12">
												<div id="chart_m"></div>
											</div>
											<div class="col-lg-12">
												<div id="chart_b"></div>
											</div>
											<div class="col-lg-12">
												<div id="chart_n"></div>
											</div>
										</div>
									</div>
									<div>
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
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Result Title</span> <input
											type="text" class="form-control" id="name" name="resname"><span
											id="limitingname" style="color: darkgray"></span>
									</div>
									<div class="form-group input-group">
										<span class=input-group-addon>Result</span> <select
											class="form-control" name="result" id="result">
											<option value='0'>Pass</option>
											<option value='1'>Fail</option>
											<option value='2'>N/A</option>
											<option value='3'>N/I</option>
										</select>
									</div>
									<span id="span">
										<div class='form-group input-group'>
											<span class=input-group-addon>Error Status Result
												Seriousness</span> <select class='form-control' name='seriousness'>
												<option value='0'>Critical</option>
												<option value='1'>Major</option>
												<option value='2'>Minor</option>
											</select>
										</div>
										<div class='panel panel-default'>
											<div class='panel-heading'
												style='padding-top: 6px; padding-bottom: 6px'>Test
												Procedure</div>
											<div class='panel-body' style='padding: 0px;'>
												<textarea class='form-control' rows='6' id='procedure'
													name='procedure'></textarea>
												<span id="limitingprocedure" style="color: darkgray"></span>
											</div>
										</div>
										<div class='form-group input-group'>
											<span class=input-group-addon>Reason</span> <input
												type='text' class='form-control' id='reason' name='reason'>
											<span id="limitingreason" style="color: darkgray"></span>
										</div>
										<div class='form-group input-group'>
											<span class=input-group-addon>Result Status</span> <select
												class='form-control' name='status'>
												<option value='0'>New</option>
												<option value='1'>Open</option>
												<option value='2'>Assigned</option>
												<option value='3'>Resolved</option>
												<option value='4'>Closed</option>
												<option value='5'>Reopened</option>
											</select>
										</div>

									</span>
									<div class="panel panel-default">
										<div class="panel-heading"
											style="padding-top: 6px; padding-bottom: 6px">
											<input type="file" name="upload" id="file"
												onchange="fileUpload(this)"><input type="hidden"
												name="attach" id="attach">
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
					<button class="btn btn-default" type="button" id="cancel">닫기</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>