package com.nbfc.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.nbfc.helper.CGTMSECheckerBatchUploadsPendingForApprovalHelper;
import com.nbfc.model.CGTMSECheckerBatchUploadsPendingForApproval;

@Repository("cgtmseCheckerBatchUploadsPendingForApprovalDao")
public interface CGTMSECheckerBatchUploadsPendingForApprovalDao {
	public List<Object> getBatchUploadsPendingForApprovalDetails(CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApproval);
	public List<CGTMSECheckerBatchUploadsPendingForApprovalHelper> getUploadedData(String status1,String status2);

}
