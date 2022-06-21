package com.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.SubjectVO;

@Repository
public class SubejctDAOImpl implements SubjectDAO {
	@Inject
	SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.SubjectMapper"; 
	
	@Override
	public void create(SubjectVO vo) {
		sqlSession.insert(namespace+".create", vo);
	}

	@Override
	public void update(SubjectVO vo) {
		sqlSession.insert(namespace+".update", vo);		
	}
	
	@Override
	public void deleteByUpdate(int subjectSeq) {
		sqlSession.update(namespace+".deleteByUpdate", subjectSeq);
	}
	
	@Override
	public List<SubjectVO> readAll(int boardSeq) {
		return sqlSession.selectList(namespace+".readAll", boardSeq);
	}
}
