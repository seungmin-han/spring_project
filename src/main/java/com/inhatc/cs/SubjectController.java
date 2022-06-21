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

import com.inhatc.domain.SubjectVO;
import com.inhatc.service.SubjectService;

@RequestMapping("/admin/*")
@Controller
public class SubjectController {
	@Inject
	private SubjectService service;
	
	@RequestMapping(value="/subject", method=RequestMethod.GET)
	@ResponseBody
	public List<SubjectVO> getSubject(@RequestParam("boardSeq") int boardSeq, Model model) {
		return service.readAll(boardSeq);
	}
	
	@RequestMapping(value="/subject/create", method=RequestMethod.POST)
	public String postBoard(@ModelAttribute SubjectVO vo) {
		service.create(vo);
		return "redirect:/admin/board/";
	}
	
	@RequestMapping(value="/subject/update", method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute SubjectVO vo) {
		service.update(vo);
		return "redirect:/admin/board/";
	}
	
	@RequestMapping(value="/subject/delete", method=RequestMethod.POST)
	public String deleteBoard(@RequestParam("subjectSeq") int subjectSeq) {
		service.deleteByUpdate(subjectSeq);
		return "redirect:/admin/board/";
	}
	
	
}
