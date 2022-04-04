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

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.bean.UtrTracerBO;
import com.nbfc.exception.BusinessException;

@Repository
@Transactional
public class UrtUploadDaoImpl implements UrtUploadDao{
	final static Logger logger = Logger.getLogger(UrtUploadDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveUtrUploadSTG(List<FileExcelDataBO> boList) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Insert_URT_STG(?,?,?,?,?,?,?,?)}");
			for(FileExcelDataBO bo:boList) {
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setLong(2, bo.getUtruploadId());
			callableStmt.setString(3, bo.getClaimPaymentVoucher());
			callableStmt.setString(4, bo.getClaimNumber());
			callableStmt.setString(5,bo.getClaimPaymentDate());
			callableStmt.setString(6, bo.getUtrNo());
			callableStmt.setString(7, bo.getUserId());
			
			callableStmt.registerOutParameter(8, Types.VARCHAR);
			callableStmt.execute();
			
			}
			String error = callableStmt.getString(8);
			tx.commit();
			
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public boolean saveUtrUploadTracker(UtrTracerBO trckerBo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Insert_URT_Tracker(?,?,?,?,?,?,?,?)}");
		
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
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public UploadFileSuccessErrorCount findUtrProgressStatusFile(String userId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_URT_Uploaded_Progress_Data(?)}");
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
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public UploadFileSuccessErrorCount findUtrUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_URT_Uploaded_Record_Status(?,?)}");
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
			logger.error(e.getMessage());
		}
		return bo;
	}

	@Override
	public List<UtrErrorFileExcelDataBO> downloadErrorUtrUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException {
		Session session = null;
		List<UtrErrorFileExcelDataBO> boList = new ArrayList<>();
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetUTRUploadedFile_data(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, uploadId);
			callableStmt.setString(3, userId);
			callableStmt.setString(4, mliId);
			callableStmt.setString(5, flag);
			callableStmt.registerOutParameter(6, Types.VARCHAR);

			callableStmt.execute();
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				UtrErrorFileExcelDataBO bo = new UtrErrorFileExcelDataBO();
				bo.setUploadId(rs.getString(1));
				bo.setClaimPaymentVoucher(rs.getString(2));
				bo.setClaimNumber(rs.getString(3));
				bo.setUtrDate(rs.getString(4));
				bo.setUtrNo(rs.getString(5));
				bo.setProcessingFlag(rs.getString(6));
				bo.setProcessingDesc(rs.getString(7));
				boList.add(bo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return boList;
	}

	@Override
	public List<LodgeFreshUrtBO> findLodgeFreshUtrByMliId(String mliId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		LodgeFreshUrtBO bo = null;
		List<LodgeFreshUrtBO> utrlist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_UTR_Uploaded_Details(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, mliId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new LodgeFreshUrtBO();
				bo.setMliId(rs.getString(1));
				bo.setMliName(rs.getString(2));
				bo.setFinancialYear(rs.getString(3));
				bo.setClaimNumber(rs.getString(4));
				bo.setClaimSettlementAmt(rs.getString(5));
				bo.setClaimPaymentDate(rs.getString(6));
				bo.setUtrNo(rs.getString(7));
				bo.setClaimPaymentVoucher(rs.getString(8));
				bo.setApprovalStatus(rs.getString(9));
				bo.setRemark(rs.getString(10));
				bo.setApprovalCode(rs.getString(11));
				utrlist.add(bo);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return utrlist;
	}

	@Override
	public boolean saveLodgeFreshUtrData(List<LodgeFreshUrtBO> bo, String usrId, String mliId, String usrType)
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
			callableStmt = conn.prepareCall("{call Update_UTR_Uploaded_Details(?,?,?,?,?,?,?,?)}");
			for(LodgeFreshUrtBO logeBo:bo) {
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, usrType);
			callableStmt.setString(3,logeBo.getMliId());
			callableStmt.setString(4, logeBo.getClaimNumber());
			callableStmt.setString(5,logeBo.getClaimPaymentVoucher());
			callableStmt.setString(6, logeBo.getUtrNo());
			callableStmt.setString(7, usrId);
			
			callableStmt.registerOutParameter(8, Types.VARCHAR);
			callableStmt.execute();
			
			}
			String error = callableStmt.getString(8);
			tx.commit();
			
			isSuccess = true;

		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public List<LodgeFreshUrtBO> findUtrUploadApprovalData(String mliId,String userType) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		LodgeFreshUrtBO bo1 = null;
		List<LodgeFreshUrtBO> utrApprovallist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_UTR_Uploaded_Details_Checker(?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2,mliId);
			callableStmt.setString(3,userType);
			callableStmt.registerOutParameter(4, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(4);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo1 = new LodgeFreshUrtBO();
				bo1.setMliId(rs.getString(1));
				bo1.setMliName(rs.getString(2));
				bo1.setFinancialYear(rs.getString(3));
				bo1.setClaimNumber(rs.getString(4));
				bo1.setClaimSettlementAmt(rs.getString(5));
				bo1.setClaimPaymentDate(rs.getString(6));
				bo1.setUtrNo(rs.getString(7));
				bo1.setClaimPaymentVoucher(rs.getString(8));
				bo1.setApprovalStatus(rs.getString(9));
				bo1.setRemark(rs.getString(10));
				bo1.setApprovalCode(rs.getString(11));
				utrApprovallist.add(bo1);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return utrApprovallist;
	}

	@Override
	public boolean saveCGTMSECheckerUtrApproval(List<LodgeFreshUrtBO> listBO) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Update_UTR_Uploaded_Details_Checker(?,?,?,?,?,?,?,?,?,?,?)}");
			for (LodgeFreshUrtBO bo : listBO) {
				callableStmt.registerOutParameter(1, Types.VARCHAR);
				callableStmt.setString(2, bo.getUserType());
				callableStmt.setString(3, bo.getMliId());
				callableStmt.setString(4, bo.getClaimNumber());
				callableStmt.setString(5, bo.getClaimPaymentVoucher());
				callableStmt.setString(6, bo.getUtrNo());
				callableStmt.setString(7, bo.getUserId());
				callableStmt.setString(8, bo.getApprovalCode());
				callableStmt.setString(9, bo.getClaimPaymentDate());
				callableStmt.setString(10, bo.getRemark());
				callableStmt.registerOutParameter(11, Types.VARCHAR);
				callableStmt.execute();
				String error = callableStmt.getString(11);
			}
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		}
		return isSuccess;
	}
	
	

}
