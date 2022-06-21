package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PostImageVO {
	private int postImageSeq;
	private String postImageName;
	private String postImageType;
	private String postImagePath;
	private int postImageSize;
	private int postImageDelNy;
	private int postImageThumNy;
	private int postSeq;
}
