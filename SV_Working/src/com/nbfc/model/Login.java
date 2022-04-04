package com.nbfc.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_user_info")
public class Login implements Serializable{
	private static final long serialVersionUID = -723583058586873479L;
	

	@Id
	@Column(name = "USR_ID")
	private String usr_id;
	@Column(name = "USR_PASSWORD")
	private String usr_password;
	@Column(name = "USR_FIRST_NAME")
	private String USR_FIRST_NAME;
	@Column(name = "USR_LAST_NAME")
	private String USR_LAST_NAME;
	@Column(name="LOGIN_STATUS")
	private String LOGIN_STATUS;
	
	@Column(name = "USR_OLD_USR_ID")
	private String USR_OLD_USR_ID;
	@Column(name = "USR_DSG_NAME")
	private String USR_DSG_NAME;
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;
	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;
	@Column(name = "USR_STATUS")
	private String USR_STATUS;
	@Column(name = "USR_TYPE")
	private String USR_TYPE;
	@Column(name = "USR_MIDDLE_NAME")
	private String USR_MIDDLE_NAME;
	@Column(name = "USR_EMAIL_ID")
	private String USR_EMAIL_ID;
	@Column(name = "USR_PWD_CHANGED_ON")
	private Date USR_PWD_CHANGED_ON;
	@Column(name = "USR_HINT_QUESTION")
	private String USR_HINT_QUESTION;
	@Column(name = "USR_HINT_ANSWER")
	private String USR_HINT_ANSWER;
	@Column(name = "USR_CREATED_BY")
	private String USR_CREATED_BY;
	@Column(name = "USR_CREATED_DT")
	private Date USR_CREATED_DT;
	@Column(name = "USR_MODIFIED_BY")
	private String USR_MODIFIED_BY;
	@Column(name = "USR_MODIFIED_DT")
	private Date USR_MODIFIED_DT;
	@Column(name = "CITY")
	private String CITY;
	@Column(name = "STATE")
	private String STATE;
	@Column(name = "DISTRICT")
	private String DISTRICT;
	@Column(name = "PIN_CODE")
	private String PIN_CODE;
	@Column(name = "MOBILE_NO")
	private String MOBILE_NO;
	@Column(name = "PHONE_NO")
	private String PHONE_NO;
	@Column(name = "FAX_NO")
	private String FAX_NO;
	@Column(name = "ADDRESS")
	private String ADDRESS;
	@Column(name = "REMARKS")
	private String REMARKS;
	@Column(name = "HINT_QUESTION")
	private String HINT_QUESTION;
	@Column(name = "HINT_ANS")
	private String HINT_ANS;
	@Column(name = "PHONE_CODE")
	private String PHONE_CODE;
	@Column(name = "PASS_CH_DT")
	private Date passwordChangeDate = new Date();
	@Column (name="ISACTIVE")	
	private String isActive;
	@Column(name = "LOGIN_ATTEMPT_TIME")
	private Date loginAttemptTime;
	@Column(name="LOGIN_ATTEMPT_COUNTER")
	private int loginAttemptCounter=0;
	@Column(name="FORGET_ATTEMPT_COUNTER")
	private int forgetAttemptCounter=0;
	
	
	public int getForgetAttemptCounter() {
		return forgetAttemptCounter;
	}

	public void setForgetAttemptCounter(int forgetAttemptCounter) {
		this.forgetAttemptCounter = forgetAttemptCounter;
	}

	public String getIsActive() {
		return isActive;
	}

	public Date getLoginAttemptTime() {
		return loginAttemptTime;
	}

	public void setLoginAttemptTime(Date loginAttemptTime) {
		this.loginAttemptTime = loginAttemptTime;
	}

	public int getLoginAttemptCounter() {
		return loginAttemptCounter;
	}

