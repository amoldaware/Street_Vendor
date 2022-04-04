package com.nbfc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.LoginBean;
import com.nbfc.controller.ForgotPasswordController;
import com.nbfc.dao.ForgotpasswordDao;
import com.nbfc.dao.LoginDao;
import com.nbfc.model.Login;
import com.nbfc.model.OtpDetailsModel;
@Service("ForgotpasswordService")
@Transactional()
public class ForgotPasswordServiceImpl implements ForgotpasswordService{
	@Autowired
	private ForgotpasswordDao forgotpassdao;
	

	
	public String verifyUserID(LoginBean loginBean) {
		return forgotpassdao.verifyUserID(loginBean);
	}

	
	public String getUsermailID(String loginUsrId) {
		return forgotpassdao.getUsermailID(loginUsrId);
	}

	
	public void sendmail(String loginUsrId,String email, String otp, String subject,
			String mailBody) {
		forgotpassdao.sendmail(loginUsrId,email,otp,subject,mailBody);
		
		
	}

	
	public String getOTP(String loginUsrId) {
		return forgotpassdao.getOTP(loginUsrId);
	}

//	
//	public void insertOTPData(String loginUsrId, String oTP) {
//		// TODO Auto-generated method stub
//		forgotpassdao.insertOTPData(loginUsrId,oTP);
//		return;
//	}

	
	public void insertOTPData(OtpDetailsModel otpDetails) {
		// TODO Auto-generated method stub
		forgotpassdao.insertOTPData(otpDetails);
		return;
	}

	
	public String verifyUserOtp(LoginBean loginBean) {
		return forgotpassdao.verifyUserOtp(loginBean);
	}

	
	public boolean changePassword(Login preChangePasswordModal) {
		// TODO Auto-generated method stub
		return forgotpassdao.changePassword(preChangePasswordModal);
	}
}
