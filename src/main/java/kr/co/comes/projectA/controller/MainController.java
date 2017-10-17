package kr.co.comes.projectA.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import kr.co.comes.projectA.dto.EventVO;
import kr.co.comes.projectA.dto.ListCriteria;
import kr.co.comes.projectA.dto.ProjectPath;
import kr.co.comes.projectA.dto.ReplayVO;
import kr.co.comes.projectA.dto.ResourceVO;
import kr.co.comes.projectA.service.MainService;
import kr.co.comes.projectA.util.UploadFileUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	@Inject
	private MainService service;

	/**
	 * @param model
	 * @return user/login.jsp 호출
	 */
	@RequestMapping(value = "/")
	public String home(Model model, String resultmsg) {
		if (resultmsg != null) {
			model.addAttribute("resultmsg", resultmsg);
		}
		return "user/login";
	}
	
	@RequestMapping(value = "/main2")
	public void main2(Model model, String resultmsg) {
	}

	/**
	 * @param model
	 * @return user/login.jsp 호출
	 */
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpSession session) {

		session.setAttribute("id", null);
		session.setAttribute("pwd", null);
		session.setAttribute("name", null);
		session.setAttribute("role", null);
		session.setAttribute("clntid", null);
		session.setAttribute("telno", null);
		session.setAttribute("email", null);
		session.setAttribute("adddate", null);
		session.setAttribute("upddate", null);
		session.setAttribute("adduser", null);
		session.setAttribute("upduser", null);
		session.setAttribute("dest", null);

		return "redirect:/";
	}

	/**
	 * 관리자용 main 페이지
	 * 
	 * @param model
	 * @return main.jsp 호출
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpSession session, ListCriteria lc, Model model) throws Exception {
		// 로그인한 회원의 아이디
		String id = (String) session.getAttribute("id");
		// 로그인한 회원의 권한
		String role = (String) session.getAttribute("role");
		char user_role = role.charAt(0);
		/*
		 * projectMapper.xml에 있는 select(id=list)구문 실행 후 ProjectVO타입으로 저장된 정보
		 * 리스트를 list란 이름으로 list.jsp에 전달
		 */
		lc.setUser(id);
		lc.setUser_role(user_role);

		model.addAttribute("p_total", service.project_TotalCount(lc));
		model.addAttribute("p_ongoing", service.project_OngoingCount(lc));
		model.addAttribute("p_hold", service.project_HoldCount(lc));
		model.addAttribute("p_end", service.project_EndCount(lc));

		model.addAttribute("u_total", service.user_TotalCount(lc));
		model.addAttribute("u_admin", service.user_AdminCount(lc));
		model.addAttribute("u_testenginner", service.user_TestEnginnerCount(lc));
		model.addAttribute("u_reviewer", service.user_ReviewerCount(lc));

		return "main";
	}

	/*
	 * @param model
	 * 
	 * @return help_support.jsp 호출
	 */
	@RequestMapping(value = "/help_support", method = RequestMethod.GET)
	public void help_support(Model model) throws Exception {

	}

