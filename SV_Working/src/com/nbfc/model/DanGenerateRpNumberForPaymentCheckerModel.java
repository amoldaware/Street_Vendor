package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="nbfc_DAN_CGPAN_INFO_TEMP")
public class DanGenerateRpNumberForPaymentCheckerModel implements Serializable {
	@Id
	@Column(name="DAN_ID")
	private String danId;
	
	@Column(name="DCI_AMOUNT_RAISED")
	private String virtualAccountNumber;
	
	@Column(name="DCI_BASE_AMT")
	private Double amount;
	@Column(name="DCI_ALLOCATION_DT")
	private Date date;
	@Column(name="APPROVAL_STATUS")
	private String status;
	@Column(name="PAY_ID")
	private String rpNumber;
	
	@Column(name="REMARK")
	private String remark;
	@Column(name="IGST_AMT")
	private String igstAmount;
	@Column(name="IGST_RATE")
	private String igstRate;
	@Column(name="SGST_AMT")
	private String sgstAmount;
	@Column(name="SGST_RATE")
	private String sgstRate;

	@Column(name="CGST_AMT")
	private String cgstAmount;
	@Column(name="CGST_RATE")
	private String cgstRate;
	public String getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(String igstAmount) {
		this.igstAmount = igstAmount;
	}
	public String getIgstRate() {
		return igstRate;
	}
	public void setIgstRate(String igstRate) {
		this.igstRate = igstRate;
	}
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public String getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(String sgstRate) {
		this.sgstRate = sgstRate;
	}
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public String getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(String cgstRate) {
		this.cgstRate = cgstRate;
	}


	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="DAN_ID")
	private Set<DisburseNonDisburseModel> disburseNonDisburseModel; 
	
	
	public Set<DisburseNonDisburseModel> getDisburseNonDisburseModel() {
		return disburseNonDisburseModel;
	}
	public void setDisburseNonDisburseModel(
			Set<DisburseNonDisburseModel> disburseNonDisburseModel) {
		this.disburseNonDisburseModel = disburseNonDisburseModel;
	}*/
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="DAN_ID")
	private Set<DanCorrespondigDataModel> danCorrespondigDataModel; 
	
	
	public Set<DanCorrespondigDataModel> getDanCorrespondigDataModel() {
		return danCorrespondigDataModel;
	}
	public void setDanCorrespondigDataModel(
			Set<DanCorrespondigDataModel> danCorrespondigDataModel) {
		this.danCorrespondigDataModel = danCorrespondigDataModel;
	}*/
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRpNumber() {
		return rpNumber;
	}
	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}
	public String getVirtualAccountNumber() {
		return virtualAccountNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setVirtualAccountNumber(String virtualAccountNumber) {
		this.virtualAccountNumber = virtualAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
