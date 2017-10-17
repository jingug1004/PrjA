package kr.co.comes.projectA.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import kr.co.comes.projectA.dto.UseProjectVO;
import kr.co.comes.projectA.dto.UserVO;
import kr.co.comes.projectA.service.CaseService;
import kr.co.comes.projectA.service.EventService;
import kr.co.comes.projectA.service.IssueService;
import kr.co.comes.projectA.service.MainService;
import kr.co.comes.projectA.service.PhaseService;
import kr.co.comes.projectA.service.ProjectService;
import kr.co.comes.projectA.service.ResultService;
import kr.co.comes.projectA.service.UserService;
import kr.co.comes.projectA.util.AES256;
import kr.co.comes.projectA.util.InputValidator;
import kr.co.comes.projectA.util.UploadFileUtils;

@Controller

@RequestMapping("/project/*")

public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Inject
	private ProjectService service;
	@Inject
	private MainService m_service;
	@Inject
	private PhaseService p_service;
	@Inject
	private EventService e_service;
	@Inject
	private CaseService c_service;
	@Inject
	private IssueService i_service;
	@Inject
	private ResultService r_service;
	@Inject
	private UserService u_service;

	private PhaseVO phaseVO;
	private EventVO eventVO;
	private IssueVO issueVO;
	private CaseVO caseVO;
	private ResultVO resultVO;
	private UseProjectVO useProjVO;

	/**
	 * 프로젝트 목록화면
	 * 
	 * @param model
	 * @return project/list.jsp 호출
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("lc") ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info(lc.toString());
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		
		lc.setUser(id);
		lc.setUser_role(user_role);
		
		/*
		 * projectMapper.xml에 있는 select(id=list)구문 실행 후 ProjectVO타입으로 저장된 정보
		 * 리스트를 list란 이름으로 list.jsp에 전달
		 */
		model.addAttribute("list", service.project_list(lc));
		System.out.println(lc.getCategory());
		
		// list.jsp에 project summary 값 전달.
		model.addAttribute("total", m_service.project_TotalCount(lc));
		model.addAttribute("ongoing", m_service.project_OngoingCount(lc));
		model.addAttribute("hold", m_service.project_HoldCount(lc));
		model.addAttribute("end", m_service.project_EndCount(lc));

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.project_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public void list2(@ModelAttribute("lc") ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info(lc.toString());
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		
		lc.setUser(id);
		lc.setUser_role(user_role);
		
		/*
		 * projectMapper.xml에 있는 select(id=list)구문 실행 후 ProjectVO타입으로 저장된 정보
		 * 리스트를 list란 이름으로 list.jsp에 전달
		 */
		model.addAttribute("list", service.project_list(lc));
		System.out.println(lc.getCategory());
		
		// list.jsp에 project summary 값 전달.
		model.addAttribute("total", m_service.project_TotalCount(lc));
		model.addAttribute("ongoing", m_service.project_OngoingCount(lc));
		model.addAttribute("hold", m_service.project_HoldCount(lc));
		model.addAttribute("end", m_service.project_EndCount(lc));

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.project_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "/list2_top", method = RequestMethod.GET)
	public void list2_top(@ModelAttribute("lc") ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info(lc.toString());
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		
		lc.setUser(id);
		lc.setUser_role(user_role);
		
		/*
		 * projectMapper.xml에 있는 select(id=list)구문 실행 후 ProjectVO타입으로 저장된 정보
		 * 리스트를 list란 이름으로 list.jsp에 전달
		 */
		
		// list.jsp에 project summary 값 전달.
		model.addAttribute("total", m_service.project_TotalCount(lc));
		model.addAttribute("ongoing", m_service.project_OngoingCount(lc));
		model.addAttribute("hold", m_service.project_HoldCount(lc));
		model.addAttribute("end", m_service.project_EndCount(lc));
	}

	/**
	 * 프로젝트 생성화면
	 * 
	 * @param model
	 * @return project/create.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(ProjectVO vo, Model model, HttpSession session) throws Exception {
		logger.info("show project create....");
	}

	/**
	 * 프로젝트 생성화면에서 생성버튼 클릭시 작용
	 * 
	 * @param model
	 * @return project/list.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(ProjectVO vo, String charge, String paticipant, BindingResult result,
			RedirectAttributes redirectAttributes, ListCriteria lc, HttpSession session) throws Exception {
		logger.info("project create post....");
		
		//input data 체크
		new InputValidator().validate(vo, result, "project");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			//에러 발생시 프로젝트 생성페이지에 resultmsg 값 전달 및 재로드
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			return "redirect:/project/create";
		}
		
		vo.setUser((String) session.getAttribute("id"));
		
		// projectMapper.xml에 있는 insert(id=create)구문 실행
		service.project_create(vo);
		
		//방금 전 생성한 project의 projid값을 projid 변수에 저장
		int projid = service.project_projid(vo.getUser());
		
		//useproj 테이블에 저장하기위해 UseProjectVO 객체화
		UseProjectVO c_use = new UseProjectVO();
		UseProjectVO p_use = new UseProjectVO();
		
		//paticipant에 들어있는 정보를 ','으로 잘라 배열 저장
		String[] salesTeamArray = paticipant.split(",");

		//charge정보를 userproj 테이블에 저장하기위해 기본 정보 셋팅 및 insert문 실행
		c_use.setAdduser((String) session.getAttribute("id"));
		c_use.setProjid(projid);
		c_use.setRole("0");
		c_use.setUserid(charge);
		service.useproj_create(c_use);
		
		//paticipant정보를 userproj 테이블에 저장하기위해 기본 정보 셋팅
		p_use.setAdduser((String) session.getAttribute("id"));
		p_use.setProjid(projid);
		p_use.setRole("1");
		for (int i = 0; i < salesTeamArray.length; i++) {
			//charge와 중복되지 않는 사용자만 insert문 실행
			if (!charge.equals(salesTeamArray[i])) {
				p_use.setUserid(salesTeamArray[i]);
				service.useproj_create(p_use);
			}
		}

		//생성이 완료되면 phase생성 페이지로 이동
		redirectAttributes.addAttribute("projid", projid);
		return "redirect:/project/phase/create";
	}

	/**
	 * 프로젝트 수정
	 * 
	 * @param model
	 * @return project/modify.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("projid") int projid, @ModelAttribute ProjectVO vo, BindingResult result,
			Model model) throws Exception {

		//해당 프로젝트의 charge와 paticipant 불러오기
		String charge = service.charge(projid);
		List<String> list = service.paticipant(projid);
		
		//participant list를 스트링형을으로 변환 ex) 결과  : [ddd ,aaa ,ddd ,]
		String paticipant = list.toString();
		
		//대괄호 및 띄어쓰기 제거
		paticipant = paticipant.replace("[", "");
		paticipant = paticipant.replace("]", "");
		paticipant = paticipant.replace(" ", "");

		model.addAttribute(service.project_read(projid));
		model.addAttribute("paticipant", paticipant);
		model.addAttribute("charge", charge);
	}

	/**
	 * 프로젝트 수정화면에서 수정완료 버튼 클릭시 작용
	 * 
	 * @param model
	 * @return project/list.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(@ModelAttribute ProjectVO vo, String charge, String paticipant, BindingResult result,
			HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		// projectMapper.xml에 있는 update(id=modify)구문 실행
		UseProjectVO use = new UseProjectVO();
		use.setProjid(vo.getProjid());

		//input data 체크
		new InputValidator().validate(vo, result, "project");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else { //에러가 나지 않을경우 수정
			
			//upduser를 현재 사용자로 셋팅해준 후 프로젝트 update 구문 실행
			vo.setUpduser((String) (session.getAttribute("id")));
			service.project_modify(vo);

			//현재 프로젝트에 저장되어있는 charge,paticipant 값 변수에 저장
			String charge_str = service.charge(vo.getProjid());
			List<String> list = service.paticipant(vo.getProjid());
			String pati = list.toString();
			pati = pati.replace("[", "");
			pati = pati.replace("]", "");
			pati = pati.replace(" ", "");

			//db에 저장되어있는 값과 현재 수정된 charge의 값이 다를 경우 update
			if (!charge_str.equals(charge)) {
				use.setUserid(charge);
				service.charge_update(use);
			}
			//paticipant에 저장되어있는 값과 현재 수정된 charge의 값이 다를 경우 update
			if (!paticipant.equals(pati)) {
				use.setRole("1");
				service.useproj_delete(use);
				String[] salesTeamArray = paticipant.split(",");
				for (int i = 0; i < salesTeamArray.length; i++) {
					if (!charge.equals(salesTeamArray[i])) {
						use.setUserid(salesTeamArray[i]);
						service.useproj_create(use);
					}
				}
			}

		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		return "redirect:/project/modify";
	}

	/**
	 * 프로젝트 삭제
	 * 
	 * @param model
	 * @return project/remove.jsp 호출
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int check[], Model model, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		// 전달받은 parameter 값 check(체크된 projid값)를 가지고 delete(id=remove)구문 실행
		if (check != null) {
			phaseVO = new PhaseVO();
			eventVO = new EventVO();
			caseVO = new CaseVO();
			issueVO = new IssueVO();
			resultVO = new ResultVO();
			useProjVO = new UseProjectVO();

			List<PhaseVO> pVO = new ArrayList<PhaseVO>();
			List<EventVO> eVO = new ArrayList<EventVO>();
			List<CaseVO> cVO = new ArrayList<CaseVO>();
			List<IssueVO> iVO = new ArrayList<IssueVO>();
			List<ResultVO> rVO = new ArrayList<ResultVO>();

			// 체크된 프로젝트 삭제
			for (int projid : check) {

				phaseVO.setProjid(projid);
				caseVO.setProjid(projid);
				eventVO.setProjid(projid);
				issueVO.setProjid(projid);
				eventVO.setProjid(projid);
				resultVO.setProjid(projid);
				useProjVO.setProjid(projid);

				// 해당 프로젝트 하위 phase List
				pVO = p_service.getPhase(phaseVO);

				for (PhaseVO phase : pVO) {

					// 해당 페이스 하위 case List
					caseVO.setPhid(phase.getPhid());
					cVO = c_service.getCase(caseVO);

					for (CaseVO Case : cVO) {

						// 해당 케이스 하위 event List
						eventVO.setSenid(Case.getSenid());
						resultVO.setSenid(Case.getSenid());
						eVO = e_service.getEvent(eventVO);
						rVO = r_service.getResult(resultVO);

						// 하위 event List 삭제
						for (EventVO event : eVO) {
							// 이미지가 있을경우 파일 삭제
							if (!event.getImage().isEmpty()) {
								// UploadController.setUploadPath("C:\\zzz\\event");
								UploadController.deleteFile(event.getImage(), ProjectPath.getEvent_path());
							}
							e_service.remove(event);
						}

						// 하위 result List 삭제
						for (ResultVO result : rVO) {
							// 이미지가 있을경우 파일 삭제
							if (!result.getAttach().isEmpty()) {
								UploadFileUtils.deleteFile(result.getAttach(), ProjectPath.getResult_path());
							}
							r_service.result_remove(result);
							r_service.resource_remove(result);
							r_service.replay_remove(result);
						}

						// 해당 케이스와 파일 삭제
						if (Case.getAttach() != null) {
							// UploadController.setUploadPath("C:\\zzz\\case");
							UploadFileUtils.deleteFile(Case.getAttach(), ProjectPath.getCase_path());
						}
						c_service.case_remove(Case);
					}

					// 해당 페이스 하위 issue List
					issueVO.setPhid(phase.getPhid());
					iVO = i_service.getIssue(issueVO);

					// 해당 노트와 파일 삭제
					for (IssueVO issue : iVO) {
						if (issue.getAttach() != null) {
							UploadFileUtils.deleteFile(issue.getAttach(), ProjectPath.getIssue_path());
						}

						i_service.issue_remove(issue);
					}

					// 해당 페이스와 페이스파일 삭제
					if (phase.getAttach() != null) {
						UploadFileUtils.deleteFile(phase.getAttach(), ProjectPath.getPhase_path());
					}
					p_service.phase_remove(phase);

				}

				// 프로젝트 삭제
				if (service.getAttach(projid) != null) {
					UploadFileUtils.deleteFile(service.getAttach(projid), ProjectPath.getProject_path());
				}
				service.project_remove(projid);
				service.useproj_delete(useProjVO);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "선택된 Project가 없습니다");
		}
		return "redirect:/project/list";
	}

	/**
	 * charge 선택
	 * 
	 * @param model
	 * @return project/charge_list.jsp 호출
	 */
	@RequestMapping(value = "/charge_list", method = RequestMethod.GET)
	public void userlist(Model model, HttpSession session) throws Exception {

		List<UserVO> list = userlist();
		model.addAttribute("list", list);
	}

	/**
	 * paticipant 선택
	 * 
	 * @param model
	 * @return project/paticipant_list.jsp 호출
	 */
	@RequestMapping(value = "/paticipant_list", method = RequestMethod.GET)
	public void paticipantlist(Model model, HttpSession session) throws Exception {

		List<UserVO> list = userlist();
		model.addAttribute("list", list);
	}
	
	/**
	 * 암호화된 DB의 USER값들을 복호화하여 출력하는 형태
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws DecoderException
	 */
	public List<UserVO> userlist() throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException, DecoderException{
		
		List<UserVO> list = u_service.list();
		//복호화 작업
		String key = "kr.co.comes.projecta";
		AES256 a256 = new AES256(key);
		URLCodec codec = new URLCodec();
		
		for(UserVO user : list){
			String encname = user.getName();
			String enctelno = user.getTelno();
			String encemail = user.getEmail();
			
			String decname = a256.decrypt(codec.decode(encname));
			String dectelno = a256.decrypt(codec.decode(enctelno));
			String decemail = a256.decrypt(codec.decode(encemail));
			
			user.setName(decname);
			user.setTelno(dectelno);
			user.setEmail(decemail);	
		}
		
		return list;
	}
}
