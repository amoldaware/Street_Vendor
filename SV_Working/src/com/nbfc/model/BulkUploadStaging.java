package com.nbfc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sv_upload_stg")
public class BulkUploadStaging implements Serializable {
	public static final long serialVersionUID = 1L;
	
	@Column(name="UPLOAD_ID")
	private BigDecimal uploadId;
	
	@Column(name="CIG_MEMBER")
	private String cigMember;
	
	@Column(name="CIG_MEMBER")
	private String cigName;
	
	@Column(name="CIG_MEMBER")
	private String cigCode;
	
	@Column(name="CIG_MEMBER")
	private String jlgMember;
	
	@Column(name="CIG_MEMBER")
	private String jlgName;
	
	@Column(name="CIG_MEMBER")
	private String jlgCode;
	
	@Column(name="STREET_VENDOR_NAME")
	private String streetVendorName;
	
	@Column(name="FATHER_SPOUSE_NAME")
	private String fatherSpouseName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DOB")
	private Date DOB;
	
	@Column(name="Gender")
	private String Gender;
	
	@Column(name="Marital_Status")
	private String   maritalStatus;
	
	@Column(name="Mobile_no")
	private BigDecimal   mobileNo;
	
	@Column(name="Social_Category")
	private String   socialCategory;
	
	@Column(name="Nativity")
	private String   nativity;
	
	@Column(name="PWD")
	private String  PWD;
	
	@Column(name="Minority_Community")
	private String  minorityCommunity;
	
	@Column(name="KYC_Documents")
	private String kycDocuments;
	
	@Column(name="Others_KYC_Document")
	private String   othersKYCDocument;
	
	@Column(name="KYC_Documents_No")
	private String   kycDocumentsNo;
	
	@Column(name="Vending_Proof_Doc")
	private String   vendingProofDoc;
	
	@Column(name="Vending_Proof_Doc_No")
	private String   vendingProofDocNo;
	
	@Column(name="Permanent_Address")
	private String   permanentAddress;
	
	@Column(name="District")
	private String   district;
	
	@Column(name="State")
	private String   state;
	
	@Column(name="Pin")
	private Integer   pinCode;
	
	@Column(name="Present_Address")
	private String   presentAddress;
	
	@Column(name="Present_District")
	private String   presentDistrict;
	
	@Column(name="Present_State")
	private String   presentState;
	
	@Column(name="Present_Pin")
	private String   presentPinCode;
	
	@Column(name="Activity_Name")
	private String   activityName;
	
	@Column(name="Others_Activity")
	private String   othersActivity;
	
	@Column(name="Vending_Place_Name")
	private String   vendingPlaceName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Vending_Duration")
	private Date vendingDuration;
	
	@Column(name="Vending_Area")
	private String   vendingArea;
	
	@Column(name="Monthly_Sales")
	private Integer   monthlysSales ;
	
	@Column(name="Disbursement_Amount")
	private Integer   disbursementAmount ;
	
	@Column(name="Loan_Sanction_Amount")
	private Integer   loanSanctionAmount ;
	
	@Column(name="Guarantee_Amount")
	private Integer   guaranteeAmount ;
	
	@Column(name="Loan_Tenure")
	private Integer   loanTenure ;
	
	@Column(name="Loan_Account_No")
	private String   loanAccountNo;
	
	@Column(name="Moratorium")
	private int   Moratorium ;
	
	@Column(name="Total_Instalment")
	private int  totalInstallment ;
	
	@Column(name="PROCESS_FLAG")
	private String   processFlag;
	
	@Column(name="PROCESS_DESC")
	private String   processDesc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Create_Date")
	private String   createDate;
	
	@Column(name="CREATE_BY")
	private String   createBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Modify_Date")
	private String   modifyDate;
	
