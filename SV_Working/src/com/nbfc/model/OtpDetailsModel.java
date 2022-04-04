package com.nbfc.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NBFC_OTP_DETAIL")

public class OtpDetailsModel implements Serializable {
	
	@Id
    @Column(name = "User_ID")
	private String user_id;

	@Column(name = "OTP")
	private String otp;
	
	
	@Column(name = "INSERTEDON")
	private Date current_date=new Date();


	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}


}
