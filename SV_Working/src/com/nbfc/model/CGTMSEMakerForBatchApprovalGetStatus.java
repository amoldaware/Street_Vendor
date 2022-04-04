package com.nbfc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//This model class is used for show the number of approve and rejected count in dash board
@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSEMakerForBatchApprovalGetStatus implements Serializable{
	@Id
	@Column(name = "NBFC_MAKER_ID" )
	String usrId;
	
	@Column(name = "STATUS" )
	String status;
	
	@Column(name = "PORTFOLIO_NO" )
	Integer portfolioNo;
	
	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	
	@Column(name = "FILE_PATH" )
	String filePath;
	
	@Column(name = "FILE_ID" )
	String fileId;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

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
}
