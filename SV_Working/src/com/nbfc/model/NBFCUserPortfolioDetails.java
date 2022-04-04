package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class NBFCUserPortfolioDetails {

	@Id
	@Column(name = "LOAN_ACCOUNT_NO")
	private String LOAN_ACCOUNT_NO;
	@Column(name = "PORTFOLIO_NAME")
	private String PORTFOLIO_NAME;
	@Column(name = "SANCTIONED_AMOUNT")
	private String SANCTIONED_AMOUNT;
	@Column(name = "MICRO_SMALL")
	private String MICRO_SMALL;

	public String getLOAN_ACCOUNT_NO() {
		return LOAN_ACCOUNT_NO;
	}

	public void setLOAN_ACCOUNT_NO(String lOAN_ACCOUNT_NO) {
		LOAN_ACCOUNT_NO = lOAN_ACCOUNT_NO;
	}

	public String getPORTFOLIO_NAME() {
		return PORTFOLIO_NAME;
	}

	public void setPORTFOLIO_NAME(String pORTFOLIO_NAME) {
		PORTFOLIO_NAME = pORTFOLIO_NAME;
	}

	public String getSANCTIONED_AMOUNT() {
		return SANCTIONED_AMOUNT;
	}

	public void setSANCTIONED_AMOUNT(String sANCTIONED_AMOUNT) {
		SANCTIONED_AMOUNT = sANCTIONED_AMOUNT;
	}

	public String getMICRO_SMALL() {
		return MICRO_SMALL;
	}

	public void setMICRO_SMALL(String mICRO_SMALL) {
		MICRO_SMALL = mICRO_SMALL;
	}

}
