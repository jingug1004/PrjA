<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/sb-admin.css" rel="stylesheet">
<link href="../../css/plugins/morris.css" rel="stylesheet">
<link href="../../font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="../../css/jquery.minical.css" rel="stylesheet"
	type="text/css">
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script src="/js/FileSaver.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/js/jquery.wordexport.js"></script>
<script src="/js/html2canvas.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("a.word-export").click(function(event) {
			$("#export-content").wordExport();
		});
	});

	function PDF() {
		var img = new Image;
		var doc = new jsPDF('p', 'pt', 'letter');
		img.src = "/images/logo2.png";
		var specialElementHandlers = {
			'#editor' : function(element, renderer) {
				return true;
			}
		};
		//var source = window.document.getElementsByTagName("intro")[0]; // $("intro")[0];
		doc.addImage(img, 'JPEG', 100, 500, 180, 100);
		doc.fromHTML($('#export-content').html(), 15, 15, {
			'width' : 500,
			'elementHandlers' : specialElementHandlers
		});
		doc.save('Test.pdf');
	}

	function pdfdown() {
		html2canvas(document.getElementById("export-content"), {
			onrendered : function(canvas) {
				var imgData = canvas.toDataURL('/images');
				var doc = new jsPDF('p', 'mm', [ 297, 210 ]);
				doc.addImage(imgData, 'PNG', 0, 10, 207, 100);
				doc.save('sample.pdf');
			}
		});
	}
	
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�ȵ���̵� ����̽� �׽�Ʈ ��� ����</title>
</head>
<body>
	<iframe id="some" style="display: none"></iframe>
	<div id="export-content">
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div align="center">
				<u><h2>�ȵ���̵� ����̽� �׽�Ʈ ��� ����</h2></u>
			</div>
		</div>
		<br />
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div align="right" class="col-lg-9" style="margin-left: 100px;">
				<p>
					�ۼ��� : ${id}<br />
					�ۼ� ���� : ${date}<br />
				</p>
				<p></p>
			</div>
		</div>
		<br>
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<!-- table div start -->
			<div class="col-lg-8" style="margin-left: 250px;">
				<table border="1" class="table table-bordered table-hover"
					align="center">
					<thead>
						<tr>
							<th>������Ʈ��</th>
							<th>Phase ����</th>
							<th>�ó����� ����</th>
							<th>�׽�Ʈ ����</th>
							<th>���� Ƚ��</th>
							<th>���� Ƚ��</th>
							<th>�׽�Ʈ �Ⱓ</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${project.name}</td>
							<td>${project.phcount}</td>
							<td>${project.secount}</td>
							<td>${project.trecount}</td>
							<td>${project.trepcount}</td>
							<td>${project.trefcount}</td>
							<td>${project.fromdate}~${project.todate}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<!-- table div end -->
		</div>
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div align="center">
				<u><h4>������Ʈ ����</h4></u>
			</div>
		</div>
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div class="col-lg-8" style="margin-left: 250px;">
				<div style="border: 1px solid;">
					������Ʈ : [${project.name}](${project.abbr})<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${project.desc}<br /> �׽�Ʈ �Ⱓ : ${project.fromdate} ~
					${project.todate}<br /> �׽�Ʈ ������ : ${project.user}
				</div>
			</div>
		</div>
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div class="col-lg-8" style="margin-left: 250px;">
				<div style="border: 1px solid;">
					Phase �̷�<br /> Phase : [${phase.name}](${phase.abbr})<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${phase.desc}<br /> �׽�Ʈ �Ⱓ : ${phase.fromdate} ~ ${phase.todate}
				</div>
			</div>
		</div>
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			<div class="col-lg-8" style="margin-left: 250px;">
				<div align="center">
					<u><h4>�׽�Ʈ ��</h4></u>
				</div>
				<c:forEach var="wor" items="${sena}" varStatus="status">
					<div style="border: 1px solid;">
						�׽�Ʈ Case �̷�<br /> Test Case : [${wor.senaname}](${wor.senaabbr})<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						${wor.senadesc}<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						${wor.senacategory}<br /> �׽�Ʈ ��� : �� ${wor.recount}�� ���� ��
						${wor.repcount}�� ����, ${wor.refcount}�� ����<br />
					</div>
					<div class="row" style="margin-left: 0px; margin-right: 0px;">
						<c:forEach var="row" items="${test}" varStatus="status">
							<c:set var="res" value="${row.result}" />
							<div align="left">
								<c:choose>
									<c:when test="${res eq 'Pass'}">
											<p>${row.resname}</p>
									&nbsp;&nbsp;&nbsp;&nbsp;[${row.devname}] / [${row.appname}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;��� : ����<br />
									&nbsp;&nbsp;&nbsp;&nbsp;����͸� �ð� : [${row.starttime}] ~ [${row.endtime}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;CPU : [${row.cpu_min}] ~ [${row.cpu_max}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;Memory : [${row.memory_min}] ~ [${row.memory_max}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;Battery : [${row.battery_min}] ~ [${row.battery_max}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;Network : [${row.network_min}] ~ [${row.network_max}]
									</c:when>
									<c:otherwise>
											<p>${row.resname}</p>
									&nbsp;&nbsp;&nbsp;&nbsp;[${row.devname}] / [${row.appname}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;��� : ����<br />
									&nbsp;&nbsp;&nbsp;&nbsp;���� : [${row.reason}]<br />
									&nbsp;&nbsp;&nbsp;&nbsp;�ɰ��� : ${row.seriousness}<br />
									&nbsp;&nbsp;&nbsp;&nbsp;�������� : ${row.procedure}<br />
									&nbsp;&nbsp;&nbsp;&nbsp;�ý��� �α� : <br />
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
<%
	response.setHeader("Content-Type", "text/html");
	response.setHeader("Content-Disposition","attachment;filename=test.html");
	response.setHeader("Content-Description","JSP Generated Data");
%>
