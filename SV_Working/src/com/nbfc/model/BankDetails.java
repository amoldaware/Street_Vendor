package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class BankDetails {

	@Id
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;
	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;
	@Column(name="LONG_NAME")
	private String longName;
	@Column(name="SHORT_NAME")
	private String shortName;
	@Column(name="RBI_REGISTRATION_NO")
	private String RBI_REGISTRATION_NO;
	@Column(name="COMPANY_CIN")
	private String COMPANY_CIN;
	@Column(name="COMPANY_PAN")
	private String COMPANY_PAN;
	@Column(name="GSTIN_NO")
	private String GSTIN_NO;

	

	
	@Override
	public String toString() {
		return "BankDetails [MEM_BNK_ID=" + MEM_BNK_ID + ", MEM_ZNE_ID="
				+ MEM_ZNE_ID + ", MEM_BRN_ID=" + MEM_BRN_ID + ", longName="
				+ longName + ", shortName=" + shortName
				+ ", RBI_REGISTRATION_NO=" + RBI_REGISTRATION_NO
				+ ", COMPANY_CIN=" + COMPANY_CIN + ", COMPANY_PAN="
				+ COMPANY_PAN + ", GSTIN_NO=" + GSTIN_NO + "]";
	}

	public String getRBI_REGISTRATION_NO() {
		return RBI_REGISTRATION_NO;
	}

	public void setRBI_REGISTRATION_NO(String rBI_REGISTRATION_NO) {
		RBI_REGISTRATION_NO = rBI_REGISTRATION_NO;
	}

	public String getCOMPANY_CIN() {
		return COMPANY_CIN;
	}

	public void setCOMPANY_CIN(String cOMPANY_CIN) {
		COMPANY_CIN = cOMPANY_CIN;
	}

	public String getCOMPANY_PAN() {
		return COMPANY_PAN;
	}

	public void setCOMPANY_PAN(String cOMPANY_PAN) {
		COMPANY_PAN = cOMPANY_PAN;
	}

	public String getGSTIN_NO() {
		return GSTIN_NO;
	}

	public void setGSTIN_NO(String gSTIN_NO) {
		GSTIN_NO = gSTIN_NO;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

}
