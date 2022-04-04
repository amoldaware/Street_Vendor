package com.nbfc.service;

import java.util.List;

import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbission;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbissionSave;

public interface CGTMSEMakerRejectionAndSumbissionService {
	public boolean cgtmseMakerRejected(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj4);
	public List<CGTMSEMakerRejectionAndSumbission> getDataForNBFCMakerFileDownLoad(CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbission);
	public boolean storeData(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSave);
	

}
