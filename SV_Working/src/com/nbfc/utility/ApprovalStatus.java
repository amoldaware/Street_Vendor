package com.nbfc.utility;

public enum ApprovalStatus {

	SUBMITTED_TO_MLI_CHECHER("MM"), SUBMITTED_TO_CGTMSE("MC_A"), REJECTED_BY_CHECKER("MC_R"), APPROVED("CG_A"),
	REJECTED("CG_R"), PENDING_AT_CGTMSE_CHECKER("CGC_M");

	private String value;

	private ApprovalStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}