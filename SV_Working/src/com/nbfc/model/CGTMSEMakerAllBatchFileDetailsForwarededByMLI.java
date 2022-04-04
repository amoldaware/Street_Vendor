package com.nbfc.model;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSEMakerAllBatchFileDetailsForwarededByMLI implements Serializable{
	
public static final long serialVersionUID = 1L;

	@Id
	@Column(name= "NBFC_MAKER_ID")
	String  USRID;
	
	@Column(name = "NBFC_UPLOADED_DATE")
	public Date DATEOFUPLOAD;
	
	@Column(name = "PORTFOLIO_NO")
	public Integer PORTFOLIONO;
	
	@Column(name = "SANCTIONED_AMOUNT")
	public String TOTALSANCTIONEDAMOUNT;
	
	@Column(name = "STATUS")
	public String STATUS;

	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	
	@Column(name = "FILE_PATH" )
	public String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getSubPortfolioNo() {
		return subPortfolioNo;
	}

	public void setSubPortfolioNo(Integer subPortfolioNo) {
		this.subPortfolioNo = subPortfolioNo;
	}

	public Date getDATEOFUPLOAD() {
		return DATEOFUPLOAD;
	}

	public void setDATEOFUPLOAD(java.util.Date dateofUplaod) {
		DATEOFUPLOAD = (Date) dateofUplaod;
	}

	public Integer getPORTFOLIONO() {
		return PORTFOLIONO;
	}

	public void setPORTFOLIONO(Integer pORTFOLIONO) {
		PORTFOLIONO = pORTFOLIONO;
	}

	public String getTOTALSANCTIONEDAMOUNT() {
		return TOTALSANCTIONEDAMOUNT;
	}

	public void setTOTALSANCTIONEDAMOUNT(String tOTALSANCTIONEDAMOUNT) {
		TOTALSANCTIONEDAMOUNT = tOTALSANCTIONEDAMOUNT;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUSRID() {
		return USRID;
	}

	public void setUSRID(String uSRID) {
		USRID = uSRID;
	}

	
}
