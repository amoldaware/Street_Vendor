package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_ASF_DETAIL")
public class ASFDetailModel {

	@Id
	@Column(name = "CGPAN")
	private String CGPAN;
	@Column(name = "GA_DAN_ID")
	private String GA_DAN_ID;
	@Column(name = "ASF_DAN_ID")
	private String ASF_DAN_ID;
	public String getCGPAN() {
		return CGPAN;
	}
	public void setCGPAN(String cGPAN) {
		CGPAN = cGPAN;
	}
	public String getGA_DAN_ID() {
		return GA_DAN_ID;
	}
	public void setGA_DAN_ID(String gA_DAN_ID) {
		GA_DAN_ID = gA_DAN_ID;
	}
	public String getASF_DAN_ID() {
		return ASF_DAN_ID;
	}
	public void setASF_DAN_ID(String aSF_DAN_ID) {
		ASF_DAN_ID = aSF_DAN_ID;
	}
	
	

	
	
	

}
