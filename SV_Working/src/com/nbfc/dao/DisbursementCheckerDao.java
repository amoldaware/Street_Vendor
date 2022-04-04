package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.nbfc.model.DisbursementForApprovalModel;
import com.raistudies.domain.CustomExceptionHandler;

public interface DisbursementCheckerDao {

	//List<DisbursementCheckerApprovalModel> getPortfolioNumber();

	List<DisbursementCheckerApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);


	List<DisbursementCheckerApprovalModel> getPortfolioNoForCheckerApproval();


	int approveDisbursedData(List disbursedData, String userId)throws CustomExceptionHandler;

	int rejectDisbursedData(List rejectDataList, String remark, String userId);

}
