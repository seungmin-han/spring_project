package com.inhatc.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileUploadVO {
	private String attachPath;
	private String Filename;
	private MultipartFile upload;
	private String CKEditorFuncNum;
}
