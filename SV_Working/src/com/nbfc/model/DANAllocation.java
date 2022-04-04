package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_DEMAND_ADVICE_INFO")
public class DANAllocation {

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
	private Date DAN_CREATED_MODIFIED_DT;
	@Column(name = "IS_CGTMSE_PROCESSED")
	private String IS_CGTMSE_PROCESSED;
	@Column(name = "IS_CGTMSE_PROCESSED_DATE")
	private String IS_CGTMSE_PROCESSED_DATE;
	@Column(name = "DAN_FSTATUS")
	private String DAN_FSTATUS;
	@Column(name = "NBFC_MAKER_ID")
	private String NBFC_MAKER_ID;
	@Column(name = "NBFC_MAKER_DATE")
	private String NBFC_MAKER_DATE;
	@Column(name = "NBFC_CHECKER_ID")
	private String NBFC_CHECKER_ID;
	@Column(name = "NBFC_CHECKER_DATE")
	private Date NBFC_CHECKER_DATE;

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

	public Date getDAN_CREATED_MODIFIED_DT() {
		return DAN_CREATED_MODIFIED_DT;
	}

	public void setDAN_CREATED_MODIFIED_DT(Date dAN_CREATED_MODIFIED_DT) {
		DAN_CREATED_MODIFIED_DT = dAN_CREATED_MODIFIED_DT;
	}

	public String getIS_CGTMSE_PROCESSED() {
		return IS_CGTMSE_PROCESSED;
	}

	public void setIS_CGTMSE_PROCESSED(String iS_CGTMSE_PROCESSED) {
		IS_CGTMSE_PROCESSED = iS_CGTMSE_PROCESSED;
	}

	public String getIS_CGTMSE_PROCESSED_DATE() {
		return IS_CGTMSE_PROCESSED_DATE;
	}

	public void setIS_CGTMSE_PROCESSED_DATE(String iS_CGTMSE_PROCESSED_DATE) {
		IS_CGTMSE_PROCESSED_DATE = iS_CGTMSE_PROCESSED_DATE;
	}

	public String getDAN_FSTATUS() {
		return DAN_FSTATUS;
	}

	public void setDAN_FSTATUS(String dAN_FSTATUS) {
		DAN_FSTATUS = dAN_FSTATUS;
	}

	public String getNBFC_MAKER_ID() {
		return NBFC_MAKER_ID;
	}

	public void setNBFC_MAKER_ID(String nBFC_MAKER_ID) {
		NBFC_MAKER_ID = nBFC_MAKER_ID;
	}

	public String getNBFC_MAKER_DATE() {
		return NBFC_MAKER_DATE;
	}

	public void setNBFC_MAKER_DATE(String nBFC_MAKER_DATE) {
		NBFC_MAKER_DATE = nBFC_MAKER_DATE;
	}

	public String getNBFC_CHECKER_ID() {
		return NBFC_CHECKER_ID;
	}

	public void setNBFC_CHECKER_ID(String nBFC_CHECKER_ID) {
		NBFC_CHECKER_ID = nBFC_CHECKER_ID;
	}

	public Date getNBFC_CHECKER_DATE() {
		return NBFC_CHECKER_DATE;
	}

	public void setNBFC_CHECKER_DATE(Date nBFC_CHECKER_DATE) {
		NBFC_CHECKER_DATE = nBFC_CHECKER_DATE;
	}

	public DANAllocation() {
		super();
	}

	public DANAllocation(String dAN_ID, String dAN_TYPE, String mEM_BNK_ID,
			String mEM_ZNE_ID, String mEM_BRN_ID, Date dAN_GENERATED_DT,
			Date dAN_DUE_DT, Date dAN_EXPIRY_DT, String dAN_WAIVED_FLAG,
			String dAN_STATUS, String dAN_CREATED_MODIFIED_BY,
			Date dAN_CREATED_MODIFIED_DT, String iS_CGTMSE_PROCESSED,
			String iS_CGTMSE_PROCESSED_DATE, String dAN_FSTATUS,
			String nBFC_MAKER_ID, String nBFC_MAKER_DATE,
			String nBFC_CHECKER_ID, Date nBFC_CHECKER_DATE) {
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
		IS_CGTMSE_PROCESSED = iS_CGTMSE_PROCESSED;
		IS_CGTMSE_PROCESSED_DATE = iS_CGTMSE_PROCESSED_DATE;
		DAN_FSTATUS = dAN_FSTATUS;
		NBFC_MAKER_ID = nBFC_MAKER_ID;
		NBFC_MAKER_DATE = nBFC_MAKER_DATE;
		NBFC_CHECKER_ID = nBFC_CHECKER_ID;
		NBFC_CHECKER_DATE = nBFC_CHECKER_DATE;
	}

	@Override
	public String toString() {
		return "DANAllocation [DAN_ID=" + DAN_ID + ", DAN_TYPE=" + DAN_TYPE
				+ ", MEM_BNK_ID=" + MEM_BNK_ID + ", MEM_ZNE_ID=" + MEM_ZNE_ID
				+ ", MEM_BRN_ID=" + MEM_BRN_ID + ", DAN_GENERATED_DT="
				+ DAN_GENERATED_DT + ", DAN_DUE_DT=" + DAN_DUE_DT
				+ ", DAN_EXPIRY_DT=" + DAN_EXPIRY_DT + ", DAN_WAIVED_FLAG="
				+ DAN_WAIVED_FLAG + ", DAN_STATUS=" + DAN_STATUS
				+ ", DAN_CREATED_MODIFIED_BY=" + DAN_CREATED_MODIFIED_BY
				+ ", DAN_CREATED_MODIFIED_DT=" + DAN_CREATED_MODIFIED_DT
				+ ", IS_CGTMSE_PROCESSED=" + IS_CGTMSE_PROCESSED
				+ ", IS_CGTMSE_PROCESSED_DATE=" + IS_CGTMSE_PROCESSED_DATE
				+ ", DAN_FSTATUS=" + DAN_FSTATUS + ", NBFC_MAKER_ID="
				+ NBFC_MAKER_ID + ", NBFC_MAKER_DATE=" + NBFC_MAKER_DATE
				+ ", NBFC_CHECKER_ID=" + NBFC_CHECKER_ID
				+ ", NBFC_CHECKER_DATE=" + NBFC_CHECKER_DATE + "]";
	}
	

}
