package com.nbfc.helper;

public class CGTMSECheckerBatchUploadsPendingForApprovalHelper {
	public String excelFileFullName;
	public Integer quaterNumber;
	public String mliName;
	public Long noOfFiles;
	public Integer portfolioNumber;
	public String portFolioName;
	public String status;
	public String totSanctionAmt;
	public String outstandingAmount;
	
	
	public String getPortFolioName() {
		return portFolioName;
	}
	public void setPortFolioName(String portFolioName) {
		this.portFolioName = portFolioName;
	}
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String uploadedExcelFileId;
	public String excelUploadedDate;
	public Long totalNoOfRecordsUploadedInExcelFile;
	String quaterName;
	public String getQuaterName() {
		return quaterName;
	}
	public void setQuaterName(String quaterName) {
		this.quaterName = quaterName;
	}
	public Long getTotalNoOfRecordsUploadedInExcelFile() {
		return totalNoOfRecordsUploadedInExcelFile;
	}
	public void setTotalNoOfRecordsUploadedInExcelFile(
			Long totalNoOfRecordsUploadedInExcelFile2) {
		this.totalNoOfRecordsUploadedInExcelFile = totalNoOfRecordsUploadedInExcelFile2;
	}
	public String getExcelFileFullName() {
		return excelFileFullName;
	}
	public void setExcelFileFullName(String excelFileFullName) {
		this.excelFileFullName = excelFileFullName;
	}
	public Integer getQuaterNumber() {
		return quaterNumber;
	}
	public void setQuaterNumber(Integer quaterNumber) {
		this.quaterNumber = quaterNumber;
	}
	public String getMliName() {
		return mliName;
	}
	public void setMliName(String mliName) {
		this.mliName = mliName;
	}
	public Long getNoOfFiles() {
		return noOfFiles;
	}
	public void setNoOfFiles(Long noOfFiles) {
		this.noOfFiles = noOfFiles;
	}
	public Integer getPortfolioNumber() {
		return portfolioNumber;
	}
	public void setPortfolioNumber(Integer portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotSanctionAmt() {
		return totSanctionAmt;
	}
	public void setTotSanctionAmt(String totSanctionAmt) {
		this.totSanctionAmt = totSanctionAmt;
	}
	public String getUploadedExcelFileId() {
		return uploadedExcelFileId;
	}
	public void setUploadedExcelFileId(String uploadedExcelFileId) {
		this.uploadedExcelFileId = uploadedExcelFileId;
	}
	public String getExcelUploadedDate() {
		return excelUploadedDate;
	}
	public void setExcelUploadedDate(String excelUploadedDate) {
		this.excelUploadedDate = excelUploadedDate;
	}

}
