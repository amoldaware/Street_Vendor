package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;


public interface DisbursementForApprovalService {
	//public List<DisbursementForApprovalModel> getPortfolioNumber();
	public List<DisbursementForApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);
	//public List<DisbursementForApprovalModel> updateDisbursedData();
	public int updateDisbursedData(List<DisbursementForApprovalModel> disbursedData,String userId);
}
