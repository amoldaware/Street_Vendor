package com.nbfc.service;

import java.util.List;
import java.util.Map;

import com.nbfc.model.District;
import com.nbfc.model.DistrictName;
import com.nbfc.model.State;
import com.nbfc.model.StateName;
/**
 * @author Saurav Tyagi 2017
 * 
 */

public interface DistrictService {
	public Map<String, String> listDistricts(String state) throws NullPointerException;
	
	
	
	// public List<District> loadDistrict(String state);

	//public Map<String, String> loadDistrict(String state);

	 //public List loadDistrict(String state);
	
}
