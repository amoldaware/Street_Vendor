package com.nbfc.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.APIClaimDataBO;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.controller.ClaimUploadController;
import com.nbfc.dao.ClaimUploadCgtmseDao;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;

@Service
public class ClaimUploadCgtmseServiceImpl implements ClaimUploadCgtmseService {
	final static Logger logger = Logger.getLogger(ClaimUploadCgtmseServiceImpl.class.getName());
	@Autowired
	ClaimUploadCgtmseDao cgtmseDao;

	@Override
	//public List<ClaimUploadCgtmseBO> getAllCGTMSE() throws BusinessException {
	public List<ClaimUploadCgtmseBO> getAllCGTMSE(String apprvStatus) throws BusinessException {
		List<ClaimUploadCgtmseBO> allCGTMSE = null;
		try {
			//allCGTMSE = cgtmseDao.findAllCGTMSE();
			allCGTMSE = cgtmseDao.findAllCGTMSE(apprvStatus);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return allCGTMSE;
	}

	@Override
	public boolean updateMliCgtmse(ClaimUploadCgtmseBO bo) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = cgtmseDao.updateMliCgtmse(bo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadUploadedFileList(String uploadId, String flag)
			throws BusinessException {
		List<ClaimErrorFileExcelDataBO> downloadUploadedFileList = null;
		try {
			downloadUploadedFileList = cgtmseDao.downloadUploadedFileList(uploadId, flag);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return downloadUploadedFileList;

	}

	@Override
	public ClaimUploadHeader downloadFileMgmtCertificate(String mliId, String approvalStatus) throws BusinessException {
		ClaimUploadHeader mgmtCertificate = null;
		try {
			mgmtCertificate = cgtmseDao.downloadFileMgmtCertificate(mliId, approvalStatus);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mgmtCertificate;
	}

	@Override
	public List<ClaimUploadCgtmseBO> getApprovedSubmittedData(String submitStatus) throws BusinessException {
		List<ClaimUploadCgtmseBO> allApprovedSubmitata = null;
		try
		{
			allApprovedSubmitata = cgtmseDao.getApprovedSubmittedData(submitStatus);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return allApprovedSubmitata;
	}

	@Override
	public List<APIClaimDataBO> getRequiredDataToSendThroughAPI(String uploadId) throws BusinessException {
		List<APIClaimDataBO> ClaimAPIData = null;
		try
		{
			ClaimAPIData = cgtmseDao.getRequiredDataToSendThroughAPI(uploadId);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ClaimAPIData;
	}

	@Override
	public String updateClaimSentData(Map<String, String> apiRtnData) throws BusinessException 
	{
		String updateAPIData = "";
		try
		{
			updateAPIData = cgtmseDao.updateClaimSentData(apiRtnData);
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
		return updateAPIData;
	}

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadUploadedAPIClaimFile(String uploadId, String flag)
			throws BusinessException {

		List<ClaimErrorFileExcelDataBO> downloadUploadedFileList = null;
		try {
			downloadUploadedFileList = cgtmseDao.downloadUploadedAPIClaimFile(uploadId, flag);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return downloadUploadedFileList;
	}

}
