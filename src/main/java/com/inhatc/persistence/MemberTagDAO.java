package com.inhatc.persistence;

import java.util.List;

import com.inhatc.domain.MemberTagVO;

public interface MemberTagDAO {
	public void create(MemberTagVO vo) throws Exception;
	public void delete(int memberTagSeq) throws Exception;
	public List<MemberTagVO> readAll(int memberSeq) throws Exception;
}
