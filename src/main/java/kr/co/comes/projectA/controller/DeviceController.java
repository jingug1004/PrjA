package kr.co.comes.projectA.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.comes.projectA.dto.DeviceVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.service.DeviceService;
import kr.co.comes.projectA.util.InputValidator;

@Controller
@RequestMapping("/device/*")
public class DeviceController {

	@Inject
	DeviceService deviceservice;

	/**
	 * @param model
	 * @param lc
	 * @return project/device/devlist.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping(value = "/devlist")
	public String deviceList(Model model, ListCriteria lc) throws Exception {
		List<DeviceVO> list = deviceservice.deviceList(lc);
		model.addAttribute("list", list);
		return "device/devlist";
	}

	/**
	 * @param model
	 * @return project/device/getdevice.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getdevice")
	public String getdevice(Model model) {
		return "device/getdevice";
	}

	/**
	 * @param model
	 * @return project/device/getwc.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getwc")
	public String getwc(Model model) {
		return "device/getwc";
	}

	/**
	 * @param model
	 * @return project/device/getchk.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getchk")
	public String getchk(Model model) {
		return "device/getchk";
	}

	/**
	 * @param model
	 * @return project/device/getdnf.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getdnf")
	public String getdnf(Model model) {
		return "device/getdnf";
	}

	/**
	 * @param model
	 * @param lc
	 * @return project/device/devpop.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping("/devpop")
	public String deviceListpop(Model model, ListCriteria lc, HttpSession session, @ModelAttribute DeviceVO vo)
			throws Exception {
		String id = (String) session.getAttribute("id");
		lc.setUser(id);
		List<DeviceVO> list = deviceservice.deviceList(lc);
		model.addAttribute("list", list);
		vo.setAdduser(id);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(deviceservice.listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
		return "device/devpop";
	}

	/**
	 * @param model
	 * @param lc
	 * @param session
	 * @param vo
	 * @param result
	 * @param redirectAttributes
	 * @return project/device/devmain.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping(value = "/devmain")
	public String maindevice(Model model, ListCriteria lc, HttpSession session, @ModelAttribute DeviceVO vo,
			BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");

		new InputValidator().validate(vo, result, "search");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		}

		char user_role = role.charAt(0);
		/*
		 * 로그인한 사용자의 ID(user)와 권한(user_role)이 ListCriteria에 저장되어있을 경우에만 프로젝트 목록을
		 * 불러옴.
		 */
		if (id != null && (role.equals("0"))) {

			lc.setUser(id);
			List<DeviceVO> list = deviceservice.deviceList(lc);
			model.addAttribute("list", list);
			vo.setAdduser(id);
			model.addAttribute("count", deviceservice.countDevice(vo));
			model.addAttribute("kt_c", deviceservice.ktDevice(vo));
			model.addAttribute("skt_c", deviceservice.sktDevice(vo));
			model.addAttribute("lg_c", deviceservice.lgDevice(vo));
			model.addAttribute("etc_c", deviceservice.etcDevice(vo));
			model.addAttribute("lc", lc);

			lc.setUser(id);
			lc.setUser_role(user_role);

			// 페이징처리
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(lc);
			pageMaker.setTotalCount(deviceservice.listCount(lc));
			model.addAttribute("pageMaker", pageMaker);
		}
		return "device/devmain";
	}

	/**
	 * @param model
	 * @param vo
	 * @return project/device/devcontrol.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping(value = "/devcontrol")
	public String devcontrol(Model model, DeviceVO vo, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		vo.setAdduser(id);
		List<DeviceVO> list = deviceservice.devicecontrolList(vo);
		model.addAttribute("list", list);
		return "device/devcontrol";
	}

	/**
	 * @return project/device/deldevice.jsp 호출
	 */
	@RequestMapping("/deldevice")
	public String deviceDelete() {
		return "device/deldevice";
	}

	/**
	 * @return project/device/devdefault.jsp 호출
	 */
	@RequestMapping("/devdefault")
	public String deviceDefult() {
		return "device/devdefault";
	}

	/**
	 * @return project/device/devdefault1.jsp 호출
	 */
	@RequestMapping("/devdefault1")
	public String deviceDefult1() {
		return "device/devdefault1";
	}
}
