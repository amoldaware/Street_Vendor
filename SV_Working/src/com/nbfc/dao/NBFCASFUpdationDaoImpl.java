package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbfc.bean.ASFGenerationDetailBean;
import com.nbfc.bean.NBFCASFUpdationBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.raistudies.domain.CustomExceptionHandler;

@Service("NBFCASFUpdationDao")
public class NBFCASFUpdationDaoImpl implements NBFCASFUpdationDao {
	@Autowired
	SessionFactory sessionFactory;
	ResultSet resultset = null;
	ResultSetMetaData resultSetMetaData = null;

	@Override
	public List getASFDetails(String danId) {
		CallableStatement callableStmt = null;
		ResultSet resultset = null;
		ResultSetMetaData resultSetMetaData = null;
		ArrayList coulmName = new ArrayList();
		ArrayList nestData = new ArrayList();
		ArrayList ClaimSettlePaymentReportData = new ArrayList();
		int status = -1;
		String errorCode = null;
		// System.out.println("cgpanPayment :"+cgpanPayment);
		try {
			// System.out.println("sqlfromdate :" + sqlfromdate);
			// System.out.println("sqltodate :" + sqltodate);
			// ##
			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			callableStmt = conn.prepareCall("{?=call FUN_DOWNASF_BY_DANID(?,?,?)}");
			callableStmt.registerOutParameter(1, java.sql.Types.INTEGER);
			callableStmt.setString(2, danId);
			callableStmt.registerOutParameter(3, OracleTypes.CURSOR);
			callableStmt.registerOutParameter(4, Types.VARCHAR);
			callableStmt.execute();
			int result = callableStmt.getInt(1);
			String pouterror = callableStmt.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :" + pouterror);
			} else {

				resultset = (ResultSet) callableStmt.getObject(3);

				int coulmnCount = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= coulmnCount; i++) {
					coulmName.add(resultSetMetaData.getColumnName(i));
				}

				while (resultset.next()) {

					ArrayList columnValue = new ArrayList();
					for (int i = 1; i <= coulmnCount; i++) {
						columnValue.add(resultset.getString(i));
					}
					nestData.add(columnValue);
				}
				// System.out.println("list data " + nestData);
				ClaimSettlePaymentReportData.add(0, coulmName);
				ClaimSettlePaymentReportData.add(1, nestData);
			}
			resultset.close();
			resultset = null;
			callableStmt.close();
			callableStmt = null;
			resultSetMetaData = null;
		} catch (SQLException sqlexception) {

		} finally {
			// DBConnection.freeConnection(conn);
		}
		return ClaimSettlePaymentReportData;
	}

	@Override
	public List<NBFCASFUpdationBean> getASFDetailsByPortfolio(String userId) {
		List<NBFCASFUpdationBean> rows = new ArrayList<NBFCASFUpdationBean>();
		try {

			Session session4 = sessionFactory.openSession();
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn.prepareCall("{? = call FUN_GET_ASF_BY_PORTFOLIONAME(?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			int result = cs.getInt(1);
			String pouterror = cs.getString(4);
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :" + pouterror);
			} else {
				// Procedure execution
				resultset = (ResultSet) cs.getObject(3);

				while (resultset.next()) {

					NBFCASFUpdationBean na = new NBFCASFUpdationBean();
					na.setPortfolio_name(resultset.getString(1));
					na.setUpload_Date(resultset.getString(2));
					na.setFileid(resultset.getString(3));
					rows.add(na);

				}
				// na.setChcktbl(receivedPayments);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

	@Override
	public int approveASFData(List<String> checkedData, String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		if (checkedData.size() > 0) {
			
			for (String na : checkedData) {
			
				Session session3 = sessionFactory.openSession();
				Transaction tn = session3.beginTransaction();
				
				Connection con = session3.connection();
				try {
					CallableStatement callable = (CallableStatement) con
							.prepareCall("{ call ProcAutoGenNBFCDANASF1(?,?,?) } ");
					callable.setString(1, na);
					callable.setString(2, userId);
					callable.registerOutParameter(3, Types.VARCHAR);
					callable.execute();
					String pouterror1 = callable.getString(3);
					if (pouterror1 != null) {
						throw new CustomExceptionHandler("Error Occured  :" + pouterror1);

					}

					tn.commit();
				} catch (SQLException e) {
					if (tn != null)
						tn.rollback();
					e.printStackTrace();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						con.close();
						// session.close();
						session3.close();
						sessionFactory.close();
					} catch (Exception e2) {

					}
				}

			}

		}
		return 0;

	}

	@Override
	public List<ASFGenerationDetailBean> getAllUpdatedOSAmtDetails(String userId, String fileid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
	
		ArrayList<ASFGenerationDetailBean> ASFBeanList = new ArrayList<ASFGenerationDetailBean>();

		Connection conn = session.connection(); 

		try {
			Connection connection = null;
			CallableStatement getASfdataStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getASfdataStmt = conn
					.prepareCall(" { call NBFC.PROC_UPDATED_OSAMT_DETAILS(?,?,?,?) }");
		
			getASfdataStmt.setString(1, userId);
			getASfdataStmt.setString(2, fileid);
			getASfdataStmt.registerOutParameter(3, Types.VARCHAR);
			getASfdataStmt.registerOutParameter(4, OracleTypes.CURSOR);
			
			getASfdataStmt.execute();

			String pouterror1 = getASfdataStmt.getString(3);
			if (pouterror1 != null) {
				throw new CustomExceptionHandler("Error Occured  :"
						+ pouterror1);
		
			} else {

				resultSet = (ResultSet) getASfdataStmt.getObject(4);
				exception = getASfdataStmt.getString(3);
				if(resultSet!=null){
					ASFGenerationDetailBean asfBean=null;
					while (resultSet.next()) {
						asfBean = new ASFGenerationDetailBean();					
						asfBean.setCGPAN(resultSet.getString(1));				
						asfBean.setItpan(resultSet.getString(3));					
						asfBean.setLoadAccountNo(resultSet.getString(2));	
						asfBean.setOutstanding_date(resultSet.getString(7));	
						asfBean.setOutstanding_amount(resultSet.getDouble(4));	
						asfBean.setPrevoutstandingAmt(resultSet.getDouble(6));	
						asfBean.setMse_name(resultSet.getString(5));	
						ASFBeanList.add(asfBean);
					}
					
					
				
				}
			
				
			}
			resultSet.close();
			getASfdataStmt.close();
			getASfdataStmt = null;

			conn.commit();

		} catch (Exception exception) {

			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return ASFBeanList;
		
		
	}

}
