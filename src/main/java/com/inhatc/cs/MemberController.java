package com.inhatc.cs;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inhatc.domain.MemberVO;
import com.inhatc.persistence.MemberDAO;
import com.inhatc.service.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute MemberVO vo, Model model) {	
		System.out.println("call register");
		try {
			service.create(vo);
			return "redirect:/member/register/success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "redirect:/member/register";
		}	
	}
	
	@RequestMapping(value = "/register/success", method = RequestMethod.GET)
	public String registerSuccess() {
		System.out.println("call registerSuccess");
		return "registerSuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)		
	public String loginForm() {	
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, HttpServletRequest req
			) throws Exception {
		System.out.println("call login");
		String url = "redirect:/member/login";
		String msg = service.login(memberId, memberPw, req);
		req.getSession().setAttribute("msg",msg);
		if(msg == null) {
			url = "redirect:/";
		}
		return url;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm() {
		return "updateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberVO vo, HttpSession session) {
		System.out.println("call update");	
		try {
			service.update(vo, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)		
	public String logout(HttpSession session) {
		System.out.println("call logout");
		session.invalidate();
		return "redirect:/";
	}
	
}
