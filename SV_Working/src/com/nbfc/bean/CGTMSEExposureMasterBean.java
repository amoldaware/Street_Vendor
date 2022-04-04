package com.nbfc.bean;

import java.util.Date;

public class CGTMSEExposureMasterBean {
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
	private String statusDescription;
	private String checkerId;
	private String checkerDate;
	private Float gurantee_fee;
	private Long pay_out_cap;
	private String guranteeCoverage;
	
	
	
	public String getGuranteeCoverage() {
		return guranteeCoverage;
	}
	public void setGuranteeCoverage(String guranteeCoverage) {
		this.guranteeCoverage = guranteeCoverage;
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
	public Date getInsertedOn() {
		return insertedOn;
	}
	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
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
	public String setMliDateOfSanctionOfExposure(String mliDateOfSanctionOfExposure) {
		return this.mliDateOfSanctionOfExposure = mliDateOfSanctionOfExposure;
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

	

}
