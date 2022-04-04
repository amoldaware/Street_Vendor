package com.nbfc.bean;

import javax.validation.constraints.NotNull;

public class NPAMarkBean {

	private String MLIID;
	@NotNull(message = "CGPAN is Required.")
	private String CGPAN;
	@NotNull(message = "CGPAN is Required.")
	private String npaId;

	private String npaDt;
	@NotNull(message = " 'Y' or 'N' Required !!")
	private String isAsPerRBI;
	@NotNull(message = " 'Y' or 'N' Required !!")
	private String npaConfirm;
	@NotNull(message = "NPA Reason is Required.")
	private String npaReason;
	@NotNull(message = "Field is Required.")
	private String effortsTaken;
	@NotNull(message = "Field is Required.")
	private String isAcctReconstructed;
	@NotNull(message = " 'Y' or 'N' Required !!")
	private String subsidyFlag;
	private String isSubsidyRcvd;
	private String isSubsidyAdjusted;
	@NotNull(message = "Field is Required.")
	private double subsidyLastRcvdAmt;
	private String subsidyLastRcvdDt;
	private String npaCreatedDate;
	private String securityAsOnSancDt;
	private String networthAsOnSancDt;
	private String networthAsOnNpaDt;
	private String reasonForReductionAsOnSancDt;
	private String reasonForReductionAsOnNpaDt;

	private String guarStartDt1;
	private String sanctionDt1;
	private String firstDisbDt1;
	private String lastDisbDt1;
	private String firstInstDt1;
	private String lasInspDt;
	@NotNull(message = "Amount is Required.")
	private double totalDisbAmt1;
	@NotNull(message = "Amount is Required !!")
	private double moratoriumPrincipal1;
	@NotNull(message = "Amount is Required !!")
	private double moratoriumInterest1;
	@NotNull(message = "Amount is Required !!")
	private double repayPrincipal;
	@NotNull(message = "Amount is Required !!")
	private double repayInterest;
	@NotNull(message = "Amount is Required !!")
	private double outstandingPrincipal1;
	@NotNull(message = "Amount is Required !!")
	private double outstandingInterest1;

	private String totalDisbAmt1Error;
	private String moratoriumPrincipal1Error;
	private String moratoriumInterest1Error;
	private String repayPrincipalError;
	private String repayInterestError;
	private String outstandingPrincipal1Error;
	private String totalSecuritydetailsError;
	private String totalSecuritydetails1Error;
	private String outstandingInterest1Error;
	private String claimEligibityAmtError;
	private String loanType;
	
	private double landValue;
	private double buildingValue;
	private double planetValue;
	private double otherAssetValue;
	private double currentAssetValue;
	private double othersValue;
	private double landValue1;
	private double buildingValue1;
	private double planetValue1;
	private double otherAssetValue1;
	private double currentAssetValue1;
	private double othersValue1;
	
	private double networthOfPromotor;
	private double networthOfPromotor1;
	private String reductionReason;
	private String reductionReason1;
	private double totalSecuritydetails;
	private double totalSecuritydetails1;
	private Double intrestRate;
	private double latestOsAmt;
	private double totalGuaranteeAmt;
	private double claimEligibityAmt;
	public String getClaimEligibityAmtError() {
		return claimEligibityAmtError;
	}

	public void setClaimEligibityAmtError(String claimEligibityAmtError) {
		this.claimEligibityAmtError = claimEligibityAmtError;
	}

	public double getLatestOsAmt() {
		return latestOsAmt;
	}

	public void setLatestOsAmt(double latestOsAmt) {
		this.latestOsAmt = latestOsAmt;
	}

	public double getTotalGuaranteeAmt() {
		return totalGuaranteeAmt;
	}

	public void setTotalGuaranteeAmt(double totalGuaranteeAmt) {
		this.totalGuaranteeAmt = totalGuaranteeAmt;
	}

	public double getClaimEligibityAmt() {
		return claimEligibityAmt;
	}

	public void setClaimEligibityAmt(double claimEligibityAmt) {
		this.claimEligibityAmt = claimEligibityAmt;
	}

	public String getTotalSecuritydetailsError() {
		return totalSecuritydetailsError;
	}

	public void setTotalSecuritydetailsError(String totalSecuritydetailsError) {
		this.totalSecuritydetailsError = totalSecuritydetailsError;
	}

	public String getTotalSecuritydetails1Error() {
		return totalSecuritydetails1Error;
	}

	public void setTotalSecuritydetails1Error(String totalSecuritydetails1Error) {
		this.totalSecuritydetails1Error = totalSecuritydetails1Error;
	}

	public Double getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(Double intrestRate) {
		this.intrestRate = intrestRate;
	}

