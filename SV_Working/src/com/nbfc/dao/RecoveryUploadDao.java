package com.nbfc.dao;

import java.util.List;

import com.nbfc.bean.FileExcelRecoveryDataBo;
import com.nbfc.bean.LodgeFreshRecoveryBO;
import com.nbfc.bean.RecoveryDownLoadExcelData;
import com.nbfc.bean.RecoveryErrorFileExcelDataBO;
import com.nbfc.bean.RecoveryXmlData;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.exception.BusinessException;

public interface RecoveryUploadDao {
	
	public boolean saveRecoveryUploadSTG(List<FileExcelRecoveryDataBo> boList)throws BusinessException;
	
	public boolean saveRecoveryUploadTracker(UtrTracerBO trckerBo)throws BusinessException;
	
	public UploadFileSuccessErrorCount findRecoveryProgressStatusFile(String userId) throws BusinessException;
	
	public UploadFileSuccessErrorCount findRecoveryUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException;
	
	public List<RecoveryErrorFileExcelDataBO> downloadErrorRecoveryUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException;
	
	public List<LodgeFreshRecoveryBO> findLodgeFreshRecoveryByMliId(String mliId) throws BusinessException;
	
	public boolean saveLodgeFreshRecoveryData(List<LodgeFreshRecoveryBO> bo,String usrId,String mliId,String usrType) throws BusinessException;
 
	public List<RecoveryDownLoadExcelData> findExportToExcelRecoveryData(String mliId)throws BusinessException;
	
	public RecoveryXmlData findXmlRecoveryData(String mliId)throws BusinessException;
	
	public boolean saveRecoveryUploadFtpResponce(String rpNo,String virtualAccNo,String responce)throws BusinessException;
	

}
