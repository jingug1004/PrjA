<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="kr.co.comes.projectA.dto.EventVO"%>
<%@ page import="org.json.simple.*"%>
<!DOCTYPE html>

<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="">

<title>Create Test Case Scenario( Auto Test )</title>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript" src="/js/sockjs-1.1.1.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=1"
	type="text/javascript"></script>
<script>
	var device = "${device}";
	var app = "${app}";
	//var device = "b6e6b473";
	//var app = "com.comes.mskwon.wrapproject";
	var ttt;
	var msg;
	var filename;
	var msg = "";
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
						var stop = 0; // 상태변수. stop버튼 눌렀을 경우 1
						var start = 0;
						var restart = 0; // stop버튼을 누르고(stop==1) start버튼을 눌렀을 경우 1
						var last_seq;

						Sock = new SockJS("/echo-ws");

						Sock.onopen = function() {
							//Sock.send(JSON.stringify(event));
						};

						//데이터 전달 받을 경우
						Sock.onmessage = function(evt) {
							var data = evt.data;

							try {
								var obj = JSON.parse(data);
								var seq = "";
								var evtact = "";
								var xy = "";
								var evtdesc = "";
								var param = "";
								filename = "";

								for ( var key in obj) {
									if (key == "filename") {
										filename = obj[key];
									} else if (key == "msg") {
										msg = obj[key];
									} else {
										if (key == "evtact") {
											evtact = obj[key];
										} else if (key == "seq") {
											seq = obj[key];
										} else if (key == "xy") {
											xy = obj[key];
										} else if (key == "evtdesc") {
											evtdesc = obj[key];
										} else if (key == "param") {
											param = obj[key];
										}
									}
								}

								if (filename != "") {
									$('input[name=path]').val(filename);
								} else if (msg != "") {
									alert(msg);
									location.href = "/project/scenario/scenario_auto?"
											+ "projid=${eventVO.projid}&senid=${eventVO.senid}"
											+ "&devid=${eventVO.devid}&appid=${eventVO.appid}";
								} else {
									var num = parseInt(seq);
									num++;

									$('#seq').val(num);
									$('#evtact').val(evtact);
									$('#xy').val(xy);
									$('#evtdesc').val(evtdesc);

									if (restart == 0 || start == 0) {
										$('#tbody')
												.append(
														"<tr id='tr' name='"
																+ num
																+ "' style='cursor:pointer' onclick='evt_read("
																+ num
																+ ")'><td>"
																+ evtact
																+ "</td><td>"
																+ xy
																+ "</td><td>"
																+ app
																+ "</td><td>"
																+ "</td><td>"
																+ evtdesc
																+ "</td></tr>");
										last_seq = num;
									} else {
										$('#tbody')
												.append(
														"<tr id='tr' name='"
																+ (last_seq + num)
																+ "' style='cursor:pointer' onclick='evt_read("
																+ (last_seq + num)
																+ ")'><td>"
																+ evtact
																+ "</td><td>"
																+ xy
																+ "</td><td>"
																+ app
																+ "</td><td>"
																+ "</td><td>"
																+ evtdesc
																+ "</td></tr>");
									}
									var tr = $("tr[name=" + num + "]");
									tr.children().eq(4).val(param);
								}
							} catch (e) {
								var seq = data
										.substring(data.lastIndexOf("_") + 1)
								seq = seq.substring(0, seq.lastIndexOf("."))
								var num = parseInt(seq);
								if (restart == 0) {
									var tr = $("tr[name=" + num + "]");
								} else {
									var tr = $("tr[name=" + (last_seq + num)
											+ "]");
								}
								tr.children().eq(3).text("O");
								tr.children().eq(3).val(data);
							}

						};

						//소켓 끝
						Sock.onclose = function() {
						}

						$("#start")
								.click(
										function() {

											var url = "kr.co.comes.projecta:// 5 "
													+ device;
											/* location.href = url; */
											$("#url").val(url);
											$("#msg").val(msg);
											window
													.open("/error/test",
															"error_log",
															"width=500, height=500, top=50,left=30,scrollbars=yes");

											if (stop == 1)
												restart = 1;
											start = 1;

										});

						$("#stop").click(function() {
							var url = "kr.co.comes.projecta:// 6"
							location.href = url;
							stop = 1;
						});

						$("#modify").click(function() {
							var seq = $('#seq').val();
							var evtact = $('#evtact').val();
							var xy = $('#xy').val();
							var objid = $('#objid').val();
							var evtdesc = $('#evtdesc').val();

							var tr = $("tr[name=" + seq + "]");

							tr.children().eq(4).text(evtdesc);
						});

						$("#cancel").click(function() {
							deleteFile();
							window.close();
						});

						$("#save")
								.click(
										function() {
											var not_image = false;

											var tr = $('#tbody');
											var length = $('#event tbody tr').length;

											if (length > 0) {
												for (var i = 0; i < length; i++) {
													event.evtact = tr
															.children().eq(i)
															.children().eq(0)
															.text();
													event.xy = tr.children()
															.eq(i).children()
															.eq(1).text();
													event.objid = tr.children()
															.eq(i).children()
															.eq(2).text();
													if (tr.children().eq(i)
															.children().eq(3)
															.val() != "") {
														event.image = tr
																.children().eq(
																		i)
																.children().eq(
																		3)
																.val();
													} else {
														not_image = true;
														break;
													}
													event.evtdesc = tr
															.children().eq(i)
															.children().eq(4)
															.text();
													event.param = tr.children()
															.eq(i).children()
															.eq(4).val();
													arr_val.push(JSON
															.stringify(event));
												}
												if (not_image == true) {
													alert("이미지가 저장되지 않았습니다.");
												}

												if (filename == "") {
													alert("시나리오 파일경로가 저장되지 않았습니다. 다시 시도하여 주십시오.");
													$('#alert')
															.html(
																	"시나리오 파일경로가 저장되지 않았습니다. 다시 시도하여 주십시오.");
												} else {
													myObject.value = arr_val;
													var str = myObject.value
															.toString();
													str = str
															.replace(/,/g, '!');
													str = str.replace(/}!{/g,
															'}@{');
													$('input[name=arr]').val(
															str);
													formObj
															.attr("action",
																	"/project/scenario/scenario_auto");
													formObj.attr("method",
															"post");
													ttt = '1';
													formObj.submit();
													Sock.close();
												}

											} else {
												alert("시나리오를 생성해주세요.");
												$('#alert').html(
														"시나리오를 생성해주세요.");
											}
											// 이미지,filename를 웹소켓을 모두 전달 받았을 경우

										});

					});

	$(document).keydown(function(e) {

		if (e.which === 116) {
			if (typeof event == "object") {
				event.keyCode = 0;
			}
			return false;
		} else if (e.which === 82 && e.ctrlKey) {
			return false;
		}
	});

	$(window).bind("beforeunload", function() {
		if (ttt == '0') {
			deleteFile();
		}
	});

	function deleteFile() {
		var tr = $('#tbody');
		var length = $('#event tbody tr').length;
		for (var i = 0; i < length; i++) {
			var path = tr.children().eq(i).children().eq(3).val();
			if (path != "") {
				$.ajax({
					url : "/project/scenario/deleteFile",
					type : "post",
					data : {
						fileName : path
					},
					dataType : "text",
					success : function(result) {
					}
				});
			}
		}
	}

	function evt_read(seq) {
		var tr = $("tr[name=" + seq + "]");
		var evtact = tr.children().eq(0).text();
		var xy = tr.children().eq(1).text();
		var objid = tr.children().eq(2).text();
		var evtdesc = tr.children().eq(4).text();

		$('#seq').val(seq);
		$('#evtact').val(evtact);
		$('#xy').val(xy);
		$('#objid').val(objid);
		$('#evtdesc').val(evtdesc);
	}
