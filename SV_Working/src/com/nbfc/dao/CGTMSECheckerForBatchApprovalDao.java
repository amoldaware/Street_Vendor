package com.nbfc.dao;


import java.util.List;

import com.nbfc.model.CGTMSECheckerForBatchApprovalGetStatus;

public interface CGTMSECheckerForBatchApprovalDao {
	public List<Object> getCMAStatusCount(CGTMSECheckerForBatchApprovalGetStatus cgtmseCheckerForBatchApprovalGetStatus);

}
