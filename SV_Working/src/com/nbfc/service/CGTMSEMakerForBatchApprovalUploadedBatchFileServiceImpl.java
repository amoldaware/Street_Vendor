package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.BatchApprovalDataBean;
import com.nbfc.dao.CGTMSEMakerForBatchApprovalUploadedBatchFileDao;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;

@Service("cgtmseMakerForBatchApprovalUploadedBatchFileService")
@Transactional()
public class CGTMSEMakerForBatchApprovalUploadedBatchFileServiceImpl implements CGTMSEMakerForBatchApprovalUploadedBatchFileService{

	@Autowired
	private  CGTMSEMakerForBatchApprovalUploadedBatchFileDao cgtmseMakerForBatchApprovalUploadedBatchFileDao;
	static Logger log = Logger.getLogger(CGTMSEMakerForBatchApprovalUploadedBatchFileServiceImpl.class.getName());
	
	public List<Object>  getUploadedBatchFileDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj) {
		log.info("getUploadedBatchFileDetails method called as part of  CGTMSEMakerForBatchApprovalUploadedBatchFileServiceImpl.");
		return  cgtmseMakerForBatchApprovalUploadedBatchFileDao.getUploadedBatchFileDetails(cgtmseMakerForBatchApprovalUploadedBatchFileObj);
	}
	
	public List<Object>  getUploadedBatchFileCCRDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj) {
		log.info("getUploadedBatchFileCCRDetails method called as part of  CGTMSEMakerForBatchApprovalUploadedBatchFileServiceImpl.");
		return  cgtmseMakerForBatchApprovalUploadedBatchFileDao.getUploadedBatchFileCCRDetails(cgtmseMakerForBatchApprovalUploadedBatchFileObj);
	}

	public List<CGTMSEMakerBatchUploadDetails> getUploadedData(String status1,String status2) {
		// TODO Auto-generated method stub
		return cgtmseMakerForBatchApprovalUploadedBatchFileDao.getUploadedData(status1,status2);
	}

}
