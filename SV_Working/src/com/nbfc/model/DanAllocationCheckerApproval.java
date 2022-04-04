
package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="nbfc_payment_detail_temp")
public class DanAllocationCheckerApproval implements Serializable {
	@Id
	@Column(name="PAY_ID")
	private String payId;	
	
	@Column(name="DAN_FSTATUS_REMARK")
	private String remark;
	
	@Column(name="DAN_FSTATUS")
	private String statusValue;
	
	
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	
	
	
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/*public String getDanId() {
		return danId;
	}
	public void setDanId(String danId) {
		this.danId = danId;
	}*/
	
	
}
