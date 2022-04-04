package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PORTFOLIO_MASTER")
public class TotalNumberOfPortfolio implements Serializable{
	@Id
	@Column(name = "PORTFOLIO_NO" )
	Integer portfolio_no;

	public Integer getPortfolio_no() {
		return portfolio_no;
	}

	public void setPortfolio_no(Integer portfolio_no) {
		this.portfolio_no = portfolio_no;
	}
}
