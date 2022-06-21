package com.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.GroupTagVO;

@Repository
public class GroupTagDAOImpl implements GroupTagDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.inhatc.mapper.GroupTagMapper";
	
	@Override
	public void create(GroupTagVO vo) throws Exception {		
		sqlSession.insert(namespace+".create",vo);
	}
	
	@Override
	public void delete(int groupTagSeq) throws Exception {
		sqlSession.delete(namespace+".delete",groupTagSeq);		
	}
	
	@Override
	public List<GroupTagVO> readAll(int groupSeq) throws Exception {
		return sqlSession.selectList(namespace+".readAll",groupSeq);
	}
	
}
