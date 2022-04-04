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
/**
 * @author Saurav Tyagi 2017
 * 
 */
@Entity
@Table(name = "nbfc_interface_upload")
public class MliMakerEntity implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_generator")
	@TableGenerator(name = "employee_generator", table = "pk_table", pkColumnName = "name", valueColumnName = "value", allocationSize = 1)
	@Column(name="INTERFACE_UPLOAD_ID")
	private Integer interfaceUploadID;
	
	@Column(name = "LOAN_ACCOUNT_NO")
	private String LOAN_ACCOUNT_NO;
	@Column(name = "PORTFOLIO_NO")
	private String PORTFOLIO_NO;
	@Column(name = "NBFC_MAKER_ID")
	private String USR_ID;
	@Column(name = "LONE_TYPE")
	private String LONE_TYPE;
	//@Column(name = "BUSINESS_PRODUCT")
	//private String BUSINESS_PRODUCT;
	@Column(name = "CONSTITUTION")
	private String CONSTITUTION;
	@Column(name = "NBFC_UPLOADED_DATE")
	private Date INSERT_DATE_TIME = new Date();;
	@Column(name = "MSE_NAME")
	private String MSE_NAME;
	@Column(name = "SANCTION_DATE")
	private Date SNCTION_DATE;
	@Column(name = "SANCTIONED_AMOUNT")
	private int SANCTIONED_AMOUNT;
	@Column(name = "FIRST_DISBURSEMENT_DATE")
	private Date FIRST_DISBURSEMENT_DATE;
	@Column(name = "INTEREST_RATE")
	private float INTEREST_RATE;
	@Column(name = "TENOR_IN_MONTHS")
	private int TENOR_IN_MONTHS;
	@Column(name = "MICRO_SMALL")
	private String MICRO_SMALL;
	@Column(name = "MSE_ADDRESS")
	private String MSE_ADDRESS;
	@Column(name = "CITY")
	private String CITY;
	@Column(name = "DISTRICT")
	private String DISTRICT;
	@Column(name = "PINCODE")
	private int PINCODE;
	@Column(name = "STATE")
	private String STATE;
	@Column(name = "MSE_ITPAN")
	private String MSE_ITPAN;
	@Column(name = "UDYOG_AADHAR_NO")
	private String UDYOG_AADHAR_NO;
	//@Column(name = "MSME_REGISTRATION_NO")
	//private int MSME_REGISTRATION_NO;
	@Column(name = "INDUSTRY_NATURE")
	private String INDUSTRY_NATURE;
	@Column(name = "INDUSTRY_SECTOR")
	private String INDUSTRY_SECTOR;
	@Column(name = "NO_OF_EMPLOYEES")
	private int NO_OF_EMPLOYEES;
	@Column(name = "PROJECTED_SALES")
	private int PROJECTED_SALES;
	@Column(name = "PROJECTED_EXPORTS")
	private int PROJECTED_EXPORTS;
	@Column(name = "NEW_EXISTING_UNIT")
	private String NEW_EXISTING_UNIT;
	@Column(name = "PREVIOUS_BANKING_EXPERIENCE")
	private String CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE;
	//@Column(name = "FIRST_TIME_CUSTOMER")
	//private String FIRST_TIME_CUSTOMER;
	@Column(name = "CHIEF_PROMOTER_FIRST_NAME")
	private String CHIEF_PROMOTER_FIRST_NAME;
	@Column(name = "CHIEF_PROMOTER_MIDDLE_NAME")
	private String CHIEF_PROMOTER_MIDDLE_NAME;
	@Column(name = "CHIEF_PROMOTER_LAST_NAME")
	private String CHIEF_PROMOTER_LAST_NAME;//
	@Column(name = "CHIEF_PROMOTER_IT_PAN")
	private String CHIEF_PROMOTER_IT_PAN;
	@Column(name = "CHIEF_PROMOTER_MAIL_ID")
	private String CHIEF_PROMOTER_MAIL_ID;
	@Column(name = "CHIEF_PROMOTER_CONTACT_NUMBER")
	private int CHIEF_PROMOTER_CONTACT_NUMBER;
	@Column(name = "MINORITY_COMMUNITY")
	private String MINORITY_COMMUNITY;
	@Column(name = "HANDICAPPED")
	private String HANDICAPPED;
	@Column(name = "WOMEN")
	private String WOMEN;
	@Column(name = "CATEGORY")
	private String CATEGORY;
	@Column(name = "PORTFOLIO_BASE_YER")
	private String PORTFOLIO_BASE_YER;
	@Column(name = "STATUS")
	private String STATUS;
	@Column(name = "REMARKS")
	private String REMARKS;
	@Column(name = "FLAG")
	private String FLAG;
	@Column(name = "SUB_PORTFOLIO_DTL_NO")
	private String QUARTER_NO;
	@Column(name = "FILE_PATH")
	private String FILE_PATH;
	//@Column(name = "DISBURSEMENT_STATUS")
	//private String DISBURSEMENT_STATUS;
	@Column(name="PORTFOLIO_NAME")
	private String PORTFOLIO_NAME;
	@Column(name="FILE_ID")
	private String FILE_ID;
	@Column(name="DAN_ID")
	private String DAN_ID;
	@Column(name="CGPAN")
	private String CGPAN;
	
	public String getCGPAN() {
		return CGPAN;
	}

	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}

	@Column(name="PARTIAL_SECURITY_FLAG")
	private String PARTIAL_SECURITY_FLAG;
	
	@Column(name="GUARANTEE_AMOUNT")
	private long GUARANTEE_AMOUNT;
	
	@Column(name="COLLETRAL_SECURITY_AMOUNT")
	private long COLLETRAL_SECURITY_AMOUNT;
	
	@Column(name="OUTSTANDING_AMOUNT")
	private double OUTSTANDING_AMOUNT;
	
	@Column(name="RETAIL_TRADE")
	private String RETAIL_TRADE;
	
	@Column(name="AADHAR_NUMBER")
	private long AADHAR_NUMBER;
	
	public long getAADHAR_NUMBER() {
		return AADHAR_NUMBER;
	}

	public void setAADHAR_NUMBER(long aADHAR_NUMBER) {
		AADHAR_NUMBER = aADHAR_NUMBER;
	}

	public String getRETAIL_TRADE() {
		return RETAIL_TRADE;
	}

	public void setRETAIL_TRADE(String rETAIL_TRADE) {
		RETAIL_TRADE = rETAIL_TRADE;
	}

	public String getPARTIAL_SECURITY_FLAG() {
		return PARTIAL_SECURITY_FLAG;
	}

	public void setPARTIAL_SECURITY_FLAG(String pARTIAL_SECURITY_FLAG) {
		PARTIAL_SECURITY_FLAG = pARTIAL_SECURITY_FLAG;
	}

	public long getGUARANTEE_AMOUNT() {
		return GUARANTEE_AMOUNT;
	}

	public void setGUARANTEE_AMOUNT(long gUARANTEE_AMOUNT) {
		GUARANTEE_AMOUNT = gUARANTEE_AMOUNT;
	}

	public long getCOLLETRAL_SECURITY_AMOUNT() {
		return COLLETRAL_SECURITY_AMOUNT;
	}

	public void setCOLLETRAL_SECURITY_AMOUNT(long cOLLETRAL_SECURITY_AMOUNT) {
		COLLETRAL_SECURITY_AMOUNT = cOLLETRAL_SECURITY_AMOUNT;
	}

	public double getOUTSTANDING_AMOUNT() {
		return OUTSTANDING_AMOUNT;
	}

	public void setOUTSTANDING_AMOUNT(double oUTSTANDING_AMOUNT) {
		OUTSTANDING_AMOUNT = oUTSTANDING_AMOUNT;
	}

	

	
	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	public String getFILE_ID() {
		return FILE_ID;
	}

	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}

	

	public String getPORTFOLIO_NAME() {
		return PORTFOLIO_NAME;
	}

	public void setPORTFOLIO_NAME(String pORTFOLIO_NAME) {
		PORTFOLIO_NAME = pORTFOLIO_NAME;
	}

	
	public float getINTEREST_RATE() {
		return INTEREST_RATE;
	}

	public void setINTEREST_RATE(float iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
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

	public String getLOAN_ACCOUNT_NO() {
		return LOAN_ACCOUNT_NO;
	}

	public void setLOAN_ACCOUNT_NO(String lOAN_ACCOUNT_NO) {
		LOAN_ACCOUNT_NO = lOAN_ACCOUNT_NO;
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

	
	public String getCONSTITUTION() {
		return CONSTITUTION;
	}

	public void setCONSTITUTION(String cONSTITUTION) {
		CONSTITUTION = cONSTITUTION;
	}

	@PreUpdate
	public void setINSERT_DATE_TIME(Date iNSERT_DATE_TIME) {
		this.INSERT_DATE_TIME = new Date();
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

	public int getSANCTIONED_AMOUNT() {
		return SANCTIONED_AMOUNT;
	}

	public void setSANCTIONED_AMOUNT(int sANCTIONED_AMOUNT) {
		SANCTIONED_AMOUNT = sANCTIONED_AMOUNT;
	}

	public Date getFIRST_DISBURSEMENT_DATE() {
		return FIRST_DISBURSEMENT_DATE;
	}

	public void setFIRST_DISBURSEMENT_DATE(Date fIRST_DISBURSEMENT_DATE) {
		FIRST_DISBURSEMENT_DATE = fIRST_DISBURSEMENT_DATE;
	}

	public int getTENOR_IN_MONTHS() {
		return TENOR_IN_MONTHS;
	}

	public void setTENOR_IN_MONTHS(int tENOR_IN_MONTHS) {
		TENOR_IN_MONTHS = tENOR_IN_MONTHS;
	}

	public String getMICRO_SMALL() {
		return MICRO_SMALL;
	}

	public void setMICRO_SMALL(String mICRO_SMALL) {
		MICRO_SMALL = mICRO_SMALL;
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

	public int getPINCODE() {
		return PINCODE;
	}

	public void setPINCODE(int pINCODE) {
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

	public int getNO_OF_EMPLOYEES() {
		return NO_OF_EMPLOYEES;
	}

	public void setNO_OF_EMPLOYEES(int nO_OF_EMPLOYEES) {
		NO_OF_EMPLOYEES = nO_OF_EMPLOYEES;
	}

	public int getPROJECTED_SALES() {
		return PROJECTED_SALES;
	}

	public void setPROJECTED_SALES(int pROJECTED_SALES) {
		PROJECTED_SALES = pROJECTED_SALES;
	}

	public int getPROJECTED_EXPORTS() {
		return PROJECTED_EXPORTS;
	}

	public void setPROJECTED_EXPORTS(int pROJECTED_EXPORTS) {
		PROJECTED_EXPORTS = pROJECTED_EXPORTS;
	}

	public String getNEW_EXISTING_UNIT() {
		return NEW_EXISTING_UNIT;
	}

	public void setNEW_EXISTING_UNIT(String nEW_EXISTING_UNIT) {
		NEW_EXISTING_UNIT = nEW_EXISTING_UNIT;
	}

	public String getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE() {
		return CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE;
	}

	public void setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(
			String cUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE) {
		CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE = cUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE;
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

	public int getCHIEF_PROMOTER_CONTACT_NUMBER() {
		return CHIEF_PROMOTER_CONTACT_NUMBER;
	}

	public void setCHIEF_PROMOTER_CONTACT_NUMBER(
			int cHIEF_PROMOTER_CONTACT_NUMBER) {
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

	public String getQUARTER_NO() {
		return QUARTER_NO;
	}

	public void setQUARTER_NO(String qUARTER_NO) {
		QUARTER_NO = qUARTER_NO;
	}

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
