package com.nbfc.bean;

public class LoginBean {
		private String usr_id;
		private String otp;
		private String usr_password;
		private String confirm_password;
		private String fName;
		private String lName;
		private String member_id;
		
		private String newPassword;
		private String reEnterPassword;
		private String checkBoxId;
		private String j_captcha_response;
		
		
		public String getCheckBoxId() {
			return checkBoxId;
		}

		public void setCheckBoxId(String checkBoxId) {
			this.checkBoxId = checkBoxId;
		}

		public String getJ_captcha_response() {
			return j_captcha_response;
		}

		public void setJ_captcha_response(String j_captcha_response) {
			this.j_captcha_response = j_captcha_response;
		}

		public String getMember_id() {
			return member_id;
		}

		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}

		public String getConfirm_password() {
			return confirm_password;
		}

		public void setConfirm_password(String confirm_password) {
			this.confirm_password = confirm_password;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		public String getReEnterPassword() {
			return reEnterPassword;
		}

		public void setReEnterPassword(String reEnterPassword) {
			this.reEnterPassword = reEnterPassword;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			this.lName = lName;
		}

		public LoginBean(){
		}
		
		public String getUsr_id() {
			return usr_id;
		}
		public void setUsr_id(String usr_id) {
			this.usr_id = usr_id;
		}
		public String getUsr_password() {
			return usr_password;
		}
		public void setUsr_password(String usr_password) {
			this.usr_password = usr_password;
		}
	
}
