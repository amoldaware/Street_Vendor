package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class CurrentAddressBean 
{
    @Column(name="curr_houseNo" , length=200)
	private String houseNo;
    @Column(name="curr_village" , length=200)
	private String village;
    @Column(name="curr_town_Dist",length=200)
	private String town_Dist;
    @Column(name="curr_state",length=200)
	private String state;       
    @Column(name="curr_pin",length=200)
	private String pin;
	public String getHouseNo() {
		return houseNo;
	}
	public String getVillage() {
		return village;
	}
	public String getTown_Dist() {
		return town_Dist;
	}
	public String getState() {
		return state;
	}
	public String getPin() {
		return pin;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public void setTown_Dist(String town_Dist) {
		this.town_Dist = town_Dist;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

}
