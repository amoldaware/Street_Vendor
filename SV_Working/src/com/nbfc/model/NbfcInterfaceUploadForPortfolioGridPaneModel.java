package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class NbfcInterfaceUploadForPortfolioGridPaneModel implements Serializable{
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID" )
	private Integer interfaceUploadId;
	
	
	@Column(name = "PORTFOLIO_NO" )
	private Integer portfolio_no;
	@Column(name = "SANCTIONED_AMOUNT" )
	private String sanctionAmount;
	
	@Column(name = "VERIFIEDSTATUS" )
	private String VERIFIEDSTATUS;

	public String getVERIFIEDSTATUS() {
		return VERIFIEDSTATUS;
	}


	public void setVERIFIEDSTATUS(String vERIFIEDSTATUS) {
		VERIFIEDSTATUS = vERIFIEDSTATUS;
	}


	public String getSanctionAmount() {
		return sanctionAmount;
	}


	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}


	public Integer getInterfaceUploadId() {
		return interfaceUploadId;
	}


	public void setInterfaceUploadId(Integer interfaceUploadId) {
		this.interfaceUploadId = interfaceUploadId;
	}


	public Integer getPortfolio_no() {
		return portfolio_no;
	}


	public void setPortfolio_no(Integer portfolio_no) {
		this.portfolio_no = portfolio_no;
	}}
