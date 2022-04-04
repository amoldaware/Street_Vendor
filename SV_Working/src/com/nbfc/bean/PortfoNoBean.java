package com.nbfc.bean;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class PortfoNoBean {

	private String portfolio_Number;

	public PortfoNoBean() {
		super();
	}

	public PortfoNoBean(String portfolio_Number) {
		super();
		this.portfolio_Number = portfolio_Number;
	}

	public String getPortfolio_Number() {
		return portfolio_Number;
	}

	@Override
	public String toString() {
		return "PortfoNoBean [portfolio_Number=" + portfolio_Number + "]";
	}

	public void setPortfolio_Number(String portfolio_Number) {
		this.portfolio_Number = portfolio_Number;
	}
	
	
}
