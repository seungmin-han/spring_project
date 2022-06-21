package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JoinStatusVO {
	private int joinStatusSeq;	// PK
	private char joinStatusCd; 	// N: 추방,Y: 가입, W: 대기
	private int memberSeq; 		// member PK (FK)
	private int groupSeq; 		// group PK (FK)
	
}
