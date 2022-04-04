package com.nbfc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.bean.ClaimBankMandateBean;
import com.nbfc.dao.ClaimBankMandateDao;
import com.nbfc.model.ClaimBankMandateBlobModel;
import com.nbfc.model.ClaimBankMandateBlobModelDownLoadPDF;
import com.nbfc.model.MLIInfo;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@Service("claimBankMandateService")
@Transactional()
public class ClaimBankMandateServiceImpl implements ClaimBankMandateService{
	@Autowired
	ClaimBankMandateDao claimBankMandateDao;

	@Override
	public MLIInfo getMemInfo(String memberId) {
		return claimBankMandateDao.getMemInfo(memberId);
	}
	
	public boolean saveBankMandateDetails(ClaimBankMandateBean claimBankMandateBean,String loginUserId) {
		return  claimBankMandateDao.saveBankMandateDetails(claimBankMandateBean,loginUserId);
	}
	
	public ClaimBankMandateBean getSaveBankMandateDetails(String memberId) {
		return  claimBankMandateDao.getSaveBankMandateDetails(memberId);
	}
	

	@Override
	public void saveBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj) {
		 claimBankMandateDao.saveBankMandateBlob(claimBankMandateBlobModelObj);
	}
	
	@Override
	public ClaimBankMandateBean getBankMandateDetailsForApprovalorRejection(String memId) {
		 return claimBankMandateDao.getBankMandateDetailsForApprovalorRejection(memId);
	}
	
	@Override
	public int approveBankMandateDtlsByNFCChecker(String memId,String loginUserId){
		 return claimBankMandateDao.approveBankMandateDtlsByNFCChecker(memId,loginUserId);
	}
	
	
	@Override
	public int returnBankMandateDtlsByNFCChecker(String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean){
		 return claimBankMandateDao.returnBankMandateDtlsByNFCChecker(memId,loginUserId,claimBankMandateBean);
	}
	
	@Override
	public ClaimBankMandateBean getBankMandateDetailsForUpdate(String memId){
		 return claimBankMandateDao.getBankMandateDetailsForUpdate(memId);
	}
	
	@Override
	public int updateBankMandateDtlsByNbfcMk(ClaimBankMandateBean claimBankMandateBean,String loginUserId){
		 return claimBankMandateDao.updateBankMandateDtlsByNbfcMk(claimBankMandateBean,loginUserId);
	}
	
	@Override
	public void UpdateBankMandateBlob(ClaimBankMandateBlobModel claimBankMandateBlobModelObj,String memberId) {
		 claimBankMandateDao.UpdateBankMandateBlob(claimBankMandateBlobModelObj,memberId);
	}
	
	
	@Override
	public ClaimBankMandateBlobModelDownLoadPDF downloadClaimBankMandate(String memberId) throws FileNotFoundException, SQLException, IOException {
		return claimBankMandateDao.downloadClaimBankMandate(memberId);
	
	}
	
	@Override
	public ClaimBankMandateBean getBankMandateBlob(String memberId) {
		 return claimBankMandateDao.getBankMandateBlob(memberId);
	}

	@Override
	public int approveBankMandateDtlsByCGTMSEChecker(String memId,String loginUserId){
		 return claimBankMandateDao.approveBankMandateDtlsByCGTMSEChecker(memId,loginUserId);
	}
	@Override
	public int returnBankMandateDtlsByCGTMSEChecker(String memId,String loginUserId,ClaimBankMandateBean claimBankMandateBean){
		 return claimBankMandateDao.returnBankMandateDtlsByCGTMSEChecker(memId,loginUserId,claimBankMandateBean);
	}
	
	@Override
	public List<ClaimBankMandateBean> getBankMandateDetailsForApprovalorRejectionFromCGTMSE() {
		 return claimBankMandateDao.getBankMandateDetailsForApprovalorRejectionFromCGTMSE();
	}
	
	@Override
	public List<ClaimBankMandateBean> getReportsOfNBFCAccountDetailsByCGTMSEChecker() {
		 return claimBankMandateDao.getReportsOfNBFCAccountDetailsByCGTMSEChecker();
	}
	
	
}
