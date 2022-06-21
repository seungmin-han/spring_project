package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Page {
	private int pageCount = 10;
    private int currentPage = 1;
    private int listCount = 15;
    private int totalPage;
    private int totalBlock;
    private int currentBlock;
    private int totalCount;
    private int startPage = 1;
    private int endPage;
    private int startPoint = 0;
    
    
    
    public Page(int page, int totalCount) 
    {
    	this.currentPage = page;
    	this.totalCount = totalCount;
    	
    	this.paging();
    	System.out.println(this);
    }
    
    public void paging() 
    {
    	totalPage = (int) Math.ceil((double)totalCount / listCount);
        totalBlock = (int) Math.ceil((double)totalPage / pageCount);
        
        if (totalPage <= currentPage) currentPage = totalPage;
        
        currentBlock = (int) Math.ceil((double)currentPage / pageCount);
        
        startPage = (currentBlock * pageCount) - 9;
        if (startPage <= 1) startPage = 1; 
        
        endPage =  currentBlock * pageCount;
        if (totalPage <= endPage) endPage = totalPage;
        
        startPoint = (currentPage - 1) * listCount;
        if (startPoint <= 1) startPoint = 0;
    }
}

