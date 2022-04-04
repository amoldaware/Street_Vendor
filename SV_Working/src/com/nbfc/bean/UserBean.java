package com.nbfc.bean;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



/**
 * @author Saurav Tyagi 2017
 * 
 */
public class UserBean {
	
	//@NotNull(message = "Please Fill Your First Name")
	//@Size(min = 3, max = 30, message = "First name should be between 3 - 30 characters.")
	//@Pattern(regexp="(^$|[a-zA-Z])",message="Please enter Valid First Name")
	@NotNull(message = "First Name is Required")
	@Pattern(regexp="(^([a-zA-z\\s]{3,32})$)",message="Please enter Valid First Name")
	private String fName;
	private String middalName;
	@NotNull(message = "Last Name is Required")
	//@Size(min = 3, max = 30, message = "Last Name should be between 3 - 30 characters.")
	//@Pattern(regexp="(^$|[a-zA-Z])" ,message="Please enter Valid Last Name")
	@Pattern(regexp="(^([a-zA-z\\s]{3,32})$)",message="Please enter Valid Last Name")
	private String lName;
	@NotNull(message = "Please select MLI Name")
	private String state;
	@NotNull(message = "Mobile number is required")
	@Pattern(regexp="(^$|[0-9]{10})",message="Mobile number Should be 10 digits.")
	private String mobileNumber;
	@NotNull(message = "Phone number is required")
	@Pattern(regexp="(^$|[0-9]{8}|[0-9]{7})", message="Phone number Should be 7-8 digits.")
	private String phoneNumber;
	@NotNull(message = "Email ID is Required")
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	private String email;
	@NotNull(message = "Phone COde is required")
	@Pattern(regexp = "(^$|[0-9]{3}|[0-9]{5})", message = "3-5 digits Phone Code is required.")
	private String phoneCode;

	private String userType;
	private String roleName;
	@NotNull(message = "User Designation is required")
	private String uDesignation;
	/*@NotNull(message = "Please Fill Your Hint Question")
	private String hint_Question;
	@NotNull(message = "Please Fill Your Hint Answer")
	private String hint_ans;*/
	
	private String deleteuserID;
	
	
	private String deleteMliName;
	
	private String deleteStatus;

	private String deleteFName;
	
	private String deleteMiddalName;

	private String deleteLName;

	private String deletestate;

	private String deletemobileNumber;
	
	private String deletephoneNumber;

	private String deleteemail;

	private String deletephoneCode;

	private String deleteuserType;
	/*@NotNull(message = "Please Fill Your Hint Question")
	private String hint_Question;
	@NotNull(message = "Please Fill Your Hint Answer")
	private String hint_ans;*/
	
	private String userID;
	
	private String mem_bnk_id;
	
	private String mem_zne_id;
	
	private String mem_brn_id;
	
	private String mliName;
	
	private String status;
	private String mliId;
	private String hint_question;
	private String hint_ans;
	
	private String isActive;
	
	/*public String getHint_Question() {
		return hint_Question;
	}

	public void setHint_Question(String hint_Question) {
		this.hint_Question = hint_Question;
	}*/

	/*public String getHint_ans() {
		return hint_ans;
	}

	public void setHint_ans(String hint_ans) {
		this.hint_ans = hint_ans;
	}*/

	public String getHint_question() {
		return hint_question;
	}

	public void setHint_question(String hint_question) {
		this.hint_question = hint_question;
	}

	public String getHint_ans() {
		return hint_ans;
	}

	public void setHint_ans(String hint_ans) {
		this.hint_ans = hint_ans;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getDeleteuserID() {
		return deleteuserID;
	}

	public String getuDesignation() {
		return uDesignation;
	}

	public void setuDesignation(String uDesignation) {
		this.uDesignation = uDesignation;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setDeleteuserID(String deleteuserID) {
		this.deleteuserID = deleteuserID;
	}

	public String getDeleteMliName() {
		return deleteMliName;
	}

	public void setDeleteMliName(String deleteMliName) {
		this.deleteMliName = deleteMliName;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getDeleteFName() {
		return deleteFName;
	}

	public void setDeleteFName(String deleteFName) {
		this.deleteFName = deleteFName;
	}

	public String getDeleteMiddalName() {
		return deleteMiddalName;
	}

	public void setDeleteMiddalName(String deleteMiddalName) {
		this.deleteMiddalName = deleteMiddalName;
	}

	public String getDeleteLName() {
		return deleteLName;
	}

	public void setDeleteLName(String deleteLName) {
		this.deleteLName = deleteLName;
	}

	public String getDeletestate() {
		return deletestate;
	}

	public void setDeletestate(String deletestate) {
		this.deletestate = deletestate;
	}

	public String getDeletemobileNumber() {
		return deletemobileNumber;
	}

	public void setDeletemobileNumber(String deletemobileNumber) {
		this.deletemobileNumber = deletemobileNumber;
	}

	public String getDeletephoneNumber() {
		return deletephoneNumber;
	}

	public void setDeletephoneNumber(String deletephoneNumber) {
		this.deletephoneNumber = deletephoneNumber;
	}

	public String getDeleteemail() {
		return deleteemail;
	}

	public void setDeleteemail(String deleteemail) {
		this.deleteemail = deleteemail;
	}

	public String getDeletephoneCode() {
		return deletephoneCode;
	}

	public void setDeletephoneCode(String deletephoneCode) {
		this.deletephoneCode = deletephoneCode;
	}

	public String getDeleteuserType() {
		return deleteuserType;
	}

	public void setDeleteuserType(String deleteuserType) {
		this.deleteuserType = deleteuserType;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getMiddalName() {
		return middalName;
	}

	public void setMiddalName(String middalName) {
		this.middalName = middalName;
	}

	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
