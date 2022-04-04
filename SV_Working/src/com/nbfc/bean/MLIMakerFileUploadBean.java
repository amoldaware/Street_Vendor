package com.nbfc.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class MLIMakerFileUploadBean {

	private MultipartFile file;
	private Integer interfaceUploadID;
	private String acceptStatus;
	private String LOAN_ACCOUNT_NO;// /
	private String USR_ID;
	private String LONE_TYPE;
	// private String BUSINESS_PRODUCT;// //
	private String CONSTITUTION;// /
	private Date INSERT_DATE_TIME;
	private String MSE_NAME;//
	private Date SNCTION_DATE;//
	private Integer SANCTIONED_AMOUNT;//
	private Date FIRST_DISBURSEMENT_DATE;//
	private float INSERT_RATE;//
	private Integer TENOR_IN_MONTHS;//
	private String MICRO_SMALL;//
	private String MSE_ADDRESS;//
	private String CITY;//
	private String DISTRICT;//
	private Integer PINCODE;//
	private String STATE;//
	private String MSE_ITPAN;//
	private String UDYOG_AADHAR_NO;//
	private Integer MSME_REGISTRATION_NO;
	private String INDUSTRY_NATURE;//
	private String INDUSTRY_SECTOR;//
	private Integer NO_OF_EMPLOYEES;//
	private Integer PROJECTED_SALES;//
	private Integer PROJECTED_EXPORTS;//
	private String NEW_EXISTING_UNIT;//
	private String CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE;//
	// private String FIRST_TIME_CUSTOMER;//
	private String CHIEF_PROMOTER_FIRST_NAME;
	private String CHIEF_PROMOTER_MIDDLE_NAME;
	private String CHIEF_PROMOTER_LAST_NAME;//
	private String CHIEF_PROMOTER_IT_PAN;//
	private String CHIEF_PROMOTER_MAIL_ID;//
	private Integer CHIEF_PROMOTER_CONTACT_NUMBER;//
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
	private Long PAYOUT_CAP;
	private String PORTFOLIO_PERIOD;
	private Date PORTFOLIO_START_DATE;
	// private String disbursement_status;
	private String VERIFIEDSTATUS;
	private String PORTFOLIO_NAME;
	private long guaranteeAmount;
	private long colletralSecurityAmount;
	private Double outstandingAmount;
	private String partialSecurityFlag;
	private String retail_trade;
	private String AADHAR_NUMBER;
	
	private double TOT_EXP_SIZE;
	private String EXP_NO;
	private Double UTIL_EXP;
	private Double PENDING_EXP;
	private String GURANTEE_COVERAGE;
	
	private Long utilExp1;
	private Long pendingExp1;
	private Long totExpSize1;
	private Double GURANTEE_FEE;
	private Integer TENURE;
	
	
	public Long getUtilExp1() {
		return utilExp1;
	}
	public void setUtilExp1(Long utilExp1) {
		this.utilExp1 = utilExp1;
	}
	public Long getPendingExp1() {
		return pendingExp1;
	}
	public void setPendingExp1(Long pendingExp1) {
		this.pendingExp1 = pendingExp1;
	}
	public Long getTotExpSize1() {
		return totExpSize1;
	}
	public void setTotExpSize1(Long totExpSize1) {
		this.totExpSize1 = totExpSize1;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Integer getInterfaceUploadID() {
		return interfaceUploadID;
	}
	public void setInterfaceUploadID(Integer interfaceUploadID) {
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
	public Integer getSANCTIONED_AMOUNT() {
		return SANCTIONED_AMOUNT;
	}
	public void setSANCTIONED_AMOUNT(Integer sANCTIONED_AMOUNT) {
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
	public Integer getTENOR_IN_MONTHS() {
		return TENOR_IN_MONTHS;
	}
	public void setTENOR_IN_MONTHS(Integer tENOR_IN_MONTHS) {
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
	public Integer getPINCODE() {
		return PINCODE;
	}
	public void setPINCODE(Integer pINCODE) {
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
	public Integer getMSME_REGISTRATION_NO() {
		return MSME_REGISTRATION_NO;
	}
	public void setMSME_REGISTRATION_NO(Integer mSME_REGISTRATION_NO) {
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
	public Integer getNO_OF_EMPLOYEES() {
		return NO_OF_EMPLOYEES;
	}
	public void setNO_OF_EMPLOYEES(Integer nO_OF_EMPLOYEES) {
		NO_OF_EMPLOYEES = nO_OF_EMPLOYEES;
	}
	public Integer getPROJECTED_SALES() {
		return PROJECTED_SALES;
	}
	public void setPROJECTED_SALES(Integer pROJECTED_SALES) {
		PROJECTED_SALES = pROJECTED_SALES;
	}
	public Integer getPROJECTED_EXPORTS() {
		return PROJECTED_EXPORTS;
	}
	public void setPROJECTED_EXPORTS(Integer pROJECTED_EXPORTS) {
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
	public Integer getCHIEF_PROMOTER_CONTACT_NUMBER() {
		return CHIEF_PROMOTER_CONTACT_NUMBER;
	}
	public void setCHIEF_PROMOTER_CONTACT_NUMBER(
			Integer cHIEF_PROMOTER_CONTACT_NUMBER) {
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
	
	public Long getPAYOUT_CAP() {
		return PAYOUT_CAP;
	}
	public void setPAYOUT_CAP(Long pAYOUT_CAP) {
		PAYOUT_CAP = pAYOUT_CAP;
	}
	public String getPORTFOLIO_PERIOD() {
		return PORTFOLIO_PERIOD;
	}
	public void setPORTFOLIO_PERIOD(String pORTFOLIO_PERIOD) {
		PORTFOLIO_PERIOD = pORTFOLIO_PERIOD;
	}
	public Date getPORTFOLIO_START_DATE() {
		return PORTFOLIO_START_DATE;
	}
	public void setPORTFOLIO_START_DATE(Date pORTFOLIO_START_DATE) {
		PORTFOLIO_START_DATE = pORTFOLIO_START_DATE;
	}
	public String getVERIFIEDSTATUS() {
		return VERIFIEDSTATUS;
	}
	public void setVERIFIEDSTATUS(String vERIFIEDSTATUS) {
		VERIFIEDSTATUS = vERIFIEDSTATUS;
	}
	public String getPORTFOLIO_NAME() {
		return PORTFOLIO_NAME;
	}
	public void setPORTFOLIO_NAME(String pORTFOLIO_NAME) {
		PORTFOLIO_NAME = pORTFOLIO_NAME;
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
	public Double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String getPartialSecurityFlag() {
		return partialSecurityFlag;
	}
	public void setPartialSecurityFlag(String partialSecurityFlag) {
		this.partialSecurityFlag = partialSecurityFlag;
	}
	public String getRetail_trade() {
		return retail_trade;
	}
	public void setRetail_trade(String retail_trade) {
		this.retail_trade = retail_trade;
	}
	
	public String getAADHAR_NUMBER() {
		return AADHAR_NUMBER;
	}
	public void setAADHAR_NUMBER(String aADHAR_NUMBER) {
		AADHAR_NUMBER = aADHAR_NUMBER;
	}
	public double getTOT_EXP_SIZE() {
		return TOT_EXP_SIZE;
	}
	public void setTOT_EXP_SIZE(double tOT_EXP_SIZE) {
		TOT_EXP_SIZE = tOT_EXP_SIZE;
	}
	public String getEXP_NO() {
		return EXP_NO;
	}
	public void setEXP_NO(String eXP_NO) {
		EXP_NO = eXP_NO;
	}
	public Double getUTIL_EXP() {
		return UTIL_EXP;
	}
	public void setUTIL_EXP(Double uTIL_EXP) {
		UTIL_EXP = uTIL_EXP;
	}
	public Double getPENDING_EXP() {
		return PENDING_EXP;
	}
	public void setPENDING_EXP(Double pENDING_EXP) {
		PENDING_EXP = pENDING_EXP;
	}
	public String getGURANTEE_COVERAGE() {
		return GURANTEE_COVERAGE;
	}
	public void setGURANTEE_COVERAGE(String gURANTEE_COVERAGE) {
		GURANTEE_COVERAGE = gURANTEE_COVERAGE;
	}
	public Double getGURANTEE_FEE() {
		return GURANTEE_FEE;
	}
	public void setGURANTEE_FEE(Double gURANTEE_FEE) {
		GURANTEE_FEE = gURANTEE_FEE;
	}
	public Integer getTENURE() {
		return TENURE;
	}
	public void setTENURE(Integer tENURE) {
		TENURE = tENURE;
	}
	

}
