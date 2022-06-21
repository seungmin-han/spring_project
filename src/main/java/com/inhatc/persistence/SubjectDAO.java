package com.inhatc.persistence;

import java.util.List;

import com.inhatc.domain.SubjectVO;

public interface SubjectDAO {
	public void create(SubjectVO vo);
	public void update(SubjectVO vo);
	public void deleteByUpdate(int subjectSeq);
	public List<SubjectVO> readAll(int boardSeq);
}
