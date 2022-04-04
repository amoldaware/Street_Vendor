package com.nbfc.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="CGTSITEMPUSER.ONLINE_PAYMENT_DETAIL")

public class NBFCAppropriationModel implements Serializable{
	@Id
	@Column(name="PAY_ID")
	private String rpNumber;
	@Column(name="APPROPRIATE_STATUS")
	private String appropriationStatus;
	/*
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="PAY_ID")
	private Set<NBFCAppropriationModelUpdate> nbfcAppropriationModelUpdate; 
	*/
	
	
	/*public Set<NBFCAppropriationModelUpdate> getNbfcAppropriationModelUpdate() {
		return nbfcAppropriationModelUpdate;
	}
	public void setNbfcAppropriationModelUpdate(
			Set<NBFCAppropriationModelUpdate> nbfcAppropriationModelUpdate) {
		this.nbfcAppropriationModelUpdate = nbfcAppropriationModelUpdate;
	}*/
	public String getRpNumber() {
		return rpNumber;
	}
	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}
	public String getAppropriationStatus() {
		return appropriationStatus;
	}
	public void setAppropriationStatus(String appropriationStatus) {
		this.appropriationStatus = appropriationStatus;
	}
}
