package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.BankMandateForm;

public interface BankMandateFormDao {
	
	public List<BankMandateFormBo> findBankMandateForm(String mliId,String usrId)throws BusinessException;
	public boolean saveBankMandateFormDataMli(BankMandateForm bankMandate)throws BusinessException;
	public BankMandateFormBo findBankMandateFormData(String mliId,String usrId)throws BusinessException;
	public boolean saveBankMandateFormDataMliChecker(BankMandateFormBo bankMandate)throws BusinessException;
	public BankMandateForm findBankMandateDataStutusWise(String mliId,String status)throws BusinessException;
	
	public BankMandateForm getBankMandateStatusData(String mliId,String usrId)throws BusinessException;
	
	public List<BankMandateFormBo> findAllCgtmseApprovalByUserId(String userId,String apprvStatus) throws BusinessException;

	public boolean updateCgtmseApproal(BankMandateFormBo bo) throws BusinessException;
	
	public BankMandateForm downloadCgtmseApproal(String mliId) throws BusinessException;
	
	//Added by Sarita on 24022022 [START]
	public boolean checkBankMandateExist(String mliId,String userId) throws BusinessException;
	//Added by Sarita on 24022022 [END]
	
}
