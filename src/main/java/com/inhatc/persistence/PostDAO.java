package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;
import com.inhatc.domain.PostVO;

public interface PostDAO {
	public int create(PostVO vo);
	public HashMap<String, Object> readOne(int postSeq);
	public void update(PostVO vo);
	public void updateByDelete(int postSeq);
	public List<HashMap<String, Object>> readAllList(Page page);
	public List<HashMap<String, Object>> readAllList(Page page, String q);
	public List<HashMap<String, Object>> readAllList(Page page, int groupSeq);
	public List<HashMap<String, Object>> readAllList(Page page, int groupSeq, String q);	
	public List<HashMap<String, Object>> readTodayTopicList();
	public List<HashMap<String, Object>> readPopularPostList();
	public int countReadAllList();
	public int countReadAllList(String q);
	public int countReadAllList(int groupSeq);
	public int countReadAllList(int groupSeq, String q);
}
