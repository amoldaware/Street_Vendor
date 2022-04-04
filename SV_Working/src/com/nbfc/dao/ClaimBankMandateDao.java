package com.nbfc.dao;

import com.nbfc.bean.ClaimBankMandateBean;
import com.nbfc.model.ClaimBankMandateBlobModel;
import com.nbfc.model.ClaimBankMandateBlobModelDownLoadPDF;
import com.nbfc.model.MLIInfo;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClaimBankMandateDao {
	public MLIInfo getMemInfo(String memberId);
	
	public boolean saveBankMandateDetails(ClaimBankMandateBean claimBankMandateBean,String loginUserId);
	
	public void saveBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj);
	
	public ClaimBankMandateBean getSaveBankMandateDetails(String memberId);
	
	public ClaimBankMandateBean getBankMandateDetailsForApprovalorRejection(String loginUserMemId);
	
	public int approveBankMandateDtlsByNFCChecker(String memId,String loginUserId);
	
	public int returnBankMandateDtlsByNFCChecker(String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean);
	
	public ClaimBankMandateBean getBankMandateDetailsForUpdate(String memId);	
	
	public int updateBankMandateDtlsByNbfcMk(ClaimBankMandateBean claimBankMandateBean,String loginUserId);
	
	public void UpdateBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj,String memberId);
	
	public ClaimBankMandateBlobModelDownLoadPDF downloadClaimBankMandate(String memberId) throws FileNotFoundException, SQLException, IOException;
	
	public ClaimBankMandateBean getBankMandateBlob(String memberId) ;
	
	public List<ClaimBankMandateBean> getBankMandateDetailsForApprovalorRejectionFromCGTMSE();
	
	public int approveBankMandateDtlsByCGTMSEChecker(String memId,String loginUserId);
	
	public int returnBankMandateDtlsByCGTMSEChecker(String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean);
	
	public List<ClaimBankMandateBean> getReportsOfNBFCAccountDetailsByCGTMSEChecker();
}
