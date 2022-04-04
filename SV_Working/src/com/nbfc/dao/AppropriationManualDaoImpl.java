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

import com.nbfc.bean.AppropriationManualBO;
import com.nbfc.exception.BusinessException;

@Repository
@Transactional
public class AppropriationManualDaoImpl implements AppropriationManualDao {
	final static Logger logger = Logger.getLogger(AppropriationManualDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<AppropriationManualBO> findAll(AppropriationManualBO bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<AppropriationManualBO> claimList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Appropriation_data(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getMliId());
			if (bo.getFromDt() == null || bo.getFromDt().isEmpty()) {
				callableStmt.setDate(3, new java.sql.Date(0));
			} else {
				callableStmt.setDate(3, new java.sql.Date(sdf.parse(bo.getFromDt()).getTime()));
			}
			if (bo.getToDate() == null || bo.getToDate().isEmpty()) {
				callableStmt.setDate(4, new java.sql.Date(0));
			} else {
				callableStmt.setDate(4, new java.sql.Date(sdf.parse(bo.getToDate()).getTime()));
			}
			callableStmt.setString(5, bo.getAppropriatedCase());
			callableStmt.registerOutParameter(6, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(6);

			rs = callableStmt.getResultSet();
			while (rs.next()) {
				AppropriationManualBO claimBO = new AppropriationManualBO();
				claimBO.setMliId(rs.getString(1));
				claimBO.setMliName(rs.getString(2));
				claimBO.setRpNumber(rs.getString(3));
				claimBO.setUploadedDate(rs.getString(4));
				claimBO.setVirtualAccountNo(rs.getString(5));
				claimBO.setInitiatedDt(rs.getString(6));
				claimBO.setUtrNumber(rs.getString(7));
				claimBO.setAppropriationDate(rs.getString(8));
				claimBO.setAppropriatedCase(rs.getString(9) == null? "N" : rs.getString(9));
				//claimBO.setIs_Appropriation(rs.getString(9) == null? "N" : rs.getString(9));
				System.out.println("List Contains :::" + claimBO.getIs_Appropriation());
				claimList.add(claimBO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return claimList;
	}

	@Override
	public boolean save(List<AppropriationManualBO> listBO) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_Update_Recovery_Header_Appropriation(?,?,?,?,?,?,?)}");
			for (AppropriationManualBO bo : listBO) {

				callableStmt.registerOutParameter(1, Types.INTEGER);
				callableStmt.setString(2, bo.getRpNumber());
				callableStmt.setString(3, bo.getUtrNumber());
				callableStmt.setString(4, bo.getAppropriationDate());
				callableStmt.setString(5, bo.getMliId());
				callableStmt.setString(6, bo.getUserId());
				callableStmt.registerOutParameter(7, Types.VARCHAR);
				callableStmt.execute();
				String error = callableStmt.getString(7);
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
