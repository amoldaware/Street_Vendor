package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.CGTMSEMakerForBatchApprovalGetStatusDao;
import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;

@Service("cgmseMakerForBatchApprovalGetStatusService")
@Transactional()
public class CGTMSEMakerForBatchApprovalGetStatusServiceImpl implements CGTMSEMakerForBatchApprovalGetStatusService{
	@Autowired
	private  CGTMSEMakerForBatchApprovalGetStatusDao cgtmseMakerForBatchApprovalGetStatusDao;
	static Logger log = Logger.getLogger(CGTMSEMakerForBatchApprovalGetStatusServiceImpl.class.getName());
	
	public Integer getNCAStatusCountBasedOnNCAStatus(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus) {
		log.info("getNCAStatusCountBasedOnNCAStatus method called as part of  CGTMSEMakerForBatchApprovalGetStatusServiceImpl==");
		return  (Integer) cgtmseMakerForBatchApprovalGetStatusDao.getNCAStatusCountBasedOnNCAStatus(cgtmseMakerForBatchApprovalGetStatus);
	}
	
	
	public List<Object> getNoOfBatchRejectedByCGTMSE(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus) {
		log.info("getNoOfBatchRejectedByCGTMSE method called as part of  CGTMSEMakerForBatchApprovalGetStatusServiceImpl==");
		return  cgtmseMakerForBatchApprovalGetStatusDao.getNoOfBatchRejectedByCGTMSE(cgtmseMakerForBatchApprovalGetStatus);
	}
}
