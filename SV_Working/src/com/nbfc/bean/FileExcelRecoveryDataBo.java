package com.nbfc.bean;

import java.io.Serializable;

public class FileExcelRecoveryDataBo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String pmsNumber;
	private String loanAcountNo;
	private String dateOfNpa;
	private String outstandingAmountNpa;
	private Integer uploadId;
	private String userId;
	private String memberBnkId;
	private String recoveryAmt;
	private Long recoveryUploadId;
	
	
	
	
	
	public Long getRecoveryUploadId() {
		return recoveryUploadId;
	}
	public void setRecoveryUploadId(Long recoveryUploadId) {
		this.recoveryUploadId = recoveryUploadId;
	}
	public String getPmsNumber() {
		return pmsNumber;
	}
	public void setPmsNumber(String pmsNumber) {
		this.pmsNumber = pmsNumber;
	}
	public String getLoanAcountNo() {
		return loanAcountNo;
	}
	public void setLoanAcountNo(String loanAcountNo) {
		this.loanAcountNo = loanAcountNo;
	}
	public String getDateOfNpa() {
		return dateOfNpa;
	}
	public void setDateOfNpa(String dateOfNpa) {
		this.dateOfNpa = dateOfNpa;
	}
	public String getOutstandingAmountNpa() {
		return outstandingAmountNpa;
	}
	public void setOutstandingAmountNpa(String outstandingAmountNpa) {
		this.outstandingAmountNpa = outstandingAmountNpa;
	}
	public Integer getUploadId() {
		return uploadId;
	}
	public void setUploadId(Integer uploadId) {
		this.uploadId = uploadId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMemberBnkId() {
		return memberBnkId;
	}
	public void setMemberBnkId(String memberBnkId) {
		this.memberBnkId = memberBnkId;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	
	
	
	
}
