package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class DANAllocationMakerModal {

	@Id
	@Column(name = "DAN_ID")
	private String DAN_ID;
	@Column(name = "DAN_TYPE")
	private String DAN_TYPE;
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;
	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;
	@Column(name = "DAN_GENERATED_DT")
	private Date DAN_GENERATED_DT;
	@Column(name = "DAN_DUE_DT")
	private Date DAN_DUE_DT;
	@Column(name = "DAN_EXPIRY_DT")
	private Date DAN_EXPIRY_DT;
	@Column(name = "DAN_WAIVED_FLAG")
	private String DAN_WAIVED_FLAG;
	@Column(name = "DAN_STATUS")
	private String DAN_STATUS;
	@Column(name = "DAN_CREATED_MODIFIED_BY")
	private String DAN_CREATED_MODIFIED_BY;
	@Column(name = "DAN_CREATED_MODIFIED_DT")
	private String DAN_CREATED_MODIFIED_DT;

	public String getDAN_ID() {
		return DAN_ID;
	}

	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}

	public String getDAN_TYPE() {
		return DAN_TYPE;
	}

	public void setDAN_TYPE(String dAN_TYPE) {
		DAN_TYPE = dAN_TYPE;
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

	public Date getDAN_GENERATED_DT() {
		return DAN_GENERATED_DT;
	}

	public void setDAN_GENERATED_DT(Date dAN_GENERATED_DT) {
		DAN_GENERATED_DT = dAN_GENERATED_DT;
	}

	public Date getDAN_DUE_DT() {
		return DAN_DUE_DT;
	}

	public void setDAN_DUE_DT(Date dAN_DUE_DT) {
		DAN_DUE_DT = dAN_DUE_DT;
	}

	public Date getDAN_EXPIRY_DT() {
		return DAN_EXPIRY_DT;
	}

	public void setDAN_EXPIRY_DT(Date dAN_EXPIRY_DT) {
		DAN_EXPIRY_DT = dAN_EXPIRY_DT;
	}

	public String getDAN_WAIVED_FLAG() {
		return DAN_WAIVED_FLAG;
	}

	public void setDAN_WAIVED_FLAG(String dAN_WAIVED_FLAG) {
		DAN_WAIVED_FLAG = dAN_WAIVED_FLAG;
	}

	public String getDAN_STATUS() {
		return DAN_STATUS;
	}

	public void setDAN_STATUS(String dAN_STATUS) {
		DAN_STATUS = dAN_STATUS;
	}

	public String getDAN_CREATED_MODIFIED_BY() {
		return DAN_CREATED_MODIFIED_BY;
	}

	public void setDAN_CREATED_MODIFIED_BY(String dAN_CREATED_MODIFIED_BY) {
		DAN_CREATED_MODIFIED_BY = dAN_CREATED_MODIFIED_BY;
	}

	public String getDAN_CREATED_MODIFIED_DT() {
		return DAN_CREATED_MODIFIED_DT;
	}

	public void setDAN_CREATED_MODIFIED_DT(String dAN_CREATED_MODIFIED_DT) {
		DAN_CREATED_MODIFIED_DT = dAN_CREATED_MODIFIED_DT;
	}

	public DANAllocationMakerModal(String dAN_ID, String dAN_TYPE,
			String mEM_BNK_ID, String mEM_ZNE_ID, String mEM_BRN_ID,
			Date dAN_GENERATED_DT, Date dAN_DUE_DT, Date dAN_EXPIRY_DT,
			String dAN_WAIVED_FLAG, String dAN_STATUS,
			String dAN_CREATED_MODIFIED_BY, String dAN_CREATED_MODIFIED_DT) {
		super();
		DAN_ID = dAN_ID;
		DAN_TYPE = dAN_TYPE;
		MEM_BNK_ID = mEM_BNK_ID;
		MEM_ZNE_ID = mEM_ZNE_ID;
		MEM_BRN_ID = mEM_BRN_ID;
		DAN_GENERATED_DT = dAN_GENERATED_DT;
		DAN_DUE_DT = dAN_DUE_DT;
		DAN_EXPIRY_DT = dAN_EXPIRY_DT;
		DAN_WAIVED_FLAG = dAN_WAIVED_FLAG;
		DAN_STATUS = dAN_STATUS;
		DAN_CREATED_MODIFIED_BY = dAN_CREATED_MODIFIED_BY;
		DAN_CREATED_MODIFIED_DT = dAN_CREATED_MODIFIED_DT;
	}

	public DANAllocationMakerModal() {
		super();
	}

	@Override
	public String toString() {
		return "DANAllocationMakerModal [DAN_ID=" + DAN_ID + ", DAN_TYPE="
				+ DAN_TYPE + ", MEM_BNK_ID=" + MEM_BNK_ID + ", MEM_ZNE_ID="
				+ MEM_ZNE_ID + ", MEM_BRN_ID=" + MEM_BRN_ID
				+ ", DAN_GENERATED_DT=" + DAN_GENERATED_DT + ", DAN_DUE_DT="
				+ DAN_DUE_DT + ", DAN_EXPIRY_DT=" + DAN_EXPIRY_DT
				+ ", DAN_WAIVED_FLAG=" + DAN_WAIVED_FLAG + ", DAN_STATUS="
				+ DAN_STATUS + ", DAN_CREATED_MODIFIED_BY="
				+ DAN_CREATED_MODIFIED_BY + ", DAN_CREATED_MODIFIED_DT="
				+ DAN_CREATED_MODIFIED_DT + "]";
	}

}
