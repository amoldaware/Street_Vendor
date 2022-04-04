package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class MLIInfo {

	@Id
	@Column(name="LONG_NAME")
	private String LONG_NAME;
	
	@Column(name="MEM_BNK_ID")
	private String MEM_BNK_ID;
	
	@Column(name="MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	
	@Column(name="MEM_BRN_ID")
	private String MEM_BRN_ID;
	

	
	
	public String getLONG_NAME() {
		return LONG_NAME;
	}
	public void setLONG_NAME(String lONG_NAME) {
		LONG_NAME = lONG_NAME;
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
