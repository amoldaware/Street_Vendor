package com.nbfc.service;

import java.util.List;

import com.nbfc.helper.CGTMSECheckerBatchUploadsPendingForApprovalHelper;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSECheckerBatchUploadsPendingForApproval;


public interface CGTMSECheckerBatchUploadsPendingForApprovalService {
	public List<Object> getBatchUploadsPendingForApprovalDetails(CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApproval);
	public List<CGTMSECheckerBatchUploadsPendingForApprovalHelper> getUploadedData(String status1,String status2);

}
