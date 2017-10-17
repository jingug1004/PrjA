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

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.service.AppService;
import kr.co.comes.projectA.util.InputValidator;

@Controller
@RequestMapping("/app/*")
public class AppController {

	@Inject
	AppService appservice;
	
	/**
	 * @param model
	 * @param lc
	 * @return project/app/applist.jsp ȣ��
	 * @throws Exception
	 */
	@RequestMapping(value = "/applist")
	public String appList(Model model, ListCriteria lc) throws Exception {
		List<AppVO> list = appservice.appList(lc);
		model.addAttribute("list", list);
		return "app/applist";
	}
	
	/**
	 * @param model
	 * @return project/app/delapp.jsp ȣ��
	 */
	@RequestMapping("/delapp")
	public String appDelete(Model model) {
		return "app/delapp";
	}

	
	/**
	 * @param model
	 * @return project/app/getapp.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getapp")
	public String getapp(Model model) {
		return "app/getapp";
	}
	
	/**
	 * @param model
	 * @return project/app/getpackage.jsp ȣ��(jar���� jsp)
	 */
	@RequestMapping(value = "/getpackage")
	public String getpackage(Model model) {
		return "app/getpackage";
	}
	
	/**
	 * @param model
	 * @return project/app/appdefault.jsp ȣ��
	 */
	@RequestMapping(value = "/appdefault")
	public String appdefault(Model model) {
		return "app/appdefault";
	}
	/**
	 * @param model
	 * @return project/app/appdefault1.jsp ȣ��
	 */
	@RequestMapping(value = "/appdefault1")
	public String appdefault1(Model model) {
		return "app/appdefault1";
	}
	
	/**
	 * @param model
	 * @param lc
	 * @return project/app/apppop.jsp ȣ��
	 * @throws Exception
	 */
	@RequestMapping("/apppop")
	public String appListpop(Model model, ListCriteria lc, HttpSession session,@ModelAttribute AppVO vo) throws Exception{
		String id = (String) session.getAttribute("id");
		lc.setUser(id);
		List<AppVO> list = appservice.appList(lc);
		model.addAttribute("list", list);
		vo.setAdduser(id);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(appservice.listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
		return "app/apppop";
	}
	
	/**
	 * @param model
	 * @param lc
	 * @param session
	 * @param vo
	 * @param result
	 * @param redirectAttributes
	 * @return project/app/appmain.jsp ȣ��
	 * @throws Exception
	 */
	@RequestMapping(value = "/appmain")
	public String mainapp(Model model, ListCriteria lc, HttpSession session,@ModelAttribute AppVO vo, BindingResult result,
			RedirectAttributes redirectAttributes) throws Exception {
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
		List<AppVO> list = appservice.appList(lc);
		model.addAttribute("list", list);
		vo.setAdduser(id);
		model.addAttribute("count", appservice.countApp(vo));
		model.addAttribute("lc", lc);
		
		/*lc.setUser(id);*/
		lc.setUser_role(user_role);

		// ����¡ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(appservice.listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
		
		}
		return "app/appmain";
	}
}
