package com.inhatc.service;

import java.util.List;

import com.inhatc.domain.BoardVO;


public interface BoardService {
	public void create(BoardVO vo);
	public void update(BoardVO vo);
	public void deleteByUpdate(int boardSeq);
	public List<BoardVO> readAll();
}

