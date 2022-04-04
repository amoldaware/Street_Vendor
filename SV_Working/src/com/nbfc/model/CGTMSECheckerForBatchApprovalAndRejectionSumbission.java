package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSECheckerForBatchApprovalAndRejectionSumbission implements
		Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID" )
	Integer interfaceUplaodId;
	
	@Column(name = "FILE_LINK")
	public String fileLink;
	
	@Column(name = "CGDAN")
	public String cgdan;
	
	@Column(name = "MPENDENCY")
	public String mPendency;
	
	@Column(name = "CGTMSE_MAKER_ID")
	public String usrId;
	
	@Column(name = "REJECTION_REASON")
	public String rejection_reason;
	
	@Column(name = "LONE_TYPE")
	public String loneType;
	
	/*@Column(name = "BUSINESS_PRODUCT")
	public String businessProduct;*/
	
	@Column(name = "LOAN_ACCOUNT_NO")
	public String loanAccountNo;
	
	@Column(name = "CONSTITUTION")
	public String constitution;
	
	@Column(name = "MSE_NAME")
	public String mseName;
	
	@Column(name = "SANCTION_DATE")
	public String snctionDate;
	
	@Column(name = "SANCTIONED_AMOUNT")
	public String sanctionedAmount;
	
	@Column(name = "FIRST_DISBURSEMENT_DATE")
	public String firstDisbursementDate;
	
	@Column(name = "INTEREST_RATE")
	public String interestRate;
	
	@Column(name = "TENOR_IN_MONTHS")
	public String tenorInMonth;
	
	@Column(name = "MICRO_SMALL")
	public String microSmall;
	
	@Column(name = "MSE_ADDRESS")
	public String mseAddress;
	
	@Column(name = "CITY")
	public String city;
	
	@Column(name = "DISTRICT")
	public String district;
	
	@Column(name = "PINCODE")
	public String pincode;
	
	@Column(name = "STATE")
	public String state;
	
	@Column(name = "MSE_ITPAN")
	public String mseITPAN;
	
	@Column(name = "UDYOG_AADHAR_NO")
	public String udyogAadharNo;
	
	/*@Column(name = "MSME_REGISTRATION_NO")
	public String mseregistrationNo;
	*/
	@Column(name = "INDUSTRY_NATURE")
	public String industryNature;
	
	@Column(name = "INDUSTRY_SECTOR")
	public String industrySector;
	
	@Column(name = "NO_OF_EMPLOYEES")
	public String noOfEmployees;
	
	@Column(name = "PROJECTED_SALES")
	public String projectedSales;
	
	@Column(name = "PROJECTED_EXPORTS")
	public String projectedExports;
	
	@Column(name = "NEW_EXISTING_UNIT")
	public String newExistingUnit;
	
	@Column(name = "PREVIOUS_BANKING_EXPERIENCE")
	public String previousbankingExperience;
	
	/*@Column(name = "FIRST_TIME_CUSTOMER")
	public String firstTimeCustomer;*/
	
	@Column(name = "CHIEF_PROMOTER_FIRST_NAME")
	public String chiefPromoterFirstName;
	
	@Column(name = "CHIEF_PROMOTER_MIDDLE_NAME")
	public String chiefPromoterMiddleName;
	
	@Column(name = "CHIEF_PROMOTER_LAST_NAME")
	public String chiefPromoterLastName;
	
	@Column(name = "CHIEF_PROMOTER_IT_PAN")
	public String chiefPromoterITPAN;
	
	@Column(name = "CHIEF_PROMOTER_MAIL_ID")
	public String chiefPromoterMailId;
	
	@Column(name = "CHIEF_PROMOTER_CONTACT_NUMBER")
	public String chiefPromoterContactNo;
	
	@Column(name = "MINORITY_COMMUNITY")
	public String minorityCommunity;
	
	@Column(name = "HANDICAPPED")
	public String handicapped;
	
	@Column(name = "WOMEN")
	public String women;
	
	@Column(name = "CATEGORY")
	public String category;
	
	@Column(name = "PORTFOLIO_BASE_YER")
	public String protfolioBaseYer;

	@Column(name = "PORTFOLIO_NO")
	public Integer portfolioNo;

	@Column(name = "NBFC_UPLOADED_DATE")
	public Date insertDateTime;

	@Column(name = "STATUS")
	public String status;

	@Column(name = "REMARKS")
	public String remarks;

	@Column(name = "FLAG")
	public String flag;
	
	@Column(name = "FILE_PATH")
	public String filePath;
	
	@Column (name="CGTMSC_MAKER_DATE")
	public Date cgtmseMakerDate;
	
	@Column (name="PARTIAL_SECURITY_FLAG")
	public String PARTIAL_SECURITY_FLAG;
	@Column (name="GUARANTEE_AMOUNT")
	public String GUARANTEE_AMOUNT;
	@Column (name="COLLETRAL_SECURITY_AMOUNT")
	public String COLLETRAL_SECURITY_AMOUNT;
	@Column (name="OUTSTANDING_AMOUNT")
	public String OUTSTANDING_AMOUNT;
	@Column (name="RETAIL_TRADE")
	public String RETAIL_TRADE;
	@Column(name="AADHAR_NUMBER")
	public Long AADHAR_NUMBER;
	@Column(name="FILE_ID")
	public String FILE_ID;
	
	
	
	
	public String getFILE_ID() {
		return FILE_ID;
	}
	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}
	public Long getAADHAR_NUMBER() {
		return AADHAR_NUMBER;
	}
	public void setAADHAR_NUMBER(Long aADHAR_NUMBER) {
		AADHAR_NUMBER = aADHAR_NUMBER;
	}
	public String getPARTIAL_SECURITY_FLAG() {
		return PARTIAL_SECURITY_FLAG;
	}
	public void setPARTIAL_SECURITY_FLAG(String pARTIAL_SECURITY_FLAG) {
		PARTIAL_SECURITY_FLAG = pARTIAL_SECURITY_FLAG;
	}
	public String getGUARANTEE_AMOUNT() {
		return GUARANTEE_AMOUNT;
	}
	public void setGUARANTEE_AMOUNT(String gUARANTEE_AMOUNT) {
		GUARANTEE_AMOUNT = gUARANTEE_AMOUNT;
	}
	public String getCOLLETRAL_SECURITY_AMOUNT() {
		return COLLETRAL_SECURITY_AMOUNT;
	}
	public void setCOLLETRAL_SECURITY_AMOUNT(String cOLLETRAL_SECURITY_AMOUNT) {
		COLLETRAL_SECURITY_AMOUNT = cOLLETRAL_SECURITY_AMOUNT;
	}
	public String getOUTSTANDING_AMOUNT() {
		return OUTSTANDING_AMOUNT;
	}
	public void setOUTSTANDING_AMOUNT(String oUTSTANDING_AMOUNT) {
		OUTSTANDING_AMOUNT = oUTSTANDING_AMOUNT;
	}
	public String getRETAIL_TRADE() {
		return RETAIL_TRADE;
	}
	public void setRETAIL_TRADE(String rETAIL_TRADE) {
		RETAIL_TRADE = rETAIL_TRADE;
	}
	/*public String getDisbursmentStatus() {
		return disbursmentStatus;
	}
	public void setDisbursmentStatus(String disbursmentStatus) {
		this.disbursmentStatus = disbursmentStatus;
	}*/
	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	/*@Column(name = "DISBURSEMENT_STATUS")
	public String disbursmentStatus;*/

	
	public String getLoanAccountNo() {
		return loanAccountNo;
	}
	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getTenorInMonth() {
		return tenorInMonth;
	}
	public void setTenorInMonth(String tenorInMonth) {
		this.tenorInMonth = tenorInMonth;
	}
	public String getMseITPAN() {
		return mseITPAN;
	}
	public void setMseITPAN(String mseITPAN) {
		this.mseITPAN = mseITPAN;
	}
