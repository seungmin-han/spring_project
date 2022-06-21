package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.JoinStatusVO;

@Repository
public class JoinStatusDAOImpl implements JoinStatusDAO {
	@Inject
	private SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.JoinStatusMapper";
	
	@Override
	public void create(JoinStatusVO vo){
		sqlSession.insert(namespace+".create", vo);
		
	}
	
	@Override
	public JoinStatusVO readOne(int joinStatusSeq){
		return sqlSession.selectOne(namespace+".readOne", joinStatusSeq);
	}
	
	@Override
	public JoinStatusVO readOneByFK(JoinStatusVO vo) {
		return sqlSession.selectOne(namespace+".readOneByFK", vo);
	}
	
	@Override
	public void update(JoinStatusVO vo){
		sqlSession.update(namespace+".update", vo);
		
	}
	
	@Override
	public void kick(int joinStatusSeq) {
		JoinStatusVO vo = new JoinStatusVO();
		vo.setJoinStatusSeq(joinStatusSeq);
		vo.setJoinStatusCd('N');
		sqlSession.update(namespace+".updateByPK", vo);
	}
	
	@Override
	public void accept(int joinStatusSeq) {
		JoinStatusVO vo = new JoinStatusVO();
		vo.setJoinStatusSeq(joinStatusSeq);
		vo.setJoinStatusCd('Y');
		sqlSession.update(namespace+".updateByPK", vo);
	}
	
	@Override
	public void updateByDelete(int joinStatusSeq) {
		sqlSession.update(namespace+".updateByDelete", joinStatusSeq);	
	}
	
	@Override
	public List<HashMap<String, Object>> readAll(int groupSeq){
		return sqlSession.selectList(namespace+".readAll", groupSeq);
	}
	
	@Override
	public void joinGroup(JoinStatusVO vo) {
		sqlSession.insert(namespace+".joinGroup", vo);
	}

	
}
