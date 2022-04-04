package com.nbfc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.dao.ASFGenerationBulkUploadDao;
@Service("ASFGenerationBulkUploadService")
public class ASFGenerationBulkUploadServiceImpl implements ASFGenerationBulkUploadService {
	
	@Autowired
	ASFGenerationBulkUploadDao ASFDao;

	@Override
	public List<ASFGenerationDetailBean> getASFDetailsByFileWise(String userId) {
		// TODO Auto-generated method stub
		return ASFDao.getASFDetailsByFileWise(userId);
	}

	@Override
	public List<ASFGenerationDetailBean> getAllASFDetails(String userId, String fileid) {
		// TODO Auto-generated method stub
		return ASFDao.getAllASFDetails(userId,fileid);
	}

}
