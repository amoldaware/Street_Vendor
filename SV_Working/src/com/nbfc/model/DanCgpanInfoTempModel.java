
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
@Table(name="NBFC_DAN_CGPAN_INFO_TEMP")
public class DanCgpanInfoTempModel implements Serializable {
	@Id
	@Column(name="DAN_ID")
	private String danId;	
	
	@Column(name="PAY_ID")
	private String payId;	
	
	@Column(name="DCI_GUARANTEE_START_DT")
	private Date Dci_guarantesDate;
	
	@Column(name="DCI_APPROPRIATION_DT")
	private Date Dci_appropriationDate;
	
	@Column(name="IGST_AMT")
	private Double IGST_AMT;
	
	@Column(name="CGST_AMT")
	private Double CGST_AMT;
	
	@Column(name="SGST_AMT")
	private Double SGST_AMT;
	
	public Double getIGST_AMT() {
		return IGST_AMT;
	}

	public void setIGST_AMT(Double iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}

	public Double getCGST_AMT() {
		return CGST_AMT;
	}

	public void setCGST_AMT(Double cGST_AMT) {
		CGST_AMT = cGST_AMT;
	}

	public Double getSGST_AMT() {
		return SGST_AMT;
	}

	public void setSGST_AMT(Double sGST_AMT) {
		SGST_AMT = sGST_AMT;
	}

	public Date getDci_appropriationDate() {
		return Dci_appropriationDate;
	}

	public void setDci_appropriationDate(Date dci_appropriationDate) {
		Dci_appropriationDate = dci_appropriationDate;
	}

	public String getDanId() {
		return danId;
	}

	public void setDanId(String danId) {
		this.danId = danId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Date getDci_guarantesDate() {
		return Dci_guarantesDate;
	}

	public void setDci_guarantesDate(Date dci_guarantesDate) {
		Dci_guarantesDate = dci_guarantesDate;
	}
	
	
}
