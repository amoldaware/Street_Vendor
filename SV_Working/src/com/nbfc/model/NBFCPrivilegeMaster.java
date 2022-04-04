package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "nbfc_privilege_master")
public class NBFCPrivilegeMaster implements Serializable{
	@Id
	@Column(name = "PRV_ID" )
	Integer prvId;
	
	@Column(name = "PRV_NAME" )
	String prvName;
	
	@Column(name = "PRV_DESCRIPTION" )
	String prvDescription;
	
	@Column(name = "PRV_CREATED_MODIFIED_BY" )
	String prvCreatedModifiedBy;
	

	public Integer getPrvId() {
		return prvId;
	}

	public void setPrvId(Integer prvId) {
		this.prvId = prvId;
	}

	public String getPrvName() {
		return prvName;
	}

	public void setPrvName(String prvName) {
		this.prvName = prvName;
	}

	public String getPrvDescription() {
		return prvDescription;
	}

	public void setPrvDescription(String prvDescription) {
		this.prvDescription = prvDescription;
	}
	public String getPrvCreatedModifiedBy() {
		return prvCreatedModifiedBy;
	}

	public void setPrvCreatedModifiedBy(String prvCreatedModifiedBy) {
		this.prvCreatedModifiedBy = prvCreatedModifiedBy;
	}

	
}
