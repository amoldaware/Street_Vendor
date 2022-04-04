package com.nbfc.bean;

import java.io.Serializable;

public class ClaimUploadDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String financeYears;
	private String totalExposure;
	private String maximumClaimAmount;
	private String claimSubmitted;
	private String recoveryAmount;
	private String balanceClaimAmount;
	private String actualClaimSetteled;
	private String roleType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getFinanceYears() {
		return financeYears;
	}

	public void setFinanceYears(String financeYears) {
		this.financeYears = financeYears;
	}

	public String getTotalExposure() {
		return totalExposure;
	}

	public void setTotalExposure(String totalExposure) {
		this.totalExposure = totalExposure;
	}

	public String getMaximumClaimAmount() {
		return maximumClaimAmount;
	}

	public void setMaximumClaimAmount(String maximumClaimAmount) {
		this.maximumClaimAmount = maximumClaimAmount;
	}

	public String getClaimSubmitted() {
		return claimSubmitted;
	}

	public void setClaimSubmitted(String claimSubmitted) {
		this.claimSubmitted = claimSubmitted;
	}

	public String getRecoveryAmount() {
		return recoveryAmount;
	}

	public void setRecoveryAmount(String recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}

	public String getBalanceClaimAmount() {
		return balanceClaimAmount;
	}

	public void setBalanceClaimAmount(String balanceClaimAmount) {
		this.balanceClaimAmount = balanceClaimAmount;
	}

	public String getActualClaimSetteled() {
		return actualClaimSetteled;
	}

	public void setActualClaimSetteled(String actualClaimSetteled) {
		this.actualClaimSetteled = actualClaimSetteled;
	}

}
