package com.nbfc.dao;


import java.util.List;

import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public interface PortfolioApprovalDao {
	
	public boolean approvePortfolioStatus(String appRefNo ) throws NullPointerException;
	
	public void addCheckerApproval(List<MLIMakerApprovalRejection> employee) throws NullPointerException;
	
	public boolean updateStatusMLIChkAppLodge(String usrId,String status,String prtfoliNum);

}
