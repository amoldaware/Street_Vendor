package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class DisburseNonDisburseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "INTERFACE_UPLOAD_ID")
	private int interfaceUploadId;	

	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	
	@Column(name = "SANCTIONED_AMOUNT")
	private String sanctionAmount;
	
	@Column(name = "SANCTION_DATE")
	private Date sanctionDate;
	
	@Column(name = "FILE_ID")
	private String fileName;
	
	@Column(name = "DISBURSEMENT_STATUS")
	private String disStatus;
	
	@Column(name = "DAN_ID")
	private String danId;
	
	@Column(name = "CGPAN")
	private String cgpan;
	
	@Column(name = "LOAN_ACCOUNT_NO")
	private String loanAccNo;
	
	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	public String getCgpan() {
		return cgpan;
	}
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}
	public String getLoanAccNo() {
		return loanAccNo;
	}
	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}
	public int getInterfaceUploadId() {
		return interfaceUploadId;
	}
	public void setInterfaceUploadId(int interfaceUploadId) {
		this.interfaceUploadId = interfaceUploadId;
	}
	/*public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}*/
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDisStatus() {
		return disStatus;
	}

	public void setDisStatus(String disStatus) {
		this.disStatus = disStatus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public Date getSanctionDate() {
		return sanctionDate;
	}

	

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getPortfolioName() {
		return portfolioName;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

}
