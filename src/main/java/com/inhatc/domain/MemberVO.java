package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberVO {
	private int memberSeq;
	private String memberFirstName;
	private String memberLastName;
	private String memberId;
	private String memberPw;
	private String memberNickName;
	private String  memberEmail;
	private String memberDob;
	private char memberGenderCd;
	private int memberDelNy;
	private int memberAdminNy;
}

