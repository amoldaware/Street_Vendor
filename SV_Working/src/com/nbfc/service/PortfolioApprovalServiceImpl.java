package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.PortfolioApprovalDao;
import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;
/**
 * @author Saurav Tyagi 2017
 * 
 */
@Service("PortfolioApprovalService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PortfolioApprovalServiceImpl implements PortfolioApprovalService {

	@Autowired
	PortfolioApprovalDao portfolioApprovalDao;

	
	public boolean approvePortfolioStatus(String appRefNo) {
		return portfolioApprovalDao.approvePortfolioStatus(appRefNo);
	}

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCheckerApproval(List<MLIMakerApprovalRejection> employee) {
		portfolioApprovalDao.addCheckerApproval(employee);
	}

	
	public boolean updateStatusMLIChkAppLodge(String usrId, String status,
			String prtfoliNum) {
		   return portfolioApprovalDao.updateStatusMLIChkAppLodge(usrId, status, prtfoliNum);
	}

}
