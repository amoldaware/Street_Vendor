package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
import com.nbfc.dao.CGTMSECreateExposureLimitMakerDao;
import com.nbfc.dao.CGTMSECreatedExposureLimitCheckerDao;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETMemberInfoDetails;
import com.nbfc.model.CGTMSERejectExposureMasterCheckerDetailsData;
import com.nbfc.model.CGTMSESaveExposureMasterCheckerDetailsData;

@Service("cgtmseCreatedExposureLimitCheckerService")
@Transactional()
public class CGTMSECreatedExposureLimitCheckerServiceImpl implements CGTMSECreatedExposureLimitCheckerService{
	@Autowired
	private  CGTMSECreatedExposureLimitCheckerDao cgtmseCreatedExposureLimitCheckerDao;
	
	
	
	public List<Object[]>  getMliLongNameInDropDown() {
		return (List<Object[]>) cgtmseCreatedExposureLimitCheckerDao.getMliLongNameInDropDown();
	}

	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSECreatedExposureLimitCheckerBean cgtmseCreatedExposureLimitCheckerBean) {
		return cgtmseCreatedExposureLimitCheckerDao.getMliShortNameOnChangeOfMliLongName(cgtmseCreatedExposureLimitCheckerBean);

	}

	
	public List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> getDetailsOfMemberInfo(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls) {
		return cgtmseCreatedExposureLimitCheckerDao.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
	}

	
	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(
			CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
		return cgtmseCreatedExposureLimitCheckerDao.getExposureLimitInfo(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);

	}
	
	public List<CGTMSESaveExposureMasterCheckerDetailsData>getDetailsOfExposer(
			CGTMSESaveExposureMasterCheckerDetailsData cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
		return cgtmseCreatedExposureLimitCheckerDao.getDetailsOfExposer(cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);

	}
	
	
	public int approveCreatedExposureLimit(
			CGTMSESaveExposureMasterCheckerDetailsData cgtmseSaveExposureMasterCheckerDetailsDataObj) {
		cgtmseCreatedExposureLimitCheckerDao.approveCreatedExposureLimit(cgtmseSaveExposureMasterCheckerDetailsDataObj);
		return 0;
		
	}

	/*
	public void updateStatusExposerLimitCheakerAprroval(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls)
	{
		cgtmseCreatedExposureLimitCheckerDao.updateStatusExposerLimitCheakerAprroval(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		
	 }
	
	
	public void updateStatusExposerLimitCheakerRejection(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls)
	{
		cgtmseCreatedExposureLimitCheckerDao.updateStatusExposerLimitCheakerRejection(cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
		
	 }*/
	
	
	public int rejectCreatedExposureLimit(
			CGTMSERejectExposureMasterCheckerDetailsData cgtmseRejectExposureMasterCheckerDetailsDataObj) {
		cgtmseCreatedExposureLimitCheckerDao.rejectCreatedExposureLimit(cgtmseRejectExposureMasterCheckerDetailsDataObj);
		return 0;
	}

	
	public List<Object[]> getExposureLimitDetailsPendingForApproval() {
		return cgtmseCreatedExposureLimitCheckerDao.getExposureLimitDetailsPendingForApproval();
	}

	
	public List<Object[]> getExposureLimitApprovedDetails() {
		return cgtmseCreatedExposureLimitCheckerDao.getExposureLimitApprovedDetails();
	}

	
	public List<Object[]> getExposureLimitRejectedDetails() {
		return cgtmseCreatedExposureLimitCheckerDao.getExposureLimitRejectedDetails();
	}

	
	public List<Object[]> getAllExposureLimitDetails() 
	{
			return cgtmseCreatedExposureLimitCheckerDao.getAllExposureLimitDetails();
	}
	
	
}
