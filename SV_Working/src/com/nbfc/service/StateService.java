package com.nbfc.service;

import java.util.List;

import com.nbfc.model.MLIInfo;
import com.nbfc.model.MLIReating;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.State;
import com.nbfc.model.StateMaster;

/**
 * @author Saurav Tyagi 2017
 * 
 */

public interface StateService {

	public List<State> listStates(String status) throws NullPointerException;

	public MLIInfo userInfo(String longName) throws NullPointerException;

	public List<StateMaster> listStateMaster();

	public StateMaster stateName(String stateCode);

	public List<MLIReating> mliRatingList() throws NullPointerException;
}
