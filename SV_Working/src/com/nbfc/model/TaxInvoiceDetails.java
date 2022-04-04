package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_GST_TAX_INVOICE")

public class TaxInvoiceDetails implements Serializable {
	

	@Id
	@Column(name = "TAX_INV_ID")
	private Long TAX_INV_ID;
	
	@Column(name = "INV_ID")
	private String INV_ID;
	
	@Column(name = "FY_YEAR")
	private String FY_YEAR;
	
	public String getFY_YEAR() {
		return FY_YEAR;
	}

	public void setFY_YEAR(String fY_YEAR) {
		FY_YEAR = fY_YEAR;
	}

	@Column(name = "PORTFOLIO_NAME")
	private String PORTFOLIO_NAME;
	
	

	public String getINV_ID() {
		return INV_ID;
	}

	public void setINV_ID(String iNV_ID) {
		INV_ID = iNV_ID;
	}

	@Column(name = "TAX_INVOICE_GEN_DT")
	private String TAX_INVOICE_GEN_DT;

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

	public Long getTAX_INV_ID() {
		return TAX_INV_ID;
	}

	public void setTAX_INV_ID(Long tAX_INV_ID) {
		TAX_INV_ID = tAX_INV_ID;
	}

	

	

	public String getTAX_INVOICE_GEN_DT() {
		return TAX_INVOICE_GEN_DT;
	}

	public void setTAX_INVOICE_GEN_DT(String tAX_INVOICE_GEN_DT) {
		TAX_INVOICE_GEN_DT = tAX_INVOICE_GEN_DT;
	}
	
}
