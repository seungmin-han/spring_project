package com.inhatc.persistence;

import java.util.HashMap;
import java.util.List;

import com.inhatc.domain.JoinStatusVO;

public interface JoinStatusDAO {
	public void create(JoinStatusVO vo);
	public JoinStatusVO readOne(int joinStatusSeq);
	public JoinStatusVO readOneByFK(JoinStatusVO vo);
	public void update(JoinStatusVO vo);
	public void kick(int joinStatusSeq);
	public void accept(int joinStatusSeq);
	public void updateByDelete(int joinStatusSeq);
	public List<HashMap<String, Object>> readAll(int groupSeq);
	public void joinGroup(JoinStatusVO vo);
	
}
