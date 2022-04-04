
package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "NBFC_PORTFOLIO_SUB_DTLS")
public class PortFolioDetailsInChildTBL implements Serializable{
	private static final long serialVersionUID = -723583058586873479L;
	 @ManyToOne
	 @JoinColumn(name="SUB_PORTFOLIO_DTL_NO" , nullable=false, insertable = false, updatable = false)
	 private PortFolioDetailsInParentTBL cart;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_generator")
	@TableGenerator(name = "employee_generator", table = "pk_table", pkColumnName = "name", valueColumnName = "value", allocationSize = 1)	@Column(name = "SUB_PORTFOLIO_DTL_NO")
	public Integer subPortfolioDtlNo;
	
	@Column (name="PORTFOLIO_NO")
	public Integer portfolio_No;
	
	@Column(name = "PORTFOLIO_QUARTER")
	public String portfolioQuarter;

	@Column(name = "SUB_PORTFOLIO_STATUS")
	public String subPortfolioStatus;
	@Column(name = "SUB_PORTFOLIO_CREATED_ON")
	public String subPortfolioCreatedOn;
	@Column(name = "SUB_PORTFOLIO_CREADTED_BY")
	public String subPortfolioCratedBy;
	
	
	@Column(name = "SUB_PORTFOLIO_SERIAL_NO")
	public Integer subPortfolioSerialNo ;

	public Integer getPortfolio_No() {
		return portfolio_No;
	}
	public void setPortfolio_No(Integer portfolio_No) {
		this.portfolio_No = portfolio_No;
	}
	public Integer getSubPortfolioSerialNo() {
		return subPortfolioSerialNo;
	}
	public void setSubPortfolioSerialNo(Integer subPortfolioSerialNo) {
		this.subPortfolioSerialNo = subPortfolioSerialNo;
	}
	
	public Integer getSubPortfolioDtlNo() {
		return subPortfolioDtlNo;
	}
	public void setSubPortfolioDtlNo(Integer subPortfolioDtlNo) {
		this.subPortfolioDtlNo = subPortfolioDtlNo;
	}
	public String getPortfolioQuarter() {
		return portfolioQuarter;
	}
	public void setPortfolioQuarter(String portfolioQuarter) {
		this.portfolioQuarter = portfolioQuarter;
	}

	public String getSubPortfolioStatus() {
		return subPortfolioStatus;
	}
	public void setSubPortfolioStatus(String subPortfolioStatus) {
		this.subPortfolioStatus = subPortfolioStatus;
	}
	public String getSubPortfolioCreatedOn() {
		return subPortfolioCreatedOn;
	}
	public void setSubPortfolioCreatedOn(String subPortfolioCreatedOn) {
		this.subPortfolioCreatedOn = subPortfolioCreatedOn;
	}
	public String getSubPortfolioCratedBy() {
		return subPortfolioCratedBy;
	}
	public void setSubPortfolioCratedBy(String subPortfolioCratedBy) {
		this.subPortfolioCratedBy = subPortfolioCratedBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
