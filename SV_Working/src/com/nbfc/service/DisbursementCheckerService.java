package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.raistudies.domain.CustomExceptionHandler;

public interface DisbursementCheckerService {

	public 	List<DisbursementCheckerApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber);


	public List<DisbursementCheckerApprovalModel> getPortfolioNoForCheckerApproval();



	public int approveDisbursedData(List disbursedData, String userId)throws CustomExceptionHandler;

	public int rejectDisbursedData(List rejectDataList, String remark,
			String userId);

}
