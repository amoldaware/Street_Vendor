package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.dao.CGTMSECheckerForBatchApprovalAndRejectionDao;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbission;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave;

@Service("cgtmseCheckerForBatchApprovalAndRejectionService")
@Transactional()
public class CGTMSECheckerForBatchApprovalAndRejectionServiceImpl implements CGTMSECheckerForBatchApprovalAndRejectionService{
	@Autowired
	private  CGTMSECheckerForBatchApprovalAndRejectionDao cgtmseCheckerForBatchApprovalAndRejectionDao;
	static Logger log = Logger.getLogger(CGTMSECheckerForBatchApprovalAndRejectionServiceImpl.class.getName());
	

	
	public boolean storeData(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave) {
		log.info("storeData method call as part of CGTMSECheckerForBatchApprovalAndRejectionServiceImpl=====");
		return cgtmseCheckerForBatchApprovalAndRejectionDao.storeData(cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave);
	}
	
	
	public List<CGTMSECheckerForBatchApprovalAndRejectionSumbission> getDataForNBFCCheckerFileDownLoad(CGTMSECheckerForBatchApprovalAndRejectionSumbission cgtmseCheckerForBatchApprovalAndRejectionSumbission) {
		log.info("getDataForNBFCCheckerFileDownLoad method call as part of CGTMSECheckerForBatchApprovalAndRejectionServiceImpl=====");
		return  cgtmseCheckerForBatchApprovalAndRejectionDao.getDataForNBFCCheckerFileDownLoad(cgtmseCheckerForBatchApprovalAndRejectionSumbission);
	}
	
	
	public boolean checkerApprove(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,String userId) {
		log.info("checkerApprove method call as part of CGTMSECheckerForBatchApprovalAndRejectionServiceImpl=====");
		return cgtmseCheckerForBatchApprovalAndRejectionDao.checkerApprove(cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,userId);		
	}
	
	
	public boolean checkerRejected(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave) {
		log.info("checkerRejected method call as part of CGTMSECheckerForBatchApprovalAndRejectionServiceImpl=====");
		return cgtmseCheckerForBatchApprovalAndRejectionDao.checkerRejected(cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave);		
	}

}
