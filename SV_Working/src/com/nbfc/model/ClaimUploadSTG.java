package com.nbfc.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "claim_upload_stg")
public class ClaimUploadSTG implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Record_id")
	private Integer id;

	@Column(name = "UPLOAD_ID")
	private BigInteger uploadId;

	@Column(name = "PMS_No")
	private String pmsNumber;

	@Column(name = "Loan_Account_Number")
	private String loanAcountNo;

	/* @Temporal(TemporalType.TIMESTAMP) */
	@Column(name = "Date_of_NPA")
	private String dateOfNpa;

	@Column(name = "Outstanding_Amount")
	private String outstandingAmountNpa;

	@Column(name = "PROCESS_FLAG")
	private String processFlag;

	@Column(name = "PROCESS_DESC")
	private String processDecs;

	@Column(name = "CREATE_BY")
	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date")
	private Date createdDate;

	@Column(name = "MODIFY_BY")
	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Modify_Date")
	private Date modifyDate;

	@Column(name = "Quartly")
	private String quater;

	@Column(name = "Financial_Year")
	private String financialYear;
	
	//Added by Sarita on 21/10/2021 [START]
	@Column(name = "borrowerName")
	private String borrowerName;

	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	//Added by Sarita on 21/10/2021 [END]

	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOutstandingAmountNpa() {
		return outstandingAmountNpa;
	}

	public void setOutstandingAmountNpa(String outstandingAmountNpa) {
		this.outstandingAmountNpa = outstandingAmountNpa;
	}

	public BigInteger getUploadId() {
		return uploadId;
	}

	public void setUploadId(BigInteger uploadId) {
		this.uploadId = uploadId;
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public String getProcessDecs() {
		return processDecs;
	}

	public void setProcessDecs(String processDecs) {
		this.processDecs = processDecs;
	}
	//Added by Sarita 21/01/2022 [START]
	@Column(name = "loanTerm")
	private String loanTerm;

	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	//Added by Sarita 21/01/2022 [END]
	
	//Added by Sarita 13/03/2022 [START]
	@Column(name = "memberId")
	private String memberId;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	//Added by Sarita 13/03/2022 [END]
}
