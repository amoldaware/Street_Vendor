package com.nbfc.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MLICheckerApproveRejectDetailsBean {

	@NotNull(message = "Long Name is Required.")
	@Size(min = 2, max = 30, message = "Long name should be between 3 - 30 characters.")
	private String longName;
private String phoneCode;

private String editPhoneCode;
	@NotNull(message = "Short Name is Required.")
	@Size(min = 1, max = 10, message = "Short name should be between 2 - 10 characters.")
	private String shortName;
	@NotNull(message = "Long Name is Required.")
	private String companyAddress;
	@NotNull(message = "City is Required.")
	private String city;
	private String state;
	private String district;
	@NotNull(message = "Pincode is Required.")
	@Pattern(regexp = "(^$|[0-9]{6})", message = "Pincode should be 6 digits.")
	private String pincode;
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Landline number Should be 11 digits.")
	private String landlineNumber;
	@NotNull(message = "Email id is Required.")
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	private String emailId;
	@NotNull(message = "Contact Person name is Required.")
	@Size(min = 2, max = 30, message = "Contact Person name should be between 3 - 30 characters.")
	private String contactPerson;
	@NotNull(message = "Mobile Number is Required.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number Should be 10 digits.")
	private String mobileNUmber;
	private String faxCode;
	

	private String faxNumber;
	@NotNull(message = "RBI registration No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{1}-\\d{2}+\\.\\d{5})", message = "Invalid RBI Registration Number")
	private String rbiReggistrationNumber;
	@NotNull(message = "Company CIN No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{1}\\d{5}[A-Z]{2}\\d{4}[A-Z]{3}\\d{6})", message = "Invalid Company CIN No.")
	private String companyCIN;
	@NotNull(message = "Company PAN No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{5}\\d{4}[A-Z]{1})", message = "Invalid PAN NUmber.")
	private String companyPAN;
	@NotNull(message = "GSTIN No is Required.")
	@Pattern(regexp = "(^$|[0-9]{2}[A-Z]{5}\\d{4}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1})", message = "Invalid Company GSTIN Number.")
	private String gstinNumber;
	@NotNull(message = "Rating Agency is Required.")
	private String ratingAgency;
	@NotNull(message = "Rating is Required.")
	private String rating;
	@NotNull(message = "Date of Rating is Required.")
	private Date ratingDate;
	private String ratingDateOne;
	private String userID;
	private String status;
	private String mem_bnk_id;
	private String mem_zne_id;
	private String mem_brn_id;
	private String phone_code;
	private String mliLongName;
	private String mem_bnk_name;
	private String mem_status;
	private String mem_mcgf;
	private String remarks;
	private String nameSearch;
	private String searchValue;


	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getFaxCode() {
		return faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}
	@NotNull(message = "Long Name is Required.")
	@Size(min = 2, max = 30, message = "Long name should be between 3 - 30 characters.")
	private String editLongName;

	@NotNull(message = "Short Name is Required.")
	@Size(min = 1, max = 10, message = "Short name should be between 2 - 10 characters.")
	private String editShortName;
	@NotNull(message = "Long Name is Required.")
	private String editCompanyAddress;
	@NotNull(message = "City is Required.")
	private String editCity;
	private String editState;
	private String editDistrict;
	@NotNull(message = "Pincode is Required.")
	@Pattern(regexp = "(^$|[0-9]{6})", message = "Pincode should be 6 digits.")
	private String editPincode;
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Landline number Should be 11 digits.")
	private String editLandlineNumber;
	@NotNull(message = "Email id is Required.")
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	private String editEmailId;
	@NotNull(message = "Contact Person name is Required.")
	@Size(min = 2, max = 30, message = "Contact Person name should be between 3 - 30 characters.")
	private String editContactPerson;
	@NotNull(message = "Mobile Number is Required.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number Should be 10 digits.")
	private String editMobileNUmber;
	private String editFaxNumber;
	@NotNull(message = "RBI registration No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{1}-\\d{2}+\\.\\d{5})", message = "Invalid RBI Registration Number")
	private String editRBIReggistrationNumber;
	@NotNull(message = "Company CIN No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{1}\\d{5}[A-Z]{2}\\d{4}[A-Z]{3}\\d{6})", message = "Invalid Company CIN No.")
	private String editCompanyCIN;
	@NotNull(message = "Company PAN No is Required.")
	@Pattern(regexp = "(^$|[A-Z]{5}\\d{4}[A-Z]{1})", message = "Invalid PAN NUmber.")
	private String editCompanyPAN;
	@NotNull(message = "GSTIN No is Required.")
	@Pattern(regexp = "(^$|[0-9]{2}[A-Z]{5}\\d{4}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1})", message = "Invalid Company GSTIN Number.")
	private String editGstinNumber;
	@NotNull(message = "Rating Agency is Required.")
	private String editRatingAgency;
	@NotNull(message = "Rating is Required.")
	private String editRating;
	@NotNull(message = "Date of Rating is Required.")
	private Date editRatingDate;
	private String ratingDateTwo;
	
	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getEditPhoneCode() {
		return editPhoneCode;
	}

	public void setEditPhoneCode(String editPhoneCode) {
		this.editPhoneCode = editPhoneCode;
	}


	public String getEditLongName() {
		return editLongName;
	}

	public void setEditLongName(String editLongName) {
		this.editLongName = editLongName;
	}

	public String getEditShortName() {
		return editShortName;
	}

	public void setEditShortName(String editShortName) {
		this.editShortName = editShortName;
	}

	public String getEditCompanyAddress() {
		return editCompanyAddress;
	}

	public void setEditCompanyAddress(String editCompanyAddress) {
		this.editCompanyAddress = editCompanyAddress;
	}

	public String getEditCity() {
		return editCity;
	}

	public void setEditCity(String editCity) {
		this.editCity = editCity;
	}

	public String getEditState() {
		return editState;
	}

	public void setEditState(String editState) {
		this.editState = editState;
	}

	public String getEditDistrict() {
		return editDistrict;
	}

	public void setEditDistrict(String editDistrict) {
		this.editDistrict = editDistrict;
	}

	public String getEditPincode() {
		return editPincode;
	}

	public void setEditPincode(String editPincode) {
		this.editPincode = editPincode;
	}

	public String getEditLandlineNumber() {
		return editLandlineNumber;
	}

	public void setEditLandlineNumber(String editLandlineNumber) {
		this.editLandlineNumber = editLandlineNumber;
	}

	public String getEditEmailId() {
		return editEmailId;
	}

	public void setEditEmailId(String editEmailId) {
		this.editEmailId = editEmailId;
	}

	public String getEditContactPerson() {
		return editContactPerson;
	}

	public void setEditContactPerson(String editContactPerson) {
		this.editContactPerson = editContactPerson;
	}

	public String getEditMobileNUmber() {
		return editMobileNUmber;
	}

	public void setEditMobileNUmber(String editMobileNUmber) {
		this.editMobileNUmber = editMobileNUmber;
	}

	public String getEditFaxNumber() {
		return editFaxNumber;
	}

	public void setEditFaxNumber(String editFaxNumber) {
		this.editFaxNumber = editFaxNumber;
	}

	public String getEditRBIReggistrationNumber() {
		return editRBIReggistrationNumber;
	}

	public void setEditRBIReggistrationNumber(String editRBIReggistrationNumber) {
		this.editRBIReggistrationNumber = editRBIReggistrationNumber;
	}

	public String getEditCompanyCIN() {
		return editCompanyCIN;
	}

	public void setEditCompanyCIN(String editCompanyCIN) {
		this.editCompanyCIN = editCompanyCIN;
	}

	public String getEditCompanyPAN() {
		return editCompanyPAN;
	}

	public void setEditCompanyPAN(String editCompanyPAN) {
		this.editCompanyPAN = editCompanyPAN;
	}

	public String getEditGstinNumber() {
		return editGstinNumber;
	}

	public void setEditGstinNumber(String editGstinNumber) {
		this.editGstinNumber = editGstinNumber;
	}

	public String getEditRatingAgency() {
		return editRatingAgency;
	}

	public void setEditRatingAgency(String editRatingAgency) {
		this.editRatingAgency = editRatingAgency;
	}

	public String getEditRating() {
		return editRating;
	}

	public void setEditRating(String editRating) {
		this.editRating = editRating;
	}

	public Date getEditRatingDate() {
		return editRatingDate;
	}

	public void setEditRatingDate(Date editRatingDate) {
		this.editRatingDate = editRatingDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMem_mcgf() {
		return mem_mcgf;
	}

	public void setMem_mcgf(String mem_mcgf) {
		this.mem_mcgf = mem_mcgf;
	}

	public String getMem_status() {
		return mem_status;
	}

	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}

	public String getMem_bnk_name() {
		return mem_bnk_name;
	}

	public void setMem_bnk_name(String mem_bnk_name) {
		this.mem_bnk_name = mem_bnk_name;
	}

	public String getMliLongName() {
		return mliLongName;
	}

	public void setMliLongName(String mliLongName) {
		this.mliLongName = mliLongName;
	}

	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
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

	public String getRatingDateOne() {
		return ratingDateOne;
	}

	public void setRatingDateOne(String ratingDateOne) {
		this.ratingDateOne = ratingDateOne;
	}

	public String getRatingDateTwo() {
		return ratingDateTwo;
	}

	public void setRatingDateTwo(String ratingDateTwo) {
		this.ratingDateTwo = ratingDateTwo;
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
