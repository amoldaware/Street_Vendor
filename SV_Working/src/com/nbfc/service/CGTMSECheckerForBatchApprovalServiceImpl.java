package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.CGTMSECheckerForBatchApprovalDao;
import com.nbfc.model.CGTMSECheckerForBatchApprovalGetStatus;


@Service("cgmseCheckerForBatchApprovalService")
@Transactional()
public class CGTMSECheckerForBatchApprovalServiceImpl implements CGTMSECheckerForBatchApprovalService{
	@Autowired
	private  CGTMSECheckerForBatchApprovalDao cgmseCheckerForBatchApprovalDao;
	static Logger log = Logger.getLogger(CGTMSECheckerForBatchApprovalServiceImpl.class.getName());
	
	public List<Object> getCMAStatusCount(CGTMSECheckerForBatchApprovalGetStatus cgtmseCheckerForBatchApprovalGetStatus) {
		log.info("getCMAStatusCount method call as part of CGTMSECheckerForBatchApprovalServiceImpl=====");
		return  cgmseCheckerForBatchApprovalDao.getCMAStatusCount(cgtmseCheckerForBatchApprovalGetStatus);
	}
}


