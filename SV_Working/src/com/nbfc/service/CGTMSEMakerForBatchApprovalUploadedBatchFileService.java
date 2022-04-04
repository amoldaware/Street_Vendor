package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.BatchApprovalDataBean;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;

public interface CGTMSEMakerForBatchApprovalUploadedBatchFileService {
	public List<Object> getUploadedBatchFileDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj);
	public List<Object> getUploadedBatchFileCCRDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj);

	public List<CGTMSEMakerBatchUploadDetails> getUploadedData(String status1,String status2);
}
