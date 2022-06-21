package com.inhatc.persistence;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.GroupImageVO;

@Repository
public class GroupImageDAOImpl implements GroupImageDAO {
	@Inject
	private SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.GroupImageMapper";
	
	@Override
	public void create(GroupImageVO vo) throws Exception {
		sqlSession.insert(namespace+".create", vo);
	}
	
	@Override
	public GroupImageVO readOne(int groupImageSeq) throws Exception {
		return sqlSession.selectOne(namespace+".readOne", groupImageSeq);
	}
	
	@Override
	public void update(GroupImageVO vo) throws Exception {
		sqlSession.update(namespace+".update",vo);
		
	}
	
	@Override
	public void updateByDelete(int groupImageSeq) throws Exception {
		sqlSession.update(namespace+".updateByDelete",groupImageSeq);	
	}
	
	@Override
	public List<GroupImageVO> readAll(int groupSeq) throws Exception {
		return sqlSession.selectList(namespace+".readAll", groupSeq);
	}
}

