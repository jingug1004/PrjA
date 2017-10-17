<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Report Test Results</title>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/sb-admin.css" rel="stylesheet">
<link href="/css/plugins/morris.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="/css/jquery.minical.css" rel="stylesheet" type="text/css">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	$(document).ready(
			function() {
				$('#project').click(
						function() {
							var URL = "/project/issue/project_list";
							window.open(URL, "project_list",
									"width=800, height=600, top=50,left=310,scrollbars=yes");

						});

				$('#report').click(
						function() {
							var URL = "/project/issue/project_list";
							window.open(URL, "project_list",
									"width=800, height=600, top=50,left=310,scrollbars=yes");

						});
			});
</script>
</head>
<body style="padding: 15px; margin-top: 15px;">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h6 style="font-weight: bold; color: red; text-align: center;">※
					추후 개발 예정입니다.</h6>
				<h5 class="page-header"
					style="font-weight: bold; margin-bottom: 25px; text-align: center;">안드로이드
					디바이스 테스트 결과 보고서</h5>
				작성자:<br> 일자:<br> <br>
				<table class="table table-bordered table-hover"
					style="text-align: center;">
					<tr>
						<td>프로젝트명</td>
						<td>Phase 개수</td>
						<td>시나리오 개수</td>
						<td>테스트 개수</td>
						<td>성공 횟수</td>
						<td>실패 횟수</td>
						<td>테스트 기간</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12" style="text-align: center;"></div>
			<h5
				style="text-align: center; font-weight: bold; margin-bottom: 25px">테스트
				상세</h5>
			<table class="table">
				<tr>
					<td style="text-align: right; vertical-align: text-top;">프로젝트
						:</td>
					<td>[프로젝트 명](프로젝트 약어)<br>[프로젝트설명]
					</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">작업일 :
					</td>
					<td>[프로젝트 시작일]~[프로젝트 종료일]</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">테스트
						기간 :</td>
					<td>[테스트 일자 from]~[to]</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">테스트
						참여자 :</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">Phase
						이력</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">Phase
						:</td>
					<td>[phase 명](phase 약어)<br>[phase설명]
					</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">작업일 :
					</td>
					<td>[phase 시작일]~[phase 종료일]</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">테스트
						Case 이력</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">Test
						Case :</td>
					<td>[case 명](case 약어)<br>[case 설명]<br>[카테고리]
					</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">시나리오
						:</td>
					<td>[시나리오 설명]</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">테스트
						결과 :</td>
					<td>총 X 번 수행 중 X 번 성공, X 번 실패</td>
				</tr>
				<tr>
					<td style="text-align: right; vertical-align: text-top;">테스트 1</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>[디바이스 명] / [App 명]<br> 결과 : &lt;성공 /실패 &gt; <br>
						&lt;성공인 경우 &gt; <br> 모니터링 시간 : [시작시간] ~ [종료시간]<br> CPU :
						[min] ~ [max]<br> Memory : [min] ~ [max]<br> Battery :
						xx 사용<br> Network 데이터 : [min] ~ [max]<br> &lt;실패인 경우
						&gt;<br> 사유 : [실패 사유]<br> 심각도 : <br>재현절차 : <br>시스템 로그 :<br>
					</td>

				</tr>
			</table>
		</div>

	</div>
</body>
</html>