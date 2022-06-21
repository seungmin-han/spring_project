package com.inhatc.cs;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inhatc.domain.BoardVO;
import com.inhatc.service.BoardService;
import com.inhatc.service.PostService;

@RequestMapping("/admin/*")
@Controller
public class BoardController {
	@Inject
	private BoardService service;
	
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String getBoard(Model model) {
		model.addAttribute("boardList", service.readAll());
		return "boardForm";
	}
	
	@RequestMapping(value="/board/create", method=RequestMethod.POST)
	public String postBoard(@ModelAttribute BoardVO vo) {
		service.create(vo);
		return "redirect:/admin/board/";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute BoardVO vo) {
		service.update(vo);
		return "redirect:/admin/board/";
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String deleteBoard(@RequestParam("boardSeq") int boardSeq) {
		service.deleteByUpdate(boardSeq);
		return "redirect:/admin/board/";
	}
	
}