	public void setLoginAttemptCounter(int loginAttemptCounter) {
		this.loginAttemptCounter = loginAttemptCounter;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getLOGIN_STATUS() {
		return LOGIN_STATUS;
	}
	
	public String getUSR_OLD_USR_ID() {
		return USR_OLD_USR_ID;
	}

	public void setUSR_OLD_USR_ID(String uSR_OLD_USR_ID) {
		USR_OLD_USR_ID = uSR_OLD_USR_ID;
	}

	public String getUSR_DSG_NAME() {
		return USR_DSG_NAME;
	}

	public void setUSR_DSG_NAME(String uSR_DSG_NAME) {
		USR_DSG_NAME = uSR_DSG_NAME;
	}

	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

	public String getUSR_STATUS() {
		return USR_STATUS;
	}

	public void setUSR_STATUS(String uSR_STATUS) {
		USR_STATUS = uSR_STATUS;
	}

	public String getUSR_TYPE() {
		return USR_TYPE;
	}

	public void setUSR_TYPE(String uSR_TYPE) {
		USR_TYPE = uSR_TYPE;
	}

	public String getUSR_MIDDLE_NAME() {
		return USR_MIDDLE_NAME;
	}

	public void setUSR_MIDDLE_NAME(String uSR_MIDDLE_NAME) {
		USR_MIDDLE_NAME = uSR_MIDDLE_NAME;
	}

	public String getUSR_EMAIL_ID() {
		return USR_EMAIL_ID;
	}

	public void setUSR_EMAIL_ID(String uSR_EMAIL_ID) {
		USR_EMAIL_ID = uSR_EMAIL_ID;
	}

	public Date getUSR_PWD_CHANGED_ON() {
		return USR_PWD_CHANGED_ON;
	}

	public void setUSR_PWD_CHANGED_ON(Date uSR_PWD_CHANGED_ON) {
		USR_PWD_CHANGED_ON = uSR_PWD_CHANGED_ON;
	}

	public String getUSR_HINT_QUESTION() {
		return USR_HINT_QUESTION;
	}

	public void setUSR_HINT_QUESTION(String uSR_HINT_QUESTION) {
		USR_HINT_QUESTION = uSR_HINT_QUESTION;
	}

	public String getUSR_HINT_ANSWER() {
		return USR_HINT_ANSWER;
	}

	public void setUSR_HINT_ANSWER(String uSR_HINT_ANSWER) {
		USR_HINT_ANSWER = uSR_HINT_ANSWER;
	}

	public String getUSR_CREATED_BY() {
		return USR_CREATED_BY;
	}

	public void setUSR_CREATED_BY(String uSR_CREATED_BY) {
		USR_CREATED_BY = uSR_CREATED_BY;
	}

	public Date getUSR_CREATED_DT() {
		return USR_CREATED_DT;
	}

	public void setUSR_CREATED_DT(Date uSR_CREATED_DT) {
		USR_CREATED_DT = uSR_CREATED_DT;
	}

	public String getUSR_MODIFIED_BY() {
		return USR_MODIFIED_BY;
	}

	public void setUSR_MODIFIED_BY(String uSR_MODIFIED_BY) {
		USR_MODIFIED_BY = uSR_MODIFIED_BY;
	}

	public Date getUSR_MODIFIED_DT() {
		return USR_MODIFIED_DT;
	}

	public void setUSR_MODIFIED_DT(Date uSR_MODIFIED_DT) {
		USR_MODIFIED_DT = uSR_MODIFIED_DT;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	public String getPIN_CODE() {
		return PIN_CODE;
	}

	public void setPIN_CODE(String pIN_CODE) {
		PIN_CODE = pIN_CODE;
	}

	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}

	public String getPHONE_NO() {
		return PHONE_NO;
	}

	public void setPHONE_NO(String pHONE_NO) {
		PHONE_NO = pHONE_NO;
	}

	public String getFAX_NO() {
		return FAX_NO;
	}

	public void setFAX_NO(String fAX_NO) {
		FAX_NO = fAX_NO;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getHINT_QUESTION() {
		return HINT_QUESTION;
	}

	public void setHINT_QUESTION(String hINT_QUESTION) {
		HINT_QUESTION = hINT_QUESTION;
	}

	public String getHINT_ANS() {
		return HINT_ANS;
	}

	public void setHINT_ANS(String hINT_ANS) {
		HINT_ANS = hINT_ANS;
	}

	public String getPHONE_CODE() {
		return PHONE_CODE;
	}

	public void setPHONE_CODE(String pHONE_CODE) {
		PHONE_CODE = pHONE_CODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPasswordChangeDate(Date passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	public void setLOGIN_STATUS(String lOGIN_STATUS) {
		LOGIN_STATUS = lOGIN_STATUS;
	}
	public String getUSR_FIRST_NAME() {
		return USR_FIRST_NAME;
	}
	public void setUSR_FIRST_NAME(String uSR_FIRST_NAME) {
		USR_FIRST_NAME = uSR_FIRST_NAME;
	}
		
	
	public String getUSR_LAST_NAME() {
		return USR_LAST_NAME;
	}
	public void setUSR_LAST_NAME(String uSR_LAST_NAME) {
		USR_LAST_NAME = uSR_LAST_NAME;
	}
	public String getUsr_id() {

		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_password() {
		return usr_password;
	}
	public void setUsr_password(String usr_password) {

		this.usr_password = usr_password;
	}
	

}

