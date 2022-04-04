package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MEMBER_INFO")
public class GetMLIName {

	@Id
	@Column(name = "MEM_BNK_ID")
	private String mem_bnk_id;
	@Column(name = "LONG_NAME")
	private String long_name;

	public String getMem_bnk_id() {
		return mem_bnk_id;
	}

	public void setMem_bnk_id(String mem_bnk_id) {
		this.mem_bnk_id = mem_bnk_id;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

}
