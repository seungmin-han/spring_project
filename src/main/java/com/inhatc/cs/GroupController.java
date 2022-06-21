package com.inhatc.cs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.JoinStatusVO;
import com.inhatc.domain.MemberVO;
import com.inhatc.domain.Page;
import com.inhatc.persistence.GroupDAO;
import com.inhatc.persistence.GroupImageDAO;
import com.inhatc.persistence.JoinStatusDAO;
import com.inhatc.service.GroupService;
import com.inhatc.service.PostService;

@RequestMapping("/group/*")
@Controller
public class GroupController {
	@Inject
	private GroupService groupService;

	@Inject
	private PostService postService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String myGroup(HttpSession session, Model model) {
		List<HashMap<String, Object>> result = new ArrayList<>();
		try {
			result = groupService.readMyList(session);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("groupList", result);
		return "group";
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm() {
		return "createGroup";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createGroup(
			HttpServletRequest request
			, @ModelAttribute GroupVO vo
			, MultipartFile file
			, Model model) {
		
        try {
        	String msg = groupService.create(request, vo, file);
        	model.addAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/group/";
	}	
	
	@RequestMapping(value="/{groupSeq}", method=RequestMethod.GET)
	public String readGroup(
			HttpSession session
			, @PathVariable int groupSeq
			, @RequestParam(defaultValue="1") int page
			, @RequestParam(defaultValue = "") String q
			, Model model) {
		HashMap<String, Object> result = new HashMap<>();
		List<HashMap<String, Object>> postList= new ArrayList<>();
		try {
			int totalCount = postService.countReadAllListInGroup(groupSeq,q);
			Page pageInfo = new Page(page, totalCount);
			result = groupService.readOne(session, groupSeq);
			postList = postService.readAllListInGroup(groupSeq, pageInfo, q);
			model.addAttribute("result", result);
			model.addAttribute("postList", postList);
			model.addAttribute("pageInfo", pageInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "groupMain";
	}
	
	
	@RequestMapping(value="/update/{groupSeq}", method=RequestMethod.GET)
	public String groupUpdateForm(HttpSession session, @PathVariable int groupSeq, Model model) {
		HashMap<String, Object> groupData = new HashMap<>();
		List<HashMap<String, Object>> groupMemberList = new ArrayList<>();
		try {
			
			groupData = groupService.readOne(session, groupSeq);
			groupMemberList = groupService.readGroupMemberList(groupSeq);
			model.addAttribute("groupData", groupData);
			model.addAttribute("groupMemberList",groupMemberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "groupUpdateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String groupUpdate(@ModelAttribute GroupVO vo, Model model) {
		String result = "redirect:/group/update/"+vo.getGroupSeq();
		try {
			groupService.update(vo);
			result = "redirect:/group/"+vo.getGroupSeq();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String getSearchGroup(HttpSession session, Model model, @RequestParam(defaultValue="") String q) {
		List<HashMap<String, Object>> groupAllList = new ArrayList<>()
				, recommendList = new ArrayList<>();
		try {
			groupAllList = groupService.readAllList(session,q);
			recommendList = groupService.readRecommendList(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("groupList", groupAllList);
		model.addAttribute("recommendGroupList", recommendList);
		return "groupSearch";
	}	
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	@ResponseBody
	public int joinGroup(HttpSession session, Model model, @RequestParam int groupSeq) {
		int memberSeq = (int) session.getAttribute("memberSeq");
		int result = -1;
		try {
			groupService.joinGroup(memberSeq, groupSeq);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/leave", method=RequestMethod.POST)
	public String leaveTeam(@RequestParam int joinStatusSeq) {
		groupService.kickMember(joinStatusSeq);
		return "redirect:/group/";
	}
	
	@RequestMapping(value="/kick", method = RequestMethod.POST)
	@ResponseBody
	public int kickMember(@RequestParam int joinStatusSeq) {
		int result = -1;
		try {
			groupService.kickMember(joinStatusSeq);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/accept", method = RequestMethod.POST)
	@ResponseBody
	public int acceptMember(@RequestParam int joinStatusSeq) {
		int result = -1;
		try {
			groupService.acceptMember(joinStatusSeq);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/changeMaster", method = RequestMethod.POST)
	@ResponseBody
	public int changeMaster(@ModelAttribute GroupVO vo) {
		int result = -1;
		try {
			groupService.changeMaster(vo);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
