package com.nbfc.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ClaimLodgementBean implements Serializable {
	private String nbfcMakerClaimLodgRemarks;
	private String RemarkID;
	private String nbfcClaimLodgFirstDate;
	private String nbfcClaimLodgDate;
	private String nbfcMakerId;
	private String intrestRate;
	private String activityType;
	private String mliID;
	private String userName;
	private String loanAccNo;
	private String appRemarks;
	private String returnReasons;
	private String returnReasonsRemarks;
	private String total_AmountStr;
	private String withinNPA;
	//add by azad
	private String mscItPan;
	private String chipProItPan;
	//end
	public String getWithinNPA() {
		return withinNPA;
	}

	public String getMscItPan() {
		return mscItPan;
	}

	public void setMscItPan(String mscItPan) {
		this.mscItPan = mscItPan;
	}

	public String getChipProItPan() {
		return chipProItPan;
	}

	public void setChipProItPan(String chipProItPan) {
		this.chipProItPan = chipProItPan;
	}

	public void setWithinNPA(String withinNPA) {
		this.withinNPA = withinNPA;
	}

	public String getTotal_AmountStr() {
		return total_AmountStr;
	}

	public void setTotal_AmountStr(String total_AmountStr) {
		this.total_AmountStr = total_AmountStr;
	}

	public String getReturnReasonsRemarks() {
		return returnReasonsRemarks;
	}

	public void setReturnReasonsRemarks(String returnReasonsRemarks) {
		this.returnReasonsRemarks = returnReasonsRemarks;
	}

	private Map<String,String> mapObj;
	
	//DIksha
	
			private String fromDate;
			private String toDate;
			private String bankName;
			private String borrowerName;
			private String loanAccountNo;
			private String expiryDate;
			private Date cgtCheckerDate;
			private Date claimDt;
			private Date nbfcCheckerDt;
			private Double npaEligibleAmt;
			private String mliLongName;
			//end
	

	public Map<String, String> getMapObj() {
		return mapObj;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCgtCheckerDate() {
		return cgtCheckerDate;
	}

	public void setCgtCheckerDate(Date cgtCheckerDate) {
		this.cgtCheckerDate = cgtCheckerDate;
	}

	public Date getClaimDt() {
		return claimDt;
	}

	public void setClaimDt(Date claimDt) {
		this.claimDt = claimDt;
	}

	public Date getNbfcCheckerDt() {
		return nbfcCheckerDt;
	}

	public void setNbfcCheckerDt(Date nbfcCheckerDt) {
		this.nbfcCheckerDt = nbfcCheckerDt;
	}

	public Double getNpaEligibleAmt() {
		return npaEligibleAmt;
	}

	public void setNpaEligibleAmt(Double npaEligibleAmt) {
		this.npaEligibleAmt = npaEligibleAmt;
	}

	public String getMliLongName() {
		return mliLongName;
	}

	public void setMliLongName(String mliLongName) {
		this.mliLongName = mliLongName;
	}

	public void setMapObj(Map<String, String> mapObj) {
		this.mapObj = mapObj;
	}

	public String getReturnReasons() {
		return returnReasons;
	}

	public void setReturnReasons(String returnReasons) {
		this.returnReasons = returnReasons;
	}

	public String getAppRemarks() {
		return appRemarks;
	}

	public void setAppRemarks(String appRemarks) {
		this.appRemarks = appRemarks;
	}

	public String getLoanAccNo() {
		return loanAccNo;
	}

	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}

	private List<String> chcktbl;
	private String final_status;
	private boolean selectAll;
	public String getFinal_status() {
		return final_status;
	}

	public void setFinal_status(String final_status) {
		this.final_status = final_status;
	}

	

	public String getRemarkID() {
		return RemarkID;
	}

	public void setRemarkID(String remarkID) {
		RemarkID = remarkID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private Double latestOsAmt;
	private Integer claimCnt;
	public String getMliID() {
		return mliID;
	}

	public void setMliID(String mliID) {
		this.mliID = mliID;
	}

	private Integer Cnt;
	private Integer dupCnt;
	private Integer UPTO_10L_CNT;
	private Integer BETWEEN_10L_25L_CNT;
	private Integer UPTO_25L_CNT;
	private Double total_Amount;
	private Double latestOsAmtNPA;
	
	private String firstInstallClaimStr;
	private String claimAmountStr;
	public String getClaimAmountStr() {
		return claimAmountStr;
	}

	public void setClaimAmountStr(String claimAmountStr) {
		this.claimAmountStr = claimAmountStr;
	}

	private String financialPositionComments;
	private String financialAssistanceDtls;
	private String creditSupport;
	private String bankFacilityDtls;
	private String remarks = "";
	
	private String intrestRateStatus;
	private String activitytypeStatus;
	private String wilfuldefaulterStatus;
	private String accFraudStatus;
	private String forumStatus;
	private String  suitNoStatus;
	private String  legalFileDateStatus;
	private String  legalFileDate;
	
	
	
	
	public String getLegalFileDate() {
		return legalFileDate;
	}

	public void setLegalFileDate(String legalFileDate) {
		this.legalFileDate = legalFileDate;
	}

	public String getLegalFileDateStatus() {
		return legalFileDateStatus;
	}

	public void setLegalFileDateStatus(String legalFileDateStatus) {
		this.legalFileDateStatus = legalFileDateStatus;
	}

	public String getIntrestRateStatus() {
		return intrestRateStatus;
	}

	public void setIntrestRateStatus(String intrestRateStatus) {
		this.intrestRateStatus = intrestRateStatus;
	}

	public String getActivitytypeStatus() {
		return activitytypeStatus;
	}

	public void setActivitytypeStatus(String activitytypeStatus) {
		this.activitytypeStatus = activitytypeStatus;
	}

	public String getWilfuldefaulterStatus() {
		return wilfuldefaulterStatus;
	}

	public void setWilfuldefaulterStatus(String wilfuldefaulterStatus) {
		this.wilfuldefaulterStatus = wilfuldefaulterStatus;
	}

	public String getAccFraudStatus() {
		return accFraudStatus;
	}

	public void setAccFraudStatus(String accFraudStatus) {
		this.accFraudStatus = accFraudStatus;
	}

	public String getForumStatus() {
		return forumStatus;
	}

	public void setForumStatus(String forumStatus) {
		this.forumStatus = forumStatus;
	}

	public String getSuitNoStatus() {
		return suitNoStatus;
	}

	public void setSuitNoStatus(String suitNoStatus) {
		this.suitNoStatus = suitNoStatus;
	}

	public Double getLatestOsAmtNPA() {
		return latestOsAmtNPA;
	}

	public String getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(String intrestRate) {
		this.intrestRate = intrestRate;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public void setLatestOsAmtNPA(Double latestOsAmtNPA) {
		this.latestOsAmtNPA = latestOsAmtNPA;
	}

	private String claimStatus;
	private String chkListStatus;
	private String internalRating;
	private String totalSecuritydetails2Error;
	private String claimCheckListFlag;
	private List claim;
	private String mliName;
	private String Msg;
	private String RMsg;
	private String dateOfClaimLoadge;
	private String claimRefNo;
	private String memberId;
	private String cgpan;
	private String lendingNbfcName;
	private String city;
	private String address;
	private String district;
	private String state;
	private String email;
	private String telephoneNo;
	private String gstinNo;
	private String dealingOfficerName;
	private String nameOfBorrower;
	private String adressOfBorrower;
	private String cityOfBorrower;
	private String districtOfBorrower;
	private String stateOfBorrower;
	private String pincodeOfBorrower;
	private Double latestOsGuranteeAmt;// 1
	private String latestOsGuranteeAmtVStr;
	private BigDecimal latestOsGuranteeAmtBC;
	private String dateOfNPA;
	private String dateOfClaim;
	public String getDateOfClaim() {
		return dateOfClaim;
	}

	public void setDateOfClaim(String dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}

	private String wilfulDefaulter;
	private String fraudAcc;
	private String enquiryConcluded;
	private String mliReported;
	private String reasonOfNPA;
	private String dateOfNotice;
	private String forum;
	private String suitNo;
	private String resonFillingSuit;// RESON_FILLING_SUIT
	private String dateOfSarfaesi;
	private String locationOfForum;
	private Double claimAmt;
	private String claimAmtStr;
	private MultipartFile file;
	private String anySubsidyInvolved;
	private String subsidyReceived;
	private String subsidyAdjust;
	private String dateOfSubsidy;
	private String receiveASF;
	
	public String getReceiveASF() {
		return receiveASF;
	}

	public void setReceiveASF(String receiveASF) {
		this.receiveASF = receiveASF;
	}

	private String gstNo;
	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	private Double sanctionAmt;

	public String getRMsg() {
		return RMsg;
	}

	public void setRMsg(String rMsg) {
		RMsg = rMsg;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getNbfcMakerClaimLodgRemarks() {
		return nbfcMakerClaimLodgRemarks;
	}

	public void setNbfcMakerClaimLodgRemarks(String nbfcMakerClaimLodgRemarks) {
		this.nbfcMakerClaimLodgRemarks = nbfcMakerClaimLodgRemarks;
	}

	public String getNbfcClaimLodgFirstDate() {
		return nbfcClaimLodgFirstDate;
	}

	public void setNbfcClaimLodgFirstDate(String nbfcClaimLodgFirstDate) {
		this.nbfcClaimLodgFirstDate = nbfcClaimLodgFirstDate;
	}

	public String getNbfcClaimLodgDate() {
		return nbfcClaimLodgDate;
	}

	public void setNbfcClaimLodgDate(String nbfcClaimLodgDate) {
		this.nbfcClaimLodgDate = nbfcClaimLodgDate;
	}

	public String getNbfcMakerId() {
		return nbfcMakerId;
	}

	public void setNbfcMakerId(String nbfcMakerId) {
		this.nbfcMakerId = nbfcMakerId;
	}

	public String getClaimCheckListFlag() {
		return claimCheckListFlag;
	}

	public void setClaimCheckListFlag(String claimCheckListFlag) {
		this.claimCheckListFlag = claimCheckListFlag;
	}

	public String getTotalSecuritydetails2Error() {
		return totalSecuritydetails2Error;
	}

	public void setTotalSecuritydetails2Error(String totalSecuritydetails2Error) {
		this.totalSecuritydetails2Error = totalSecuritydetails2Error;
	}

	private String dealingOfficerNO;

	public String getDealingOfficerNO() {
		return dealingOfficerNO;
	}

	public void setDealingOfficerNO(String dealingOfficerNO) {
		this.dealingOfficerNO = dealingOfficerNO;
	}

	public Double getTotal_Amount() {
		return total_Amount;
	}

	public void setTotal_Amount(Double total_Amount) {
		this.total_Amount = total_Amount;
	}

	public Integer getUPTO_10L_CNT() {
		return UPTO_10L_CNT;
	}

	public void setUPTO_10L_CNT(Integer uPTO_10L_CNT) {
		UPTO_10L_CNT = uPTO_10L_CNT;
	}

	public Integer getBETWEEN_10L_25L_CNT() {
		return BETWEEN_10L_25L_CNT;
	}

	public void setBETWEEN_10L_25L_CNT(Integer bETWEEN_10L_25L_CNT) {
		BETWEEN_10L_25L_CNT = bETWEEN_10L_25L_CNT;
	}

	public Integer getUPTO_25L_CNT() {
		return UPTO_25L_CNT;
	}

	public void setUPTO_25L_CNT(Integer uPTO_25L_CNT) {
		UPTO_25L_CNT = uPTO_25L_CNT;
	}

	public Integer getSUM_TOTAL() {
		return SUM_TOTAL;
	}

	public void setSUM_TOTAL(Integer sUM_TOTAL) {
		SUM_TOTAL = sUM_TOTAL;
	}

	private Integer SUM_TOTAL;

	public Integer getDupCnt() {
		return dupCnt;
	}

	public void setDupCnt(Integer dupCnt) {
		this.dupCnt = dupCnt;
	}

	public Integer getCnt() {
		return Cnt;
	}

	public void setCnt(Integer cnt) {
		Cnt = cnt;
	}

	private String ClaimStatusRemarkHidden;

	public String getClaimStatusRemarkHidden() {
		return ClaimStatusRemarkHidden;
	}

	public void setClaimStatusRemarkHidden(String claimStatusRemarkHidden) {
		ClaimStatusRemarkHidden = claimStatusRemarkHidden;
	}

	public String getChkListStatus() {
		return chkListStatus;
	}

	public void setChkListStatus(String chkListStatus) {
		this.chkListStatus = chkListStatus;
	}

	public Double getSanctionAmt() {
		return sanctionAmt;
	}

	public void setSanctionAmt(Double sanctionAmt) {
		this.sanctionAmt = sanctionAmt;
	}

	private Double subsidyAmt;
	private String subsidyAmtStr;

	private Double sNoTL;
	private String cgPanTL;

	private String latestOsAmtVStr;
	private BigDecimal latestOsAmtBC;

	private Double totalOsAmt;
	private String totalOsAmtStr;

	private Double recovery;
	private String modeRecovery;
	private boolean chcktbl1;
	

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	

	public List<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}

	public boolean isChcktbl1() {
		return chcktbl1;
	}

	public void setChcktbl1(boolean chcktbl1) {
		this.chcktbl1 = chcktbl1;
	}

	private Double OsAmtClaim;

	public Double getGuarantee_Amt() {
		return guarantee_Amt;
	}

	public void setGuarantee_Amt(Double guarantee_Amt) {
		this.guarantee_Amt = guarantee_Amt;
	}

	private String osAmtClaimStr;

	private String npaRecoveryFlag;
	private String confirmRecoveryFlag;

	private Double landValue;
	private String landValueStr;
	private BigDecimal landValueBD;

	private Double buildingValue;
	private String buildingValueStr;

	private Double planetValue;
	private String planetValueStr;

	private Double otherAssetValue;
	private String otherAssetValueStr;

	private Double currentAssetValue;
	private String currentAssetValueStr;

	private Double othersValue;
	private String othersValueStr;

	private Double totalSecuritydetails;
	private String totalSecuritydetailsStr;

	private String totalSecuritydetailsError;

	private Double networthOfPromotor;
	private String networthOfPromotorStr;

	private String reductionReason;

	private Double landValue1;
	private String landValue1Str;

	private Double buildingValue1;
	private String buildingValue1Str;

	private Double planetValue1;
	private String planetValue1Str;

	private Double otherAssetValue1;
	private String otherAssetValue1Str;

	private Double currentAssetValue1;
	private String currentAssetValue1Str;

	private Double othersValue1;
	private String othersValue1Str;

	private Double networthOfPromotor1;
	private String networthOfPromotor1Str;

	private String reductionReason1;

	private Double totalSecuritydetails1;
	private String totalSecuritydetails1Str;

	private String totalSecuritydetails1Error;

	private Double landValue2;
	private String landValue2Str;

	private Double buildingValue2;
	private String buildingValue2Str;

	private Double planetValue2;
	private String planetValue2Str;

	private Double otherAssetValue2;
	private String otherAssetValue2Str;

	private Double currentAssetValue2;
	private String currentAssetValue2Str;

	private Double othersValue2;
	private String othersValue2Str;

	private Double guarantee_Amt;
	private String guarantee_Amt_str;
	public String getGuarantee_Amt_str() {
		return guarantee_Amt_str;
	}

	public void setGuarantee_Amt_str(String guarantee_Amt_str) {
		this.guarantee_Amt_str = guarantee_Amt_str;
	}

	private String remarks1;
	private String returnDate;

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}

	public Integer getClaimCnt() {
		return claimCnt;
	}

	public void setClaimCnt(Integer claimCnt) {
		this.claimCnt = claimCnt;
	}

	public String getSubsidyAmtStr() {
		return subsidyAmtStr;
	}

	public void setSubsidyAmtStr(String subsidyAmtStr) {
		this.subsidyAmtStr = subsidyAmtStr;
	}

	private Double networthOfPromotor2;

	public List getClaim() {
		return claim;
	}

	public void setClaim(List claim) {
		this.claim = claim;
	}

	public String getResonFillingSuit() {
		return resonFillingSuit;
	}

	public void setResonFillingSuit(String resonFillingSuit) {
		this.resonFillingSuit = resonFillingSuit;
	}

	public String getClaimAmtStr() {
		return claimAmtStr;
	}

	public void setClaimAmtStr(String claimAmtStr) {
		this.claimAmtStr = claimAmtStr;
	}

	public Double getOsAmtClaim() {
		return OsAmtClaim;
	}

	public void setOsAmtClaim(Double osAmtClaim) {
		OsAmtClaim = osAmtClaim;
	}

	public String getOsAmtClaimStr() {
		return osAmtClaimStr;
	}

	public void setOsAmtClaimStr(String osAmtClaimStr) {
		this.osAmtClaimStr = osAmtClaimStr;
	}

	public Double getLandValue2() {
		return landValue2;
	}

	public void setLandValue2(Double landValue2) {
		this.landValue2 = landValue2;
	}

	public String getLandValue2Str() {
		return landValue2Str;
	}

	public void setLandValue2Str(String landValue2Str) {
		this.landValue2Str = landValue2Str;
	}

	public Double getBuildingValue2() {
		return buildingValue2;
	}

	public void setBuildingValue2(Double buildingValue2) {
		this.buildingValue2 = buildingValue2;
	}

	public String getBuildingValue2Str() {
		return buildingValue2Str;
	}

	public void setBuildingValue2Str(String buildingValue2Str) {
		this.buildingValue2Str = buildingValue2Str;
	}

	public Double getPlanetValue2() {
		return planetValue2;
	}

	public void setPlanetValue2(Double planetValue2) {
		this.planetValue2 = planetValue2;
	}

	public String getPlanetValue2Str() {
		return planetValue2Str;
	}

	public void setPlanetValue2Str(String planetValue2Str) {
		this.planetValue2Str = planetValue2Str;
	}

	public Double getOtherAssetValue2() {
		return otherAssetValue2;
	}

	public void setOtherAssetValue2(Double otherAssetValue2) {
		this.otherAssetValue2 = otherAssetValue2;
	}

	public String getOtherAssetValue2Str() {
		return otherAssetValue2Str;
	}

	public void setOtherAssetValue2Str(String otherAssetValue2Str) {
		this.otherAssetValue2Str = otherAssetValue2Str;
	}

	public Double getCurrentAssetValue2() {
		return currentAssetValue2;
	}

	public void setCurrentAssetValue2(Double currentAssetValue2) {
		this.currentAssetValue2 = currentAssetValue2;
	}

	public String getCurrentAssetValue2Str() {
		return currentAssetValue2Str;
	}

	public void setCurrentAssetValue2Str(String currentAssetValue2Str) {
		this.currentAssetValue2Str = currentAssetValue2Str;
	}

	public Double getOthersValue2() {
		return othersValue2;
	}

	public void setOthersValue2(Double othersValue2) {
		this.othersValue2 = othersValue2;
	}

	public String getOthersValue2Str() {
		return othersValue2Str;
	}

	public void setOthersValue2Str(String othersValue2Str) {
		this.othersValue2Str = othersValue2Str;
	}

	public Double getNetworthOfPromotor2() {
		return networthOfPromotor2;
	}

	public void setNetworthOfPromotor2(Double networthOfPromotor2) {
		this.networthOfPromotor2 = networthOfPromotor2;
	}

	public String getNetworthOfPromotor2Str() {
		return networthOfPromotor2Str;
	}

	public void setNetworthOfPromotor2Str(String networthOfPromotor2Str) {
		this.networthOfPromotor2Str = networthOfPromotor2Str;
	}

	public String getReductionReason2() {
		return reductionReason2;
	}

	public void setReductionReason2(String reductionReason2) {
		this.reductionReason2 = reductionReason2;
	}

	public Double getTotalSecuritydetails2() {
		return totalSecuritydetails2;
	}

	public void setTotalSecuritydetails2(Double totalSecuritydetails2) {
		this.totalSecuritydetails2 = totalSecuritydetails2;
	}

	public String getTotalSecuritydetails2Str() {
		return totalSecuritydetails2Str;
	}

	public void setTotalSecuritydetails2Str(String totalSecuritydetails2Str) {
		this.totalSecuritydetails2Str = totalSecuritydetails2Str;
	}

	public String getTotalSecuritydetails1Error2() {
		return totalSecuritydetails1Error2;
	}

	public void setTotalSecuritydetails1Error2(
			String totalSecuritydetails1Error2) {
		this.totalSecuritydetails1Error2 = totalSecuritydetails1Error2;
	}

	public String getSuitDate() {
		return suitDate;
	}

	public void setSuitDate(String suitDate) {
		this.suitDate = suitDate;
	}

	public String getMakerUploadDate() {
		return makerUploadDate;
	}

	public void setMakerUploadDate(String makerUploadDate) {
		this.makerUploadDate = makerUploadDate;
	}

	public String getClaimLodgementCertificate() {
		return claimLodgementCertificate;
	}

	public void setClaimLodgementCertificate(String claimLodgementCertificate) {
		this.claimLodgementCertificate = claimLodgementCertificate;
	}

	public String getClaimLodgementCheckList() {
		return claimLodgementCheckList;
	}

	public void setClaimLodgementCheckList(String claimLodgementCheckList) {
		this.claimLodgementCheckList = claimLodgementCheckList;
	}

	public String getActivityEligible() {
		return activityEligible;
	}

	public void setActivityEligible(String activityEligible) {
		this.activityEligible = activityEligible;
	}

	public String getActivityEligibleRemarks() {
		return activityEligibleRemarks;
	}

	public void setActivityEligibleRemarks(String activityEligibleRemarks) {
		this.activityEligibleRemarks = activityEligibleRemarks;
	}

	public String getCibil() {
		return cibil;
	}

	public void setCibil(String cibil) {
		this.cibil = cibil;
	}

	public String getCibilRemarks() {
		return cibilRemarks;
	}

	public void setCibilRemarks(String cibilRemarks) {
		this.cibilRemarks = cibilRemarks;
	}

	public String getRateCharge() {
		return rateCharge;
	}

	public void setRateCharge(String rateCharge) {
		this.rateCharge = rateCharge;
	}

	public String getRateChargeRemarks() {
		return rateChargeRemarks;
	}

	public void setRateChargeRemarks(String rateChargeRemarks) {
		this.rateChargeRemarks = rateChargeRemarks;
	}

	public String getDateOfNPAAsRBI() {
		return dateOfNPAAsRBI;
	}

	public void setDateOfNPAAsRBI(String dateOfNPAAsRBI) {
		this.dateOfNPAAsRBI = dateOfNPAAsRBI;
	}

	public String getDateOfNPAAsRBIRemarks() {
		return dateOfNPAAsRBIRemarks;
	}

	public void setDateOfNPAAsRBIRemarks(String dateOfNPAAsRBIRemarks) {
		this.dateOfNPAAsRBIRemarks = dateOfNPAAsRBIRemarks;
	}

	public String getWhetherOutstanding() {
		return whetherOutstanding;
	}

	public void setWhetherOutstanding(String whetherOutstanding) {
		this.whetherOutstanding = whetherOutstanding;
	}

	public String getWhetherOutstandingRemarks() {
		return whetherOutstandingRemarks;
	}

	public void setWhetherOutstandingRemarks(String whetherOutstandingRemarks) {
		this.whetherOutstandingRemarks = whetherOutstandingRemarks;
	}

	public String getApprisalDisbursement() {
		return apprisalDisbursement;
	}

	public void setApprisalDisbursement(String apprisalDisbursement) {
		this.apprisalDisbursement = apprisalDisbursement;
	}

	public String getApprisalDisbursementRemarks() {
		return apprisalDisbursementRemarks;
	}

	public void setApprisalDisbursementRemarks(
			String apprisalDisbursementRemarks) {
		this.apprisalDisbursementRemarks = apprisalDisbursementRemarks;
	}

	public String getPostDisbursement() {
		return postDisbursement;
	}

	public void setPostDisbursement(String postDisbursement) {
		this.postDisbursement = postDisbursement;
	}

	public String getPostDisbursementRemarks() {
		return postDisbursementRemarks;
	}

	public void setPostDisbursementRemarks(String postDisbursementRemarks) {
		this.postDisbursementRemarks = postDisbursementRemarks;
	}

	public String getExerciseCarried() {
		return exerciseCarried;
	}

	public void setExerciseCarried(String exerciseCarried) {
		this.exerciseCarried = exerciseCarried;
	}

	public String getExerciseCarriedRemarks() {
		return exerciseCarriedRemarks;
	}

	public void setExerciseCarriedRemarks(String exerciseCarriedRemarks) {
		this.exerciseCarriedRemarks = exerciseCarriedRemarks;
	}

	public String getInternalRating() {
		return internalRating;
	}

	public void setInternalRating(String internalRating) {
		this.internalRating = internalRating;
	}

	public String getInternalRatingRemarks() {
		return internalRatingRemarks;
	}

	public void setInternalRatingRemarks(String internalRatingRemarks) {
		this.internalRatingRemarks = internalRatingRemarks;
	}

	public String getRecoverPertaining() {
		return recoverPertaining;
	}

	public void setRecoverPertaining(String recoverPertaining) {
		this.recoverPertaining = recoverPertaining;
	}

	public String getRecoverPertainingRemarks() {
		return recoverPertainingRemarks;
	}

	public void setRecoverPertainingRemarks(String recoverPertainingRemarks) {
		this.recoverPertainingRemarks = recoverPertainingRemarks;
	}

	private String networthOfPromotor2Str;

	private String reductionReason2;

	private Double totalSecuritydetails2;
	private Double osAmtAsonNPA;

	public Double getOsAmtAsonNPA() {
		return osAmtAsonNPA;
	}

	public void setOsAmtAsonNPA(Double osAmtAsonNPA) {
		this.osAmtAsonNPA = osAmtAsonNPA;
	}

	private String totalSecuritydetails2Str;
	private String totalSecuritydetails1Error2;
	private String suitDate;

	private Double sNoGCP;
	private String sNoGCPStr;

	private String cgPanGCP;

	private Double loanLimitCovered;
	private String loanLimitCoveredStr;

	private Double guaranteeCov;
	private String guaranteeCovStr;

	private Double osAmtClaimGCP;
	private String osAmtClaimGCPStr;

	private Double eligableAmtClaim;
	private String eligableAmtClaimStr;

	private Double firstInstallClaim;

	private String makerUploadDate;
	private String claimLodgementCertificate;
	private String claimLodgementCheckList;

	private String activityEligible;
	private String activityEligibleStatus;
	private String activityEligibleRemarks;

	private String cibil;
	private String cibilStatus;
	private String cibilRemarks;

	private String rateCharge;
	private String rateChargeStatus;
	private String rateChargeRemarks;

	private String dateOfNPAAsRBIStatus;
	private String dateOfNPAAsRBI;
	private String dateOfNPAAsRBIRemarks;

	private String whetherOutstandingStatus;
	private String whetherOutstanding;
	private String whetherOutstandingRemarks;

	private String apprisalDisbursement;
	private String apprisalDisbursementStatus;
	private String apprisalDisbursementRemarks;

	private String postDisbursement;
	private String postDisbursementStatus;
	private String postDisbursementRemarks;

	private String exerciseCarried;
	private String exerciseCarriedStatus;
	private String exerciseCarriedRemarks;

	private String internalRating1;

	public String getInternalRating1() {
		return internalRating1;
	}

	public void setInternalRating1(String internalRating1) {
		this.internalRating1 = internalRating1;
	}

	private String internalRatingStatus;
	private String internalRatingRemarks;

	private String recoverPertaining;
	private String recoverPertainingStatus;

	public String getActivityEligibleStatus() {
		return activityEligibleStatus;
	}

	public void setActivityEligibleStatus(String activityEligibleStatus) {
		this.activityEligibleStatus = activityEligibleStatus;
	}

	public String getCibilStatus() {
		return cibilStatus;
	}

	public void setCibilStatus(String cibilStatus) {
		this.cibilStatus = cibilStatus;
	}

	public String getRateChargeStatus() {
		return rateChargeStatus;
	}

	public void setRateChargeStatus(String rateChargeStatus) {
		this.rateChargeStatus = rateChargeStatus;
	}

	public String getDateOfNPAAsRBIStatus() {
		return dateOfNPAAsRBIStatus;
	}

	public void setDateOfNPAAsRBIStatus(String dateOfNPAAsRBIStatus) {
		this.dateOfNPAAsRBIStatus = dateOfNPAAsRBIStatus;
	}

	public String getWhetherOutstandingStatus() {
		return whetherOutstandingStatus;
	}

	public void setWhetherOutstandingStatus(String whetherOutstandingStatus) {
		this.whetherOutstandingStatus = whetherOutstandingStatus;
	}

	public String getApprisalDisbursementStatus() {
		return apprisalDisbursementStatus;
	}

	public void setApprisalDisbursementStatus(String apprisalDisbursementStatus) {
		this.apprisalDisbursementStatus = apprisalDisbursementStatus;
	}

	public String getPostDisbursementStatus() {
		return postDisbursementStatus;
	}

	public void setPostDisbursementStatus(String postDisbursementStatus) {
		this.postDisbursementStatus = postDisbursementStatus;
	}

	public String getExerciseCarriedStatus() {
		return exerciseCarriedStatus;
	}

	public void setExerciseCarriedStatus(String exerciseCarriedStatus) {
		this.exerciseCarriedStatus = exerciseCarriedStatus;
	}

	public String getInternalRatingStatus() {
		return internalRatingStatus;
	}

	public void setInternalRatingStatus(String internalRatingStatus) {
		this.internalRatingStatus = internalRatingStatus;
	}

	public String getRecoverPertainingStatus() {
		return recoverPertainingStatus;
	}

	public void setRecoverPertainingStatus(String recoverPertainingStatus) {
		this.recoverPertainingStatus = recoverPertainingStatus;
	}

	private String recoverPertainingRemarks;

	public String getTotalOsAmtStr() {
		return totalOsAmtStr;
	}

	public void setTotalOsAmtStr(String totalOsAmtStr) {
		this.totalOsAmtStr = totalOsAmtStr;
	}

	public String getPlanetValueStr() {
		return planetValueStr;
	}

	public void setPlanetValueStr(String planetValueStr) {
		this.planetValueStr = planetValueStr;
	}

	public String getOtherAssetValueStr() {
		return otherAssetValueStr;
	}

	public void setOtherAssetValueStr(String otherAssetValueStr) {
		this.otherAssetValueStr = otherAssetValueStr;
	}

	public String getCurrentAssetValueStr() {
		return currentAssetValueStr;
	}

	public void setCurrentAssetValueStr(String currentAssetValueStr) {
		this.currentAssetValueStr = currentAssetValueStr;
	}

	public String getOthersValueStr() {
		return othersValueStr;
	}

	public void setOthersValueStr(String othersValueStr) {
		this.othersValueStr = othersValueStr;
	}

	public String getTotalSecuritydetailsStr() {
		return totalSecuritydetailsStr;
	}

	public void setTotalSecuritydetailsStr(String totalSecuritydetailsStr) {
		this.totalSecuritydetailsStr = totalSecuritydetailsStr;
	}

	public String getNetworthOfPromotorStr() {
		return networthOfPromotorStr;
	}

	public void setNetworthOfPromotorStr(String networthOfPromotorStr) {
		this.networthOfPromotorStr = networthOfPromotorStr;
	}

	public String getLandValue1Str() {
		return landValue1Str;
	}

	public void setLandValue1Str(String landValue1Str) {
		this.landValue1Str = landValue1Str;
	}

	public String getBuildingValue1Str() {
		return buildingValue1Str;
	}

	public void setBuildingValue1Str(String buildingValue1Str) {
		this.buildingValue1Str = buildingValue1Str;
	}

	public String getPlanetValue1Str() {
		return planetValue1Str;
	}

	public void setPlanetValue1Str(String planetValue1Str) {
		this.planetValue1Str = planetValue1Str;
	}

	public String getOtherAssetValue1Str() {
		return otherAssetValue1Str;
	}

	public void setOtherAssetValue1Str(String otherAssetValue1Str) {
		this.otherAssetValue1Str = otherAssetValue1Str;
	}

	public String getCurrentAssetValue1Str() {
		return currentAssetValue1Str;
	}

	public void setCurrentAssetValue1Str(String currentAssetValue1Str) {
		this.currentAssetValue1Str = currentAssetValue1Str;
	}

	public String getOthersValue1Str() {
		return othersValue1Str;
	}

	public void setOthersValue1Str(String othersValue1Str) {
		this.othersValue1Str = othersValue1Str;
	}

	public String getNetworthOfPromotor1Str() {
		return networthOfPromotor1Str;
	}

	public void setNetworthOfPromotor1Str(String networthOfPromotor1Str) {
		this.networthOfPromotor1Str = networthOfPromotor1Str;
	}

	public String getTotalSecuritydetails1Str() {
		return totalSecuritydetails1Str;
	}

	public void setTotalSecuritydetails1Str(String totalSecuritydetails1Str) {
		this.totalSecuritydetails1Str = totalSecuritydetails1Str;
	}

	public String getsNoGCPStr() {
		return sNoGCPStr;
	}

	public void setsNoGCPStr(String sNoGCPStr) {
		this.sNoGCPStr = sNoGCPStr;
	}

	public String getLoanLimitCoveredStr() {
		return loanLimitCoveredStr;
	}

	public void setLoanLimitCoveredStr(String loanLimitCoveredStr) {
		this.loanLimitCoveredStr = loanLimitCoveredStr;
	}

	public String getGuaranteeCovStr() {
		return guaranteeCovStr;
	}

	public void setGuaranteeCovStr(String guaranteeCovStr) {
		this.guaranteeCovStr = guaranteeCovStr;
	}

	public String getOsAmtClaimGCPStr() {
		return osAmtClaimGCPStr;
	}

	public void setOsAmtClaimGCPStr(String osAmtClaimGCPStr) {
		this.osAmtClaimGCPStr = osAmtClaimGCPStr;
	}

	public String getEligableAmtClaimStr() {
		return eligableAmtClaimStr;
	}

	public void setEligableAmtClaimStr(String eligableAmtClaimStr) {
		this.eligableAmtClaimStr = eligableAmtClaimStr;
	}

	public String getFirstInstallClaimStr() {
		return firstInstallClaimStr;
	}

	public void setFirstInstallClaimStr(String firstInstallClaimStr) {
		this.firstInstallClaimStr = firstInstallClaimStr;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	private String unitName;
	private String AcceptReturn;

	public String getDateOfClaimLoadge() {
		return dateOfClaimLoadge;
	}

	public void setDateOfClaimLoadge(String dateOfClaimLoadge) {
		this.dateOfClaimLoadge = dateOfClaimLoadge;
	}

	public String getClaimRefNo() {
		return claimRefNo;
	}

	public void setClaimRefNo(String claimRefNo) {
		this.claimRefNo = claimRefNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getAcceptReturn() {
		return AcceptReturn;
	}

	public void setAcceptReturn(String acceptReturn) {
		AcceptReturn = acceptReturn;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCgpan() {
		return cgpan;
	}

	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}

	public String getLendingNbfcName() {
		return lendingNbfcName;
	}

	public void setLendingNbfcName(String lendingNbfcName) {
		this.lendingNbfcName = lendingNbfcName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	public String getDealingOfficerName() {
		return dealingOfficerName;
	}

	public void setDealingOfficerName(String dealingOfficerName) {
		this.dealingOfficerName = dealingOfficerName;
	}

	public String getNameOfBorrower() {
		return nameOfBorrower;
	}

	public void setNameOfBorrower(String nameOfBorrower) {
		this.nameOfBorrower = nameOfBorrower;
	}

	public String getAdressOfBorrower() {
		return adressOfBorrower;
	}

	public void setAdressOfBorrower(String adressOfBorrower) {
		this.adressOfBorrower = adressOfBorrower;
	}

	public String getCityOfBorrower() {
		return cityOfBorrower;
	}

	public void setCityOfBorrower(String cityOfBorrower) {
		this.cityOfBorrower = cityOfBorrower;
	}

	public String getDistrictOfBorrower() {
		return districtOfBorrower;
	}

	public void setDistrictOfBorrower(String districtOfBorrower) {
		this.districtOfBorrower = districtOfBorrower;
	}

	public String getStateOfBorrower() {
		return stateOfBorrower;
	}

	public void setStateOfBorrower(String stateOfBorrower) {
		this.stateOfBorrower = stateOfBorrower;
	}

	public String getPincodeOfBorrower() {
		return pincodeOfBorrower;
	}

	public void setPincodeOfBorrower(String pincodeOfBorrower) {
		this.pincodeOfBorrower = pincodeOfBorrower;
	}

	public Double getLatestOsGuranteeAmt() {
		return latestOsGuranteeAmt;
	}

	public void setLatestOsGuranteeAmt(Double latestOsGuranteeAmt) {
		this.latestOsGuranteeAmt = latestOsGuranteeAmt;
	}

	public String getDateOfNPA() {
		return dateOfNPA;
	}

	public void setDateOfNPA(String dateOfNPA) {
		this.dateOfNPA = dateOfNPA;
	}

	public String getWilfulDefaulter() {
		return wilfulDefaulter;
	}

	public void setWilfulDefaulter(String wilfulDefaulter) {
		this.wilfulDefaulter = wilfulDefaulter;
	}

	public String getFraudAcc() {
		return fraudAcc;
	}

	public void setFraudAcc(String fraudAcc) {
		this.fraudAcc = fraudAcc;
	}

	public String getEnquiryConcluded() {
		return enquiryConcluded;
	}

	public void setEnquiryConcluded(String enquiryConcluded) {
		this.enquiryConcluded = enquiryConcluded;
	}

	public String getMliReported() {
		return mliReported;
	}

	public void setMliReported(String mliReported) {
		this.mliReported = mliReported;
	}

	public String getReasonOfNPA() {
		return reasonOfNPA;
	}

	public void setReasonOfNPA(String reasonOfNPA) {
		this.reasonOfNPA = reasonOfNPA;
	}

	public String getDateOfNotice() {
		return dateOfNotice;
	}

	public void setDateOfNotice(String dateOfNotice) {
		this.dateOfNotice = dateOfNotice;
	}

	public String getForum() {
		return forum;
	}

	public void setForum(String forum) {
		this.forum = forum;
	}

	public String getSuitNo() {
		return suitNo;
	}

	public void setSuitNo(String suitNo) {
		this.suitNo = suitNo;
	}

	public String getDateOfSarfaesi() {
		return dateOfSarfaesi;
	}

	public void setDateOfSarfaesi(String dateOfSarfaesi) {
		this.dateOfSarfaesi = dateOfSarfaesi;
	}

	public String getLocationOfForum() {
		return locationOfForum;
	}

	public void setLocationOfForum(String locationOfForum) {
		this.locationOfForum = locationOfForum;
	}

	public Double getClaimAmt() {
		return claimAmt;
	}

	public void setClaimAmt(Double claimAmt) {
		this.claimAmt = claimAmt;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getAnySubsidyInvolved() {
		return anySubsidyInvolved;
	}

	public void setAnySubsidyInvolved(String anySubsidyInvolved) {
		this.anySubsidyInvolved = anySubsidyInvolved;
	}

	public String getSubsidyReceived() {
		return subsidyReceived;
	}

	public void setSubsidyReceived(String subsidyReceived) {
		this.subsidyReceived = subsidyReceived;
	}

	public String getSubsidyAdjust() {
		return subsidyAdjust;
	}

	public void setSubsidyAdjust(String subsidyAdjust) {
		this.subsidyAdjust = subsidyAdjust;
	}

	public String getDateOfSubsidy() {
		return dateOfSubsidy;
	}

	public void setDateOfSubsidy(String dateOfSubsidy) {
		this.dateOfSubsidy = dateOfSubsidy;
	}

	public Double getSubsidyAmt() {
		return subsidyAmt;
	}

	public void setSubsidyAmt(Double subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
	}

	public Double getsNoTL() {
		return sNoTL;
	}

	public void setsNoTL(Double sNoTL) {
		this.sNoTL = sNoTL;
	}

	public String getCgPanTL() {
		return cgPanTL;
	}

	public void setCgPanTL(String cgPanTL) {
		this.cgPanTL = cgPanTL;
	}

	public Double getLatestOsAmt() {
		return latestOsAmt;
	}

	public void setLatestOsAmt(Double latestOsAmt) {
		this.latestOsAmt = latestOsAmt;
	}

	public String getLatestOsAmtVStr() {
		return latestOsAmtVStr;
	}

	public void setLatestOsAmtVStr(String latestOsAmtVStr) {
		this.latestOsAmtVStr = latestOsAmtVStr;
	}

	public BigDecimal getLatestOsAmtBC() {
		return latestOsAmtBC;
	}

	public void setLatestOsAmtBC(BigDecimal latestOsAmtBC) {
		this.latestOsAmtBC = latestOsAmtBC;
	}

	public Double getTotalOsAmt() {
		return totalOsAmt;
	}

	public void setTotalOsAmt(Double totalOsAmt) {
		this.totalOsAmt = totalOsAmt;
	}

	public Double getRecovery() {
		return recovery;
	}

	public void setRecovery(Double recovery) {
		this.recovery = recovery;
	}

	public String getModeRecovery() {
		return modeRecovery;
	}

	public void setModeRecovery(String modeRecovery) {
		this.modeRecovery = modeRecovery;
	}

	public String getNpaRecoveryFlag() {
		return npaRecoveryFlag;
	}

	public void setNpaRecoveryFlag(String npaRecoveryFlag) {
		this.npaRecoveryFlag = npaRecoveryFlag;
	}

	public String getConfirmRecoveryFlag() {
		return confirmRecoveryFlag;
	}

	public void setConfirmRecoveryFlag(String confirmRecoveryFlag) {
		this.confirmRecoveryFlag = confirmRecoveryFlag;
	}

	public Double getLandValue() {
		return landValue;
	}

	public void setLandValue(Double landValue) {
		this.landValue = landValue;
	}

	public Double getBuildingValue() {
		return buildingValue;
	}

	public void setBuildingValue(Double buildingValue) {
		this.buildingValue = buildingValue;
	}

	public Double getPlanetValue() {
		return planetValue;
	}

	public void setPlanetValue(Double planetValue) {
		this.planetValue = planetValue;
	}

	public Double getOtherAssetValue() {
		return otherAssetValue;
	}

	public void setOtherAssetValue(Double otherAssetValue) {
		this.otherAssetValue = otherAssetValue;
	}

	public Double getCurrentAssetValue() {
		return currentAssetValue;
	}

	public void setCurrentAssetValue(Double currentAssetValue) {
		this.currentAssetValue = currentAssetValue;
	}

	public Double getOthersValue() {
		return othersValue;
	}

	public void setOthersValue(Double othersValue) {
		this.othersValue = othersValue;
	}

	public Double getNetworthOfPromotor() {
		return networthOfPromotor;
	}

	public void setNetworthOfPromotor(Double networthOfPromotor) {
		this.networthOfPromotor = networthOfPromotor;
	}

	public String getReductionReason() {
		return reductionReason;
	}

	public void setReductionReason(String reductionReason) {
		this.reductionReason = reductionReason;
	}

	public Double getTotalSecuritydetails() {
		return totalSecuritydetails;
	}

	public void setTotalSecuritydetails(Double totalSecuritydetails) {
		this.totalSecuritydetails = totalSecuritydetails;
	}

	public String getTotalSecuritydetailsError() {
		return totalSecuritydetailsError;
	}

	public void setTotalSecuritydetailsError(String totalSecuritydetailsError) {
		this.totalSecuritydetailsError = totalSecuritydetailsError;
	}

	public Double getLandValue1() {
		return landValue1;
	}

	public void setLandValue1(Double landValue1) {
		this.landValue1 = landValue1;
	}

	public Double getBuildingValue1() {
		return buildingValue1;
	}

	public void setBuildingValue1(Double buildingValue1) {
		this.buildingValue1 = buildingValue1;
	}

	public Double getPlanetValue1() {
		return planetValue1;
	}

	public void setPlanetValue1(Double planetValue1) {
		this.planetValue1 = planetValue1;
	}

	public Double getOtherAssetValue1() {
		return otherAssetValue1;
	}

	public void setOtherAssetValue1(Double otherAssetValue1) {
		this.otherAssetValue1 = otherAssetValue1;
	}

	public Double getCurrentAssetValue1() {
		return currentAssetValue1;
	}

	public void setCurrentAssetValue1(Double currentAssetValue1) {
		this.currentAssetValue1 = currentAssetValue1;
	}

	public Double getOthersValue1() {
		return othersValue1;
	}

	public void setOthersValue1(Double othersValue1) {
		this.othersValue1 = othersValue1;
	}

	public Double getNetworthOfPromotor1() {
		return networthOfPromotor1;
	}

	public void setNetworthOfPromotor1(Double networthOfPromotor1) {
		this.networthOfPromotor1 = networthOfPromotor1;
	}

	public String getReductionReason1() {
		return reductionReason1;
	}

	public void setReductionReason1(String reductionReason1) {
		this.reductionReason1 = reductionReason1;
	}

	public Double getTotalSecuritydetails1() {
		return totalSecuritydetails1;
	}

	public void setTotalSecuritydetails1(Double totalSecuritydetails1) {
		this.totalSecuritydetails1 = totalSecuritydetails1;
	}

	public String getTotalSecuritydetails1Error() {
		return totalSecuritydetails1Error;
	}

	public void setTotalSecuritydetails1Error(String totalSecuritydetails1Error) {
		this.totalSecuritydetails1Error = totalSecuritydetails1Error;
	}

	public Double getsNoGCP() {
		return sNoGCP;
	}

	public void setsNoGCP(Double sNoGCP) {
		this.sNoGCP = sNoGCP;
	}

	public String getCgPanGCP() {
		return cgPanGCP;
	}

	public void setCgPanGCP(String cgPanGCP) {
		this.cgPanGCP = cgPanGCP;
	}

	public Double getLoanLimitCovered() {
		return loanLimitCovered;
	}

	public void setLoanLimitCovered(Double loanLimitCovered) {
		this.loanLimitCovered = loanLimitCovered;
	}

	public Double getGuaranteeCov() {
		return guaranteeCov;
	}

	public void setGuaranteeCov(Double guaranteeCov) {
		this.guaranteeCov = guaranteeCov;
	}

	public Double getOsAmtClaimGCP() {
		return osAmtClaimGCP;
	}

	public void setOsAmtClaimGCP(Double osAmtClaimGCP) {
		this.osAmtClaimGCP = osAmtClaimGCP;
	}

	public Double getEligableAmtClaim() {
		return eligableAmtClaim;
	}

	public void setEligableAmtClaim(Double eligableAmtClaim) {
		this.eligableAmtClaim = eligableAmtClaim;
	}

	public Double getFirstInstallClaim() {
		return firstInstallClaim;
	}

	public void setFirstInstallClaim(Double firstInstallClaim) {
		this.firstInstallClaim = firstInstallClaim;
	}

	public String getFinancialAssistanceDtls() {
		return financialAssistanceDtls;
	}

	public void setFinancialAssistanceDtls(String financialAssistanceDtls) {
		this.financialAssistanceDtls = financialAssistanceDtls;
	}

	public String getFinancialPositionComments() {
		return financialPositionComments;
	}

	public void setFinancialPositionComments(String financialPositionComments) {
		this.financialPositionComments = financialPositionComments;
	}

	public String getCreditSupport() {
		return creditSupport;
	}

	public void setCreditSupport(String creditSupport) {
		this.creditSupport = creditSupport;
	}

	public String getBankFacilityDtls() {
		return bankFacilityDtls;
	}

	public void setBankFacilityDtls(String bankFacilityDtls) {
		this.bankFacilityDtls = bankFacilityDtls;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLatestOsGuranteeAmtVStr() {
		return latestOsGuranteeAmtVStr;
	}

	public void setLatestOsGuranteeAmtVStr(String latestOsGuranteeAmtVStr) {
		this.latestOsGuranteeAmtVStr = latestOsGuranteeAmtVStr;
	}

	public BigDecimal getLatestOsGuranteeAmtBC() {
		return latestOsGuranteeAmtBC;
	}

	public void setLatestOsGuranteeAmtBC(BigDecimal latestOsGuranteeAmtBC) {
		this.latestOsGuranteeAmtBC = latestOsGuranteeAmtBC;
	}

	public String getLandValueStr() {
		return landValueStr;
	}

	public void setLandValueStr(String landValueStr) {
		this.landValueStr = landValueStr;
	}

	public BigDecimal getLandValueBD() {
		return landValueBD;
	}

	public void setLandValueBD(BigDecimal landValueBD) {
		this.landValueBD = landValueBD;
	}

	public String getBuildingValueStr() {
		return buildingValueStr;
	}

	public void setBuildingValueStr(String buildingValueStr) {
		this.buildingValueStr = buildingValueStr;
	}

}
