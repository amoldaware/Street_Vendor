package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_user_privilege")
public class UserPerivilegeDetails implements Serializable {

	private static final long serialVersionUID = -723583058586873479L;

	@Id
	@Column(name = "USR_ID")
	String user_id;
	@Column(name = "PRV_ID")
	Integer prv_id;
	@Column(name = "UPR_FLAG")
	String upr_flag;

	@Column(name="SNO")
	private String sNo;
	
	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getPrv_id() {
		return prv_id;
	}

	public void setPrv_id(Integer prv_id) {
		this.prv_id = prv_id;
	}

	public String getUpr_flag() {
		return upr_flag;
	}

	public void setUpr_flag(String upr_flag) {
		this.upr_flag = upr_flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserPerivilegeDetails [user_id=" + user_id + ", prv_id="
				+ prv_id + ", upr_flag=" + upr_flag + ", sNo=" + sNo + "]";
	}
	

}
