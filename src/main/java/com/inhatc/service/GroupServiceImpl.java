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
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.domain.GroupImageVO;
import com.inhatc.domain.GroupVO;
import com.inhatc.domain.JoinStatusVO;
import com.inhatc.persistence.GroupDAO;
import com.inhatc.persistence.GroupImageDAO;
import com.inhatc.persistence.JoinStatusDAO;

@Service
public class GroupServiceImpl implements GroupService{
	@Inject
	private GroupDAO groupDao;				// 그룹 DAO
	@Inject
	private JoinStatusDAO joinStatusDao;	// 가입 상태 DAO 
	@Inject
	private GroupImageDAO groupImageDao;	// 그룹 이미지 DAO
	
	/**
	 * joinStatus 테이블에서 memberSeq와 같은 데이터들을 리스트로 가져와 
	 * 저장하고 그 리스트를 순회하며 해당하는 그룹의 정보를 다시 가져옴
	 */
//	@Override
//	public void readAll(Model model, HttpSession session) {
//		try {
//			int memberSeq = (int) session.getAttribute("memberSeq");
//			List<GroupVO> groupList = new ArrayList<>();
//			List<JoinStatusVO> statusList = joinStatusDao.readAll(memberSeq);
//			for(JoinStatusVO joinStatus : statusList) {
//				groupList.add(groupDao.readOne(joinStatus.getGroupSeq()));
//			}
//			model.addAttribute("groupList", groupList);
//			model.addAttribute("statusList", statusList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	public List<HashMap<String, Object>> readMyList(HttpSession session) {
		List<HashMap<String, Object>> result = new ArrayList<>();
		try {
			int memberSeq = (int) session.getAttribute("memberSeq");
			System.out.println(memberSeq);
			result = groupDao.readMyList(memberSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> readAllList(HttpSession session, String q) {
		List<HashMap<String, Object>> result = new ArrayList<>();
		try {
			int memberSeq = (int) session.getAttribute("memberSeq");
			if(q.isBlank() || q.isEmpty()) {
				result = groupDao.readAllList(memberSeq);
			} else {
				result = groupDao.readAllList(memberSeq, q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> readRecommendList(HttpSession session) {
		List<HashMap<String, Object>> result = new ArrayList<>();
		try {
			int memberSeq = (int) session.getAttribute("memberSeq");
			result = groupDao.readRecommendList(memberSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@Override
	public String create(HttpServletRequest request, GroupVO vo, MultipartFile file) {
		int memberSeq = (int) request.getSession().getAttribute("memberSeq");
		JoinStatusVO joinStatusVO = null;
		GroupImageVO groupImageVO = null;
		String result = null;
		File target = null;
		
		try {
        	groupDao.create(vo);		// 그룹 생성.	
		} catch (Exception e) {
			e.printStackTrace();
			result = "그룹 생성 실패";	
			return result;				// 실패시 메시지 반환; 
		}
		// 그룹이 정상적으로 생성되면 가입 상태를 생성.
		try {							
			joinStatusVO = new JoinStatusVO();
			joinStatusVO.setGroupSeq(vo.getGroupSeq());
			joinStatusVO.setMemberSeq(memberSeq);
			joinStatusVO.setJoinStatusCd('Y');
			joinStatusDao.create(joinStatusVO);
		} catch (Exception e) {	
			// 가입 상태를 생성하지 못하면 만든 그룹을 지움.
			groupDao.updateByDelete(vo.getGroupSeq());
			e.printStackTrace();
			result = "가입 상태 생성 실패";
			return result;				// 실패시 메시지 반환;
		}
		
		// 이미지 업로드 전 파일이 있는지 검사.
		try {
			if(file.getSize() <= 0) {
				return result;			// 없다면 DB 생성도 X
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			Date now = new Date();
			String DateString = sdf.format(now);
			
			groupImageVO = new GroupImageVO();
			String uploadPath = request.getSession().getServletContext().getRealPath("/resources/images");
			
			String fileName = DateString+file.getOriginalFilename();
			target = new File(uploadPath, fileName);			
			String filePath = target.toString();
			int fileSize = (int) file.getSize();
			String fileType = file.getContentType();
			
			// 디렉토리가 존재하지 않을 경우 생성.
	        if (! new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        
	        // 실제 파일 쓰기(write).
	        try {
	            FileCopyUtils.copy(file.getBytes(), target);
	        } catch(Exception e) {
	        // 파일 쓰기 실패시 가입 상태와 그룹을 지움.
	        	groupDao.updateByDelete(vo.getGroupSeq());
				joinStatusDao.updateByDelete(joinStatusVO.getJoinStatusSeq());
	            e.printStackTrace();
	            result = "파일 업로드 실패.";
	            return result;
	        }
	        
	        groupImageVO.setGroupImageName(fileName);
			groupImageVO.setGroupImagePath(filePath);
			groupImageVO.setGroupImageSize(fileSize);
			groupImageVO.setGroupImageType(fileType);
			groupImageVO.setGroupSeq(vo.getGroupSeq());
	        
	        groupImageDao.create(groupImageVO);
			
		} catch (Exception e) {
			// 업로드된 파일 삭제 후 가입 상태, 그룹을 지움
			if(target.exists()) target.delete(); 
			joinStatusDao.updateByDelete(joinStatusVO.getJoinStatusSeq());
			groupDao.updateByDelete(vo.getGroupSeq());
			e.printStackTrace();
			result = "그룹 이미지 생성 실패";
		} 
		
		return result;
	}
	
	
	@Override
	public HashMap<String, Object> readOne(HttpSession session, int groupSeq) {
		int memberSeq = (int) session.getAttribute("memberSeq");
		HashMap<String, Object> result = new HashMap<>();
		try {
			result = groupDao.readOne(memberSeq, groupSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void update(GroupVO vo) {
		groupDao.update(vo);
	}
	
	@Override
	public void joinGroup(int memberSeq, int groupSeq) {
		JoinStatusVO vo = new JoinStatusVO();
		vo.setMemberSeq(memberSeq);
		vo.setGroupSeq(groupSeq);
		if(joinStatusDao.readOneByFK(vo) != null) {
			joinStatusDao.update(vo);
		} else {
			joinStatusDao.joinGroup(vo);
		}
	}
	
	@Override
	public void kickMember(int joinStatusSeq) {
		joinStatusDao.kick(joinStatusSeq);
		
	}
	
	@Override
	public void acceptMember(int joinStatusSeq) {
		joinStatusDao.accept(joinStatusSeq);
		
	}
	
	@Override
	public void changeMaster(GroupVO vo) {
		groupDao.changeMaster(vo);		
	}
	
	@Override
	public List<HashMap<String, Object>> readGroupMemberList(int groupSeq) {
		return joinStatusDao.readAll(groupSeq);
	}
	
	@Override
	public List<GroupVO> readJoinedGroup(int memberSeq) {
		return groupDao.readJoinedGroup(memberSeq);
	}
	
}
