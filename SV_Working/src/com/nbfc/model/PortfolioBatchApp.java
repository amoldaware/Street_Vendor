package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nbfc_interface_upload")
public class PortfolioBatchApp {

	@Id
	@Column(name = "PORTFOLIO_NO")
	private String arp_ref_no;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name = "VERIFIEDSTATUS")
	private String verifiedStatus;
	
	@Column(name = "PORTFOLIO_NAME")
	private String portfolio_name;

	public String getPortfolio_name() {
		return portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}

	public String getVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(String verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
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

	@Override
	public String toString() {
		return "PortfolioBatchApp [arp_ref_no=" + arp_ref_no + ", status="
				+ status + ", verifiedStatus=" + verifiedStatus
				+ ", portfolio_name=" + portfolio_name + "]";
	}

	

}
