
package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.dao.DisbursementForApprovalDao;
import com.nbfc.dao.SanctionMakerDao;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.SanctionMakerModel;
@Service("sanctionMakerService")
@Transactional()
public class SanctionMakerServiceImpl implements SanctionMakerService{

	@Autowired
	private SanctionMakerDao sanctionMakerDao;
	
	static Logger log = Logger.getLogger(CGTMSECheckerBatchUploadsPendingForApprovalServiceImpl.class.getName());

	
	public List<SanctionMakerModel> getPortfolioNumber() {
		log.info("getPortfolioNumber method called in DisbursementForApprovalServiceImpl");
				return (List<SanctionMakerModel>)sanctionMakerDao.getPortfolioNumber();
	}

	
	public List<SanctionMakerModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		return (List<SanctionMakerModel>)sanctionMakerDao.getDisburseDataForApproval(portfolioNo, loanAccountNumber);
	}

	
	public int updateDisbursedData(List disbursedData,String userId) {
		// TODO Auto-generated method stub
		return sanctionMakerDao.updateDisbursedData(disbursedData,userId);
	}

	

}
