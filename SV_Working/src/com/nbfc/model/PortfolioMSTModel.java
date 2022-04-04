package com.nbfc.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class PortfolioMSTModel {
	private static final long serialVersionUID = -723583058586873479L;

	@OneToMany(mappedBy="cart")
    private Set<SanctionDetailsChild> items;
	
	@Id
	@Column(name = "PORTFOLIO_NO" )
	private Integer portfolio_no;
	
	@Column(name = "EXPOSURE_ID" )
	private Double exposure_id;
	
	
	/*@Column(name = "EXPOSURE_LIMIT")
	private Double exposure_limit;
	*/
	@Column(name = "MAX_PORTFOLIO_SIZE")
	private Double max_portfolio_size;
	
	@Column(name = "GURANTEE_FEE")
	private Float gurantee_fee;
	
	@Column(name = "PORTFOLIO_LIFE_INMONTHS")
	private Integer portfolio_life;
	
	@Column(name = "PAY_OUT_CAP")
	private Float pay_out_cap;
	

	//@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	@Column(name = "PORTFOLIO_SANCTION_DATE")
	private Date dateOfSanction;
	
	@Column(name = "PORTFOLIO_BASE_YEAR")
	private String financial_year;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	//@Column(name = "PORTFOLIO_START_DATE")
	private Date portfolio_start_date;
	
	@Column(name = "PORTFOLIO_STATUS")
	private String portfolio_status;
	
	@Column(name = "CREADTED_BY")
	String creadted_by;
	
	@Column(name = "INSERTEDON")
	private String insertedon;
	
	@Column(name = "TOTAL_UPLOAD_COUNT")
	Integer total_upload_count;
	@Column(name = "PORTFOLIO_AMOUNT")
	private Integer portfolio_amount;
	@Column(name="PORTFOLIO_NAME")
	private String PortFolioName;
	public String getPortFolioName() {
		return PortFolioName;
	}
	public void setPortFolioName(String portFolioName) {
		PortFolioName = portFolioName;
	}
	public Set<SanctionDetailsChild> getItems() {
		return items;
	}
	public void setItems(Set<SanctionDetailsChild> items) {
		this.items = items;
	}
	public Integer getPortfolio_no() {
		return portfolio_no;
	}
	public void setPortfolio_no(Integer portfolio_no) {
		this.portfolio_no = portfolio_no;
	}
	public Double getExposure_id() {
		return exposure_id;
	}
	public void setExposure_id(Double exposure_id) {
		this.exposure_id = exposure_id;
	}
	
	/*public Double getExposure_limit() {
		return exposure_limit;
	}
	public void setExposure_limit(Double exposure_limit) {
		this.exposure_limit = exposure_limit;
	}*/
	public Double getMax_portfolio_size() {
		return max_portfolio_size;
	}
	public void setMax_portfolio_size(Double max_portfolio_size) {
		this.max_portfolio_size = max_portfolio_size;
	}
	public Float getGurantee_fee() {
		return gurantee_fee;
	}
	public void setGurantee_fee(Float gurantee_fee) {
		this.gurantee_fee = gurantee_fee;
	}
	public Integer getPortfolio_life() {
		return portfolio_life;
	}
	public void setPortfolio_life(Integer portfolio_life) {
		this.portfolio_life = portfolio_life;
	}
	public Float getPay_out_cap() {
		return pay_out_cap;
	}
	public void setPay_out_cap(Float pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}
	
	public String getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}
	
	public Date getDateOfSanction() {
		return dateOfSanction;
	}
	public void setDateOfSanction(Date dateOfSanction) {
		this.dateOfSanction = dateOfSanction;
	}
	public Date getPortfolio_start_date() {
		return portfolio_start_date;
	}
	public void setPortfolio_start_date(Date portfolio_start_date) {
		this.portfolio_start_date = portfolio_start_date;
	}
	public String getPortfolio_status() {
		return portfolio_status;
	}
	public void setPortfolio_status(String portfolio_status) {
		this.portfolio_status = portfolio_status;
	}
	public String getCreadted_by() {
		return creadted_by;
	}
	public void setCreadted_by(String creadted_by) {
		this.creadted_by = creadted_by;
	}
	public String getInsertedon() {
		return insertedon;
	}
	public void setInsertedon(String insertedon) {
		this.insertedon = insertedon;
	}
	public Integer getTotal_upload_count() {
		return total_upload_count;
	}
	public void setTotal_upload_count(Integer total_upload_count) {
		this.total_upload_count = total_upload_count;
	}
	public Integer getPortfolio_amount() {
		return portfolio_amount;
	}
	public void setPortfolio_amount(Integer portfolio_amount) {
		this.portfolio_amount = portfolio_amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
