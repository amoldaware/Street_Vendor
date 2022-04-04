package com.nbfc.bean;

import java.util.List;

public class DanAllForCgtmseCheckerBean {

	private String danId;
	private String mliName;
	private String amount;
	private boolean selectAll;
	private List<String>chcktbl;
	private String portfolioNo;

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
	
	public String getPortfolioNo() {
		return portfolioNo;
	}
	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}
	public String getMliName() {
		return mliName;
	}
	public void setMliName(String mliName) {
		this.mliName = mliName;
	}
	
	public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
