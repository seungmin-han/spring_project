package com.inhatc.cs;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.inhatc.domain.MemberTagVO;
import com.inhatc.persistence.MemberTagDAO;
import com.inhatc.service.MemberTagService;

@RequestMapping("/memberTag/*")
@EnableWebMvc
@Controller
public class MemberTagController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MemberTagService service;
	
	
	@RequestMapping(value = "/{memberSeq}", method = RequestMethod.GET)
	@ResponseBody
	public List<MemberTagVO> getTags(@PathVariable("memberSeq") int memberSeq) {
		List<MemberTagVO> result = null;
		try {
			result = service.readAll(memberSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public int createTag(@RequestParam("memberSeq") int memberSeq, @RequestParam("memberTagName") String memberTagName) {
		int result = -1;
		try {
			service.create(memberSeq, memberTagName);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delete/{memberTagSeq}", method = RequestMethod.POST)
	@ResponseBody
	public int deleteTag(@PathVariable("memberTagSeq") int memberTagSeq) {
		int result = -1;
		try {
			service.delete(memberTagSeq);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
