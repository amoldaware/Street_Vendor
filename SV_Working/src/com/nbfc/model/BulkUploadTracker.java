package com.nbfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BULK_UPLOAD_TRACKER")
public class BulkUploadTracker {
	@Id
	@Column(name="bu_id")
	private int bu_id;
	@Column(name="member_id")
	private String member_id;
	@Column(name="upload_id")
	private String upload_id;
	@Column(name="process_name")
	private String process_name;
	@Column(name="uploaded_by")
	private String upload_by;
	@Column(name="uploaded_date")
	private Date upload_date;
	@Column(name="STATUS")
	private String status;
	@Column(name="SUCCESS_CNT")
	private int status_cnt;
	@Column(name="UNSUCCESS_CNT")
	private int unsuccess_cnt;
	@Column(name="MODIFY_DATE")
	private Date modified_date;
	@Column(name="excel_cnt")
	private int excel_cnt;
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
	public String getUpload_id() {
		return upload_id;
	}
	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
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
