package com.nbfc.bean;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class MLICheckerBean {

	private String appRefNo;
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public String getAppRefNo() {
		return appRefNo;
	}

	public void setAppRefNo(String appRefNo) {
		this.appRefNo = appRefNo;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MLICheckerBean(String appRefNo, String remarks) {
		super();
		this.appRefNo = appRefNo;
		this.remarks = remarks;
	}

	public MLICheckerBean() {
		super();
	}

	@Override
	public String toString() {
		return "MLICheckerBean [appRefNo=" + appRefNo + ", remarks=" + remarks
				+ "]";
	}

}
