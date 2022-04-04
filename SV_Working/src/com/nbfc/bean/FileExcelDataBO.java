package com.nbfc.bean;

import java.io.Serializable;
import java.math.BigInteger;


public class FileExcelDataBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pmsNumber;
	private String loanAcountNo;
	private String dateOfNpa;
	private String outstandingAmountNpa;
	private Integer uploadId;
	private String userId;
	private String memberBnkId;
	private String financialyear;
	private String quater;
	
	private String claimPaymentVoucher;
	private String claimNumber;
	private String claimPaymentDate;
	private String utrNo;
	private Long utruploadId;
	
	public Long getUtruploadId() {
		return utruploadId;
	}

	public void setUtruploadId(Long utruploadId) {
		this.utruploadId = utruploadId;
	}

	public String getClaimPaymentVoucher() {
		return claimPaymentVoucher;
	}

	public void setClaimPaymentVoucher(String claimPaymentVoucher) {
		this.claimPaymentVoucher = claimPaymentVoucher;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimPaymentDate() {
		return claimPaymentDate;
	}

	public void setClaimPaymentDate(String claimPaymentDate) {
		this.claimPaymentDate = claimPaymentDate;
	}

	public String getUtrNo() {
		return utrNo;
	}

	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}

	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}

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

	public Integer getUploadId() {
		return uploadId;
	}

	public void setUploadId(Integer uploadId) {
		this.uploadId = uploadId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemberBnkId() {
		return memberBnkId;
	}

	public void setMemberBnkId(String memberBnkId) {
		this.memberBnkId = memberBnkId;
	}

	public String getFinancialyear() {
		return financialyear;
	}

	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}
    //Added by Sarita on 21/10/2021 --- [START]
	private String borrowerName;

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	//Added by Sarita on 21/10/2021 --- [END]
	//Added by Sarita on 21/01/2022 -- [START]
	private String loanTerm;

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	//Added by Sarita on 21/01/2022 -- [END]
}
