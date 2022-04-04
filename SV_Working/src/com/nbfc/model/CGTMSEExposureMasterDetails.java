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
public class CGTMSEExposureMasterDetails implements Serializable{
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
	 
	 @Column(name = "TO_DATE")
	 private Date toDate;
	 
	 @Column(name = "STATUS")
	 private String status;
	
	 @Column(name = "EXPOSURE_SANCTION_DATE")
	 private Date exposureSanctionDate;
	 
	 @Column(name = "CHECKER_ID")
	 private String checkerId;
	
	 @Column(name = "CHECKER_DATE")
	 private Date checkerDate;
	 
	 @Column(name = "REMARKS")
	 private String remarks;
	 
	 @Column(name = "STATUS_DESCRIPTION")
	 private String statusDescription;
	 
	 @Column(name = "GURANTEE_FEE")
	 private Float gurantee_fee;
	
	 
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

	public Date getExposureSanctionDate() {
		return exposureSanctionDate;
	}

	public void setExposureSanctionDate(Date exposureSanctionDate) {
		this.exposureSanctionDate = exposureSanctionDate;
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

	

	public Long getPay_out_cap() {
		return pay_out_cap;
	}

	public void setPay_out_cap(Long pay_out_cap) {
		this.pay_out_cap = pay_out_cap;
	}

	@Override
	public String toString() {
		return "CGTMSEExposureMasterDetails [exposureId=" + exposureId
				+ ", memBnkId=" + memBnkId + ", memZneId=" + memZneId
				+ ", memBrnId=" + memBrnId + ", exposureLimit=" + exposureLimit
				+ ", makerId=" + makerId + ", makerDate=" + makerDate
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", status="
				+ status + ", exposureSanctionDate=" + exposureSanctionDate
				+ ", checkerId=" + checkerId + ", checkerDate=" + checkerDate
				+ ", remarks=" + remarks + ", statusDescription="
				+ statusDescription + ", gurantee_fee=" + gurantee_fee
				+ ", pay_out_cap=" + pay_out_cap + ", guranteeCoverage="
				+ guranteeCoverage + "]";
	}
	 
	 
	   
}
