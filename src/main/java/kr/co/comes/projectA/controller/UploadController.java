package kr.co.comes.projectA.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.util.UploadFileUtils;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	
	/**
	 * Ajax 통신 파일 업로드
	 * 
	 * @param file,uploadPath
	 * @return upload 파일 name
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String uploadAjax(MultipartFile file, String uploadPath) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());

		String fileName = file.getOriginalFilename();
		fileName = fileName.replace(" ", "_");
		return UploadFileUtils.uploadFile(uploadPath, fileName, file.getBytes());
	}
	
	/**
	 * 썸네일 이미지 보여주기 및 다운로드
	 * 
	 * @param fileName,uploadPath
	 * @return ResponseEntity<byte[]>
	 */
	@ResponseBody
	@RequestMapping(value = "/displayFile", produces = "text/plain;charset=UTF-8")
	public static ResponseEntity<byte[]> displayFile(String fileName, String uploadPath) throws Exception {
		System.out.println(fileName + "////" + uploadPath);

		if (uploadPath.equals("project")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getProject_path());
		} else if (uploadPath.equals("phase")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getPhase_path());
		} else if (uploadPath.equals("case")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getCase_path());
		} else if (uploadPath.equals("event")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getEvent_path());
		} else if (uploadPath.equals("result")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getResult_path());
		} else if (uploadPath.equals("issue")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getIssue_path());
		} else if (uploadPath.equals("license")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getLicense_path());
		} else if (uploadPath.equals("download")) {
			return UploadFileUtils.displayFile(fileName, ProjectPath.getDownload_path());
		} else {
			return null;
		}
	}

	/**
	 * Ajax 통신 파일 삭제
	 * 
	 * @param fileName,uploadPath
	 * @return ResponseEntity<String>
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public static ResponseEntity<String> deleteFile(String fileName, String uploadPath) {

		logger.info("delete file: " + fileName);

		return UploadFileUtils.deleteFile(fileName, uploadPath);
	}

}
