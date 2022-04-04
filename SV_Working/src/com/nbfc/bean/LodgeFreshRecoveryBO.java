package com.nbfc.bean;

import java.io.InputStream;
import java.io.Serializable;

public class LodgeFreshRecoveryBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String uploadId;
	private String pms_no;
	private String rp_no;
	private String uploadedDate;
	private String loanAccountNo;
	private String approvalDate;
	private String mliName;
	private String mliId;
	private String recoveryAmt;
	private String statusDesc;
	private String vertualAccNo;
	private String checkerApprovalDt; 
    private String totalRecordCount;
	private String elgibilityCheck;
    private String approvalStatus;
	private String remark;
	private String userId;
	private String userType;
	private byte[] fileData;
	private InputStream fileDataIS;
    private String disableSaveBtn;
	private String approvalCode;
	private String approvalAction;
	
	
	
	
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getRp_no() {
		return rp_no;
	}
	public void setRp_no(String rp_no) {
		this.rp_no = rp_no;
	}
	public String getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getVertualAccNo() {
		return vertualAccNo;
	}
	public void setVertualAccNo(String vertualAccNo) {
		this.vertualAccNo = vertualAccNo;
	}
	public String getCheckerApprovalDt() {
		return checkerApprovalDt;
	}
	public void setCheckerApprovalDt(String checkerApprovalDt) {
		this.checkerApprovalDt = checkerApprovalDt;
	}
	public String getPms_no() {
		return pms_no;
	}
	public void setPms_no(String pms_no) {
		this.pms_no = pms_no;
	}
	public String getLoanAccountNo() {
		return loanAccountNo;
	}
	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}
	public String getMliName() {
		return mliName;
	}
	public void setMliName(String mliName) {
		this.mliName = mliName;
	}
	public String getMliId() {
		return mliId;
	}
	public void setMliId(String mliId) {
		this.mliId = mliId;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(String totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getElgibilityCheck() {
		return elgibilityCheck;
	}
	public void setElgibilityCheck(String elgibilityCheck) {
		this.elgibilityCheck = elgibilityCheck;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public InputStream getFileDataIS() {
		return fileDataIS;
	}
	public void setFileDataIS(InputStream fileDataIS) {
		this.fileDataIS = fileDataIS;
	}
	public String getDisableSaveBtn() {
		return disableSaveBtn;
	}
	public void setDisableSaveBtn(String disableSaveBtn) {
		this.disableSaveBtn = disableSaveBtn;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	public String getApprovalAction() {
		return approvalAction;
	}
	public void setApprovalAction(String approvalAction) {
		this.approvalAction = approvalAction;
	}
	
	

}
