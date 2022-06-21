package com.inhatc.cs;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inhatc.domain.GroupVO;
import com.inhatc.domain.JoinStatusVO;
import com.inhatc.domain.Page;
import com.inhatc.persistence.GroupDAO;
import com.inhatc.persistence.JoinStatusDAO;
import com.inhatc.persistence.PostDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class groupListTest {
	
	@Inject
	private PostDAO dao;
	
	
	@Test
	public void listAll() throws Exception {
		int totalCount = dao.countReadAllList(25);
		Page pageInfo = new Page(1, totalCount);
		System.out.println(dao.readAllList(pageInfo, 25));
	}
	
}
