package kr.co.comes.projectA.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
public class ErrorController {


	@RequestMapping("/test")
	public String test(Model model) {
		return "error/test";
	}
	
	@RequestMapping("/send")
	public String send(Model model) {
		return "error/senderrorlog";
	}
	
	@RequestMapping("/elog")
	public String elog(Model model) {
		return "error/elog";
	}
	

	@RequestMapping(value = "/log")
	public void readlogg(Model model, HttpServletRequest request) {
		
	}

}
