package com.nbfc.dao;

import java.util.List;

import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;

public interface CGTMSEMakerForBatchApprovalGetStatusDao {
	public Integer getNCAStatusCountBasedOnNCAStatus(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus);
	public List<Object> getNoOfBatchRejectedByCGTMSE(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus);
}
