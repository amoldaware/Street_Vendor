package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NBFC_PAYMENT_DETAIL")
public class DANPaymentDetails {

	@Id
	@Column(name = "PAY_ID")
	private String PAY_ID;
	@Column(name = "PAY_MODE_OF_DELIVERY")
	private String PAY_MODE_OF_DELIVERY;

	@Column(name = "PAY_MODE_OF_PAYMENT")
	private String PAY_MODE_OF_PAYMENT;

	@Column(name = "PAY_INSTRUMENT_TYPE")
	private String PAY_INSTRUMENT_TYPE;

	@Column(name = "PAY_INSTRUMENT_NUMBER")
	private String PAY_INSTRUMENT_NUMBER;

	@Column(name = "PAY_INSTRUMENT_DT")
	private Date PAY_INSTRUMENT_DT;

	@Column(name = "PAY_INSTRUMENT_OUTSTATION_FLAG")
	private String PAY_INSTRUMENT_OUTSTATION_FLAG;

	@Column(name = "PAY_AMOUNT")
	private Integer PAY_AMOUNT;

	@Column(name = "PAY_DRAWN_AT_BANK")
	private String PAY_DRAWN_AT_BANK;

	@Column(name = "PAY_DRAWN_AT_BRANCH")
	private String PAY_DRAWN_AT_BRANCH;

	@Column(name = "PAY_PAYEE_BANK_OR_BRANCH")
	private String PAY_PAYEE_BANK_OR_BRANCH;

	@Column(name = "PAY_PAYABLE_TO")
	private String PAY_PAYABLE_TO;

	@Column(name = "PAY_PAYABLE_AT")
	private String PAY_PAYABLE_AT;

	@Column(name = "PAY_PAYMENT_DT")
	private Date PAY_PAYMENT_DT;

	@Column(name = "PAY_RECEIVED_DT")
	private Date PAY_RECEIVED_DT;

	@Column(name = "PAY_REALISATION_AMOUNT")
	private Integer PAY_REALISATION_AMOUNT;

	@Column(name = "PAY_REALISATION_DT")
	private Date PAY_REALISATION_DT;

	@Column(name = "PAY_COLLECTING_BANK")
	private String PAY_COLLECTING_BANK;

	@Column(name = "PAY_COLLECTING_BANK_BRANCH")
	private String PAY_COLLECTING_BANK_BRANCH;

	@Column(name = "PAY_CGTSI_ACCOUNT_NUMBER")
	private String PAY_CGTSI_ACCOUNT_NUMBER;

	@Column(name = "PAY_CGTSI_ACCOUNT_BRANCH")
	private String PAY_CGTSI_ACCOUNT_BRANCH;

	public String getPAY_ID() {
		return PAY_ID;
	}

	public void setPAY_ID(String pAY_ID) {
		PAY_ID = pAY_ID;
	}

	public String getPAY_MODE_OF_DELIVERY() {
		return PAY_MODE_OF_DELIVERY;
	}

	public void setPAY_MODE_OF_DELIVERY(String pAY_MODE_OF_DELIVERY) {
		PAY_MODE_OF_DELIVERY = pAY_MODE_OF_DELIVERY;
	}

	public String getPAY_MODE_OF_PAYMENT() {
		return PAY_MODE_OF_PAYMENT;
	}

	public void setPAY_MODE_OF_PAYMENT(String pAY_MODE_OF_PAYMENT) {
		PAY_MODE_OF_PAYMENT = pAY_MODE_OF_PAYMENT;
	}

	public String getPAY_INSTRUMENT_TYPE() {
		return PAY_INSTRUMENT_TYPE;
	}

	public void setPAY_INSTRUMENT_TYPE(String pAY_INSTRUMENT_TYPE) {
		PAY_INSTRUMENT_TYPE = pAY_INSTRUMENT_TYPE;
	}

	public String getPAY_INSTRUMENT_NUMBER() {
		return PAY_INSTRUMENT_NUMBER;
	}

	public void setPAY_INSTRUMENT_NUMBER(String pAY_INSTRUMENT_NUMBER) {
		PAY_INSTRUMENT_NUMBER = pAY_INSTRUMENT_NUMBER;
	}

	public Date getPAY_INSTRUMENT_DT() {
		return PAY_INSTRUMENT_DT;
	}

	public void setPAY_INSTRUMENT_DT(Date pAY_INSTRUMENT_DT) {
		PAY_INSTRUMENT_DT = pAY_INSTRUMENT_DT;
	}

	public String getPAY_INSTRUMENT_OUTSTATION_FLAG() {
		return PAY_INSTRUMENT_OUTSTATION_FLAG;
	}

