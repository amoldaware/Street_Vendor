package com.nbfc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;
@Repository("cgtmseMakerAllBatchFileDetailsForwarededByMLIDao")
public interface CGTMSEMakerAllBatchFileDetailsForwarededByMLIDao {
	//public List<Object> getIndividualBatchDetailsAndSanctionAmtSum(CGTMSEMakerAllBatchFileDetailsForwarededByMLI obj);
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getIndividualBatchDetails(CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLI);
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getAllPortFolioNo();
}
