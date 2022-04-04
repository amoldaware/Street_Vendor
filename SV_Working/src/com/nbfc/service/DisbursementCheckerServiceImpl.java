package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.dao.DisbursementCheckerDao;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.nbfc.model.DisbursementForApprovalModel;
import com.raistudies.domain.CustomExceptionHandler;
@Service("disbursementCheckerService")
@Transactional()
public class DisbursementCheckerServiceImpl implements DisbursementCheckerService{
	@Autowired
	private DisbursementCheckerDao disbursementCheckerDao;
	/*
	public List<DisbursementCheckerApprovalModel> getPortfolioNumber() {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.getPortfolioNumber();
	}*/

	
	public List<DisbursementCheckerApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.getDisburseDataForApproval(portfolioNo, loanAccountNumber);
	}

	

	
	public List<DisbursementCheckerApprovalModel> getPortfolioNoForCheckerApproval() {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.getPortfolioNoForCheckerApproval();

	}

	

	
	public int approveDisbursedData(List disbursedData, String userId)
			throws CustomExceptionHandler {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.approveDisbursedData(disbursedData,userId);
	}

	
	public int rejectDisbursedData(List rejectDataList, String remark,
			String userId) {
		// TODO Auto-generated method stub
		return disbursementCheckerDao.rejectDisbursedData(rejectDataList,remark,userId);
	}

}
