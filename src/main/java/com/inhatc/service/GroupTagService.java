package com.inhatc.service;

import java.util.List;

import com.inhatc.domain.GroupTagVO;
import com.inhatc.domain.MemberTagVO;


public interface GroupTagService {
	public void create(int groupSeq, String groupTagName) throws Exception;
	public void delete(int groupTagSeq) throws Exception;
	public List<GroupTagVO> readAll(int groupSeq) throws Exception;
}
