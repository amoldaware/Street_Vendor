package com.nbfc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.MLICheckerUpdateDao;

@Service("MLICheckerUpdateService")
@Transactional()
public class MLICheckerUpdateServiceImpl implements MLICheckerUpdateService{
    
	@Autowired
	MLICheckerUpdateDao mliCheckerUpdateDao;
	
    
    public boolean updateStatusMLIChkAppLodge(String usrId, String status,String prtfoliNum){
		
    	return mliCheckerUpdateDao.updateStatusMLIChkAppLodge(usrId, status, prtfoliNum);
	}
}
