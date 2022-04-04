package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class MLIEditApproveRejectUpdate {
	

	
	@Id
	@Column(name="MEM_BNK_ID")
	private String mem_bnk_id;
	@Column(name="LONG_NAME")
	private String longName;
	@Column(name="MEM_ZNE_ID")
	private String mem_zne_id;
	@Column(name="MEM_BRN_ID")
	private String mem_brn_id;
	@Column(name="SHORT_NAME")
	private String shortName;
	@Column(name="MEM_ADDRESS")
	private String companyAddress;
	@Column(name="MEM_CITY")
	private String city;
	@Column(name="MEM_STATE_NAME")
	private String state;
	@Column(name="MEM_DISTRICT_NAME")
	private String district;
	@Column(name="MEM_PINCODE")
	private String pincode;
	@Column(name="MEM_PHONE_NUMBER")
	private String landlineNumber;
	@Column(name="MEM_EMAIL")
	private String emailId;
	@Column(name="CONTACT_PERSON")
	private String contactPerson;
	@Column(name="MOBILE_NO")
	private String mobileNUmber;
	@Column(name="MEM_FAX_NUMBER")
	private String faxNumber;
	@Column(name="RBI_REGISTRATION_NO")
	private String rbiReggistrationNumber;
	@Column(name="COMPANY_CIN")
	private String companyCIN;
	@Column(name="COMPANY_PAN")
	private String companyPAN;
	@Column(name="GSTIN_NO")
	private String gstinNumber;
	@Column(name="RATING_AGENCY")
	private String ratingAgency;
	@Column(name="RATING")
	private String rating;
	@Column(name="DATE_OF_RATING")
	private Date ratingDate;
	@Column(name="CREADTED_BY")
	private String userID;
	@Column(name="STATUS")
	private String status;
	@Column(name="MEM_BANK_NAME")
	private String mem_bnk_name;
	@Column(name="MEM_PHONE_CODE")
	private String phone_code;
	@Column(name="MEM_STATUS")
	private String mem_status;
	@Column(name="MEM_MCGF")
	private String mem_mcgf;;
	@Column(name="CGTMSE_CHECKER_ID")
	private String cgtmse_checker_id;
	@Column(name="CGTMSE_CHECKER_DATE")
	private Date cgtmse_checker_date=new Date();
	@Column(name="REMARKS")
	private String remarks;
	@Column(name="CGTMSE_MAKER_ID")
	private String cgtmse_maker_id;
	@Column(name="CGTMSE_MAKER_DATE")
	private Date cgtmse_maker_date;
	@Column(name="MEM_FAX_CODE")
	private String faxCode;
		
	
	public String getFaxCode() {
		return faxCode;
	}
	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}
	public String getCgtmse_maker_id() {
		return cgtmse_maker_id;
	}
	public void setCgtmse_maker_id(String cgtmse_maker_id) {
		this.cgtmse_maker_id = cgtmse_maker_id;
	}
	public void setCgtmse_maker_date(Date cgtmse_maker_date) {
		this.cgtmse_maker_date = cgtmse_maker_date;
	}
	public Date getCgtmse_maker_date() {
		return cgtmse_maker_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCgtmse_checker_id() {
		return cgtmse_checker_id;
	}
	public void setCgtmse_checker_id(String cgtmse_checker_id) {
		this.cgtmse_checker_id = cgtmse_checker_id;
	}
	
	public void setCgtmse_checker_date(Date cgtmse_checker_date) {
		this.cgtmse_checker_date = new Date();
	}
	public String getMem_status() {
		return mem_status;
	}
	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}
	public String getMem_mcgf() {
		return mem_mcgf;
	}
	public void setMem_mcgf(String mem_mcgf) {
		this.mem_mcgf = mem_mcgf;
	}
	public String getPhone_code() {
		return phone_code;
	}
	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}
	public String getMem_bnk_name() {
		return mem_bnk_name;
	}
	public void setMem_bnk_name(String mem_bnk_name) {
		this.mem_bnk_name = mem_bnk_name;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLandlineNumber() {
		return landlineNumber;
	}
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getMobileNUmber() {
		return mobileNUmber;
	}
	public void setMobileNUmber(String mobileNUmber) {
		this.mobileNUmber = mobileNUmber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getRbiReggistrationNumber() {
		return rbiReggistrationNumber;
	}
	public void setRbiReggistrationNumber(String rbiReggistrationNumber) {
		this.rbiReggistrationNumber = rbiReggistrationNumber;
	}
	public String getCompanyCIN() {
		return companyCIN;
	}
	public void setCompanyCIN(String companyCIN) {
		this.companyCIN = companyCIN;
	}
	public String getCompanyPAN() {
		return companyPAN;
	}
	public void setCompanyPAN(String companyPAN) {
		this.companyPAN = companyPAN;
	}
	public String getGstinNumber() {
		return gstinNumber;
	}
	public void setGstinNumber(String gstinNumber) {
		this.gstinNumber = gstinNumber;
	}
	public String getRatingAgency() {
		return ratingAgency;
	}
	public void setRatingAgency(String ratingAgency) {
		this.ratingAgency = ratingAgency;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Date getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMem_bnk_id() {
		return mem_bnk_id;
	}
	public void setMem_bnk_id(String mem_bnk_id) {
		this.mem_bnk_id = mem_bnk_id;
	}
	public String getMem_zne_id() {
		return mem_zne_id;
	}
	public void setMem_zne_id(String mem_zne_id) {
		this.mem_zne_id = mem_zne_id;
	}
	public String getMem_brn_id() {
		return mem_brn_id;
	}
	public void setMem_brn_id(String mem_brn_id) {
		this.mem_brn_id = mem_brn_id;
	}





}
