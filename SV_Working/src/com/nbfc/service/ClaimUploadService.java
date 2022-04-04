package com.nbfc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPmsDetail;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadTracker;

public interface ClaimUploadService {

	public ClaimUploadDetailsBO getClaimUploadByMemberBankId(String memberBnkId) throws BusinessException;

	public boolean saveClaimUploadExcelFileSTG(List<FileExcelDataBO> boList, HttpSession session)
			throws BusinessException;

	public LodgeFreshClaimBO getLodgeFreshClaimByMliId(String mliId) throws BusinessException;

	public boolean saveLodgeFreshClaimData(LodgeFreshClaimBO bo) throws BusinessException;

	public boolean updateLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException;

	public String getPmsNumberById(String pmsNo) throws BusinessException;

	public String getLoanAcNumberByLoanNo(String loanNo) throws BusinessException;

	public String getQuarterFileUploadByUserId(String userId,String quater,String fy) throws BusinessException;

	public List<ClaimPmsDetail> getClaimPmsDetail(String memberBnkId, String claimId, HttpServletResponse response)
			throws BusinessException;

	public void getPdfManagementCertificateClaimPms(String memberBnkId, String claimId, HttpServletResponse response,
			HttpServletRequest request) throws BusinessException;

	public boolean findByClaimNumber(String claimNumber) throws BusinessException;

	public UploadFileSuccessErrorCount getSuccessErrorUploadeFileCount(String uploadId, String userId,
			HttpSession session) throws BusinessException;

	public String checkDuplicatePmsNoByPmsNo(String pmsNo) throws BusinessException;

	/*
	 * public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String
	 * uploadId, String userId) throws BusinessException;
	 */

	public List<ClaimUploadDetailsBO> getFinancialYears() throws BusinessException;

	public String getApprovalStatusCheckerByMliIdAndQuaterFy(String mliId, String quater, String financialYear)
			throws BusinessException;

	public String getCurrentQuarter() throws BusinessException;

	public ClaimUploadTracker getJobFileProccessStatus(String quater, String fy, String MliId) throws BusinessException;

	public UploadFileSuccessErrorCount getProgressStatusFile(String userId) throws BusinessException;

	//public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId) throws BusinessException;

	public String getFileProcessStatusByUserId(String userId) throws BusinessException;

	public boolean deleteFailureDataByUserIdAndQuater(String userId, String quater) throws BusinessException;

	public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String uploadId, String userId, String mliId,
			String flag)
			throws BusinessException;

	public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException;
	public String getMakerByMliIdandStatus(String status,String mliId) throws BusinessException;

}
