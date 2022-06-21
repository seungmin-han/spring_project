package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class GroupImageVO {
	private int groupImageSeq;
	private String groupImageName;
	private String groupImageType;
	private String groupImagePath;
	private int groupImageSize;
	private int groupImageDelNy;
	private int groupImageThumNy;
	private int groupSeq;
	
}