/*
	@RequestMapping(value = "/device/replay", method = RequestMethod.GET)
	public String get_replay2(Model model) throws Exception {
		return "project/result/get_replay";
	}*/

	/*
	 * @param param
	 * 
	 * @return project//device/replay.jsp 호출
	 */
	@RequestMapping(value = "/device/replay", method = RequestMethod.POST)
	public String get_replay(@RequestParam("param") String param, HttpServletRequest request) throws Exception {

		System.out.println("/device/replay_parm:" + param);

		ReplayVO vo = new ReplayVO();
		Gson gson = new Gson();
		String source = "";
		SocketHandler socket = new SocketHandler();
		String ipaddr = request.getRemoteAddr().toString();

		JSONObject json = new JSONObject(param);

		String code = (String) json.get("code");
		String msg = (String) json.get("msg");

		// code가 ok가 아닐 경우 error 메세지 웹소켓으로 전달
		if (code.equals("OK")) {
			JSONObject payload = new JSONObject(json.get("payload").toString());
			int repeatnum = (Integer) json.get("repeatnum");
			vo.setRepeatnum(repeatnum);
			vo.setParam(param);
			vo.setPayload(payload.toString());
			source = gson.toJson(vo);
			socket.doMessage(ipaddr, source);
		} else {
			vo.setMsg(msg);
			source = gson.toJson(vo);
			socket.doMessage(ipaddr, source);
		}

		return "project/result/replay";

	}

	/*
	 * Run test Monitoring Resource 값
	 * 
	 * @param param
	 * 
	 * @return project/result/get_resource.jsp 호출
	 */
	@RequestMapping(value = "/project/resource", method = RequestMethod.POST)
	public String get_resource(@RequestParam("param") String param, ResourceVO vo, HttpServletRequest request)
			throws Exception {
		System.out.println(param);

		String source = "";
		Gson gson = new Gson();
		SocketHandler socket = new SocketHandler();
		String ipaddr = request.getRemoteAddr().toString();
		int Cmin, Mmin, Bmin, Nmin = 0;
		int Cmax, Mmax, Bmax, Nmax = 0;

		List<String> cpu = new ArrayList<String>();
		List<String> memory = new ArrayList<String>();
		List<String> battery = new ArrayList<String>();
		List<String> network = new ArrayList<String>();
		List<String> action = new ArrayList<String>();

		JSONObject c_jobj = new JSONObject();
		JSONObject m_jobj = new JSONObject();
		JSONObject b_jobj = new JSONObject();
		JSONObject n_jobj = new JSONObject();
		JSONObject a_jobj = new JSONObject();

		JSONObject json = new JSONObject(param);

		String code = (String) json.get("code");
		String msg = (String) json.get("msg");

		// code가 ok가 아닐 경우 error 메세지 웹소켓으로 전달
		if (!code.equals("OK")) {
			System.out.println("doMessage start");
			EventVO msgvo = new EventVO();
			msgvo.setMsg(msg);
			source = gson.toJson(msgvo);
			System.out.println("get_resource_code:\n" + source);
			socket.doMessage(ipaddr, source);
		} else {
			// code가 ok일 경우 파싱
			JSONArray arr = new JSONArray(json.get("payload").toString());
			System.out.println(arr.toString());

			int size = arr.length();

			for (int i = 0; i < size; i++) {
				// cpu의 값들만 파싱하여 생성한 JSONObject에 값을 넣은 후 time과 함께 JSONArray에
				// 담아줌.
				if(arr.getJSONObject(i).toString().contains("action")){
					a_jobj.put("action", arr.getJSONObject(i).getString("action"));
					a_jobj.put("time", arr.getJSONObject(i).getString("time"));
					action.add(a_jobj.toString());
					System.out.println("****a_job:"+a_jobj.toString());
				}else if (arr.getJSONObject(i).toString().contains("cpu")) {
					c_jobj.put("cpu", arr.getJSONObject(i).getString("cpu"));
					c_jobj.put("time", arr.getJSONObject(i).getString("time"));
					cpu.add(c_jobj.toString());
				} else if (arr.getJSONObject(i).toString().contains("memory")) {
					m_jobj.put("memory", arr.getJSONObject(i).getString("memory"));
					m_jobj.put("time", arr.getJSONObject(i).getString("time"));
					memory.add(m_jobj.toString());
				} else if (arr.getJSONObject(i).toString().contains("battery")) {
					b_jobj.put("battery", arr.getJSONObject(i).getString("battery"));
					b_jobj.put("time", arr.getJSONObject(i).getString("time"));
					battery.add(b_jobj.toString());
				} else if (arr.getJSONObject(i).toString().contains("network")) {
					n_jobj.put("network", arr.getJSONObject(i).getString("network"));
					n_jobj.put("time", arr.getJSONObject(i).getString("time"));
					network.add(n_jobj.toString());
				}
			}

			// 리소스 모니터링 값이 있을 경우에만 해당 (최소,최대값 구해 json 형식으로 다시 만드는 작업)
			if (cpu.size() > 0) {
				JSONArray carr = new JSONArray(cpu.toString());
				JSONArray marr = new JSONArray(memory.toString());
				JSONArray barr = new JSONArray(battery.toString());
				JSONArray narr = new JSONArray(network.toString());

				Cmax = Integer.parseInt(carr.getJSONObject(0).getString("cpu"));
				Cmin = Integer.parseInt(carr.getJSONObject(0).getString("cpu"));
				String str_M = marr.getJSONObject(0).getString("memory");
				str_M = str_M.substring(0, str_M.indexOf("|"));
				str_M = str_M.trim();
				Mmax = Integer.parseInt(str_M);
				Mmin = Integer.parseInt(str_M);
				Bmax = Integer.parseInt(barr.getJSONObject(0).getString("battery"));
				Bmin = Integer.parseInt(barr.getJSONObject(0).getString("battery"));
				String str_N = narr.getJSONObject(0).getString("network");
				str_N = str_N.substring(0, str_N.indexOf("|"));
				str_N = str_N.trim();

				if (isNumeric(str_N)) {
					Nmax = Integer.parseInt(str_N);
					Nmin = Integer.parseInt(str_N);
				}

				for (int i = 0; i < carr.length(); i++) {
					if (Integer.parseInt(carr.getJSONObject(i).getString("cpu")) > Cmax)
						Cmax = Integer.parseInt(carr.getJSONObject(i).getString("cpu"));
					if (Integer.parseInt(carr.getJSONObject(i).getString("cpu")) < Cmin)
						Cmin = Integer.parseInt(carr.getJSONObject(i).getString("cpu"));
				}

				for (int i = 0; i < marr.length(); i++) {
					str_M = marr.getJSONObject(i).getString("memory");
					str_M = str_M.substring(0, str_M.indexOf("|"));
					str_M = str_M.trim();
					if (Integer.parseInt(str_M) > Mmax)
						Mmax = Integer.parseInt(str_M);
					if (Integer.parseInt(str_M) < Mmin)
						Mmin = Integer.parseInt(str_M);
				}

				for (int i = 0; i < barr.length(); i++) {
					if (Integer.parseInt(barr.getJSONObject(i).getString("battery")) > Bmax)
						Bmax = Integer.parseInt(barr.getJSONObject(i).getString("battery"));
					if (Integer.parseInt(barr.getJSONObject(i).getString("battery")) < Bmin)
						Bmin = Integer.parseInt(barr.getJSONObject(i).getString("battery"));
				}

				for (int i = 0; i < narr.length(); i++) {
					str_N = narr.getJSONObject(i).getString("network");
					str_N = str_N.substring(0, str_N.indexOf("|"));
					str_N = str_N.trim();
					if (isNumeric(str_N)) {
						if (Integer.parseInt(str_N) > Nmax)
							Nmax = Integer.parseInt(str_N);
						if (Integer.parseInt(str_N) < Nmin)
							Nmin = Integer.parseInt(str_N);
					}
				}

				System.out.println(ipaddr);

				System.out.println("CPU : " + Cmin + " ~ " + Cmax);
				System.out.println("Battery : " + Bmin + " ~ " + Bmax);
				System.out.println("Memory : " + Mmin + " ~ " + Mmax);
				System.out.println("Network : " + Nmin + " ~ " + Nmax);

				vo.setAction(action.toString());
				vo.setCpu(cpu.toString());
				vo.setMemory(memory.toString());
				vo.setBattery(battery.toString());
				vo.setNetwork(network.toString());
				vo.setCpu_min(Integer.toString(Cmin));
				vo.setCpu_max(Integer.toString(Cmax));
				vo.setMemory_min(Integer.toString(Mmin));
				vo.setMemory_max(Integer.toString(Mmax));
				vo.setBattery_min(Integer.toString(Bmin));
				vo.setBattery_max(Integer.toString(Bmax));
				vo.setNetwork_min(Integer.toString(Nmin));
				vo.setNetwork_max(Integer.toString(Nmax));
				vo.setParam(param);

				source = gson.toJson(vo);
				System.out.println("get_resource:\n" + source);

				System.out.println(vo.toString());

			}

			// 정상적으로 파싱한 json 형식의 source를 웹소켓으로 내용 전달
			if (source != null) {
				socket = new SocketHandler();
				System.out.println("doMessage start");
				socket.doMessage(ipaddr, source);
			}
		}
		return "project/result/get_resource";
	}

	/**
	 * Controller에서 scenario 이미지 받음, 받음과 동시에 서버에 파일 저장하고 웹소켓 전송(jsp)
	 * 
	 * @return project/scenario/get_image.jsp 호출
	 */
	@RequestMapping(value = "/getimage", method = RequestMethod.POST)
	public String getImage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		MultipartRequest multipartReq = (MultipartRequest) request;
		Iterator<String> it = multipartReq.getFileNames();
		String name = null;

		while (it.hasNext()) {
			String key = it.next();
			name = key.toString();
			System.out.println("multipartFileName:" + name);
		}

		MultipartFile multipartFile = multipartReq.getFile(name);

		String originalName = multipartFile.getOriginalFilename();
		System.out.println("***1:"+originalName);
		int i;
		i = originalName.indexOf("_");
		originalName = originalName.substring(i + 1); // 1.jpg
		i = originalName.indexOf("_");
		originalName = originalName.substring(i + 1); // 1.jpg
		i = originalName.indexOf("_");
		originalName = originalName.substring(i + 1); // 1.jpg
		i = originalName.indexOf(".");
		String jpg = originalName.substring(i); // .jpg
		String seq_str = originalName.substring(0, i); // 1
		
		System.out.println("***2:"+originalName);
		System.out.println("***seq_str:"+seq_str);
		int seq = Integer.parseInt(seq_str);
		seq++;
		originalName = Integer.toString(seq) + jpg;

		System.out.println("***3:"+originalName);

		String uploadPath = null;
		if (multipartFile.isEmpty()) {
			System.out.println("--------비어있는 파일---------");
		} else {
			try {
				uploadPath = UploadFileUtils.uploadFile(ProjectPath.getEvent_path(), originalName,
						multipartFile.getBytes());
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		String ipaddr = request.getRemoteAddr().toString();
		System.out.println(uploadPath);
		System.out.println(ipaddr);

		if (uploadPath != null) {
			SocketHandler socket = new SocketHandler();
			System.out.println("doMessage start");
			socket.doMessage(ipaddr, uploadPath);
		}

		return "project/scenario/get_image";
	}

	@RequestMapping(value = "/error/log", method = RequestMethod.POST)
	public void log() throws IOException {
	}

	/*
	 * 
	 * @return dbdown.jsp 호출
	 */
	@RequestMapping(value = "/dbdown")
	public void dbdown() throws IOException {
	}

	/*
	 * 
	 * @return download.jsp 호출
	 */
	@RequestMapping(value = "/download")
	public void download() throws IOException {
	}

	// Double형 체크 메소드
	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
