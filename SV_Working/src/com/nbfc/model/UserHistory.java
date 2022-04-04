package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.Email;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Entity
@Table(name = "NBFC_USER_INFO_HISTORY")
public class UserHistory implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@Column(name = "USR_ID")
	private String usr_id;
	
	@Column(name = "USR_FIRST_NAME")
	private String fName;

	

	@Column(name = "USR_MIDDLE_NAME")
	private String mName;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Column(name = "USR_LAST_NAME")
	private String lName;

	@Column(name = "USR_CREATED_DT")
	private Date createdDate = new Date();

	@Column(name = "STATE")
	private String state;

	@PreUpdate
	public void setCreatedDate() {
		this.createdDate = new Date();
	}

	@Column(name = "MOBILE_NO")
	private String mobileNumber;

	@Column(name = "PHONE_NO")
	private String phoneNumber;

	@Column(name = "USR_EMAIL_ID")
	private String email;

	@Column(name = "USR_CREATED_BY")
	private String createdBy;

	@Column(name = "USR_TYPE")
	private String userType;

	@Column(name = "USR_PASSWORD")
	private String password;
	
	@Column(name = "MEM_BNK_ID")
	private String mem_bnk_id;
	
	@Column(name = "MEM_ZNE_ID")
	private String mem_zne_id;
	
	@Column(name = "MEM_BRN_ID")
	private String mem_brn_id;

	@Column(name = "HINT_QUESTION")
	private String hint_question;
	
	@Column(name = "HINT_ANS")
	private String hint_ans;
	
	@Column(name = "PHONE_CODE")
	private String phone_code;
	
	@Column(name = "LOGIN_STATUS")
	private String LOGIN_STATUS;
	
	@Column(name = "USR_DELETE_DT")
	private String Usr_delete_date;
	
	
	
	public String getUsr_delete_date() {
		return Usr_delete_date;
	}

	public void setUsr_delete_date(String usr_delete_date) {
		Usr_delete_date = usr_delete_date;
	}

	public String getLOGIN_STATUS() {
		return LOGIN_STATUS;
	}

	public void setLOGIN_STATUS(String lOGIN_STATUS) {
		LOGIN_STATUS = lOGIN_STATUS;
	}

	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
