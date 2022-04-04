package com.nbfc.bean;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class PortfolioBatchBean {
	
	private String arp_ref_no;
	private String status;
	private String portfolio_name;
	
	public String getPortfolio_name() {
		return portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}

	public PortfolioBatchBean() {
		super();
	}
	
	public PortfolioBatchBean(String arp_ref_no, String status) {
		super();
		this.arp_ref_no = arp_ref_no;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "PortfolioBatchBean [arp_ref_no=" + arp_ref_no + ", status="
				+ status + ", portfolio_name=" + portfolio_name + "]";
	}

	public String getArp_ref_no() {
		return arp_ref_no;
	}
	public void setArp_ref_no(String arp_ref_no) {
		this.arp_ref_no = arp_ref_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
