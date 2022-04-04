package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "all_dan_list_vw")
public class DanAllForCgtmseCheckerModel implements Serializable {
	@Id
    @Column(name = "ID")
    private Long id; // 
	@Column(name = "DAN_ID")
	private String dan_Id;
	@Column(name = "PORTFOLIO_RATE")
	private int portfolio_rate;
	@Column(name = "AMOUNT")
	private double amount;
	@Column(name = "PORTFOLIO_NAME")
	private String portfolio_name;
	@Column(name = "MLI_NAME")
	private String mli_name;
	
	@Column(name = "DAN_FSTATUS")
	private String danStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDanStatus() {
		return danStatus;
	}
	public void setDanStatus(String danStatus) {
		this.danStatus = danStatus;
	}
	public String getDan_Id() {
		return dan_Id;
	}
	public void setDan_Id(String dan_Id) {
		this.dan_Id = dan_Id;
	}
	public int getPortfolio_rate() {
		return portfolio_rate;
	}
	public void setPortfolio_rate(int portfolio_rate) {
		this.portfolio_rate = portfolio_rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPortfolio_name() {
		return portfolio_name;
	}
	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}
	public String getMli_name() {
		return mli_name;
	}
	public void setMli_name(String mli_name) {
		this.mli_name = mli_name;
	}
	@Override
	public String toString() {
		return "DanAllForCgtmseCheckerModel [id=" + id + ", dan_Id=" + dan_Id
				+ ", portfolio_rate=" + portfolio_rate + ", amount=" + amount
				+ ", portfolio_name=" + portfolio_name + ", mli_name="
				+ mli_name + ", danStatus=" + danStatus + "]";
	}

}
