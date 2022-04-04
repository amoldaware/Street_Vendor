package com.nbfc.bean;

import java.io.Serializable;

public class RecoveryErrorFileExcelDataBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String pms_no;
	private String loanAccNo;
	private String recoveryAmt;
	private String uploadId;
	private String processingFlag;
	private String processingDesc;
	public String getPms_no() {
		return pms_no;
	}
	public void setPms_no(String pms_no) {
		this.pms_no = pms_no;
	}
	public String getLoanAccNo() {
		return loanAccNo;
	}
	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getProcessingFlag() {
		return processingFlag;
	}
	public void setProcessingFlag(String processingFlag) {
		this.processingFlag = processingFlag;
	}
	public String getProcessingDesc() {
		return processingDesc;
	}
	public void setProcessingDesc(String processingDesc) {
		this.processingDesc = processingDesc;
	}
	
	
	

}
