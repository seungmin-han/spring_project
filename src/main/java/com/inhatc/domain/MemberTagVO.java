package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberTagVO {
	private int memberTagSeq;
	private int memberSeq;
	private String memberTagName;
}


