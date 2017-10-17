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
	 * LicenseDecoder.initData(request) : �޸𸮿� ���̼��� ���Ͽ� ����� ������ �ҷ��´�.
	 * maxuser : ���̼����� ����� �ִ� ������
	 * serial : ���̼����� ����� ����MAC�ּ�
	 * term : ���̼����� ���� ����
	 * @param model
	 * @return project/license/list.jsp ȣ��
	 */
	@RequestMapping(value = "/list")
	public String licenseList(Model model,HttpServletRequest request) {
		LicenseDecoder.initData(request);
		String commercial;
		System.out.println("����Ʈ Ȯ��");
		String maxuser = LicenseDecoder.getMaxuser();
		System.out.println(maxuser + "maxuser Ȯ��");
		String serial = LicenseDecoder.getSerial();
		System.out.println(serial + "serial Ȯ��");
		int term = LicenseDecoder.getTerm();
		System.out.println(term + "term Ȯ��");
		if(term < 1000){
			commercial = "ü��";
		}else{
			commercial = "���";
		}
		model.addAttribute("term", term);
		model.addAttribute("user", maxuser);
		model.addAttribute("commercial", commercial);
		return "license/list";
	}

	/**
	 * @param model
	 * @return project/license/view.jsp ȣ��
	 */
	@RequestMapping("/view")
	public String licenseView(Model model) {
		return "license/view";
	}

	/**
	 * @param model
	 * @return project/license/regist.jsp ȣ��
	 */
	@RequestMapping("/regist")
	public String licenseRegist(Model model) {
		return "license/regist";
	}
	
	/**
	 * @param file -> regist���� ���õ� ������ ����
	 * @param req
	 * @param model
	 * @return project/license/regist_upload.jsp ȣ��
	 * @throws Exception
	 */
	@RequestMapping("/regist_upload")
	public String readLicense(@RequestParam("upload") MultipartFile file, HttpServletRequest req, Model model) throws Exception {
		
		String ipaddr = req.getRemoteAddr().toString();
		SocketHandler socket = new SocketHandler();
		String pmes = "���̼��� ��Ͽ� �����߽��ϴ�.";
		String fmes = "���̼��� ��Ͽ� �����߽��ϴ�.";
		
		try{
			//������ ��ο� ���� �������
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
