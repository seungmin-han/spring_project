package com.inhatc.persistence;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.GroupImageVO;

public interface GroupImageDAO {
	public void create(GroupImageVO vo) throws Exception;
	public GroupImageVO readOne(int groupImageSeq) throws Exception;
	public void update(GroupImageVO vo) throws Exception;
	public void updateByDelete(int groupImageSeq) throws Exception;
	public List<GroupImageVO> readAll(int groupSeq) throws Exception;
}
