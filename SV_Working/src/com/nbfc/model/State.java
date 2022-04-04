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
@Table(name = "NBFC_MEMBER_INFO")
public class State {
	@Id
	@Column(name="SHORT_NAME")
	private String ste_code;
	@Column(name="LONG_NAME")
	private String ste_name;
	@Column(name="STATUS")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "State [ste_code=" + ste_code + ", ste_name=" + ste_name
				+ ", status=" + status + "]";
	}
	public String getSte_code() {
		return ste_code;
	}
	public void setSte_code(String ste_code) {
		this.ste_code = ste_code;
	}
	public String getSte_name() {
		return ste_name;
	}
	public void setSte_name(String ste_name) {
		this.ste_name = ste_name;
	}

	
}
