package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class PortfolioAmountCount {

	@Id
	@Column(name = "PORTFOLIO_NO")
	private String portfolioNUmber;
	@Column(name = "SANCTIONED_AMOUNT")
	private String sanctionAmount;
	@Column(name = "OUTSTANDING_AMOUNT")
	private long outStandingAmount;
	@Column(name="STATUS")
	private String status;
	@Column(name="VERIFIEDSTATUS")
	private String verifactionStatus;


	

	public long getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(long outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public String getPortfolioNUmber() {
		return portfolioNUmber;
	}

	public void setPortfolioNUmber(String portfolioNUmber) {
		this.portfolioNUmber = portfolioNUmber;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

}
