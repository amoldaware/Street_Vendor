package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity
@Table(name = "nbfc_interface_upload")
public class DisbursementForApprovalModel implements Serializable {
	/*
	 * private String id; public String getId() { return id; } public void
	 * setId(String id) { this.id = id; }
	 */
	@Id
	@Column(name = "LOAN_ACCOUNT_NO")
	String loanAccountNumber;
	@Column(name = "PORTFOLIO_NO")
	String portfolioNo;
	
	
	@Column(name = "DISB_MAKER_ID")
	String disbMakerId;
	public String getDisbMakerId() {
		return disbMakerId;
	}

	public void setDisbMakerId(String disbMakerId) {
		this.disbMakerId = disbMakerId;
	}

	public Date getDisbMakerDate() {
		return disbMakerDate;
	}

	public void setDisbMakerDate(Date disbMakerDate) {
		this.disbMakerDate = disbMakerDate;
	}

	@Column(name = "DISB_MAKER_DATE")
	Date disbMakerDate;
	
	
	@Column(name = "STATUS")
	String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "NEW_EXISTING_UNIT")
	String unit;
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "FIRST_DISBURSEMENT_DATE")
	Date dateOfDisbursement;
	@Column(name = "SANCTIONED_AMOUNT")
	String amountOfDisbursement;
	@Column(name = "DISBURSEMENT_STATUS")
	String whetherDisbursed;

	public String getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public Date getDateOfDisbursement() {
		return dateOfDisbursement;
	}

	public void setDateOfDisbursement(Date dateOfDisbursement) {
		this.dateOfDisbursement = dateOfDisbursement;
	}

	public String getAmountOfDisbursement() {
		return amountOfDisbursement;
	}

	public void setAmountOfDisbursement(String amountOfDisbursement) {
		this.amountOfDisbursement = amountOfDisbursement;
	}

	public String getWhetherDisbursed() {
		return whetherDisbursed;
	}

	public void setWhetherDisbursed(String whetherDisbursed) {
		this.whetherDisbursed = whetherDisbursed;
	}

	@Override
	public String toString() {
		return "DisbursementForApprovalModel [portfolioNo=" + portfolioNo
				+ ", unit=" + unit + ", loanAccountNumber=" + loanAccountNumber
				+ ", dateOfDisbursement=" + dateOfDisbursement
				+ ", amountOfDisbursement=" + amountOfDisbursement
				+ ", whetherDisbursed=" + whetherDisbursed + "]";
	}

}
