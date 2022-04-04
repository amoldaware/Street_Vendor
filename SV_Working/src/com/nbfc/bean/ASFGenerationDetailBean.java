package com.nbfc.bean;

import org.springframework.web.multipart.MultipartFile;

public class ASFGenerationDetailBean {
	private MultipartFile file;
	private String CGPAN;
	private String mli_Name;
	public String getMli_Name() {
		return mli_Name;
	}

	public void setMli_Name(String mli_Name) {
		this.mli_Name = mli_Name;
	}

	private String loadAccountNo;
	private String Itpan;
	private Double PrevoutstandingAmt;
	private String Mse_name;
	private String acceptStatus;
	private String portfolioName;
	private String fileId;
	private Double outstanding_amount;
	private String outstanding_date;
	private String from_date;
	private String to_date;
	
	
	

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public Double getOutstanding_amount() {
		return outstanding_amount;
	}

	public void setOutstanding_amount(Double outstanding_amount) {
		this.outstanding_amount = outstanding_amount;
	}

	public String getOutstanding_date() {
		return outstanding_date;
	}

	public void setOutstanding_date(String outstanding_date) {
		this.outstanding_date = outstanding_date;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getAcceptStatus() {
		return acceptStatus;
	}

	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}

	public MultipartFile getFile() {
		return file;
	}
   
	public String getMse_name() {
		return Mse_name;
	}

	public void setMse_name(String mse_name) {
		Mse_name = mse_name;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getCGPAN() {
		return CGPAN;
	}

	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}

	public String getLoadAccountNo() {
		return loadAccountNo;
	}

	public void setLoadAccountNo(String loadAccountNo) {
		this.loadAccountNo = loadAccountNo;
	}

	public String getItpan() {
		return Itpan;
	}

	public void setItpan(String itpan) {
		Itpan = itpan;
	}

	public Double getPrevoutstandingAmt() {
		return PrevoutstandingAmt;
	}

	public void setPrevoutstandingAmt(Double prevoutstandingAmt) {
		PrevoutstandingAmt = prevoutstandingAmt;
	}
	

}
