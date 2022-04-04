package com.nbfc.dao;

import java.util.List;

import com.nbfc.model.BankDetails;
import com.nbfc.model.MLIBankIdDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIExposureIDDetails;
import com.nbfc.model.MLIExposureId;
import com.nbfc.model.MLIIdDetails;
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
public interface UserDao {

	public void addEmployee(User employee);

	public List<User> listEmployeess() throws NullPointerException;

	public User getEmployee(String fName) throws NullPointerException;
	
	public MLIName getMLIName(String mem_bnk_id) throws NullPointerException;
	
	public MLIExposureId getExposureId(String mem_bnk_id, String fyBasedOnStartAndEndDate) throws NullPointerException;
	
	public MLIExposureId getMemBankId(String exposureID) throws NullPointerException;
	
	public MLIBankIdDetails getMLIId(String mliLongName) throws NullPointerException;
	
	public BankDetails getMLILongName(String mem_bank_id) throws NullPointerException;
	
	public MLIDetails getBNKId(String userID);
	
	public List<PortfolioNumberMaster> getPortfolioNUmberForChecker(String mliName);

	public List<UserPerivilegeDetails> getUserPrivlageDetails();
	
	public UserPerivilegeDetails getUserPrivlageDetailsForEdit(String UserID);
	
	public User getUserDetails(String userID);
	
	public void updateUserPrivlage(UserPerivilegeDetails userPerivilegeDetails);
	
	public int deleteUser(String deleteUserId);

	public int userRegisteredCount(String mem_BNK_ID);

	public void addEmployeeHistory1(String userId, String date);

	public void UpdateUprflag(String userId);

	public int userEmailIDCount(String email);

	public void updateUserInfo(UserInfoModel userInfo);
	
	public MLIIdDetails getMLIID(String BankName);
	
	public UserInfo getUserInfo(String userId);


}
