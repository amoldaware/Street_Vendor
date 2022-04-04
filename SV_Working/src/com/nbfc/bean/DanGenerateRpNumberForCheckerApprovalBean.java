package com.nbfc.bean;

import java.util.List;

public class DanGenerateRpNumberForCheckerApprovalBean {

	private String rpNumber;
	private String danId;
	private String remark;

	private String virtualAccountNumber;
	//private Long amount;
	private String amount;
	private String date;
	private String status;
	private List<String> chcktbl2;
	private String message;
	private List<String> chcktbl;
	private boolean chcktbl1;
	private boolean selectAll;
	private boolean selectAll1;
	private boolean select_all2;
	
	private String ifscCode;
	private String beneficiaryAcccount;
	private String branch;
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBeneficiaryAcccount() {
		return beneficiaryAcccount;
	}

	public void setBeneficiaryAcccount(String beneficiaryAcccount) {
		this.beneficiaryAcccount = beneficiaryAcccount;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<String> getChcktbl2() {
		return chcktbl2;
	}

	public void setChcktbl2(List<String> chcktbl2) {
		this.chcktbl2 = chcktbl2;
	}

	public boolean isSelect_all2() {
		return select_all2;
	}

	public void setSelect_all2(boolean select_all2) {
		this.select_all2 = select_all2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDanId() {
		return danId;
	}

	public void setDanId(String danId) {
		this.danId = danId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isChcktbl1() {
		return chcktbl1;
	}

	public void setChcktbl1(boolean chcktbl1) {
		this.chcktbl1 = chcktbl1;
	}

	public boolean isSelectAll1() {
		return selectAll1;
	}

	public void setSelectAll1(boolean selectAll1) {
		this.selectAll1 = selectAll1;
	}

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

	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	public String getVirtualAccountNumber() {
		return virtualAccountNumber;
	}

	public void setVirtualAccountNumber(String virtualAccountNumber) {
		this.virtualAccountNumber = virtualAccountNumber;
	}

	public String getAmount() {
		return amount;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
