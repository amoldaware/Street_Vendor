package com.nbfc.service;

import java.util.List;
import java.util.Map;

import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumberInfo;
import com.nbfc.model.PortfolioValidate;
/**
 * @author Saurav Tyagi 2017
 * 
 */
public interface PortfolioInfoService {

	public Map<String, String> portfolioAppRefNumber(String baseYr,String mliExposureId) throws NullPointerException;
	
	public Map<String, String> getDetailsForMLI(String mliName) throws NullPointerException;
	
	public Map<String, String> portfoloQuarterNumber(String protfolioNumber,List<Integer> qNumberlist) throws NullPointerException;
	
	public PortfolioNumInfo PortfolioInfo(String portfolioNum) throws NullPointerException;	
	
	public boolean addMILDetails(List<MliMakerEntity> mliMakerEntity) throws NullPointerException;
	
	public String portfolioAmountCount(String portfolioNumber) throws NullPointerException;
	
	public int portfolioSuccessRecordsCount(String portfolioNumber) throws NullPointerException;
	
	public int portfolioFailedRecordsCount(String portfolioNumber)throws NullPointerException;
	
	public PortfolioNumberInfo portfolioNumberInfo(String portfolioName)throws NullPointerException;
	
	public PortfolioNumInfo getPortfolioNum(String pprtfolioName) throws NullPointerException;
	
	}
