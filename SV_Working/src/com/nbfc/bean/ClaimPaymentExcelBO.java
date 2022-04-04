package com.nbfc.bean;

import java.io.Serializable;

public class ClaimPaymentExcelBO implements Serializable {
	private static final long serialVersionUID = -7378430040102490693L;
	private String serialNo;
	private String memberId;
	private String netPayable;
	private String transferACNo;
	private String ifscCode;
	private String acNoOfBeneficiary;
	private String acType;
	private String nameOfBeneficiary;
	private String beneficiaryBnkName;
	private String remark;
	private String originatingBank;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getNetPayable() {
		return netPayable;
	}

	public void setNetPayable(String netPayable) {
		this.netPayable = netPayable;
	}

	public String getTransferACNo() {
		return transferACNo;
	}

	public void setTransferACNo(String transferACNo) {
		this.transferACNo = transferACNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAcNoOfBeneficiary() {
		return acNoOfBeneficiary;
	}

	public void setAcNoOfBeneficiary(String acNoOfBeneficiary) {
		this.acNoOfBeneficiary = acNoOfBeneficiary;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getNameOfBeneficiary() {
		return nameOfBeneficiary;
	}

	public void setNameOfBeneficiary(String nameOfBeneficiary) {
		this.nameOfBeneficiary = nameOfBeneficiary;
	}

	public String getBeneficiaryBnkName() {
		return beneficiaryBnkName;
	}

	public void setBeneficiaryBnkName(String beneficiaryBnkName) {
		this.beneficiaryBnkName = beneficiaryBnkName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOriginatingBank() {
		return originatingBank;
	}

	public void setOriginatingBank(String originatingBank) {
		this.originatingBank = originatingBank;
	}

}
