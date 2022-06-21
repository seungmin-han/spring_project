package com.inhatc.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.FileUploadVO;
import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;
import com.inhatc.domain.PostVO;

public interface PostService {
	public void create(HttpSession session, PostVO vo);
	public HashMap<String, Object> readOne(int postSeq);
	public void update(PostVO vo);
	public int countReadAllList(String q);
	public int countReadAllListInGroup(int groupSeq, String q);
	public List<HashMap<String, Object>> readAllList(Page page, String q);
	public List<HashMap<String, Object>> readAllListInGroup(int groupSeq, Page page, String q);
	public List<HashMap<String, Object>> readTodayTopicList();
	public List<HashMap<String, Object>> readPopularPostList();
	public void updateByDelete(int postSeq);
	public HashMap<String, Object> imageUpload(HttpSession session, FileUploadVO file);
}
