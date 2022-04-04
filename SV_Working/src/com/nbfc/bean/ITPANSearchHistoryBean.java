package com.nbfc.bean;

import javax.validation.constraints.NotNull;

public class ITPANSearchHistoryBean {

	private String memBankName;
	private String ssiUnitName;
	private String ssiCinstititupan;
	private String cgpanNumber;
	private int guaranteeAmount;
	private String appStatus;
	private String npaDate;
	private String mliId;
	private String itPan;
	private String confidentialFlagStatus;
	private String creditDecisionFlagStatus;
	private String changeSubsequentlyFlagStatus;
	private String noCostFlagStatus;
	private String cibileCheckFlagStatus;
	private String scheamName;
	
	
	public String getScheamName() {
		return scheamName;
	}

	public void setScheamName(String scheamName) {
		this.scheamName = scheamName;
	}

	public String getConfidentialFlagStatus() {
		return confidentialFlagStatus;
	}

	public void setConfidentialFlagStatus(String confidentialFlagStatus) {
		this.confidentialFlagStatus = confidentialFlagStatus;
	}

	public String getCreditDecisionFlagStatus() {
		return creditDecisionFlagStatus;
	}

	public void setCreditDecisionFlagStatus(String creditDecisionFlagStatus) {
		this.creditDecisionFlagStatus = creditDecisionFlagStatus;
	}

	public String getChangeSubsequentlyFlagStatus() {
		return changeSubsequentlyFlagStatus;
	}

	public void setChangeSubsequentlyFlagStatus(String changeSubsequentlyFlagStatus) {
		this.changeSubsequentlyFlagStatus = changeSubsequentlyFlagStatus;
	}

	public String getNoCostFlagStatus() {
		return noCostFlagStatus;
	}

	public void setNoCostFlagStatus(String noCostFlagStatus) {
		this.noCostFlagStatus = noCostFlagStatus;
	}

	public String getCibileCheckFlagStatus() {
		return cibileCheckFlagStatus;
	}

	public void setCibileCheckFlagStatus(String cibileCheckFlagStatus) {
		this.cibileCheckFlagStatus = cibileCheckFlagStatus;
	}

	
	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	
	public String getItPan() {
		return itPan;
	}

	public void setItPan(String itPan) {
		this.itPan = itPan;
	}

	public String getMemBankName() {
		return memBankName;
	}

	public void setMemBankName(String memBankName) {
		this.memBankName = memBankName;
	}

	public String getSsiUnitName() {
		return ssiUnitName;
	}

	public void setSsiUnitName(String ssiUnitName) {
		this.ssiUnitName = ssiUnitName;
	}

	public String getSsiCinstititupan() {
		return ssiCinstititupan;
	}

	public void setSsiCinstititupan(String ssiCinstititupan) {
		this.ssiCinstititupan = ssiCinstititupan;
	}

	public String getCgpanNumber() {
		return cgpanNumber;
	}

	public void setCgpanNumber(String cgpanNumber) {
		this.cgpanNumber = cgpanNumber;
	}

	public int getGuaranteeAmount() {
		return guaranteeAmount;
	}

	public void setGuaranteeAmount(int guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getNpaDate() {
		return npaDate;
	}

	public void setNpaDate(String npaDate) {
		this.npaDate = npaDate;
	}

}
