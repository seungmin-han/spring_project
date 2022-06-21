package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.inhatc.domain.Page;
import com.inhatc.domain.PostVO;

@Repository
public class PostDAOImpl implements PostDAO{
	@Inject
	SqlSession sqlSession;
	private String namespace = "com.inhatc.mapper.PostMapper";
	
	@Override
	public int create(PostVO vo) {
		int queryCd = 0;
		
		if(vo.getSubjectSeq() == 0) 
		{
			queryCd += 1;
		}
		if(vo.getPostPublicNy() == 1) 
		{
			queryCd += 2;	
		}
		
		switch (queryCd) 
		{
			case 0: 
			{
				sqlSession.insert(namespace+".create", vo);
				break;
			}
			case 1:
			{
				sqlSession.insert(namespace+".createWithoutSubjectSeq", vo);
				break;
			}
			case 2:
			{
				sqlSession.insert(namespace+".createWithoutGroupSeq", vo);
				break;
			}
			case 3:
			{
				sqlSession.insert(namespace+".createWithoutSubjectSeqAndGroupSeq", vo);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + queryCd);
		}
		
		return vo.getPostSeq();
	}
	
	
	@Override
	public List<HashMap<String, Object>> readAllList(Page page) {
		return sqlSession.selectList(namespace+".readAllList", page);
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(Page page, String q) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("listCount", page.getListCount());
		param.put("startPoint", page.getStartPoint());
		param.put("keyword", "%"+q+"%");
		return sqlSession.selectList(namespace+".readAllListWithKeyword", param);
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(Page page, int groupSeq) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("listCount", page.getListCount());
		param.put("startPoint", page.getStartPoint());
		param.put("groupSeq", groupSeq);
		
		System.out.println(param.toString());
		return sqlSession.selectList(namespace+".readAllListInGroup", param);
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(Page page, int groupSeq, String q) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("listCount", page.getListCount());
		param.put("startPoint", page.getStartPoint());
		param.put("groupSeq", groupSeq);
		param.put("keyword", "%"+q+"%");
		return sqlSession.selectList(namespace+".readAllListInGroupWithKeyword", param);
	}
	
	@Override
	public int countReadAllList() {
		return sqlSession.selectOne(namespace+".countReadAllList");
	}
	
	@Override
	public int countReadAllList(String q) {
		q = "%"+q+"%";
		return sqlSession.selectOne(namespace+".countReadAllListWithKeyword", q);
	}
	
	@Override
	public int countReadAllList(int groupSeq) {
		return sqlSession.selectOne(namespace+".countReadAllListInGroup", groupSeq);
	}
	
	@Override
	public int countReadAllList(int groupSeq, String q) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("groupSeq", groupSeq);
		param.put("keyword", q);
		return sqlSession.selectOne(namespace+".countReadAllListInGroupWithKeyword", param);
	}
	
	
	@Override
	public HashMap<String, Object> readOne(int postSeq) {
		return sqlSession.selectOne(namespace+".readOne", postSeq);
	}
	
	@Override
	public void update(PostVO vo) {
		int queryCd = 0;
		
		if(vo.getSubjectSeq() == 0) 
		{
			queryCd += 1;
		}
		if(vo.getPostPublicNy() == 1) 
		{
			queryCd += 2;	
		}
		
		switch (queryCd) 
		{
			case 0: 
			{
				sqlSession.update(namespace+".update", vo);
				break;
			}
			case 1:
			{
				sqlSession.update(namespace+".updateWithoutSubjectSeq", vo);
				break;
			}
			case 2:
			{
				sqlSession.update(namespace+".updateWithoutGroupSeq", vo);
				break;
			}
			case 3:
			{
				sqlSession.update(namespace+".updateWithoutSubjectSeqAndGroupSeq", vo);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + queryCd);
		}	
	}
	
	
	@Override
	public void updateByDelete(int postSeq) {
		sqlSession.update(namespace+".updateByDelete", postSeq);
		
	}
	
	@Override
	public List<HashMap<String, Object>> readPopularPostList() {
		return sqlSession.selectList(namespace+".readPopularPostList");
	}
	
	@Override
	public List<HashMap<String, Object>> readTodayTopicList() {
		return sqlSession.selectList(namespace+".readTodayTopicList");
	}
	
	
}
