package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MLI_UNDERTAKEN_UPLOAD")
public class MLI_UNDERTAKEN_UPLOAD {
	@Id
	@Column(name="MLI_ID")
	private String mliId;
	@Column(name="FILE_NAME")
	private String fileName;
	@Column(name="FILE_EXTENSION")
	private String fileExtension;
	@Column(name="FILE_PATH")
	private String filePath;
	@Column(name="UPLOAD_DATE")
	private Date uploadeDate=new Date();
	@Column(name="UPLOAD_BY")
	private String uploadBy;
	public String getMliId() {
		return mliId;
	}
	public void setMliId(String mliId) {
		this.mliId = mliId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadeDate() {
		return uploadeDate;
	}
	public void setUploadeDate(Date uploadeDate) {
		this.uploadeDate = uploadeDate;
	}
	public String getUploadBy() {
		return uploadBy;
	}
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	
	
}
