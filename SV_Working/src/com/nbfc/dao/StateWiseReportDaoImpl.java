package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raistudies.domain.CustomExceptionHandler;

@Repository("StateWiseReportDao")
public class StateWiseReportDaoImpl implements StateWiseReportDao{

	@Autowired
	SessionFactory sessionFactory;
	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;
	
	@Override
	public List<Map<String, Object>> getStateDetails(String userId,String role,
			Date toDate, Date fromDate, String guaranteeStatus) {
			
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Session session4 = sessionFactory.openSession();
		Transaction tn = session4.beginTransaction(); 
		Connection conn = session4.connection();
		try {
			String status=null;
			
			 
			if(guaranteeStatus.equalsIgnoreCase("Y")){
				status="CCA";
			}else{
				status="AJ";
			}
			CallableStatement cs = conn
					.prepareCall("{? = call MIS_REPORT.MIS_STATEWISE_REPORT(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setString(3, role);
			cs.setDate(4, new java.sql.Date(toDate.getTime()));
			cs.setDate(5, new java.sql.Date( fromDate.getTime()));
			cs.setString(6, status);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(8);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(7);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException exception) {
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		}
		finally {
			session4.close();

		}
		return rows;

	}
	@Override
	public List<Map<String, Object>> getStateMliWiseDetails(String userId,String role,
			Date toDate, Date fromDate, String state) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Session session5 = sessionFactory.openSession();
		 Transaction tn = session5.beginTransaction(); 
			Connection conn = session5.connection();
		try {
			
			CallableStatement cs = conn
					.prepareCall("{? = call MIS_REPORT.MIS_STATE_MLIWISE_REPORT(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setString(3, role);
			cs.setDate(4, new java.sql.Date(toDate.getTime()));
			cs.setDate(5, new java.sql.Date( fromDate.getTime()));
			cs.setString(6, state);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(8);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(7);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException exception) {
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		}finally {
			session5.close();
		}
		return rows;
	}
	@Override
	public List<Map<String, Object>> getStateMliWiseGIssuedDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Session session6 = sessionFactory.openSession();
		 Transaction tn = session6.beginTransaction(); 
			Connection conn = session6.connection();
		try {
			
			
			
			CallableStatement cs = conn
					.prepareCall("{? = call MIS_REPORT.MIS_STATE_GI_MLIWISE_REPORT(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setString(3, role);
			cs.setDate(4, new java.sql.Date(toDate.getTime()));
			cs.setDate(5, new java.sql.Date( fromDate.getTime()));
			cs.setString(6, state);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(8);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(7);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException exception) {
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		}finally {
			session6.close();

		}
		return rows;
	}
	@Override
	public List<Map<String, Object>> getGuaranteeApprovedDistWiseDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Session session7 = sessionFactory.openSession();
		Transaction tn = session7.beginTransaction(); 
		Connection conn = session7.connection();
		try {
			
			CallableStatement cs = conn
					.prepareCall("{? = call MIS_REPORT.MIS_DISTWISE_GA_REPORT(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setString(3, role);
			cs.setDate(4, new java.sql.Date(toDate.getTime()));
			cs.setDate(5, new java.sql.Date( fromDate.getTime()));
			cs.setString(6, state);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(8);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(7);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException exception) {
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		}finally {
			session7.close();

		}
		return rows;
	
	}
	@Override
	public List<Map<String, Object>> getGuaranteeIssuedDistWiseDetails(
			String userId,String role, Date toDate, Date fromDate, String state) {

		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Session session8 = sessionFactory.openSession();
		Transaction tn = session8.beginTransaction(); 
		Connection conn = session8.connection();
		try {
			
			CallableStatement cs = conn
					.prepareCall("{? = call MIS_REPORT.MIS_DISTWISE_GI_REPORT(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.setString(3, role);
			cs.setDate(4, new java.sql.Date(toDate.getTime()));
			cs.setDate(5, new java.sql.Date( fromDate.getTime()));
			cs.setString(6, state);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(7);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"
						+ pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(7);
				resultSetMetaData = resultset.getMetaData();
				int coulmnCount = resultSetMetaData.getColumnCount();
				while (resultset.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= coulmnCount; i++) {
						columns.put(resultSetMetaData.getColumnLabel(i),
								resultset.getObject(i));
					}
					rows.add(columns);
				}
			}

		} catch (SQLException exception) {
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		}finally {
			session8.close();

		}
		return rows;
	
	
	}

}
