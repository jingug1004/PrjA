package kr.co.comes.projectA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.comes.projectA.dto.EventVO;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.service.EventService;
import kr.co.comes.projectA.util.InputValidator;

@Controller

@RequestMapping("/project/scenario/*")

public class ScenarioController {

	public static List<String> param = new ArrayList<String>();
	Gson gson = new Gson();
	
	@Inject
	private EventService service;

	/**
	 * scenario auto 생성페이지
	 * 
	 * @param model
	 * @return /project/scenario/scenario_auto.jsp 호출
	 */
	@RequestMapping(value = "/scenario_auto", method = RequestMethod.GET)
	public void scnario_createGET(EventVO vo, Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		System.out.println(vo.getDevid());
		model.addAttribute("eventVO", vo);
		model.addAttribute("device", service.getDevice(vo.getDevid()));
		model.addAttribute("app", service.getApp(vo.getAppid()));
	}

	/**
	 * scenario auto 생성
	 * 
	 * @param model
	 * @return /project/scenario/scenario_modify.jsp 호출
	 */
	@RequestMapping(value = "/scenario_auto", method = RequestMethod.POST)
	public String scnario_creatPOST(EventVO event,
			 BindingResult result, String arr, String path, Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		
		System.out.println(arr);
		int projid = event.getProjid();
		int senid = event.getSenid();
		String[] str = null;
		EventVO vo = new EventVO();

		arr = arr.replaceAll("!", ","); // jsp 단에서 ','가 넘어오지않아 '!'로 치환해서 보냈기때문에 다시 '!'를 ',' 로 치환
		arr = arr.replaceAll("@", "\n"); // jsp 단에서 배열 구분위해 치환해둔 @를 \n으로 치환
		str = arr.split("\n"); // '\n' 기준으로 배열로 변경
		
		//input data  체크 (체크를 위한 vo 셋팅)
		for (String string : str) {
			System.out.println("String::" + string);
			vo = gson.fromJson(string, EventVO.class); // eventVO 로 바꿈.
			vo.setProjid(projid);
			vo.setSenid(senid);
			vo.setAdduser((String) session.getAttribute("id"));
			vo.setFilename(path);
			vo.setDevid(event.getDevid());
			vo.setAppid(event.getAppid());
			
			new InputValidator().validate(vo, result, "auto_event");
			if (result.hasErrors()) {
				String resultmsg = "";
				for (int i = 0; i < result.getGlobalErrors().size(); i++) {
					resultmsg += result.getGlobalErrors().get(i).getCode();
				}

				redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
				redirectAttributes.addAttribute("eventVO", event);
				redirectAttributes.addAttribute("device", service.getDevice(vo.getDevid()));
				redirectAttributes.addAttribute("app", service.getApp(vo.getAppid()));
				return "redirect:/project/scenario/scenario_auto";
			}
		}
		
		//이전에 시나리오를 생성했을 경우 이전 시나리오 삭제
		if (service.event_read(event) != null) {
			List<EventVO> eVO = service.getEvent(event);
			for (EventVO eventvo : eVO) {
				if (eventvo.getImage() != null) {
					UploadController.deleteFile(eventvo.getImage(), ProjectPath.getEvent_path());
				}
				service.remove(eventvo);
			}
		}
		
		//시나리오 생성
		for (String string : str) {
			System.out.println("String::" + string);
			vo = gson.fromJson(string, EventVO.class); // eventVO 로 바꿈.
			vo.setProjid(projid);
			vo.setSenid(senid);
			vo.setAdduser((String) session.getAttribute("id"));
			vo.setFilename(path);
			System.out.println(vo.toString());
			service.event_create(vo);
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		return "redirect:/project/scenario/scenario_modify";

	}

	/**
	 * scenario manual 생성페이지
	 * 
	 * @param model
	 * @return /project/scenario/scenario_manual.jsp 호출
	 */
	@RequestMapping(value = "/scenario_manual", method = RequestMethod.GET)
	public void scnario_manualGET(EventVO vo, Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		System.out.println(vo.getProjid() + "   " + vo.getSenid());
		model.addAttribute("eventVO", vo);
	}

	/**
	 * scenario manual 생성
	 * 
	 * @param model
	 * @return /project/scenario/scenario_manual.jsp 호출
	 */
	@RequestMapping(value = "/scenario_manual", method = RequestMethod.POST)
	public String scnario_manualPOST(String arr, EventVO event, BindingResult result ,Model model, 
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		
		String[] str;
		System.out.println(arr);
		arr = arr.replaceAll("!", ","); //  jsp 단에서 치환해둔 '!'를 다시 ','로 치환
		arr = arr.replaceAll("@", "\n"); // jsp 단에서 배열 구분위해 치환해둔 @를 \n으로 치환
		str = arr.split("\n"); // '\n' 기준으로 배열로 변경
		
		int projid = event.getProjid();
		int senid = event.getSenid();
		
		EventVO vo = new EventVO();
		
		//input data 체크
		for (String string : str) {
			System.out.println("String::" + string);
			vo = gson.fromJson(string, EventVO.class); // eventVO 로 바꿈.
			vo.setProjid(projid);
			vo.setSenid(senid);
			vo.setAdduser((String) session.getAttribute("id"));
			System.out.println(vo.toString());
			
			new InputValidator().validate(vo, result, "manual_event");
			if (result.hasErrors()) {
				String resultmsg = "";
				for (int i = 0; i < result.getGlobalErrors().size(); i++) {
					resultmsg += result.getGlobalErrors().get(i).getCode();
				}
				System.out.println(resultmsg);
				redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
				redirectAttributes.addAttribute("projid", vo.getProjid());
				redirectAttributes.addAttribute("senid", vo.getSenid());
				return "redirect:/project/scenario/scenario_manual";
			}
		}

		//시나리오 생성 전 시나리오 있을 경우 삭제
		if (service.event_read(event) != null) {
			List<EventVO> eVO = service.getEvent(event);
			for (EventVO e : eVO) {
				if (e.getImage() != null) {
					UploadController.deleteFile(e.getImage(), ProjectPath.getEvent_path());
				}
				service.remove(e);
			}
		}
		
		//시나리오 생성
		for (String string : str) {
			vo = gson.fromJson(string, EventVO.class); // eventVO 로 바꿈.
			System.out.println(event.toString());
			vo.setProjid(event.getProjid());
			vo.setSenid(event.getSenid());
			vo.setAdduser((String) session.getAttribute("id"));
			service.event_create(vo);
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		return "redirect:/project/scenario/scenario_modify";
	}

	/**
	 * scenario modify 페이지
	 * 
	 * @param model
	 * @return project/scenario/modify.jsp 호출
	 */
	@RequestMapping(value = "/scenario_modify", method = RequestMethod.GET)
	public void scnario__modifyGET(EventVO vo, Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {

		System.out.println(vo.getProjid() + "      " + vo.getSenid());
		model.addAttribute("list", service.event_read(vo));
		model.addAttribute("eventVO", vo);

	}

	/**
	 * scenario modify
	 * 
	 * @param model
	 * @return project/scenario/modify.jsp 호출
	 */
	@RequestMapping(value = "/scenario_modify", method = RequestMethod.POST)
	public String scnario_modifyPOST(EventVO vo, Model model, HttpSession session, BindingResult result,
			RedirectAttributes redirectAttributes) throws Exception {
		
		//input data 체크
		new InputValidator().validate(vo, result, "modify_event");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {

			service.event_update(vo);
		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		return "redirect:/project/scenario/scenario_modify";
	}

	/**
	 * 이벤트 정보
	 * 
	 * @param param
	 * @return project/scenario/get_scenario.jsp 호출
	 */
	@RequestMapping(value = "/getscenario", method = RequestMethod.POST)
	public String get_scenario(String param, EventVO vo, Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {

		System.out.println(param);
		
		return "project/scenario/get_scenario";
	}
	
	
	/**
	 * 이벤트 정보 저장되어있는 파일 이름
	 * 
	 * @param param
	 * @return project/scenario/get_path.jsp 호출
	 */
	@RequestMapping(value = "/getpath")
	public String get_path(@RequestParam("param") String param, HttpServletRequest request) throws Exception {
		
		System.out.println(param);
		String ipaddr = request.getRemoteAddr().toString();
		
		//jsp단에서 이벤트와 파일명을 구분하기위해 json형식으로 값 변환
		JsonObject json = new JsonObject();
		json.addProperty("filename", param);
		System.out.println(json.toString());

		//파일이름이 param값으로 넘어왔을 경우 웹소켓으로 파일명 전달
		if (param != null) {
			SocketHandler socket = new SocketHandler();
			System.out.println("doMessage start");
			socket.doMessage(ipaddr, json.toString());
		}

		return "project/scenario/get_path";
	}

}
