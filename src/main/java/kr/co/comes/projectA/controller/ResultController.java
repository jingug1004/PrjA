package kr.co.comes.projectA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import kr.co.comes.projectA.dto.CaseVO;
import kr.co.comes.projectA.dto.EventVO;
import kr.co.comes.projectA.dto.GoogleChartDTO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.PageMaker;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.dto.ResultVO;
import kr.co.comes.projectA.service.CaseService;
import kr.co.comes.projectA.service.EventService;
import kr.co.comes.projectA.service.ResultService;
import kr.co.comes.projectA.util.InputValidator;

@Controller

@RequestMapping("/project/result")

public class ResultController {

	@Inject
	private ResultService service;
	@Inject
	private CaseService c_service;
	@Inject
	private EventService e_service;

	EventVO event = new EventVO();

	/**
	 * result list
	 * 
	 * @param model
	 * @return project/result/list.jsp 호출
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void result(ListCriteria lc, ResultVO vo, Model model, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);

		lc.setUser(id);
		lc.setUser_role(user_role);

		model.addAttribute("name", service.nameSelect(lc));
		model.addAttribute("list", service.result_list(lc));
		model.addAttribute("lc", lc);

		// 페이징 처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(lc);
		pageMaker.setTotalCount(service.result_listCount(lc));
		model.addAttribute("pageMaker", pageMaker);

		// project,phase,scenario abbr 구하기
		String proj_abbr = c_service.proj_abbr(lc.getProjid());
		String ph_abbr = c_service.ph_abbr(lc);
		String senaabbr = c_service.senaabbr(lc);

		// 약어 입력안했으나 ' ' 값일 경우 띄어쓰기 제거
		proj_abbr = proj_abbr.replaceAll(" ", "");
		if (ph_abbr != null)
			ph_abbr = ph_abbr.replaceAll(" ", "");
		senaabbr = senaabbr.replaceAll(" ", "");

		model.addAttribute("proj_abbr", proj_abbr);
		model.addAttribute("ph_abbr", ph_abbr);
		model.addAttribute("senaabbr", senaabbr);
	}

	/**
	 * test 수행 페이지
	 * 
	 * @param model
	 * @return project/result/test.jsp 호출
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void testGET(CaseVO vo, ListCriteria lc, Model model, HttpSession session) throws Exception {

		String filename = service.getFilename(lc);
		System.out.println(filename);

		model.addAttribute("filename", filename);
		model.addAttribute("name", service.nameSelect(lc));
		model.addAttribute("lc", lc);
		vo = c_service.case_read(vo);
		System.out.println(vo.getDevid());
		System.out.println(vo.getAppid());
		if (vo.getDevid() != null) {
			vo.setDev_name(c_service.getDevName(vo.getDevid()));
			model.addAttribute("device", e_service.getDevice(vo.getDevid()));
		}
		if (vo.getAppid() != null) {
			vo.setApp_name(c_service.getAppName(vo.getAppid()));
			model.addAttribute("app", e_service.getApp(vo.getAppid()));
		}
		System.out.println(vo.getDev_name());
		System.out.println(vo.getS_category());

		event.setProjid(vo.getProjid());
		event.setSenid(vo.getSenid());
		List<EventVO> list = e_service.event_read(event);
		model.addAttribute("caseVO", vo);
		System.out.println("vo.getS_category():" + vo.getS_category());
		if (!vo.getS_category().equals("Resource Monitoring")) {
			model.addAttribute("eventlength", list.size());
			model.addAttribute("event", list);
		}
		System.out.println(e_service.getApp(vo.getAppid()));
		System.out.println(e_service.getDevice(vo.getDevid()));

	}

	/**
	 * test 수행
	 * 
	 * @param model
	 * @return project/result/modify.jsp 호출
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String testPOST(ResultVO vo, String arr, ListCriteria lc, ResourceVO rvo, Model model, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		// input data 체크

		new InputValidator().validate(vo, result, "result");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}
			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
			redirectAttributes.addAttribute("projid", vo.getProjid());
			redirectAttributes.addAttribute("phid", vo.getPhid());
			redirectAttributes.addAttribute("senid", vo.getSenid());
			return "redirect:/project/result/test";
		}

		// adduser 셋팅후 result insert 및 resource insert
		vo.setAdduser((String) session.getAttribute("id"));
		service.result_create(vo);
		int resid = service.getResid(vo);

		if (!vo.getC_category().equals("Automation Test")) {
			rvo.setResid(resid);
			service.resource_create(rvo);
		}

		Gson gson = new Gson();
		String[] str = null;
		if (!vo.getC_category().equals("Resource Monitoring")) {
			ReplayVO rp = new ReplayVO();
			arr = arr.replaceAll("!", ","); // jsp 단에서 ','가 넘어오지않아 '!'로 치환해서
											// 보냈기때문에
											// 다시 '!'를 ',' 로 치환
			arr = arr.replaceAll("@", "\n"); // jsp 단에서 배열 구분위해 치환해둔 @를 \n으로 치환
			str = arr.split("\n"); // '\n' 기준으로 배열로 변경
			System.out.println("str:" + str);

			for (String string : str) {
				System.out.println("String::" + string);
				rp = gson.fromJson(string, ReplayVO.class); // eventVO 로 바꿈.
				rp.setProjid(vo.getProjid());
				rp.setResid(resid);
				rp.setSenid(vo.getSenid());
				service.replay_create(rp);
				System.out.println(vo.toString());
			}
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		redirectAttributes.addAttribute("resid", resid);
		return "redirect:/project/result/modify";

	}

	/**
	 * result modify 페이지
	 * 
	 * @param model
	 * @return project/result/modify.jsp 호출
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(ResultVO vo, ListCriteria lc, Model model) throws Exception {

		vo = service.read(vo);
		ResultVO name = service.nameSelect(lc);
		vo.setPhid(lc.getPhid());
		vo.setProj_name(name.getProj_name());
		vo.setPh_name(name.getProj_name());
		vo.setS_name(name.getS_name());

		model.addAttribute("resultVO", vo);
		ResourceVO rvo = service.getReource(vo);

		int repeatnum = service.getRepeatNum(vo);

		event.setProjid(vo.getProjid());
		event.setSenid(vo.getSenid());
		List<EventVO> list = e_service.event_read(event);

		if (list.size() > 0) {
			model.addAttribute("eventlength", list.size());
			model.addAttribute("event", list);

			if (repeatnum > 0) {
				repeatnum = repeatnum / list.size();
				System.out.println("repeatnum:" + repeatnum);
				model.addAttribute("repeatnum", repeatnum);
			}
		}
		// 리소스 값 있을 경우 값 넘겨줌
		if (rvo != null) {
			List<String> json = chart(rvo.getCpu(), rvo.getMemory(), rvo.getBattery(), rvo.getNetwork(),
					rvo.getAction());
			model.addAttribute("a_json", json.get(0));
			model.addAttribute("c_json", json.get(1));
			model.addAttribute("m_json", json.get(2));
			model.addAttribute("b_json", json.get(3));
			model.addAttribute("n_json", json.get(4));
			model.addAttribute("memory_max", json.get(5));
			model.addAttribute("json", "true");
		} else {
			model.addAttribute("json", "false");
		}

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(ResultVO vo, ListCriteria lc, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		// input data 체크
		new InputValidator().validate(vo, result, "result");
		if (result.hasErrors()) {
			String resultmsg = "";
			for (int i = 0; i < result.getGlobalErrors().size(); i++) {
				resultmsg += result.getGlobalErrors().get(i).getCode();
			}

			redirectAttributes.addFlashAttribute("resultmsg", resultmsg);
		} else {
			service.result_modify(vo);
		}
		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		redirectAttributes.addAttribute("resid", vo.getResid());
		return "redirect:/project/result/modify";
	}

	/*
	 * * result 삭제
	 * 
	 * @param model
	 * 
	 * @return project/result/list.jsp 호출
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(int check[], ResultVO vo, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		if (check != null) {
			// 전달받은 parameter 값 check(체크된 projid값)를 가지고 delete(id=remove)구문 실행
			for (int resid : check) {
				vo.setResid(resid);
				if (service.getAttach(vo) != null) {
					UploadController.deleteFile(service.getAttach(vo), ProjectPath.getResult_path());
				}
				service.result_remove(vo);
				service.resource_remove(vo);
				service.replay_remove(vo);
			}
		} else {
			redirectAttributes.addFlashAttribute("resultmsg", "선택된 result가 없습니다");
		}

		redirectAttributes.addAttribute("projid", vo.getProjid());
		redirectAttributes.addAttribute("phid", vo.getPhid());
		redirectAttributes.addAttribute("senid", vo.getSenid());
		return "redirect:/project/result/list";
	}

	/*
	 * replay 값 전달
	 * 
	 * @return str(테이블에 넣을 replay 결과 값 string 형으로 반환)
	 */
	@RequestMapping(value = "/table")
	@ResponseBody()
	public List<String> table(ReplayVO vo) throws Exception {
		System.out.println("table Controller ajax");
		System.out.println(vo.getProjid());
		System.out.println(vo.getSenid());
		System.out.println(vo.getResid());
		System.out.println(vo.getRepeatnum());
		List<String> list = service.getReplay(vo);
		System.out.println(list.toString());
		return service.getReplay(vo);
	}

