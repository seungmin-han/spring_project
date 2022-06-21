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

import com.inhatc.domain.GroupTagVO;
import com.inhatc.domain.MemberTagVO;
import com.inhatc.persistence.MemberTagDAO;
import com.inhatc.service.GroupTagService;
import com.inhatc.service.MemberTagService;

@RequestMapping("/groupTag/*")
@EnableWebMvc
@Controller
public class GroupTagController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private GroupTagService service;
	
	
	@RequestMapping(value = "/{groupSeq}", method = RequestMethod.GET)
	@ResponseBody
	public List<GroupTagVO> getTags(@PathVariable("groupSeq") int groupSeq) {
		List<GroupTagVO> result = null;
		try {
			result = service.readAll(groupSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public int createTag(@RequestParam("groupSeq") int groupSeq, @RequestParam("groupTagName") String groupTagName) {
		int result = -1;
		try {
			service.create(groupSeq, groupTagName);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delete/{groupTagSeq}", method = RequestMethod.POST)
	@ResponseBody
	public int deleteTag(@PathVariable("groupTagSeq") int groupTagSeq) {
		int result = -1;
		try {
			service.delete(groupTagSeq);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
