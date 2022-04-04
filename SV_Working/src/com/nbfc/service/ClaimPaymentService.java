package com.nbfc.service;

import java.util.List;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimPaymentExcelBO;
import com.nbfc.exception.BusinessException;

/**
 * @author umar.kumar
 */
public interface ClaimPaymentService {

	public List<ClaimPaymentBO> getMliList(String mliId) throws BusinessException;

	public List<ClaimPaymentBO> getAll(ClaimPaymentBO bo) throws BusinessException;

	public boolean save(List<ClaimPaymentBO> listBO) throws BusinessException;
	
	public List<ClaimPaymentExcelBO> getAllExelData(String dataList) throws BusinessException;

}
