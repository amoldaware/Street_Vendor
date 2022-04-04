package com.nbfc.dao;

import java.util.List;
import java.util.Map;

import com.nbfc.model.MLIMaker;
import com.nbfc.model.MLIMakerApprovalRejection;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.PortfolioNumberDetails;
import com.nbfc.model.PortfolioValidate;
import com.nbfc.model.State;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public interface PortfolioBatchAppRefNoDao {

	//public Map<String, String> listPortfolioBatchRefNo(String status);
	public List<PortfolioBatchApp> appRefNoList(String status) throws NullPointerException;
	
	public List<MLIMakerApprovalRejection> getPortfolioDetail(String appRefNo)throws NullPointerException;
	
	public List<MLIMakerApprovalRejection> getPortfolioFailedRecords(String appRefNo)throws NullPointerException;
	
	public List<MliMakerEntity> getPortfolioDet(String portFolioNo)throws NullPointerException;
	
	public List<PortfolioValidate> getLoanaccountNumber() throws NullPointerException;
	
	public PortfolioNumberDetails getPortfolioNUmber(String portfolioName);
}
