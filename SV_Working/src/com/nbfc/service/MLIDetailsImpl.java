package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.MliUploadDetails;
import com.nbfc.dao.MLIDetailsDao;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.StateMaster;

@Service("MLIDetailsService")
@Transactional()
public class MLIDetailsImpl implements MLIDetailsService {

	@Autowired
	MLIDetailsDao mliDetailsDao;

	
	public List<MLIRegistration> getMLIDetails(String longName, String status) {
		return mliDetailsDao.getMLIDetails(longName, status);
	}

	
	public MLIRegistration getMLIDetails(String mliName) {
		return mliDetailsDao.getMLIDetails(mliName);
	}

	
	public List<MLIRegistration> getMLIAllDetails() {
		return mliDetailsDao.getMLIAllDetails();
	}

	
	public MLIMainEditDetails getMLIEditDetails(String longName,
			String status) {
		return mliDetailsDao.getMLIEditDetails(longName, status);
	}

	
	public AudMLiDetails getAUDMLIEditDetails(String longName,
			String status) {
		return mliDetailsDao.getAUDMLIEditDetails(longName, status);
	}

	
	public void updateAUDMLIApproveRejectStatus(
			AudMLiDetails mliEditApproveRejectUpdate) {
		
		mliDetailsDao.updateAUDMLIApproveRejectStatus(mliEditApproveRejectUpdate);
	}

	
	public List<MLIRegistration> getMLIDetailsForApproval(String Status1,
			String status2,String status3) {
		// TODO Auto-generated method stub
		return mliDetailsDao.getMLIDetailsForApproval(Status1,status2,status3);
	}

	
	public List<MLIRegistration> getMLIDetailsbyIndex(String indexFirst,
			String indexSecond) {
		// TODO Auto-generated method stub
		return mliDetailsDao.getMLIDetailsbyIndex(indexFirst, indexSecond);
	}

	
	public List<MLIMainEditDetails> getApprovedMLIDetails(String status) {
		// TODO Auto-generated method stub
		return mliDetailsDao.getApprovedMLIDetails(status);
	}

	
	public List<MLIRegistration> ApproverejectMliDetailsByIndex(
			String nameSearch, String searchValue) {
		return mliDetailsDao.ApproverejectMliDetailsByIndex(nameSearch,searchValue);
	}

	
	public List<StateMaster> getStatename(String state) {
		// TODO Auto-generated method stub
		return mliDetailsDao.getStatename(state);
	}


	@Override
	public List<MliUploadDetails> getUploadFile(String longName, String status) {
		
		return mliDetailsDao.getUploadFile(longName,status);
	}


	@Override
	public List<MliUploadDetails> getMLIDetailsbyFileIndex(String indexFirst, String indexSecond) {
		// TODO Auto-generated method stub
		return mliDetailsDao.getMLIDetailsbyFileIndex(indexFirst, indexSecond);
	}


}
