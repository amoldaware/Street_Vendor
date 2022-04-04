package com.nbfc.bean;

import java.io.Serializable;

public class RecoveryReportBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer serialNo;
	private String mliId;
	private String mliName;
	private String loanAccount;
	private String misMatch;
	private String pmsNumber;
	private String fromDt;
	private String toDate;
	private String uploadedDate;
	private String totalRecord;
	private String status;
	private String remark;
	private String virtualAccountNo;
	private String approvalDate;
	private String uploadedBy;
	private String approvedBy;
	private String utr;
	private String appropriationDate;
	private String ftpResponse;
	private String userRole;
	private String rpNumber;
	private String recoveryAmt;
	
	
	
	

	public String getRecoveryAmt() {
		return recoveryAmt;
	}

	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}

	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVirtualAccountNo() {
		return virtualAccountNo;
	}

	public void setVirtualAccountNo(String virtualAccountNo) {
		this.virtualAccountNo = virtualAccountNo;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getUtr() {
		return utr;
	}

	public void setUtr(String utr) {
		this.utr = utr;
	}

	public String getAppropriationDate() {
		return appropriationDate;
	}

	public void setAppropriationDate(String appropriationDate) {
		this.appropriationDate = appropriationDate;
	}

	public String getFtpResponse() {
		return ftpResponse;
	}

	public void setFtpResponse(String ftpResponse) {
		this.ftpResponse = ftpResponse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPmsNumber() {
		return pmsNumber;
	}

	public void setPmsNumber(String pmsNumber) {
		this.pmsNumber = pmsNumber;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getMisMatch() {
		return misMatch;
	}

	public void setMisMatch(String misMatch) {
		this.misMatch = misMatch;
	}

	public String getFromDt() {
		return fromDt;
	}

	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
