package com.nbfc.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPmsDetail;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.dao.ClaimUploadDao;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadSTG;
import com.nbfc.model.ClaimUploadTracker;

@Service
public class ClaimUploadServiceImpl implements ClaimUploadService {
	final static Logger logger = Logger.getLogger(ClaimUploadServiceImpl.class.getName());

	@Autowired
	ClaimUploadDao claimUploadDao;

	static BigInteger uploadIdTemp = null;

	@Override
	public ClaimUploadDetailsBO getClaimUploadByMemberBankId(String memberBnkId) throws BusinessException {
		ClaimUploadDetailsBO bo = null;
		try {
			bo = claimUploadDao.findClaimUploadByMemberBankId(memberBnkId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public boolean saveClaimUploadExcelFileSTG(List<FileExcelDataBO> boList, HttpSession session)
			throws BusinessException {
		boolean isSuccess = false;
		ClaimUploadTracker claimTracker = null;
		try {
			
			
			List<ClaimUploadSTG> stgList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date now = new Date();
			uploadIdTemp = BigInteger.valueOf(Long.parseLong(sdf.format(now)));

			session.setAttribute("tempuploadId", uploadIdTemp);

			if (boList != null && !boList.isEmpty()) {
				String userId = null;
				String memberBnkId = null;
				
				for (FileExcelDataBO bo : boList) {
					System.out.println("SARITA ::::" +bo.getDateOfNpa());
					ClaimUploadSTG claim = new ClaimUploadSTG();
					userId = bo.getUserId();
					memberBnkId = bo.getMemberBnkId();
					System.out.println("Member Id is ::::" + memberBnkId);
					claim.setUploadId(BigInteger.valueOf(Long.parseLong(sdf.format(now))));
					claim.setPmsNumber(bo.getPmsNumber());
					claim.setLoanAcountNo(bo.getLoanAcountNo());
					claim.setDateOfNpa(bo.getDateOfNpa());
					claim.setOutstandingAmountNpa(bo.getOutstandingAmountNpa());
					claim.setProcessFlag("P");
					claim.setProcessDecs("PENDING");
					claim.setCreatedDate(now);
					claim.setCreatedby(userId);
					claim.setQuater(bo.getQuater());
					claim.setFinancialYear(bo.getFinancialyear());
					//Added by Sarita on 21/10/2021 [START]
					claim.setBorrowerName(bo.getBorrowerName());
					//Added by Sarita on 21/10/2021 [END]
					//Added by Sarita on 21/01/2022 [START]
					claim.setLoanTerm(bo.getLoanTerm());
					//Added by Sarita on 21/01/2022 [END]
					//Added by Sarita on 12/03/2022 [START]
					claim.setMemberId(memberBnkId);
					//Added by Sarita on 12/03/2022 [END]
					stgList.add(claim);
				}
				
				System.out.println("Claim Tracker ::::" + memberBnkId);
				claimTracker = new ClaimUploadTracker();
				claimTracker.setMember_id(memberBnkId);
				claimTracker.setUploadId(BigInteger.valueOf(Long.parseLong(sdf.format(now))));
				claimTracker.setProcess_name("Claim Upload Process");
				claimTracker.setUpload_by(userId);
				claimTracker.setUpload_date(now);
				claimTracker.setStatus("P");
				claimTracker.setStatus_cnt(0);
				claimTracker.setUnsuccess_cnt(0);
				claimTracker.setExcel_cnt(stgList.size());
				claimTracker.setQuater(stgList.get(0).getQuater());
				claimTracker.setFinancialYear(stgList.get(0).getFinancialYear());
				//Added by Sarita on 21/10/2021 [START]
				claimTracker.setBorrowerName(stgList.get(0).getBorrowerName());
				//Added by Sarita on 21/10/2021 [END]
				//Added by Sarita on 21/01/2022 [START]
				claimTracker.setLoanTerm(stgList.get(0).getLoanTerm());
				//Added by Sarita on 21/01/2022 [END]
			}
			isSuccess = claimUploadDao.saveClaimUploadSTG(stgList, claimTracker);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public LodgeFreshClaimBO getLodgeFreshClaimByMliId(String mliId) throws BusinessException {
		LodgeFreshClaimBO bo = null;
		try {
			bo = claimUploadDao.findLodgeFreshClaimByMliId(mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public boolean saveLodgeFreshClaimData(LodgeFreshClaimBO bo) throws BusinessException {
		boolean isSuccess = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date parse = sdf.parse(bo.getFreshClaimDate());
			String claimDate = sdf1.format(parse);
			bo.setFreshClaimDate(claimDate);
			isSuccess = claimUploadDao.saveLodgeFreshClaimData(bo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public boolean updateLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException {
		boolean isSuccess = false;
		try {

			isSuccess = claimUploadDao.updateLodgeFreshClaimChecker(bo);
			if (isSuccess) {
				isSuccess = claimUploadDao.updateHistoryLodgeFreshClaimChecker(bo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public String getPmsNumberById(String pmsNo) throws BusinessException {
		String pmsNumber = null;
		try {
			pmsNumber = claimUploadDao.findPmsNumberById(pmsNo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return pmsNumber;
	}

	@Override
	public String getQuarterFileUploadByUserId(String userId, String quater, String fy) throws BusinessException {
		String quarter = null;
		try {
			quarter = claimUploadDao.findQuarterFileUploadByUserId(userId, quater, fy);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return quarter;
	}

	@Override
	public List<ClaimPmsDetail> getClaimPmsDetail(String memberBnkId, String claimId, HttpServletResponse response)
			throws BusinessException {
		ClaimPmsDetail claimPms = null;
		ClaimPmsDetail claimPms1 = null;
		List<ClaimPmsDetail> claimPmsinfo = new ArrayList<>();
		try {
			claimPmsinfo = claimUploadDao.findClaimPmsDetailByMemAndClaimId(memberBnkId, claimId);
			if (claimPmsinfo != null && response != null) {
				String optionalColumns[] = { "SNo", "PMS No", "Loan Account", "Borrower Name", "Sanction Date",
						"Sanction Amount", "NPA Date", "Claim Amount" };
				Utils.writeToExcel("ClaimPmsDetails", "ClaimPmsDetails", optionalColumns, claimPmsinfo, response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimPmsinfo;
	}

	@Override
	public void getPdfManagementCertificateClaimPms(String memberBnkId, String claimId, HttpServletResponse response,
			HttpServletRequest request) throws BusinessException {
		try {
			claimUploadDao.getPdfManagementCertificateData(memberBnkId, claimId, response, request);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public boolean findByClaimNumber(String claimNumber) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = claimUploadDao.findByClaimNumber(claimNumber);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public UploadFileSuccessErrorCount getSuccessErrorUploadeFileCount(String uploadId, String userId,
			HttpSession session) throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = claimUploadDao.findSuccessErrorUploadFile((BigInteger) session.getAttribute("tempuploadId"), userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	/*
	 * @Override public List<ClaimErrorFileExcelDataBO>
	 * downloadErrorUploadedFileList(String uploadId, String userId) throws
	 * BusinessException { List<ClaimErrorFileExcelDataBO> bo = null; try { bo =
	 * claimUploadDao.downloadErrorUploadedFileList(uploadId, userId); } catch
	 * (Exception e) { logger.error(e.getMessage()); } return bo; }
	 */

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String uploadId, String userId, String mliId,
			String flag)
			throws BusinessException {
		List<ClaimErrorFileExcelDataBO> bo = null;
		try {
			bo = claimUploadDao.downloadErrorUploadedFileList(uploadId, userId,mliId, flag);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}
	@Override
	public String checkDuplicatePmsNoByPmsNo(String pmsNo) throws BusinessException {
		String pmsNumber = null;
		try {
			pmsNumber = claimUploadDao.checkDuplicatePmsNoByPmsNo(pmsNo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return pmsNumber;
	}

	@Override
	public List<ClaimUploadDetailsBO> getFinancialYears() throws BusinessException {
		List<ClaimUploadDetailsBO> fy = null;
		try {
			fy = claimUploadDao.findFinancialYears();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return fy;
	}

	@Override
	public String getApprovalStatusCheckerByMliIdAndQuaterFy(String mliId, String quater, String financialYear)
			throws BusinessException {
		String approveStatus = null;
		try {
			approveStatus = claimUploadDao.findApprovalStatusCheckerByMliIdAndQuaterFy(mliId, quater, financialYear);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return approveStatus;
	}

	@Override
	public String getCurrentQuarter() throws BusinessException {
		String findCurrentQuarter = null;
		try {
			findCurrentQuarter = claimUploadDao.findCurrentQuarter();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return findCurrentQuarter;
	}

	@Override
	public ClaimUploadTracker getJobFileProccessStatus(String quater, String fy, String MliId)
			throws BusinessException {
		ClaimUploadTracker trackerstatus = null;
		try {
			trackerstatus = claimUploadDao.getJobFileProccessStatus(quater, fy, MliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return trackerstatus;
	}

	@Override
	public String getLoanAcNumberByLoanNo(String loanNo) throws BusinessException {
		String loanNo1 = null;
		try {
			loanNo1 = claimUploadDao.findLoanAcNumberByLoanNo(loanNo);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return loanNo1;
	}

	@Override
	public UploadFileSuccessErrorCount getProgressStatusFile(String userId) throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = claimUploadDao.findProgressStatusFile(userId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	/*
	 * @Override public UploadFileSuccessErrorCount
	 * findUploadedRecordStatusBar(String userId) throws BusinessException {
	 * UploadFileSuccessErrorCount bo = null; try { bo =
	 * claimUploadDao.findUploadedRecordStatusBar(userId);
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage()); } return bo; }
	 */

	@Override
	public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = claimUploadDao.findUploadedRecordStatusBar(userId, mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public String getFileProcessStatusByUserId(String userId) throws BusinessException {
		String filerecordCount = null;
		try {
			filerecordCount = claimUploadDao.fingFileProcessStatusByUserId(userId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return filerecordCount;
	}

	@Override
	public boolean deleteFailureDataByUserIdAndQuater(String userId, String quater) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = claimUploadDao.deleteFailureDataByUserIdAndQuater(userId, quater);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}
	@Override
	public String getMakerByMliIdandStatus(String status, String mliId) throws BusinessException {
		String maker = null;
		try {
			maker = claimUploadDao.findMakerByMliIdandStatus(status, mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return maker;
	}

}
