
package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.model.SanctionCheckerModel;
import com.raistudies.domain.CustomExceptionHandler;

public interface SanctionCheckerDao {



	List<SanctionCheckerModel> getPortfolioNoForCheckerApproval();


	int approveDisbursedData(List disbursedData, String userId)throws CustomExceptionHandler;

	int rejectDisbursedData(List rejectDataList, String remark, String userId);


	List<SanctionCheckerBean> getSanctionCheckerDataForApproval(
			String portfolioNo, String loanAccountNumber);

}
