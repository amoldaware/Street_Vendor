package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nbfc_interface_upload")
public class DanAllClass {
	
	@Id
	@Column(name = "LOAN_ACCOUNT_NO")
	private String LOAN_ACCOUNT_NO;
	
	
	@Column(name="FILE_ID")
	private String FILE_ID;
	@Column(name="DAN_ID")
	private String DAN_ID;
	
	@Column(name = "PORTFOLIO_NAME")
	private String portfolio_Name;

	public String getLOAN_ACCOUNT_NO() {
		return LOAN_ACCOUNT_NO;
	}

	public void setLOAN_ACCOUNT_NO(String lOAN_ACCOUNT_NO) {
		LOAN_ACCOUNT_NO = lOAN_ACCOUNT_NO;
	}

	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	public String getPortfolio_Name() {
		return portfolio_Name;
	}

	public void setPortfolio_Name(String portfolio_Name) {
		this.portfolio_Name = portfolio_Name;
	}

}
