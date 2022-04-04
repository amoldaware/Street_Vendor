package com.nbfc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimPaymentExcelBO;
import com.nbfc.dao.ClaimPaymentDao;
import com.nbfc.exception.BusinessException;

@Service
public class ClaimPaymentServiceImpl implements ClaimPaymentService {
	final static Logger logger = Logger.getLogger(ClaimPaymentServiceImpl.class.getName());

	@Autowired
	ClaimPaymentDao claimPaymentDao;

	@Override
	public List<ClaimPaymentBO> getMliList(String mliId) throws BusinessException {
		List<ClaimPaymentBO> mliList = null;
		try {
			mliList = claimPaymentDao.findMliList(mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mliList;
	}

	@Override
	public List<ClaimPaymentBO> getAll(ClaimPaymentBO bo) throws BusinessException {
		List<ClaimPaymentBO> claimPaymentList = null;
		try {
			claimPaymentList = claimPaymentDao.findAll(bo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimPaymentList;
	}

	@Override
	public boolean save(List<ClaimPaymentBO> listBO) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = claimPaymentDao.save(listBO);
			if (isSuccess) {
				if (listBO.get(0).getApprovalAction().equalsIgnoreCase("APPROVE")) {
					isSuccess = claimPaymentDao.generateVoucherForApprovalByUserId(listBO.get(0).getUserName());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}
	

	@Override
	public List<ClaimPaymentExcelBO> getAllExelData(String dataList) throws BusinessException {
		List<ClaimPaymentExcelBO> claimPaymentList = null;
		try {
			claimPaymentList = claimPaymentDao.getAllExelData(dataList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimPaymentList;
	}


}
