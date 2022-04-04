package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.StateDao;
import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIReating;
import com.nbfc.model.State;
import com.nbfc.model.StateMaster;
/**
 * @author Saurav Tyagi 2017
 * 
 */

@Service("stateService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StateServiceImpl implements StateService {
	@Autowired
	StateDao stateDao;

	
	public List<State> listStates(String status) {

		return stateDao.listStates(status);
	}

	
	public MLIInfo userInfo(String longName) {
		
		return stateDao.userInfo(longName);
	}

	
	public List<StateMaster> listStateMaster() {
		
		return stateDao.listStateMaster();
	}

	
	public List<MLIReating> mliRatingList() throws NullPointerException {
	
		return stateDao.mliRatingList();
	}


	public StateMaster stateName(String stateCode) {
		return stateDao.stateName(stateCode);
	}
	
}
