package com.nbfc.bean;

import java.io.Serializable;

public class ClaimUploadCgtmseBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mliName;
	private String financialYears;
	private String claimNumber;
	private String claimUploadDt;
	private String recordCount;
	private String uploadedOSAmmount;
	private String crystallisedPortfolioAmount;
	private String recovery;
	private String eligibleClaimAmount;
	private String uploadedFileLink;
	private String managementCertificate;
	private String remark;
	private String userId;
	private String mliId;
	private String approvalStatus;
	private String uploadId;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}

	public String getFinancialYears() {
		return financialYears;
	}

	public void setFinancialYears(String financialYears) {
		this.financialYears = financialYears;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimUploadDt() {
		return claimUploadDt;
	}

	public void setClaimUploadDt(String claimUploadDt) {
		this.claimUploadDt = claimUploadDt;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getUploadedOSAmmount() {
		return uploadedOSAmmount;
	}

	public void setUploadedOSAmmount(String uploadedOSAmmount) {
		this.uploadedOSAmmount = uploadedOSAmmount;
	}

	public String getCrystallisedPortfolioAmount() {
		return crystallisedPortfolioAmount;
	}

	public void setCrystallisedPortfolioAmount(String crystallisedPortfolioAmount) {
		this.crystallisedPortfolioAmount = crystallisedPortfolioAmount;
	}

	public String getRecovery() {
		return recovery;
	}

	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}

	public String getEligibleClaimAmount() {
		return eligibleClaimAmount;
	}

	public void setEligibleClaimAmount(String eligibleClaimAmount) {
		this.eligibleClaimAmount = eligibleClaimAmount;
	}

	public String getUploadedFileLink() {
		return uploadedFileLink;
	}

	public void setUploadedFileLink(String uploadedFileLink) {
		this.uploadedFileLink = uploadedFileLink;
	}

	public String getManagementCertificate() {
		return managementCertificate;
	}

	public void setManagementCertificate(String managementCertificate) {
		this.managementCertificate = managementCertificate;
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

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}
	//Added by Sarita on 22-12-2021 [START]
	private String claimPaymentDt;
	public String getClaimPaymentDt() {
		return claimPaymentDt;
	}
	public void setClaimPaymentDt(String claimPaymentDt) {
		this.claimPaymentDt = claimPaymentDt;
	}
	//Added by Sarita on 22-12-2021 [END]
	
	//Added by Sarita on 25-03-2022 [START]
	private String claimSubmitToCkrDt;
	public String getClaimSubmitToCkrDt() {
		return claimSubmitToCkrDt;
	}
	public void setClaimSubmitToCkrDt(String claimSubmitToCkrDt) {
		this.claimSubmitToCkrDt = claimSubmitToCkrDt;
	}
	private String quaterly;
	public String getQuaterly() {
		return quaterly;
	}
	public void setQuaterly(String quaterly) {
		this.quaterly = quaterly;
	}
	
	//Added by Sarita on 25-03-2022 [END]
}
