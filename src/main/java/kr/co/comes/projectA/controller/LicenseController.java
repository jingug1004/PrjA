package kr.co.comes.projectA.controller;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.comes.projectA.service.LicenseService;
import kr.co.comes.projectA.util.LicenseDecoder;

@Controller
@RequestMapping("/license/*")
public class LicenseController {

	@Inject
	LicenseService licenseservice;
	
	/**
	 * LicenseDecoder.initData(request) : 메모리에 라이선스 파일에 저장된 값들을 불러온다.
	 * maxuser : 라이선스의 저장된 최대 유저수
	 * serial : 라이선스의 저장된 물리MAC주소
	 * term : 라이선스의 남은 일자
	 * @param model
	 * @return project/license/list.jsp 호출
	 */
	@RequestMapping(value = "/list")
	public String licenseList(Model model,HttpServletRequest request) {
		LicenseDecoder.initData(request);
		String commercial;
		System.out.println("리스트 확인");
		String maxuser = LicenseDecoder.getMaxuser();
		System.out.println(maxuser + "maxuser 확인");
		String serial = LicenseDecoder.getSerial();
		System.out.println(serial + "serial 확인");
		int term = LicenseDecoder.getTerm();
		System.out.println(term + "term 확인");
		if(term < 1000){
			commercial = "체험";
		}else{
			commercial = "상용";
		}
		model.addAttribute("term", term);
		model.addAttribute("user", maxuser);
		model.addAttribute("commercial", commercial);
		return "license/list";
	}

	/**
	 * @param model
	 * @return project/license/view.jsp 호출
	 */
	@RequestMapping("/view")
	public String licenseView(Model model) {
		return "license/view";
	}

	/**
	 * @param model
	 * @return project/license/regist.jsp 호출
	 */
	@RequestMapping("/regist")
	public String licenseRegist(Model model) {
		return "license/regist";
	}
	
	/**
	 * @param file -> regist에서 선택된 파일의 정보
	 * @param req
	 * @param model
	 * @return project/license/regist_upload.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping("/regist_upload")
	public String readLicense(@RequestParam("upload") MultipartFile file, HttpServletRequest req, Model model) throws Exception {
		
		String ipaddr = req.getRemoteAddr().toString();
		SocketHandler socket = new SocketHandler();
		String pmes = "라이선스 등록에 성공했습니다.";
		String fmes = "라이선스 등록에 실패했습니다.";
		
		try{
			//서버의 경로에 파일 저장로직
			File f = new File("/home/comes/license/" + file.getOriginalFilename());
			file.transferTo(f);		
			
			LicenseDecoder.initData(req);
			
			socket.doMessage(ipaddr, pmes);
		
			}catch(Exception e){
				e.printStackTrace();
				socket.doMessage(ipaddr, fmes);
			}
		return "redirect:/license/list";
	}

}
