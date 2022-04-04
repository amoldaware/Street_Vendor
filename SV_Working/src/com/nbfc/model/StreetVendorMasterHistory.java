package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

@Entity
@Table(name = "NBFC_STREET_VENDOR_MASTER_HIST")
public class StreetVendorMasterHistory 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="applicationNo")
	private String applicationNo;
	
	@Column(name="loanAccountNumber")
	private String loanAccountNumber;
	
	@Column(name="disbursedAmount")
	private String disbursedAmount;
	
	@Column(name="Disbursement_Amount")
	private String Disbursement_Amount;
	
	@Column(name="disbursedDt")
	private String disbursedDt;
	
	@Column(name="DisbursementDate")
	private String DisbursementDate;
	
	@Column(name="sanctionedAmount")
	private String sanctionedAmount;
	
	@Column(name="Loan_Sanction_Amount")
	private String Loan_Sanction_Amount;
	
	@Column(name="sanctionedDt")
	private String sanctionedDt;
	
	@Column(name="SanctionDate")
	private String SanctionDate;
	
	@Column(name="upload_date")
	private String upload_date;
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public String getDisbursedAmount() {
		return disbursedAmount;
	}
	public String getDisbursement_Amount() {
		return Disbursement_Amount;
	}
	public String getDisbursedDt() {
		return disbursedDt;
	}
	public String getDisbursementDate() {
		return DisbursementDate;
	}
	public String getSanctionedAmount() {
		return sanctionedAmount;
	}
	public String getLoan_Sanction_Amount() {
		return Loan_Sanction_Amount;
	}
	public String getSanctionedDt() {
		return sanctionedDt;
	}
	public String getSanctionDate() {
		return SanctionDate;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public void setDisbursedAmount(String disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}
	public void setDisbursement_Amount(String disbursement_Amount) {
		Disbursement_Amount = disbursement_Amount;
	}
	public void setDisbursedDt(String disbursedDt) {
		this.disbursedDt = disbursedDt;
	}
	public void setDisbursementDate(String disbursementDate) {
		DisbursementDate = disbursementDate;
	}
	public void setSanctionedAmount(String sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public void setLoan_Sanction_Amount(String loan_Sanction_Amount) {
		Loan_Sanction_Amount = loan_Sanction_Amount;
	}
	public void setSanctionedDt(String sanctionedDt) {
		this.sanctionedDt = sanctionedDt;
	}
	public void setSanctionDate(String sanctionDate) {
		SanctionDate = sanctionDate;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}	
}
