
package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "nbfc_demand_advice_info")
public class DanAllForCgtmseCheckerApprovalModel implements Serializable {
	@Id
	@Column(name = "DAN_ID")
	private String dan_Id;
	@Column(name = "DAN_FSTATUS")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDan_Id() {
		return dan_Id;
	}
	public void setDan_Id(String dan_Id) {
		this.dan_Id = dan_Id;
	}
	
	
	
}
