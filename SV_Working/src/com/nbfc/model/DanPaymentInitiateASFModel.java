
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
@Table(name="ALL_PAYID_ASF_GEN_VW")
public class DanPaymentInitiateASFModel implements Serializable {
	@Id
	@Column(name="ID")
	private int id;
	/*@Column(name="DAN_ID")
	private String danId;*/
	
	@Column(name="PAYMENT_STATUS")
	private String status;
	@Column(name="VIRTUAL_ACCOUNT_NO")
	private String virtualAccountNumber;
	
	
	@Column(name="DAN_FSTATUS")
	private String danStatus;
	
	
	public String getDanStatus() {
		return danStatus;
	}

	public void setDanStatus(String danStatus) {
		this.danStatus = danStatus;
	}

	public String getVirtualAccountNumber() {
		return virtualAccountNumber;
	}

	public void setVirtualAccountNumber(String virtualAccountNumber) {
		this.virtualAccountNumber = virtualAccountNumber;
	}

	@Column(name="PAY_ID")
	private String rpNumber;
	@Column(name="AMOUNT")
	//private long amount;
	private double amount;
	
	@Column(name="DCI_ALLOCATION_DT")
	private Date date;
	
	@Column(name="MLI_ID")
	private String mliId;
	

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getDanId() {
		return danId;
	}

	public void setDanId(String danId) {
		this.danId = danId;
	}*/

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	
	
	
	
	
	
}
