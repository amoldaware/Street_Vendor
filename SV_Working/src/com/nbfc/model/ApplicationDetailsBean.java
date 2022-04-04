package com.nbfc.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONObject;


@Entity
@Table(name = "disbursement_details")
public class ApplicationDetailsBean {
	
	@Id
	@Column(name="applicationNo",length=14)
	private String applicationNo;
	
	@Column(name="category" , length=10)
	private String category;
	
	@Column(name="voterIDCardNo" , length=50)
	private String voterIDCardNo;
	
	@Column(name="svaVendingActivityName" , length=200)
	private String svaVendingActivityName;
	
	@Column(name="durationOfVending" , length=200)
	private String durationOfVending;
	
	@Column(name="vendorType" , length=10)
	private String vendorType;
	
	@Column(name="disbursedDt", length=200)
	private String disbursedDt;
	
	@Column(name="applicationDate", length=200)
	private String applicationDate; 
	
	@Column(name="lenderName" , length=200)
	private String lenderName;
	
	@Column(name="lenderBranch", length=100)
	private String lenderBranch;
	
	@Column(name="isDisbursed", length=50)
	private String isDisbursed;
	
	@Column(name="disbursedAmount" , length=100)
	private String disbursedAmount;
	
	@Column(name="loanAccountNumber" , length=200)
	private String loanAccountNumber;
	
	@Column(name="sanctionedDt" , length=200)
	private String sanctionedDt;
	
	@Column(name="sanctionedAmount" , length=100)
	private String sanctionedAmount;
	
	@Column(name="loanTenure" , length=50)
	private String loanTenure;
	
	@Column(name="moratoriumMonths" , length=50)
	private String moratoriumMonths;
	
	@Column(name="rateOfInterest" , length=50)
	private String rateOfInterest;
	
	@Column(name="nameOfCIGGLG", length=200)
	private String nameOfCIGGLG;
	
	@Column(name="memberOfCIGGLG", length=200)
	private String memberOfCIGGLG;
	
	@Column(name="loanTerm", length=50)
	private String loanTerm;
	
	@Column(name="status" , length=10)
	private String status;
	
	@Column(name="guaranteeReady", length=10)
	private String guaranteeReady;
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public String getCategory() {
		return category;
	}
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}
	public String getSvaVendingActivityName() {
		return svaVendingActivityName;
	}
	public String getDurationOfVending() {
		return durationOfVending;
	}
	public String getVendorType() {
		return vendorType;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public String getLenderName() {
		return lenderName;
	}
	public String getLenderBranch() {
		return lenderBranch;
	}
	public String getIsDisbursed() {
		return isDisbursed;
	}
    
	public String getDisbursedDt() {
		return disbursedDt;
	}
	public void setDisbursedDt(String disbursedDt) {
		this.disbursedDt = disbursedDt;
	}
	public String getDisbursedAmount() {
		return disbursedAmount;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}
	public void setSvaVendingActivityName(String svaVendingActivityName) {
		this.svaVendingActivityName = svaVendingActivityName;
	}
	public void setDurationOfVending(String durationOfVending) {
		this.durationOfVending = durationOfVending;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	public void setLenderBranch(String lenderBranch) {
		this.lenderBranch = lenderBranch;
	}
	public void setIsDisbursed(String isDisbursed) {
		this.isDisbursed = isDisbursed;
	}
	
	public void setDisbursedAmount(String disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	
	public String getSanctionedDt() {
		return sanctionedDt;
	}
	public String getSanctionedAmount() {
		return sanctionedAmount;
	}
	public String getLoanTenure() {
		return loanTenure;
	}
	public String getMoratoriumMonths() {
		return moratoriumMonths;
	}
	public String getRateOfInterest() {
		return rateOfInterest;
	}
	public String getNameOfCIGGLG() {
		return nameOfCIGGLG;
	}
	public String getMemberOfCIGGLG() {
		return memberOfCIGGLG;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public String getStatus() {
		return status;
	}
	public void setSanctionedDt(String sanctionedDt) {
		this.sanctionedDt = sanctionedDt;
	}
	public void setSanctionedAmount(String sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}
	public void setMoratoriumMonths(String moratoriumMonths) {
		this.moratoriumMonths = moratoriumMonths;
	}
	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public void setNameOfCIGGLG(String nameOfCIGGLG) {
		this.nameOfCIGGLG = nameOfCIGGLG;
	}
	public void setMemberOfCIGGLG(String memberOfCIGGLG) {
		this.memberOfCIGGLG = memberOfCIGGLG;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGuaranteeReady() {
		return guaranteeReady;
	}
	public void setGuaranteeReady(String guaranteeReady) {
		this.guaranteeReady = guaranteeReady;
	}

	@Embedded
	private PersonalDetailsBean personalDetails;
	@Embedded
	private BankAccountDetailsBean bankAccountDetails;
	@Embedded
	private AreaOfVendingBean areaOfVending;
	@Embedded
	private PermanentAddressBean lenderPermanentAddress;
	@Embedded
	private CurrentAddressBean lenderCurrentAddress;
	@Embedded
	private LenderBean lenderBean;
	
	public LenderBean getLenderBean() {
		return lenderBean;
	}
	public void setLenderBean(LenderBean lenderBean) {
		this.lenderBean = lenderBean;
	}
	public PersonalDetailsBean getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDetailsBean personalDetails) {
		this.personalDetails = personalDetails;
	}
	
	public ApplicationDetailsBean(PersonalDetailsBean personalDetails) {
		super();
		this.personalDetails = personalDetails;
	}
	public BankAccountDetailsBean getBankAccountDetails() {
		return bankAccountDetails;
	}
	public AreaOfVendingBean getAreaOfVending() {
		return areaOfVending;
	}
	public PermanentAddressBean getLenderPermanentAddress() {
		return lenderPermanentAddress;
	}
	public CurrentAddressBean getLenderCurrentAddress() {
		return lenderCurrentAddress;
	}
	public void setBankAccountDetails(BankAccountDetailsBean bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}
	public void setAreaOfVending(AreaOfVendingBean areaOfVending) {
		this.areaOfVending = areaOfVending;
	}
	public void setLenderPermanentAddress(PermanentAddressBean lenderPermanentAddress) {
		this.lenderPermanentAddress = lenderPermanentAddress;
	}
	public void setLenderCurrentAddress(CurrentAddressBean lenderCurrentAddress) {
		this.lenderCurrentAddress = lenderCurrentAddress;
	}
	public ApplicationDetailsBean() {
		super();
	}
	public void setApplicationDetails(String string, Map<String, String> list) {
		// TODO Auto-generated method stub
		
	}
	
}
