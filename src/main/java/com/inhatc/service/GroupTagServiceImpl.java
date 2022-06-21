package com.inhatc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.domain.GroupTagVO;
import com.inhatc.domain.MemberTagVO;
import com.inhatc.persistence.GroupTagDAO;
import com.inhatc.persistence.MemberTagDAO;

@Service
public class GroupTagServiceImpl implements GroupTagService {
	@Inject
	private GroupTagDAO dao;
	
	@Override
	public void create(int groupSeq, String groupTagName) throws Exception {
		try {
			GroupTagVO vo = new GroupTagVO();
			vo.setGroupSeq(groupSeq);
			vo.setGroupTagName(groupTagName);
			dao.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(int groupTagSeq) throws Exception {
		try {
			dao.delete(groupTagSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<GroupTagVO> readAll(int groupSeq) throws Exception {
		try {
			return dao.readAll(groupSeq);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
