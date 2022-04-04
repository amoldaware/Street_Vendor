package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class PersonalDetailsBean 
{
	@Column(name="applicant_Name",length=200)
    private String applicant_Name;
	@Column(name="applicant_Gender",length=200)
    private String applicant_Gender;
	@Column(name="applicant_DOB",length=200)
    private String applicant_DOB;
	@Column(name="mobileNo",length=200)
    private String mobileNo;
	@Column(name="maritalStatusName",length=200)
    private String maritalStatusName;
	@Column(name="aadhaarNo",length=200)
    private String aadhaarNo;
	/*@Column(name="voterIDCardNo1")
    private String voterIDCardNo;*/
	@Column(name="pwd",length=200)
    private String pwd;
	@Column(name="minorityCommunity",length=200)
	private String minorityCommunity;
	
	public String getApplicant_Name() {
		return applicant_Name;
	}
	public String getApplicant_Gender() {
		return applicant_Gender;
	}
	public String getApplicant_DOB() {
		return applicant_DOB;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public String getMaritalStatusName() {
		return maritalStatusName;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	/*public String getVoterIDCardNo() {
		return voterIDCardNo;
	}*/
	public String getPwd() {
		return pwd;
	}
	public void setApplicant_Name(String applicant_Name) {
		this.applicant_Name = applicant_Name;
	}
	public void setApplicant_Gender(String applicant_Gender) {
		this.applicant_Gender = applicant_Gender;
	}
	public void setApplicant_DOB(String applicant_DOB) {
		this.applicant_DOB = applicant_DOB;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	/*public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}*/
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMinorityCommunity() {
		return minorityCommunity;
	}
	public void setMinorityCommunity(String minorityCommunity) {
		this.minorityCommunity = minorityCommunity;
	}
   }
