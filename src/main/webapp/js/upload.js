function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName,uploadPath){
	alert("dddd11111");
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		alert("dddd");
		imgsrc = "/displayFile?fileName="+fullName+"&uploadPath="+uploadPath;
		alert("imagsrc:"+imgsrc);
		
		fileLink = fullName.substr(14);
		alert("fileLink:"+fileLink);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "/displayFile?fileName="+front + end+"&uploadPath="+uploadPath;
		alert("getLink:"+getLink);
	}else{
		imgsrc ="/images/file.png";
		fileLink = fullName.substr(12);
		getLink = "/displayFile?fileName="+fullName+"&uploadPath="+uploadPath;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


