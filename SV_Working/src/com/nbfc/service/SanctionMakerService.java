
package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.SanctionMakerModel;


public interface SanctionMakerService {
	public List<SanctionMakerModel> getPortfolioNumber();
	public List<SanctionMakerModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);
	//public List<DisbursementForApprovalModel> updateDisbursedData();
	public int updateDisbursedData(List disbursedData,String userId);
}
