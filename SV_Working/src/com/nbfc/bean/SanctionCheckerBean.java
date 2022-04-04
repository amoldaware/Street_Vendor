

package com.nbfc.bean;

import java.util.Date;

public class SanctionCheckerBean {
	 String id;
	 public String getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(String portfolioNo) {
		this.portfolioNo = portfolioNo;
	}
	 public String getModifySanctionAmount() {
			return modifySanctionAmount;
		}

		public void setModifySanctionAmount(String modifySanctionAmount) {
			this.modifySanctionAmount = modifySanctionAmount;
		}

		String modifySanctionAmount;
	 String portfolioNo;
	 String unit;
	 public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	String loanAccountNumber;
	 String dateOfDisbursement;
	 String amountOfDisbursement;
	 String whetherDisbursed;

	

	@Override
	public String toString() {
		return  portfolioNo ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public String getDateOfDisbursement() {
		return dateOfDisbursement;
	}

	public void setDateOfDisbursement(String dateOfDisbursement) {
		this.dateOfDisbursement = dateOfDisbursement;
	}

	public String getAmountOfDisbursement() {
		return amountOfDisbursement;
	}

	public void setAmountOfDisbursement(String amountOfDisbursement) {
		this.amountOfDisbursement = amountOfDisbursement;
	}

	/*public String getWhetherDisbursed() {
		return whetherDisbursed;
	}

	public void setWhetherDisbursed(String whetherDisbursed) {
		this.whetherDisbursed = whetherDisbursed;
	}*/

}
