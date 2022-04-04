package com.nbfc.bean;

public class PortfolioDetailsBean {

	private String portfolioName;
	private Double expNum;
	private String portfolioNum;
	private Double expLmt;
	private Long payCap;

	private String EXP_NO;
	private Double UTIL_EXP;
	private Double PENDING_EXP;
	private Double GURANTEE_FEE;
	private String GURANTEE_COVERAGE;
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public Double getExpNum() {
		return expNum;
	}
	public void setExpNum(Double expNum) {
		this.expNum = expNum;
	}
	public String getPortfolioNum() {
		return portfolioNum;
	}
	public void setPortfolioNum(String portfolioNum) {
		this.portfolioNum = portfolioNum;
	}
	public Double getExpLmt() {
		return expLmt;
	}
	public void setExpLmt(Double expLmt) {
		this.expLmt = expLmt;
	}
	
	public Long getPayCap() {
		return payCap;
	}
	public void setPayCap(Long payCap) {
		this.payCap = payCap;
	}
	public String getEXP_NO() {
		return EXP_NO;
	}
	public void setEXP_NO(String eXP_NO) {
		EXP_NO = eXP_NO;
	}
	public Double getUTIL_EXP() {
		return UTIL_EXP;
	}
	public void setUTIL_EXP(Double uTIL_EXP) {
		UTIL_EXP = uTIL_EXP;
	}
	public Double getPENDING_EXP() {
		return PENDING_EXP;
	}
	public void setPENDING_EXP(Double pENDING_EXP) {
		PENDING_EXP = pENDING_EXP;
	}
	public Double getGURANTEE_FEE() {
		return GURANTEE_FEE;
	}
	public void setGURANTEE_FEE(Double gURANTEE_FEE) {
		GURANTEE_FEE = gURANTEE_FEE;
	}
	public String getGURANTEE_COVERAGE() {
		return GURANTEE_COVERAGE;
	}
	public void setGURANTEE_COVERAGE(String gURANTEE_COVERAGE) {
		GURANTEE_COVERAGE = gURANTEE_COVERAGE;
	}
	
}
