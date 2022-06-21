package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@Inject
	private SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.GroupMapper";
	
	
	@Override
	public void create(GroupVO vo){
		sqlSession.insert(namespace+".create", vo);
	}
	
	
	@Override
	public HashMap<String, Object> readOne(int memberSeq, int groupSeq){
		GroupVO vo = new GroupVO();
		vo.setMemberSeq(memberSeq);
		vo.setGroupSeq(groupSeq);
		return sqlSession.selectOne(namespace+".readOne", vo);
	}
	
	@Override
	public void update(GroupVO vo){
		sqlSession.update(namespace+".update", vo);
		
	}
	
	@Override
	public void changeMaster(GroupVO vo) {
		sqlSession.update(namespace+".changeMaster", vo);
		
	}
	
	@Override
	public void updateByDelete(int groupSeq) {
		sqlSession.update(namespace+".updateByDelete", groupSeq);
	}
	
	@Override
	public List<HashMap<String, Object>> readMyList(int memberSeq) {
		return sqlSession.selectList(namespace+".readMyList",memberSeq);
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(int memberSeq) {
		return sqlSession.selectList(namespace+".readAllList", memberSeq);
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(int memberSeq, String q) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("memberSeq", memberSeq);
		hashMap.put("keyword", "%"+q+"%");
		return sqlSession.selectList(namespace+".readAllListWithKeyword", hashMap);
	}
	
	@Override
	public List<HashMap<String, Object>> readRecommendList(int memberSeq) {
		return sqlSession.selectList(namespace+".readRecommendList",memberSeq);
	}
	
	@Override
	public List<GroupVO> readJoinedGroup(int memberSeq) {
		return sqlSession.selectList(namespace+".readJoinedGroup", memberSeq);
	}
	
}
