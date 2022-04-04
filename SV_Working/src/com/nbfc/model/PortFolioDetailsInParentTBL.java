
package com.nbfc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;
import javax.persistence.OneToMany;







@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortFolioDetailsInParentTBL implements Serializable{
	private static final long serialVersionUID = -723583058586873479L;

	@OneToMany(mappedBy="cart")
    private Set<PortFolioDetailsInChildTBL> items;
	
	@Id
	@Column(name = "PORTFOLIO_NO" )
	private Integer portfolio_no;
	
	@Column(name = "EXPOSURE_ID" )
	private Long exposure_id;
	
	public Long getExposure_id() {
		return exposure_id;
	}

	public void setExposure_id(Long exposure_id) {
		this.exposure_id = exposure_id;
	}

	@Column(name="PORTFOLIO_NAME")
	private String PortFolioName;
	
	 @Column(name = "GURANTEE_FEE")
	 private Float gurantee_fee;
	 
	 @Column(name = "PAY_OUT_CAP")
	 private Long pay_out_cap;
	 @Column(name="PORTFOLIO_BASE_YEAR")
	private String portFolio_baseYrs;
	 

	
	public String getPortFolio_baseYrs() {
		return portFolio_baseYrs;
	}

	public void setPortFolio_baseYrs(String portFolio_baseYrs) {
		this.portFolio_baseYrs = portFolio_baseYrs;
	}

	public Float getGurantee_fee() {
		return gurantee_fee;
	}

	public void setGurantee_fee(Float gurantee_fee) {
		this.gurantee_fee = gurantee_fee;
	}

	
	public Long getPay_out_cap() {
		return pay_out_cap;
	}

	public void setPay_out_cap(Long pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPortfolio_no() {
		return portfolio_no;
	}

	public void setPortfolio_no(Integer portfolio_no) {
		this.portfolio_no = portfolio_no;
	}


	public String getPortFolioName() {
		return PortFolioName;
	}

	public void setPortFolioName(String portFolioName) {
		PortFolioName = portFolioName;
	}

	public Set<PortFolioDetailsInChildTBL> getItems() {
		return items;
	}

	public void setItems(Set<PortFolioDetailsInChildTBL> items) {
		this.items = items;
	}
	
	

}
