package com.nbfc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nbfc.bean.LoginBean;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;

public interface LoginService {
	public String verifyUserLogin(LoginBean loginBean);
	public List<Object> gerLoginUserPrivilege(String loginUsrId,String usrFlag);
	public Login userDetails(String userID);
	public UserPerivilegeDetails getUserPrivlageDtl(String uID,String uStatus);
	public NBFCPrivilegeMaster getPrivlageMstDtl(int prv_id);
	public boolean changePassword(Login login);
	public int getLoginAttempt(LoginBean loginBean);
	public void updateLoginHistory(LoginBean loginBean, HttpSession session, HttpServletRequest request) throws Exception;
}
