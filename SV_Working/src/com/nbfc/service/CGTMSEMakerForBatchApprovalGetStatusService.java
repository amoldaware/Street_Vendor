package com.nbfc.service;

import java.util.List;

import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;

public interface CGTMSEMakerForBatchApprovalGetStatusService {
	public Integer getNCAStatusCountBasedOnNCAStatus(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus);
	public List<Object> getNoOfBatchRejectedByCGTMSE(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus);

}
