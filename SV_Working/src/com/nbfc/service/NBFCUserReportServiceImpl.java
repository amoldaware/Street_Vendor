package com.nbfc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.GetMLINameBean;
import com.nbfc.dao.NBFCUserReportDao;
import com.nbfc.model.NBFCUserPortfolioDetails;

@Service("NBFCUserReportService")
@Transactional()
public class NBFCUserReportServiceImpl implements NBFCUserReportService{

	@Autowired
	NBFCUserReportDao nbfcUserReportDao;
	
	
	public List<NBFCUserPortfolioDetails> getPortfolioDetailForUserReport(String mliID) {
		// TODO Auto-generated method stub
		return nbfcUserReportDao.getPortfolioDetailForUserReport(mliID);
	}

	
	public List<Map<String, Object>> getUserDashboardDetails(String userId) {
		// TODO Auto-generated method stub
		return nbfcUserReportDao.getUserDashboardDetails(userId);
	}

	
	public GetMLINameBean getMliDetails(String userId) {
		// TODO Auto-generated method stub
		return nbfcUserReportDao.getMliDetails(userId);
	}

}
