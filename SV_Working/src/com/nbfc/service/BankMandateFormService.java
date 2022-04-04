package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.exception.BusinessException;

public interface BankMandateFormService {
	
	public List<BankMandateFormBo> findBankMandateForm(String mliId,String usrId)throws BusinessException;
	public boolean saveBankMandateFormDataMli(BankMandateFormBo bo)throws BusinessException;
	public BankMandateFormBo findDataByUsrId(String mliId,String usrId)throws BusinessException;
	public boolean saveBankMandateFormDataMliChecker(BankMandateFormBo bo)throws BusinessException;
	public BankMandateFormBo findBankMandateFormStatus(String mliId,String usrId)throws BusinessException;
	public BankMandateFormBo getBankMandateDataStatusWise(String mliId,String status)throws BusinessException;
	
	public List<BankMandateFormBo> getAllCgtmseApprovalByUserId(String userId,String apprvStatus) throws BusinessException;

	public boolean updateCgtmseApproal(BankMandateFormBo bo) throws BusinessException;
	
	public BankMandateFormBo downloadCgtmseApproal(String mliId) throws BusinessException;
	
	//Added by Sarita on 24022022 [START]
	public boolean checkBankMandateExist(String mliId,String userId1) throws BusinessException;
	//Added by Sarita on 24022022 [END]
}
