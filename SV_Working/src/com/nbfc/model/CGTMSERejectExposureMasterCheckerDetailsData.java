
package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class CGTMSERejectExposureMasterCheckerDetailsData implements Serializable{
	 @Id
	 @Column(name = "EXPOSURE_ID")
	 private Long exposureId;
	 
	 @Column(name = "CHECKER_ID")
	 private String checkerId;
	 
	
	 @Column(name = "CHECKER_DATE")
	 private Date checkerDate;
	 
	 
	 @Column(name = "STATUS")
	 private String checkerStatus;
	 
	 
	 @Column(name = "MEM_BNK_ID")
	 private String memBnkId;
	 
	 @Column(name = "MEM_BRN_ID")
	 private String memBrnId;
	 
	 @Column(name = "MEM_ZNE_ID")
	 private String memZneId;
	 
	 @Column(name = "EXPOSURE_LIMIT")
	 private Long exposureLimit;
	 @Column(name = "STATUS_DESCRIPTION")
	 private String checkerStatusDescription;
	 @Column(name = "REMARKS")
	 private String remarks;
	 

	public String getCheckerStatusDescription() {
		return checkerStatusDescription;
	}

	public void setCheckerStatusDescription(String checkerStatusDescription) {
		this.checkerStatusDescription = checkerStatusDescription;
	}

	public Long getExposureId() {
		return exposureId;
	}

	public void setExposureId(Long exposureId) {
		this.exposureId = exposureId;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public Date getCheckerDate() {
		return checkerDate;
	}

	public void setCheckerDate(Date checkerDate) {
		this.checkerDate = checkerDate;
	}

	public String getCheckerStatus() {
		return checkerStatus;
	}

	public void setCheckerStatus(String checkerStatus) {
		this.checkerStatus = checkerStatus;
	}

	public String getMemBnkId() {
		return memBnkId;
	}

	public void setMemBnkId(String memBnkId) {
		this.memBnkId = memBnkId;
	}

	public String getMemBrnId() {
		return memBrnId;
	}

	public void setMemBrnId(String memBrnId) {
		this.memBrnId = memBrnId;
	}

	public String getMemZneId() {
		return memZneId;
	}

	public void setMemZneId(String memZneId) {
		this.memZneId = memZneId;
	}

	public Long getExposureLimit() {
		return exposureLimit;
	}

	public void setExposureLimit(Long exposureLimit) {
		this.exposureLimit = exposureLimit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
