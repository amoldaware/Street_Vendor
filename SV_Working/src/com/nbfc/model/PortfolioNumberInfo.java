package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortfolioNumberInfo {

	@Id
	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	@Column(name = "PORTFOLIO_NO")
	private String portfolioNUmber;

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getPortfolioNUmber() {
		return portfolioNUmber;
	}

	public void setPortfolioNUmber(String portfolioNUmber) {
		this.portfolioNUmber = portfolioNUmber;
	}

}
