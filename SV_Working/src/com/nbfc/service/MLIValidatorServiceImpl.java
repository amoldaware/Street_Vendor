package com.nbfc.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.model.DistrictName;
import com.nbfc.model.NBFCHeaderDetails;
import com.nbfc.model.PromoterDetails;
import com.nbfc.model.SSIDetail;
import com.nbfc.model.StateName;
import com.nbfc.dao.MLIValidatorDao;
/**
 * @author Saurav Tyagi 2017
 * 
 */
@Service("mliValidatorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MLIValidatorServiceImpl implements MLIValidatorService {

	@Autowired
	MLIValidatorDao mliValidatorDao;

	
	public List<PromoterDetails> getDetails() {
		return mliValidatorDao.getDetails();
	}

	
	public List<SSIDetail> getSSIDetails() {
		return mliValidatorDao.getSSIDetails();
	}

	
	public List<DistrictName> getStateCode() {
		// TODO Auto-generated method stub
		return mliValidatorDao.getStateCode();
	}

	
	public List<StateName> getStateName() {
		// TODO Auto-generated method stub
		return mliValidatorDao.getStateName();
	}

	
	public HashMap<Integer, String> getHeaderDetails() {
		// TODO Auto-generated method stub
		return mliValidatorDao.getHeaderDetails();
	}

}
