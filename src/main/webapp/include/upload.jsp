<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/css/AdminLTE.css" rel="stylesheet" type="text/css" />
<style>
.fileDrop {
	width: 100%;
	height: auto;
	min-height: 140px;
	border: 1px dotted gray;
	margin: auto;
}
</style>
<script>
	//서버단
 	var project_path = "/project/project/";
	var phase_path = "/project/phase/";
	var case_path = "/project/case/";
	var download_path = "/project/download/";
	var license_path = "/project/license/";
	var note_path = "/project/note/";
	var result_path = "/project/result/";
	var event_path = "/project/event/";   
	//로컬
/*   var project_path = "C:\\zzz\\project";
	 var phase_path = "C:\\zzz\\phase";
	 var case_path = "C:\\zzz\\case";
	 var download_path = "C:\\zzz\\download";
	 var license_path = "C:\\zzz\\license";
	 var issue_path = "C:\\zzz\\issue";
	 var result_path = "C:\\zzz\\result";
	 var event_path = "C:\\zzz\\event";   */

	function checkImageType(fileName) {

		var pattern = /jpg|gif|png|jpeg/i;

		return fileName.match(pattern);

	}

	function getFileInfo(fullName, uploadPath) {
		var fileName, imgsrc, getLink;

		var fileLink;

		if (checkImageType(fullName)) {
			imgsrc = "/displayFile?fileName=" + fullName + "&uploadPath="
					+ uploadPath;
			//alert("imgsrc:"+imgsrc);
			fileLink = fullName.substr(14);
			//alert("fileLink:"+fileLink);
			var front = fullName.substr(0, 12); // /2015/07/01/ 
			var end = fullName.substr(14);

			getLink = "/displayFile?fileName=" + front + end + "&uploadPath="
					+ uploadPath;
			//alert("fullName:"+fullName);
		} else {
			imgsrc = "/images/file.png";
			fileLink = fullName.substr(12);
			getLink = "/displayFile?fileName=" + fullName + "&uploadPath="
					+ uploadPath;
		}
		fileName = fileLink.substr(fileLink.indexOf("_") + 1);
		return {
			fileName : fileName,
			imgsrc : imgsrc,
			getLink : getLink,
			fullName : fullName
		};

	}

	function deleteFile(fullName, path) {

		var deleteData = new FormData();

		deleteData.append("fileName", fullName);
		deleteData.append("uploadPath", path);

		$.ajax({
			url : '/deleteFile',
			data : deleteData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}

		});
	}
</script>
