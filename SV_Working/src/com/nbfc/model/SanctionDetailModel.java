package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Retrive the Long Name in DropDown
@Entity
@Embeddable
@Table(name = "NBFC_MEMBER_INFO")
public class SanctionDetailModel implements Serializable {
	public SanctionDetailModel() {
		System.out.println("SanctionDetailsGetLongNameModel class called===");
	}

	@Id
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;

	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;

	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;

	@Column(name = "CBD_ID")
	private Integer cbdId;

	@Column(name = "MEM_BANK_NAME")
	private String memBankName;

	@Column(name = "MEM_ZONE_NAME")
	private String memZoneName;

	@Column(name = "MEM_BRANCH_NAME")
	private String memBranchName;

	@Column(name = "SHORT_NAME")
	private String shortName;

	@Column(name = "LONG_NAME")
	private String longName;

	@Column(name = "MEM_ADDRESS")
	private String memAddress;

	@Column(name = "MEM_CITY")
	private String memCity;

	@Column(name = "MEM_PINCODE")
	private String memPinCode;

	@Column(name = "MEM_DISTRICT_NAME")
	private String memDistrictName;

	@Column(name = "MEM_STATE_NAME")
	private String memStateName;

	@Column(name = "MEM_PHONE_CODE")
	private String memPhoneCode;

	@Column(name = "MEM_PHONE_NUMBER")
	private String memPhoneNumber;

	@Column(name = "MEM_FAX_CODE")
	private String memFaxCode;

	@Column(name = "MEM_FAX_NUMBER")
	private String memFaxNumber;

	@Column(name = "MEM_EMAIL")
	private String memEmail;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "MEM_MCGF")
	private String memMCGF;

	@Column(name = "MEM_DAN_DELIVERY_MODE")
	private String memDanDeliveryMode;

	@Column(name = "MEM_REPORTING_ZONE_ID")
	private String memReportingZoneId;

	@Column(name = "MEM_REMARKS")
	private String memRemarks;

	@Column(name = "CREADTED_BY")
	private String creadtedBy;

	@Column(name = "INSERTEDON")
	private String insertedOn;

	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public Integer getCbdId() {
		return cbdId;
	}

	public void setCbdId(Integer cbdId) {
		this.cbdId = cbdId;
	}

	public String getMemBankName() {
		return memBankName;
	}

	public void setMemBankName(String memBankName) {
		this.memBankName = memBankName;
	}

	public String getMemZoneName() {
		return memZoneName;
	}

	public void setMemZoneName(String memZoneName) {
		this.memZoneName = memZoneName;
	}

	public String getMemBranchName() {
		return memBranchName;
	}

	public void setMemBranchName(String memBranchName) {
		this.memBranchName = memBranchName;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemCity() {
		return memCity;
	}

	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}

	public String getMemPinCode() {
		return memPinCode;
	}

	public void setMemPinCode(String memPinCode) {
		this.memPinCode = memPinCode;
	}

	public String getMemDistrictName() {
		return memDistrictName;
	}

	public void setMemDistrictName(String memDistrictName) {
		this.memDistrictName = memDistrictName;
	}

	public String getMemStateName() {
		return memStateName;
	}

	public void setMemStateName(String memStateName) {
		this.memStateName = memStateName;
	}

	public String getMemPhoneCode() {
		return memPhoneCode;
	}

	public void setMemPhoneCode(String memPhoneCode) {
		this.memPhoneCode = memPhoneCode;
	}

	public String getMemPhoneNumber() {
		return memPhoneNumber;
	}

	public void setMemPhoneNumber(String memPhoneNumber) {
		this.memPhoneNumber = memPhoneNumber;
	}

	public String getMemFaxCode() {
		return memFaxCode;
	}

	public void setMemFaxCode(String memFaxCode) {
		this.memFaxCode = memFaxCode;
	}

	public String getMemFaxNumber() {
		return memFaxNumber;
	}

	public void setMemFaxNumber(String memFaxNumber) {
		this.memFaxNumber = memFaxNumber;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemMCGF() {
		return memMCGF;
	}

	public void setMemMCGF(String memMCGF) {
		this.memMCGF = memMCGF;
	}

	public String getMemDanDeliveryMode() {
		return memDanDeliveryMode;
	}

	public void setMemDanDeliveryMode(String memDanDeliveryMode) {
		this.memDanDeliveryMode = memDanDeliveryMode;
	}

	public String getMemReportingZoneId() {
		return memReportingZoneId;
	}

	public void setMemReportingZoneId(String memReportingZoneId) {
		this.memReportingZoneId = memReportingZoneId;
	}

	public String getMemRemarks() {
		return memRemarks;
	}

	public void setMemRemarks(String memRemarks) {
		this.memRemarks = memRemarks;
	}

	public String getCreadtedBy() {
		return creadtedBy;
	}

	public void setCreadtedBy(String creadtedBy) {
		this.creadtedBy = creadtedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInsertedOn() {
		return insertedOn;
	}

	public void setInsertedOn(String insertedOn) {
		this.insertedOn = insertedOn;
	}

}
