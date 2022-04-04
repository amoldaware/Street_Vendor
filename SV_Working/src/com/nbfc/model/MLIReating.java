package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_MLI_RATING")
public class MLIReating {

	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "MLI_RATING")
	private String mliReating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMliReating() {
		return mliReating;
	}

	public void setMliReating(String mliReating) {
		this.mliReating = mliReating;
	}

}
