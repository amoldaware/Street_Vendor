package com.nbfc.bean;

import java.io.Serializable;

public class RecoveryDownLoadExcelData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rp_no;
	private String virtualAccNo;
	private String recoveryAmt;
	private String ifscCode;
	public String getRp_no() {
		return rp_no;
	}
	public void setRp_no(String rp_no) {
		this.rp_no = rp_no;
	}
	public String getVirtualAccNo() {
		return virtualAccNo;
	}
	public void setVirtualAccNo(String virtualAccNo) {
		this.virtualAccNo = virtualAccNo;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	

}
