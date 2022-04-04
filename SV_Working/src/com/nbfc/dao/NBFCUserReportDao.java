package com.nbfc.dao;

import java.util.List;
import java.util.Map;

import com.nbfc.bean.GetMLINameBean;
import com.nbfc.model.NBFCUserPortfolioDetails;

public interface NBFCUserReportDao {

	public List<NBFCUserPortfolioDetails> getPortfolioDetailForUserReport(String mliID);
	
	public List<Map<String, Object>> getUserDashboardDetails(String userId);
	
	public GetMLINameBean getMliDetails(String userId);
}
