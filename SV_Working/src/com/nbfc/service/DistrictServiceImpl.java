package com.nbfc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.DistrictDao;
import com.nbfc.model.District;
import com.nbfc.model.DistrictName;
import com.nbfc.model.State;
import com.nbfc.model.StateName;
/**
 * @author Saurav Tyagi 2017
 * 
 */

@Service("districtService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	DistrictDao districtDao;

	/*
	public List<District> loadDistrict(String state) {
		
		return districtDao.loadDistrict(state);
	}*/
	
	
	public Map<String, String> listDistricts(String state) {
		return districtDao.listDistricts(state);
	}

	
}
