package com.nbfc.bean;

import java.io.Serializable;

public class ClaimPaymentBO implements Serializable {
	private static final long serialVersionUID = -7378430040102490693L;
	private Integer serialNo;
	private String mliId;
	private String mliName;
	private String claimNumber;
	private String claimLogdeDt;
	private String uploadedOSAmount;
	private String claimSettledAmount;
	private String acountNumber;
	private String claimSettledDt;
	private String voucherNumber;
	private String approvalStatus;
	private String remark;
	private String utrNumber;
	private String paymentDt;
	private String approvalCode;
	private String utrUpdated;
	private String fromDt;
	private String toDate;
	private String claimDateFlag;
	private String recordExist;
	private String isUtrUploaded;
	private String userRole;
	private String userName;
	private String approvalAction;

	
	

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getApprovalAction() {
		return approvalAction;
	}

	public void setApprovalAction(String approvalAction) {
		this.approvalAction = approvalAction;
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

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimLogdeDt() {
		return claimLogdeDt;
	}

	public void setClaimLogdeDt(String claimLogdeDt) {
		this.claimLogdeDt = claimLogdeDt;
	}

	public String getUploadedOSAmount() {
		return uploadedOSAmount;
	}

	public void setUploadedOSAmount(String uploadedOSAmount) {
		this.uploadedOSAmount = uploadedOSAmount;
	}

	public String getClaimSettledAmount() {
		return claimSettledAmount;
	}

	public void setClaimSettledAmount(String claimSettledAmount) {
		this.claimSettledAmount = claimSettledAmount;
	}

	public String getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(String acountNumber) {
		this.acountNumber = acountNumber;
	}

	public String getClaimSettledDt() {
		return claimSettledDt;
	}

	public void setClaimSettledDt(String claimSettledDt) {
		this.claimSettledDt = claimSettledDt;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
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

	public String getUtrNumber() {
		return utrNumber;
	}

	public void setUtrNumber(String utrNumber) {
		this.utrNumber = utrNumber;
	}

	public String getPaymentDt() {
		return paymentDt;
	}

	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getUtrUpdated() {
		return utrUpdated;
	}

	public void setUtrUpdated(String utrUpdated) {
		this.utrUpdated = utrUpdated;
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

	public String getClaimDateFlag() {
		return claimDateFlag;
	}

	public void setClaimDateFlag(String claimDateFlag) {
		this.claimDateFlag = claimDateFlag;
	}

	public String getRecordExist() {
		return recordExist;
	}

	public void setRecordExist(String recordExist) {
		this.recordExist = recordExist;
	}

	public String getIsUtrUploaded() {
		return isUtrUploaded;
	}

	public void setIsUtrUploaded(String isUtrUploaded) {
		this.isUtrUploaded = isUtrUploaded;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