	public double getLandValue() {
		return landValue;
	}

	public void setLandValue(double landValue) {
		this.landValue = landValue;
	}

	public double getBuildingValue() {
		return buildingValue;
	}

	public void setBuildingValue(double buildingValue) {
		this.buildingValue = buildingValue;
	}

	public double getPlanetValue() {
		return planetValue;
	}

	public void setPlanetValue(double planetValue) {
		this.planetValue = planetValue;
	}

	public double getOtherAssetValue() {
		return otherAssetValue;
	}

	public void setOtherAssetValue(double otherAssetValue) {
		this.otherAssetValue = otherAssetValue;
	}

	public double getCurrentAssetValue() {
		return currentAssetValue;
	}

	public void setCurrentAssetValue(double currentAssetValue) {
		this.currentAssetValue = currentAssetValue;
	}

	public double getOthersValue() {
		return othersValue;
	}

	public void setOthersValue(double othersValue) {
		this.othersValue = othersValue;
	}

	public double getLandValue1() {
		return landValue1;
	}

	public void setLandValue1(double landValue1) {
		this.landValue1 = landValue1;
	}

	public double getBuildingValue1() {
		return buildingValue1;
	}

	public void setBuildingValue1(double buildingValue1) {
		this.buildingValue1 = buildingValue1;
	}

	public double getPlanetValue1() {
		return planetValue1;
	}

	public void setPlanetValue1(double planetValue1) {
		this.planetValue1 = planetValue1;
	}

	public double getOtherAssetValue1() {
		return otherAssetValue1;
	}

	public void setOtherAssetValue1(double otherAssetValue1) {
		this.otherAssetValue1 = otherAssetValue1;
	}

	public double getCurrentAssetValue1() {
		return currentAssetValue1;
	}

	public void setCurrentAssetValue1(double currentAssetValue1) {
		this.currentAssetValue1 = currentAssetValue1;
	}

	public double getOthersValue1() {
		return othersValue1;
	}

	public void setOthersValue1(double othersValue1) {
		this.othersValue1 = othersValue1;
	}

	public double getNetworthOfPromotor() {
		return networthOfPromotor;
	}

	public void setNetworthOfPromotor(double networthOfPromotor) {
		this.networthOfPromotor = networthOfPromotor;
	}

	public double getNetworthOfPromotor1() {
		return networthOfPromotor1;
	}

	public void setNetworthOfPromotor1(double networthOfPromotor1) {
		this.networthOfPromotor1 = networthOfPromotor1;
	}

	public String getReductionReason() {
		return reductionReason;
	}

	public void setReductionReason(String reductionReason) {
		this.reductionReason = reductionReason;
	}

	public String getReductionReason1() {
		return reductionReason1;
	}

	public void setReductionReason1(String reductionReason1) {
		this.reductionReason1 = reductionReason1;
	}

	public double getTotalSecuritydetails() {
		return totalSecuritydetails;
	}

	public void setTotalSecuritydetails(double totalSecuritydetails) {
		this.totalSecuritydetails = totalSecuritydetails;
	}

	public double getTotalSecuritydetails1() {
		return totalSecuritydetails1;
	}

