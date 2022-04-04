package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ClaimPaymentBO;
import com.nbfc.bean.RecoveryReportBO;
import com.nbfc.bean.RecoveryReportExcelBO;

@Repository
public class RecoveryReportDaoImpl implements RecoveryReportDao {
	final static Logger logger = Logger.getLogger(RecoveryReportDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RecoveryReportBO> findAll(RecoveryReportBO bo) {

		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<RecoveryReportBO> claimList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

		try {

			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Recovery_Report(?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getMliId());
			if (bo.getFromDt() == null || bo.getFromDt().isEmpty()) {
				callableStmt.setDate(3, new java.sql.Date(0));
			} else {
				Date fromDt = sdf1.parse(bo.getFromDt());
				String formatfmDate = sdf.format(fromDt);
				callableStmt.setDate(3, new java.sql.Date(sdf.parse(formatfmDate).getTime()));
			}

			if (bo.getToDate() == null || bo.getToDate().isEmpty()) {
				callableStmt.setDate(4, new java.sql.Date(0));
			} else {
				Date toDt = sdf1.parse(bo.getToDate());
				String formatToDate = sdf.format(toDt);

				callableStmt.setDate(4, new java.sql.Date(sdf.parse(formatToDate).getTime()));
			}
			callableStmt.setString(5, bo.getPmsNumber());
			callableStmt.setString(6, bo.getLoanAccount());
			callableStmt.setString(7, bo.getMisMatch());
			callableStmt.registerOutParameter(8, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(8);

			rs = callableStmt.getResultSet();
			int index = 0;
			while (rs.next()) {
				RecoveryReportBO claimBO = new RecoveryReportBO();
				index++;
				claimBO.setSerialNo(index);
				claimBO.setMliId(rs.getString(1));
				claimBO.setMliName(rs.getString(2));
				claimBO.setRpNumber(rs.getString(3));
				claimBO.setUploadedDate(rs.getString(4));
				claimBO.setTotalRecord(rs.getString(5));
				claimBO.setStatus(rs.getString(6));
				claimBO.setRemark(rs.getString(7));
				claimBO.setVirtualAccountNo(rs.getString(8));
				claimBO.setApprovalDate(rs.getString(9));
				claimBO.setUploadedBy(rs.getString(10));
				claimBO.setApprovedBy(rs.getString(11));
				claimBO.setUtr(rs.getString(12));
				claimBO.setAppropriationDate(rs.getString(13));
				claimBO.setFtpResponse(rs.getString(14));
				claimBO.setMisMatch(rs.getString(15));
				claimBO.setRecoveryAmt(rs.getString(16));
				claimList.add(claimBO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimList;

	}

	@Override
	public List<RecoveryReportBO> findMliList(String mliId) {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<RecoveryReportBO> mliList = new ArrayList<>();
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
				RecoveryReportBO bo = new RecoveryReportBO();
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
	public List<RecoveryReportExcelBO> findExportToExcelData(RecoveryReportBO bo) {

		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<RecoveryReportExcelBO> claimList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

		try {

			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Recovery_Report(?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getMliId());
			if (bo.getFromDt() == null || bo.getFromDt().isEmpty()) {
				callableStmt.setDate(3, new java.sql.Date(0));
			} else {
				Date fromDt = sdf1.parse(bo.getFromDt());
				String formatfmDate = sdf.format(fromDt);
				callableStmt.setDate(3, new java.sql.Date(sdf.parse(formatfmDate).getTime()));
			}

			if (bo.getToDate() == null || bo.getToDate().isEmpty()) {
				callableStmt.setDate(4, new java.sql.Date(0));
			} else {
				Date toDt = sdf1.parse(bo.getToDate());
				String formatToDate = sdf.format(toDt);

				callableStmt.setDate(4, new java.sql.Date(sdf.parse(formatToDate).getTime()));
			}
			callableStmt.setString(5, bo.getPmsNumber());
			callableStmt.setString(6, bo.getLoanAccount());
			callableStmt.setString(7, bo.getMisMatch());
			callableStmt.registerOutParameter(8, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(8);

			rs = callableStmt.getResultSet();
			int index = 0;
			while (rs.next()) {
				RecoveryReportExcelBO claimBO = new RecoveryReportExcelBO();
				index++;
				claimBO.setSerialNo(index);
				claimBO.setMliId(rs.getString(1));
				claimBO.setMliName(rs.getString(2));
				claimBO.setRpNumber(rs.getString(3));
				claimBO.setUploadedDate(rs.getString(4));
				claimBO.setTotalRecord(rs.getString(5));
				claimBO.setStatus(rs.getString(6));
				claimBO.setRemark(rs.getString(7));
				claimBO.setVirtualAccountNo(rs.getString(8));
				claimBO.setApprovalDate(rs.getString(9));
				claimBO.setUploadedBy(rs.getString(10));
				claimBO.setApprovedBy(rs.getString(11));
				claimBO.setUtr(rs.getString(12));
				claimBO.setAppropriationDate(rs.getString(13));
				claimBO.setFtpResponse(rs.getString(14));
				claimBO.setMisMatch(rs.getString(15));
				claimBO.setRecoveryAmt(rs.getString(16));
				claimList.add(claimBO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimList;

	}

}
