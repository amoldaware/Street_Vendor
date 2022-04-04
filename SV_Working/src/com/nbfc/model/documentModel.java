package com.nbfc.model;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="NBFC_CLAIM_LODGE_BLOB")
public class documentModel {

	

	@Id
	@GeneratedValue
	@Column(name="CLAIM_REF_NO")
	private String CLAIM_REF_NO;

	   @Lob @Basic(fetch = FetchType.LAZY)
	    @Column(name="LEGAL_DOCUMENT", nullable=false)
	    private byte[] LEGAL_DOCUMENT;


	
//	@Column(name="LEGAL_DOCUMENT")
//	@Lob
//	private Blob LEGAL_DOCUMENT;

	
	public String getCLAIM_REF_NO() {
		return CLAIM_REF_NO;
	}

	public byte[] getLEGAL_DOCUMENT() {
		return LEGAL_DOCUMENT;
	}

	public void setLEGAL_DOCUMENT(byte[] lEGAL_DOCUMENT) {
		LEGAL_DOCUMENT = lEGAL_DOCUMENT;
	}

	public void setCLAIM_REF_NO(String cLAIM_REF_NO) {
		CLAIM_REF_NO = cLAIM_REF_NO;
	}

//	public Blob getLEGAL_DOCUMENT() {
//		return LEGAL_DOCUMENT;
//	}
//
//	public void setLEGAL_DOCUMENT(Blob lEGAL_DOCUMENT) {
//		LEGAL_DOCUMENT = lEGAL_DOCUMENT;
//	}
	
}
