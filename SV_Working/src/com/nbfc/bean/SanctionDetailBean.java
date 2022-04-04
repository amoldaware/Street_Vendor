package com.nbfc.bean;

import java.util.Date;

public class SanctionDetailBean {
	public SanctionDetailBean(){
		
		System.out.println("SanctionDetailsShowInputFormBean class called==");
	}
	
	private Integer ID;

	private String long_name;
	
	private String short_name;
	
	private Double exposure_limit;
	
	private Double max_portfolio_size;
	
	private Float gurantee_fee;
	
	private Integer portfolio_life;
	
	private Float pay_out_cap;
	
	private String dateOfSanction;
	
	private String  financial_year;
	
	private Date portfolio_start_date;

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

	public Double getExposure_limit() {
		return exposure_limit;
	}

	public void setExposure_limit(Double expLimit) {
		this.exposure_limit = expLimit;
	}

	public Double getMax_portfolio_size() {
		return max_portfolio_size;
	}

	public void setMax_portfolio_size(Double utilisiedExposureLimit) {
		this.max_portfolio_size = utilisiedExposureLimit;
	}

	

	public Integer getPortfolio_life() {
		return portfolio_life;
	}

	public void setPortfolio_life(Integer portfolio_life) {
		this.portfolio_life = portfolio_life;
	}

	

	public String getDateOfSanction() {
		return dateOfSanction;
	}

	public void setDateOfSanction(String toDate) {
		this.dateOfSanction = toDate;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public Date getPortfolio_start_date() {
		return portfolio_start_date;
	}

	public void setPortfolio_start_date(Date portfolio_start_date) {
		this.portfolio_start_date = portfolio_start_date;
	}

	public Float getGurantee_fee() {
		return gurantee_fee;
	}

	public void setGurantee_fee(Float gurantee_fee) {
		this.gurantee_fee = gurantee_fee;
	}

	public Float getPay_out_cap() {
		return pay_out_cap;
	}

	public void setPay_out_cap(Float pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}
	
	
}
