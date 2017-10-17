package kr.co.comes.projectA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.EventVO;
import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.PhaseVO;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.dto.ProjectVO;
import kr.co.comes.projectA.dto.ResultVO;
import kr.co.comes.projectA.service.CaseService;
import kr.co.comes.projectA.service.EventService;
import kr.co.comes.projectA.service.IssueService;
import kr.co.comes.projectA.service.PhaseService;
import kr.co.comes.projectA.service.ResultService;
import kr.co.comes.projectA.util.InputValidator;

@Controller

@RequestMapping("/project/phase/*")

public class PhaseController {

	private static final Logger logger = LoggerFactory.getLogger(PhaseController.class);

	@Inject
	private PhaseService service;
	@Inject
	private EventService e_service;
	@Inject
	private CaseService c_service;
	@Inject
	private IssueService i_service;
	@Inject
	private ResultService r_service;

	private EventVO eventVO;
	private CaseVO caseVO;
	private IssueVO issueVO;
	private ResultVO resultVO;

	/**
	 * @param model
	 * @return project/phase/list.jsp 호출
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void phaselist(ListCriteria lc, @RequestParam("projid") int projid, Model model, HttpSession session)
			throws Exception {
		logger.info(lc.toString());
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");

		char user_role = role.charAt(0);
		
		lc.setUser(id);
		lc.setUser_role(user_role);

		/*
		 * * phaseMapper.xml에 있는 select(id=list)구문 실행 후 PhaseVO타입으로 저장된 정보 리스트를
		 * list란 이름으로 phaselist.jsp에 전달
		 */

		model.addAttribute("list", service.phase_list(lc));
		model.addAttribute("lc", lc);

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.phase_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/**
	 * Phase 생성화면
	 * 
	 * @param model
	 * @return project/phase/create.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(ProjectVO vo, ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info("show project create....");

		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);

		// 해당 프로젝트 name 값 전달
		model.addAttribute("proj_name", service.proj_name(lc.getProjid()));
		// 접근 권한있는 프로젝트 제목 리스트를 불러옴
		model.addAttribute("proj_namelist", service.proj_namelist(lc));
	}

	/**
	 * Phase 생성화면에서 생성버튼 클릭시 작용
	 * 
	 * @param model
	 * @return project/phase/list.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(String skip, PhaseVO vo, BindingResult result, ListCriteria lc, HttpSession session,
			Model model, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("project create post....");

		// projectMapper.xml에 있는 insert(id=create)구문 실행
		lc.setUser((String) session.getAttribute("id"));
		vo.setUser((String) session.getAttribute("id"));

		System.out.println(skip);
		System.out.println(lc.getUser());
		System.out.println(lc.getProjid());

		// 스킵버튼 클릭시 phid 값 -1부여
		if (skip.equals("skip")) {
			// phid가 -1인 phase 생성
			service.phase_skip(vo.getProjid());
			redirectAttributes.addAttribute("phid", -1);
		} else {
			//input data 체크
			new InputValidator().validate(vo, result, "phase");
			if (result.hasErrors()) {
				String resultmsg = "";
				for (int i = 0; i < result.getGlobalErrors().size(); i++) {
					resultmsg += result.getGlobalErrors().get(i).getCode();
				}

				redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
				redirectAttributes.addAttribute("projid", vo.getProjid());
				return "redirect:/project/phase/create";
			}

			//페이스 생성
			service.phase_create(vo);
			vo.setPhid(service.phase_phid(lc));
			redirectAttributes.addAttribute("phid", vo.getPhid());
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		return "redirect:/project/case/create";
	}

	/*
	 * * Phase 수정
	 * 
	 * @param model
	 * 
	 * @return project/phase/modify.jsp 호출
	 */

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(PhaseVO vo, HttpSession session, Model model) throws Exception {
		// 전달받은 parameter 값 projid를 가지고 select(id=read)구문 실행 후 modify.jsp페이지에 전달
		model.addAttribute(service.phase_read(vo));
	}