</script>


</head>
<!-- onbeforeunload="closePage()" -->
<body style="margin-top: 15px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding: 30px">
						<span id="alert" style='color: red'></span>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group input-group">
									<span class=input-group-addon>Action</span> <input type="text"
										class="form-control" id="evtact" readonly><input
										type="hidden" id="seq"><input type="hidden"
										name="devid" value="${eventVO.devid}"><input
										type="hidden" name="appid" value="${eventVO.appid}"><input
										type="hidden" id="url"><input type="hidden" id="msg">
								</div>
								<div class="form-group input-group">
									<span class=input-group-addon>x,y</span> <input type="text"
										class="form-control" id="xy" readonly>
								</div>
								<div class="form-group input-group">
									<span class=input-group-addon>Object id</span> <input
										type="text" class="form-control" id="objid" value="${app}"
										readonly>
								</div>
								<div class="form-group input-group">
									<span class=input-group-addon>Description</span> <input
										type="text" class="form-control" id="evtdesc">
								</div>
								<p style="text-align: center;">
									<button class="btn btn-default" type="button" id="modify">수정</button>
								</p>
								<form role="form">
									<input type="hidden" name="projid" value="${eventVO.projid}">
									<input type="hidden" name="senid" value="${eventVO.senid}">
									<input type="hidden" name="arr"> <input type="hidden"
										name="path">
									<div>
										<table class="table table-bordered table-hover table-striped"
											id="event">
											<thead>
												<tr>
													<th style="vertical-align: middle; text-align: center;">action</th>
													<th style="vertical-align: middle; text-align: center;">coordinate</th>
													<th style="vertical-align: middle; text-align: center;">Object
														id</th>
													<th style="vertical-align: middle; text-align: center;">image</th>
													<th style="vertical-align: middle; text-align: center;">desc</th>
												</tr>
											</thead>
											<tbody id="tbody">

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
			<div class="col-lg-12" style="text-align: center;">
				<p>
					<button class="btn btn-default" type="button" id="start">Start
						Create Scenario</button>
					<button class="btn btn-default" type="button" id="stop">Stop
						Create Scenario</button>
					<button class="btn btn-default" type="button" id="save">저장</button>
					<button class="btn btn-default" type="button" id="cancel">닫기</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>