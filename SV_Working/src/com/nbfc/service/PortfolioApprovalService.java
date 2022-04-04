package com.nbfc.service;

import java.util.List;
import com.nbfc.model.MLIMakerApprovalRejection;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public interface PortfolioApprovalService {

	
	public boolean approvePortfolioStatus(String appRefNo);
	public void addCheckerApproval(List<MLIMakerApprovalRejection> employee);
	public boolean updateStatusMLIChkAppLodge(String usrId,String status,String prtfoliNum);//new Add 03092018
}
