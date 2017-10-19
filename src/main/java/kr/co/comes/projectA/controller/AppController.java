package kr.co.comes.projectA.controller;

import kr.co.comes.projectA.dto.AppVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.service.AppService;
import kr.co.comes.projectA.util.InputValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/app/*")
public class AppController {

	@Inject
	AppService appservice;

	/**
	 * @param model
	 * @param lc
	 * @return project/app/applist.jsp 호출
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
	 * @return project/app/delapp.jsp 호출
	 */
	@RequestMapping("/delapp")
	public String appDelete(Model model) {
		return "app/delapp";
	}


	/**
	 * @param model
	 * @return project/app/getapp.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getapp")
	public String getapp(Model model) {
		return "app/getapp";
	}

	/**
	 * @param model
	 * @return project/app/getpackage.jsp 호출(jar연결 jsp)
	 */
	@RequestMapping(value = "/getpackage")
	public String getpackage(Model model) {
		return "app/getpackage";
	}

	/**
	 * @param model
	 * @return project/app/appdefault.jsp 호출
	 */
	@RequestMapping(value = "/appdefault")
	public String appdefault(Model model) {
		return "app/appdefault";
	}
	/**
	 * @param model
	 * @return project/app/appdefault1.jsp 호출
	 */
	@RequestMapping(value = "/appdefault1")
	public String appdefault1(Model model) {
		return "app/appdefault1";
	}

	/**
	 * @param model
	 * @param lc
	 * @return project/app/apppop.jsp 호출
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
	 * @return project/app/appmain.jsp 호출
	 * @throws Exception
	 */
	@RequestMapping(value = "/appmain")
	public String mainapp(Model model, ListCriteria lc, HttpSession session,@ModelAttribute AppVO vo, BindingResult result,
						  RedirectAttributes redirectAttributes) throws Exception {
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
			List<AppVO> list = appservice.appList(lc);
			model.addAttribute("list", list);
			vo.setAdduser(id);
			model.addAttribute("count", appservice.countApp(vo));
			model.addAttribute("lc", lc);

		/*lc.setUser(id);*/
			lc.setUser_role(user_role);

			// 페이징처리
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(lc);
			pageMaker.setTotalCount(appservice.listCount(lc));
			model.addAttribute("pageMaker", pageMaker);

		}
		return "app/appmain";
	}
}
