package com.nbfc.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.dao.PortfolioInfoDao;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumberInfo;
/**
 * @author Saurav Tyagi 2017
 * 
 */

@Service("PortfolioInfoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PortfolioInfoServiceImpl implements PortfolioInfoService {

	@Autowired
	PortfolioInfoDao portfolioInfoDao;

	
	public Map<String, String> portfolioAppRefNumber(String baseYr,String mliExposureId) {
		return portfolioInfoDao.portfolioAppRefNumber(baseYr,mliExposureId);
	}

	
	public PortfolioNumInfo PortfolioInfo(String portfolioNum) {
		return portfolioInfoDao.PortfolioInfo(portfolioNum);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addMILDetails(List<MliMakerEntity> mliMakerEntity) {
		return portfolioInfoDao.addMILDetails(mliMakerEntity);
	}

	
	public Map<String, String> portfoloQuarterNumber(String protfolioNumber,
			List<Integer> qNumberlist) {
		return portfolioInfoDao.portfoloQuarterNumber(protfolioNumber,
				qNumberlist);
	}

	
	public String portfolioAmountCount(String portfolioNumber)
			throws NullPointerException {
		return portfolioInfoDao.portfolioAmountCount(portfolioNumber);
	}

	
	public int portfolioSuccessRecordsCount(String portfolioNumber)
			throws NullPointerException {
		
		return portfolioInfoDao.portfolioSuccessRecordsCount(portfolioNumber);
	}

	
	public int portfolioFailedRecordsCount(String portfolioNumber)
			throws NullPointerException {
		
		return portfolioInfoDao.portfolioFailedRecordsCount(portfolioNumber);
	}

	
	public PortfolioNumberInfo portfolioNumberInfo(String portfolioName)
			throws NullPointerException {
		
		return portfolioInfoDao.portfolioNumberInfo(portfolioName);
	}

	
	public Map<String, String> getDetailsForMLI(String mliName)
			throws NullPointerException {
		
		return portfolioInfoDao.getDetailsForMLI(mliName);
	}

	
	public PortfolioNumInfo getPortfolioNum(String pprtfolioName)
			throws NullPointerException {
		// TODO Auto-generated method stub
		return portfolioInfoDao.getPortfolioNum(pprtfolioName);
	}

	

}
