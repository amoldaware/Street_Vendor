package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Embeddable
public class LenderBean {
	@Column(name="lender_bankName")
	private String lender_bankName;
	@Column(name="lender_lenderCode")
	private int lenderCode;
	@Column(name="mli_id")
	private String mli_id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_date")
	private Date upload_date;
	
	public String getLender_bankName() {
		return lender_bankName;
	}
	public void setLender_bankName(String lender_bankName) {
		this.lender_bankName = lender_bankName;
	}
	/*public String getBankName() {
		return bankName;
	}*/
	public int getLenderCode() {
		return lenderCode;
	}
	public String getMli_id() {
		return mli_id;
	}
	public void setMli_id(String mli_id) {
		this.mli_id = mli_id;
	}
	/*public void setBankName(String bankName) {
		this.bankName = bankName;
	}*/
	public void setLenderCode(int lenderCode) {
		this.lenderCode = lenderCode;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
    
}
