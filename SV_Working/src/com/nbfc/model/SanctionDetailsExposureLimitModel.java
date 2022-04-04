package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
//GET ExposureLimit
@Entity
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class SanctionDetailsExposureLimitModel implements Serializable{
@Id
@Column(name = "EXPOSURE_ID")
private Double exposureId;

@Column(name = "MEM_BNK_ID")
private String memBnkId;

@Column(name = "MEM_ZNE_ID")
private String memZneId;

@Column(name = "MEM_BRN_ID")
private String memBrnId;
@Column(name = "EXPOSURE_LIMIT")
private Double exposureLimit;
@Column(name = "STATUS")
private String status;

//@DateTimeFormat(pattern="dd/MM/yyyy")
@Column(name = "TO_DATE")
private String toDate;
 
public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
}



public Double getExposureId() {
	return exposureId;
}

public void setExposureId(Double exposureId) {
	this.exposureId = exposureId;
}

public String getMemBnkId() {
	return memBnkId;
}

public void setMemBnkId(String memBnkId) {
	this.memBnkId = memBnkId;
}

public String getMemZneId() {
	return memZneId;
}

public void setMemZneId(String memZneId) {
	this.memZneId = memZneId;
}

public String getMemBrnId() {
	return memBrnId;
}

public void setMemBrnId(String memBrnId) {
	this.memBrnId = memBrnId;
}

public Double getExposureLimit() {
	return exposureLimit;
}

public void setExposureLimit(Double exposureLimit) {
	this.exposureLimit = exposureLimit;
}

public String getStatus(){
	return status;
}

public void setStatus(String status) {
	this.status = status;
}





}
