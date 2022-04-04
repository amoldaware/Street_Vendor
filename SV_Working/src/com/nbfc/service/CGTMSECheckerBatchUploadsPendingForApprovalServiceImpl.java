package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.dao.CGTMSECheckerBatchUploadsPendingForApprovalDao;
import com.nbfc.helper.CGTMSECheckerBatchUploadsPendingForApprovalHelper;
import com.nbfc.model.CGTMSECheckerBatchUploadsPendingForApproval;

@Service("cgtmseCheckerBatchUploadsPendingForApprovalService")
@Transactional()
public class CGTMSECheckerBatchUploadsPendingForApprovalServiceImpl implements CGTMSECheckerBatchUploadsPendingForApprovalService{
	@Autowired
	private  CGTMSECheckerBatchUploadsPendingForApprovalDao cgtmseCheckerBatchUploadsPendingForApprovalDao;
	static Logger log = Logger.getLogger(CGTMSECheckerBatchUploadsPendingForApprovalServiceImpl.class.getName());
	
	
	public List<Object> getBatchUploadsPendingForApprovalDetails(CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApproval) {
		return cgtmseCheckerBatchUploadsPendingForApprovalDao.getBatchUploadsPendingForApprovalDetails(cgtmseCheckerBatchUploadsPendingForApproval);
	}

	@Override
	public List<CGTMSECheckerBatchUploadsPendingForApprovalHelper> getUploadedData(String status1,String status2 ) {
		return cgtmseCheckerBatchUploadsPendingForApprovalDao.getUploadedData(status1,status2);
	}

}
