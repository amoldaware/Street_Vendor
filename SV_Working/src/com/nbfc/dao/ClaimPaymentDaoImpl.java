package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.ClaimPaymentExcelBO;
import com.nbfc.exception.BusinessException;

@Repository
@Transactional
public class ClaimPaymentDaoImpl implements ClaimPaymentDao {
	final static Logger logger = Logger.getLogger(ClaimPaymentDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ClaimPaymentBO> findMliList(String mliId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<ClaimPaymentBO> mliList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_MLI_Recovery(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, mliId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);

			rs = callableStmt.getResultSet();
			while (rs.next()) {
				ClaimPaymentBO bo = new ClaimPaymentBO();
				bo.setMliId(rs.getString(1));
				bo.setMliName(rs.getString(2));
				mliList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mliList;
	}

	@Override
	public List<ClaimPaymentBO> findAll(ClaimPaymentBO bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<ClaimPaymentBO> claimList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetPaymentDetails(?,?,?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, String.valueOf(bo.getMliId()));
			callableStmt.setString(3, bo.getClaimNumber());
			if (bo.getFromDt() == null || bo.getFromDt().isEmpty()) {
				callableStmt.setDate(4, new java.sql.Date(0));
			} else {
				callableStmt.setDate(4, new java.sql.Date(sdf.parse(bo.getFromDt()).getTime()));
			}

			if (bo.getToDate() == null || bo.getToDate().isEmpty()) {
				callableStmt.setDate(5, new java.sql.Date(0));
			} else {
				callableStmt.setDate(5, new java.sql.Date(sdf.parse(bo.getToDate()).getTime()));
			}

			callableStmt.setString(6, bo.getClaimDateFlag());
			callableStmt.setString(7, bo.getUtrUpdated());
			callableStmt.setString(8, bo.getUserRole());
			callableStmt.setString(9, bo.getApprovalStatus());
			callableStmt.registerOutParameter(10, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(10);

			rs = callableStmt.getResultSet();
			while (rs.next()) {
				ClaimPaymentBO claimBO = new ClaimPaymentBO();
				claimBO.setMliName(rs.getString(1));
				claimBO.setClaimNumber(rs.getString(2));
				claimBO.setClaimLogdeDt(rs.getString(3));
				claimBO.setUploadedOSAmount(rs.getString(4));
				claimBO.setClaimSettledAmount(rs.getString(5));
				claimBO.setAcountNumber(rs.getString(6));
				claimBO.setClaimSettledDt(rs.getString(7));
				claimBO.setVoucherNumber(rs.getString(8));
				claimBO.setApprovalStatus(rs.getString(9));
				claimBO.setRemark(rs.getString(10));
				claimBO.setUtrNumber(rs.getString(11));
				claimBO.setPaymentDt(rs.getString(12));
				claimBO.setIsUtrUploaded(rs.getString(13));
				claimBO.setUserRole(rs.getString(14));
				claimBO.setMliId(rs.getString(15));
				claimBO.setApprovalCode(rs.getString(16));
				claimList.add(claimBO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimList;
	}

	@Override
	public boolean save(List<ClaimPaymentBO> listBO) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Fun_insert_Payment_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (ClaimPaymentBO bo : listBO) {
				callableStmt.registerOutParameter(1, Types.INTEGER);
				callableStmt.setString(2, bo.getMliId());
				callableStmt.setString(3, bo.getClaimNumber());
				callableStmt.setString(4, bo.getClaimLogdeDt());
				callableStmt.setString(5, bo.getUploadedOSAmount());
				callableStmt.setString(6, bo.getClaimSettledAmount());
				callableStmt.setString(7, bo.getAcountNumber());
				callableStmt.setString(8, bo.getClaimSettledDt());
				callableStmt.setString(9, bo.getUserName());
				callableStmt.setString(10, bo.getIsUtrUploaded());
				callableStmt.setString(11, bo.getUserRole());
				callableStmt.setString(12, bo.getApprovalStatus());
				callableStmt.setString(13, bo.getRemark());
				callableStmt.registerOutParameter(14, Types.VARCHAR);
				callableStmt.execute();
				String error = callableStmt.getString(14);
			}
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		}
		return isSuccess;
	}

	@Override
	public boolean generateVoucherForApprovalByUserId(String userId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUN_GENERATE_VOUCHER(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, userId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		}
		return isSuccess;
	}

	@Override
	public List<ClaimPaymentExcelBO> getAllExelData(String dataList) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<ClaimPaymentExcelBO> claimList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Payment_Export_data(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, dataList);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);

			rs = callableStmt.getResultSet();
			while (rs.next()) {
				ClaimPaymentExcelBO bo = new ClaimPaymentExcelBO();
				bo.setSerialNo(rs.getString(1));
				bo.setMemberId(rs.getString(2));
				bo.setNetPayable(rs.getString(3));
				bo.setTransferACNo(rs.getString(4));
				bo.setIfscCode(rs.getString(5));
				bo.setAcNoOfBeneficiary(rs.getString(6));
				bo.setAcType(rs.getString(7));
				bo.setNameOfBeneficiary(rs.getString(8));
				bo.setBeneficiaryBnkName(rs.getString(9));
				bo.setRemark(rs.getString(10));
				bo.setOriginatingBank(rs.getString(11));
				claimList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimList;
	}

}
