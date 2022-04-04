package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.UserDashboardBean;
import com.nbfc.dao.UserActivityDao;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserDashboardVmodel;
import com.nbfc.model.UserRolePrivelage;
import com.nbfc.service.UserActivityService;

@Service("UserActivityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	UserActivityDao userActivityDao;

	
	public List<UserActivity> getActivity(String roleName, String subActName) {
		return userActivityDao.getActivity( roleName, subActName);
	}

	
	public MLIName getBankID(String bankName) {
		return userActivityDao.getBankID(bankName);
	}
	
	public List<MLIDetails> getUserName(String mem_ban_id) {
		return userActivityDao.getUserName(mem_ban_id);
	}

	
	public List<UserRolePrivelage> getUserPrvDetails() {
		
		return userActivityDao.getUserPrvDetails();
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUserRoles(UserRolePrivelage userRolePrivelage) {
		userActivityDao.saveUserRoles(userRolePrivelage);
		
	}

	
	public int getMaxNumber() {
		return userActivityDao.getMaxNumber();
	}

	
	public void setUserType(String roleName, String userID) {
		// TODO Auto-generated method stub
		userActivityDao.setUserType(roleName, userID);
	}


	
	public int verifychkCnt(String Mliname) {
		return userActivityDao.verifychkCnt(Mliname);
	}


	
	public int verifymkCnt(String bnkId) {
		return userActivityDao.verifymkCnt(bnkId);
	}

	
	public int checkExistingUserRole(String mliId, String userrole,String userName) {
		// TODO Auto-generated method stub
		return userActivityDao.checkExistingUserRole(mliId,userrole,userName);	
		}

	
	public UserDashboardVmodel getUserDashboardDetails(String mliId) {
		// TODO Auto-generated method stub
		return userActivityDao.getUserDashboardDetails(mliId);
	}

	
	public List<UserActivity> getReport(String roleName, String subActName) {
		// TODO Auto-generated method stub
		return userActivityDao.getReport(roleName, subActName);
	}

	
	public UserDashboardBean getUserDashboardReport(String user_id) {
		// TODO Auto-generated method stub
		return userActivityDao.getUserDashboardReport(user_id);
	}
//
	
	public List<UserActivity> getActivityName(String role, String action) {
		// TODO Auto-generated method stub
		return userActivityDao.getActivityName(role,action);
	}

	
	public List<MLIRegistration> getMliLongName(String val) {
		// TODO Auto-generated method stub
		return userActivityDao.getMliLongName(val);
	}

	
	public void sendmail(String user_id, String email, String password,
			String subject, String mailBody) {
		
	userActivityDao.sendmail(user_id,email,password,subject,mailBody);
	}


	public int checkExistingUserRoleforModify(String mliId, String roleName,
			String userName) {
	
		return userActivityDao.checkExistingUserRoleforModify(mliId,roleName,userName);	
	}

	

}
