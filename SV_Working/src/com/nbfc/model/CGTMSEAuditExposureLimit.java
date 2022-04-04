package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.TableGenerator;

@Entity
@Embeddable
@Table(name = "NBFC_AUDIT_EXPOSURE_LIMIT")
public class CGTMSEAuditExposureLimit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_generator")
	@TableGenerator(name = "employee_generator", table = "pk_table", pkColumnName = "name", valueColumnName = "value", allocationSize = 1)
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
	private String makerDate;

	@Column(name = "FROM_DATE")
	private String fromDate;

	@Column(name = "TO_DATE")
	private String toDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "EXPOSURE_SANCTION_DATE")
	private String exposureSanctionDate;

	@Column(name = "CHECKER_ID")
	private String checkerId;

	@Column(name = "CHECKER_DATE")
	private String checkerDate;

	@Column(name = "STATUS_DESCRIPTION")
	private String statusDescription;

	@Column(name = "MODIFIED_EXPOSURE_DATE")
	private String modifiedExposureDate;

	@Column(name = "REMARKS")
	private String remarks;

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

	public String getModifiedExposureDate() {
		return modifiedExposureDate;
	}

	public void setModifiedExposureDate(String modifiedExposureDate) {
		this.modifiedExposureDate = modifiedExposureDate;
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

	public String getMakerDate() {
		return makerDate;
	}

	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getExposureSanctionDate() {
		return exposureSanctionDate;
	}

	public void setExposureSanctionDate(String exposureSanctionDate) {
		this.exposureSanctionDate = exposureSanctionDate;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckerDate() {
		return checkerDate;
	}

	public void setCheckerDate(String checkerDate) {
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

	@Override
	public String toString() {
		return "CGTMSEAuditExposureLimit [exposureId=" + exposureId
				+ ", memBnkId=" + memBnkId + ", memZneId=" + memZneId
				+ ", memBrnId=" + memBrnId + ", exposureLimit=" + exposureLimit
				+ ", makerId=" + makerId + ", makerDate=" + makerDate
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", status="
				+ status + ", exposureSanctionDate=" + exposureSanctionDate
				+ ", checkerId=" + checkerId + ", checkerDate=" + checkerDate
				+ ", statusDescription=" + statusDescription
				+ ", modifiedExposureDate=" + modifiedExposureDate
				+ ", remarks=" + remarks + ", gurantee_fee=" + gurantee_fee
				+ ", pay_out_cap=" + pay_out_cap + ", guranteeCoverage="
				+ guranteeCoverage + ", getGuranteeCoverage()="
				+ getGuranteeCoverage() + ", getGurantee_fee()="
				+ getGurantee_fee() + ", getPay_out_cap()=" + getPay_out_cap()
				+ ", getModifiedExposureDate()=" + getModifiedExposureDate()
				+ ", getExposureId()=" + getExposureId() + ", getMemBnkId()="
				+ getMemBnkId() + ", getMemZneId()=" + getMemZneId()
				+ ", getMemBrnId()=" + getMemBrnId() + ", getExposureLimit()="
				+ getExposureLimit() + ", getMakerId()=" + getMakerId()
				+ ", getMakerDate()=" + getMakerDate() + ", getStatus()="
				+ getStatus() + ", getFromDate()=" + getFromDate()
				+ ", getToDate()=" + getToDate()
				+ ", getExposureSanctionDate()=" + getExposureSanctionDate()
				+ ", getCheckerId()=" + getCheckerId() + ", getCheckerDate()="
				+ getCheckerDate() + ", getStatusDescription()="
				+ getStatusDescription() + ", getRemarks()=" + getRemarks()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}


