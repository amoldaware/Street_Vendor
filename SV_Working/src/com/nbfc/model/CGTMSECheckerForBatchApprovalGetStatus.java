package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSECheckerForBatchApprovalGetStatus implements Serializable{
	
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID" )
	String interfaceUploadId;
	

	@Column(name = "NBFC_MAKER_ID" )
	String usrId;
	
	@Column(name = "STATUS" )
	String status;
	
	@Column(name = "PORTFOLIO_NO" )
	Integer portfolioNo;
	
	@Column(name = "FILE_PATH" )
	String filePath;
	
	
	
	public String getFilePath() {
		return filePath;
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

	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;

	public Integer getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(Integer portfolioNo) {
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

	public String getInterfaceUploadId() {
		return interfaceUploadId;
	}

	public void setInterfaceUploadId(String interfaceUploadId) {
		this.interfaceUploadId = interfaceUploadId;
	}

}
