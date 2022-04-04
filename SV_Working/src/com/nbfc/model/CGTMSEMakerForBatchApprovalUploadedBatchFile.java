package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSEMakerForBatchApprovalUploadedBatchFile implements Serializable{
	public static final long serialVersionUID = 1L;
	@Id
	@Column(name = "NBFC_MAKER_ID" )
	String usrId;
	
	@Column(name = "STATUS" )
	String status;
	
	@Column(name = "PORTFOLIO_NO" )
	Long portfolioNo;
	
	@Column(name = "SANCTIONED_AMOUNT" )
	String sanctionAmount;
	@Column(name = "OUTSTANDING_AMOUNT" )
	String outstandingAmount;
	@Column(name = "FILE_ID" )
	String fileId;
	@Column(name = "MEM_BNK_ID" )
	String MEM_BNK_ID;
	@Column(name = "MEM_ZNE_ID" )
	String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID" )
	String MEM_BRN_ID;
	
	
	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	
	@Column(name="DISBURSEMENT_STATUS")
    private String disbursement_status;
	
	
	public String getDisbursement_status() {
		return disbursement_status;
	}

	public void setDisbursement_status(String disbursement_status) {
		this.disbursement_status = disbursement_status;
	}

	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="NBFC_UPLOADED_DATE")
	private Date dateOfUpload;
	
	@Column(name = "FILE_PATH" )
	public String filePath;
	
	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	

	public Date getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(Date dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}

	public Integer getSubPortfolioNo() {
		System.out.println("getSubPortfolioNo() method call ==="+subPortfolioNo);
		return subPortfolioNo;
	}

	public void setSubPortfolioNo(Integer subPortfolioNo) {
		System.out.println("setSubPortfolioNo method call in model calss==");
		this.subPortfolioNo = subPortfolioNo;
	}

	public Long getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(Long portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
