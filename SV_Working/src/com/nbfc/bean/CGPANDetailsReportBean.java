package com.nbfc.bean;

import java.sql.Date;

public class CGPANDetailsReportBean {
	
	private String Cgpan;
	private String danId;
	private String expiryDate;
	private String payId;
	private double outstandingAmt;
	private double guaranteeAmt;
	private double dci_base_amt;
	private double dci_total_amt;
	public double getDci_base_amt() {
		return dci_base_amt;
	}

	public void setDci_base_amt(double dci_base_amt) {
		this.dci_base_amt = dci_base_amt;
	}

	public double getDci_total_amt() {
		return dci_total_amt;
	}

	public void setDci_total_amt(double dci_total_amt) {
		this.dci_total_amt = dci_total_amt;
	}

	private String dciGuaranteeeSDate;
	private String appsubmittedDate;
	public String getAppsubmittedDate() {
		return appsubmittedDate;
	}

	public void setAppsubmittedDate(String appsubmittedDate) {
		this.appsubmittedDate = appsubmittedDate;
	}

	private String dciAppropriationDate;
	private String crystalizationDate;
	private String ssiName;
	private String mliId;
	private String displayCgpen;
	private String fYYear;
	private double outstandingAmount1;
	private double dan_Amt;
	private String status;
	private String Taxid;
	
	
	
	
	
	public String getfYYear() {
		return fYYear;
	}

	public void setfYYear(String fYYear) {
		this.fYYear = fYYear;
	}

	public String getTaxid() {
		return Taxid;
	}

	public void setTaxid(String taxid) {
		Taxid = taxid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getDan_Amt() {
		return dan_Amt;
	}

	public void setDan_Amt(double dan_Amt) {
		this.dan_Amt = dan_Amt;
	}

	public void setGuaranteeAmt(double guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}

	public void setOutstandingAmount1(double outstandingAmount1) {
		this.outstandingAmount1 = outstandingAmount1;
	}

	private Double igstAmt;
	private Double cgstAmt;
	private Double sgstAmt;
	private Float guaranteeFee;
	private Long taxInvoiceId;
	private String portfolioName;
	private String bankname;
	private String state;
	private String date;
	private String userId;
	private String appropriation_by;
	
	
		
	
	


	
	
	public String getAppropriation_by() {
		return appropriation_by;
	}

	public void setAppropriation_by(String appropriation_by) {
		this.appropriation_by = appropriation_by;
	}

	public double getGuaranteeAmt() {
		return guaranteeAmt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Long getTaxInvoiceId() {
		return taxInvoiceId;
	}

	public void setTaxInvoiceId(Long taxInvoiceId) {
		this.taxInvoiceId = taxInvoiceId;
	}

	public Double getOutstandingAmount1() {
		return outstandingAmount1;
	}

	public void setOutstandingAmount1(Double outstandingAmount1) {
		this.outstandingAmount1 = outstandingAmount1;
	}

	public Double getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(Double igstAmt) {
		this.igstAmt = igstAmt;
	}

	public Double getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(Double cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public Double getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(Double sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public Float getGuaranteeFee() {
		return guaranteeFee;
	}

	public void setGuaranteeFee(Float guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}

	public String getCrystalizationDate() {
		return crystalizationDate;
	}

	public void setCrystalizationDate(String crystalizationDate) {
		this.crystalizationDate = crystalizationDate;
	}

	public String getDciAppropriationDate() {
		return dciAppropriationDate;
	}

	public void setDciAppropriationDate(String dciAppropriationDate) {
		this.dciAppropriationDate = dciAppropriationDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getDisplayCgpen() {
		return displayCgpen;
	}

	public void setDisplayCgpen(String displayCgpen) {
		this.displayCgpen = displayCgpen;
	}

	public String getDanId() {
		return danId;
	}

	public void setDanId(String danId) {
		this.danId = danId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	

	public double getOutstandingAmt() {
		return outstandingAmt;
	}

	public void setOutstandingAmt(double outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}



	public String getDciGuaranteeeSDate() {
		return dciGuaranteeeSDate;
	}

	public void setDciGuaranteeeSDate(String dciGuaranteeeSDate) {
		this.dciGuaranteeeSDate = dciGuaranteeeSDate;
	}

	public String getSsiName() {
		return ssiName;
	}

	public void setSsiName(String ssiName) {
		this.ssiName = ssiName;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getCgpan() {
		System.out.println("cgpan getter=="+Cgpan);
		return Cgpan;
	}

	public void setCgpan(String cgpan) {
		System.out.println("cgpan setter=="+cgpan);
		Cgpan = cgpan;
	}

	
	
	
}
