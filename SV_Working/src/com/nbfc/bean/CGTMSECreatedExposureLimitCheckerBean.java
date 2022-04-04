package com.nbfc.bean;

import java.util.Date;



public class CGTMSECreatedExposureLimitCheckerBean {
	
	private String mliLongName;
	private String mliShortName;
	private Long mliExposureLimit;
	private String mliDateOfSanctionOfExposure;
	private String mliValidityOfExposureLimitStartDate;
	private String mliValidityOfExposureLimitEndDate;
	private Long exposureId;
	private Long memBnkId;
	private Long memZneId;
	private Long memBrnId;
	private String createdBy;
	private String status;
	private Date insertedOn;
	private String exposureSanctionDate;
	private String fromDate;
	private String toDate;
	private String checkerId;
	private String checkerDate;
	private String statusDescription;
	private String remarks;
	private Float gurantee_fee;
	private String  financial_year;
	private Long pay_out_cap;
	private String guranteeCoverage;
	
	private String auditLongName;

	private String auditShortName;
	
	private Long auditExposureLimit;

	private String auditDateOfSanctionOfExposure;
	
	private String auditFromDate;
	
	private String auditToDate;
	
	private Long AuditExposureId;
	public String getAuditLongName() {
		return auditLongName;
	}


	public void setAuditLongName(String auditLongName) {
		this.auditLongName = auditLongName;
	}


	public String getAuditShortName() {
		return auditShortName;
	}


	public void setAuditShortName(String auditShortName) {
		this.auditShortName = auditShortName;
	}


	public Long getAuditExposureLimit() {
		return auditExposureLimit;
	}


	public void setAuditExposureLimit(Long auditExposureLimit) {
		this.auditExposureLimit = auditExposureLimit;
	}


	public String getAuditDateOfSanctionOfExposure() {
		return auditDateOfSanctionOfExposure;
	}


	public void setAuditDateOfSanctionOfExposure(
			String auditDateOfSanctionOfExposure) {
		this.auditDateOfSanctionOfExposure = auditDateOfSanctionOfExposure;
	}


	public String getAuditFromDate() {
		return auditFromDate;
	}


	public void setAuditFromDate(String auditFromDate) {
		this.auditFromDate = auditFromDate;
	}


	public String getAuditToDate() {
		return auditToDate;
	}


	public void setAuditToDate(String auditToDate) {
		this.auditToDate = auditToDate;
	}


	public Long getAuditExposureId() {
		return AuditExposureId;
	}


	public void setAuditExposureId(Long auditExposureId) {
		AuditExposureId = auditExposureId;
	}


	public String getAuditRemarks() {
		return auditRemarks;
	}


	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}


	public Float getAudGuranteeFee() {
		return audGuranteeFee;
	}


	public void setAudGuranteeFee(Float audGuranteeFee) {
		this.audGuranteeFee = audGuranteeFee;
	}


	public Long getAudPayOutCap() {
		return audPayOutCap;
	}


	public void setAudPayOutCap(Long audPayOutCap) {
		this.audPayOutCap = audPayOutCap;
	}


	public String getAudGuranteeCoverage() {
		return audGuranteeCoverage;
	}


	public void setAudGuranteeCoverage(String audGuranteeCoverage) {
		this.audGuranteeCoverage = audGuranteeCoverage;
	}


	private String auditRemarks;
	private Float audGuranteeFee;
	private Long audPayOutCap;
	private String audGuranteeCoverage;
	
	
	
	
	
	
	
	
	
	
	
	
	public String getGuranteeCoverage() {
		return guranteeCoverage;
	}


	public void setGuranteeCoverage(String guranteeCoverage) {
		this.guranteeCoverage = guranteeCoverage;
	}


	@Override
	public String toString(){
		return "CGTMSECreatedExposureLimitCheckerBean [mliLongName=" + mliLongName + ", mliShortName=" + mliShortName
				+ ", mliExposureLimit=" + mliExposureLimit + ", mliDateOfSanctionOfExposure="
				+ mliDateOfSanctionOfExposure + ", mliValidityOfExposureLimitStartDate="
				+ mliValidityOfExposureLimitStartDate + ", mliValidityOfExposureLimitEndDate="
				+ mliValidityOfExposureLimitEndDate + ", exposureId=" + exposureId + ", memBnkId=" + memBnkId
				+ ", memZneId=" + memZneId + ", memBrnId=" + memBrnId + ", createdBy=" + createdBy + ", status="
				+ status + ", insertedOn=" + insertedOn + ", exposureSanctionDate=" + exposureSanctionDate
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", checkerId=" + checkerId + ", checkerDate="
				+ checkerDate + ", statusDescription=" + statusDescription + ", remarks=" + remarks + "]";
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public Long getExposureId() {
		return exposureId;
	}
	public void setExposureId(Long exposureId) {
		this.exposureId = exposureId;
	}
	public Long getMemBnkId() {
		return memBnkId;
	}
	public void setMemBnkId(Long memBnkId) {
		this.memBnkId = memBnkId;
	}
	public Long getMemZneId() {
		return memZneId;
	}
	public void setMemZneId(Long memZneId) {
		this.memZneId = memZneId;
	}
	public Long getMemBrnId() {
		return memBrnId;
	}
	public void setMemBrnId(Long memBrnId) {
		this.memBrnId = memBrnId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMliLongName() {
		return mliLongName;
	}
	public void setMliLongName(String mliLongName) {
		this.mliLongName = mliLongName;
	}
	public String getMliShortName() {
		return mliShortName;
	}
	public void setMliShortName(String mliShortName) {
		this.mliShortName = mliShortName;
	}
	public Long getMliExposureLimit() {
		return mliExposureLimit;
	}
	public void setMliExposureLimit(Long mliExposureLimit) {
		this.mliExposureLimit = mliExposureLimit;
	}
	public String getMliDateOfSanctionOfExposure() {
		return mliDateOfSanctionOfExposure;
	}
	public void setMliDateOfSanctionOfExposure(String mliDateOfSanctionOfExposure) {
		this.mliDateOfSanctionOfExposure = mliDateOfSanctionOfExposure;
	}
	public String getMliValidityOfExposureLimitStartDate() {
		return mliValidityOfExposureLimitStartDate;
	}
	public void setMliValidityOfExposureLimitStartDate(
			String mliValidityOfExposureLimitStartDate) {
		this.mliValidityOfExposureLimitStartDate = mliValidityOfExposureLimitStartDate;
	}
	public String getMliValidityOfExposureLimitEndDate() {
		return mliValidityOfExposureLimitEndDate;
	}
	public void setMliValidityOfExposureLimitEndDate(
			String mliValidityOfExposureLimitEndDate) {
		this.mliValidityOfExposureLimitEndDate = mliValidityOfExposureLimitEndDate;
	}
	public Date getInsertedOn() {
		return insertedOn;
	}
	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}
	public String getExposureSanctionDate() {
		return exposureSanctionDate;
	}
	public void setExposureSanctionDate(String exposureSanctionDate) {
		this.exposureSanctionDate = exposureSanctionDate;
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
