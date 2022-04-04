package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Embeddable
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class CGTMSEExposureMasterCheckerGETExposureDetails implements Serializable{
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
	 
	 @Column(name = "REMARKS")
	 private String remarks;
	
	 @Column(name = "STATUS_DESCRIPTION")
	 private String statusDescription;
	 
	 @Column(name = "FROM_DATE")
	 private Date fromDate;
	 
	 @DateTimeFormat(pattern="dd/MM/yyyy")
	 @Temporal(TemporalType.DATE)
	 @Column(name = "EXPOSURE_SANCTION_DATE")
	 private Date exposureSanctionDate;
	 
	 @DateTimeFormat(pattern="dd/MM/yyyy")
	 @Temporal(TemporalType.DATE)
	 @Column(name = "TO_DATE")
	 private Date toDate;
	 
	 @Column(name = "STATUS")
	 private String status;
	 
	
	 @Column(name = "CHECKER_ID")
	 private String checkerId;
	
	 @Column(name = "CHECKER_DATE")
	 private Date checkerDate;
	 
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

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public Date getCheckerDate() {
		return checkerDate;
	}

	public void setCheckerDate(Date checkerDate) {
		this.checkerDate = checkerDate;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	
	
	


}
