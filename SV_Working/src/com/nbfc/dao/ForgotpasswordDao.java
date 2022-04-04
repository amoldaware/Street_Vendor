package com.nbfc.dao;

import com.nbfc.bean.LoginBean;
import com.nbfc.model.Login;
import com.nbfc.model.OtpDetailsModel;

public interface ForgotpasswordDao {

	String verifyUserID(LoginBean loginBean);

	String getUsermailID(String loginUsrId);



	void sendmail(String loginUsrId, String email, String otp, String subject,
			String mailBody);

	String getOTP(String loginUsrId);

	//void insertOTPData(String loginUsrId, String oTP);

	void insertOTPData(OtpDetailsModel otpDetails);

	String verifyUserOtp(LoginBean loginBean);

	boolean changePassword(Login preChangePasswordModal);

}
