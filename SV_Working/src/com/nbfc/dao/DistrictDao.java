package com.nbfc.dao;


import java.util.Map;

import com.nbfc.model.DistrictName;
import com.nbfc.model.StateName;
/**
 * @author Saurav Tyagi 2017
 * 
 */

public interface DistrictDao {
	public Map<String, String> listDistricts(String state) throws NullPointerException;
	
	
}
