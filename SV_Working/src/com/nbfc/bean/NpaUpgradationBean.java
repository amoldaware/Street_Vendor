package com.nbfc.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class NpaUpgradationBean {
	
	private String MLIID;
	@NotNull(message = "CGPAN is Required.")
	private String CGPAN;
	private String npaDt;
	private String npaReason;
	@NotNull(message="Npa Upgradation date is required.")
	private String npaUpgradationDt;
	@NotNull(message="Npa Upgradation remark is required.")
	private String upgradationRemarks;
	private String bankName;
	private String borrowerName;
	private int dayCount;
	private String status;
	private boolean selectAll;
	private List<String> chcktbl;
	private String upgradationReturnRemarks;
	private String fromDate;
	private String toDate;
	
	
	
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
	public String getUpgradationReturnRemarks() {
		return upgradationReturnRemarks;
	}
	public void setUpgradationReturnRemarks(String upgradationReturnRemarks) {
		this.upgradationReturnRemarks = upgradationReturnRemarks;
	}
	public List<String> getChcktbl() {
		return chcktbl;
	}
	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}
	public boolean isSelectAll() {
		return selectAll;
	}
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
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
	public String getNpaDt() {
		return npaDt;
	}
	public void setNpaDt(String npaDt) {
		this.npaDt = npaDt;
	}
	public String getNpaReason() {
		return npaReason;
	}
	public void setNpaReason(String npaReason) {
		this.npaReason = npaReason;
	}
	public String getNpaUpgradationDt() {
		return npaUpgradationDt;
	}
	public void setNpaUpgradationDt(String npaUpgradationDt) {
		this.npaUpgradationDt = npaUpgradationDt;
	}
	public String getUpgradationRemarks() {
		return upgradationRemarks;
	}
	public void setUpgradationRemarks(String upgradationRemarks) {
		this.upgradationRemarks = upgradationRemarks;
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
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
