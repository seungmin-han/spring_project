package com.inhatc.cs;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inhatc.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private PostService postService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		List<HashMap<String, Object>> todayTopicList = new ArrayList<>();
		List<HashMap<String, Object>> popularPostList = new ArrayList<>();
		
		todayTopicList = postService.readTodayTopicList();
		popularPostList = postService.readPopularPostList();
		
		model.addAttribute("todayTopicList", todayTopicList);
		model.addAttribute("popularPostList", popularPostList);
		return "home";
	}
	
}
