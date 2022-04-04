package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nbfc_user_privilege")
public class UserRolePrivelage {

	@Id
	@Column(name = "SNO")
	private int s_no;
	@Column(name = "USR_ID")
	private String user_id;
	@Column(name = "PRV_ID")
	private int prv_id;
	@Column(name = "UPR_FLAG")
	private String upr_flag;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPrv_id() {
		return prv_id;
	}
	public void setPrv_id(int prv_id) {
		this.prv_id = prv_id;
	}
	public String getUpr_flag() {
		return upr_flag;
	}
	public void setUpr_flag(String upr_flag) {
		this.upr_flag = upr_flag;
	}

	/*@Override
	public String toString() {
		return "UserRolePrivelage [s_no=" + s_no + ", user_id=" + user_id
				+ ", prv_id=" + prv_id + ", upr_flag=" + upr_flag + "]";
	}*/

}
