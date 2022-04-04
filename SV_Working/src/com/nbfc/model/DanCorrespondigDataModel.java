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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name="all_payid_gen_vw")//
public class DanCorrespondigDataModel implements Serializable{
	
	@Id
	@Column(name = "ID")
	private int id;	
	
	/*@Column(name = "DAN_ID")
	private String danId;*/

	
	@Column(name = "PAY_ID")
	private String rpNumber;
	
	@Column(name = "AMOUNT")
	private String totalFee;
	
	@Column(name="DCI_ALLOCATION_DT")
	private Date date;

	
	@Column(name = "MLI_ID")
	private String mliId;
	

	@Column(name = "DAN_FSTATUS")
	private String status;
	
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="DAN_ID")
	private Set<DanGenerateRpNumberForPaymentCheckerModel> danGenerateRpNumberForPaymentCheckerModel; 
	
	
	public Set<DanGenerateRpNumberForPaymentCheckerModel> getDisburseNonDisburseModel() {
		return danGenerateRpNumberForPaymentCheckerModel;
	}
	public void setDisburseNonDisburseModel(
			Set<DanGenerateRpNumberForPaymentCheckerModel> disburseNonDisburseModel) {
		this.danGenerateRpNumberForPaymentCheckerModel = disburseNonDisburseModel;
	}
	*/
	
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
	}
*/
	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
	
}
