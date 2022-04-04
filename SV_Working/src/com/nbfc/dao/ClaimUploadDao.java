package com.nbfc.dao;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPmsDetail;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.BulkUploadTracker;
import com.nbfc.model.ClaimUploadHeader;
import com.nbfc.model.ClaimUploadSTG;
import com.nbfc.model.ClaimUploadTracker;

public interface ClaimUploadDao {

	public ClaimUploadDetailsBO findClaimUploadByMemberBankId(String memberBnkId) throws BusinessException;

	public boolean saveClaimUploadSTG(List<ClaimUploadSTG> boList, ClaimUploadTracker claimTracker)
			throws BusinessException;

	public LodgeFreshClaimBO findLodgeFreshClaimByMliId(String mliId) throws BusinessException;

	public boolean saveLodgeFreshClaimData(LodgeFreshClaimBO bo) throws BusinessException;

	public boolean updateLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException;

	public String findPmsNumberById(String pmsNo) throws BusinessException;

	public String findQuarterFileUploadByUserId(String userId,String quater,String fy) throws BusinessException;

	public List<ClaimPmsDetail> findClaimPmsDetailByMemAndClaimId(String memberBnkId, String claimId)
			throws BusinessException;

	public void getPdfManagementCertificateData(String memberBnkId, String claimId, HttpServletResponse response,
			HttpServletRequest request) throws BusinessException;

	public boolean findByClaimNumber(String claimNumber) throws BusinessException;

	public String findLoanAcNumberByLoanNo(String loanNo) throws BusinessException;

	public UploadFileSuccessErrorCount findSuccessErrorUploadFile(BigInteger uploadId, String userId)
			throws BusinessException;

	public String checkDuplicatePmsNoByPmsNo(String pmsNo) throws BusinessException;

	/*
	 * public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String
	 * uploadId, String userId) throws BusinessException;
	 */
	public List<ClaimUploadDetailsBO> findFinancialYears() throws BusinessException;

	public String findApprovalStatusCheckerByMliIdAndQuaterFy(String mliId, String quater, String financialYear)
			throws BusinessException;

	public String findCurrentQuarter() throws BusinessException;

	public ClaimUploadTracker getJobFileProccessStatus(String quater, String fy, String MliId) throws BusinessException;

	public UploadFileSuccessErrorCount findProgressStatusFile(String userId) throws BusinessException;

	// public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId)
	// throws BusinessException;

	public boolean updateHistoryLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException;

	public String fingFileProcessStatusByUserId(String userId) throws BusinessException;

	public boolean deleteFailureDataByUserIdAndQuater(String userId, String quater) throws BusinessException;

	public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException;

	public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException;

	public String findMakerByMliIdandStatus(String status, String mliId) throws BusinessException;

}