	/*
	 * * Phase 수정화면에서 수정완료 버튼 클릭시 작용
	 * 
	 * @param model
	 * 
	 * @return project/phase/list.jsp 호출
	 */

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(PhaseVO vo, HttpSession session, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		
		//input data 체크
		new InputValidator().validate(vo, result, "phase");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			redirectAttributes.addAttribute("projid", vo.getProjid());
			return "redirect:/project/phase/create";
		}
		
		// 현재 사용자로 upduser 셋팅 후 projectMapper.xml에 있는 update 구문 실행
		vo.setUpduser((String) (session.getAttribute("id")));
		service.phase_modify(vo);

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		return "redirect:/project/phase/modify";
	}

	/*
	 * **
	 * 
	 * Phase 삭제**
	 * 
	 * @param model
	 * 
	 * @return project/phase/list.jsp 호출
	 */

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int check[], PhaseVO vo, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		// 전달받은 parameter 값 check(체크된 projid값)를 가지고 delete(id=remove)구문 실행

		if (check != null) {
			eventVO = new EventVO();
			caseVO = new CaseVO();
			issueVO = new IssueVO();
			resultVO = new ResultVO();

			List<EventVO> eVO = new ArrayList<EventVO>();
			List<CaseVO> cVO = new ArrayList<CaseVO>();
			List<IssueVO> iVO = new ArrayList<IssueVO>();
			List<ResultVO> rVO = new ArrayList<ResultVO>();

			caseVO.setProjid(vo.getProjid());
			eventVO.setProjid(vo.getProjid());
			issueVO.setProjid(vo.getProjid());
			resultVO.setProjid(vo.getProjid());
			
			// 체크된 phse 삭제
			for (int phid : check) {
				
				// 해당 페이스 하위 case List
				caseVO.setPhid(phid);
				cVO = c_service.getCase(caseVO);

				for (CaseVO Case : cVO) {
					eventVO.setSenid(Case.getSenid());
					resultVO.setSenid(Case.getSenid());

					// 해당 케이스 하위 event,result List
					eVO = e_service.getEvent(eventVO);
					rVO = r_service.getResult(resultVO);

					// 하위 event List 삭제
					for (EventVO event : eVO) {
						// 이미지가 있을경우 파일 삭제
						if (!event.getImage().isEmpty()) {
							UploadController.deleteFile(event.getImage(), ProjectPath.getEvent_path());
						}
						e_service.remove(event);
					}

					// 하위 result List 삭제
					for (ResultVO result : rVO) {
						// 이미지가 있을경우 파일 삭제
						if (!result.getAttach().isEmpty()) {
							UploadController.deleteFile(result.getAttach(), ProjectPath.getResult_path());
						}
						r_service.result_remove(result);
						r_service.resource_remove(result);
						r_service.replay_remove(result);
					}

					// 해당 케이스와 파일 삭제
					if (!Case.getAttach().isEmpty()) {
						UploadController.deleteFile(Case.getAttach(), ProjectPath.getCase_path());
					}
					c_service.case_remove(Case);
				}

				
				// 해당 페이스 하위 issue List 삭제
				issueVO.setPhid(phid);
				iVO = i_service.getIssue(issueVO);
				for (IssueVO issue : iVO) {
					if (!issue.getAttach().isEmpty()) {
						UploadController.deleteFile(issue.getAttach(), ProjectPath.getIssue_path());
					}

					i_service.issue_remove(issue);
				}

				//phase 삭제 및 파일 삭제
				vo.setPhid(phid);
				if (!service.getAttach(vo).isEmpty()) {
					UploadController.deleteFile(service.getAttach(vo), ProjectPath.getPhase_path());
				}
				
				service.phase_remove(vo);
			}  
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "선택된 Phase가 없습니다");
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		return "redirect:/project/phase/list";
	}

}