package com.nbfc.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "claim_upload_tracker")
public class ClaimUploadTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bu_id")
	private int bu_id;

	@Column(name = "member_id")
	private String member_id;

	@Column(name = "upload_id")
//	private String upload_id;
	private BigInteger uploadId;

	@Column(name = "process_name")
	private String process_name;

	@Column(name = "uploaded_by")
	private String upload_by;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "uploaded_date")
	private Date upload_date;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SUCCESS_CNT")
	private int status_cnt;

	@Column(name = "UNSUCCESS_CNT")
	private int unsuccess_cnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE")
	private Date modified_date;

	@Column(name = "excel_cnt")
	private int excel_cnt;

	@Column(name = "Quartly")
	private String quater;

	@Column(name = "Financial_Year")
	private String financialYear;

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

	public int getBu_id() {
		return bu_id;
	}

	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public BigInteger getUploadId() {
		return uploadId;
	}

	public void setUploadId(BigInteger uploadId) {
		this.uploadId = uploadId;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getUpload_by() {
		return upload_by;
	}

	public void setUpload_by(String upload_by) {
		this.upload_by = upload_by;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatus_cnt() {
		return status_cnt;
	}

	public void setStatus_cnt(int status_cnt) {
		this.status_cnt = status_cnt;
	}

	public int getUnsuccess_cnt() {
		return unsuccess_cnt;
	}

	public void setUnsuccess_cnt(int unsuccess_cnt) {
		this.unsuccess_cnt = unsuccess_cnt;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public int getExcel_cnt() {
		return excel_cnt;
	}

	public void setExcel_cnt(int excel_cnt) {
		this.excel_cnt = excel_cnt;
	}
    
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
}
