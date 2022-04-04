package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.dao.DisbursementForApprovalDao;
import com.nbfc.model.DisbursementForApprovalModel;
@Service("disbursementForApprovalService")
@Transactional()
public class DisbursementForApprovalServiceImpl implements DisbursementForApprovalService{

	@Autowired
	private DisbursementForApprovalDao disbursementForApprovalDao;
	
	static Logger log = Logger.getLogger(CGTMSECheckerBatchUploadsPendingForApprovalServiceImpl.class.getName());

	/*
	public List<DisbursementForApprovalModel> getPortfolioNumber() {
		log.info("getPortfolioNumber method called in DisbursementForApprovalServiceImpl");
				return (List<DisbursementForApprovalModel>)disbursementForApprovalDao.getPortfolioNumber();
	}*/

	
	public List<DisbursementForApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		return (List<DisbursementForApprovalModel>)disbursementForApprovalDao.getDisburseDataForApproval(portfolioNo, loanAccountNumber);
	}

	
	public int updateDisbursedData(List<DisbursementForApprovalModel> disbursedData,String userId) {
		// TODO Auto-generated method stub
		return disbursementForApprovalDao.updateDisbursedData(disbursedData,userId);
	}

	

}
