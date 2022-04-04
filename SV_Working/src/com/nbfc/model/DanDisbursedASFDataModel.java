package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Embeddable
@Table(name="ALL_ASF_DAN__VW")
public class DanDisbursedASFDataModel implements Serializable {

	@Id
	@Column(name = "ID")
	private int id;	
	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	@Column(name = "OUTSTANDING_AMOUNT")
	private String outstandingAmount;
	
	@Column(name = "SANCTIONED_AMOUNT")
	private String sanction_Amount;
	public String getSanction_Amount() {
		return sanction_Amount;
	}
	public void setSanction_Amount(String sanction_Amount) {
		this.sanction_Amount = sanction_Amount;
	}
	@Column(name = "SANCTION_DATE")
	private Date sanctionDate;
	@Column(name = "FILE_ID")
	private String fileName;
	
	@Column(name="IGST_AMT")
	private double igstAmount;
	@Column(name="IGST_RATE")
	private double igstRate;
	@Column(name="SGST_AMT")
	private double sgstAmount;
	@Column(name="SGST_RATE")
	private double sgstRate;

	
	
	@Column(name="CGST_RATE")
	private double cgstRate;
	@Column(name="LOAN_ACCOUNT_NO")	
	private String loanAccountNo;
	@Column(name="BASE_AMOUNT")	
	private long baseAmount;
	@Column(name="PORTFOLIO_RATE")	
	private double portfolioRate;
	@Column(name="DAN_AMOUNT")	
	private double danAmount;
	
	@Column(name="CGPAN")	
	private String cgpan;
	
	@Column(name="DAN_ID")	
	private String dan_id;
	@Column(name="CGST_AMT")
	private double cgstAmount;
	public String getCgpan() {
		return cgpan;
	}
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}
	public String getDan_id() {
		return dan_id;
	}
	public void setDan_id(String dan_id) {
		this.dan_id = dan_id;
	}
	public double getDanAmount() {
		return danAmount;
	}
	public void setDanAmount(double danAmount) {
		this.danAmount = danAmount;
	}
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
	public String getLoanAccountNo() {
		return loanAccountNo;
	}
	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public Date getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public double getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(double igstAmount) {
		this.igstAmount = igstAmount;
	}
	public double getIgstRate() {
		return igstRate;
	}
	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}
	public double getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public double getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}
	public double getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public double getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(double cgstRate) {
		this.cgstRate = cgstRate;
	}
	
	
	
}
