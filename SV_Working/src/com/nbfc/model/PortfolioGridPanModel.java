
package com.nbfc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortfolioGridPanModel implements Serializable{
	private static final long serialVersionUID = -723583058586873479L;

   // private Set<SanctionDetailsChild> items;
	
	@Id
	@Column(name = "PORTFOLIO_NO" )
	private Integer portfolio_no;
	
	/*@Column(name = "LONG_NAME" )
	private String long_name;*/
	
	@Column(name="EXPOSURE_ID")
	private String exposureID;
	
	
	public String getExposureID() {
		return exposureID;
	}
	public void setExposureID(String exposureID) {
		this.exposureID = exposureID;
	}
	/*@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="PORTFOLIO_NO")
	private Set<NbfcInterfaceUploadForPortfolioGridPaneModel> nbfcInterfaceUploadForPortfolioGridPaneModel; 
	
	public Set<NbfcInterfaceUploadForPortfolioGridPaneModel> getNbfcInterfaceUploadForPortfolioGridPaneModel() {
		return nbfcInterfaceUploadForPortfolioGridPaneModel;
	}
	public void setNbfcInterfaceUploadForPortfolioGridPaneModel(
			Set<NbfcInterfaceUploadForPortfolioGridPaneModel> nbfcInterfaceUploadForPortfolioGridPaneModel) {
		this.nbfcInterfaceUploadForPortfolioGridPaneModel = nbfcInterfaceUploadForPortfolioGridPaneModel;
	}*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "MAX_PORTFOLIO_SIZE")
	private long max_portfolio_size;	
	
	
	@Column(name="PORTFOLIO_NAME")
	private String PortFolioName;
	
	@Column(name="PORTFOLIO_START_DATE")
	private String portfolioDate;
	
	
	public String getPortfolioDate() {
		return portfolioDate;
	}
	public void setPortfolioDate(String portfolioDate) {
		this.portfolioDate = portfolioDate;
	}
	public String getPortfolioStatus() {
		return portfolioStatus;
	}
	public void setPortfolioStatus(String portfolioStatus) {
		this.portfolioStatus = portfolioStatus;
	}
	@Column(name="PORTFOLIO_STATUS")
	private String portfolioStatus;
	public String getPortFolioName() {
		return PortFolioName;
	}
	public void setPortFolioName(String portFolioName) {
		PortFolioName = portFolioName;
	}
	/*public Set<SanctionDetailsChild> getItems() {
		return items;
	}
	public void setItems(Set<SanctionDetailsChild> items) {
		this.items = items;
	}*/
	public Integer getPortfolio_no() {
		return portfolio_no;
	}
	public void setPortfolio_no(Integer portfolio_no) {
		this.portfolio_no = portfolio_no;
	}
	
	/*public String getLong_name() {
		return long_name;
	}
	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}	*/
	
	public long getMax_portfolio_size() {
		return max_portfolio_size;
	}
	public void setMax_portfolio_size(Long max_portfolio_size) {
		this.max_portfolio_size = max_portfolio_size;
	}	
	
	
	
	

}
