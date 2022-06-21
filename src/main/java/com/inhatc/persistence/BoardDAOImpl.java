package com.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) {
		sqlSession.insert(namespace+".create", vo);
	}
	
	@Override
	public void update(BoardVO vo) {
		sqlSession.update(namespace+".update", vo);
	}
	
	@Override
	public void deleteByUpdate(int boardSeq) {
		sqlSession.update(namespace+".deleteByUpdate", boardSeq);
	}
	
	@Override
	public List<BoardVO> readAll() {
		return sqlSession.selectList(namespace+".readAll");
	}
}


