
package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "nbfc_interface_upload")
public class CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave  implements Serializable{
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_generator")
	@TableGenerator(name = "employee_generator", table = "pk_table", pkColumnName = "name", valueColumnName = "value", allocationSize = 1)	@Column(name = "ID")
	Integer id;*/
	
	
	
	@Id
	@Column(name = "INTERFACE_UPLOAD_ID" )
	Integer interfaceUplaodId;
	
	
	@Column(name = "NBFC_MAKER_ID" )
	String usrId;
	
	@Column(name = "CGTMSE_CHECKER_ID" )
	String cgtmseCheckerId;
	
	
	@Column(name = "CGDAN")
	public String cgdan;

	@Column(name = "MPENDENCY")
	public String mPendency;
	
	@Column(name = "REMARKS")
	public String rejection_reason;
	
	@Column(name = "PORTFOLIO_NO")
	public Integer portfolioNo;
	
	@Column (name="STATUS")
	public String status;
	
	
	@Column(name = "SANCTIONED_AMOUNT" )
	String sanctionAmount;
	
	@Column(name = "SUB_PORTFOLIO_DTL_NO" )
	Integer subPortfolioNo;
	
	@Column(name = "NBFC_UPLOADED_DATE" )
	public String dateOfUpload;
	
	@Column(name = "FILE_PATH" )
	public String filePath;
	
	@Column(name = "FILE_ID" )
	public String fileId;
	
	
	
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getInterfaceUplaodId() {
		return interfaceUplaodId;
	}

	public void setInterfaceUplaodId(Integer interfaceUplaodId) {
		this.interfaceUplaodId = interfaceUplaodId;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public Integer getSubPortfolioNo() {
		return subPortfolioNo;
	}

	public void setSubPortfolioNo(Integer subPortfolioNo) {
		this.subPortfolioNo = subPortfolioNo;
	}

	public String getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(String dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	

	public String getCgdan() {
		return cgdan;
	}

	public void setCgdan(String cgdan) {
		this.cgdan = cgdan;
	}

	public String getmPendency() {
		return mPendency;
	}

	public void setmPendency(String mPendency) {
		this.mPendency = mPendency;
	}

	public String getRejection_reason() {
		return rejection_reason;
	}

	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}

	public Integer getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(Integer portfolioNo) {
		this.portfolioNo = portfolioNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getCgtmseCheckerId() {
		return cgtmseCheckerId;
	}

	public void setCgtmseCheckerId(String string) {
		this.cgtmseCheckerId = string;
	}
	
}
