package com.nbfc.service;

import com.nbfc.bean.LoginBean;
import com.nbfc.model.Login;
import com.nbfc.model.OtpDetailsModel;

public interface ForgotpasswordService {
	public String verifyUserID(LoginBean loginBean);
	public String getUsermailID(String loginUsrId);
	public void sendmail(String loginUsrId,String email, String otp, String subject,
			String mailBody);
	public String getOTP(String loginUsrId);
	//public void insertOTPData(String loginUsrId, String oTP);
	public void insertOTPData(OtpDetailsModel otpDetails);
	public String verifyUserOtp(LoginBean loginBean);
	public boolean changePassword(Login preChangePasswordModal);
}
