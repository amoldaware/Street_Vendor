package com.nbfc.bean;

import java.io.Serializable;

public class UploadFileSuccessErrorCount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uploadId;
	private String successCount;
	private String unSuccessCount;
	private String status;
	private String cout;

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getUnSuccessCount() {
		return unSuccessCount;
	}

	public void setUnSuccessCount(String unSuccessCount) {
		this.unSuccessCount = unSuccessCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCout() {
		return cout;
	}

	public void setCout(String cout) {
		this.cout = cout;
	}

}
