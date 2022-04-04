package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class MLIBankIdDetails {

	@Id
	@Column(name = "MEM_BNK_ID")
	private String bank_id;
	@Column(name = "MEM_ZNE_ID")
	private String zone_id;
	@Column(name = "MEM_BRN_ID")
	private String bnr_id;
	@Column(name = "SHORT_NAME")
	private String short_name;
	@Column(name = "LONG_NAME")
	private String long_name;

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public String getBnr_id() {
		return bnr_id;
	}

	public void setBnr_id(String bnr_id) {
		this.bnr_id = bnr_id;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	@Override
	public String toString() {
		return "MLIBankIdDetails [bank_id=" + bank_id + ", zone_id=" + zone_id
				+ ", bnr_id=" + bnr_id + ", short_name=" + short_name
				+ ", long_name=" + long_name + "]";
	}

}
