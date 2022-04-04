
package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.dao.SanctionCheckerDao;
import com.nbfc.model.SanctionCheckerModel;
import com.raistudies.domain.CustomExceptionHandler;
@Service("sanctionCheckerService")
@Transactional()
public class SanctionCheckerServiceImpl implements SanctionCheckerService{
	@Autowired
	private SanctionCheckerDao sanctionCheckerDao;
	/*
	public List<DisbursementCheckerApprovalModel> getPortfolioNumber() {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.getPortfolioNumber();
	}*/

	
	public List<SanctionCheckerBean> getSanctionCheckerDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		// TODO Auto-generated method stub
		return sanctionCheckerDao.getSanctionCheckerDataForApproval(portfolioNo, loanAccountNumber);
	}

	

	
	public List<SanctionCheckerModel> getPortfolioNoForCheckerApproval() {
		// TODO Auto-generated method stub
		return sanctionCheckerDao.getPortfolioNoForCheckerApproval();

	}

	

	
	public int approveDisbursedData(List disbursedData, String userId)
			throws CustomExceptionHandler {
		// TODO Auto-generated method stub
		return sanctionCheckerDao.approveDisbursedData(disbursedData,userId);
	}

	
	public int rejectDisbursedData(List rejectDataList, String remark,
			String userId) {
		// TODO Auto-generated method stub
		return sanctionCheckerDao.rejectDisbursedData(rejectDataList,remark,userId);
	}



	

}
