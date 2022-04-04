package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.UserDao;
import com.nbfc.model.BankDetails;
import com.nbfc.model.BulkUploadTracker;
import com.nbfc.model.MLIBankIdDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIExposureIDDetails;
import com.nbfc.model.MLIExposureId;
import com.nbfc.model.MLIIdDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.UseRoleModel;
import com.nbfc.model.User;
import com.nbfc.model.UserInfo;
import com.nbfc.model.UserInfoModel;
import com.nbfc.model.UserPerivilegeDetails;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Service("employeeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao employeeDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(User employee) {
		employeeDao.addEmployee(employee);
	}
	
	public List<User> listEmployeess() {
		return employeeDao.listEmployeess();
	}

	public User getEmployee(String fName) {
		return employeeDao.getEmployee(fName);
	}

	
	public MLIName getMLIName(String mem_bnk_id) {
		return employeeDao.getMLIName(mem_bnk_id);
	}

	
	public MLIDetails getBNKId(String userID) {
		return employeeDao.getBNKId(userID);
	}

	
	public List<PortfolioNumberMaster> getPortfolioNUmberForChecker(String mliName) {
		return employeeDao.getPortfolioNUmberForChecker(mliName);
	}
	
	
	public List<UserPerivilegeDetails> getUserPrivlageDetails() {
		
		return employeeDao.getUserPrivlageDetails();
	}

	
	public User getUserDetails(String userID) {
		// TODO Auto-generated method stub
		return employeeDao.getUserDetails(userID);
	}

	
	public UserPerivilegeDetails getUserPrivlageDetailsForEdit(String userID) {
		return employeeDao.getUserPrivlageDetailsForEdit(userID);
	}

	
	public void updateUserPrivlage(UserPerivilegeDetails userPerivilegeDetails) {
		employeeDao.updateUserPrivlage(userPerivilegeDetails);
	}
	public int deleteUser(String deleteUserId) {
		return employeeDao.deleteUser(deleteUserId);		
	}

	
	public int userRegisteredCount(String mem_BNK_ID) {
		return employeeDao.userRegisteredCount(mem_BNK_ID);		
	}

	public int userLimit(String tvlKey) {
		return employeeDao.userLitmit(tvlKey);		
	}
	public MLIExposureId getExposureId(String mem_bnk_id,String fyBasedOnStartAndEndDate)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return employeeDao.getExposureId(mem_bnk_id,fyBasedOnStartAndEndDate);
	}

	
	public MLIExposureId getMemBankId(String exposureID)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return employeeDao.getMemBankId(exposureID);
	}

	
	public BankDetails getMLILongName(String mem_bank_id)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return employeeDao.getMLILongName(mem_bank_id);
	}

	
	public MLIBankIdDetails getMLIId(String mliLongName)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return employeeDao.getMLIId(mliLongName);
	}

	
	public void addEmployeeHistory1(String userId, String date) {
		// TODO Auto-generated method stub
		employeeDao.addEmployeeHistory1(userId,date);
	}

	
	public void UpdateUprflag(String userId) {
		employeeDao.UpdateUprflag(userId);
		}

	
	public int userEmailIDCount(String email) {
		// TODO Auto-generated method stub
		return employeeDao.userEmailIDCount(email);
	}

	
	public void updateUserInfo(UserInfoModel userInfo) {
		employeeDao.updateUserInfo(userInfo);
		// TODO Auto-generated method stub
		
	}
	
	public MLIIdDetails getMLIID(String BankName){
		return employeeDao.getMLIID(BankName);
		
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		// TODO Auto-generated method stub
		return employeeDao.getUserInfo(userId);
	}
	public List<BulkUploadTracker> getBulkUploadTrack(String userId)
	{
		return 	employeeDao.getBulkUploadTrack(userId);
	}

	@Override
	public void updateUserPrivlige(String userId, int prvId) {
		employeeDao.updateUserPrivlige(userId,prvId);
		
	}
	}

