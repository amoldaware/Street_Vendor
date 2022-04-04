package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="NBFC_DAN_CGPAN_INFO_temp")
public class NBFCAppropriationModelUpdate implements Serializable{
	@Id
	/*@Column(name="DAN_ID")
	private String danId;*/
	@Column(name="PAY_ID")
	private String rpNumber;
	@Column(name="DCI_GUARANTEE_START_DT")
	private Date guaranteeStartDate;
	@Column(name="DCI_APPROPRIATION_FLAG")
	private String dciAppropriationFlag;
	
	
	
	public String getDciAppropriationFlag() {
		return dciAppropriationFlag;
	}
	
	/*private NBFCAppropriationModel nbfcAppropriate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAY_ID", nullable = false)
	
	public NBFCAppropriationModel getNbfcAppropriate() {
		return nbfcAppropriate;
	}
	public void setNbfcAppropriate(NBFCAppropriationModel nbfcAppropriate) {
		this.nbfcAppropriate = nbfcAppropriate;
	}*/
	public void setDciAppropriationFlag(String dciAppropriationFlag) {
		this.dciAppropriationFlag = dciAppropriationFlag;
	}
	@Column(name="DCI_APPROPRIATION_BY")
	private String approvedBy;
	/*public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}*/
	public Date getGuaranteeStartDate() {
		return guaranteeStartDate;
	}
	public void setGuaranteeStartDate(Date guaranteeStartDate) {
		this.guaranteeStartDate = guaranteeStartDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getRpNumber() {
		return rpNumber;
	}
	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}
	
}
