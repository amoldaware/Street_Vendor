package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_EXPOSURE_LIMIT")
public class ExposureDetails {

	
	@Id
	@Column(name="EXPOSURE_ID")
	private String exposure_id;
	@Column(name="EXPOSURE_LIMIT")
	private String exposure_limit;
	
	
	public String getExposure_id() {
		return exposure_id;
	}
	public void setExposure_id(String exposure_id) {
		this.exposure_id = exposure_id;
	}
	public String getExposure_limit() {
		return exposure_limit;
	}
	public void setExposure_limit(String exposure_limit) {
		this.exposure_limit = exposure_limit;
	};
	
	
	
}