/*	public String getMseregistrationNo() {
		return mseregistrationNo;
	}
	public void setMseregistrationNo(String mseregistrationNo) {
		this.mseregistrationNo = mseregistrationNo;
	}*/
	public String getPreviousbankingExperience() {
		return previousbankingExperience;
	}
	public void setPreviousbankingExperience(String previousbankingExperience) {
		this.previousbankingExperience = previousbankingExperience;
	}
	/*public String getFirstTimeCustomer() {
		return firstTimeCustomer;
	}
	public void setFirstTimeCustomer(String firstTimeCustomer) {
		this.firstTimeCustomer = firstTimeCustomer;
	}*/
	public String getChiefPromoterFirstName() {
		return chiefPromoterFirstName;
	}
	public void setChiefPromoterFirstName(String chiefPromoterFirstName) {
		this.chiefPromoterFirstName = chiefPromoterFirstName;
	}
	public String getChiefPromoterMiddleName() {
		return chiefPromoterMiddleName;
	}
	public void setChiefPromoterMiddleName(String chiefPromoterMiddleName) {
		this.chiefPromoterMiddleName = chiefPromoterMiddleName;
	}
	public String getChiefPromoterLastName() {
		return chiefPromoterLastName;
	}
	public void setChiefPromoterLastName(String chiefPromoterLastName) {
		this.chiefPromoterLastName = chiefPromoterLastName;
	}
	public String getChiefPromoterITPAN() {
		return chiefPromoterITPAN;
	}
	public void setChiefPromoterITPAN(String chiefPromoterITPAN) {
		this.chiefPromoterITPAN = chiefPromoterITPAN;
	}
	public String getChiefPromoterMailId() {
		return chiefPromoterMailId;
	}
	public void setChiefPromoterMailId(String chiefPromoterMailId) {
		this.chiefPromoterMailId = chiefPromoterMailId;
	}
	public String getChiefPromoterContactNo() {
		return chiefPromoterContactNo;
	}
	public void setChiefPromoterContactNo(String chiefPromoterContactNo) {
		this.chiefPromoterContactNo = chiefPromoterContactNo;
	}
	

	
	public Date getCgtmseMakerDate() {
		return cgtmseMakerDate;
	}
	public void setCgtmseMakerDate(Date cgtmseMakerDate) {
		this.cgtmseMakerDate = cgtmseMakerDate;
	}
	public Integer getSubPortfolioNo() {
		return subPortfolioNo;
	}
	public void setSubPortfolioNo(Integer subPortfolioNo) {
		this.subPortfolioNo = subPortfolioNo;
	}
	
	public String getRejection_reason() {
		return rejection_reason;
	}
	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getLoneType() {
	return loneType;
}

