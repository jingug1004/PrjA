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
	 * @return project/device/devlist.jsp ȣ��
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
	 * @return project/device/getdevice.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getdevice")
	public String getdevice(Model model) {
		return "device/getdevice";
	}

	/**
	 * @param model
	 * @return project/device/getwc.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getwc")
	public String getwc(Model model) {
		return "device/getwc";
	}

	/**
	 * @param model
	 * @return project/device/getchk.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getchk")
	public String getchk(Model model) {
		return "device/getchk";
	}

	/**
	 * @param model
	 * @return project/device/getdnf.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getdnf")
	public String getdnf(Model model) {
		return "device/getdnf";
	}

	/**
	 * @param model
	 * @param lc
	 * @return project/device/devpop.jsp ȣ��
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
	 * @return project/device/devmain.jsp ȣ��
	 * @throws Exception
	 */
	@RequestMapping(value = "/devmain")
	public String maindevice(Model model, ListCriteria lc, HttpSession session, @ModelAttribute DeviceVO vo,
			BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
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
		 * �α����� ������� ID(user)�� ����(user_role)�� ListCriteria�� ����Ǿ����� ��쿡�� ������Ʈ �����
		 * �ҷ���.
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

			// ����¡ó��
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
	 * @return project/device/devcontrol.jsp ȣ��
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
	 * @return project/device/deldevice.jsp ȣ��
	 */
	@RequestMapping("/deldevice")
	public String deviceDelete() {
		return "device/deldevice";
	}

	/**
	 * @return project/device/devdefault.jsp ȣ��
	 */
	@RequestMapping("/devdefault")
	public String deviceDefult() {
		return "device/devdefault";
	}

	/**
	 * @return project/device/devdefault1.jsp ȣ��
	 */
	@RequestMapping("/devdefault1")
	public String deviceDefult1() {
		return "device/devdefault1";
	}
}
