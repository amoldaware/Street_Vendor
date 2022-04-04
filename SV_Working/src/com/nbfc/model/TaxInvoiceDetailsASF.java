
package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_TAX_INVOICE_ASF")
public class TaxInvoiceDetailsASF implements Serializable {
	

	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	@Id
	@Column(name = "TAX_INV_ID")
	private Long TAX_INV_ID;
	
	@Column(name = "DAN_ID")
	private String DAN_ID;
	
	@Column(name = "PORTFOLIO_NAME")
	private String PORTFOLIO_NAME;
	
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
