
package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.nbfc.model.SanctionCheckerModel;
import com.raistudies.domain.CustomExceptionHandler;

public interface SanctionCheckerService {

	

	public List<SanctionCheckerModel> getPortfolioNoForCheckerApproval();



	public int approveDisbursedData(List disbursedData, String userId)throws CustomExceptionHandler;

	public int rejectDisbursedData(List rejectDataList, String remark,
			String userId);


	public List<SanctionCheckerBean> getSanctionCheckerDataForApproval(
			String portfolioNo, String loanAccountNumber);

}
