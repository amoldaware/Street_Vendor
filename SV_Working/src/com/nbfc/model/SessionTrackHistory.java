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
@Table(name = "stvn_session_track")
public class SessionTrackHistory implements Serializable{
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	@Column(name="MEM_BNK_ID")
	String memBnkId;
	@Column(name="USR_ID")
	String  usrId ;
	@Column(name="IP")
	String ipAddress  ;
	@Column(name="MACHINENAME")
	String machineName;
	@Column(name="LOGIN_TIME")
	Date loginTime  ;
	@Column(name="LOGOUT_TIME")
	Date logoutTime;  
	@Column(name="WEBSESSID")
	String webSessionId  ;
	@Column(name="BROWSER_INFO")
	String  browserInfo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemBnkId() {
		return memBnkId;
	}
	public void setMemBnkId(String memBnkId) {
		this.memBnkId = memBnkId;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getWebSessionId() {
		return webSessionId;
	}
	public void setWebSessionId(String webSessionId) {
		this.webSessionId = webSessionId;
	}
	public String getBrowserInfo() {
		return browserInfo;
	}
	public void setBrowserInfo(String browserInfo) {
		this.browserInfo = browserInfo;
	}


}
