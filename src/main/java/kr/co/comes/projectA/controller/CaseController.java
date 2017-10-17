package kr.co.comes.projectA.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.EventVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.dto.ResultVO;
import kr.co.comes.projectA.dto.UserVO;
import kr.co.comes.projectA.service.CaseService;
import kr.co.comes.projectA.service.EventService;
import kr.co.comes.projectA.service.PhaseService;
import kr.co.comes.projectA.service.ResultService;
import kr.co.comes.projectA.service.UserService;
import kr.co.comes.projectA.util.InputValidator;
import kr.co.comes.projectA.util.UploadFileUtils;

@Controller

@RequestMapping("/project/case/*")

public class CaseController {

	@Inject
	private CaseService service;
	@Inject
	private EventService e_service;
	@Inject
	private PhaseService p_service;
	@Inject
	private ResultService r_service;
	@Inject
	private UserService u_service;

	private EventVO eventVO;
	private ResultVO resultVO;

	/**
	 * 
	 * @param model
	 * @return project/case/list.jsp 호출
	 */

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void caselist(ListCriteria lc, Model model, HttpSession session) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		/*
		 * * caseMapper.xml에 있는 select구문 실행 후 CaseVO타입으로 저장된 정보 리스트를
		 * list란 이름으로 phaselist.jsp에 전달
		 */
		lc.setUser(id);
		lc.setUser_role(user_role);
		System.out.println("projid:" + lc.getProjid() + "phid:" + lc.getPhid());
		
		//project,phase abbr 구하기
		String proj_abbr = service.proj_abbr(lc.getProjid());
		String ph_abbr = service.ph_abbr(lc);

		//약어 입력안했으나 ' ' 값일 경우 띄어쓰기 제거
		proj_abbr = proj_abbr.replaceAll(" ", "");
		if (ph_abbr != null) {
			ph_abbr = ph_abbr.replaceAll(" ", "");
		}

		model.addAttribute("p_name", service.case_proj_ph_name(lc));
		model.addAttribute("proj_abbr", proj_abbr);
		model.addAttribute("ph_abbr", ph_abbr);
		model.addAttribute("list", service.case_list(lc));
		model.addAttribute("lc", lc);

		// 페이징처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.case_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/**
	 * case 생성화면
	 * 
	 * @param model
	 * @return project/case/create.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(ListCriteria lc, Model model, HttpSession session) throws Exception {

		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		lc.setUser(id);
		lc.setUser_role(user_role);

		// 해당 프로젝트 name 값 전달
		model.addAttribute("proj_name", p_service.proj_name(lc.getProjid()));
		// 해당 phase name 값 전달
		model.addAttribute("ph_name", service.ph_name(lc));
		model.addAttribute("lc", lc);

		UserVO vo = new UserVO();
		vo = u_service.viewUser(id);

		//현재 사용자의 기본 앱과 디바이스가 설정되어있을 경우 default 처리를 위한 값 넘김
		if (vo.getAppid() != null) {
			model.addAttribute("appid", service.getAppid(id));
			model.addAttribute("appName", service.getAppName(service.getAppid(id)));
		}
		if (vo.getDevid() != null) {
			model.addAttribute("devid", service.getDevid(id));
			model.addAttribute("devName", service.getDevName(service.getDevid(id)));
		}
	}

