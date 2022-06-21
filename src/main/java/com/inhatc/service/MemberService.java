package com.inhatc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.inhatc.domain.MemberVO;

public interface MemberService {
	public void create(MemberVO vo) throws Exception;
	public MemberVO readOne(String memberId) throws Exception;
	public void update(MemberVO vo, HttpSession session) throws Exception;
	public void updateByDelete(int memberSeq) throws Exception;
	public List<MemberVO> listAll() throws Exception;
	public String login(String memberId, String memberPw, HttpServletRequest req);
}
