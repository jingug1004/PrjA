package kr.co.comes.projectA.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.LoginVO;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.UserVO;
import kr.co.comes.projectA.service.MainService;
import kr.co.comes.projectA.service.UserService;
import kr.co.comes.projectA.util.AES256;
import kr.co.comes.projectA.util.InputValidator;
import kr.co.comes.projectA.util.LicenseDecoder;

/**
 * Handles requests for the application home page.
 */
/**
 * @author COMES
 *
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

	@Inject
	UserService userservice;
	@Inject
	private MainService m_service;
	@Autowired
	private SqlSession session;
	/**
	 * @param model
	 * @return user/login_check.jsp ȣ��
	 */
	@RequestMapping(value = "/login_check")
	public String logincheck(Model model, @ModelAttribute LoginVO login, BindingResult result,
			HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// mins edit : login validate
		String resultmsg = "";
		/*LicenseDecoder.initData(request);
		if(!LicenseDecoder.getSerial().equals(LicenseDecoder.getMacAdrress()) || LicenseDecoder.getTerm() > 0) {
			// ���̼����� �����Ϳ� �ٸ�
			resultmsg = "trial ������ ����Ǿ����ϴٶ��̼����� �������ֽʽÿ�.";
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			return "redirect:/";
		}*/
		new InputValidator().validate(login, result, "login");
		if (result.hasErrors()) {
			
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			return "redirect:/";
		}
		
		return "user/login_check";
	}

	/**
	 * @param model
	 * @return user/index.jsp ȣ��
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "user/index";
	}

	/**
	 * @param model
	 * @return user/regist.jsp ȣ��
	 */
	@RequestMapping(value = "/regist")
	public String regist(Model model) {

		return "user/regist";
	}

	/**
	 * @param vo
	 * @return redirect�� ���ؼ� regist_insert�� ���� �� �� user/list�� �ٽ� �̵��ϵ��� �Ѵ�.
	 * ��/��ȣȭ�� ���� Exception�߰�
	 * @throws GeneralSecurityException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws EncoderException 
	 */
	@RequestMapping(value = "/regist_insert")
	public String regist_insert(Model model,UserVO vo, BindingResult result,HttpServletRequest request, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException, EncoderException {

		LicenseDecoder.initData(request);
		
		String name = "kr.co.comes.projectA.userMapper";
		String maxuser = LicenseDecoder.getMaxuser();
		
		// cuser -> DB�� ����� user�� ����
		System.out.println(session.selectOne(name + ".listCount"));
		int cuser = session.selectOne(name + ".listCount");
		// user -> ���̼������Ͽ� �ִ� maxuser�� ��
		int user = Integer.parseInt(maxuser);
		String userid = vo.getId();

		//DB�� ����� user�� ���� ���̼����� ����� maxuser�� ���� ���ؼ� ���̼����� ����� ������ ������ ȸ�������� �Ұ����� ����
		if(cuser < user){
			new InputValidator().validate(vo, result, "user");
			if (result.hasErrors()) {
				String resultmsg = "";
				for (int i = 0; i < result.getGlobalErrors().size(); i++) {
					resultmsg += result.getGlobalErrors().get(i).getCode();
				}

				redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
				return "redirect:/user/regist";
			}
			//��/��ȣȭ�� ���� Ű������
			String key = "kr.co.comes.projecta";
			AES256 a256 = new AES256(key);
			URLCodec codec = new URLCodec();
			
			//��ȣȭ �� �Էµ� �������� �����͸� �������
			String userpwd = vo.getPwd();
			String username = vo.getName();
			String usertelno = vo.getTelno();
			String useremail = vo.getEmail();
			String userrole = vo.getRole();
			
			//��й�ȣ, �̸�, ��ȭ��ȣ, �̸����� ��ȣȭó��
			String encpwd = codec.encode(a256.encrypt(""+userpwd));
			String encname = codec.encode(a256.encrypt(username));
			String enctelno = codec.encode(a256.encrypt(""+usertelno));
			String encemail = codec.encode(a256.encrypt(""+useremail));
			
			//��ȣȭ�� ������ DB�� �����ϱ� ���� vo�� ���
			vo.setId(userid);
			vo.setPwd(encpwd);
			vo.setName(encname);
			vo.setTelno(enctelno);
			vo.setEmail(encemail);
			vo.setRole(userrole);
			
			//������ ����
			userservice.insertUser(vo);
			redirectAttributes.addAttribute("id", userid);
			System.out.println(userid);
			return "redirect:/user/modify";
		}else{
			//maxuser�� ���Ͽ� ȸ�������� �Ұ��� �Ҷ�
			redirectAttributes.addFlashAttribute("resultmsg","ȸ�������� ��ҵǾ����ϴٶ��̼����� maxuser�� Ȯ�����ּ���");
			redirectAttributes.addAttribute("id", userid);
			return "redirect:/user/regist";
		}
	}

	/**
	 * @param model
	 *            List<UserVO> list = userservice.userList(); userlist ������ ������
	 *            �����͸� ����Ʈ ���·� �ְ� model.addAttribute("list", list);
	 *            addAttribute�� ���ؼ� list��� ��ü�� "list"�� ���
	 * 
	 * @return user/list.jsp ȣ��
	 */
	@RequestMapping(value = "/list")
	public void user_list(Model model, ListCriteria lc, HttpSession session) throws Exception {

		// �α����� ȸ���� ���̵�
		String id = (String) session.getAttribute("id");
		// �α����� ȸ���� ����
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
			/*
			 * projectMapper.xml�� �ִ� select(id=list)���� ���� �� ProjectVOŸ������ ����� ����
			 * ����Ʈ�� list�� �̸����� list.jsp�� ����
			 */
			List<UserVO> list = userservice.userList(lc);
			
			System.out.println(list + " <--- list���");
			
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
			
			model.addAttribute("list", list);

			lc.setUser(id);
			lc.setUser_role(user_role);

			model.addAttribute("total", m_service.user_TotalCount(lc));
			model.addAttribute("admin", m_service.user_AdminCount(lc));
			model.addAttribute("testenginner", m_service.user_TestEnginnerCount(lc));
			model.addAttribute("reviewer", m_service.user_ReviewerCount(lc));

			// ����¡ó��
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(lc);
			pageMaker.setTotalCount(userservice.listCount(lc));
			model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String delete(String check[], RedirectAttributes redirectAttributes) throws Exception {
		if (check != null) {
			for (String id : check) {
				userservice.deleteUser(id);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "���õ� User�� �����ϴ�");
		}
		return "redirect:/user/list";
	}

	/**
	 * @param id
	 *            viewUser�������� where���� ã�� id ����
	 * @param model
	 * @return user/change.jsp ȣ��
	 */
	@RequestMapping("/change")
	public String userView(String id, Model model) {
		UserVO vo = userservice.viewUser(id);
		model.addAttribute("dto", vo);
		return "user/change";
	}

	/**
	 * @param vo
	 *            ModelAttribute�� ������ �������� �信 ���� updateUser�� ���ؼ� �� �÷��� �� ����
	 * @return user/change���� �����̷�Ʈ
	 */
	@RequestMapping("/update")
	public String userUpdate(@ModelAttribute UserVO vo, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) {

		session.setAttribute("name", vo.getName());

		// input data üũ
		new InputValidator().validate(vo, result, "user");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {
			userservice.updateUser(vo);
		}
		redirectAttributes.addAttribute("id", vo.getId());
		return "redirect:/user/change";
	}

	/**
	 * @param vo
	 *            ModelAttribute�� ������ �������� �信 ���� updateUser�� ���ؼ� �� �÷��� �� ����
	 * @return user/list���� �����̷�Ʈ
	 * @throws DecoderException 
	 * @throws GeneralSecurityException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(Model model, String id) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException, DecoderException {
		UserVO vo = userservice.userOne(id);
		System.out.println(id);
		System.out.println(vo);
		
		String key = "kr.co.comes.projecta";
		AES256 a256 = new AES256(key);
		URLCodec codec = new URLCodec();
		
		System.out.println(vo.getPwd());
		String encpwd = vo.getPwd();
		String encname = vo.getName();
		String enctelno = vo.getTelno();
		String encemail = vo.getEmail();
		
		String decpwd = a256.decrypt(codec.decode(encpwd));
		String decname = a256.decrypt(codec.decode(encname));
		String dectelno = a256.decrypt(codec.decode(enctelno));
		String decemail = a256.decrypt(codec.decode(encemail));
		
		vo.setPwd(decpwd);
		vo.setName(decname);
		vo.setTelno(dectelno);
		vo.setEmail(decemail);
		
		model.addAttribute("userVO", vo);
	}

	/**
	 * @param vo
	 *            ModelAttribute�� ������ �������� �信 ���� updateUser�� ���ؼ� �� �÷��� �� ����
	 * @return user/list���� �����̷�Ʈ
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws EncoderException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(@ModelAttribute UserVO vo, BindingResult result, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, NoSuchAlgorithmException, EncoderException, GeneralSecurityException {

		new InputValidator().validate(vo, result, "user");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {
			//
			String key = "kr.co.comes.projecta";
			AES256 a256 = new AES256(key);
			URLCodec codec = new URLCodec();
			
			String userid = vo.getId();
			String userpwd = vo.getPwd();
			String username = vo.getName();
			String usertelno = vo.getTelno();
			String useremail = vo.getEmail();
			
			String encpwd = codec.encode(a256.encrypt(""+userpwd));
			String encname = codec.encode(a256.encrypt(username));
			String enctelno = codec.encode(a256.encrypt(""+usertelno));
			String encemail = codec.encode(a256.encrypt(""+useremail));
			
			System.out.println(encpwd);
			System.out.println(encname);
			System.out.println(enctelno);
			System.out.println(encemail);
			
			vo.setId(userid);
			vo.setPwd(encpwd);
			vo.setName(encname);
			vo.setTelno(enctelno);
			vo.setEmail(encemail);
			
			userservice.updateUser(vo);
		}
		redirectAttributes.addAttribute("id", vo.getId());
		return "redirect:/user/modify";
	}

}