	/**
	 * case  생성화면에서 생성버튼 클릭시 작용
	 * 
	 * @param model
	 * @return project/phase/list.jsp 호출
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(String copy, String insert, ListCriteria lc, CaseVO vo, HttpSession session, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) throws Exception {
		int senid = vo.getSenid();
		
		//input data 체크
		new InputValidator().validate(vo, result, "case");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}
			System.out.println("ddd:" + resultmsg);
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			redirectAttributes.addAttribute("projid", vo.getProjid());
			redirectAttributes.addAttribute("phid", vo.getPhid());
			return "redirect:/project/case/create";
		}

		//phase가 없을경우 (skip 제외) 처리
		if (vo.getPhid() == 0) {
			redirectAttributes.addFlashAttribute("resultmsg", "Phase가 없습니다");
			redirectAttributes.addAttribute("projid", vo.getProjid());
			redirectAttributes.addAttribute("phid", vo.getPhid());
			return "redirect:/project/case/create";
		}

		System.out.println("copy:" + copy);

		// 새로이 케이스 생성하는 경우
		if (insert.equals("new")) {
			vo.setUser((String) session.getAttribute("id"));
			System.out.println("vo.getSenaabbr():"+vo.getSenaabbr());
			service.case_create(vo);
		} else { // 복사하는경우
			// 원본 케이스에 첨부 파일이 있는경우 파일 복사
			String attach = service.getAttach(vo);
			if (!service.getAttach(vo).equals("")) {
				InputStream in = new FileInputStream(ProjectPath.getCase_path() + attach);
				byte[] filebyte = IOUtils.toByteArray(in);
				String fileName = attach.substring(attach.indexOf("_") + 1);
				fileName = fileName.substring(fileName.indexOf("_") + 1);

				// 파일업로드
				String savedPath = UploadFileUtils.uploadFile(ProjectPath.getCase_path(), fileName, filebyte);

				vo.setAttach(savedPath);
			} else {
			}

			// caseMapper.xml에 있는 insert구문 실행
			vo.setUser((String) session.getAttribute("id"));
			service.case_create(vo);
			// modify 새 case로 저장클릭후 scenario copy 여부 묻는 팝업창에서 'ok' 선택했을경우 시나리오 복사
			if (copy.equals("ok")) {
				eventVO = new EventVO();
				eventVO.setProjid(vo.getProjid());
				eventVO.setSenid(senid);

				List<EventVO> eVO = e_service.getEvent(eventVO);
				for (EventVO event : eVO) {
					System.out.println(event.getImage());
					if (e_service.getImage(event).isEmpty()) {
						System.out.println("e_service.getImage(event).isEmpty()");
					} else { // 원본 시나리오에 첨부 파일이 있는경우 파일 복사
						System.out.println(event.getProjid());
						System.out.println(event.getSenid());
						System.out.println(event.getSeq());
						InputStream in = new FileInputStream(ProjectPath.getEvent_path() + event.getImage());
						byte[] filebyte = IOUtils.toByteArray(in);
						String fileName = event.getImage().substring(event.getImage().indexOf("_") + 1);
						fileName = fileName.substring(fileName.indexOf("_") + 1);

						// 파일업로드
						String savedPath = UploadFileUtils.uploadFile(ProjectPath.getEvent_path(), fileName, filebyte);
						event.setImage(savedPath);
					}
					event.setSenid(service.senid(lc));
					event.setAdduser((String) session.getAttribute("id"));
					e_service.event_create(event);
				}

			}

		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("senid", service.senid(lc));
		return "redirect:/project/case/modify";
	}

	/*
	 * * case 수정
	 * 
	 * @param model
	 * 
	 * @return project/case/modify.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(ListCriteria lc, CaseVO vo, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		
		vo = service.case_read(vo);
		vo.setProj_name(p_service.proj_name(lc.getProjid()));
		vo.setPh_name(service.ph_name(lc));

		//기본 앱,디바이스 설정 해놨을 경우 해당 앱과 디바이스 이름 검색해서 셋팅
		if (vo.getAppid() != null) {
			vo.setApp_name(service.getAppName(vo.getAppid()));
		}
		if (vo.getDevid() != null) {
			vo.setDev_name(service.getDevName(vo.getDevid()));
		}

		model.addAttribute("caseVO", vo);
	}

	/*
	 * * 프로젝트 수정화면에서 수정완료 버튼 클릭시 작용
	 * 
	 * @param model
	 * 
	 * @return project/phase/list.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(CaseVO vo, BindingResult result, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		//input data 체크
		new InputValidator().validate(vo, result, "case");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {
			//input data가 정상적으로 입력됐을 경우
			// upduser를 현재 사용자로 셋팅후 update 구문 실행
			vo.setUpduser((String) (session.getAttribute("id")));
			service.case_modify(vo);
		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		return "redirect:/project/case/modify";
	}

	/*
	 * * Case 삭제
	 * 
	 * @param model
	 * 
	 * @return project/case/list.jsp 호출
	 */

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int check[], CaseVO vo, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		
		// 전달받은 parameter 값 check(체크된 projid값)를 가지고 delete 구문 실행
		if (check != null) {
			eventVO = new EventVO();
			resultVO = new ResultVO();
			for (int id : check) {

				// 해당 시나리오들과 파일들 삭제
				eventVO.setProjid(vo.getProjid());
				eventVO.setSenid(id);
				List<EventVO> eVO = e_service.getEvent(eventVO);
				for (EventVO event : eVO) {
					if (event.getImage() != null) {
						UploadFileUtils.deleteFile(event.getImage(), ProjectPath.getEvent_path());
					}
					e_service.remove(event);
				}

				// 해당 결과들과 파일들 삭제
				resultVO.setProjid(vo.getProjid());
				resultVO.setSenid(id);
				List<ResultVO> rVO = r_service.getResult(resultVO);
				for (ResultVO result : rVO) {
					if (result.getAttach() != null) {
						UploadFileUtils.deleteFile(result.getAttach(), ProjectPath.getResult_path());
					}
					r_service.result_remove(result);
					r_service.resource_remove(result);
					r_service.replay_remove(result);
				}

				// 해당 케이스와 파일 삭제
				vo.setSenid(id);
				UploadFileUtils.deleteFile(service.getAttach(vo), ProjectPath.getCase_path());
				service.case_remove(vo);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "선택된 Case가 없습니다");
		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		return "redirect:/project/case/list";
	}
}
