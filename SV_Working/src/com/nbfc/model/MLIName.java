package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Saurav Tyagi 2017
 * 
 */
@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class MLIName implements Serializable{
	

	@Id
	@Column(name = "MEM_BNK_ID")
	private String bnkId;
	@Column(name = "MEM_ZNE_ID")
	private String zneID;
	@Column(name = "MEM_BRN_ID")
	private String brnName;
	@Column(name = "LONG_NAME")
	private String mliLName;
	@Column(name = "SHORT_NAME")
	private String mliSName;

	
	public String getBnkId() {
		return bnkId;
	}
	public void setBnkId(String bnkId) {
		this.bnkId = bnkId;
	}
	public String getZneID() {
		return zneID;
	}
	public void setZneID(String zneID) {
		this.zneID = zneID;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getMliLName() {
		return mliLName;
	}
	public void setMliLName(String mliLName) {
		this.mliLName = mliLName;
	}
	public String getMliSName() {
		return mliSName;
	}
	public void setMliSName(String mliSName) {
		this.mliSName = mliSName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = -723583058586873479L;

}
