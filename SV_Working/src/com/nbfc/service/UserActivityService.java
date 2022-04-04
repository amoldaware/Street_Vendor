package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.UserDashboardBean;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserDashboardVmodel;
import com.nbfc.model.UserRolePrivelage;

public interface UserActivityService {

	public List<UserActivity> getActivity(String roleName,String subActName);
	
	public List<UserActivity> getReport(String roleName,String subActName);
	
	public MLIName getBankID(String bankName);
	
	public List<MLIDetails> getUserName(String mem_ban_id);
	
	public List<UserRolePrivelage> getUserPrvDetails();
	
	public void saveUserRoles(UserRolePrivelage userRolePrivelage);
	
	public int getMaxNumber();
	
	public void setUserType(String roleName, String userID);

	public int verifychkCnt(String Mliname);

	public int verifymkCnt(String bnkId);

	public int checkExistingUserRole(String mliId, String userrole,String userName);
	
	public UserDashboardVmodel getUserDashboardDetails(String mliId);
	
	public UserDashboardBean getUserDashboardReport(String user_id);

	public List<UserActivity> getActivityName(String role, String action);

	public List<MLIRegistration> getMliLongName(String val);

	public void sendmail(String user_id, String email, String password,
			String subject, String mailBody);

	public int checkExistingUserRoleforModify(String mliId, String roleName,
			String userName);
	
	
}
