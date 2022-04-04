package com.nbfc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class ClaimErrorFileExcelDataBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pmsNumber;
	private String loanAcountNo;
	private String dateOfNpa;
	private String outstandingAmountNpa;
	private String uploadId;
	private String processingFlag;
	private String processingDesc;

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
	//Added by Sarita on 21/10/2021 [START]
	private String borrowerName;

	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
			this.borrowerName = borrowerName;
	}
	//Added by Sarita on 21/10/2021 [END]
	//Added by Sarita 21/01/2022 [START]
	private String loanTerm;

	public String getLoanTerm() {
			return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
			this.loanTerm = loanTerm;
	}
	//Added by Sarita 21/01/2022 [END]
	//Added by Sarita 01-04-2022 [START]
	private String APIDataSubmission;

	public String getAPIDataSubmission() {
		return APIDataSubmission;
	}

	public void setAPIDataSubmission(String aPIDataSubmission) {
		APIDataSubmission = aPIDataSubmission;
	}
	//Added by Sarita 01-04-2022 [END]
}
