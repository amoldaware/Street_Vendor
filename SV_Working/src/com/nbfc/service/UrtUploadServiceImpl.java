package com.nbfc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.dao.UrtUploadDao;
import com.nbfc.exception.BusinessException;

@Service
public class UrtUploadServiceImpl implements UrtUploadService {
	final static Logger logger = Logger.getLogger(UrtUploadServiceImpl.class.getName());
	@Autowired
	UrtUploadDao urtUploadDao;
	static Long uploadUtrIdTemp = null;

	public boolean saveUtrUploadExcelFileSTG(List<FileExcelDataBO> boList, HttpSession session)
			throws BusinessException {
		boolean isSuccess = false;
		UtrTracerBO utrtracerBo = null;
		try {
			List<FileExcelDataBO> utrRecordList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date now = new Date();
			uploadUtrIdTemp = Long.valueOf(Long.parseLong(sdf.format(now)));

			session.setAttribute("tempUtrUploadId", uploadUtrIdTemp);
			if (boList != null && !boList.isEmpty()) {

				String userId = null;
				String memberBnkId = null;
				for (FileExcelDataBO bo : boList) {
					FileExcelDataBO utrUpload = new FileExcelDataBO();
					userId = bo.getUserId();
					memberBnkId = bo.getMemberBnkId();
					utrUpload.setUtruploadId(Long.valueOf(Long.parseLong(sdf.format(now))));
					utrUpload.setClaimPaymentVoucher(bo.getClaimPaymentVoucher());
					utrUpload.setClaimNumber(bo.getClaimNumber());
					utrUpload.setClaimPaymentDate(bo.getClaimPaymentDate());
					utrUpload.setUtrNo(bo.getUtrNo());
					utrUpload.setUserId(bo.getUserId());
					utrRecordList.add(utrUpload);
				}

				utrtracerBo = new UtrTracerBO();
				utrtracerBo.setMember_id(memberBnkId);
				utrtracerBo.setUtrUploadId(Long.valueOf(Long.parseLong(sdf.format(now))));
				utrtracerBo.setProcess_name("UTR Upload Process");
				utrtracerBo.setUpload_by(userId);
				utrtracerBo.setUpload_date(now);
				utrtracerBo.setStatus("P");
				utrtracerBo.setStatus_cnt(0);
				utrtracerBo.setUnsuccess_cnt(0);
				utrtracerBo.setSuccess_cnt(0);
				utrtracerBo.setExcel_cnt(utrRecordList.size());

			}
			isSuccess = urtUploadDao.saveUtrUploadSTG(utrRecordList);
			if (isSuccess) {

				isSuccess = urtUploadDao.saveUtrUploadTracker(utrtracerBo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public UploadFileSuccessErrorCount getUtrProgressStatusFile(String userId) throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = urtUploadDao.findUtrProgressStatusFile(userId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public UploadFileSuccessErrorCount findUtrUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = urtUploadDao.findUtrUploadedRecordStatusBar(userId, mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public List<UtrErrorFileExcelDataBO> downloadErrorUtrUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException {
		List<UtrErrorFileExcelDataBO> bo = null;
		try {
			bo = urtUploadDao.downloadErrorUtrUploadedFileList(uploadId, userId,mliId, flag);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public List<LodgeFreshUrtBO> getLodgeFreshUtrByMliId(String mliId) throws BusinessException {
		List<LodgeFreshUrtBO> listlodgeFreshUrt=null;
		try {
			listlodgeFreshUrt = urtUploadDao.findLodgeFreshUtrByMliId(mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listlodgeFreshUrt;	
		}

	@Override
	public boolean saveLodgeFreshUtrData(List<LodgeFreshUrtBO> bo,String usrId,String mliId,String usrType) throws BusinessException {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		try {
			
			isSuccess = urtUploadDao.saveLodgeFreshUtrData(bo,usrId,mliId,usrType);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;	
		}

	@Override
	public boolean save(List<LodgeFreshUrtBO> listBO) throws BusinessException {
		// TODO Auto-generated method stub
	   boolean isSuccess = false;
		try {
			
			//isSuccess = urtUploadDao.saveData(bo,usrId,mliId,usrType);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;	
		}

	@Override
	public List<LodgeFreshUrtBO> getAllUtrApproval(String mliId,String userType) throws BusinessException {
		List<LodgeFreshUrtBO> listUtrApprovalData=null;
		try {
			listUtrApprovalData = urtUploadDao.findUtrUploadApprovalData(mliId,userType.substring(1));

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listUtrApprovalData;	
		}

	@Override
	public boolean saveCheckerApproval(List<LodgeFreshUrtBO> listBO) throws BusinessException {
		boolean isSuccess = false;
		try {
			isSuccess = urtUploadDao.saveCGTMSECheckerUtrApproval(listBO);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	
	}


