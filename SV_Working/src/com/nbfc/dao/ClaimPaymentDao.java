package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimPaymentExcelBO;
import com.nbfc.exception.BusinessException;

public interface ClaimPaymentDao {

	public List<ClaimPaymentBO> findMliList(String mliId) throws BusinessException;

	public List<ClaimPaymentBO> findAll(ClaimPaymentBO bo) throws BusinessException;

	public boolean save(List<ClaimPaymentBO> listBO) throws BusinessException;

	public boolean generateVoucherForApprovalByUserId(String userId) throws BusinessException;
	
	public List<ClaimPaymentExcelBO> getAllExelData(String dataList) throws BusinessException;
}
