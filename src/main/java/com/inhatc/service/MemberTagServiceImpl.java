package com.inhatc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.domain.MemberTagVO;
import com.inhatc.persistence.MemberTagDAO;

@Service
public class MemberTagServiceImpl implements MemberTagService {
	@Inject
	private MemberTagDAO dao;
	
	@Override
	public void create(int memberSeq, String memberTagName) throws Exception {
		try {
			MemberTagVO vo = new MemberTagVO();
			vo.setMemberSeq(memberSeq);
			vo.setMemberTagName(memberTagName);
			dao.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(int memberTagSeq) throws Exception {
		try {
			dao.delete(memberTagSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<MemberTagVO> readAll(int memberSeq) throws Exception {
		try {
			return dao.readAll(memberSeq);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
