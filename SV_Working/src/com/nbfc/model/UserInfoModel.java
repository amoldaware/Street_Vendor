package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nbfc_user_info")
public class UserInfoModel{

	@Id
	@Column(name = "USR_ID")
	private String usr_id;
	@Column(name = "USR_FIRST_NAME")
	private String fName;
	@Column(name = "USR_MIDDLE_NAME")
	private String mName;
	@Column(name = "USR_LAST_NAME")
	private String lName;
	@Column(name = "MOBILE_NO")
	private String mobileNumber;
	@Column(name = "PHONE_NO")
	private String phoneNumber;
	@Column(name = "USR_EMAIL_ID")
	private String email;
	@Column(name = "USR_TYPE")
	private String userType;
	@Column (name="ISACTIVE")
	private String isActive;
	@Column(name = "PHONE_CODE")
	private String phone_code;
	
	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}

	@Column(name = "HINT_QUESTION")
	private String hint_question;
	
	@Column(name = "HINT_ANS")
	private String hint_ans;
	
	
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
	
	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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

	
	@Override
	public String toString() {
		return "UserInfoModel [usr_id=" + usr_id + ", fName="
				+ fName + ", lName=" + lName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", phoneNumber="
				+ phoneNumber + ",userType="+userType+"]";
	}

	public UserInfoModel() {
		super();
	}

	
	
}
