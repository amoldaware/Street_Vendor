package com.nbfc.bean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MLIRegistrationBean {
	

	private static final String METHOD = null;

	private String longName;
	private String shortName;
	@NotNull(message = "Company Address is Required.")
	private String companyAddress;
	@NotNull(message = "City is Required.")
	private String city;
	@NotNull(message = "State is Required.")
	private String state;
	@NotNull(message = "District is Required.")
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
	@Size(min = 2, max = 100, message = "Contact Person name should be between 3 - 100 characters.")
	private String contactPerson;
	@NotNull(message = "Mobile Number is Required.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number Should be 10 digits.")
	private String mobileNUmber;
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
	private String ratingDate;
	@Pattern(regexp = "(^$|[0-9]{3})", message = "Phone Code is required.")
	private String phoneCode;
	private String faxCode;
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

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getFaxCode() {
		return faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	/*
	 * public String getEditPhoneCode() { return editPhoneCode; } public void
	 * setEditPhoneCode(String editPhoneCode) { this.editPhoneCode =
	 * editPhoneCode; }
	 */
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

	public String getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(String ratingDate) {
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

	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}

	public String getMliLongName() {
		return mliLongName;
	}

	public void setMliLongName(String mliLongName) {
		this.mliLongName = mliLongName;
	}

	public String getMem_bnk_name() {
		return mem_bnk_name;
	}

	public void setMem_bnk_name(String mem_bnk_name) {
		this.mem_bnk_name = mem_bnk_name;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static String getMethod() {
		return METHOD;
	}


	
}
