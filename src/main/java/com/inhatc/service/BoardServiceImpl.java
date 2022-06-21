package com.inhatc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.inhatc.domain.BoardVO;
import com.inhatc.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;
	
	@Override
	public void create(BoardVO vo) {
		dao.create(vo);	
	}
	
	public void update(BoardVO vo) {
		dao.update(vo);	
	}
	
	@Override
	public void deleteByUpdate(int boardSeq) {
		dao.deleteByUpdate(boardSeq);
	}
	
	@Override
	public List<BoardVO> readAll() {
		return dao.readAll();
	}
	
}
