package com.inhatc.persistence;


import java.util.List;

import com.inhatc.domain.MemberVO;

public interface MemberDAO {
	public void create(MemberVO vo) throws Exception;
	public MemberVO readOne(String memberId) throws Exception;
	public MemberVO login(String memberId, String memberPw) throws Exception;
	public void update(MemberVO vo) throws Exception;
	public void updateByDelete(String memberId) throws Exception;
}
