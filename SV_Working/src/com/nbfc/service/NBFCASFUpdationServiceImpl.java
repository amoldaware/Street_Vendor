package com.nbfc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.NBFCASFUpdationBean;
import com.nbfc.dao.NBFCASFUpdationDao;
@Service("NBFCASFUpdationService")
public class NBFCASFUpdationServiceImpl implements NBFCASFUpdationService {

	@Autowired
	NBFCASFUpdationDao nbfcASFUpdationDao;

	@Override
	public List getASFDetails(String danId) {
		return nbfcASFUpdationDao.getASFDetails(danId);
	}

	@Override
	public List<NBFCASFUpdationBean> getASFDetailsByPortfolio(String userId) {
		// TODO Auto-generated method stub
		return nbfcASFUpdationDao.getASFDetailsByPortfolio(userId);
	}

	@Override
	public int approveASFData(List<String> checkedData, String userId) {
		// TODO Auto-generated method stub
		return nbfcASFUpdationDao.approveASFData(checkedData,userId);
	}

	@Override
	public List<ASFGenerationDetailBean> getAllUpdatedOSAmtDetails(String userId, String fileid) {
		// TODO Auto-generated method stub
		return nbfcASFUpdationDao.getAllUpdatedOSAmtDetails(userId,fileid);
	}



}
