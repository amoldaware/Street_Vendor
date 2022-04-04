package com.nbfc.bean;

import java.io.Serializable;

public class UtrErrorFileExcelDataBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String claimPaymentVoucher;
	private String claimNumber;
	private String utrDate;
	private String utrNo;
	private String uploadId;
	private String processingFlag;
	private String processingDesc;
	public String getClaimPaymentVoucher() {
		return claimPaymentVoucher;
	}
	public void setClaimPaymentVoucher(String claimPaymentVoucher) {
		this.claimPaymentVoucher = claimPaymentVoucher;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getUtrDate() {
		return utrDate;
	}
	public void setUtrDate(String utrDate) {
		this.utrDate = utrDate;
	}
	public String getUtrNo() {
		return utrNo;
	}
	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public String getProcessingFlag() {
		return processingFlag;
	}
	public void setProcessingFlag(String processingFlag) {
		this.processingFlag = processingFlag;
	}
	public String getProcessingDesc() {
		return processingDesc;
	}
	public void setProcessingDesc(String processingDesc) {
		this.processingDesc = processingDesc;
	}
	
	

}
