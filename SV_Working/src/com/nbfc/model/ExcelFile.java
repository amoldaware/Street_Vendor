package com.nbfc.model;

import org.springframework.web.multipart.MultipartFile;
/**
 * @author Saurav Tyagi 2017
 * 
 */
public class ExcelFile {

	private MultipartFile file;
	private String QUARTER_NO;
	private String PORTFOLIO_BASE_YER;
	private String PORTFOLIO_NO;
	private String SENCTIONED_PORTFOLIO;
	private String GUARANTEE_COMMISSION;
	private String PAYOUT_CAP;
	private String PORTFOLIO_PERIOD;
	private String PORTFOLIO_START_DATE;
	private String TYPE_OF_LONE;

	

	public String getPORTFOLIO_BASE_YER() {
		return PORTFOLIO_BASE_YER;
	}

	public void setPORTFOLIO_BASE_YER(String pORTFOLIO_BASE_YER) {
		PORTFOLIO_BASE_YER = pORTFOLIO_BASE_YER;
	}

	public String getPORTFOLIO_NO() {
		return PORTFOLIO_NO;
	}

	public void setPORTFOLIO_NO(String pORTFOLIO_NO) {
		PORTFOLIO_NO = pORTFOLIO_NO;
	}

	public String getSENCTIONED_PORTFOLIO() {
		return SENCTIONED_PORTFOLIO;
	}

	public void setSENCTIONED_PORTFOLIO(String sENCTIONED_PORTFOLIO) {
		SENCTIONED_PORTFOLIO = sENCTIONED_PORTFOLIO;
	}

	public String getGUARANTEE_COMMISSION() {
		return GUARANTEE_COMMISSION;
	}

	public void setGUARANTEE_COMMISSION(String gUARANTEE_COMMISSION) {
		GUARANTEE_COMMISSION = gUARANTEE_COMMISSION;
	}

	public String getPAYOUT_CAP() {
		return PAYOUT_CAP;
	}

	public void setPAYOUT_CAP(String pAYOUT_CAP) {
		PAYOUT_CAP = pAYOUT_CAP;
	}

	public String getPORTFOLIO_PERIOD() {
		return PORTFOLIO_PERIOD;
	}

	public void setPORTFOLIO_PERIOD(String pORTFOLIO_PERIOD) {
		PORTFOLIO_PERIOD = pORTFOLIO_PERIOD;
	}

	public String getPORTFOLIO_START_DATE() {
		return PORTFOLIO_START_DATE;
	}

	public void setPORTFOLIO_START_DATE(String pORTFOLIO_START_DATE) {
		PORTFOLIO_START_DATE = pORTFOLIO_START_DATE;
	}

	public String getTYPE_OF_LONE() {
		return TYPE_OF_LONE;
	}

	public void setTYPE_OF_LONE(String tYPE_OF_LONE) {
		TYPE_OF_LONE = tYPE_OF_LONE;
	}

	public String getQUARTER_NO() {
		return QUARTER_NO;
	}

	public void setQUARTER_NO(String qUARTER_NO) {
		QUARTER_NO = qUARTER_NO;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
