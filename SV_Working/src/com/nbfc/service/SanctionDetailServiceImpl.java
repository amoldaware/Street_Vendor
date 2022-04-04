package com.nbfc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.SanctionDetailBean;
import com.nbfc.dao.SanctionDetailDao;
import com.nbfc.model.PortfolioMSTModel;
import com.nbfc.model.SanctionDetailsChild;
import com.nbfc.model.SanctionDetailsExposureLimitModel;
import com.nbfc.model.SanctionDetailsSave;

@Service("sanctionDetailService")
@Transactional()

public class SanctionDetailServiceImpl implements SanctionDetailService{
	public SanctionDetailServiceImpl(){
		System.out.println("Inside  SanctionDetailServiceImpl class===");
	}
	
	@Autowired
	private  SanctionDetailDao sanctionDetailDao;
	
	
	
	public List<Object[]> getLongName() {
		System.out.println("Inside getLongName method as part of SanctionDetailServiceImpl class==");
		return sanctionDetailDao.getLongName();
	}

	
	public ArrayList getShortNameOnChangeOfLongName(SanctionDetailBean sanctionDetailBean) {
		return sanctionDetailDao.getShortNameOnChangeOfLongName(sanctionDetailBean);
	}
	
	
	
	public List<SanctionDetailsExposureLimitModel> getExposureLimit(SanctionDetailsExposureLimitModel sanctionDetailsExposureLimitModelObj) {
		System.out.println("Inside the getExposureLimit of SanctionDetailServiceImpl class===");
		return sanctionDetailDao.getExposureLimit(sanctionDetailsExposureLimitModelObj);
	}
	
	
	public long getUtilisiedExposureLimit(String longName) {
		System.out.println("Inside the getUtilisiedExposureLimit of SanctionDetailServiceImpl class===");
		return sanctionDetailDao.getUtilisiedExposureLimit(longName);
	}
	
	
	public void addSanctionDetails(PortfolioMSTModel portfolioMSTModel) {
		sanctionDetailDao.addSanctionDetails(portfolioMSTModel);
	}
	
	
	public void addSanctionDetails1(SanctionDetailsChild sanctionDetailsChild) {
		sanctionDetailDao.addSanctionDetails1(sanctionDetailsChild);
	}
	
	public int getGeneratedPortfolioCount() {
		return sanctionDetailDao.getGeneratedPortfolioCount();
	}
	
	
	public Double getSumOfMaxPortfolio(Double expID) {
		return sanctionDetailDao.getSumOfMaxPortfolio(expID);
	}
	
}
