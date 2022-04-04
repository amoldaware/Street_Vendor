package com.nbfc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.ExposureWiseGuaranteeDao;
import com.nbfc.dao.StateWiseReportDao;
@Service("ExposureWiseGuaranteeDataService")
@Transactional()
public class ExposureWiseGuaranteeDataServiceImpl implements ExposureWiseGuaranteeDataService{

	@Autowired
	ExposureWiseGuaranteeDao exposureWiseGuaranteeDao;
	
	@Override
	public List<Map<String, Object>> getExposureGuaranteeData(String milName,
			String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getExposureGuaranteeData(milName, role, toDate, fromDate);
	}

	@Override
	public List<Map<String, Object>> getMliExposureDataByMLIName(String userId,
			String EXPOSURE_ID, String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getMliExposureDataByMLIName(userId,EXPOSURE_ID, role, toDate, fromDate);
	}

	@Override
	public List<Map<String, Object>> getMliExposureDataBYPortfoioName(String userId,
			String portfolioName, String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getMliExposureDataBYPortfoioName(userId,portfolioName, role, toDate, fromDate);
	}

	@Override
	public List<Map<String, Object>> getMliExposureDataBYFileId(String userId,
			String fileId, String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getMliExposureDataBYFileId(userId,fileId, role, toDate, fromDate);
	}

	/*@Override
	public List<Map<String, Object>> getExcelDataBYPortfoioName(String userId,
			String excelPortfolioName, String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getExcelDataBYPortfoioName(userId, excelPortfolioName, role, toDate, fromDate);
	}*/

	@Override
	public ArrayList<List> getGETuploadedFileData(String userId, String fileId,
			String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getGETuploadedFileData(userId, fileId, role, toDate, fromDate);
	}

	@Override
	public ArrayList<List> getExposureDataBYFileId(String userId,
			String fileId, String role, Date toDate, Date fromDate) {
		// TODO Auto-generated method stub
		return exposureWiseGuaranteeDao.getExposureDataBYFileId(userId, fileId, role, toDate, fromDate);
	}

	
}