	@Column(name="MODIFY_BY")
	private String   modifyBy;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="RECORD_ID")
	private int recordId;

	public BigDecimal getUploadId() {
		return uploadId;
	}

	public void setUploadId(BigDecimal uploadId) {
		this.uploadId = uploadId;
	}

	public String getCigMember() {
		return cigMember;
	}

	public void setCigMember(String cigMember) {
		this.cigMember = cigMember;
	}

	public String getCigName() {
		return cigName;
	}

	public void setCigName(String cigName) {
		this.cigName = cigName;
	}

	public String getCigCode() {
		return cigCode;
	}

	public void setCigCode(String cigCode) {
		this.cigCode = cigCode;
	}

	public String getJlgMember() {
		return jlgMember;
	}

	public void setJlgMember(String jlgMember) {
		this.jlgMember = jlgMember;
	}

	public String getJlgName() {
		return jlgName;
	}

	public void setJlgName(String jlgName) {
		this.jlgName = jlgName;
	}

	public String getJlgCode() {
		return jlgCode;
	}

	public void setJlgCode(String jlgCode) {
		this.jlgCode = jlgCode;
	}

	public String getStreetVendorName() {
		return streetVendorName;
	}

	public void setStreetVendorName(String streetVendorName) {
		this.streetVendorName = streetVendorName;
	}

	public String getFatherSpouseName() {
		return fatherSpouseName;
	}

	public void setFatherSpouseName(String fatherSpouseName) {
		this.fatherSpouseName = fatherSpouseName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public BigDecimal getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(BigDecimal mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(String socialCategory) {
		this.socialCategory = socialCategory;
	}

	public String getNativity() {
		return nativity;
	}

	public void setNativity(String nativity) {
		this.nativity = nativity;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pWD) {
		PWD = pWD;
	}

	public String getMinorityCommunity() {
		return minorityCommunity;
	}

	public void setMinorityCommunity(String minorityCommunity) {
		this.minorityCommunity = minorityCommunity;
	}

	public String getKycDocuments() {
		return kycDocuments;
	}

	public void setKycDocuments(String kycDocuments) {
		this.kycDocuments = kycDocuments;
	}

	public String getOthersKYCDocument() {
		return othersKYCDocument;
	}

	public void setOthersKYCDocument(String othersKYCDocument) {
		this.othersKYCDocument = othersKYCDocument;
	}

	public String getKycDocumentsNo() {
		return kycDocumentsNo;
	}

	public void setKycDocumentsNo(String kycDocumentsNo) {
		this.kycDocumentsNo = kycDocumentsNo;
	}

	public String getVendingProofDoc() {
		return vendingProofDoc;
	}

	public void setVendingProofDoc(String vendingProofDoc) {
		this.vendingProofDoc = vendingProofDoc;
	}

	public String getVendingProofDocNo() {
		return vendingProofDocNo;
	}

	public void setVendingProofDocNo(String vendingProofDocNo) {
		this.vendingProofDocNo = vendingProofDocNo;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPresentDistrict() {
		return presentDistrict;
	}

	public void setPresentDistrict(String presentDistrict) {
		this.presentDistrict = presentDistrict;
	}

	public String getPresentState() {
		return presentState;
	}

	public void setPresentState(String presentState) {
		this.presentState = presentState;
	}

	public String getPresentPinCode() {
		return presentPinCode;
	}

	public void setPresentPinCode(String presentPinCode) {
		this.presentPinCode = presentPinCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getOthersActivity() {
		return othersActivity;
	}

	public void setOthersActivity(String othersActivity) {
		this.othersActivity = othersActivity;
	}

	public String getVendingPlaceName() {
		return vendingPlaceName;
	}

	public void setVendingPlaceName(String vendingPlaceName) {
		this.vendingPlaceName = vendingPlaceName;
	}

	public Date getVendingDuration() {
		return vendingDuration;
	}

	public void setVendingDuration(Date vendingDuration) {
		this.vendingDuration = vendingDuration;
	}

	public String getVendingArea() {
		return vendingArea;
	}

	public void setVendingArea(String vendingArea) {
		this.vendingArea = vendingArea;
	}

	public Integer getMonthlysSales() {
		return monthlysSales;
	}

	public void setMonthlysSales(Integer monthlysSales) {
		this.monthlysSales = monthlysSales;
	}

	public Integer getDisbursementAmount() {
		return disbursementAmount;
	}

	public void setDisbursementAmount(Integer disbursementAmount) {
		this.disbursementAmount = disbursementAmount;
	}

	public Integer getLoanSanctionAmount() {
		return loanSanctionAmount;
	}

	public void setLoanSanctionAmount(Integer loanSanctionAmount) {
		this.loanSanctionAmount = loanSanctionAmount;
	}

	public Integer getGuaranteeAmount() {
		return guaranteeAmount;
	}

	public void setGuaranteeAmount(Integer guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public int getMoratorium() {
		return Moratorium;
	}

	public void setMoratorium(int moratorium) {
		Moratorium = moratorium;
	}

	public int getTotalInstallment() {
		return totalInstallment;
	}

	public void setTotalInstallment(int totalInstallment) {
		this.totalInstallment = totalInstallment;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	
	
}
