package com.nbfc.bean;

import java.io.InputStream;
import java.io.Serializable;

public class LodgeFreshUrtBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String claimPaymentVoucher;
	private String claimNumber;
	private String mliName;
	private String mliId;
	private String claimPaymentDate;
	private String claimSettlementAmt;
	private String totalRecordCount;
	private String utrNo;
	private String elgibilityCheck;
    private String approvalStatus;
	private String remark;
	private String userId;
	private String userType;
	private byte[] fileData;
	private InputStream fileDataIS;
    private String disableSaveBtn;
	private String approvalCode;
	private String financialYear;
	private String approvalAction;
	
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getClaimSettlementAmt() {
		return claimSettlementAmt;
	}
	public void setClaimSettlementAmt(String claimSettlementAmt) {
		this.claimSettlementAmt = claimSettlementAmt;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getClaimPaymentVoucher() {
		return claimPaymentVoucher;
	}
	public void setClaimPaymentVoucher(String claimPaymentVoucher) {
		this.claimPaymentVoucher = claimPaymentVoucher;
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
	public String getClaimPaymentDate() {
		return claimPaymentDate;
	}
	public void setClaimPaymentDate(String claimPaymentDate) {
		this.claimPaymentDate = claimPaymentDate;
	}
	public String getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(String totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getUtrNo() {
		return utrNo;
	}
	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
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
