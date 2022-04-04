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
/**
 * @author Saurav Tyagi 2017
 * 
 */
@Entity
@Table(name = "nbfc_interface_upload")
public class MLIMaker implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;

	private String USR_ID;
	private String LONE_TYPE;
	private String BUSINESS_PRODUCT;
	private String QUARTER_NO;
	private String LOAN_ACCOUNT_NO;
	private String CONSTITUTION;
	private String MSE_NAME;
	private Date SNCTION_DATE;
	private String SANCTIONED_AMOUNT;
	private Date FIRST_DISBURSEMENT_DATE;
	private String INTEREST_RATE;
	private String MICRO_SMALL;
	private String TENOR_IN_MONTHS;
	private String MSE_ADDRESS;
	private String CITY;
	private String DISTRICT;
	private String PINCODE;
	private String STATE;
	private String MSE_ITPAN;
	private String UDYOG_AADHAR_NO;
	private String MSME_REGISTRATION_NO;
	private String INDUSTRY_NATURE;
	private String INDUSTRY_SECTOR;
	private String NO_OF_EMPLOYEES;
	private String PROJECTED_SALES;
	private String PROJECTED_EXPORTS;
	private String NEW_EXISTING_UNIT;
	private String PREVIOUS_BANKING_EXPERIENCE;
	private String FIRST_TIME_CUSTOMER;
	private String CHIEF_PROMOTER_FIRST_NAME;
	private String CHIEF_PROMOTER_MIDDLE_NAME;
	private String CHIEF_PROMOTER_LAST_NAME;
	private String CHIEF_PROMOTER_IT_PAN;
	private String CHIEF_PROMOTER_MAIL_ID;
	private String CHIEF_PROMOTER_CONTACT_NUMBER;
	private String MINORITY_COMMUNITY;
	private String HANDICAPPED;
	private String WOMEN;
	private String CATEGORY;
	private String PORTFOLIO_BASE_YER;
	private String PORTFOLIO_NO;
	private String INSERT_DATE_TIME;
	private String STATUS;
	private String FILE_PATH;
	private String REMARKS;
	private String FLAG;
	private String FILE_LINK;
	private String CGDAN;
	private String MPENDENCY;
	private String REJECTION_REASON;

	public String getINSERT_DATE_TIME() {
		return INSERT_DATE_TIME;
	}
	public void setINSERT_DATE_TIME(String iNSERT_DATE_TIME) {
		INSERT_DATE_TIME = iNSERT_DATE_TIME;
	}
	
	public String getUSR_ID() {
		return USR_ID;
	}
	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}
	public String getLONE_TYPE() {
		return LONE_TYPE;
	}
	public void setLONE_TYPE(String lONE_TYPE) {
		LONE_TYPE = lONE_TYPE;
	}
	public String getBUSINESS_PRODUCT() {
		return BUSINESS_PRODUCT;
	}
	public void setBUSINESS_PRODUCT(String bUSINESS_PRODUCT) {
		BUSINESS_PRODUCT = bUSINESS_PRODUCT;
	}
	public String getQUARTER_NO() {
		return QUARTER_NO;
	}
	public void setQUARTER_NO(String qUARTER_NO) {
		QUARTER_NO = qUARTER_NO;
	}
	public String getLOAN_ACCOUNT_NO() {
		return LOAN_ACCOUNT_NO;
	}
	public void setLOAN_ACCOUNT_NO(String lOAN_ACCOUNT_NO) {
		LOAN_ACCOUNT_NO = lOAN_ACCOUNT_NO;
	}
	public String getCONSTITUTION() {
		return CONSTITUTION;
	}
	public void setCONSTITUTION(String cONSTITUTION) {
		CONSTITUTION = cONSTITUTION;
	}
	public String getMSE_NAME() {
		return MSE_NAME;
	}
	public void setMSE_NAME(String mSE_NAME) {
		MSE_NAME = mSE_NAME;
	}
	public Date getSNCTION_DATE() {
		return SNCTION_DATE;
	}
	public void setSNCTION_DATE(Date sNCTION_DATE) {
		SNCTION_DATE = sNCTION_DATE;
	}
	public String getSANCTIONED_AMOUNT() {
		return SANCTIONED_AMOUNT;
	}
	public void setSANCTIONED_AMOUNT(String sANCTIONED_AMOUNT) {
		SANCTIONED_AMOUNT = sANCTIONED_AMOUNT;
	}
	public Date getFIRST_DISBURSEMENT_DATE() {
		return FIRST_DISBURSEMENT_DATE;
	}
	public void setFIRST_DISBURSEMENT_DATE(Date fIRST_DISBURSEMENT_DATE) {
		FIRST_DISBURSEMENT_DATE = fIRST_DISBURSEMENT_DATE;
	}
	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}
	public void setINTEREST_RATE(String iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
	public String getMICRO_SMALL() {
		return MICRO_SMALL;
	}
	public void setMICRO_SMALL(String mICRO_SMALL) {
		MICRO_SMALL = mICRO_SMALL;
	}
	public String getTENOR_IN_MONTHS() {
		return TENOR_IN_MONTHS;
	}
	public void setTENOR_IN_MONTHS(String tENOR_IN_MONTHS) {
		TENOR_IN_MONTHS = tENOR_IN_MONTHS;
	}
	public String getMSE_ADDRESS() {
		return MSE_ADDRESS;
	}
	public void setMSE_ADDRESS(String mSE_ADDRESS) {
		MSE_ADDRESS = mSE_ADDRESS;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getPINCODE() {
		return PINCODE;
	}
	public void setPINCODE(String pINCODE) {
		PINCODE = pINCODE;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getMSE_ITPAN() {
		return MSE_ITPAN;
	}
	public void setMSE_ITPAN(String mSE_ITPAN) {
		MSE_ITPAN = mSE_ITPAN;
	}
	public String getUDYOG_AADHAR_NO() {
		return UDYOG_AADHAR_NO;
	}
	public void setUDYOG_AADHAR_NO(String uDYOG_AADHAR_NO) {
		UDYOG_AADHAR_NO = uDYOG_AADHAR_NO;
	}
	public String getMSME_REGISTRATION_NO() {
		return MSME_REGISTRATION_NO;
	}
	public void setMSME_REGISTRATION_NO(String mSME_REGISTRATION_NO) {
		MSME_REGISTRATION_NO = mSME_REGISTRATION_NO;
	}
	public String getINDUSTRY_NATURE() {
		return INDUSTRY_NATURE;
	}
	public void setINDUSTRY_NATURE(String iNDUSTRY_NATURE) {
		INDUSTRY_NATURE = iNDUSTRY_NATURE;
	}
	public String getINDUSTRY_SECTOR() {
		return INDUSTRY_SECTOR;
	}
	public void setINDUSTRY_SECTOR(String iNDUSTRY_SECTOR) {
		INDUSTRY_SECTOR = iNDUSTRY_SECTOR;
	}
	public String getNO_OF_EMPLOYEES() {
		return NO_OF_EMPLOYEES;
	}
	public void setNO_OF_EMPLOYEES(String nO_OF_EMPLOYEES) {
		NO_OF_EMPLOYEES = nO_OF_EMPLOYEES;
	}
	public String getPROJECTED_SALES() {
		return PROJECTED_SALES;
	}
	public void setPROJECTED_SALES(String pROJECTED_SALES) {
		PROJECTED_SALES = pROJECTED_SALES;
	}
	public String getPROJECTED_EXPORTS() {
		return PROJECTED_EXPORTS;
	}
	public void setPROJECTED_EXPORTS(String pROJECTED_EXPORTS) {
		PROJECTED_EXPORTS = pROJECTED_EXPORTS;
	}
	public String getNEW_EXISTING_UNIT() {
		return NEW_EXISTING_UNIT;
	}
	public void setNEW_EXISTING_UNIT(String nEW_EXISTING_UNIT) {
		NEW_EXISTING_UNIT = nEW_EXISTING_UNIT;
	}
	public String getPREVIOUS_BANKING_EXPERIENCE() {
		return PREVIOUS_BANKING_EXPERIENCE;
	}
	public void setPREVIOUS_BANKING_EXPERIENCE(String pREVIOUS_BANKING_EXPERIENCE) {
		PREVIOUS_BANKING_EXPERIENCE = pREVIOUS_BANKING_EXPERIENCE;
	}
	public String getFIRST_TIME_CUSTOMER() {
		return FIRST_TIME_CUSTOMER;
	}
	public void setFIRST_TIME_CUSTOMER(String fIRST_TIME_CUSTOMER) {
		FIRST_TIME_CUSTOMER = fIRST_TIME_CUSTOMER;
	}
	public String getCHIEF_PROMOTER_FIRST_NAME() {
		return CHIEF_PROMOTER_FIRST_NAME;
	}
	public void setCHIEF_PROMOTER_FIRST_NAME(String cHIEF_PROMOTER_FIRST_NAME) {
		CHIEF_PROMOTER_FIRST_NAME = cHIEF_PROMOTER_FIRST_NAME;
	}
	public String getCHIEF_PROMOTER_MIDDLE_NAME() {
		return CHIEF_PROMOTER_MIDDLE_NAME;
	}
	public void setCHIEF_PROMOTER_MIDDLE_NAME(String cHIEF_PROMOTER_MIDDLE_NAME) {
		CHIEF_PROMOTER_MIDDLE_NAME = cHIEF_PROMOTER_MIDDLE_NAME;
	}
	public String getCHIEF_PROMOTER_LAST_NAME() {
		return CHIEF_PROMOTER_LAST_NAME;
	}
	public void setCHIEF_PROMOTER_LAST_NAME(String cHIEF_PROMOTER_LAST_NAME) {
		CHIEF_PROMOTER_LAST_NAME = cHIEF_PROMOTER_LAST_NAME;
	}
	public String getCHIEF_PROMOTER_IT_PAN() {
		return CHIEF_PROMOTER_IT_PAN;
	}
	public void setCHIEF_PROMOTER_IT_PAN(String cHIEF_PROMOTER_IT_PAN) {
		CHIEF_PROMOTER_IT_PAN = cHIEF_PROMOTER_IT_PAN;
	}
	public String getCHIEF_PROMOTER_MAIL_ID() {
		return CHIEF_PROMOTER_MAIL_ID;
	}
	public void setCHIEF_PROMOTER_MAIL_ID(String cHIEF_PROMOTER_MAIL_ID) {
		CHIEF_PROMOTER_MAIL_ID = cHIEF_PROMOTER_MAIL_ID;
	}
	public String getCHIEF_PROMOTER_CONTACT_NUMBER() {
		return CHIEF_PROMOTER_CONTACT_NUMBER;
	}
	public void setCHIEF_PROMOTER_CONTACT_NUMBER(
			String cHIEF_PROMOTER_CONTACT_NUMBER) {
		CHIEF_PROMOTER_CONTACT_NUMBER = cHIEF_PROMOTER_CONTACT_NUMBER;
	}
	public String getMINORITY_COMMUNITY() {
		return MINORITY_COMMUNITY;
	}
	public void setMINORITY_COMMUNITY(String mINORITY_COMMUNITY) {
		MINORITY_COMMUNITY = mINORITY_COMMUNITY;
	}
	public String getHANDICAPPED() {
		return HANDICAPPED;
	}
	public void setHANDICAPPED(String hANDICAPPED) {
		HANDICAPPED = hANDICAPPED;
	}
	public String getWOMEN() {
		return WOMEN;
	}
	public void setWOMEN(String wOMEN) {
		WOMEN = wOMEN;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getPORTFOLIO_BASE_YER() {
		return PORTFOLIO_BASE_YER;
	}
	public void setPORTFOLIO_BASE_YER(String pORTFOLIO_BASE_YER) {
		PORTFOLIO_BASE_YER = pORTFOLIO_BASE_YER;
	}
	public String getPORTFOLIO_NO() {
		return PORTFOLIO_NO;
	}
	public void setPORTFOLIO_NO(String pORTFOLIO_NO) {
		PORTFOLIO_NO = pORTFOLIO_NO;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getFILE_LINK() {
		return FILE_LINK;
	}
	public void setFILE_LINK(String fILE_LINK) {
		FILE_LINK = fILE_LINK;
	}
	public String getCGDAN() {
		return CGDAN;
	}
	public void setCGDAN(String cGDAN) {
		CGDAN = cGDAN;
	}
	public String getMPENDENCY() {
		return MPENDENCY;
	}
	public void setMPENDENCY(String mPENDENCY) {
		MPENDENCY = mPENDENCY;
	}
	public String getREJECTION_REASON() {
		return REJECTION_REASON;
	}
	public void setREJECTION_REASON(String rEJECTION_REASON) {
		REJECTION_REASON = rEJECTION_REASON;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*
	 * @Id
	 * 
	 * @Column(name = "APP_REF_NO") private String APP_REF_NO;
	 * 
	 * @Column(name = "USR_ID")// private String USR_ID;
	 * 
	 * @Column(name = "LONE_TYPE")///// private String LONE_TYPE;
	 * 
	 * @Column(name = "BUSINESS_PRODUCT")///////// private String
	 * BUSINESS_PRODUCT;
	 * 
	 * @Column(name = "CONSTITUTION") private String CONSTITUTION;
	 * 
	 * @Column(name = "MSE_NAME") private String MSE_NAME;
	 * 
	 * @Column(name = "SNCTION_DATE") private Date SNCTION_DATE;
	 * 
	 * @Column(name = "SANCTIONED_AMOUNT") private int SANCTIONED_AMOUNT;
	 * 
	 * @Column(name = "FIRST_DISBURSEMENT_DATE") private Date
	 * FIRST_DISBURSEMENT_DATE;
	 * 
	 * @Column(name = "INSERT_RATE") private String INSERT_RATE;
	 * 
	 * @Column(name = "MICRO_SMALL") private String MICRO_SMALL;
	 * 
	 * @Column(name = "MSE_ADDRESS") private String MSE_ADDRESS;
	 * 
	 * @Column(name = "CITY") private String CITY;
	 * 
	 * @Column(name = "DISTRICT") private String DISTRICT;
	 * 
	 * @Column(name = "PINCODE") private int PINCODE;
	 * 
	 * @Column(name = "STATE") private String STATE;
	 * 
	 * @Column(name = "ITPAN") private String ITPAN;
	 * 
	 * @Column(name = "UDYOG_AADHAR_NO") private String UDYOG_AADHAR_NO;
	 * 
	 * @Column(name = "INDUSTRY_NATURE") private String INDUSTRY_NATURE;
	 * 
	 * @Column(name = "INDUSTRY_SECTOR") private String INDUSTRY_SECTOR;
	 * 
	 * @Column(name = "NO_OF_EMPLOYEES") private int NO_OF_EMPLOYEES;
	 * 
	 * @Column(name = "PROJECTED_SALES") private int PROJECTED_SALES;
	 * 
	 * @Column(name = "PROJECTED_EXPORTS") private int PROJECTED_EXPORTS;
	 * 
	 * @Column(name = "NEW_EXISTING_UNIT") private String NEW_EXISTING_UNIT;
	 * 
	 * @Column(name = "UBBANKED_COUSTOMER") private String UBBANKED_COUSTOMER;
	 * 
	 * @Column(name = "NEW_CUSTOMER") private String NEW_CUSTOMER;
	 * 
	 * @Column(name = "CHIEF_PROMOTER_NAME") private String CHIEF_PROMOTER_NAME;
	 * 
	 * @Column(name = "MINORITY_COMMUNITY") private String MINORITY_COMMUNITY;
	 * 
	 * @Column(name = "HANDICAPPED") private String HANDICAPPED;
	 * 
	 * @Column(name = "WOMEN") private String WOMEN;
	 * 
	 * @Column(name = "CATEGORY") private String CATEGORY;
	 * 
	 * @Column(name = "PORTFOLIO_BASE_YER") private String PORTFOLIO_BASE_YER;
	 * 
	 * @Column(name = "PORTFOLIO_NO") private String PORTFOLIO_NO;
	 * 
	 * @Column(name = "STATUS") private String STATUS;
	 * 
	 * @Column(name = "REMARKS") private String REMARKS;
	 * 
	 * @Column(name = "FLAG") private String FLAG;
	 * 
	 * @Column(name = "QUARTER_NO") private String QUARTER_NO;//////////
	 * 
	 * @Column(name = "INSERT_DATE_TIME") private Date insertedDate = new
	 * Date();
	 * 
	 * @Column(name = "FILE_PATH") private String FILE_PATH;
	 * 
	 * @Column(name = "SSI_REGISTRATION_NO") private String SSI_REGISTRATION_NO;
	 * 
	 * @Column(name = "PROMOTER_ITPAN") private String PROMOTER_ITPAN;
	 * 
	 * @Column(name = "TENURE") private int TENURE;
	 */

}
