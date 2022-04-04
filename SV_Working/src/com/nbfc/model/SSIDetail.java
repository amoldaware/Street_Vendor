package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SSI_DETAIL")
public class SSIDetail implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@Column(name = "SSI_REFERENCE_NUMBER")
	private int SSI_REFERENCE_NUMBER;
	@Column(name = "SSI_UNIT_NAME")
	private String SSI_UNIT_NAME;
	@Column(name = "SSI_ADDRESS")
	private String SSI_ADDRESS;

	public int getSSI_REFERENCE_NUMBER() {
		return SSI_REFERENCE_NUMBER;
	}

	public void setSSI_REFERENCE_NUMBER(int sSI_REFERENCE_NUMBER) {
		SSI_REFERENCE_NUMBER = sSI_REFERENCE_NUMBER;
	}

	public String getSSI_UNIT_NAME() {
		return SSI_UNIT_NAME;
	}

	public void setSSI_UNIT_NAME(String sSI_UNIT_NAME) {
		SSI_UNIT_NAME = sSI_UNIT_NAME;
	}

	public String getSSI_ADDRESS() {
		return SSI_ADDRESS;
	}

	public void setSSI_ADDRESS(String sSI_ADDRESS) {
		SSI_ADDRESS = sSI_ADDRESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
