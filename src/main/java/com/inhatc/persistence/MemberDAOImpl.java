package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.inhatc.mapper.MemberMapper";
	
	
	@Override
	public void create(MemberVO vo) throws Exception {
		sqlSession.insert(namespace+".create", vo);
	}
	
	@Override
	public MemberVO readOne(String memberId) throws Exception {
		return sqlSession.selectOne(namespace+".readOne",memberId);	
	}
	
	@Override
	public MemberVO login(String memberId, String memberPw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("memberPw", memberPw);
		return (MemberVO)sqlSession.selectOne(namespace+".login", paramMap);
	}
	
	@Override
	public void update(MemberVO vo) throws Exception {
		System.out.println(vo);
		sqlSession.update(namespace+".update", vo);
	}
	
	@Override
	public void updateByDelete(String memberId) throws Exception {
		sqlSession.update(namespace+".updateByDelete", memberId);
	}
}
