package com.nbfc.bean;

public class BorrowerDetailsBean {

	private String portfolioName;
	private String borrowerName;
	private double sanctionAmount;
	private String loanAccountNo;
	private String sanctionDate;
	private String disbursmentDate;
	private String fileName;
	private int calculatedAmount;
	private double totalFee;
	private String rpNumber;
	private boolean selectStatus;

	public boolean isSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(boolean selectStatus) {
		this.selectStatus = selectStatus;
	}

	public String getReNumber() {
		return rpNumber;
	}

	public void setReNumber(String reNumber) {
		this.rpNumber = reNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public BorrowerDetailsBean() {
		super();
	}

	public BorrowerDetailsBean(String portfolioName, String borrowerName,
			double sanctionAmount, String loanAccountNo, String sanctionDate,
			String disbursmentDate, int calculatedAmount) {
		super();
		this.portfolioName = portfolioName;
		this.borrowerName = borrowerName;
		this.sanctionAmount = sanctionAmount;
		this.loanAccountNo = loanAccountNo;
		this.sanctionDate = sanctionDate;
		this.disbursmentDate = disbursmentDate;
		this.calculatedAmount = calculatedAmount;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public double getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(double sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public String getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getDisbursmentDate() {
		return disbursmentDate;
	}

	public void setDisbursmentDate(String disbursmentDate) {
		this.disbursmentDate = disbursmentDate;
	}

	public int getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(int calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

}
