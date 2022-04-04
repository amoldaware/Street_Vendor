package com.nbfc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.nbfc.bean.FileExcelRecoveryDataBo;
import com.nbfc.bean.LodgeFreshRecoveryBO;
import com.nbfc.bean.RecoveryDownLoadExcelData;
import com.nbfc.bean.RecoveryErrorFileExcelDataBO;
import com.nbfc.bean.RecoveryXmlData;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.exception.BusinessException;


public interface RecoveryUploadService {
	
	public boolean saveRecoveryUploadExcelFileSTG(List<FileExcelRecoveryDataBo> boList, HttpSession session)throws BusinessException;
	
	public UploadFileSuccessErrorCount getRecoveryProgressStatusFile(String userId) throws BusinessException;
	
	public UploadFileSuccessErrorCount findRecoveryUploadedRecordStatusBar(String userId, String mliId)throws BusinessException;

	public List<RecoveryErrorFileExcelDataBO> downloadErrorRecoveryUploadedFileList(String uploadId, String userId, String mliId,
			String flag)
			throws BusinessException;
	public List<LodgeFreshRecoveryBO> getLodgeFreshRecoveryByMliId(String mliId) throws BusinessException;
	
	public boolean saveLodgeFreshRecoveryData(List<LodgeFreshRecoveryBO> bo,String usrId,String mliId,String usrType) throws BusinessException;
	
	public List<RecoveryDownLoadExcelData> getExportToExcelRecoveryData(String mliId)throws BusinessException;
	
	public RecoveryXmlData getXmlRecoveryData(String mliId)throws BusinessException;
}
