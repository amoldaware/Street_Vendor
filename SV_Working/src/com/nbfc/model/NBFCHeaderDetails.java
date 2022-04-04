package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_EXL_HEAD_NM")
public class NBFCHeaderDetails {

	@Id
	@Column(name = "HEADER_ID")
	private Integer HEADER_ID;
	@Column(name = "HEADER_NAME")
	private String HEADER_NAME;

	public Integer getHEADER_ID() {
		return HEADER_ID;
	}

	public void setHEADER_ID(Integer hEADER_ID) {
		HEADER_ID = hEADER_ID;
	}

	public String getHEADER_NAME() {
		return HEADER_NAME;
	}

	public void setHEADER_NAME(String hEADER_NAME) {
		HEADER_NAME = hEADER_NAME;
	}

	@Override
	public String toString() {
		return "NBFCHeaderDetails [HEADER_ID=" + HEADER_ID + ", HEADER_NAME="
				+ HEADER_NAME + "]";
	}

}
