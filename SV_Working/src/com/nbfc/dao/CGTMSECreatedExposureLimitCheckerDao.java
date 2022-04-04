package com.nbfc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETMemberInfoDetails;
import com.nbfc.model.CGTMSERejectExposureMasterCheckerDetailsData;
import com.nbfc.model.CGTMSESaveExposureMasterCheckerDetailsData;

public interface CGTMSECreatedExposureLimitCheckerDao 
{
	public List<Object[]> getMliLongNameInDropDown();
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSECreatedExposureLimitCheckerBean cgtmseExposureMasterCheckerBean);
	public List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> getDetailsOfMemberInfo(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
	public List<CGTMSESaveExposureMasterCheckerDetailsData> getDetailsOfExposer(CGTMSESaveExposureMasterCheckerDetailsData cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj);
	public int approveCreatedExposureLimit(CGTMSESaveExposureMasterCheckerDetailsData cgtmseSaveExposureMasterCheckerDetailsDataObj);
	public int rejectCreatedExposureLimit(CGTMSERejectExposureMasterCheckerDetailsData cgtmseRejectExposureMasterCheckerDetailsDataObj);
	/*public void updateStatusExposerLimitCheakerRejection(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls);
	public void updateStatusExposerLimitCheakerAprroval(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls);*/
	public List<Object[]> getExposureLimitDetailsPendingForApproval();
	public List<Object[]> getExposureLimitApprovedDetails();
	public List<Object[]> getExposureLimitRejectedDetails();
	public List<Object[]>getAllExposureLimitDetails();
	
}
