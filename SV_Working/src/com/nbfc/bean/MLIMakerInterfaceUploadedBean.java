package com.nbfc.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MLIMakerInterfaceUploadedBean {

	
	private MultipartFile file;
	private MultipartFile file1;
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	private int interfaceUploadID;
	private String acceptStatus;
	private String LOAN_ACCOUNT_NO;// /
	private String USR_ID;
	private String LONE_TYPE;
	//private String BUSINESS_PRODUCT;// //
	private String CONSTITUTION;// /
	private Date INSERT_DATE_TIME;
	private String MSE_NAME;//
	private Date SNCTION_DATE;//
	private int SANCTIONED_AMOUNT;//
	private Date FIRST_DISBURSEMENT_DATE;//
	private float INSERT_RATE;//
	private int TENOR_IN_MONTHS;//
	private String MICRO_SMALL;//
	private String MSE_ADDRESS;//
	private String CITY;//
	private String DISTRICT;//
	private int PINCODE;//
	private String STATE;//
	private String MSE_ITPAN;//
	private String UDYOG_AADHAR_NO;//
	//private int MSME_REGISTRATION_NO;
	private String INDUSTRY_NATURE;//
	private String INDUSTRY_SECTOR;//
	private int NO_OF_EMPLOYEES;//
	private int PROJECTED_SALES;//
	private int PROJECTED_EXPORTS;//
	private String NEW_EXISTING_UNIT;//
	private String CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE;//
	//private String FIRST_TIME_CUSTOMER;//
	private String CHIEF_PROMOTER_FIRST_NAME;
	private String CHIEF_PROMOTER_MIDDLE_NAME;
	private String CHIEF_PROMOTER_LAST_NAME;//
	private String CHIEF_PROMOTER_IT_PAN;//
	private String CHIEF_PROMOTER_MAIL_ID;//
	private int CHIEF_PROMOTER_CONTACT_NUMBER;//
	private String MINORITY_COMMUNITY;//
	private String HANDICAPPED;// /
	private String WOMEN;// /
	private String CATEGORY;//
	private String PORTFOLIO_BASE_YER;
	private String PORTFOLIO_NO;
	private String STATUS;
	private String REMARKS;
	private String FLAG;
	private String QUARTER_NO;
	private Date insertedDate;
	private String FILE_PATH;
	private String SENCTIONED_PORTFOLIO;
	private String GUARANTEE_COMMISSION;
	private String PAYOUT_CAP;
	private String PORTFOLIO_PERIOD;
	private String PORTFOLIO_START_DATE;
//	private String disbursement_status;
	private String VERIFIEDSTATUS;
	private String PORTFOLIO_NAME;
	private String FILE_ID;
	private String RETAIL_TRADE;
	private long AADHAR_NUMBER;
	
	private long guaranteeAmount;
	private long colletralSecurityAmount;
	private double outstandingAmount;
	private String partialSecurityFlag;
	
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
	
	
	public long getGuaranteeAmount() {
		return guaranteeAmount;
	}
	public void setGuaranteeAmount(long guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}
	public long getColletralSecurityAmount() {
		return colletralSecurityAmount;
	}
	public void setColletralSecurityAmount(long colletralSecurityAmount) {
		this.colletralSecurityAmount = colletralSecurityAmount;
	}
	public double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String getPartialSecurityFlag() {
		return partialSecurityFlag;
	}
	public void setPartialSecurityFlag(String partialSecurityFlag) {
		this.partialSecurityFlag = partialSecurityFlag;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getInterfaceUploadID() {
		return interfaceUploadID;
	}
	public void setInterfaceUploadID(int interfaceUploadID) {
		this.interfaceUploadID = interfaceUploadID;
	}
	public String getAcceptStatus() {
		return acceptStatus;
	}
	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
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
	/*public String getBUSINESS_PRODUCT() {
		return BUSINESS_PRODUCT;
	}
	public void setBUSINESS_PRODUCT(String bUSINESS_PRODUCT) {
		BUSINESS_PRODUCT = bUSINESS_PRODUCT;
	}*/
	public String getCONSTITUTION() {
		return CONSTITUTION;
	}
	public void setCONSTITUTION(String cONSTITUTION) {
		CONSTITUTION = cONSTITUTION;
	}
	public Date getINSERT_DATE_TIME() {
		return INSERT_DATE_TIME;
	}
	public void setINSERT_DATE_TIME(Date iNSERT_DATE_TIME) {
		INSERT_DATE_TIME = iNSERT_DATE_TIME;
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
	public float getINSERT_RATE() {
		return INSERT_RATE;
	}
	public void setINSERT_RATE(float iNSERT_RATE) {
		INSERT_RATE = iNSERT_RATE;
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
/*	public int getMSME_REGISTRATION_NO() {
		return MSME_REGISTRATION_NO;
	}
	public void setMSME_REGISTRATION_NO(int mSME_REGISTRATION_NO) {
		MSME_REGISTRATION_NO = mSME_REGISTRATION_NO;
	}
*/	public String getINDUSTRY_NATURE() {
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
/*	public String getFIRST_TIME_CUSTOMER() {
		return FIRST_TIME_CUSTOMER;
	}
	public void setFIRST_TIME_CUSTOMER(String fIRST_TIME_CUSTOMER) {
		FIRST_TIME_CUSTOMER = fIRST_TIME_CUSTOMER;
	}
*/	public String getCHIEF_PROMOTER_FIRST_NAME() {
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
	public int getCHIEF_PROMOTER_CONTACT_NUMBER() {
		return CHIEF_PROMOTER_CONTACT_NUMBER;
	}
	public void setCHIEF_PROMOTER_CONTACT_NUMBER(int cHIEF_PROMOTER_CONTACT_NUMBER) {
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
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public String getSENCTIONED_PORTFOLIO() {
		return SENCTIONED_PORTFOLIO;
	}
	public void setSENCTIONED_PORTFOLIO(String sENCTIONED_PORTFOLIO) {
		SENCTIONED_PORTFOLIO = sENCTIONED_PORTFOLIO;
	}
	public String getGUARANTEE_COMMISSION() {
		return GUARANTEE_COMMISSION;
	}
	public void setGUARANTEE_COMMISSION(String gUARANTEE_COMMISSION) {
		GUARANTEE_COMMISSION = gUARANTEE_COMMISSION;
	}
	public String getPAYOUT_CAP() {
		return PAYOUT_CAP;
	}
	public void setPAYOUT_CAP(String pAYOUT_CAP) {
		PAYOUT_CAP = pAYOUT_CAP;
	}
	public String getPORTFOLIO_PERIOD() {
		return PORTFOLIO_PERIOD;
	}
	public void setPORTFOLIO_PERIOD(String pORTFOLIO_PERIOD) {
		PORTFOLIO_PERIOD = pORTFOLIO_PERIOD;
	}
	public String getPORTFOLIO_START_DATE() {
		return PORTFOLIO_START_DATE;
	}
	public void setPORTFOLIO_START_DATE(String pORTFOLIO_START_DATE) {
		PORTFOLIO_START_DATE = pORTFOLIO_START_DATE;
	}
/*	public String getDisbursement_status() {
		return disbursement_status;
	}
	public void setDisbursement_status(String disbursement_status) {
		this.disbursement_status = disbursement_status;
	}
*/	public String getVERIFIEDSTATUS() {
		return VERIFIEDSTATUS;
	}
	public void setVERIFIEDSTATUS(String vERIFIEDSTATUS) {
		VERIFIEDSTATUS = vERIFIEDSTATUS;
	}
	
	
}
