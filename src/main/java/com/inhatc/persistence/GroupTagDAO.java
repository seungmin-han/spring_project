package com.inhatc.persistence;

import java.util.List;

import com.inhatc.domain.GroupTagVO;

public interface GroupTagDAO {
	public void create(GroupTagVO vo) throws Exception;
	public void delete(int groupTagSeq) throws Exception;
	public List<GroupTagVO> readAll(int groupSeq) throws Exception;
}
