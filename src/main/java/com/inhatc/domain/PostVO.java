package com.inhatc.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PostVO {
	private int postSeq;
	private int boardSeq;
	private int subjectSeq;
	private int memberSeq;
	private int groupSeq;
	private String postTitle;
	private Date postDate;
	private String postContent;
	private int postPublicNy;
	private int postView;
	private int postLike;
	private int postDelNy; 
	
}


