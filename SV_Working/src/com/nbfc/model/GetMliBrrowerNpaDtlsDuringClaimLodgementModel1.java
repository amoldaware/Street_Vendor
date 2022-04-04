package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class GetMliBrrowerNpaDtlsDuringClaimLodgementModel1 implements Serializable{
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID" )
	Integer interfaceUplaodId;
	@Column(name = "MSE_NAME")
	String mseName;
	@Column(name = "MSE_ADDRESS")
	String mseAddress;
	@Column(name = "CITY")
	String mseCity;
	@Column(name = "DISTRICT")
	String mseDistrict;
	@Column(name = "STATE")
	String mseState;
	@Column(name = "PINCODE")
	String msePinCode;
	
	@Column(name = "MEM_BNK_ID")
	private String memBnkId;
	 
	@Column(name = "MEM_ZNE_ID")
	private String memZneId;
	 
	@Column(name = "MEM_BRN_ID")
	private String memBrnId;
	
	@Column(name = "CGPAN")
	private String cgpan;
	
	
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
	public String getCgpan() {
		return cgpan;
	}
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}
	public Integer getInterfaceUplaodId() {
		return interfaceUplaodId;
	}
	public void setInterfaceUplaodId(Integer interfaceUplaodId) {
		this.interfaceUplaodId = interfaceUplaodId;
	}
	public String getMseName() {
		return mseName;
	}
	public void setMseName(String mseName) {
		this.mseName = mseName;
	}
	public String getMseAddress() {
		return mseAddress;
	}
	public void setMseAddress(String mseAddress) {
		this.mseAddress = mseAddress;
	}
	public String getMseCity() {
		return mseCity;
	}
	public void setMseCity(String mseCity) {
		this.mseCity = mseCity;
	}
	public String getMseDistrict() {
		return mseDistrict;
	}
	public void setMseDistrict(String mseDistrict) {
		this.mseDistrict = mseDistrict;
	}
	public String getMsePinCode() {
		return msePinCode;
	}
	public void setMsePinCode(String msePinCode) {
		this.msePinCode = msePinCode;
	}
	public String getMseState() {
		return mseState;
	}
	public void setMseState(String mseState) {
		this.mseState = mseState;
	}
	
	
	
}
