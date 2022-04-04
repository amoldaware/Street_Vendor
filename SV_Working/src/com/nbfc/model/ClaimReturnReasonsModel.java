package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="NBFC_CLAIM_RETURN_REMARK_DTLS")
public class ClaimReturnReasonsModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SI_NO")
	private String SI_NO;
	
	public String getSI_NO() {
		return SI_NO;
	}

	public void setSI_NO(String sI_NO) {
		SI_NO = sI_NO;
	}

	@Column(name = "CLAIM_REF_NO")
	private String CLAIM_REF_NO;
	
	
	@Column(name="RETURN_ID")
	private String RETURN_ID;
	
	@Column(name="CGS_CK_ID")
	private String CGS_CK_ID="";

	public String getCLAIM_REF_NO() {
		return CLAIM_REF_NO;
	}

	public void setCLAIM_REF_NO(String cLAIM_REF_NO) {
		CLAIM_REF_NO = cLAIM_REF_NO;
	}

	public String getRETURN_ID() {
		return RETURN_ID;
	}

	public void setRETURN_ID(String rETURN_ID) {
		RETURN_ID = rETURN_ID;
	}

	public String getCGS_CK_ID() {
		return CGS_CK_ID;
	}

	public void setCGS_CK_ID(String cGS_CK_ID) {
		CGS_CK_ID = cGS_CK_ID;
	}

}
