<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ include file="/include/home_menu.jsp"%>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/plugins/jqueryplugins/inputvalidator.js?ver=5"
	type="text/javascript"></script>
<script>
	var msg;

	function PDF() {
		var doc = new jsPDF('p', 'pt', 'letter');
		var specialElementHandlers = {
			'#ignorePDF' : function(element, renderer) {
				return true;
			}
		};
		var source = $("body")[0]; //window.document.getElementsByTagName("body")[0];
		doc.fromHTML(source, 1000, 1000, {
			'width' : 1000,
			'elementHandlers' : specialElementHandlers
		});
		doc.save('Test.pdf');
	}

	$(document).ready(
			function() {
				var browser2 = false;
				var agent = navigator.userAgent.toLowerCase();


				var filter = "win16|win32|win64|mac|macintel";
				if (navigator.platform) {
					if (filter.indexOf(navigator.platform.toLowerCase()) < 0) {
						alert("Chrome, Internet Exploer11만 지원 가능합니다.");
					} else {
						// MS 계열 브라우저를 구분하기 위함.
						if (name === 'Microsoft Internet Explorer'
								|| agent.indexOf('trident') > -1
								|| agent.indexOf('edge/') > -1) {
							browser = 'ie';
							/*  if(name === 'Microsoft Internet Explorer') { // IE old version (IE 10 or Lower)
							     agent = /msie ([0-9]{1,}[\.0-9]{0,})/.exec(agent);
							     browser += parseInt(agent[1]);
							 } else { // IE 11+
							     if(agent.indexOf('trident') > -1) { // IE 11 
							         browser += 11;
							     } else if(agent.indexOf('edge/') > -1) { // Edge
							         browser = 'edge';
							     }
							 } */
							browser2 = true;

						} else if (agent.indexOf('safari') > -1) { // Chrome or Safari
							if (agent.indexOf('opr') > -1) { // Opera
								browser = 'opera';
								browser2 = false;
							} else if (agent.indexOf('chrome') > -1) { // Chrome
								browser = 'chrome';
								browser2 = true;
							} else { // Safari
								browser = 'safari';
								browser2 = false;
							}
						} else if (agent.indexOf('firefox') > -1) { // Firefox
							browser = 'firefox';
							browser2 = false;
						}

						if (browser2 == false) {
							alert("Chrome, Internet Exploer11만 지원 가능합니다.");
						}
					}
				}

				msg = '${resultmsg}';

				if ('${resultmsg}' != "") {
					msg = msg.replace(/다/g, "다\n");
					alert(msg);
					msg = msg.replace(/\n/g, "<br>");
					$('#alert').html(msg);
					deleteCookie("userInputId");
				}

				// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
				var userInputId = getCookie("userInputId");
				$("input[name='id']").val(userInputId);

				if ($("input[name='id']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
					$("#idSaveCheck").prop("checked", true); // ID 저장하기를 체크 상태로 두기.
				}

				$("#idSaveCheck").change(function() { // 체크박스에 변화가 있다면,
					if ($("#idSaveCheck").is(":checked")) { // ID 저장하기 체크했을 때,
						var userInputId = $("input[name='id']").val();
						setCookie("userInputId", userInputId, 30); // 7일 동안 쿠키 보관
					} else { // ID 저장하기 체크 해제 시,
						deleteCookie("userInputId");
					}
				});

				// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
				$("input[name='id']").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
					if ($("#idSaveCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
						var userInputId = $("input[name='id']").val();
						setCookie("userInputId", userInputId, 30); // 7일 동안 쿠키 보관
					}
					var inputid = $("input[name='id']").val();
				});

				var formObj = $("form[role='form']");

				//생성버튼
				$('#login').click(function() {
					formObj.attr("action", "/user/login_check");
					formObj.attr("method", "post");

					/* if ($('#id').val() == "") {
						alert("아이디를 입력해주세요");
					}else if($('#pwd').val() == ""){
						alert("패스워드를 입력해주세요");
					}else{ */
					formObj.submit();
					/* } */

					/* PDF(); */
				});
			});

	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}

	function search() {
		$('#id').submit();
	}
</script>
</head>
<style>
#intro {
	position: absolute;
	width: 425px;
	height: 323px;
	margin: -170px 0 0 -213px;
	top: 50%;
	left: 50%;
}
</style>
<body>
	<!-- Page Heading -->
	<div id="intro">
		<div style="text-align: center;">
			<h3 class="page-header" style="font-weight: bold;">Project A</h3>
			<span id="alert" style='color: red'></span>
			<ol class="breadcrumb">
				<li>
					<form role="form">
						<input type="text" name="id" id="id" class="form-control input-lg"
							placeholder="아이디" maxlength=5> <span id="limitingid"
							style="color: darkgray"></span> <br /> <input type="password"
							name="pwd" id="pwd" class="form-control input-lg"
							placeholder="비밀번호"> <span id="limitingpwd"
							style="color: darkgray"></span> <br /> <input type="button"
							style="background-color: black; border-color: black;"
							class="btn btn-lg btn-success btn-block" id="login" value="로그인">
						<input type="checkbox" id="idSaveCheck">&nbsp;ID저장
					</form>
				</li>
			</ol>
		</div>
	</div>
</body>

</html>