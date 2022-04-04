package com.nbfc.model;



import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="NBFC_MEMBER_ACCOUNT_INFO_BLOB")

public class ClaimBankMandateBlobModel {


	@Id
	@Column(name="MEM_ID")
	private String MEM_ID;

	@Column(name="LEGAL_DOCUMENT")
	@Lob
	private Blob LEGAL_DOCUMENT;
	
	@Column(name="MEM_STATUS")
	private String MEM_STATUS;

	@Column(name="NBFC_MK_USER_ID")
	private String NBFC_MK_USER_ID;
	
	@Column(name="NBFC_MK_DATE")
	private Date NBFC_MK_DATE;
	
	@Column(name="NBFC_CK_USER_ID")
	private String NBFC_CK_USER_ID;
	
	@Column(name="NBFC_CK__DATE")
	private Date NBFC_CK__DATE;
	
	@Column(name="CGTMSE_CK_USER_ID")
	private String CGTMSE_CK_USER_ID;
	
	@Column(name="CGTMSE_CK_DATE")
	private Date CGTMSE_CK_DATE;
	
	@Column(name="INSERTEDON")
	private Date INSERTEDON;
	
	
	
	
	public String getMEM_STATUS() {
		return MEM_STATUS;
	}

	public void setMEM_STATUS(String mEM_STATUS) {
		MEM_STATUS = mEM_STATUS;
	}
	public String getMEM_ID() {
		return MEM_ID;
	}

	public void setMEM_ID(String mEM_ID) {
		MEM_ID = mEM_ID;
	}

	public Blob getLEGAL_DOCUMENT() {
		return LEGAL_DOCUMENT;
	}

	public void setLEGAL_DOCUMENT(Blob lEGAL_DOCUMENT) {
		LEGAL_DOCUMENT = lEGAL_DOCUMENT;
	}

	public String getNBFC_MK_USER_ID() {
		return NBFC_MK_USER_ID;
	}

	public void setNBFC_MK_USER_ID(String nBFC_MK_USER_ID) {
		NBFC_MK_USER_ID = nBFC_MK_USER_ID;
	}

	public Date getNBFC_MK_DATE() {
		return NBFC_MK_DATE;
	}

	public void setNBFC_MK_DATE(Date nBFC_MK_DATE) {
		NBFC_MK_DATE = nBFC_MK_DATE;
	}

	public String getNBFC_CK_USER_ID() {
		return NBFC_CK_USER_ID;
	}

	public void setNBFC_CK_USER_ID(String nBFC_CK_USER_ID) {
		NBFC_CK_USER_ID = nBFC_CK_USER_ID;
	}

	public Date getNBFC_CK__DATE() {
		return NBFC_CK__DATE;
	}

	public void setNBFC_CK__DATE(Date nBFC_CK__DATE) {
		NBFC_CK__DATE = nBFC_CK__DATE;
	}

	public String getCGTMSE_CK_USER_ID() {
		return CGTMSE_CK_USER_ID;
	}

	public void setCGTMSE_CK_USER_ID(String cGTMSE_CK_USER_ID) {
		CGTMSE_CK_USER_ID = cGTMSE_CK_USER_ID;
	}

	public Date getCGTMSE_CK_DATE() {
		return CGTMSE_CK_DATE;
	}

	public void setCGTMSE_CK_DATE(Date cGTMSE_CK_DATE) {
		CGTMSE_CK_DATE = cGTMSE_CK_DATE;
	}

	public Date getINSERTEDON() {
		return INSERTEDON;
	}

	public void setINSERTEDON(Date iNSERTEDON) {
		INSERTEDON = iNSERTEDON;
	}
	
	

}
