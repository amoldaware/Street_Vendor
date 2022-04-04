package com.nbfc.service;

import java.util.List;

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
}
