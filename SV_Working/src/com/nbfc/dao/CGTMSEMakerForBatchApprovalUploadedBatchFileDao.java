package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.BatchApprovalDataBean;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;

public interface CGTMSEMakerForBatchApprovalUploadedBatchFileDao {
	public List<Object> getUploadedBatchFileDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj);
	public List<Object> getUploadedBatchFileCCRDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj);
	public List<CGTMSEMakerBatchUploadDetails> getUploadedData(String status, String status2);
}
