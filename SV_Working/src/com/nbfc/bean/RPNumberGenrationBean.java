package com.nbfc.bean;


public class RPNumberGenrationBean {

	private String rpNUmber;
	private String virtualACNO;
	private int amount;
	private String genratedDate;
	private String status;
	private boolean selectPayment;

	
	public RPNumberGenrationBean() {
		super();
	}

	public RPNumberGenrationBean(String rpNUmber, String virtualACNO,
			int amount, String genratedDate, String status) {
		super();
		this.rpNUmber = rpNUmber;
		this.virtualACNO = virtualACNO;
		this.amount = amount;
		this.genratedDate = genratedDate;
		this.status = status;
		
	}

	public String getRpNUmber() {
		return rpNUmber;
	}

	public void setRpNUmber(String rpNUmber) {
		this.rpNUmber = rpNUmber;
	}

	public String getVirtualACNO() {
		return virtualACNO;
	}

	public void setVirtualACNO(String virtualACNO) {
		this.virtualACNO = virtualACNO;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getGenratedDate() {
		return genratedDate;
	}

	public void setGenratedDate(String genratedDate) {
		this.genratedDate = genratedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSelectPayment() {
		return selectPayment;
	}

	public void setSelectPayment(boolean selectPayment) {
		this.selectPayment = selectPayment;
	}

}
