package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.dao.BankMandateFormDao;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.BankMandateForm;

@Service
public class BankMandateFormServiceImpl implements BankMandateFormService {
	final static Logger logger = Logger.getLogger(BankMandateFormServiceImpl.class.getName());

	@Autowired
	BankMandateFormDao bankMandateFormDao;

	@Override
	public List<BankMandateFormBo> findBankMandateForm(String mliId, String usrId) throws BusinessException {
		List<BankMandateFormBo> bankMandateFormInfo = new ArrayList<>();
		try {
			bankMandateFormInfo = bankMandateFormDao.findBankMandateForm(mliId, usrId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bankMandateFormInfo;

	}

	@Override
	public boolean saveBankMandateFormDataMli(BankMandateFormBo bo) throws BusinessException {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		BankMandateForm bankMandateObj = new BankMandateForm();
		try {
			if(bo.getBankId()!=null && !bo.getBankId().isEmpty()) {
				bankMandateObj.setId(Integer.parseInt(bo.getBankId()));
			}
			bankMandateObj.setMliName(bo.getMlIName());
			bankMandateObj.setMliId(bo.getMilId());
			bankMandateObj.setMliContactNo(bo.getMliContactNo());
			bankMandateObj.setMliMobileNo(bo.getMliMobileNo());
			bankMandateObj.setMliEmailId(bo.getMliEmailId());
			bankMandateObj.setBankName(bo.getBankName());
			bankMandateObj.setBranchName(bo.getBranch());
			bankMandateObj.setAccountName(bo.getAccName());
			bankMandateObj.setAccountNo(bo.getAccNum());
			bankMandateObj.setTypeOfAcc(bo.getTypeOfAcc());
			bankMandateObj.setIfscCode(bo.getIfscCode());
			bankMandateObj.setUploadDoc(bo.getUploadDoc());
			bankMandateObj.setDeclartionFlag(bo.getDeclaration());
			bankMandateObj.setCreatedBy(bo.getLoginId());
			bankMandateObj.setMakerRemarks(bo.getmRemarks());
			bankMandateObj.setBankMandatFileDate(bo.getFileData());
			bankMandateObj.setApprovalStatus(bo.getApproveStatus());
			isSuccess = bankMandateFormDao.saveBankMandateFormDataMli(bankMandateObj);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public BankMandateFormBo findDataByUsrId(String mliId, String usrId) throws BusinessException {
		// TODO Auto-generated method stub
		BankMandateFormBo bankMandateFileData = null;
		try {
			bankMandateFileData = bankMandateFormDao.findBankMandateFormData(mliId, usrId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bankMandateFileData;
	}

	@Override
	public boolean saveBankMandateFormDataMliChecker(BankMandateFormBo bo) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = bankMandateFormDao.saveBankMandateFormDataMliChecker(bo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public BankMandateFormBo getBankMandateDataStatusWise(String mliId, String status) throws BusinessException {
		BankMandateFormBo bankMandateBo = null;
		try {
			BankMandateForm bnkMandate = bankMandateFormDao.findBankMandateDataStutusWise(mliId, status);
			bankMandateBo = new BankMandateFormBo();

			if (bnkMandate != null) {
				bankMandateBo.setApproveStatus(bnkMandate.getApprovalStatus());
			} else {
				bankMandateBo.setApproveStatus("NotApproved");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bankMandateBo;

	}

	@Override
	public BankMandateFormBo findBankMandateFormStatus(String mliId, String usrId) throws BusinessException {
		BankMandateFormBo bankMandate = null;
		try {
			BankMandateForm bnkMandate = bankMandateFormDao.getBankMandateStatusData(mliId, usrId);
			bankMandate = new BankMandateFormBo();

			if (bnkMandate != null) {
				bankMandate.setId(bnkMandate.getId());
				bankMandate.setMilId(bnkMandate.getMliId());
				bankMandate.setMlIName(bnkMandate.getMliName());
				bankMandate.setMliContactNo(bnkMandate.getMliContactNo());
				bankMandate.setMliMobileNo(bnkMandate.getMliMobileNo());
				bankMandate.setMliEmailId(bnkMandate.getMliEmailId());
				bankMandate.setBankName(bnkMandate.getBankName());
				bankMandate.setBranch(bnkMandate.getBranchName());
				bankMandate.setAccName(bnkMandate.getAccountName());
				bankMandate.setAccNum(bnkMandate.getAccountNo());
				bankMandate.setTypeOfAcc(bnkMandate.getTypeOfAcc());
				bankMandate.setIfscCode(bnkMandate.getIfscCode());
				bankMandate.setUploadDoc(bnkMandate.getUploadDoc());
				bankMandate.setDeclaration(bnkMandate.getDeclartionFlag());
				bankMandate.setApproveStatus(bnkMandate.getApprovalStatus());
				bankMandate.setcRemarks(bnkMandate.getCheckerRemarks());
				bankMandate.setmRemarks(bnkMandate.getMakerRemarks());
				
			} else {
				bankMandate.setApproveStatus(" ");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bankMandate;

	}
	
	@Override
	public List<BankMandateFormBo> getAllCgtmseApprovalByUserId(String userId,String apprvStatus) throws BusinessException {
		List<BankMandateFormBo> list = null;
		try {
			list = bankMandateFormDao.findAllCgtmseApprovalByUserId(userId,apprvStatus);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean updateCgtmseApproal(BankMandateFormBo bo) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = bankMandateFormDao.updateCgtmseApproal(bo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}
	
	@Override
	public BankMandateFormBo downloadCgtmseApproal(String mliId) throws BusinessException {
		BankMandateFormBo bo = null;
		try {
			BankMandateForm cgtmsedata = bankMandateFormDao.downloadCgtmseApproal(mliId);
			if (cgtmsedata != null) {
				bo = new BankMandateFormBo();
				bo.setFileData(cgtmsedata.getBankMandatFileDate());
				bo.setFileName(cgtmsedata.getUploadDoc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public boolean checkBankMandateExist(String mliId,String userId) throws BusinessException 
	{
		boolean isBnkExist = false;
		try
		{
			isBnkExist = bankMandateFormDao.checkBankMandateExist(mliId,userId);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return isBnkExist;
	}
}
