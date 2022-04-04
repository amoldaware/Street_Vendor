package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.dao.MLIRegistrationDao;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.BankDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLIRegistrationAppraval;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.NewMLIRegistration;

@Service("mliRegService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MLIRegServiceImpl implements MLIRegService {
    
	@Autowired
	private MLIRegistrationDao mliRegistrationDao;
	
	
	public List<BankDetails> getBankDetails() {
		
		return mliRegistrationDao.getBankDetails();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addMLIDetails(MLIRegistration mliRegistration) {
		mliRegistrationDao.addMLIDetails(mliRegistration);
	}

	
	public List<MLIRegistration> getMLIRegList(String mliLingName,String status) {
		// TODO Auto-generated method stub
		return mliRegistrationDao.getMLIRegList(mliLingName,status);
	}

	
	public MLIRegistration getMLIDetails(String mliName) {
		
		return mliRegistrationDao.getMLIDetails(mliName);
	}

	
	public void updateMLIDetails(MLIRegistrationAppraval mliRegistration) {
		mliRegistrationDao.updateMLIDetails(mliRegistration);
		
	}

	
	public void editMLIDetails(MLIEditDetails mliRegistration) {
		mliRegistrationDao.editMLIDetails(mliRegistration);
		
	}

	
	public MLIEditDetails getMLIDtl(String mliName) {
		// TODO Auto-generated method stub
		return mliRegistrationDao.getMLIDtl(mliName);
	}

	
	public void audAddMLIDetails(AudMLiDetails mliRegistration) {
		mliRegistrationDao.audAddMLIDetails(mliRegistration);
		
	}

	
	public AudMLiDetails getMLIAudDetails(String mliName) {
		return mliRegistrationDao.getMLIAudDetails(mliName);
	}

	
	public void updateMLIApproveRejectStatus(
			MLIEditApproveRejectUpdate mliEditApproveRejectUpdate) {
		mliRegistrationDao.updateMLIApproveRejectStatus(mliEditApproveRejectUpdate);
		
	}

	
	public List<MLIRegistration> getMLIListForChecker(String status) {
		// TODO Auto-generated method stub
		return mliRegistrationDao.getMLIListForChecker(status);
	}

	
	public MLIRegistrationAppraval getMLIDetailsForApproveReject(String mliName) {
		
	return mliRegistrationDao.getMLIDetailsForApproveReject(mliName);
	}

	
	public void addNEWMLIDetails(NewMLIRegistration mliRegistration) {
		mliRegistrationDao.addNEWMLIDetails(mliRegistration);
		
	}

	@Override
	public void mliUploadFile(MLI_UNDERTAKEN_UPLOAD mli_upload) {
	mliRegistrationDao.mliUploadFile(mli_upload);
	}

	
}
