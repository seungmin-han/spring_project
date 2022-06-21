package com.inhatc.domain;

import java.io.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupVO {
	private int groupSeq;			//PK
	private String groupName;		//그룹명
	private String groupText;		//그룹소개
	private int groupJoinAcceptNy;	//그룹 자동가입 설정.
	private int groupDelNy;			//그룹 삭제 여부
	private int memberSeq;			//그룹장 유저의 member PK
}
