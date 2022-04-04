package com.nbfc.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class NbfcAppropriationBean {
	 private String fromDate;
	 private String toDate;

	private String rpNumber;
	private String danId;
	private String memberId;
	private String memberName;
	
	

	

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

	private int instrumentAmount;

	
	private String virtualAccountNumber;
	private int allocatedAmount;
	private Date instrumentDate;
	private int inwardAmount;
	private String message;
	//private List<String> chcktbl;
	private List<String> chcktbl;

	//private boolean chcktbl1;
	
	private String date;
	

	

	private boolean selectAll;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getInstrumentAmount() {
		return instrumentAmount;
	}

	public void setInstrumentAmount(int instrumentAmount) {
		this.instrumentAmount = instrumentAmount;
	}

	public int getAllocatedAmount() {
		return allocatedAmount;
	}

	public void setAllocatedAmount(int allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public Date getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}



	public int getInwardAmount() {
		return inwardAmount;
	}

	public void setInwardAmount(int inwardAmount) {
		this.inwardAmount = inwardAmount;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	/*public boolean isChcktbl1() {
		return chcktbl1;
	}

	public void setChcktbl1(boolean chcktbl1) {
		this.chcktbl1 = chcktbl1;
	}
*/
	

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	/*public List<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
	}*/

	public String getRpNumber() {
		return rpNumber;
	}

	public List<String> getChcktbl() {
		return chcktbl;
	}

	public void setChcktbl(List<String> chcktbl) {
		this.chcktbl = chcktbl;
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

	

	

	

}
