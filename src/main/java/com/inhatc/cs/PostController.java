package com.inhatc.cs;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.BoardVO;
import com.inhatc.domain.FileUploadVO;
import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;
import com.inhatc.domain.PostVO;
import com.inhatc.service.BoardService;
import com.inhatc.service.GroupService;
import com.inhatc.service.PostService;

@RequestMapping("/post/*")
@Controller
public class PostController {
	@Inject
	private PostService postService;
	@Inject
	private BoardService boardService;
	@Inject
	private GroupService groupService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String community(Model model
			, @RequestParam(defaultValue="1") int page
			, @RequestParam(defaultValue = "") String q) 
	{
		int totalCount = postService.countReadAllList(q);
		Page pageInfo = new Page(page, totalCount);
		model.addAttribute("postList", postService.readAllList(pageInfo,q));
		model.addAttribute("pageInfo", pageInfo);
		return "community";
	}
	
	@RequestMapping(value="/{postSeq}", method=RequestMethod.GET)
	public String viewPost(Model model
			, @RequestParam(defaultValue="1") int page
			, @RequestParam(defaultValue = "") String q 
			, @PathVariable int postSeq) 
	{
		model.addAttribute("postData", postService.readOne(postSeq));
		model.addAttribute("page", page);
		model.addAttribute("q", q);
		return "view";
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(HttpSession session
			, Model model) 
	{
		int memberSeq = (int)session.getAttribute("memberSeq");
		List<BoardVO> boardList = boardService.readAll();
		List<GroupVO> groupList = groupService.readJoinedGroup(memberSeq);
		model.addAttribute("boardList", boardList);
		model.addAttribute("groupList", groupList);
		
		return "postForm";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPost(HttpSession session
			, Model model
			, @ModelAttribute PostVO vo) 
	{
		postService.create(session, vo);
		return "redirect:/post/";
	}
	
	@RequestMapping(value = "/update/{postSeq}", method = RequestMethod.GET)
	public String createForm(HttpSession session
			, Model model
			,@PathVariable int postSeq
			, @RequestParam(defaultValue = "1") int page
			, @RequestParam(defaultValue = "") String q) 
	{
		int memberSeq = (int) session.getAttribute("memberSeq");
		List<BoardVO> boardList = boardService.readAll();
		List<GroupVO> groupList = groupService.readJoinedGroup(memberSeq);
		model.addAttribute("boardList", boardList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("postData", postService.readOne(postSeq));
		model.addAttribute("page", page);
		model.addAttribute("q", q);
		return "postUpdateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(Model model
			, @ModelAttribute PostVO vo
			, @RequestParam("page") int page
			, @RequestParam("q") String q) 
	{
		postService.update(vo);
		return "redirect:/post/"+vo.getPostSeq()+"?page="+page+"&q="+q;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePost(Model model
			, @RequestParam("postSeq") int postSeq
			, @RequestParam("page") int page
			, @RequestParam("q") String q) 
	{
		postService.updateByDelete(postSeq);
		return "redirect:/post/?page="+page+"&q="+q;
	}
	
	@ResponseBody
	@RequestMapping(value="/image/upload", method = RequestMethod.POST)
	public HashMap<String, Object> postImageUpload(HttpSession session, FileUploadVO file) 
	{
		HashMap<String, Object> result = new HashMap<>();
		
		try 
		{
			result = postService.imageUpload(session, file);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
}