public void setLoneType(String loneType) {
	this.loneType = loneType;
}

/*public String getBusinessProduct() {
	return businessProduct;
}

public void setBusinessProduct(String businessProduct) {
	this.businessProduct = businessProduct;
}*/


public String getConstitution() {
	return constitution;
}

public void setConstitution(String constitution) {
	this.constitution = constitution;
}

public String getMseName() {
	return mseName;
}

public void setMseName(String mseName) {
	this.mseName = mseName;
}

public String getSnctionDate() {
	return snctionDate;
}

public void setSnctionDate(String snctionDate) {
	this.snctionDate = snctionDate;
}

public String getSanctionedAmount() {
	return sanctionedAmount;
}

public void setSanctionedAmount(String sanctionedAmount) {
	this.sanctionedAmount = sanctionedAmount;
}

public String getFirstDisbursementDate() {
	return firstDisbursementDate;
}

public void setFirstDisbursementDate(String firstDisbursementDate) {
	this.firstDisbursementDate = firstDisbursementDate;
}



public String getMicroSmall() {
	return microSmall;
}

public void setMicroSmall(String microSmall) {
	this.microSmall = microSmall;
}

public String getMseAddress() {
	return mseAddress;
}

public void setMseAddress(String mseAddress) {
	this.mseAddress = mseAddress;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
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

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}



public String getUdyogAadharNo() {
	return udyogAadharNo;
}

public void setUdyogAadharNo(String udyogAadharNo) {
	this.udyogAadharNo = udyogAadharNo;
}

public String getIndustryNature() {
	return industryNature;
}

public void setIndustryNature(String industryNature) {
	this.industryNature = industryNature;
}

public String getIndustrySector() {
	return industrySector;
}

public void setIndustrySector(String industrySector) {
	this.industrySector = industrySector;
}

public String getNoOfEmployees() {
	return noOfEmployees;
}

public void setNoOfEmployees(String noOfEmployees) {
	this.noOfEmployees = noOfEmployees;
}

public String getProjectedSales() {
	return projectedSales;
}

public void setProjectedSales(String projectedSales) {
	this.projectedSales = projectedSales;
}

public String getProjectedExports() {
	return projectedExports;
}

public void setProjectedExports(String projectedExports) {
	this.projectedExports = projectedExports;
}

public String getNewExistingUnit() {
	return newExistingUnit;
}

public void setNewExistingUnit(String newExistingUnit) {
	this.newExistingUnit = newExistingUnit;
}

public String getMinorityCommunity() {
	return minorityCommunity;
}

public void setMinorityCommunity(String minorityCommunity) {
	this.minorityCommunity = minorityCommunity;
}

public String getHandicapped() {
	return handicapped;
}

public void setHandicapped(String handicapped) {
	this.handicapped = handicapped;
}

public String getWomen() {
	return women;
}

public void setWomen(String women) {
	this.women = women;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}



public String getProtfolioBaseYer() {
	return protfolioBaseYer;
}

public void setProtfolioBaseYer(String protfolioBaseYer) {
	this.protfolioBaseYer = protfolioBaseYer;
}


public Date getInsertDateTime() {
	return insertDateTime;
}

public void setInsertDateTime(Date dateofUplaod) {
	this.insertDateTime = dateofUplaod;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getFilePath() {
	return filePath;
}

public void setFilePath(String filePath) {
	this.filePath = filePath;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public String getFlag() {
	return flag;
}

public void setFlag(String flag) {
	this.flag = flag;
}

public String getUsrId() {
	return usrId;
}
public void setUsrId(String usrId) {
	this.usrId = usrId;
}
public Integer getInterfaceUplaodId() {
	return interfaceUplaodId;
}
public void setInterfaceUplaodId(Integer interfaceUplaodId) {
	this.interfaceUplaodId = interfaceUplaodId;
}
public Integer getPortfolioNo() {
	return portfolioNo;
}
public void setPortfolioNo(Integer portfolioNo) {
	this.portfolioNo = portfolioNo;
}
public String getFileLink() {
	return fileLink;
}
public void setFileLink(String fileLink) {
	this.fileLink = fileLink;
}
public String getCgdan() {
	return cgdan;
}
public void setCgdan(String cgdan) {
	this.cgdan = cgdan;
}
public String getmPendency() {
	return mPendency;
}
public void setmPendency(String mPendency) {
	this.mPendency = mPendency;
}

}
