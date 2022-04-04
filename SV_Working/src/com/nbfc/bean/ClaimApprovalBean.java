package com.nbfc.bean;

public class ClaimApprovalBean {

	private String CLAIM_REF_NO;
	private String MLI_STATUS;
	private String MLI_REMARK;
	private String FINAL_SETTELEMENT_AMT;
	private String ACTUAL_CLAIM_AMT;
	public String getACTUAL_CLAIM_AMT() {
		return ACTUAL_CLAIM_AMT;
	}
	public void setACTUAL_CLAIM_AMT(String aCTUAL_CLAIM_AMT) {
		ACTUAL_CLAIM_AMT = aCTUAL_CLAIM_AMT;
	}
	public String getFINAL_SETTELEMENT_AMT() {
		return FINAL_SETTELEMENT_AMT;
	}
	public void setFINAL_SETTELEMENT_AMT(String fINAL_SETTELEMENT_AMT) {
		FINAL_SETTELEMENT_AMT = fINAL_SETTELEMENT_AMT;
	}
	public String getCLAIM_REF_NO() {
		return CLAIM_REF_NO;
	}
	public void setCLAIM_REF_NO(String cLAIM_REF_NO) {
		CLAIM_REF_NO = cLAIM_REF_NO;
	}
	public String getMLI_STATUS() {
		return MLI_STATUS;
	}
	public void setMLI_STATUS(String mLI_STATUS) {
		MLI_STATUS = mLI_STATUS;
	}
	public String getMLI_REMARK() {
		return MLI_REMARK;
	}
	public void setMLI_REMARK(String mLI_REMARK) {
		MLI_REMARK = mLI_REMARK;
	}
}
