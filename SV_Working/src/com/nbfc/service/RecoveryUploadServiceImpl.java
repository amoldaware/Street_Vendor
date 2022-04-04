package com.nbfc.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.integration.sftp.session.SftpSession;*/
import org.springframework.stereotype.Service;

import com.nbfc.bean.FileExcelRecoveryDataBo;
import com.nbfc.bean.LodgeFreshRecoveryBO;
import com.nbfc.bean.RecoveryDownLoadExcelData;
import com.nbfc.bean.RecoveryErrorFileExcelDataBO;
import com.nbfc.bean.RecoveryXmlData;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.dao.RecoveryUploadDao;
import com.nbfc.exception.BusinessException;
import com.nbfc.utility.SFTPServerConnection;
@Service
public class RecoveryUploadServiceImpl implements RecoveryUploadService {
	final static Logger logger = Logger.getLogger(RecoveryUploadServiceImpl.class.getName());
	@Autowired
	RecoveryUploadDao recoveryUploadDao;
	//@Autowired
    //SFTPServerConnection sftpServerConnection;
	static Long uploadRecoveryIdTemp = null;


	@Override
	public boolean saveRecoveryUploadExcelFileSTG(List<FileExcelRecoveryDataBo> boList, HttpSession session)
			throws BusinessException {
		boolean isSuccess = false;
		UtrTracerBO utrtracerBo = null;
		try {
			List<FileExcelRecoveryDataBo> recoveryRecordList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date now = new Date();
			uploadRecoveryIdTemp = Long.valueOf(Long.parseLong(sdf.format(now)));

			session.setAttribute("tempUtrUploadId", uploadRecoveryIdTemp);
			if (boList != null && !boList.isEmpty()) {

				String userId = null;
				String memberBnkId = null;
				for (FileExcelRecoveryDataBo bo : boList) {
					FileExcelRecoveryDataBo recveryUpload = new FileExcelRecoveryDataBo();
					userId = bo.getUserId();
					memberBnkId = bo.getMemberBnkId();
					recveryUpload.setRecoveryUploadId(Long.valueOf(Long.parseLong(sdf.format(now))));
					recveryUpload.setPmsNumber(bo.getPmsNumber());
					recveryUpload.setLoanAcountNo(bo.getLoanAcountNo());
					recveryUpload.setRecoveryAmt(bo.getRecoveryAmt());
					recveryUpload.setUserId(bo.getUserId());
					recoveryRecordList.add(recveryUpload);
				}
				utrtracerBo = new UtrTracerBO();
				utrtracerBo.setMember_id(memberBnkId);
				utrtracerBo.setUtrUploadId(Long.valueOf(Long.parseLong(sdf.format(now))));
				utrtracerBo.setProcess_name("Recovery Upload Process");
				utrtracerBo.setUpload_by(userId);
				utrtracerBo.setUpload_date(now);
				utrtracerBo.setStatus("P");
				utrtracerBo.setStatus_cnt(0);
				utrtracerBo.setUnsuccess_cnt(0);
				utrtracerBo.setSuccess_cnt(0);
				utrtracerBo.setExcel_cnt(recoveryRecordList.size());
			}
			isSuccess = recoveryUploadDao.saveRecoveryUploadSTG(recoveryRecordList);
			if (isSuccess) {

				isSuccess = recoveryUploadDao.saveRecoveryUploadTracker(utrtracerBo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
		return isSuccess;
	}


	@Override
	public UploadFileSuccessErrorCount getRecoveryProgressStatusFile(String userId) throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = recoveryUploadDao.findRecoveryProgressStatusFile(userId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}


	@Override
	public UploadFileSuccessErrorCount findRecoveryUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = recoveryUploadDao.findRecoveryUploadedRecordStatusBar(userId, mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}


	@Override
	public List<RecoveryErrorFileExcelDataBO> downloadErrorRecoveryUploadedFileList(String uploadId, String userId,
			String mliId, String flag) throws BusinessException {
		List<RecoveryErrorFileExcelDataBO> bo = null;
		try {
			bo = recoveryUploadDao.downloadErrorRecoveryUploadedFileList(uploadId, userId,mliId, flag);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
	}


	@Override
	public List<LodgeFreshRecoveryBO> getLodgeFreshRecoveryByMliId(String mliId) throws BusinessException {
		List<LodgeFreshRecoveryBO> listlodgeFreshrecovery=null;
		try {
			listlodgeFreshrecovery = recoveryUploadDao.findLodgeFreshRecoveryByMliId(mliId);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listlodgeFreshrecovery;
	}


	@Override
	public boolean saveLodgeFreshRecoveryData(List<LodgeFreshRecoveryBO> bo, String usrId, String mliId, String usrType)
			throws BusinessException {
		boolean isSuccess = false;
		try {
			
			isSuccess = recoveryUploadDao.saveLodgeFreshRecoveryData(bo,usrId,mliId,usrType);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;	
	}


	@Override
	public List<RecoveryDownLoadExcelData> getExportToExcelRecoveryData(String mliId) throws BusinessException {
	
		return recoveryUploadDao.findExportToExcelRecoveryData(mliId);
	}


	@Override
	public RecoveryXmlData getXmlRecoveryData(String mliId) throws BusinessException {
	RecoveryXmlData bo=null;
   try     {
			
	   bo = recoveryUploadDao.findXmlRecoveryData(mliId);
	   if(bo!=null) {
		   createXML(bo);
	   }
	   
	   

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bo;
		
		
	}
	public void createXML(RecoveryXmlData bo) throws IOException {
        try
        
        {  
        	String fileName=bo.getVirtualAccNo();
			/*
			 * String fileName=bo.getVirtualAccNo(); SftpSession session =
			 * sftpServerConnection.getSftpFactory().getSession(); InputStream
			 * resourceAsStream
			 * =SFTPServerConnection.class.getClassLoader().getResourceAsStream(
			 * "mytextfile.txt"); session.write(resourceAsStream, "upload/Tractionfile" +
			 * fileName+".xml"); session.close();
			 */
        	
        	File file = new File(fileName+".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(RecoveryXmlData.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            marshaller.marshal(bo, sw);
            marshaller.marshal(bo, file);
            System.out.println(sw.toString());
            System.out.println(file.getName());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
	

	}


