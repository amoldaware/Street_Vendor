package com.nbfc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nbfc.bean.ApplicationStatusDetailsBean;

@Repository("ApplicationStatusDao")
public interface ApplicationStatusDao {
	public List<Map<String, Object>> getApplicationStatus(String userId, Date toDate, Date fromDate, String status);

	public List<Map<String, Object>> getFileData(String FileId);

	public ApplicationStatusDetailsBean getapplicationDetails(String FileId);

	public ArrayList<List> getGETuploadedFileData(String fileId);

	public List<Map<String, Object>> getstreetVendorReport(String userId, Date toDate, Date fromDate,
			String mliLongName, String role,String pmsNo);

	public String getMliNameByMliId(String mliId);

	public List<Map<String, Object>> findSummaryReport(String date,String date1);
	public List<String> getLenderName();

	public Map<String, String> getAllMliNames();

	public long getCount(String lenderName);

	public Map<String, String> getHoUser(String mliId);

	public void updateData(String lendername, String user);
	
	public Map<Integer, String> getDistrictDetails(String state);
	
	public List<Map<String, Object>> getStateActivityDetails(Date fromDate, Date toDate, String state, String district,
			String activity);
}
