package com.inhatc.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.MemberTagVO;

@Repository
public class MemberTagDAOImpl implements MemberTagDAO {
	

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.inhatc.mapper.MemberTagMapper";
	
	@Override
	public void create(MemberTagVO vo) throws Exception {
		sqlSession.insert(namespace+".create",vo);
		
	}
	@Override
	public void delete(int memberTagSeq) throws Exception {
		sqlSession.delete(namespace+".delete",memberTagSeq);
		
	}
	@Override
	public List<MemberTagVO> readAll(int memberSeq) {
		return sqlSession.selectList(namespace+".readAll",memberSeq);
	}

}
