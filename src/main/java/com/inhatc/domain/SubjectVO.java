package com.inhatc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SubjectVO {
	private int subjectSeq;
	private String subjectName;
	private int subjectDelNy;
	private int boardSeq;	
}
