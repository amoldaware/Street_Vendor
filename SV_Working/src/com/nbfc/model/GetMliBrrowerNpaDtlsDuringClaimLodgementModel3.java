package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "NBFC_NPA_DETAIL")
public class GetMliBrrowerNpaDtlsDuringClaimLodgementModel3 implements Serializable{
	 
	 @Column(name = "LATEST_OS_AMT")
	 private Double latestOsAmt;
	 
	 @Column(name = "NPA_REASONS_TURNING_NPA")
	 private String npaReasionTurningNpa;
	 @Id
	 @Column(name = "CGPAN")
	 private String cgpen;
	 
	 
	public Double getLatestOsAmt() {
		return latestOsAmt;
	}

	public void setLatestOsAmt(Double latestOsAmt) {
		this.latestOsAmt = latestOsAmt;
	}

	public String getNpaReasionTurningNpa() {
		return npaReasionTurningNpa;
	}

	public void setNpaReasionTurningNpa(String npaReasionTurningNpa) {
		this.npaReasionTurningNpa = npaReasionTurningNpa;
	}

	public String getCgpen() {
		return cgpen;
	}

	public void setCgpen(String cgpen) {
		this.cgpen = cgpen;
	}
	 
	 
	
}
