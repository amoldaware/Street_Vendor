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
@Table(name = "bank_mandateform")
public class BankMandateForm implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Bank_Manadate_Id")
	private Integer id;
	
	@Column(name = "MLI_Name")
	private String mliName;
	
	@Column(name = "MLI_Id")
	private String mliId;

	@Column(name = "MLI_ContNo")
	private String mliContactNo;

	@Column(name = "MLI_MobileNo")
	private String mliMobileNo;
	
	@Column(name = "MLI_EmailId")
	private String mliEmailId;

	@Column(name = "Bank_Name")
	private String bankName;
	
	@Column(name = "Branch_Name")
	private String branchName;
	
	@Column(name = "Account_Name")
	private String accountName;
	
	@Column(name = "Account_No")
	private String accountNo;
	
	@Column(name = "Type_Of_Account")
	private String typeOfAcc;
	
	@Column(name = "IFSC_Code")
	private String ifscCode;
	
	@Column(name = "Upload_Document")
	private String uploadDoc;
	
	
	@Column(name = "Declartion_Flag")
	private String declartionFlag;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date")
	private Date createDate;
	
	@Column(name = "Maker_Remarks")
	private String makerRemarks;
	
	@Column(name = "Checker_Remarks")
	private String checkerRemarks;

	@Column(name = "Approval_Status")
	private String approvalStatus;
	
	
	@Column(name = "File_Data", columnDefinition = "BLOB")
	@Lob
	private byte[] bankMandatFileDate;




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getMliName() {
		return mliName;
	}




	public void setMliName(String mliName) {
		this.mliName = mliName;
	}




	public String getMliId() {
		return mliId;
	}




	public void setMliId(String mliId) {
		this.mliId = mliId;
	}




	public String getMliContactNo() {
		return mliContactNo;
	}




	public void setMliContactNo(String mliContactNo) {
		this.mliContactNo = mliContactNo;
	}




	public String getMliMobileNo() {
		return mliMobileNo;
	}




	public void setMliMobileNo(String mliMobileNo) {
		this.mliMobileNo = mliMobileNo;
	}




	public String getMliEmailId() {
		return mliEmailId;
	}




	public void setMliEmailId(String mliEmailId) {
		this.mliEmailId = mliEmailId;
	}




	public String getBankName() {
		return bankName;
	}




	public void setBankName(String bankName) {
		this.bankName = bankName;
	}




	public String getBranchName() {
		return branchName;
	}




	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}




	public String getAccountName() {
		return accountName;
	}




	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}




	public String getAccountNo() {
		return accountNo;
	}




	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}




	public String getTypeOfAcc() {
		return typeOfAcc;
	}




	public void setTypeOfAcc(String typeOfAcc) {
		this.typeOfAcc = typeOfAcc;
	}




	public String getIfscCode() {
		return ifscCode;
	}




	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}




	public String getUploadDoc() {
		return uploadDoc;
	}




	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}




	public String getDeclartionFlag() {
		return declartionFlag;
	}




	public void setDeclartionFlag(String declartionFlag) {
		this.declartionFlag = declartionFlag;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public Date getCreateDate() {
		return createDate;
	}




	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}




	public String getMakerRemarks() {
		return makerRemarks;
	}




	public void setMakerRemarks(String makerRemarks) {
		this.makerRemarks = makerRemarks;
	}

	


	public String getCheckerRemarks() {
		return checkerRemarks;
	}




	public void setCheckerRemarks(String checkerRemarks) {
		this.checkerRemarks = checkerRemarks;
	}




	public byte[] getBankMandatFileDate() {
		return bankMandatFileDate;
	}




	public void setBankMandatFileDate(byte[] bankMandatFileDate) {
		this.bankMandatFileDate = bankMandatFileDate;
	}




	public String getApprovalStatus() {
		return approvalStatus;
	}




	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	


}
