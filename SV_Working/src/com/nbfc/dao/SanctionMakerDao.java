
package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.SanctionMakerModel;

public interface SanctionMakerDao {
	public List<SanctionMakerModel> getPortfolioNumber();
	public List<SanctionMakerModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);
	public int updateDisbursedData(List<SanctionMakerModel> disbursedData,String userId);
}
