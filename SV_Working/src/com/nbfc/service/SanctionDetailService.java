package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbfc.bean.SanctionDetailBean;
import com.nbfc.model.PortfolioMSTModel;
import com.nbfc.model.SanctionDetailsChild;
import com.nbfc.model.SanctionDetailsExposureLimitModel;
import com.nbfc.model.SanctionDetailsSave;



public interface SanctionDetailService {
	public List<Object[]>getLongName();
	public ArrayList getShortNameOnChangeOfLongName(SanctionDetailBean sanctionDetailBean);
	public List<SanctionDetailsExposureLimitModel> getExposureLimit(SanctionDetailsExposureLimitModel sanctionDetailsExposureLimitModelObj);
	public long getUtilisiedExposureLimit(String longName);
	
	//public void addSanctionDetails(SanctionDetailsSave sanctionDetailsSave);
	public void addSanctionDetails(PortfolioMSTModel portfolioMSTModel);
	
	public void addSanctionDetails1(SanctionDetailsChild sanctionDetailsChild);
	//public ArrayList getGeneratedPortfolioCount();
	public int getGeneratedPortfolioCount();
	public Double getSumOfMaxPortfolio(Double expID);

}
