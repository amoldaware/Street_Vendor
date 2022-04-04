package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class MLIExposureIDDetails {
	


	@Id
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;
	@Column(name = "EXPOSURE_ID")
	private String EXPOSURE_ID;
	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;
	@Column(name = "STATUS")
	private String STATUS;

	@Override
	public String toString() {
		return "MLIExposureId [EXPOSURE_ID=" + EXPOSURE_ID + ", MEM_BNK_ID="
				+ MEM_BNK_ID + ", MEM_ZNE_ID=" + MEM_ZNE_ID + ", MEM_BRN_ID="
				+ MEM_BRN_ID + ", STATUS=" + STATUS + "]";
	}

	public String getEXPOSURE_ID() {
		return EXPOSURE_ID;
	}

	public void setEXPOSURE_ID(String eXPOSURE_ID) {
		EXPOSURE_ID = eXPOSURE_ID;
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

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}



}
