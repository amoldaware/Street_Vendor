
package com.nbfc.bean;


public class PortfolioGridPanBean {
	public PortfolioGridPanBean(){
		
	}
	
	private Integer ID;

	private String long_name;
	private String portfolioNo;
	private String portfolioName;
	private String short_name;
	private String nameSearch;
	private long max_portfolio_size;
	//private Float pay_out_cap;
	private String portfolioStartDate;
	

	public String getPortfolioStartDate() {
		return portfolioStartDate;
	}

	public void setPortfolioStartDate(String portfolioStartDate) {
		this.portfolioStartDate = portfolioStartDate;
	}

	private String portfolioStatus;

	//private Double rating;
	private String searchValue;


	public String getTotalSantionAmount() {
		return totalSantionAmount;
	}

	public void setTotalSantionAmount(String totalSantionAmount) {
		this.totalSantionAmount = totalSantionAmount;
	}

	private String totalSantionAmount;

	public String getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	
	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	/*public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}*/

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}


	

	//private Double exposure_limit;
	
	//private Float gurantee_fee;
	
	//private Integer portfolio_life;
	
	
	//private String  financial_year;
	//private String Utilized_exposure_limit;
	
	
	
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	/*public Double getExposure_limit() {
		return exposure_limit;
	}

	public void setExposure_limit(Double expLimit) {
		this.exposure_limit = expLimit;
	}*/

	public long getMax_portfolio_size() {
		return max_portfolio_size;
	}

	public void setMax_portfolio_size(Long utilisiedExposureLimit) {
		this.max_portfolio_size = utilisiedExposureLimit;
	}

	

	/*public Integer getPortfolio_life() {
		return portfolio_life;
	}

	public void setPortfolio_life(Integer portfolio_life) {
		this.portfolio_life = portfolio_life;
	}

	

	
	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
*/

	/*public Float getGurantee_fee() {
		return gurantee_fee;
	}

	public void setGurantee_fee(Float gurantee_fee) {
		this.gurantee_fee = gurantee_fee;
	}*/

	/*public Float getPay_out_cap() {
		return pay_out_cap;
	}*/

	

	public String getPortfolioStatus() {
		return portfolioStatus;
	}

	public void setPortfolioStatus(String portfolioStatus) {
		this.portfolioStatus = portfolioStatus;
	}

	/*public void setPay_out_cap(Float pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}*/
	
	
}
