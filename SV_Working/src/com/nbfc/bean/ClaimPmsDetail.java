package com.nbfc.bean;

import java.io.Serializable;

public class ClaimPmsDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer sno;
	private String pms;
	private String loanAccount;
	private String borrowerName;
	private String sanctionDate;
	private Double sanctionAmt;
	private String claimDate;
	private Double claimAmt;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public Double getSanctionAmt() {
		return sanctionAmt;
	}

	public void setSanctionAmt(Double sanctionAmt) {
		this.sanctionAmt = sanctionAmt;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public Double getClaimAmt() {
		return claimAmt;
	}

	public void setClaimAmt(Double claimAmt) {
		this.claimAmt = claimAmt;
	}

}
