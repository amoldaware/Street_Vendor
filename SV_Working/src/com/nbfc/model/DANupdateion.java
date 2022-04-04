package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NBFC_DAN_CGPAN_INFO")
public class DANupdateion {
	


	@Id
	@Column(name = "DAN_ID")
	private String DAN_ID;
	@Column(name = "CGPAN")
	private String CGPAN;
	@Column(name = "DCI_NEW_DAN_ID")
	private String DCI_NEW_DAN_ID;
	@Column(name = "DCI_AMOUNT_RAISED")
	private Integer DCI_AMOUNT_RAISED;
	@Column(name = "DCI_AMOUNT_CANCELLED")
	private Integer DCI_AMOUNT_CANCELLED;
	@Column(name = "DCI_DUE_DATE")
	private Date DCI_DUE_DATE;
	@Column(name = "PAY_ID")
	private String PAY_ID;
	@Column(name = "DCI_GUARANTEE_START_DT")
	private Date DCI_GUARANTEE_START_DT;
	@Column(name = "DCI_PENALTY")
	private Integer DCI_PENALTY;
	@Column(name = "DCI_APPROPRIATION_FLAG")
	private String DCI_APPROPRIATION_FLAG;
	@Column(name = "DCI_APPROPRIATION_BY")
	private String DCI_APPROPRIATION_BY;
	@Column(name = "DCI_APPROPRIATION_DT")
	private Date DCI_APPROPRIATION_DT;

	@Column(name = "DCI_ALLOCATION_FLAG")
	private String DCI_ALLOCATION_FLAG;
	@Column(name = "DCI_ALLOCATION_BY")
	private String DCI_ALLOCATION_BY;
	@Column(name = "DCI_ALLOCATION_DT")
	private Date DCI_ALLOCATION_DT;
	@Column(name = "DCI_CLAIM_RECOVERY_AMT")
	private Integer DCI_CLAIM_RECOVERY_AMT;
	@Column(name = "DCI_ALLOCATION_VALID_FLAG")
	private String DCI_ALLOCATION_VALID_FLAG;
	@Column(name = "DCI_REMARKS")
	private String DCI_REMARKS;
	@Column(name = "EST_FEE_AMT")
	private Integer EST_FEE_AMT;
	@Column(name = "DCI_STAX_AMT")
	private Integer DCI_STAX_AMT;
	@Column(name = "DCI_ECESS_AMT")
	private Integer DCI_ECESS_AMT;
	@Column(name = "DCI_HECESS_AMT")
	private Integer DCI_HECESS_AMT;
	@Column(name = "DCI_BASE_AMT")
	private Integer DCI_BASE_AMT;
	@Column(name = "DCI_SWBHCESS_AMT")
	private Integer DCI_SWBHCESS_AMT;
	@Column(name = "DCI_STANDARD_RATE")
	private Integer DCI_STANDARD_RATE;
	@Column(name = "NPA_RISK_RATE")
	private Integer NPA_RISK_RATE;
	@Column(name = "CLAIM_RISK_RATE")
	private Integer CLAIM_RISK_RATE;
	@Column(name = "FINAL_RATE")
	private Integer FINAL_RATE;
	@Column(name = "DCI_KKALYANCESS_AMT")
	private Integer DCI_KKALYANCESS_AMT;
	@Column(name = "IGST_RATE")
	private Integer IGST_RATE;

