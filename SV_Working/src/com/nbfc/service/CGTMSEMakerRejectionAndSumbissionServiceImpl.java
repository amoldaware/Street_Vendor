package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbfc.dao.CGTMSEMakerRejectionAndSumbissionDao;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbission;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbissionSave;

@Service("cgtmseMakerRejectionAndSumbissionService")
@Transactional()
public class CGTMSEMakerRejectionAndSumbissionServiceImpl implements CGTMSEMakerRejectionAndSumbissionService{
	
	@Autowired
	private  CGTMSEMakerRejectionAndSumbissionDao cgtmseMakerRejectionAndSumbissionDao;
	
	
	
	public List<CGTMSEMakerRejectionAndSumbission> getDataForNBFCMakerFileDownLoad(CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbission) {
		System.out.println("Retrive Data==in CGTMSEMakerRejectionAndSumbissionServiceImpl class=="+cgtmseMakerRejectionAndSumbission.getPortfolioNo());
		return  cgtmseMakerRejectionAndSumbissionDao.getDataForNBFCMakerFileDownLoad(cgtmseMakerRejectionAndSumbission);
	}

	
	public boolean storeData(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj4) {
			return cgtmseMakerRejectionAndSumbissionDao.storeData(cgtmseMakerRejectionAndSumbissionSaveObj4);		
	}
	
	
	public boolean cgtmseMakerRejected(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj4) {
			return cgtmseMakerRejectionAndSumbissionDao.cgtmseMakerRejected(cgtmseMakerRejectionAndSumbissionSaveObj4);		
	}
	
	
}
