package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class BankAccountDetailsBean 
{
	@Column(name="bankName" , length=200)
    private String bankName; 
	@Column(name="accountNo" , length=200)
    private String accountNo;
	@Column(name="branchName" , length=200)
    private String branchName;
	@Column(name="ifsc" , length=200)
    private String ifsc;
	
	public String getBankName() {
		return bankName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
}
