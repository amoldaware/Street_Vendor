package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSEMakerRejectionModel  implements Serializable{
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_generator")
	@TableGenerator(name = "employee_generator", table = "pk_table", pkColumnName = "name", valueColumnName = "value", allocationSize = 1)	@Column(name = "ID")
	Integer id;*/
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID")
	public Integer interfaceUploadId;
	
	@Column(name = "CGDAN")
	public String cgdan;

	@Column(name = "MPENDENCY")
	public String mPendency;
	
	@Column(name = "REMARKS")
	public String rejection_reason;
	
	@Column(name = "PORTFOLIO_NO")
	public Integer portfolioNo;
	
	@Column (name="STATUS")
	public String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(Integer portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	

	public Integer getInterfaceUploadId() {
		return interfaceUploadId;
	}

	public void setInterfaceUploadId(Integer interfaceUploadId) {
		this.interfaceUploadId = interfaceUploadId;
	}

	public String getCgdan() {
		return cgdan;
	}

	public void setCgdan(String cgdan) {
		this.cgdan = cgdan;
	}

	public String getmPendency() {
		return mPendency;
	}

	public void setmPendency(String mPendency) {
		this.mPendency = mPendency;
	}

	public String getRejection_reason() {
		return rejection_reason;
	}

	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}

	
	
	
}
