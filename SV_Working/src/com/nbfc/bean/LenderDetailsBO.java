package com.nbfc.bean;

import java.io.Serializable;

public class LenderDetailsBO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String bankName;
	private int lenderCode;
	private String fromDate;
	private String toDate;
	private String applicationNo;
	private String status;
	private String guaranteeIssue;
	
	public int getId() {
		return id;
	}
	public String getBankName() {
		return bankName;
	}
	public int getLenderCode() {
		return lenderCode;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setLenderCode(int lenderCode) {
		this.lenderCode = lenderCode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getStatus() {
		return status;
	}
	public String getGuaranteeIssue() {
		return guaranteeIssue;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setGuaranteeIssue(String guaranteeIssue) {
		this.guaranteeIssue = guaranteeIssue;
	}
	
	
}
