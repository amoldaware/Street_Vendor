package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_SUB_DTLS")
public class PortfolioNumber {

	@Id
	@Column(name = "SUB_PORTFOLIO_DTL_NO")
	private String sub_portfolio_dt_no;
	@Column(name = "PORTFOLIO_NO")
	private String portfolioNo;
	/*@Column(name="PORTFOLIO_NAME")
	private String portfolio_Name;*/
	public String getSub_portfolio_dt_no() {
		return sub_portfolio_dt_no;
	}
	public void setSub_portfolio_dt_no(String sub_portfolio_dt_no) {
		this.sub_portfolio_dt_no = sub_portfolio_dt_no;
	}
	public String getPortfolioNo() {
		return portfolioNo;
	}
	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}
	public String getPortfolioQuarter() {
		return portfolioQuarter;
	}
	public void setPortfolioQuarter(String portfolioQuarter) {
		this.portfolioQuarter = portfolioQuarter;
	}
	
	public int getSubPortfolioSerialNO() {
		return subPortfolioSerialNO;
	}
	public void setSubPortfolioSerialNO(int subPortfolioSerialNO) {
		this.subPortfolioSerialNO = subPortfolioSerialNO;
	}

	@Column(name = "PORTFOLIO_QUARTER")
	private String portfolioQuarter;
	@Column(name = "SUB_PORTFOLIO_SERIAL_NO")
	private int subPortfolioSerialNO;

	}
