package com.nbfc.dao;

import java.util.List;
import java.util.Map;

import com.nbfc.bean.APIClaimDataBO;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;

public interface ClaimUploadCgtmseDao {
	//public List<ClaimUploadCgtmseBO> findAllCGTMSE() throws BusinessException;
	public List<ClaimUploadCgtmseBO> findAllCGTMSE(String apprvStatus) throws BusinessException;

	public boolean updateMliCgtmse(ClaimUploadCgtmseBO bo) throws BusinessException;

	public List<ClaimErrorFileExcelDataBO> downloadUploadedFileList(String uploadId, String flag)
			throws BusinessException;

	public ClaimUploadHeader downloadFileMgmtCertificate(String mliId, String approvalStatus) throws BusinessException;
	
	public List<ClaimUploadCgtmseBO> getApprovedSubmittedData(String submitStatus) throws BusinessException;
	
	public List<APIClaimDataBO> getRequiredDataToSendThroughAPI(String uploadId) throws BusinessException;
	
	public String updateClaimSentData(Map<String,String> apiRtnData)throws BusinessException;
	
	public List<ClaimErrorFileExcelDataBO> downloadUploadedAPIClaimFile(String uploadId, String flag)
			throws BusinessException;
}
