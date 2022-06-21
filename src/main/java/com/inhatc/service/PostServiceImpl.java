package com.inhatc.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.FileUploadVO;
import com.inhatc.domain.GroupVO;
import com.inhatc.domain.Page;
import com.inhatc.domain.PostImageVO;
import com.inhatc.domain.PostVO;
import com.inhatc.persistence.PostDAO;


@Service
public class PostServiceImpl implements PostService {
	
	@Inject
	private PostDAO dao;
	
	@Override
	public void create(HttpSession session, PostVO vo) {
		int memberSeq = (int) session.getAttribute("memberSeq");
		vo.setMemberSeq(memberSeq);
		dao.create(vo);
	}
	
	@Override
	public int countReadAllList(String q) {
		int result = 0;
		if(q.isBlank() || q.isEmpty()) {
			result = dao.countReadAllList();
		} else {
			result = dao.countReadAllList(q);
		}
		return result;
	}
	
	public int countReadAllListInGroup(int groupSeq, String q) {
		int result = 0;
		if(q.isBlank() || q.isEmpty()) {
			result = dao.countReadAllList(groupSeq);
		} else {
			result = dao.countReadAllList(groupSeq, q);
		}
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(Page page, String q) {
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		if(q.isBlank() || q.isEmpty()) {
			result = dao.readAllList(page);
		} else {
			result = dao.readAllList(page, q);
		}
		
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> readAllListInGroup(int groupSeq,Page page, String q) {
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		if(q.isBlank() || q.isEmpty()) {
			result = dao.readAllList(page, groupSeq);
		} else {
			result = dao.readAllList(page, groupSeq, q);
		}
		
		return result;
	}
	
	@Override
	public HashMap<String, Object> readOne(int postSeq) {
		return dao.readOne(postSeq);
	}
	
	@Override
	public void update(PostVO vo) {
		dao.update(vo);
		
	}
	
	@Override
	public void updateByDelete(int postSeq) {
		dao.updateByDelete(postSeq);
		
	}
	
	@Override
	public HashMap<String, Object> imageUpload(HttpSession session, FileUploadVO file) {
		HashMap<String, Object> result = new HashMap<>();
		List<PostImageVO> imgs = new ArrayList<>();
		File target = null;
		MultipartFile mf = file.getUpload();
		try {
			if(mf == null) {
				return result;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			Date now = new Date();
			String DateString = sdf.format(now);
			
			PostImageVO postImageVO = new PostImageVO();
			String uploadPath = session.getServletContext().getRealPath("/resources/images/");
			
			String fileName = DateString+mf.getOriginalFilename();
			target = new File(uploadPath, fileName);			
			String filePath = target.toString();
			int fileSize = (int) mf.getSize();
			String fileType = mf.getContentType();
			
			// 디렉토리가 존재하지 않을 경우 생성.
	        if (! new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        
	        // 실제 파일 쓰기(write).
	        try {
	            FileCopyUtils.copy(mf.getBytes(), target);
	            String relativeFilePath = "/resources/images/"+fileName;
	            result.put("fileName", fileName);
	            result.put("uploaded", 1);
	            result.put("url", relativeFilePath);
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        
	        postImageVO.setPostImageName(fileName);
			postImageVO.setPostImagePath(filePath);
			postImageVO.setPostImageSize(fileSize);
			postImageVO.setPostImageType(fileType);
			
			if(session.getAttribute("imgs") != null) {
				imgs = (List<PostImageVO>) session.getAttribute("imgs");
			}
			imgs.add(postImageVO);
			session.setAttribute("imgs", imgs);
			
		} catch (Exception e) {
			// 업로드된 파일 삭제 후 가입 상태, 그룹을 지움
			e.printStackTrace();			
		} 
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> readPopularPostList() {
		return dao.readPopularPostList();
	}
	
	@Override
	public List<HashMap<String, Object>> readTodayTopicList() {
		return dao.readTodayTopicList();
	}
	
}
