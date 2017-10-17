package kr.co.comes.projectA.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.icu.util.Calendar;

import kr.co.comes.projectA.dto.ReportVO;
import kr.co.comes.projectA.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private SqlSession session;

	@Inject
	ReportService reportservice;

	String name = "kr.co.comes.projectA.reportMapper";
	
	/**
	 * @param model
	 * @param vo
	 * @return report.jsp ȣ��
	 */
	@RequestMapping(value = "/project/report")
	public String report(Model model, ReportVO vo) {
		return "report";
	}

	/**
	 * @param model
	 * @param vo
	 * @param senid
	 * @param ses
	 * ����Ʈ�� �����ִ� ������
	 * report.jsp���� �Ѿ�� ���� �̿��Ͽ� ����Ʈ ����
	 * @return report_result.jsp ȣ��
	 */
	@RequestMapping(value = "/project/report_result" )
	public String reportList(Model model, ReportVO vo, int senid, HttpSession ses) {
		System.out.println(senid);
		
		//�ۼ���
		String id = (String) ses.getAttribute("id");
		
		//�ۼ�����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		ReportVO report = (ReportVO) session.selectOne(name + ".reportList", vo);
		model.addAttribute("project", report);
		if (senid == 0) {
			System.out.println("������Ʈ ��ü ���");
			List<ReportVO> phase = session.selectList(name + ".allphaseList", vo);
			model.addAttribute("phase", phase);
			List<ReportVO> sena = session.selectList(name + ".allcaseList", vo);
			model.addAttribute("sena", sena);
			List<ReportVO> test = session.selectList(name + ".alltestList", vo);
			model.addAttribute("test", test);
			model.addAttribute("date", strToday);
			model.addAttribute("id",id);
		} else {
			ReportVO phase = session.selectOne(name + ".phaseList", vo);
			model.addAttribute("phase", phase);
			List<ReportVO> sena = session.selectList(name + ".caseList", vo);
			model.addAttribute("sena", sena);
			List<ReportVO> test = session.selectList(name + ".testList", vo);
			model.addAttribute("test", test);
			model.addAttribute("date", strToday);
			model.addAttribute("id",id);
		}
		return "/report_result";
	}
	
	/**
	 * @param model
	 * @param vo
	 * @param senid
	 * @param ses
	 * report_result���� ����Ʈ�� html�� �����ϴ� ��ư�� Ŭ���ϸ� ȣ��ȴ�.
	 * ������ ����Ʈ�� html�� ����
	 * @return project1.jsp ȣ��
	 */
	@RequestMapping(value = "/project/report_result1" )
	public String reportList1(Model model, ReportVO vo, int senid, HttpSession ses) {
		System.out.println("�ٿ�ε�");
		
		String id = (String) ses.getAttribute("id");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		ReportVO report = (ReportVO) session.selectOne(name + ".reportList", vo);
		model.addAttribute("project", report);
		if (senid == 0) {
			System.out.println("������Ʈ ��ü ���");
			List<ReportVO> phase = session.selectList(name + ".allphaseList", vo);
			model.addAttribute("phase", phase);
			List<ReportVO> sena = session.selectList(name + ".allcaseList", vo);
			model.addAttribute("sena", sena);
			List<ReportVO> test = session.selectList(name + ".alltestList", vo);
			model.addAttribute("test", test);
			model.addAttribute("date", strToday);
			model.addAttribute("id",id);
		} else {
			ReportVO phase = session.selectOne(name + ".phaseList", vo);
			model.addAttribute("phase", phase);
			List<ReportVO> sena = session.selectList(name + ".caseList", vo);
			model.addAttribute("sena", sena);
			List<ReportVO> test = session.selectList(name + ".testList", vo);
			model.addAttribute("test", test);
			model.addAttribute("date", strToday);
			model.addAttribute("id",id);
		}
		return "/report_result1";
	}

}
