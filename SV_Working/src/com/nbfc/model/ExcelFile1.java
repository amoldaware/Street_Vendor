package com.nbfc.model;

import org.springframework.web.multipart.MultipartFile;

public class ExcelFile1 {
	private MultipartFile file;
	private String QUARTER_NO;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getQUARTER_NO() {
		return QUARTER_NO;
	}

	public void setQUARTER_NO(String qUARTER_NO) {
		QUARTER_NO = qUARTER_NO;
	}
}
