package com.nbfc.bean;

public class AppropriationManualBO {
	private String mliId;
	private String mliName;
	private String rpNumber;
	private String uploadedDate;
	private String virtualAccountNo;
	private String initiatedDt;
	private String remark;
	private String utrNumber;
	private String fromDt;
	private String toDate;
	private String appropriatedCase;
	private String appropriationDate;
	private String userRole;
	private String userName;
	private String userId;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMliId() {
		return mliId;
	}

	public void setMliId(String mliId) {
		this.mliId = mliId;
	}

	public String getMliName() {
		return mliName;
	}

	public void setMliName(String mliName) {
		this.mliName = mliName;
	}

	public String getRpNumber() {
		return rpNumber;
	}

	public void setRpNumber(String rpNumber) {
		this.rpNumber = rpNumber;
	}

	public String getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getVirtualAccountNo() {
		return virtualAccountNo;
	}

	public void setVirtualAccountNo(String virtualAccountNo) {
		this.virtualAccountNo = virtualAccountNo;
	}

	public String getInitiatedDt() {
		return initiatedDt;
	}

	public void setInitiatedDt(String initiatedDt) {
		this.initiatedDt = initiatedDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUtrNumber() {
		return utrNumber;
	}

	public void setUtrNumber(String utrNumber) {
		this.utrNumber = utrNumber;
	}

	public String getFromDt() {
		return fromDt;
	}

	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAppropriatedCase() {
		return appropriatedCase;
	}

	public void setAppropriatedCase(String appropriatedCase) {
		this.appropriatedCase = appropriatedCase;
	}

	public String getAppropriationDate() {
		return appropriationDate;
	}

	public void setAppropriationDate(String appropriationDate) {
		this.appropriationDate = appropriationDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	//Added by Sarita on 27/12/2021 [START] 
	private String Is_Appropriation;
	public String getIs_Appropriation() {
		return Is_Appropriation;
	}
	public void setIs_Appropriation(String is_Appropriation) {
		Is_Appropriation = is_Appropriation;
	}
	
	//Added by Sarita on 27/12/2021 [END]
}
