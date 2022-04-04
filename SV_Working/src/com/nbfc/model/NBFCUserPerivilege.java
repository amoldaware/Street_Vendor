package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TableGenerator;
@Entity
@Table(name = "nbfc_user_privilege")
public class NBFCUserPerivilege implements Serializable{
	
	@Id
	@Column(name = "SNO" )
	Integer serialNo;
	
	@Column(name = "USR_ID" )
	String usrId;
	
	@Column(name = "PRV_ID" )
	Integer prvId;
	
	@Column(name = "UPR_FLAG" )
	String uprFlag;

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	
	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public Integer getPrvId() {
		return prvId;
	}

	public void setPrvId(Integer prvId) {
		this.prvId = prvId;
	}

	public String getUprFlag() {
		return uprFlag;
	}

	public void setUprFlag(String uprFlag) {
		this.uprFlag = uprFlag;
	}

}
