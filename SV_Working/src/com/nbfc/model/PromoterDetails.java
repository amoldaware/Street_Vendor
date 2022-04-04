package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTER_DETAIL")
public class PromoterDetails implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "SSI_REFERENCE_NUMBER")
	private int SSI_REFERENCE_NUMBER;
	@Column(name = "PMR_CHIEF_FIRST_NAME")
	private String PMR_CHIEF_FIRST_NAME;
	@Column(name = "PMR_CHIEF_MIDDLE_NAME")
	private String PMR_CHIEF_MIDDLE_NAME;
	@Column(name = "PMR_CHIEF_LAST_NAME")
	private String PMR_CHIEF_LAST_NAME;
	@Column(name = "PMR_CHIEF_IT_PAN")
	private String PMR_CHIEF_IT_PAN;
	
	public String getPMR_CHIEF_LAST_NAME() {
		return PMR_CHIEF_LAST_NAME;
	}

	public void setPMR_CHIEF_LAST_NAME(String pMR_CHIEF_LAST_NAME) {
		PMR_CHIEF_LAST_NAME = pMR_CHIEF_LAST_NAME;
	}

	public int getSSI_REFERENCE_NUMBER() {
		return SSI_REFERENCE_NUMBER;
	}

	public void setSSI_REFERENCE_NUMBER(int sSI_REFERENCE_NUMBER) {
		SSI_REFERENCE_NUMBER = sSI_REFERENCE_NUMBER;
	}

	public String getPMR_CHIEF_FIRST_NAME() {
		return PMR_CHIEF_FIRST_NAME;
	}

	public void setPMR_CHIEF_FIRST_NAME(String pMR_CHIEF_FIRST_NAME) {
		PMR_CHIEF_FIRST_NAME = pMR_CHIEF_FIRST_NAME;
	}

	public String getPMR_CHIEF_MIDDLE_NAME() {
		return PMR_CHIEF_MIDDLE_NAME;
	}

	public void setPMR_CHIEF_MIDDLE_NAME(String pMR_CHIEF_MIDDLE_NAME) {
		PMR_CHIEF_MIDDLE_NAME = pMR_CHIEF_MIDDLE_NAME;
	}

	public String getPMR_CHIEF_IT_PAN() {
		return PMR_CHIEF_IT_PAN;
	}

	public void setPMR_CHIEF_IT_PAN(String pMR_CHIEF_IT_PAN) {
		PMR_CHIEF_IT_PAN = pMR_CHIEF_IT_PAN;
	}

}