	public void setTotalSecuritydetails1(double totalSecuritydetails1) {
		this.totalSecuritydetails1 = totalSecuritydetails1;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getTotalDisbAmt1Error() {
		return totalDisbAmt1Error;
	}

	public void setTotalDisbAmt1Error(String totalDisbAmt1Error) {
		this.totalDisbAmt1Error = totalDisbAmt1Error;
	}

	public String getMoratoriumPrincipal1Error() {
		return moratoriumPrincipal1Error;
	}

	public void setMoratoriumPrincipal1Error(String moratoriumPrincipal1Error) {
		this.moratoriumPrincipal1Error = moratoriumPrincipal1Error;
	}

	public String getMoratoriumInterest1Error() {
		return moratoriumInterest1Error;
	}

	public void setMoratoriumInterest1Error(String moratoriumInterest1Error) {
		this.moratoriumInterest1Error = moratoriumInterest1Error;
	}

	public String getRepayPrincipalError() {
		return repayPrincipalError;
	}

	public void setRepayPrincipalError(String repayPrincipalError) {
		this.repayPrincipalError = repayPrincipalError;
	}

	public String getRepayInterestError() {
		return repayInterestError;
	}

	public void setRepayInterestError(String repayInterestError) {
		this.repayInterestError = repayInterestError;
	}

	public String getOutstandingPrincipal1Error() {
		return outstandingPrincipal1Error;
	}

	public void setOutstandingPrincipal1Error(String outstandingPrincipal1Error) {
		this.outstandingPrincipal1Error = outstandingPrincipal1Error;
	}

	public String getOutstandingInterest1Error() {
		return outstandingInterest1Error;
	}

	public void setOutstandingInterest1Error(String outstandingInterest1Error) {
		this.outstandingInterest1Error = outstandingInterest1Error;
	}

	public String getLasInspDt() {
		return lasInspDt;
	}

	public void setLasInspDt(String lasInspDt) {
		this.lasInspDt = lasInspDt;
	}

	public double getRepayPrincipal() {
		return repayPrincipal;
	}

	public void setRepayPrincipal(double repayPrincipal) {
		this.repayPrincipal = repayPrincipal;
	}

	public double getRepayInterest() {
		return repayInterest;
	}

	public void setRepayInterest(double repayInterest) {
		this.repayInterest = repayInterest;
	}

	public double getOutstandingPrincipal1() {
		return outstandingPrincipal1;
	}

	public void setOutstandingPrincipal1(double outstandingPrincipal1) {
		this.outstandingPrincipal1 = outstandingPrincipal1;
	}

	public double getOutstandingInterest1() {
		return outstandingInterest1;
	}

	public void setOutstandingInterest1(double outstandingInterest1) {
		this.outstandingInterest1 = outstandingInterest1;
	}

	public String getEffortsTaken() {
		return effortsTaken;
	}

	public void setEffortsTaken(String effortsTaken) {
		this.effortsTaken = effortsTaken;
	}

	public String getMLIID() {
		return MLIID;
	}

	public void setMLIID(String mLIID) {
		MLIID = mLIID;
	}

	public String getCGPAN() {
		return CGPAN;
	}

	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}

	public String getNpaId() {
		return npaId;
	}

	public void setNpaId(String npaId) {
		this.npaId = npaId;
	}

	public String getNpaDt() {
		return npaDt;
	}

	public void setNpaDt(String npaDt) {
		this.npaDt = npaDt;
	}

	public String getIsAsPerRBI() {
		return isAsPerRBI;
	}

	public void setIsAsPerRBI(String isAsPerRBI) {
		this.isAsPerRBI = isAsPerRBI;
	}

	public String getNpaConfirm() {
		return npaConfirm;
	}

	public void setNpaConfirm(String npaConfirm) {
		this.npaConfirm = npaConfirm;
	}

	public String getNpaReason() {
		return npaReason;
	}

	public void setNpaReason(String npaReason) {
		this.npaReason = npaReason;
	}

	public String getIsAcctReconstructed() {
		return isAcctReconstructed;
	}

	public void setIsAcctReconstructed(String isAcctReconstructed) {
		this.isAcctReconstructed = isAcctReconstructed;
	}

	public String getSubsidyFlag() {
		return subsidyFlag;
	}

	public void setSubsidyFlag(String subsidyFlag) {
		this.subsidyFlag = subsidyFlag;
	}

	public String getIsSubsidyRcvd() {
		return isSubsidyRcvd;
	}

	public void setIsSubsidyRcvd(String isSubsidyRcvd) {
		this.isSubsidyRcvd = isSubsidyRcvd;
	}

	public String getIsSubsidyAdjusted() {
		return isSubsidyAdjusted;
	}

	public void setIsSubsidyAdjusted(String isSubsidyAdjusted) {
		this.isSubsidyAdjusted = isSubsidyAdjusted;
	}

	public double getSubsidyLastRcvdAmt() {
		return subsidyLastRcvdAmt;
	}

	public void setSubsidyLastRcvdAmt(double subsidyLastRcvdAmt) {
		this.subsidyLastRcvdAmt = subsidyLastRcvdAmt;
	}

	public String getSubsidyLastRcvdDt() {
		return subsidyLastRcvdDt;
	}

	public void setSubsidyLastRcvdDt(String subsidyLastRcvdDt) {
		this.subsidyLastRcvdDt = subsidyLastRcvdDt;
	}

	public String getNpaCreatedDate() {
		return npaCreatedDate;
	}

	public void setNpaCreatedDate(String npaCreatedDate) {
		this.npaCreatedDate = npaCreatedDate;
	}

	public String getSecurityAsOnSancDt() {
		return securityAsOnSancDt;
	}

	public void setSecurityAsOnSancDt(String securityAsOnSancDt) {
		this.securityAsOnSancDt = securityAsOnSancDt;
	}

	public String getNetworthAsOnSancDt() {
		return networthAsOnSancDt;
	}

	public void setNetworthAsOnSancDt(String networthAsOnSancDt) {
		this.networthAsOnSancDt = networthAsOnSancDt;
	}

	public String getNetworthAsOnNpaDt() {
		return networthAsOnNpaDt;
	}

