package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;

public interface GroupDAO {
	public void create(GroupVO vo);
	public HashMap<String, Object> readOne(int memberSeq, int groupSeq);
	public void update(GroupVO vo);
	public void updateByDelete(int groupSeq);
	public void changeMaster(GroupVO vo);
	public List<HashMap<String, Object>> readMyList(int memberSeq);
	public List<HashMap<String, Object>> readAllList(int memberSeq);
	public List<HashMap<String, Object>> readAllList(int memberSeq, String q);
	public List<HashMap<String, Object>> readRecommendList(int memberSeq);
	public List<GroupVO> readJoinedGroup(int memberSeq);
	
}
