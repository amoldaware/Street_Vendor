package com.nbfc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExposureWiseGuaranteeDataService {

	public List<Map<String, Object>> getExposureGuaranteeData(String userId,String role,Date toDate,Date fromDate);

	public List<Map<String, Object>> getMliExposureDataByMLIName(String userId,String EXPOSURE_ID,String role,Date toDate,Date fromDate);

	public List<Map<String, Object>> getMliExposureDataBYPortfoioName(String userId,String portfolioName,String role,Date toDate,Date fromDate);
	
	//public List<Map<String, Object>> getExcelDataBYPortfoioName(String userId,String excelPortfolioName,String role,Date toDate,Date fromDate);

	public List<Map<String, Object>> getMliExposureDataBYFileId(String userId,String fileId,String role,Date toDate,Date fromDate);
	
	
	//download under portfolioname
	public ArrayList<List> getGETuploadedFileData(String userId,String fileId,String role,Date toDate,Date fromDate);
	
	//download under fileId
	public ArrayList<List> getExposureDataBYFileId(String userId,String fileId,String role,Date toDate,Date fromDate);
}
