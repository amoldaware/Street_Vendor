
package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;



@Entity
@Table(name = "all_dan_list_vw")
public class DanAllocationForNbfcMakerUsingVWModel implements Serializable {
	
	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "DAN_Id")
	private String danId;
	@Column(name = "AMOUNT")
	private String amount;
	@Column(name = "PORTFOLIO_RATE")
	private String portfolioRate;
	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	@Column(name = "MLI_NAME")
	private String mliName;
	@Column(name = "DAN_FSTATUS")
	private String status;
	@Column(name = "MLI_ID")
	private String mliId;
	public String getMliId() {
		return mliId;
	}
	public void setMliId(String mliId) {
		this.mliId = mliId;
	}
	@Column(name = "FILE_ID")
	private String fileId;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPortfolioRate() {
		return portfolioRate;
	}
	public void setPortfolioRate(String portfolioRate) {
		this.portfolioRate = portfolioRate;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getMliName() {
		return mliName;
	}
	public void setMliName(String mliName) {
		this.mliName = mliName;
	}
	

}
