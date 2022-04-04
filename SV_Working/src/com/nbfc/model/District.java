package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Saurav Tyagi 2017
 * 
 */

@Entity
@Table(name = "NBFC_DISTRICT_MASTER")
public class District {
	
	@Id
	@Column(name = "DST_CODE")
	private String DST_CODE;
	
	@Column(name = "STE_CODE")
	private String ste_code;
	@Column(name = "DST_NAME")
	private String dst_name;
	public String getSte_code() {
		return ste_code;
	}
	public void setSte_code(String ste_code) {
		this.ste_code = ste_code;
	}
	public String getDst_name() {
		return dst_name;
	}
	public void setDst_name(String dst_name) {
		this.dst_name = dst_name;
	}
	public String getDST_CODE() {
		return DST_CODE;
	}
	public void setDST_CODE(String dST_CODE) {
		DST_CODE = dST_CODE;
	}

	
}
