package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PROFORMA_INVOICE_ASF")
public class ProformaInvoiceDetailsASF  implements Serializable{
	@Id
	@Column(name = "PROFORMA_INV_ID")
	private Long PROFORMA_INV_ID;
	
	@Column(name = "DAN_ID")
	private String DAN_ID;
	
	@Column(name = "PORTFOLIO_NAME")
	private String PORTFOLIO_NAME;
	
	@Column(name = "DAN_TYPE")
	private String DAN_TYPE;
	
	public String getDAN_TYPE() {
		return DAN_TYPE;
	}

	public void setDAN_TYPE(String dAN_TYPE) {
		DAN_TYPE = dAN_TYPE;
	}

	public String getPORTFOLIO_NAME() {
		return PORTFOLIO_NAME;
	}

	public void setPORTFOLIO_NAME(String pORTFOLIO_NAME) {
		PORTFOLIO_NAME = pORTFOLIO_NAME;
	}

	@Column(name = "PROFORMA_INVOICE_GEN_DT")
	private String PROFORMA_INVOICE_GEN_DT;

	public Long getPROFORMA_INV_ID() {
		return PROFORMA_INV_ID;
	}

	public void setPROFORMA_INV_ID(Long pROFORMA_INV_ID) {
		PROFORMA_INV_ID = pROFORMA_INV_ID;
	}

	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	public String getPROFORMA_INVOICE_GEN_DT() {
		return PROFORMA_INVOICE_GEN_DT;
	}

	public void setPROFORMA_INVOICE_GEN_DT(String pROFORMA_INVOICE_GEN_DT) {
		PROFORMA_INVOICE_GEN_DT = pROFORMA_INVOICE_GEN_DT;
	}

	
}
