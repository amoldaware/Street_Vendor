package com.nbfc.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRoleBean {

	//private String mliName;
	private String userName;
	private String roleName;
	//@NotNull(message = "Please select User Type")
	private String userType;
	
	
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/*
	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}
*/
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

	
	@NotNull(message = "Please Fill Your First Name")
	@Size(min = 3, max = 30, message = "First name should be between 3 - 30 characters.")
	private String fName;
	private String middalName;
	@NotNull(message = "Please Fill Your Last Name")
	@Size(min = 3, max = 30, message = "Last Name should be between 3 - 30 characters.")
	private String lName;
	@NotNull(message = "Please select MLI Name")
	private String state;
	@Pattern(regexp="(^$|[0-9]{10})",message="Mobile number Should be 10 digits.")
	private String mobileNumber;
	@Pattern(regexp="(^$|[0-9]{11})", message="Phone number Should be 11 digits.")
	private String phoneNumber;
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	private String email;
	/*@NotNull(message = "Please select User Type")
	private String userType;*/
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

	
	/*public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}*/

}
