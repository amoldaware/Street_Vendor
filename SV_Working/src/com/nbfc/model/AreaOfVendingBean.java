package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class AreaOfVendingBean {
	@Column(name="stationaryVendor", length=200)
	public String stationaryVendor;
	@Column(name="mobileVendor",length=200)
    public String mobileVendor;
	@Column(name="nearestMobileLM",length=200)
    public String nearestMobileLM;
	@Column(name="vDistrictName",length=250)
	public String vDistrictName;
	@Column(name="vStateName",length=250)
	public String vStateName;
	/*@Column(name="wardNo")
    public String wardNo;
	@Column(name="districtCode")
    public String districtCode;
	@Column(name="pinCode")
    public String pinCode;*/
	@Column(name="ulbName",length=200)
    public String ulbName;
	public String getStationaryVendor() {
		return stationaryVendor;
	}
	public String getMobileVendor() {
		return mobileVendor;
	}
	public String getNearestMobileLM() {
		return nearestMobileLM;
	}
	/*public String getWardNo() {
		return wardNo;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public String getPinCode() {
		return pinCode;
	}*/
	public String getUlbName() {
		return ulbName;
	}
	public void setStationaryVendor(String stationaryVendor) {
		this.stationaryVendor = stationaryVendor;
	}
	public void setMobileVendor(String mobileVendor) {
		this.mobileVendor = mobileVendor;
	}
	public void setNearestMobileLM(String nearestMobileLM) {
		this.nearestMobileLM = nearestMobileLM;
	}
	/*public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}*/
	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}
	public String getvDistrictName() {
		return vDistrictName;
	}
	public String getvStateName() {
		return vStateName;
	}
	public void setvDistrictName(String vDistrictName) {
		this.vDistrictName = vDistrictName;
	}
	public void setvStateName(String vStateName) {
		this.vStateName = vStateName;
	}
	
}