	public void setPAY_INSTRUMENT_OUTSTATION_FLAG(
			String pAY_INSTRUMENT_OUTSTATION_FLAG) {
		PAY_INSTRUMENT_OUTSTATION_FLAG = pAY_INSTRUMENT_OUTSTATION_FLAG;
	}

	public Integer getPAY_AMOUNT() {
		return PAY_AMOUNT;
	}

	public void setPAY_AMOUNT(Integer pAY_AMOUNT) {
		PAY_AMOUNT = pAY_AMOUNT;
	}

	public String getPAY_DRAWN_AT_BANK() {
		return PAY_DRAWN_AT_BANK;
	}

	public void setPAY_DRAWN_AT_BANK(String pAY_DRAWN_AT_BANK) {
		PAY_DRAWN_AT_BANK = pAY_DRAWN_AT_BANK;
	}

	public String getPAY_DRAWN_AT_BRANCH() {
		return PAY_DRAWN_AT_BRANCH;
	}

	public void setPAY_DRAWN_AT_BRANCH(String pAY_DRAWN_AT_BRANCH) {
		PAY_DRAWN_AT_BRANCH = pAY_DRAWN_AT_BRANCH;
	}

	public String getPAY_PAYEE_BANK_OR_BRANCH() {
		return PAY_PAYEE_BANK_OR_BRANCH;
	}

	public void setPAY_PAYEE_BANK_OR_BRANCH(String pAY_PAYEE_BANK_OR_BRANCH) {
		PAY_PAYEE_BANK_OR_BRANCH = pAY_PAYEE_BANK_OR_BRANCH;
	}

	public String getPAY_PAYABLE_TO() {
		return PAY_PAYABLE_TO;
	}

	public void setPAY_PAYABLE_TO(String pAY_PAYABLE_TO) {
		PAY_PAYABLE_TO = pAY_PAYABLE_TO;
	}

	public String getPAY_PAYABLE_AT() {
		return PAY_PAYABLE_AT;
	}

	public void setPAY_PAYABLE_AT(String pAY_PAYABLE_AT) {
		PAY_PAYABLE_AT = pAY_PAYABLE_AT;
	}

	public Date getPAY_PAYMENT_DT() {
		return PAY_PAYMENT_DT;
	}

	public void setPAY_PAYMENT_DT(Date pAY_PAYMENT_DT) {
		PAY_PAYMENT_DT = pAY_PAYMENT_DT;
	}

	public Date getPAY_RECEIVED_DT() {
		return PAY_RECEIVED_DT;
	}

	public void setPAY_RECEIVED_DT(Date pAY_RECEIVED_DT) {
		PAY_RECEIVED_DT = pAY_RECEIVED_DT;
	}

	public Integer getPAY_REALISATION_AMOUNT() {
		return PAY_REALISATION_AMOUNT;
	}

	public void setPAY_REALISATION_AMOUNT(Integer pAY_REALISATION_AMOUNT) {
		PAY_REALISATION_AMOUNT = pAY_REALISATION_AMOUNT;
	}

	public Date getPAY_REALISATION_DT() {
		return PAY_REALISATION_DT;
	}

	public void setPAY_REALISATION_DT(Date pAY_REALISATION_DT) {
		PAY_REALISATION_DT = pAY_REALISATION_DT;
	}

	public String getPAY_COLLECTING_BANK() {
		return PAY_COLLECTING_BANK;
	}

	public void setPAY_COLLECTING_BANK(String pAY_COLLECTING_BANK) {
		PAY_COLLECTING_BANK = pAY_COLLECTING_BANK;
	}

	public String getPAY_COLLECTING_BANK_BRANCH() {
		return PAY_COLLECTING_BANK_BRANCH;
	}

	public void setPAY_COLLECTING_BANK_BRANCH(String pAY_COLLECTING_BANK_BRANCH) {
		PAY_COLLECTING_BANK_BRANCH = pAY_COLLECTING_BANK_BRANCH;
	}

	public String getPAY_CGTSI_ACCOUNT_NUMBER() {
		return PAY_CGTSI_ACCOUNT_NUMBER;
	}

	public void setPAY_CGTSI_ACCOUNT_NUMBER(String pAY_CGTSI_ACCOUNT_NUMBER) {
		PAY_CGTSI_ACCOUNT_NUMBER = pAY_CGTSI_ACCOUNT_NUMBER;
	}

	public String getPAY_CGTSI_ACCOUNT_BRANCH() {
		return PAY_CGTSI_ACCOUNT_BRANCH;
	}

	public void setPAY_CGTSI_ACCOUNT_BRANCH(String pAY_CGTSI_ACCOUNT_BRANCH) {
		PAY_CGTSI_ACCOUNT_BRANCH = pAY_CGTSI_ACCOUNT_BRANCH;
	}
	
	
}
