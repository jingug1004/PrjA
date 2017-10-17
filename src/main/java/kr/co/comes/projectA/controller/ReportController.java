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
	 * @return report.jsp 호출
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
	 * 리포트를 보여주는 페이지
	 * report.jsp에서 넘어온 값을 이용하여 리포트 생성
	 * @return report_result.jsp 호출
	 */
	@RequestMapping(value = "/project/report_result" )
	public String reportList(Model model, ReportVO vo, int senid, HttpSession ses) {
		System.out.println(senid);
		
		//작성자
		String id = (String) ses.getAttribute("id");
		
		//작성일자
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		ReportVO report = (ReportVO) session.selectOne(name + ".reportList", vo);
		model.addAttribute("project", report);
		if (senid == 0) {
			System.out.println("프로젝트 전체 출력");
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
	 * report_result에서 리포트를 html로 저장하는 버튼을 클릭하면 호출된다.
	 * 생성된 리포트를 html로 저장
	 * @return project1.jsp 호출
	 */
	@RequestMapping(value = "/project/report_result1" )
	public String reportList1(Model model, ReportVO vo, int senid, HttpSession ses) {
		System.out.println("다운로드");
		
		String id = (String) ses.getAttribute("id");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		ReportVO report = (ReportVO) session.selectOne(name + ".reportList", vo);
		model.addAttribute("project", report);
		if (senid == 0) {
			System.out.println("프로젝트 전체 출력");
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
