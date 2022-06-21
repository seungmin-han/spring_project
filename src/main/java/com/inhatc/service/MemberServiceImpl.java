package com.inhatc.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inhatc.domain.MemberVO;
import com.inhatc.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject 
	MemberDAO dao;
	
	@Override
	public void create(MemberVO vo) throws Exception {
		try {
			dao.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public MemberVO readOne(String memberId) throws Exception {
		return dao.readOne(memberId);
	}
	
	@Override
	public void update(MemberVO vo, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		try {
			dao.update(vo);
			session.setAttribute("memberNickName", vo.getMemberNickName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateByDelete(int memberSeq) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<MemberVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String login(String memberId, String memberPw, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String result = null;
		try {
			MemberVO vo = dao.login(memberId, memberPw);
			if(vo != null) {
				session.setAttribute("memberId", vo.getMemberId());
				session.setAttribute("memberNickName", vo.getMemberNickName());
				session.setAttribute("memberSeq", vo.getMemberSeq());
			} else {
				result = "아이디나 비밀번호가 일치하지 않습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "DB Connection Error";
		}
		System.out.println("login callback:"+result);
		return result;
	}
	
	
}
