package com.nbfc.bean;

import java.io.Serializable;

public class RecoveryReportExcelBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer serialNo;
	private String mliId;
	private String mliName;
	private String rpNumber;
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
	private String misMatch;
    private String recoveryAmt;
	
	
	
	

	public String getRecoveryAmt() {
		return recoveryAmt;
	}

	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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

	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
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

	public String getMisMatch() {
		return misMatch;
	}

	public void setMisMatch(String misMatch) {
		this.misMatch = misMatch;
	}

}
