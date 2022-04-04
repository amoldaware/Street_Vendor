package com.nbfc.bean;

public class DanCorrespondingDataListBeanCheckerBean {

	
	private String portfolioName;
	private String fileName;
	private String totalFee;
	private String sanctionAmount;
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	private String outstandingAmount;

	private String sanctionDate;
	private String borrowerName;
	private String igstAmount;
	private String sgstAmount;
	private String cgstAmount;
	private String danId;
	private String loanAccNo;
	private String cgpan;
	private double danAmount;
	
	
	public String getLoanAccNo() {
		return loanAccNo;
	}
	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}
	public String getCgpan() {
		return cgpan;
	}
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}
	public double getDanAmount() {
		return danAmount;
	}
	public void setDanAmount(double danAmount) {
		this.danAmount = danAmount;
	}
	private long baseAmount;
	private double portfolioRate;
	public double getPortfolioRate() {
		return portfolioRate;
	}
	public void setPortfolioRate(double portfolioRate) {
		this.portfolioRate = portfolioRate;
	}
	public long getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(long baseAmount) {
		this.baseAmount = baseAmount;
	}
	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	private String igstRate;
	private String sgstRate;
	private String cgstRate;
	public String getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(String igstAmount) {
		this.igstAmount = igstAmount;
	}
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public String getIgstRate() {
		return igstRate;
	}
	public void setIgstRate(String igstRate) {
		this.igstRate = igstRate;
	}
	public String getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(String sgstRate) {
		this.sgstRate = sgstRate;
	}
	public String getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(String cgstRate) {
		this.cgstRate = cgstRate;
	}
	public String getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	public String getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	private boolean chcktbl;
	
	
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public boolean isChcktbl() {
		return chcktbl;
	}
	public void setChcktbl(boolean chcktbl) {
		this.chcktbl = chcktbl;
	}
	public boolean isSelectAll() {
		return selectAll;
	}
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}
	private boolean selectAll;
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	
	


}
