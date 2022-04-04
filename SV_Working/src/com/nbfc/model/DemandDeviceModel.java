package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NBFC_DEMAND_ADVICE_INFO")
public class DemandDeviceModel implements Serializable{
	@Id
	@Column(name="DAN_ID")
	private String danId;	
	
	@Column(name="DAN_TYPE")
	private String Dan_type;
	
	@Column(name="MEM_BNK_ID")
	public String mem_bank_id;
	
	@Column(name="MEM_ZNE_ID")
	public String mem_zne_id;
	
	@Column(name="MEM_BRN_ID")
	public String mem_brn_id;
	
	

	public String getMem_bank_id() {
		return mem_bank_id;
	}

	public void setMem_bank_id(String mem_bank_id) {
		this.mem_bank_id = mem_bank_id;
	}

	public String getMem_zne_id() {
		return mem_zne_id;
	}

	public void setMem_zne_id(String mem_zne_id) {
		this.mem_zne_id = mem_zne_id;
	}

	public String getMem_brn_id() {
		return mem_brn_id;
	}

	public void setMem_brn_id(String mem_brn_id) {
		this.mem_brn_id = mem_brn_id;
	}

	public String getDanId() {
		return danId;
	}

	public void setDanId(String danId) {
		this.danId = danId;
	}

	public String getDan_type() {
		return Dan_type;
	}

	public void setDan_type(String dan_type) {
		Dan_type = dan_type;
	}	
	

	
}
