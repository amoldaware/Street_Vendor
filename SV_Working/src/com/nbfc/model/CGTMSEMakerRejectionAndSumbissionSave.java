package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSEMakerRejectionAndSumbissionSave  implements Serializable{
	
	
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID")
	public Integer interfaceUploadId;
	
	
	@Column(name = "CGDAN")
	public String cgdan;

	@Column(name = "MPENDENCY")
	public String mPendency;
	
	@Column(name = "REMARKS")
	public String rejection_reason;
	
	@Column (name="CGTMSE_MAKER_ID")
	public String cgtmseMakerId;
	
	
	@Column(name = "FILE_PATH" )
	public String filePath;
	
	@Column(name = "FILE_ID" )
	public String fileId;
	
	@Column(name = "PORTFOLIO_NAME")
	public String portfolioNo;
	
	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	
	@Column (name="STATUS")
	public String status;
	
	/*@Column (name="DISBURSEMENT_STATUS")
	public String DISBURSEMENT_STATUS;*/
	

	/*public String getDISBURSEMENT_STATUS() {
		return DISBURSEMENT_STATUS;
	}

	public void setDISBURSEMENT_STATUS(String dISBURSEMENT_STATUS) {
		DISBURSEMENT_STATUS = dISBURSEMENT_STATUS;
	}*/

	public String getFilePath() {
		return filePath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getSubPortfolioNo() {
		return subPortfolioNo;
	}

	public void setSubPortfolioNo(Integer subPortfolioNo) {
		this.subPortfolioNo = subPortfolioNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	

	public String getCgdan() {
		return cgdan;
	}

	public void setCgdan(String cgdan) {
		this.cgdan = cgdan;
	}

	public String getmPendency() {
		return mPendency;
	}

	public void setmPendency(String mPendency) {
		this.mPendency = mPendency;
	}

	public String getRejection_reason() {
		return rejection_reason;
	}

	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}

	public String getCgtmseMakerId() {
		return cgtmseMakerId;
	}

	public void setCgtmseMakerId(String cgtmseMakerId) {
		this.cgtmseMakerId = cgtmseMakerId;
	}

	
	public Integer getInterfaceUploadId() {
		return interfaceUploadId;
	}

	public void setInterfaceUploadId(Integer interfaceUploadId) {
		this.interfaceUploadId = interfaceUploadId;
	}
}
