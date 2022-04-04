package com.nbfc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.dao.ApplicationStatusDao;

@Service("ApplicationStatusService")
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
	@Autowired
	ApplicationStatusDao applicationStatusDao;
	public List<Map<String, Object>> getFileData(String FileId){
		return applicationStatusDao.getFileData(FileId);

	}
	public List<Map<String, Object>> getApplicationStatus(String userId,Date toDate,Date fromDate,String status){
		return applicationStatusDao.getApplicationStatus(userId,toDate, fromDate, status);
	}
	@Override
	public ApplicationStatusDetailsBean getapplicationDetails(String FileId) {
		// TODO Auto-generated method stub
		return applicationStatusDao.getapplicationDetails(FileId);
	}
	@Override
	public ArrayList<List> getGETuploadedFileData(
			String fileId) {
		// TODO Auto-generated method stub
		return applicationStatusDao.getGETuploadedFileData(fileId);
	}
	
	public List<Map<String, Object>> getstreetVendorReport(String userId, Date toDate, Date fromDate,
			String mliLongName, String role,String pmsNo) {
		// TODO Auto-generated method stub
		return applicationStatusDao.getstreetVendorReport(userId,toDate,fromDate,mliLongName,role,pmsNo);
	}
	@Override
	public String getMliName(String mliId) {
		
		return applicationStatusDao.getMliNameByMliId(mliId);
	}

	@Override
	public List<Map<String, Object>> getSummaryReport(String date,String date1) {
		List<Map<String, Object>> findSummaryReport = null;
		try {
			findSummaryReport = applicationStatusDao.findSummaryReport(date,date1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return findSummaryReport;
	}
	@Override
	public List<String> getLenderName() {
		return applicationStatusDao.getLenderName();
	}
	public long getCount(String lenderName) {
		return applicationStatusDao.getCount(lenderName);
	}
	@Override
	public Map<String, String> getAllMliNames() {
		return applicationStatusDao.getAllMliNames();
	}
	
	@Override
	public Map<String, String> getHoUser(String mliId) {
		return applicationStatusDao.getHoUser(mliId);
	}
	@Override
	public void updateData(String lendername, String user) {
		applicationStatusDao.updateData(lendername,user);
		
	}
	@Override
	public Map<Integer, String> getDistrictDetails(String state) {
		return applicationStatusDao.getDistrictDetails(state);
	}
	@Override
	public List<Map<String, Object>> getStateActivityDetails(Date fromDate, Date toDate, String state, String district,
			String activity) {
		return applicationStatusDao.getStateActivityDetails(fromDate,toDate,state,district,activity);
	}
	
}
