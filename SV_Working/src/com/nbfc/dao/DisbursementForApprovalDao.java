package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;

public interface DisbursementForApprovalDao {
	//public List<DisbursementForApprovalModel> getPortfolioNumber();
	public List<DisbursementForApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);
	public int updateDisbursedData(List<DisbursementForApprovalModel> disbursedData,String userId);
}
