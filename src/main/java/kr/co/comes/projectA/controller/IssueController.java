package kr.co.comes.projectA.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.comes.projectA.dto.IssueVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.service.CaseService;
import kr.co.comes.projectA.service.IssueService;
import kr.co.comes.projectA.service.MainService;
import kr.co.comes.projectA.service.PhaseService;
import kr.co.comes.projectA.service.ProjectService;
import kr.co.comes.projectA.util.InputValidator;
import kr.co.comes.projectA.util.UploadFileUtils;

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
	 * Issue ���ȭ��
	 * 
	 * @param model
	 * @return project/issue/list.jsp ȣ��
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(ListCriteria lc, Model model, HttpSession session) throws Exception {
		logger.info(lc.toString());
		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		/*
		 * * projectMapper.xml�� �ִ� select(id=list)���� ���� �� ProjectVOŸ������ ����� ����
		 * ����Ʈ�� list�� �̸����� list.jsp�� ����
		 */

		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", service.issue_list(lc));
		model.addAttribute("lc", lc);
		// list.jsp�� project summary �� ����.
		model.addAttribute("note", m_service.NoteCount(lc));
		model.addAttribute("issue", m_service.IssueCount(lc));
		model.addAttribute("defect", m_service.DefectCount(lc));

		// ����¡ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.issue_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/**
	 * Issue ����ȭ��
	 * 
	 * @param model
	 * @return project/issue/create.jsp ȣ��
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
	 * Issue ����
	 * 
	 * @param model
	 * @return project/issue/modify.jsp ȣ��
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(HttpSession session, IssueVO vo, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) throws Exception {

		// input data üũ
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
	 * Issue ����������
	 * 
	 * @param model
	 * 
	 * @return project/issue/modify.jsp ȣ��
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(HttpSession session, IssueVO vo, Model model) throws Exception {
		vo = service.issue_read(vo);
		model.addAttribute("IssueVO", service.issue_read(vo));
	}

	/*
	 * Issue ����
	 * 
	 * @param model
	 * 
	 * @return project/issue/modify.jsp ȣ��
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(HttpSession session, IssueVO vo, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		// input data üũ
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
	 * * Issue ����
	 * 
	 * @param model
	 * 
	 * @return project/issue/list.jsp ȣ��
	 */

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(String check[], IssueVO vo, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		if (check != null) {
			// ���޹��� parameter �� check(üũ�� projid��)�� ������ delete(id=remove)���� ����
			for (int i = 0; i < check.length; i++) {
				vo.setSeq(Integer.parseInt(check[i].substring(0, check[i].indexOf("^"))));
				vo.setProjid(Integer.parseInt(check[i].substring(check[i].indexOf("^") + 1, check[i].length())));
				UploadFileUtils.deleteFile(service.getAttach(vo), ProjectPath.getIssue_path());
				service.issue_remove(vo);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "���õ� Note�� �����ϴ�");
		}
		return "redirect:/project/issue/list";
	}

	/*
	 * Project List �˾�
	 * 
	 * @param model
	 * 
	 * @return project/issue/project_list.jsp ȣ��
	 */
	@RequestMapping(value = "/project_list", method = RequestMethod.GET)
	public void project_list(HttpSession session, ListCriteria lc, Model model) throws Exception {
		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", proj_service.project_list(lc));
		model.addAttribute("lc", lc);

		// ����¡ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(proj_service.project_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);

	}

	/*
	 * Phase List �˾�
	 * 
	 * @param model
	 * 
	 * @return project/issue/phase_list.jsp ȣ��
	 */
	@RequestMapping(value = "/phase_list", method = RequestMethod.GET)
	public void phaselist(ListCriteria lc, Model model, HttpSession session) throws Exception {
		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", ph_service.phase_list(lc));
		model.addAttribute("lc", lc);

		// ����¡ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(ph_service.phase_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

	/*
	 * Case List �˾�
	 * 
	 * @param model
	 * 
	 * @return project/issue/case_list.jsp ȣ��
	 */
	@RequestMapping(value = "/case_list", method = RequestMethod.GET)
	public void caselist(ListCriteria lc, Model model, HttpSession session) throws Exception {
		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		lc.setUser(id);
		lc.setUser_role(user_role);
		model.addAttribute("list", case_service.getReport(lc));
		model.addAttribute("lc", lc);

		// ����¡ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(case_service.case_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);
	}

}
