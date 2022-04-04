package com.nbfc.service;

import java.util.List;
import java.util.Map;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.NBFCASFUpdationBean;


public interface NBFCASFUpdationService {

	public List getASFDetails(String danId);
	
	public List<NBFCASFUpdationBean> getASFDetailsByPortfolio(String userId);

	public int approveASFData(List<String> checkedData, String userId);

	public List<ASFGenerationDetailBean> getAllUpdatedOSAmtDetails(String userId, String fileid);
}
