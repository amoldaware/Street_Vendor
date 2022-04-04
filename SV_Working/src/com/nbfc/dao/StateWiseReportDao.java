package com.nbfc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StateWiseReportDao {

	public List<Map<String, Object>> getStateDetails(String userId,String role,Date toDate,Date fromDate,String guatanteeStatus);

	public List<Map<String, Object>> getStateMliWiseDetails(String userId,String role,Date toDate,Date fromDate,String state);

	public List<Map<String, Object>> getStateMliWiseGIssuedDetails(String userId,String role,Date toDate,Date fromDate,String state);

	public List<Map<String, Object>> getGuaranteeApprovedDistWiseDetails(String userId,String role,Date toDate,Date fromDate,String state);

	public List<Map<String, Object>> getGuaranteeIssuedDistWiseDetails(String userId,String role,Date toDate,Date fromDate,String state);

}
