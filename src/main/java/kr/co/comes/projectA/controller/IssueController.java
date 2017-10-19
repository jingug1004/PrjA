package kr.co.comes.projectA.controller;

import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.service.*;
import kr.co.comes.projectA.util.InputValidator;
import kr.co.comes.projectA.util.UploadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RequestMapping("/project/issue/*")
@Controller
public class IssueController {

	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Inject
	private ProjectService proj_service;
	@Inject
	private PhaseService ph_service;
	@Inject
	private CaseService case_service;
	@Inject
	private MainService m_service;
	@Inject
	private IssueService service;

	/**
	 * Issue 목록화면
	 *
	 * @param model
	 * @return project/issue/list.jsp 호출
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info(lc.toString());
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		/*
		 * * projectMapper.xml에 있는 select(id=list)구문 실행 후 ProjectVO타입으로 저장된 정보
		 * 리스트를 list란 이름으로 list.jsp에 전달
		 */

		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", service.issue_list(lc));
		model.addAttribute("lc", lc);
		// list.jsp에 project summary 값 전달.
		model.addAttribute("note", m_service.NoteCount(lc));
		model.addAttribute("issue", m_service.IssueCount(lc));
		model.addAttribute("defect", m_service.DefectCount(lc));

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.issue_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/**
	 * Issue 생성화면
	 *
	 * @param model
	 * @return project/issue/create.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(HttpSession session, ListCriteria lc, Model model) throws Exception {
		String id = (String) session.getAttribute("id");
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("lc", lc);

	}

	/**
	 * Issue 생성
	 *
	 * @param model
	 * @return project/issue/modify.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(HttpSession session, IssueVO vo, Model model, BindingResult result,
							 RedirectAttributes redirectAttributes) throws Exception {

		// input data 체크
		new InputValidator().validate(vo, result, "issue");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			redirectAttributes.addAttribute("projid", vo.getProjid());
			redirectAttributes.addAttribute("phid", vo.getPhid());
			redirectAttributes.addAttribute("seq", vo.getSeq());
			return "redirect:/project/issue/create";
		}
		vo.setAdduser((String) session.getAttribute("id"));
		service.issue_create(vo);
		vo.setSeq(service.seq(vo.getProjid()));

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("seq", vo.getSeq());
		return "redirect:/project/issue/modify";
	}

	/*
	 * Issue 수정페이지
	 *
	 * @param model
	 *
	 * @return project/issue/modify.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(HttpSession session, IssueVO vo, Model model) throws Exception {
		vo = service.issue_read(vo);
		model.addAttribute("IssueVO", service.issue_read(vo));
	}

	/*
	 * Issue 수정
	 *
	 * @param model
	 *
	 * @return project/issue/modify.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(HttpSession session, IssueVO vo, BindingResult result, Model model,
							 RedirectAttributes redirectAttributes) throws Exception {

		// input data 체크
		new InputValidator().validate(vo, result, "issue");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {
			vo.setUpduser((String) session.getAttribute("id"));
			service.issue_modify(vo);
		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("seq", vo.getSeq());

		return "redirect:/project/issue/modify";
	}

	/*
	 * * Issue 삭제
	 *
	 * @param model
	 *
	 * @return project/issue/list.jsp 호출
	 */

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(String check[], IssueVO vo, Model model, HttpSession session,
						 RedirectAttributes redirectAttributes) throws Exception {
		if (check != null) {
			// 전달받은 parameter 값 check(체크된 projid값)를 가지고 delete(id=remove)구문 실행
			for (int i = 0; i < check.length; i++) {
				vo.setSeq(Integer.parseInt(check[i].substring(0, check[i].indexOf("^"))));
				vo.setProjid(Integer.parseInt(check[i].substring(check[i].indexOf("^") + 1, check[i].length())));
				UploadFileUtils.deleteFile(service.getAttach(vo), ProjectPath.getIssue_path());
				service.issue_remove(vo);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "선택된 Note가 없습니다");
		}
		return "redirect:/project/issue/list";
	}

	/*
	 * Project List 팝업
	 *
	 * @param model
	 *
	 * @return project/issue/project_list.jsp 호출
	 */
	@RequestMapping(value = "/project_list", method = RequestMethod.GET)
	public void project_list(HttpSession session, ListCriteria lc, Model model) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", proj_service.project_list(lc));
		model.addAttribute("lc", lc);

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(proj_service.project_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);

	}

	/*
	 * Phase List 팝업
	 *
	 * @param model
	 *
	 * @return project/issue/phase_list.jsp 호출
	 */
	@RequestMapping(value = "/phase_list", method = RequestMethod.GET)
	public void phaselist(ListCriteria lc, Model model, HttpSession session) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", ph_service.phase_list(lc));
		model.addAttribute("lc", lc);

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(ph_service.phase_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/*
	 * Case List 팝업
	 *
	 * @param model
	 *
	 * @return project/issue/case_list.jsp 호출
	 */
	@RequestMapping(value = "/case_list", method = RequestMethod.GET)
	public void caselist(ListCriteria lc, Model model, HttpSession session) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", case_service.getReport(lc));
		model.addAttribute("lc", lc);

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(case_service.case_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

}
