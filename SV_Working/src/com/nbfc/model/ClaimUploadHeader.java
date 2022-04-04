package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "claim_upload_header")
public class ClaimUploadHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Claim_ID")
	private int id;

	@Column(name = "Claim_Number")
	private String claimNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Claim_Date")
	private Date claimDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Upload_Date")
	private Date uploadDate;

	@Column(name = "MLI_ID")
	private String mliId;

	@Column(name = "Total_Records")
	private String totalRecords;

	@Column(name = "Claim_Amount")
	private String claimAmount;

	@Column(name = "Uploaded_By")
	private String uploadedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Uploaded_Date")
	private Date uploadedDate;

	@Column(name = "Mgmt_Certificate")
	private String mgmtCertificateName;

	@Column(name = "Approval_Status")
	private String approvalStatus;

	@Column(name = "Approval_Remark")
	private String approvalRemark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getMgmtCertificateName() {
		return mgmtCertificateName;
	}

	public void setMgmtCertificateName(String mgmtCertificateName) {
		this.mgmtCertificateName = mgmtCertificateName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalRemark() {
		return approvalRemark;
	}

	public void setApprovalRemark(String approvalRemark) {
		this.approvalRemark = approvalRemark;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public byte[] getMgmtFileDate() {
		return mgmtFileDate;
	}

	public void setMgmtFileDate(byte[] mgmtFileDate) {
		this.mgmtFileDate = mgmtFileDate;
	}

	public String getQuartly() {
		return quartly;
	}

	public void setQuartly(String quartly) {
		this.quartly = quartly;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	@Column(name = "Approved_By")
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Approved_Date")
	private Date approvedDate;

	@Column(name = "Mgmt_Certificate_data", columnDefinition = "BLOB")
	@Lob
	private byte[] mgmtFileDate;

	@Column(name = "Quartly")
	private String quartly;

	@Column(name = "Financial_Year")
	private String financialYear;
	
	//SARITA 29102021 [START]
	@Column(name = "Checker_Submit_Date")   
	public Date Checker_Submit_Date;

	public Date getChecker_Submit_Date() {
		return Checker_Submit_Date;
	}
	public void setChecker_Submit_Date(Date checker_Submit_Date) {
		Checker_Submit_Date = checker_Submit_Date;
	}
	//SARITA 29102021 [END]
}