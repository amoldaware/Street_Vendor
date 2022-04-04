package com.nbfc.service;

import java.util.HashMap;
import java.util.List;

import com.nbfc.model.DistrictName;
import com.nbfc.model.NBFCHeaderDetails;
import com.nbfc.model.PromoterDetails;
import com.nbfc.model.SSIDetail;
import com.nbfc.model.StateName;
/**
 * @author Saurav Tyagi 2017
 * 
 */
public interface MLIValidatorService {

	public List<PromoterDetails> getDetails() throws NullPointerException;

	public List<SSIDetail> getSSIDetails() throws NullPointerException;
	
    public List<DistrictName> getStateCode();
	
	public List<StateName> getStateName();
	
	public HashMap<Integer, String> getHeaderDetails();

}
