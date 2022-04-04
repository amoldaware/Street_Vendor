package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortfolioNumberMaster {
	
	
	@Id
	@Column(name="PORTFOLIO_NO")
	private Integer portfolioNUmber;
	@Column(name="EXPOSURE_ID")
	private String exposureId;
	@Column(name="PORTFOLIO_NAME")
	private String portfolio_name;
	public String getPortfolio_name() {
		return portfolio_name;
	}
	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}
	public Integer getPortfolioNUmber() {
		return portfolioNUmber;
	}
	public void setPortfolioNUmber(Integer portfolioNUmber) {
		this.portfolioNUmber = portfolioNUmber;
	}
	/*public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}*/
	public String getExposureId() {
		return exposureId;
	}
	public void setExposureId(String exposureId) {
		this.exposureId = exposureId;
	}
	
	
}