	@Column(name = "IGST_AMT")
	private Integer IGST_AMT;
	@Column(name = "CGST_RATE")
	private Integer CGST_RATE;
	@Column(name = "CGST_AMT")
	private String CGST_AMT;
	@Column(name = "SGST_RATE")
	private Integer SGST_RATE;
	@Column(name = "SGST_AMT")
	private Integer SGST_AMT;
	@Column(name = "APPROVAL_STATUS")
	private String APPROVAL_STATUS;
	@Column(name = "REMARK")
	private String REMARK;
	public String getDAN_ID() {
		return DAN_ID;
	}
	public void setDAN_ID(String dAN_ID) {
		DAN_ID = dAN_ID;
	}
	public String getCGPAN() {
		return CGPAN;
	}
	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}
	public String getDCI_NEW_DAN_ID() {
		return DCI_NEW_DAN_ID;
	}
	public void setDCI_NEW_DAN_ID(String dCI_NEW_DAN_ID) {
		DCI_NEW_DAN_ID = dCI_NEW_DAN_ID;
	}
	public Integer getDCI_AMOUNT_RAISED() {
		return DCI_AMOUNT_RAISED;
	}
	public void setDCI_AMOUNT_RAISED(Integer dCI_AMOUNT_RAISED) {
		DCI_AMOUNT_RAISED = dCI_AMOUNT_RAISED;
	}
	public Integer getDCI_AMOUNT_CANCELLED() {
		return DCI_AMOUNT_CANCELLED;
	}
	public void setDCI_AMOUNT_CANCELLED(Integer dCI_AMOUNT_CANCELLED) {
		DCI_AMOUNT_CANCELLED = dCI_AMOUNT_CANCELLED;
	}
	public Date getDCI_DUE_DATE() {
		return DCI_DUE_DATE;
	}
	public void setDCI_DUE_DATE(Date dCI_DUE_DATE) {
		DCI_DUE_DATE = dCI_DUE_DATE;
	}
	public String getPAY_ID() {
		return PAY_ID;
	}
	public void setPAY_ID(String pAY_ID) {
		PAY_ID = pAY_ID;
	}
	public Date getDCI_GUARANTEE_START_DT() {
		return DCI_GUARANTEE_START_DT;
	}
	public void setDCI_GUARANTEE_START_DT(Date dCI_GUARANTEE_START_DT) {
		DCI_GUARANTEE_START_DT = dCI_GUARANTEE_START_DT;
	}
	public Integer getDCI_PENALTY() {
		return DCI_PENALTY;
	}
	public void setDCI_PENALTY(Integer dCI_PENALTY) {
		DCI_PENALTY = dCI_PENALTY;
	}
	public String getDCI_APPROPRIATION_FLAG() {
		return DCI_APPROPRIATION_FLAG;
	}
	public void setDCI_APPROPRIATION_FLAG(String dCI_APPROPRIATION_FLAG) {
		DCI_APPROPRIATION_FLAG = dCI_APPROPRIATION_FLAG;
	}
	public String getDCI_APPROPRIATION_BY() {
		return DCI_APPROPRIATION_BY;
	}
	public void setDCI_APPROPRIATION_BY(String dCI_APPROPRIATION_BY) {
		DCI_APPROPRIATION_BY = dCI_APPROPRIATION_BY;
	}
	public Date getDCI_APPROPRIATION_DT() {
		return DCI_APPROPRIATION_DT;
	}
	public void setDCI_APPROPRIATION_DT(Date dCI_APPROPRIATION_DT) {
		DCI_APPROPRIATION_DT = dCI_APPROPRIATION_DT;
	}
	public String getDCI_ALLOCATION_FLAG() {
		return DCI_ALLOCATION_FLAG;
	}
	public void setDCI_ALLOCATION_FLAG(String dCI_ALLOCATION_FLAG) {
		DCI_ALLOCATION_FLAG = dCI_ALLOCATION_FLAG;
	}
	public String getDCI_ALLOCATION_BY() {
		return DCI_ALLOCATION_BY;
	}
	public void setDCI_ALLOCATION_BY(String dCI_ALLOCATION_BY) {
		DCI_ALLOCATION_BY = dCI_ALLOCATION_BY;
	}
	public Date getDCI_ALLOCATION_DT() {
		return DCI_ALLOCATION_DT;
	}
	public void setDCI_ALLOCATION_DT(Date dCI_ALLOCATION_DT) {
		DCI_ALLOCATION_DT = dCI_ALLOCATION_DT;
	}
	public Integer getDCI_CLAIM_RECOVERY_AMT() {
		return DCI_CLAIM_RECOVERY_AMT;
	}
	public void setDCI_CLAIM_RECOVERY_AMT(Integer dCI_CLAIM_RECOVERY_AMT) {
		DCI_CLAIM_RECOVERY_AMT = dCI_CLAIM_RECOVERY_AMT;
	}
	public String getDCI_ALLOCATION_VALID_FLAG() {
		return DCI_ALLOCATION_VALID_FLAG;
	}
	public void setDCI_ALLOCATION_VALID_FLAG(String dCI_ALLOCATION_VALID_FLAG) {
		DCI_ALLOCATION_VALID_FLAG = dCI_ALLOCATION_VALID_FLAG;
	}
	public String getDCI_REMARKS() {
		return DCI_REMARKS;
	}
	public void setDCI_REMARKS(String dCI_REMARKS) {
		DCI_REMARKS = dCI_REMARKS;
	}
	public Integer getEST_FEE_AMT() {
		return EST_FEE_AMT;
	}
	public void setEST_FEE_AMT(Integer eST_FEE_AMT) {
		EST_FEE_AMT = eST_FEE_AMT;
	}
	public Integer getDCI_STAX_AMT() {
		return DCI_STAX_AMT;
	}
	public void setDCI_STAX_AMT(Integer dCI_STAX_AMT) {
		DCI_STAX_AMT = dCI_STAX_AMT;
	}
	public Integer getDCI_ECESS_AMT() {
		return DCI_ECESS_AMT;
	}
	public void setDCI_ECESS_AMT(Integer dCI_ECESS_AMT) {
		DCI_ECESS_AMT = dCI_ECESS_AMT;
	}
	public Integer getDCI_HECESS_AMT() {
		return DCI_HECESS_AMT;
	}
	public void setDCI_HECESS_AMT(Integer dCI_HECESS_AMT) {
		DCI_HECESS_AMT = dCI_HECESS_AMT;
	}
	public Integer getDCI_BASE_AMT() {
		return DCI_BASE_AMT;
	}
	public void setDCI_BASE_AMT(Integer dCI_BASE_AMT) {
		DCI_BASE_AMT = dCI_BASE_AMT;
	}
	public Integer getDCI_SWBHCESS_AMT() {
		return DCI_SWBHCESS_AMT;
	}
	public void setDCI_SWBHCESS_AMT(Integer dCI_SWBHCESS_AMT) {
		DCI_SWBHCESS_AMT = dCI_SWBHCESS_AMT;
	}
	public Integer getDCI_STANDARD_RATE() {
		return DCI_STANDARD_RATE;
	}
	public void setDCI_STANDARD_RATE(Integer dCI_STANDARD_RATE) {
		DCI_STANDARD_RATE = dCI_STANDARD_RATE;
	}
	public Integer getNPA_RISK_RATE() {
		return NPA_RISK_RATE;
	}
	public void setNPA_RISK_RATE(Integer nPA_RISK_RATE) {
		NPA_RISK_RATE = nPA_RISK_RATE;
	}
	public Integer getCLAIM_RISK_RATE() {
		return CLAIM_RISK_RATE;
	}
	public void setCLAIM_RISK_RATE(Integer cLAIM_RISK_RATE) {
		CLAIM_RISK_RATE = cLAIM_RISK_RATE;
	}
	public Integer getFINAL_RATE() {
		return FINAL_RATE;
	}
	public void setFINAL_RATE(Integer fINAL_RATE) {
		FINAL_RATE = fINAL_RATE;
	}
	public Integer getDCI_KKALYANCESS_AMT() {
		return DCI_KKALYANCESS_AMT;
	}
	public void setDCI_KKALYANCESS_AMT(Integer dCI_KKALYANCESS_AMT) {
		DCI_KKALYANCESS_AMT = dCI_KKALYANCESS_AMT;
	}
	public Integer getIGST_RATE() {
		return IGST_RATE;
	}
	public void setIGST_RATE(Integer iGST_RATE) {
		IGST_RATE = iGST_RATE;
	}
	public Integer getIGST_AMT() {
		return IGST_AMT;
	}
	public void setIGST_AMT(Integer iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}
	public Integer getCGST_RATE() {
		return CGST_RATE;
	}
	public void setCGST_RATE(Integer cGST_RATE) {
		CGST_RATE = cGST_RATE;
	}
	public String getCGST_AMT() {
		return CGST_AMT;
	}
	public void setCGST_AMT(String cGST_AMT) {
		CGST_AMT = cGST_AMT;
	}
	public Integer getSGST_RATE() {
		return SGST_RATE;
	}
	public void setSGST_RATE(Integer sGST_RATE) {
		SGST_RATE = sGST_RATE;
	}
	public Integer getSGST_AMT() {
		return SGST_AMT;
	}
	public void setSGST_AMT(Integer sGST_AMT) {
		SGST_AMT = sGST_AMT;
	}
	public String getAPPROVAL_STATUS() {
		return APPROVAL_STATUS;
	}
	public void setAPPROVAL_STATUS(String aPPROVAL_STATUS) {
		APPROVAL_STATUS = aPPROVAL_STATUS;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	


}
