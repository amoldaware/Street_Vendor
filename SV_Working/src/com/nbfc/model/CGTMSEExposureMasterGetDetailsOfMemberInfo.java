package com.nbfc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class CGTMSEExposureMasterGetDetailsOfMemberInfo implements Serializable{
	 @Id
	 @Column(name = "MEM_BNK_ID")
	  private String memBnkId;
	
	 @Column(name = "MEM_ZNE_ID")
	  private String memZneId;
	 
	 @Column(name = "MEM_BRN_ID")
	  private String memBrnId;
	 
	 @Column(name = "LONG_NAME")
	  private String mliLongName;
	 
	 @Column(name = "STATUS")
	  private String status;
	 
	 @Column(name = "SHORT_NAME")
	 private String mliShortName;

	public String getMemBnkId() {
		return memBnkId;
	}

	public void setMemBnkId(String memBnkId) {
		this.memBnkId = memBnkId;
	}

	public String getMemZneId() {
		return memZneId;
	}

	public void setMemZneId(String memZneId) {
		this.memZneId = memZneId;
	}

	public String getMemBrnId() {
		return memBrnId;
	}

	public void setMemBrnId(String memBrnId) {
		this.memBrnId = memBrnId;
	}

	public String getMliLongName() {
		return mliLongName;
	}

	public void setMliLongName(String mliLongName) {
		this.mliLongName = mliLongName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMliShortName() {
		return mliShortName;
	}

	public void setMliShortName(String mliShortName) {
		this.mliShortName = mliShortName;
	}

	

}
