package com.inhatc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.domain.SubjectVO;
import com.inhatc.persistence.SubjectDAO;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Inject
	private SubjectDAO dao;
	
	@Override
	public void create(SubjectVO vo) {
		dao.create(vo);
	}
	
	@Override
	public void update(SubjectVO vo) {
		dao.update(vo);
	}
	
	@Override
	public void deleteByUpdate(int subjectSeq) {
		dao.deleteByUpdate(subjectSeq);
	}
	
	@Override
	public List<SubjectVO> readAll(int boardSeq) {
		return dao.readAll(boardSeq);
	}
}
