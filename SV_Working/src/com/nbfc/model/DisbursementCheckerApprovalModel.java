
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
public class DisbursementCheckerApprovalModel implements Serializable {
	/*
	 * private String id; public String getId() { return id; } public void
	 * setId(String id) { this.id = id; }
	 */
	@Id
	@Column(name = "LOAN_ACCOUNT_NO")
	String loanAccountNumber;
	@Column(name = "PORTFOLIO_NO")
	String portfolioNo;
	@Column(name = "STATUS")
	String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "REMARKS")
	String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	@Column(name = "DISB_CHECKER_ID")
	String disbCheckerId;	
	public String getDisbCheckerId() {
		return disbCheckerId;
	}

	public void setDisbCheckerId(String disbCheckerId) {
		this.disbCheckerId = disbCheckerId;
	}

	public Date getDisbCheckerDate() {
		return disbCheckerDate;
	}

	public void setDisbCheckerDate(Date disbCheckerDate) {
		this.disbCheckerDate = disbCheckerDate;
	}
	@Column(name = "DISB_CHECKER_DATE")
	Date disbCheckerDate;
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
