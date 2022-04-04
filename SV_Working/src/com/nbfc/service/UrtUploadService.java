package com.nbfc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.exception.BusinessException;

public interface UrtUploadService {
	
	public boolean saveUtrUploadExcelFileSTG(List<FileExcelDataBO> boList, HttpSession session)throws BusinessException;
	
	public UploadFileSuccessErrorCount getUtrProgressStatusFile(String userId) throws BusinessException;
	
	public UploadFileSuccessErrorCount findUtrUploadedRecordStatusBar(String userId, String mliId)throws BusinessException;
	
	public List<UtrErrorFileExcelDataBO> downloadErrorUtrUploadedFileList(String uploadId, String userId, String mliId,
			String flag)
			throws BusinessException;
	
	public List<LodgeFreshUrtBO> getLodgeFreshUtrByMliId(String mliId) throws BusinessException;
	
	public boolean saveLodgeFreshUtrData(List<LodgeFreshUrtBO> bo,String usrId,String mliId,String usrType) throws BusinessException;

	public boolean save(List<LodgeFreshUrtBO> listBO) throws BusinessException;
	
	public List<LodgeFreshUrtBO> getAllUtrApproval(String mliId,String userType) throws BusinessException;

	public boolean saveCheckerApproval(List<LodgeFreshUrtBO> listBO) throws BusinessException;
	

}
