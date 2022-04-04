package com.nbfc.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement (name = "transaction")
@XmlAccessorType(XmlAccessType.NONE)
public class RecoveryXmlData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlElement(name="PAY_ID")
	private String rp_no;
	@XmlElement(name="VIRTUAL_ACCOUNT_NUMBER")
	private String virtualAccNo;
	@XmlElement(name="AMOUNT")
	private String recoveryAmt;
	@XmlElement(name="PAYMENT_INITIATED_DATE")
	private String paymentInitiateddDate;
	
	public String getRp_no() {
		return rp_no;
	}
	public void setRp_no(String rp_no) {
		this.rp_no = rp_no;
	}
	public String getVirtualAccNo() {
		return virtualAccNo;
	}
	public void setVirtualAccNo(String virtualAccNo) {
		this.virtualAccNo = virtualAccNo;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getPaymentInitiateddDate() {
		return paymentInitiateddDate;
	}
	public void setPaymentInitiateddDate(String paymentInitiateddDate) {
		this.paymentInitiateddDate = paymentInitiateddDate;
	}
	
	
	

}
