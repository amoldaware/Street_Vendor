package com.nbfc.helper;

public class CGTMSEMakerBatchUploadDetails {
	public String filePath;
	Integer subPortfolioNo;
	String status;
	Long portfolioNo;
	String portFolioName;
	String sanctionAmount;
	String outstandingAmount;
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	Long noOfFile;
	String fileName;
	Long noOfRecords;
	String quaterName;
	String portfolioQuarter;
	String uploadDate;
	public String nbfcUploadedDate;
	String shortName;
	
	public String getPortFolioName() {
		return portFolioName;
	}
	public void setPortFolioName(String portFolioName) {
		this.portFolioName = portFolioName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getPortfolioNo() {
		return portfolioNo;
	}
	public void setPortfolioNo(Long portfolioNo) {
		this.portfolioNo = portfolioNo;
	}
	public String getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	public Long getNoOfFile() {
		return noOfFile;
	}
	public void setNoOfFile(Long noOfFile) {
		this.noOfFile = noOfFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(Long noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	public String getQuaterName() {
		return quaterName;
	}
	public void setQuaterName(String quaterName) {
		this.quaterName = quaterName;
	}
	public String getPortfolioQuarter() {
		return portfolioQuarter;
	}
	public void setPortfolioQuarter(String portfolioQuarter) {
		this.portfolioQuarter = portfolioQuarter;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getNbfcUploadedDate() {
		return nbfcUploadedDate;
	}
	public void setNbfcUploadedDate(String reportDate) {
		this.nbfcUploadedDate = reportDate;
	}
	
	
}
