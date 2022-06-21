package com.inhatc.persistence;

import java.util.List;

import com.inhatc.domain.BoardVO;


public interface BoardDAO {
	public void create(BoardVO vo);
	public void update(BoardVO vo);
	public void deleteByUpdate(int boardSeq);
	public List<BoardVO> readAll();
}

