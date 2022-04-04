package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortfolioNumInfo {

	@Id
	@Column(name = "PORTFOLIO_NO")
	private String portfolio_Number;
	@Column(name = "PORTFOLIO_BASE_YEAR")
	private String portfolio_base_yer;
	@Column(name = "MAX_PORTFOLIO_SIZE")
	private String senctioned_portfolio;
	@Column(name = "GURANTEE_FEE")
	private String guarantee_commission;
	@Column(name = "PAY_OUT_CAP")
	private String payout_cap;
	@Column(name = "PORTFOLIO_LIFE_INMONTHS")
	private String portfolio_period;
	@Column(name = "PORTFOLIO_START_DATE")
	private String portfolio_start_date;
	@Column(name="PORTFOLIO_NAME")
	private String portfolio_name;
	@Column(name="EXPOSURE_ID")
	private String EXPOSURE_ID;
	
	
	/*private String FILE_ID;
	@Column(name="DAN_ID")
	private String dan_id;
	@Column(name="INTEREST_RATE")
	private String interest_rate;*/
	

	/*
	 * @Column(name = "TYPE_OF_LONE") private String typeOfLone;
	 */

	public String getEXPOSURE_ID() {
		return EXPOSURE_ID;
	}

	public void setEXPOSURE_ID(String eXPOSURE_ID) {
		EXPOSURE_ID = eXPOSURE_ID;
	}

	
	/*public String getFILE_ID() {
		return FILE_ID;
	}

	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}

	public String getDan_id() {
		return dan_id;
	}

	public void setDan_id(String dan_id) {
		this.dan_id = dan_id;
	}

	public String getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
*/
	public String getPortfolio_name() {
		return portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}

	public String getPortfolio_base_yer() {
		return portfolio_base_yer;
	}

	public void setPortfolio_base_yer(String portfolio_base_yer) {
		this.portfolio_base_yer = portfolio_base_yer;
	}

	public String getPortfolio_Number() {
		return portfolio_Number;
	}

	public void setPortfolio_Number(String portfolio_Number) {
		this.portfolio_Number = portfolio_Number;
	}

	public PortfolioNumInfo() {
		super();
	}

	public String getSenctioned_portfolio() {
		return senctioned_portfolio;
	}

	public void setSenctioned_portfolio(String senctioned_portfolio) {
		this.senctioned_portfolio = senctioned_portfolio;
	}

	public String getGuarantee_commission() {
		return guarantee_commission;
	}

	public void setGuarantee_commission(String guarantee_commission) {
		this.guarantee_commission = guarantee_commission;
	}

	public String getPayout_cap() {
		return payout_cap;
	}

	public void setPayout_cap(String payout_cap) {
		this.payout_cap = payout_cap;
	}

	public String getPortfolio_period() {
		return portfolio_period;
	}

	public void setPortfolio_period(String portfolio_period) {
		this.portfolio_period = portfolio_period;
	}

	public String getPortfolio_start_date() throws Exception {
		return portfolio_start_date;
	}

	public void setPortfolio_start_date(String portfolio_start_date) {
		this.portfolio_start_date = portfolio_start_date;
	}

	public PortfolioNumInfo(String portfolio_Number, String portfolio_base_yer,
			String senctioned_portfolio, String guarantee_commission,
			String payout_cap, String portfolio_period,
			String portfolio_start_date) {
		super();
		this.portfolio_Number = portfolio_Number;
		this.portfolio_base_yer = portfolio_base_yer;
		this.senctioned_portfolio = senctioned_portfolio;
		this.guarantee_commission = guarantee_commission;
		this.payout_cap = payout_cap;
		this.portfolio_period = portfolio_period;
		this.portfolio_start_date = portfolio_start_date;

	}

	@Override
	public String toString() {
		return "PortfolioNumInfo [portfolio_Number=" + portfolio_Number
				+ ", portfolio_base_yer=" + portfolio_base_yer
				+ ", senctioned_portfolio=" + senctioned_portfolio
				+ ", guarantee_commission=" + guarantee_commission
				+ ", payout_cap=" + payout_cap + ", portfolio_period="
				+ portfolio_period + ", portfolio_start_date="
				+ portfolio_start_date + "]";
	}

}
