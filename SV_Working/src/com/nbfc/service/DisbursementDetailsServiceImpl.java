package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LenderDetailsBO;
import com.nbfc.dao.DisbursementDetailsDao;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ApplicationDetailsBean;
import com.nbfc.model.LenderBean;

@Service
public  class DisbursementDetailsServiceImpl implements DisbursementDetailsService
{
	final static Logger logger = Logger.getLogger(DisbursementDetailsServiceImpl.class.getName());
	
	@Autowired
	DisbursementDetailsDao disbursementDetailsDao;

	@Override
	public List<LenderDetailsBO> getLenderDetails() throws BusinessException 
	{
		List<LenderDetailsBO> lbData = null; 
		try
		{
			lbData = disbursementDetailsDao.getLenderDetails();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return lbData;
	}

	@Override
	public String saveLenderDetails(Map<String, List> lenderDetails) throws BusinessException
	{
		String result = "";
		try
		{
			result = disbursementDetailsDao.saveLenderDetails(lenderDetails);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public List<ApplicationDetailsBean> getDisburmentDetailsData(int lenderCode,String fromDate,String toDate,String applicationNo,String status,String guaranteeIssue) throws BusinessException {

		List<ApplicationDetailsBean> lbData = null; 
		try
		{
			lbData = disbursementDetailsDao.getDisburmentDetailsData(lenderCode,fromDate,toDate,applicationNo,status,guaranteeIssue);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return lbData;
	
	}

	@Override
	public String getLenderData(int lenderCode) throws BusinessException {

		String lbData = ""; 
		try
		{
			lbData = disbursementDetailsDao.getLenderData(lenderCode);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return lbData;
	
	}

	@Override
	public List getApllicationNoList(int lenderCode) throws BusinessException {
		List<String> applicationNoList = new ArrayList();
		try
		{
			applicationNoList = disbursementDetailsDao.getApllicationNoList(lenderCode);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return applicationNoList;
	}

	@Override
	public int updateLenderDetails() throws BusinessException {
		int count = 0;
		try
		{
			count = disbursementDetailsDao.updateLenderDetails();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<ApplicationDetailsBean> getDisburseData(List<FileExcelDataBO> list) 
	{
		List<ApplicationDetailsBean> data = null; 
		try
		{
			data = disbursementDetailsDao.getDisburseData(list); 
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void updateMasterTableData() 
	{
		try
		{
			disbursementDetailsDao.updateMasterTableData();
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveUnmatcherMasterTableData() {
		try
		{
			disbursementDetailsDao.saveUnmatcherMasterTableData();
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	
}
