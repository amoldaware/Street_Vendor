package com.nbfc.bean;

import java.util.Date;

public class APIClaimDataBO 
{
	private static final long serialVersionUID = 1L;

	private String pMSNumber;
	private String borrowerName;
	private String claimStatus;
	private String loanAccountNumber;
	private String dateOfNPA;
	private String outstandingLoanAmount;
	private String dateOfClaimSubmission;
	private String dateOfClaimSettlement;
	private String claimSettledByCGTMSE;
	private String loanTerm;
	
	public String getpMSNumber() {
		return pMSNumber;
	}
	public void setpMSNumber(String pMSNumber) {
		this.pMSNumber = pMSNumber;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public String getDateOfNPA() {
		return dateOfNPA;
	}
	public void setDateOfNPA(String dateOfNPA) {
		this.dateOfNPA = dateOfNPA;
	}
	public String getOutstandingLoanAmount() {
		return outstandingLoanAmount;
	}
	public void setOutstandingLoanAmount(String outstandingLoanAmount) {
		this.outstandingLoanAmount = outstandingLoanAmount;
	}
	public String getDateOfClaimSubmission() {
		return dateOfClaimSubmission;
	}
	public void setDateOfClaimSubmission(String dateOfClaimSubmission) {
		this.dateOfClaimSubmission = dateOfClaimSubmission;
	}
	public String getDateOfClaimSettlement() {
		return dateOfClaimSettlement;
	}
	public void setDateOfClaimSettlement(String dateOfClaimSettlement) {
		this.dateOfClaimSettlement = dateOfClaimSettlement;
	}
	public String getClaimSettledByCGTMSE() {
		return claimSettledByCGTMSE;
	}
	public void setClaimSettledByCGTMSE(String claimSettledByCGTMSE) {
		this.claimSettledByCGTMSE = claimSettledByCGTMSE;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	
	private String isValid;
	private String failureRemarks;

	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getFailureRemarks() {
		return failureRemarks;
	}
	public void setFailureRemarks(String failureRemarks) {
		this.failureRemarks = failureRemarks;
	}
}
