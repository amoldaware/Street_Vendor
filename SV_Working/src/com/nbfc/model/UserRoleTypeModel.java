package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_user_info")
public class UserRoleTypeModel implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@Column(name = "USR_ID")
	private String usr_id;

	
	@Column(name = "USR_TYPE")
	private String userType;

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
