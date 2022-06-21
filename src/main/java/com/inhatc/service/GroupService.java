package com.inhatc.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.GroupVO;

public interface GroupService {
	public String create(HttpServletRequest request, GroupVO vo, MultipartFile file);
//	public void readAll(Model model, HttpSession session);
	public HashMap<String, Object> readOne(HttpSession session, int groupSeq);
	public void update(GroupVO vo);
	public List<HashMap<String, Object>> readMyList(HttpSession session);
	public List<HashMap<String, Object>> readAllList(HttpSession session, String q);
	public List<HashMap<String, Object>> readRecommendList(HttpSession session);
	public List<HashMap<String, Object>> readGroupMemberList(int groupSeq);
	public List<GroupVO> readJoinedGroup(int memberSeq);
	public void joinGroup(int memberSeq, int groupSeq);
	public void kickMember(int joinStatusSeq);
	public void acceptMember(int joinStatusSeq);
	public void changeMaster(GroupVO vo);
//	public void updateByDelete(int groupSeq) throws Exception;
}
