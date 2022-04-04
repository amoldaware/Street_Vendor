package com.nbfc.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class UtrTracerBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int bu_id;
    private String member_id;
    private Long utrUploadId;
    private String process_name;
    private String upload_by;
    private Date upload_date;
    private String status;
    private int status_cnt;
    private int success_cnt;
    private int unsuccess_cnt;
    private Date modified_date;
    private int excel_cnt;
    private String financialYear;

	

	public int getSuccess_cnt() {
		return success_cnt;
	}

	public void setSuccess_cnt(int success_cnt) {
		this.success_cnt = success_cnt;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public int getBu_id() {
		return bu_id;
	}

	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	

	public Long getUtrUploadId() {
		return utrUploadId;
	}

	public void setUtrUploadId(Long utrUploadId) {
		this.utrUploadId = utrUploadId;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getUpload_by() {
		return upload_by;
	}

	public void setUpload_by(String upload_by) {
		this.upload_by = upload_by;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatus_cnt() {
		return status_cnt;
	}

	public void setStatus_cnt(int status_cnt) {
		this.status_cnt = status_cnt;
	}

	public int getUnsuccess_cnt() {
		return unsuccess_cnt;
	}

	public void setUnsuccess_cnt(int unsuccess_cnt) {
		this.unsuccess_cnt = unsuccess_cnt;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public int getExcel_cnt() {
		return excel_cnt;
	}

	public void setExcel_cnt(int excel_cnt) {
		this.excel_cnt = excel_cnt;
	}

}
