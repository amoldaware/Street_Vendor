package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.exception.BusinessException;

public interface UrtUploadDao {
	
	public boolean saveUtrUploadSTG(List<FileExcelDataBO> boList)throws BusinessException;
	
	public boolean saveUtrUploadTracker(UtrTracerBO trckerBo)throws BusinessException;
	
	public UploadFileSuccessErrorCount findUtrProgressStatusFile(String userId) throws BusinessException;
	
	public UploadFileSuccessErrorCount findUtrUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException;
	
	public List<UtrErrorFileExcelDataBO> downloadErrorUtrUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException;
	
	public List<LodgeFreshUrtBO> findLodgeFreshUtrByMliId(String mliId) throws BusinessException;
	
	public boolean saveLodgeFreshUtrData(List<LodgeFreshUrtBO> bo,String usrId,String mliId,String usrType) throws BusinessException;

	public List<LodgeFreshUrtBO> findUtrUploadApprovalData(String mliId,String userType) throws BusinessException;
	
	public boolean saveCGTMSECheckerUtrApproval(List<LodgeFreshUrtBO> listBO) throws BusinessException;
}
