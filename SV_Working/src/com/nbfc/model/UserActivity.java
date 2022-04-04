package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_activity_info_test")
public class UserActivity {

	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "ACT_NAME")
	private String act_name;
	@Column(name = "ROLE_NAME")
	private String role_name;
	@Column(name = "CREATED_BY")
	private String ctrated_by;
	@Column(name = "SUB_SCT_NAME")
	private String sub_act_name;
	@Column(name = "ACT_VALUE")
	private String act_value;
	@Column(name="INDEX_POSITION")
	private int index_postion;
	@Column(name="ISACTIVE")
	private String isactive;

	
	

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getAct_value() {
		return act_value;
	}

	public void setAct_value(String act_value) {
		this.act_value = act_value;
	}

	public String getSub_act_name() {
		return sub_act_name;
	}

	public void setSub_act_name(String sub_act_name) {
		this.sub_act_name = sub_act_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getCtrated_by() {
		return ctrated_by;
	}

	public void setCtrated_by(String ctrated_by) {
		this.ctrated_by = ctrated_by;
	}

	@Override
	public String toString() {
		return "UserActivity [id=" + id + ", act_name=" + act_name
				+ ", role_name=" + role_name + ", ctrated_by=" + ctrated_by
				+ ", sub_act_name=" + sub_act_name + ", act_value=" + act_value
				+ "]";
	}

}
