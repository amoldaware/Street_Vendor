package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class GetMliBrrowerNpaDtlsDuringClaimLodgementModel2 implements Serializable{
	 @Id
	 @Column(name = "MEM_BNK_ID")
	 private String memBnkId;
	 
	 @Column(name = "MEM_ZNE_ID")
	 private String memZneId;
	 
	 @Column(name = "MEM_BRN_ID")
	 private String memBrnId;
	 
	 
	 @Column(name = "MEM_BANK_NAME")
	 private String memBankName;
	 
	 @Column(name = "MEM_CITY")
	 private String memCity;
	 
	 @Column(name = "MEM_ADDRESS")
	 private String memAddress;
	 
	 @Column(name = "MEM_DISTRICT_NAME")
	 private String memDistrictName;
	 
	 @Column(name = "MEM_STATE_NAME")
	 private String memStateName;
	 
	 @Column(name = "MEM_EMAIL")
	 private String memEmail;
	 
	 @Column(name = "MEM_PHONE_NUMBER")
	 private String memPhoneNumber;
	 
	 @Column(name = "GSTIN_NO")
	 private String memGstinNo;
	 
	 @Column(name = "STATUS")
	 private String status;

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

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
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

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPhoneNumber() {
		return memPhoneNumber;
	}

	public void setMemPhoneNumber(String memPhoneNumber) {
		this.memPhoneNumber = memPhoneNumber;
	}

	public String getMemGstinNo() {
		return memGstinNo;
	}

	public void setMemGstinNo(String memGstinNo) {
		this.memGstinNo = memGstinNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemBankName() {
		return memBankName;
	}

	public void setMemBankName(String memBankName) {
		this.memBankName = memBankName;
	}
	 
	

	
}
