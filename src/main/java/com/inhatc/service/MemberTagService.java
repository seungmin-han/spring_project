package com.inhatc.service;

import java.util.List;

import com.inhatc.domain.MemberTagVO;


public interface MemberTagService {
	public void create(int memberSeq, String memberTagName) throws Exception;
	public void delete(int memberTagSeq) throws Exception;
	public List<MemberTagVO> readAll(int memberSeq) throws Exception;
}