	public void setNetworthAsOnNpaDt(String networthAsOnNpaDt) {
		this.networthAsOnNpaDt = networthAsOnNpaDt;
	}

	public String getReasonForReductionAsOnSancDt() {
		return reasonForReductionAsOnSancDt;
	}

	public void setReasonForReductionAsOnSancDt(
			String reasonForReductionAsOnSancDt) {
		this.reasonForReductionAsOnSancDt = reasonForReductionAsOnSancDt;
	}

	public String getReasonForReductionAsOnNpaDt() {
		return reasonForReductionAsOnNpaDt;
	}

	public void setReasonForReductionAsOnNpaDt(
			String reasonForReductionAsOnNpaDt) {
		this.reasonForReductionAsOnNpaDt = reasonForReductionAsOnNpaDt;
	}

	public String getGuarStartDt1() {
		return guarStartDt1;
	}

	public void setGuarStartDt1(String guarStartDt1) {
		this.guarStartDt1 = guarStartDt1;
	}

	public String getSanctionDt1() {
		return sanctionDt1;
	}

	public void setSanctionDt1(String sanctionDt1) {
		this.sanctionDt1 = sanctionDt1;
	}

	public String getFirstDisbDt1() {
		return firstDisbDt1;
	}

	public void setFirstDisbDt1(String firstDisbDt1) {
		this.firstDisbDt1 = firstDisbDt1;
	}

	public String getLastDisbDt1() {
		return lastDisbDt1;
	}

	public void setLastDisbDt1(String lastDisbDt1) {
		this.lastDisbDt1 = lastDisbDt1;
	}

	public String getFirstInstDt1() {
		return firstInstDt1;
	}

	public void setFirstInstDt1(String firstInstDt1) {
		this.firstInstDt1 = firstInstDt1;
	}

	public double getTotalDisbAmt1() {
		return totalDisbAmt1;
	}

	public void setTotalDisbAmt1(double totalDisbAmt1) {
		this.totalDisbAmt1 = totalDisbAmt1;
	}

	public double getMoratoriumPrincipal1() {
		return moratoriumPrincipal1;
	}

	public void setMoratoriumPrincipal1(double moratoriumPrincipal1) {
		this.moratoriumPrincipal1 = moratoriumPrincipal1;
	}

	public double getMoratoriumInterest1() {
		return moratoriumInterest1;
	}

	public void setMoratoriumInterest1(double moratoriumInterest1) {
		this.moratoriumInterest1 = moratoriumInterest1;
	}

	@Override
	public String toString() {
		return "NPADetailsBean [MLIID=" + MLIID + ", CGPAN=" + CGPAN
				+ ", npaId=" + npaId + ", npaDt=" + npaDt + ", isAsPerRBI="
				+ isAsPerRBI + ", npaConfirm=" + npaConfirm + ", npaReason="
				+ npaReason + ", effortsTaken=" + effortsTaken
				+ ", isAcctReconstructed=" + isAcctReconstructed
				+ ", subsidyFlag=" + subsidyFlag + ", isSubsidyRcvd="
				+ isSubsidyRcvd + ", isSubsidyAdjusted=" + isSubsidyAdjusted
				+ ", subsidyLastRcvdAmt=" + subsidyLastRcvdAmt
				+ ", subsidyLastRcvdDt=" + subsidyLastRcvdDt
				+ ", npaCreatedDate=" + npaCreatedDate
				+ ", securityAsOnSancDt=" + securityAsOnSancDt
				+ ", networthAsOnSancDt=" + networthAsOnSancDt
				+ ", networthAsOnNpaDt=" + networthAsOnNpaDt
				+ ", reasonForReductionAsOnSancDt="
				+ reasonForReductionAsOnSancDt
				+ ", reasonForReductionAsOnNpaDt="
				+ reasonForReductionAsOnNpaDt + ", guarStartDt1="
				+ guarStartDt1 + ", sanctionDt1=" + sanctionDt1
				+ ", firstDisbDt1=" + firstDisbDt1 + ", lastDisbDt1="
				+ lastDisbDt1 + ", firstInstDt1=" + firstInstDt1
				+ ", lasInspDt=" + lasInspDt + ", totalDisbAmt1="
				+ totalDisbAmt1 + ", moratoriumPrincipal1="
				+ moratoriumPrincipal1 + ", moratoriumInterest1="
				+ moratoriumInterest1 + ", repayPrincipal=" + repayPrincipal
				+ ", repayInterest=" + repayInterest
				+ ", outstandingPrincipal1=" + outstandingPrincipal1
				+ ", outstandingInterest1=" + outstandingInterest1 + "]";
	}

}
