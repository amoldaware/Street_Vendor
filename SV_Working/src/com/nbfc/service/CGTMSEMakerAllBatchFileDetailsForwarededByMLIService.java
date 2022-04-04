package com.nbfc.service;

import java.util.List;

import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;

public interface CGTMSEMakerAllBatchFileDetailsForwarededByMLIService {
	//public List<Object> getIndividualBatchDetailsAndSanctionAmtSum(CGTMSEMakerAllBatchFileDetailsForwarededByMLI obj);
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getIndividualBatchDetails(CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLI);
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getAllPortFolioNo();
	
}
