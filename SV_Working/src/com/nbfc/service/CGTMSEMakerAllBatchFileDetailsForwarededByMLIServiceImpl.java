package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.CGTMSEMakerAllBatchFileDetailsForwarededByMLIDao;
import com.nbfc.model.CGTMSEMakerAllBatchFileDetailsForwarededByMLI;

@Service("cgtmseMakerAllBatchFileDetailsForwarededByMLIService")
@Transactional()

public class CGTMSEMakerAllBatchFileDetailsForwarededByMLIServiceImpl implements CGTMSEMakerAllBatchFileDetailsForwarededByMLIService{
	@Autowired
	private  CGTMSEMakerAllBatchFileDetailsForwarededByMLIDao cgmseMakerAllBatchFileDetailsForwarededByMLIDao;
	/*
	public List<Object> getIndividualBatchDetailsAndSanctionAmtSum(CGTMSEMakerAllBatchFileDetailsForwarededByMLI obj) {
		System.out.println(" Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIServiceImpl#############");

		return cgmseMakerAllBatchFileDetailsForwarededByMLIDao.getIndividualBatchDetailsAndSanctionAmtSum(obj);
	}*/
	
	
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getIndividualBatchDetails(CGTMSEMakerAllBatchFileDetailsForwarededByMLI cgtmseMakerAllBatchFileDetailsForwarededByMLI) {
		System.out.println(" Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIServiceImpl#############");

		return cgmseMakerAllBatchFileDetailsForwarededByMLIDao.getIndividualBatchDetails(cgtmseMakerAllBatchFileDetailsForwarededByMLI);
	}
	public List<CGTMSEMakerAllBatchFileDetailsForwarededByMLI> getAllPortFolioNo() {
		System.out.println(" Inside CGTMSEMakerAllBatchFileDetailsForwarededByMLIServiceImpl#############");

		return cgmseMakerAllBatchFileDetailsForwarededByMLIDao.getAllPortFolioNo();
	}
	

}
