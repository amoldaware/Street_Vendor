package com.nbfc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.StateWiseReportDao;

@Service("StateWiseReportService")
@Transactional()
public class StateWiseReportServiceImpl implements StateWiseReportService{
	
	@Autowired
	StateWiseReportDao stateWiseReportDao;

	@Override
	public List<Map<String, Object>> getStateDetails(String userId,String role,
			Date toDate, Date fromDate, String status) {
		// TODO Auto-generated method stub
		return stateWiseReportDao.getStateDetails(userId,role, toDate, fromDate, status);
	}

	@Override
	public List<Map<String, Object>> getStateMliWiseDetails(String userId,String role,
			Date toDate, Date fromDate, String state) {
		// TODO Auto-generated method stub
		return stateWiseReportDao.getStateMliWiseDetails(userId,role, toDate, fromDate, state);
	}

	@Override
	public List<Map<String, Object>> getStateMliWiseGIssuedDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {
		// TODO Auto-generated method stub
		return stateWiseReportDao.getStateMliWiseGIssuedDetails(userId,role, toDate, fromDate, state);
	}

	@Override
	public List<Map<String, Object>> getGuaranteeApprovedDistWiseDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {
		// TODO Auto-generated method stub
		return stateWiseReportDao.getGuaranteeApprovedDistWiseDetails(userId,role, toDate, fromDate, state);
	}

	@Override
	public List<Map<String, Object>> getGuaranteeIssuedDistWiseDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {
		// TODO Auto-generated method stub
		return stateWiseReportDao.getGuaranteeIssuedDistWiseDetails(userId,role, toDate, fromDate, state);
	}

}
