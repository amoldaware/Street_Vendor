package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.PortfolioBatchAppRefNoDao;
import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.PortfolioNumberDetails;
import com.nbfc.model.PortfolioValidate;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Service("PortfolioBatchService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PortfolioBatchServiceImpl implements PortfolioBatchService{

	
	@Autowired
	PortfolioBatchAppRefNoDao portfolioBatchAppRefNoDao;

	
	public List<PortfolioBatchApp> appRefNoList(String status) {
		return portfolioBatchAppRefNoDao.appRefNoList(status);
	}

	
	public List<MLIMakerApprovalRejection> getPortfolioDetail(String appRefNo) {
		
		return portfolioBatchAppRefNoDao.getPortfolioDetail(appRefNo);
	}

	
	public List<MliMakerEntity> getPortfolioDet(String portFolioNo) {
		// TODO Auto-generated method stub
		return portfolioBatchAppRefNoDao.getPortfolioDet(portFolioNo);
	}

	
	public List<PortfolioValidate> getLoanaccountNumber() {
	
		return portfolioBatchAppRefNoDao.getLoanaccountNumber();
	}

	
	public List<MLIMakerApprovalRejection> getPortfolioFailedRecords(
			String appRefNo) throws NullPointerException {
		
		return portfolioBatchAppRefNoDao.getPortfolioFailedRecords(appRefNo);
	}

	
	public PortfolioNumberDetails getPortfolioNUmber(String portfolioName) {
		// TODO Auto-generated method stub
		return portfolioBatchAppRefNoDao.getPortfolioNUmber(portfolioName);
	}
	
	



}