	/*
	 * google 차트 그리기
	 * 
	 * @return str(구글 차트 그려줄 값 string 형으로 반환)
	 */
	@RequestMapping(value = "/chart")
	@ResponseBody()
	public List<String> chart(String getCpu, String getMemory, String getBattery, String getNetwork, String getAction) throws Exception {

		// GoogleChartDTO 객체화
		GoogleChartDTO action = new GoogleChartDTO();
		GoogleChartDTO cpu = new GoogleChartDTO();
		GoogleChartDTO memory = new GoogleChartDTO();
		GoogleChartDTO battery = new GoogleChartDTO();
		GoogleChartDTO network = new GoogleChartDTO();

		System.out.println("getAction:"+getAction);
		// db에 저장되어있는 값(param 값)들 변수에 저장
		String db_action = getAction;
		String db_cpu = getCpu;
		String db_memory = getMemory;
		String db_battery = getBattery;
		String db_network = getNetwork;

		String memory_max="";
		String time_str ="";
		int time,h,m;
		float s;
		// param값들 JsonArray 형식으로 변환
		JSONArray arr_action = new JSONArray(db_action);
		JSONArray arr_cpu = new JSONArray(db_cpu);
		JSONArray arr_memory = new JSONArray(db_memory);
		JSONArray arr_battery = new JSONArray(db_battery);
		JSONArray arr_network = new JSONArray(db_network);

		int row_action = arr_action.length();
		int row_cpu = arr_cpu.length();
		int row_memory = arr_memory.length();
		int row_battery = arr_battery.length();
		int row_network = arr_network.length();

		// 구글차트 Column 설정
		action.addColumn("time", "string");                             
		action.addColumn("action", "number");
		action.addColumn("act", "tooltip");
		
		cpu.addColumn("time", "string");
		cpu.addColumn("cpu 사용량(%)", "number");

		memory.addColumn("time", "string");
		memory.addColumn("memory", "number");

		battery.addColumn("time", "string");                                        
		battery.addColumn("battery 남은 용량(%)", "number");

		network.addColumn("time", "string");
		network.addColumn("송신량", "number");
		network.addColumn("수신량", "number");

		// 각각 JSONArray의 길이만큼의 row 생성
		action.createRows(row_action);
		cpu.createRows(row_cpu);
		memory.createRows(row_memory);
		battery.createRows(row_battery);
		network.createRows(row_network);

		for (int i = 0; i < row_action; i++) {
			time = Integer.parseInt(arr_action.getJSONObject(i).getString("time"));
			h = (time / 1000) / 3600;
			m = (time / 1000) / 60;
			s = ((float)time / 1000);
			System.out.println(String.format("%.1f", s));
			if(h > 0) time_str = String.format("d:d:%.1f", h,m,s);
			else if(m > 0) time_str = String.format("d:%.1f", m,s);
			else time_str = String.format("%.1f", s);
			String act = arr_action.getJSONObject(i).getString("action");
			act = act.replaceAll(" ", "");
			action.addCell(i, time_str);
			action.addCell(i, 0);
			action.addCell(i, act+"  "+"time→"+time_str);
		}

		
		// JSONArray에 담겨져있는 값 파싱하여 addCell 작업
		for (int i = 0; i < row_cpu; i++) {
			time = Integer.parseInt(arr_cpu.getJSONObject(i).getString("time"));
			
			h = (time / 1000) / 3600;
			m = (time / 1000) / 60;
			s = ((float)time / 1000);
			System.out.println(String.format("%.1f", s));
			if(h > 0) time_str = String.format("d:d:%.1f", h,m,s);
			else if(m > 0) time_str = String.format("d:%.1f", m,s);
			else time_str = String.format("%.1f", s);
			cpu.addCell(i, time_str);
			cpu.addCell(i, arr_cpu.getJSONObject(i).get("cpu"));
		}

		for (int i = 0; i < row_memory; i++) {
			time = Integer.parseInt(arr_memory.getJSONObject(i).getString("time"));
			h = (time / 1000) / 3600;
			m = (time / 1000) / 60;
			s = ((float)time / 1000);
			System.out.println(String.format("%.1f", s));
			if(h > 0) time_str = String.format("d:d:%.1f", h,m,s);
			else if(m > 0) time_str = String.format("d:%.1f", m,s);
			else time_str = String.format("%.1f", s);
			memory.addCell(i, time_str);
			String memory_str = arr_memory.getJSONObject(i).get("memory").toString();
			String str = memory_str.substring(0, memory_str.indexOf("|"));
			memory_max = memory_str.substring(memory_str.indexOf("|")+1);
			System.out.println("memory_max:"+memory_max);
			memory.addCell(i, str);
		}

		for (int i = 0; i < row_battery; i++) {
			time = Integer.parseInt(arr_battery.getJSONObject(i).getString("time"));
			h = (time / 1000) / 3600;
			m = (time / 1000) / 60;
			s = ((float)time / 1000);
			System.out.println(String.format("%.1f", s));
			if(h > 0) time_str = String.format("d:d:%.1f", h,m,s);
			else if(m > 0) time_str = String.format("d:%.1f", m,s);
			else time_str = String.format("%.1f", s);
			battery.addCell(i, time_str);
			battery.addCell(i, arr_battery.getJSONObject(i).get("battery"));
		}

		for (int i = 0; i < row_network; i++) {
			time = Integer.parseInt(arr_network.getJSONObject(i).getString("time"));
			h = (time / 1000) / 3600;
			m = (time / 1000) / 60;
			s = ((float)time / 1000);
			System.out.println(String.format("%.1f", s));
			if(h > 0) time_str = String.format("d:d:%.1f", h,m,s);
			else if(m > 0) time_str = String.format("d:%.1f", m,s);
			else time_str = String.format("%.1f", s);
			network.addCell(i, time_str);
			String network_str = arr_network.getJSONObject(i).get("network").toString();
			String str1 = network_str.substring(0, network_str.indexOf("|"));
			String str2 = network_str.substring(network_str.indexOf("|")+1);
			network.addCell(i, str1);
			network.addCell(i, str2);
		}

		Gson gson = new Gson();

		// 결과 값 모두 JSON 형식으로 변환
		String ACTIONjson = gson.toJson(action.getResult());
		String CPUjson = gson.toJson(cpu.getResult());
		String MEMORYjson = gson.toJson(memory.getResult());
		String BATTERYjson = gson.toJson(battery.getResult());
		String NETWORKjson = gson.toJson(network.getResult());

		List<String> str = new ArrayList<String>();
		// JSON 형식으로 변환한 모니터링 값들을 리스트에 추가하여 리턴
		str.add(ACTIONjson);
		str.add(CPUjson);
		str.add(MEMORYjson);
		str.add(BATTERYjson);
		str.add(NETWORKjson);
		str.add(memory_max);
		return str;
	}
}
