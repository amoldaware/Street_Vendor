package com.nbfc.bean;

import java.io.Serializable;

public class BankMandateFormBo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String bankId;
	private String milId;
	private String mlIName;
	private String mliContactNo;
	private String mliMobileNo;
	private String mliEmailId;
	private String accName;
	private String bankName;
	private String branch;
	private String accNum;
	private String typeOfAcc;
	private String ifscCode;
	private String uploadDoc;
	private String mRemarks;
	private String cRemarks;
	private String cgtmsRemarks;
	private String declaration;
	private String loginId;
	private byte[] fileData;
	private String approveStatus;
	private String fileName;
	
	
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCgtmsRemarks() {
		return cgtmsRemarks;
	}
	public void setCgtmsRemarks(String cgtmsRemarks) {
		this.cgtmsRemarks = cgtmsRemarks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getMilId() {
		return milId;
	}
	public void setMilId(String milId) {
		this.milId = milId;
	}
	public String getMlIName() {
		return mlIName;
	}
	public void setMlIName(String mlIName) {
		this.mlIName = mlIName;
	}
	public String getMliContactNo() {
		return mliContactNo;
	}
	public void setMliContactNo(String mliContactNo) {
		this.mliContactNo = mliContactNo;
	}
	public String getMliMobileNo() {
		return mliMobileNo;
	}
	public void setMliMobileNo(String mliMobileNo) {
		this.mliMobileNo = mliMobileNo;
	}
	public String getMliEmailId() {
		return mliEmailId;
	}
	public void setMliEmailId(String mliEmailId) {
		this.mliEmailId = mliEmailId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getTypeOfAcc() {
		return typeOfAcc;
	}
	public void setTypeOfAcc(String typeOfAcc) {
		this.typeOfAcc = typeOfAcc;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getUploadDoc() {
		return uploadDoc;
	}
	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	public String getmRemarks() {
		return mRemarks;
	}
	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}
	public String getcRemarks() {
		return cRemarks;
	}
	public void setcRemarks(String cRemarks) {
		this.cRemarks = cRemarks;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
