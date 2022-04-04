package com.nbfc.bean;

import java.io.InputStream;
import java.io.Serializable;

public class LodgeFreshClaimBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String claimNumber;
	private String mliName;
	private String mliId;
	private String freshClaimDate;
	private String totalRecordCount;
	private String totalAmount;
	private String elgibilityCheck;
	private String mgmtCertificatefileName;
	private String approvalStatus;
	private String remark;
	private String userId;
	private byte[] fileData;
	private InputStream fileDataIS;
	private String financialYears;
	private String disableSaveBtn;
	private String currentQuater;
	private String approvalCode;
	private String approvalAction;
	
	
	

	public String getApprovalAction() {
		return approvalAction;
	}

	public void setApprovalAction(String approvalAction) {
		this.approvalAction = approvalAction;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getCurrentQuater() {
		return currentQuater;
	}

	public void setCurrentQuater(String currentQuater) {
		this.currentQuater = currentQuater;
	}

	public String getDisableSaveBtn() {
		return disableSaveBtn;
	}

	public void setDisableSaveBtn(String disableSaveBtn) {
		this.disableSaveBtn = disableSaveBtn;
	}

	public String getFinancialYears() {
		return financialYears;
	}

	public void setFinancialYears(String financialYears) {
		this.financialYears = financialYears;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
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

	public String getUserId() {
		return userId;
	}

	public String getMgmtCertificatefileName() {
		return mgmtCertificatefileName;
	}

	public void setMgmtCertificatefileName(String mgmtCertificatefileName) {
		this.mgmtCertificatefileName = mgmtCertificatefileName;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}

	public String getFreshClaimDate() {
		return freshClaimDate;
	}

	public void setFreshClaimDate(String freshClaimDate) {
		this.freshClaimDate = freshClaimDate;
	}

	public String getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(String totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getElgibilityCheck() {
		return elgibilityCheck;
	}

	public void setElgibilityCheck(String elgibilityCheck) {
		this.elgibilityCheck = elgibilityCheck;
	}
	//SARITA 29102021 [START]
	public String Checker_Submit_Date;
	public String getChecker_Submit_Date() {
		return Checker_Submit_Date;
	}

	public void setChecker_Submit_Date(String checker_Submit_Date) {
		Checker_Submit_Date = checker_Submit_Date;
	}
	//SARITA 29102021 [END]

}
