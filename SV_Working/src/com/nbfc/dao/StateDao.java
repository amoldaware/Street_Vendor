package com.nbfc.dao;

import java.util.List;

import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIReating;
import com.nbfc.model.State;
import com.nbfc.model.StateMaster;

/**
 * @author Saurav Tyagi 2017
 * 
 */

public interface StateDao {
	
	public List<State> listStates(String status) throws NullPointerException;

	public MLIInfo userInfo(String longName) throws NullPointerException;

	public List<StateMaster> listStateMaster();

	public List<MLIReating> mliRatingList() throws NullPointerException;
	
	public StateMaster stateName(String stateCode);

}
