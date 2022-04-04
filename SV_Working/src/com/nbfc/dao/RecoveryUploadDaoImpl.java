package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.FileExcelRecoveryDataBo;
import com.nbfc.bean.LodgeFreshRecoveryBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.RecoveryDownLoadExcelData;
import com.nbfc.bean.RecoveryErrorFileExcelDataBO;
import com.nbfc.bean.RecoveryXmlData;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.exception.BusinessException;

@Repository
@Transactional
public class RecoveryUploadDaoImpl implements RecoveryUploadDao{

	final static Logger logger = Logger.getLogger(UrtUploadDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveRecoveryUploadSTG(List<FileExcelRecoveryDataBo> boList) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Insert_Recovery_STG(?,?,?,?,?,?,?)}");
			for(FileExcelRecoveryDataBo bo:boList) {
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setLong(2, bo.getRecoveryUploadId());
			callableStmt.setString(3, bo.getPmsNumber());
			callableStmt.setString(4, bo.getLoanAcountNo());
			callableStmt.setString(5,bo.getRecoveryAmt());
			callableStmt.setString(6,bo.getUserId());
			callableStmt.registerOutParameter(7, Types.VARCHAR);
			callableStmt.execute();
			
			}
			String error = callableStmt.getString(7);
			tx.commit();
			
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
		return isSuccess;
	}

	@Override
	public boolean saveRecoveryUploadTracker(UtrTracerBO trckerBo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Insert_Recovery_Tracker(?,?,?,?,?,?,?,?)}");
		
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, trckerBo.getMember_id());
			callableStmt.setLong(3, trckerBo.getUtrUploadId());
			callableStmt.setString(4, trckerBo.getUpload_by());
			callableStmt.setInt(5,trckerBo.getSuccess_cnt());
			callableStmt.setInt(6,trckerBo.getUnsuccess_cnt());
			callableStmt.setInt(7, trckerBo.getExcel_cnt());
			callableStmt.registerOutParameter(8, Types.VARCHAR);
	
			callableStmt.execute();
			String error = callableStmt.getString(8);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
		return isSuccess;
	}

	@Override
	public UploadFileSuccessErrorCount findRecoveryProgressStatusFile(String userId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Recovery_Uploaded_Progress_Data(?)}");
			callableStmt.setString(1, userId);
			callableStmt.execute();
			rs = callableStmt.getResultSet();
			if (rs.next()) {
				bo = new UploadFileSuccessErrorCount();
				bo.setUploadId(rs.getString(1));
				bo.setStatus(rs.getString(2));
				bo.setCout(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return bo;

	}

	@Override
	public UploadFileSuccessErrorCount findRecoveryUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Recovery_Uploaded_Record_Status(?,?)}");
			callableStmt.setString(1, userId);
			callableStmt.setString(2, mliId);
			callableStmt.execute();
			rs = callableStmt.getResultSet();
			if (rs.next()) {
				bo = new UploadFileSuccessErrorCount();
				bo.setUploadId(rs.getString(1));
				bo.setSuccessCount(rs.getString(2));
				bo.setUnSuccessCount(rs.getString(3));
				bo.setStatus(rs.getString(4));
				bo.setCout(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public List<RecoveryErrorFileExcelDataBO> downloadErrorRecoveryUploadedFileList(String uploadId, String userId,
			String mliId, String flag) throws BusinessException {
		Session session = null;
		List<RecoveryErrorFileExcelDataBO> boList = new ArrayList<>();
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetRecoveryUploadedFile_data(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, uploadId);
			callableStmt.setString(3, userId);
			callableStmt.setString(4, mliId);
			callableStmt.setString(5, flag);
			callableStmt.registerOutParameter(6, Types.VARCHAR);

			callableStmt.execute();
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				RecoveryErrorFileExcelDataBO bo = new RecoveryErrorFileExcelDataBO();
				bo.setUploadId(rs.getString(1));
				bo.setPms_no(rs.getString(2));
				bo.setLoanAccNo(rs.getString(3));
				bo.setRecoveryAmt(rs.getString(4));
				bo.setProcessingFlag(rs.getString(5));
				bo.setProcessingDesc(rs.getString(6));
				boList.add(bo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return boList;
	}

	@Override
	public List<LodgeFreshRecoveryBO> findLodgeFreshRecoveryByMliId(String mliId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		LodgeFreshRecoveryBO bo = null;
		List<LodgeFreshRecoveryBO> recoverylist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_Recovery_Uploaded_Details(?,?)}");
			callableStmt.setString(1, mliId);
			callableStmt.registerOutParameter(2, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(2);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new LodgeFreshRecoveryBO();
				bo.setRp_no(rs.getString(1));
				bo.setUploadedDate(rs.getString(2));
				bo.setTotalRecordCount(rs.getString(3));
				bo.setStatusDesc(rs.getString(4));
				bo.setRemark(rs.getString(5));
				bo.setVertualAccNo(rs.getString(6));
				bo.setCheckerApprovalDt(rs.getString(7));
				bo.setUploadId(rs.getString(8));
				bo.setMliName(rs.getString(9));
				bo.setRecoveryAmt(rs.getString(10));
				bo.setApprovalCode(rs.getString(11));
				recoverylist.add(bo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return recoverylist;
	}

	@Override
	public boolean saveLodgeFreshRecoveryData(List<LodgeFreshRecoveryBO> bo, String usrId, String mliId, String usrType)
			throws BusinessException {
		Session session = null;
        Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_Update_Recovery_Header(?,?,?,?,?,?,?,?,?,?,?)}");
			for(LodgeFreshRecoveryBO logeBo:bo) {
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, logeBo.getRp_no());
			callableStmt.setString(3,logeBo.getTotalRecordCount());
			callableStmt.setString(4, logeBo.getUploadedDate());
			callableStmt.setString(5,logeBo.getApprovalStatus());
			callableStmt.setString(6, logeBo.getRemark());
			callableStmt.setString(7, logeBo.getRecoveryAmt());
			callableStmt.setString(8, logeBo.getUploadId());
			callableStmt.setString(9, mliId);
			callableStmt.setString(10, usrId);
			
			callableStmt.registerOutParameter(11, Types.VARCHAR);
			callableStmt.execute();
			
			}
			String error = callableStmt.getString(11);
			tx.commit();
			
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public List<RecoveryDownLoadExcelData> findExportToExcelRecoveryData(String mliId)throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		RecoveryDownLoadExcelData bo = null;
		List<RecoveryDownLoadExcelData> recoveryDatalist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Checker_RecoveryExportData(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, mliId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new RecoveryDownLoadExcelData();
				bo.setRp_no(rs.getString(1));
				bo.setVirtualAccNo(rs.getString(2));
				bo.setRecoveryAmt(rs.getString(3));
				bo.setIfscCode(rs.getString(4));
				recoveryDatalist.add(bo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return recoveryDatalist;
	}

	@Override
	public RecoveryXmlData findXmlRecoveryData(String mliId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		RecoveryXmlData bo = null;
		List<RecoveryXmlData> recoveryXmlDatalist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Recovery_XMLtData(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, mliId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);
			rs = callableStmt.getResultSet();
			if (rs.next()) {
				bo = new RecoveryXmlData();
				bo.setRp_no(rs.getString(1));
				bo.setVirtualAccNo(rs.getString(2));
				bo.setRecoveryAmt(rs.getString(3));
				bo.setPaymentInitiateddDate(rs.getString(4));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public boolean saveRecoveryUploadFtpResponce(String rpNo, String virtualAccNo, String responce)
			throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Update_FTP_XML_Response(?,?,?,?,?)}");
		
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2,rpNo);
			callableStmt.setString(3, virtualAccNo);
			callableStmt.setString(4, responce);
			callableStmt.registerOutParameter(5, Types.VARCHAR);
	
			callableStmt.execute();
			String error = callableStmt.getString(5);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

}
