package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Embeddable
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class CGTMSECreateExposureLimitByMaker implements Serializable{
	
	 @Id
	 @Column(name = "EXPOSURE_ID")
	 private Long exposureId;
	 
	 @Column(name = "MEM_BNK_ID")
	 private String memBnkId;
	 
	 @Column(name = "MEM_ZNE_ID")
	 private String memZneId;
	 
	 @Column(name = "MEM_BRN_ID")
	 private String memBrnId;
	 
	 @Column(name = "EXPOSURE_LIMIT")
	 private Long exposureLimit;


	@Column(name = "MAKER_ID")
	 private String makerId;
	 
	 @Column(name = "MAKER_DATE")
	 private Date makerDate;
	 
	 @Column(name = "FROM_DATE")
	 private Date fromDate;
	 
	 @Column(name = "EXPOSURE_SANCTION_DATE")
	 private Date exposureSanctionDate;
	 
	 @Column(name = "TO_DATE")
	 private Date toDate;
	 
	 @Column(name = "STATUS")
	 private String status;
	 
	 @Column(name = "STATUS_DESCRIPTION")
	 private String statusDescription;

	 @Column(name = "GURANTEE_FEE")
	 private Float gurantee_fee;
	 
	 @Column(name = "PORTFOLIO_BASE_YEAR")
	 private String financial_year;
	 
	 @Column(name = "PAY_OUT_CAP")
	 private Long pay_out_cap;
	 
	 @Column(name = "GURANTEE_COVERAGE")
	 private String guranteeCoverage;


	public String getGuranteeCoverage() {
		return guranteeCoverage;
	}

	public void setGuranteeCoverage(String guranteeCoverage) {
		this.guranteeCoverage = guranteeCoverage;
	}

	public Long getExposureId() {
		return exposureId;
	}

	public void setExposureId(Long exposureId) {
		this.exposureId = exposureId;
	}

	public String getMemBnkId() {
		return memBnkId;
	}

	public void setMemBnkId(String memBnkId) {
		this.memBnkId = memBnkId;
	}

	public String getMemZneId() {
		return memZneId;
	}

	public void setMemZneId(String memZneId) {
		this.memZneId = memZneId;
	}

	public String getMemBrnId() {
		return memBrnId;
	}

	public void setMemBrnId(String memBrnId) {
		this.memBrnId = memBrnId;
	}

	public Long getExposureLimit() {
		return exposureLimit;
	}

	public void setExposureLimit(Long exposureLimit) {
		this.exposureLimit = exposureLimit;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public Date getMakerDate() {
		return makerDate;
	}

	public void setMakerDate(Date makerDate) {
		this.makerDate = makerDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getExposureSanctionDate() {
		return exposureSanctionDate;
	}

	public void setExposureSanctionDate(Date exposureSanctionDate) {
		this.exposureSanctionDate = exposureSanctionDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getGurantee_fee() {
		return gurantee_fee;
	}

	public void setGurantee_fee(Float gurantee_fee) {
		this.gurantee_fee = gurantee_fee;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	
	 
	 public Long getPay_out_cap() {
		return pay_out_cap;
	}

	public void setPay_out_cap(Long pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}
