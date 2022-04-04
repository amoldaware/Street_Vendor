package com.nbfc.bean;

public class BatchApprovalDataBean {

	private String fileId;
	private String longName;
	private String outStandingAmount;
	private String recordCount;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(String outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

}
