package com.nbfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nbfc_user_info")
public class UserInfo {

	@Id
	@Column(name = "USR_ID")
	private String USR_ID;
	@Column(name = "MEM_BNK_ID")
	private String MEM_BNK_ID;
	@Column(name = "MEM_ZNE_ID")
	private String MEM_ZNE_ID;
	@Column(name = "MEM_BRN_ID")
	private String MEM_BRN_ID;
	@Column(name = "USR_FIRST_NAME")
	private String USR_FIRST_NAME;
	@Column(name = "USR_LAST_NAME")
	private String USR_LAST_NAME;
	@Column(name = "USR_EMAIL_ID")
	private String USR_EMAIL_ID;

	public String getUSR_ID() {
		return USR_ID;
	}

	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}

	public String getMEM_BNK_ID() {
		return MEM_BNK_ID;
	}

	public void setMEM_BNK_ID(String mEM_BNK_ID) {
		MEM_BNK_ID = mEM_BNK_ID;
	}

	public String getMEM_ZNE_ID() {
		return MEM_ZNE_ID;
	}

	public void setMEM_ZNE_ID(String mEM_ZNE_ID) {
		MEM_ZNE_ID = mEM_ZNE_ID;
	}

	public String getMEM_BRN_ID() {
		return MEM_BRN_ID;
	}

	public void setMEM_BRN_ID(String mEM_BRN_ID) {
		MEM_BRN_ID = mEM_BRN_ID;
	}

	public String getUSR_FIRST_NAME() {
		return USR_FIRST_NAME;
	}

	public void setUSR_FIRST_NAME(String uSR_FIRST_NAME) {
		USR_FIRST_NAME = uSR_FIRST_NAME;
	}

	public String getUSR_LAST_NAME() {
		return USR_LAST_NAME;
	}

	public void setUSR_LAST_NAME(String uSR_LAST_NAME) {
		USR_LAST_NAME = uSR_LAST_NAME;
	}

	public String getUSR_EMAIL_ID() {
		return USR_EMAIL_ID;
	}

	public void setUSR_EMAIL_ID(String uSR_EMAIL_ID) {
		USR_EMAIL_ID = uSR_EMAIL_ID;
	}

	@Override
	public String toString() {
		return "UserInfo [USR_ID=" + USR_ID + ", MEM_BNK_ID=" + MEM_BNK_ID
				+ ", MEM_ZNE_ID=" + MEM_ZNE_ID + ", MEM_BRN_ID=" + MEM_BRN_ID
				+ ", USR_FIRST_NAME=" + USR_FIRST_NAME + ", USR_LAST_NAME="
				+ USR_LAST_NAME + ", USR_EMAIL_ID=" + USR_EMAIL_ID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((MEM_BNK_ID == null) ? 0 : MEM_BNK_ID.hashCode());
		result = prime * result
				+ ((MEM_BRN_ID == null) ? 0 : MEM_BRN_ID.hashCode());
		result = prime * result
				+ ((MEM_ZNE_ID == null) ? 0 : MEM_ZNE_ID.hashCode());
		result = prime * result
				+ ((USR_EMAIL_ID == null) ? 0 : USR_EMAIL_ID.hashCode());
		result = prime * result
				+ ((USR_FIRST_NAME == null) ? 0 : USR_FIRST_NAME.hashCode());
		result = prime * result + ((USR_ID == null) ? 0 : USR_ID.hashCode());
		result = prime * result
				+ ((USR_LAST_NAME == null) ? 0 : USR_LAST_NAME.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (MEM_BNK_ID == null) {
			if (other.MEM_BNK_ID != null)
				return false;
		} else if (!MEM_BNK_ID.equals(other.MEM_BNK_ID))
			return false;
		if (MEM_BRN_ID == null) {
			if (other.MEM_BRN_ID != null)
				return false;
		} else if (!MEM_BRN_ID.equals(other.MEM_BRN_ID))
			return false;
		if (MEM_ZNE_ID == null) {
			if (other.MEM_ZNE_ID != null)
				return false;
		} else if (!MEM_ZNE_ID.equals(other.MEM_ZNE_ID))
			return false;
		if (USR_EMAIL_ID == null) {
			if (other.USR_EMAIL_ID != null)
				return false;
		} else if (!USR_EMAIL_ID.equals(other.USR_EMAIL_ID))
			return false;
		if (USR_FIRST_NAME == null) {
			if (other.USR_FIRST_NAME != null)
				return false;
		} else if (!USR_FIRST_NAME.equals(other.USR_FIRST_NAME))
			return false;
		if (USR_ID == null) {
			if (other.USR_ID != null)
				return false;
		} else if (!USR_ID.equals(other.USR_ID))
			return false;
		if (USR_LAST_NAME == null) {
			if (other.USR_LAST_NAME != null)
				return false;
		} else if (!USR_LAST_NAME.equals(other.USR_LAST_NAME))
			return false;
		return true;
	}

}
