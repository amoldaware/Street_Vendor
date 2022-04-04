/**
 * 
 */
package com.nbfc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author manoj.k
 *
 */
@Entity
@Table(name="pcs_mail_conf")
public class MailConfiguration implements Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mail_config_id")
	private int mailConfigurationId;
	
	@Column(name="SMTP_HOST")
	private String smtpHost;
	
	@Column(name="SMTP_PORT")
	private String smtpPort;
	
	@Column(name="SMTP_DOMAIN")
	private String smtpDomain;
	
	@Column(name="DELETE_FLAG")
	private String deleteFlag;
	
	@Column(name="EMAIL_ID")
	private String  emailId;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL_ID_FROM_NAME")
	private String  emailIdFromName;

	public int getMailConfigurationId() {
		return mailConfigurationId;
	}

	public void setMailConfigurationId(int mailConfigurationId) {
		this.mailConfigurationId = mailConfigurationId;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpDomain() {
		return smtpDomain;
	}

	public void setSmtpDomain(String smtpDomain) {
		this.smtpDomain = smtpDomain;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailIdFromName() {
		return emailIdFromName;
	}

	public void setEmailIdFromName(String emailIdFromName) {
		this.emailIdFromName = emailIdFromName;
	}
	
	
}
