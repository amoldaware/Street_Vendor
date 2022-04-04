package com.nbfc.model;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="NBFC_CLAIM_LODGE_BLOB")
public class ClaimLodgeBlobModel{
	@Id
	@Column(name="CLAIM_REF_NO")
	private String CLAIM_REF_NO;
	
	@Column(name="CGPAN")
	private String CGPAN;

	@Column(name="LEGAL_DOCUMENT")
	@Lob
	private Blob LEGAL_DOCUMENT;

	@Column(name="NBFC_MAKER_ID")
	private String NBFC_MAKER_ID;
	
	@Column(name="NBFC_MAKER_DATE")
	private Date NBFC_MAKER_DATE;
	
	@Column(name="INSERTEDON")
	private Date INSERTEDON;

	public String getCLAIM_REF_NO() {
		return CLAIM_REF_NO;
	}

	public void setCLAIM_REF_NO(String cLAIM_REF_NO) {
		CLAIM_REF_NO = cLAIM_REF_NO;
	}

	public String getCGPAN() {
		return CGPAN;
	}

	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}

	public Blob getLEGAL_DOCUMENT() {
		return LEGAL_DOCUMENT;
	}

	public void setLEGAL_DOCUMENT(Blob lEGAL_DOCUMENT) {
		LEGAL_DOCUMENT = lEGAL_DOCUMENT;
	}

	public String getNBFC_MAKER_ID() {
		return NBFC_MAKER_ID;
	}

	public void setNBFC_MAKER_ID(String nBFC_MAKER_ID) {
		NBFC_MAKER_ID = nBFC_MAKER_ID;
	}

	public Date getNBFC_MAKER_DATE() {
		return NBFC_MAKER_DATE;
	}

	public void setNBFC_MAKER_DATE(Date nBFC_MAKER_DATE) {
		NBFC_MAKER_DATE = nBFC_MAKER_DATE;
	}

	public Date getINSERTEDON() {
		return INSERTEDON;
	}

	public void setINSERTEDON(Date iNSERTEDON) {
		INSERTEDON = iNSERTEDON;
	}
	
	
	


	
	
}
