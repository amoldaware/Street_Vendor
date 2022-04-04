package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.raistudies.domain.CustomExceptionHandler;

import oracle.jdbc.OracleTypes;
@Repository("ClosureRequestDao")
public class ClosureRequestDaoImpl implements ClosureRequestDao{
	@Autowired
	SessionFactory sessionFactory;
	
	public List<ApplicationStatusDetailsBean> getClosureDetailsForEdit(String userId,String status) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ApplicationStatusDetailsBean ApplicationStatusDetailsBean = null;
		ArrayList<ApplicationStatusDetailsBean> closureDetailsList = new ArrayList<ApplicationStatusDetailsBean>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FunGetAppClosureDetails(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, userId);
			getReceivedPaymentsStmt.registerOutParameter(3, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(4, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(4);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {
				Date npaDt = null;
				Date npaCreatedDate = null;
				DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception = getReceivedPaymentsStmt.getString(4);

				while (resultSet.next()) {

					result1 = 1;
					ApplicationStatusDetailsBean = new ApplicationStatusDetailsBean();
					ApplicationStatusDetailsBean.setCGPAN(resultSet.getString(3));
					//npaCreatedDate = resultSet.getDate(37);
					//npaDetailsBean.setNpaCreatedDate(dateFormat
					//		.format(npaCreatedDate));
					//npaDetailsBean.setLoanType(resultSet.getString(38));

					closureDetailsList.add(ApplicationStatusDetailsBean);

				}

			}

			resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

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

		// return npaDetailsBean;
		return closureDetailsList;

	
	}
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ApplicationStatusDetailsBean appClosureDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int dateCount=0;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultset = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call getAppClosureRequestDetails(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, cgpan);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();
			int result = stmt.getInt(1);
			
			if (result == 1) {

				String error = stmt.getString(4);
				stmt.close();
				stmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				appClosureDetailsBean = new ApplicationStatusDetailsBean();

			//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) stmt.getObject(3);
	
			while (resultset.next()) {
				
				appClosureDetailsBean.setBankName(resultset.getString("Bank_Name"));
				appClosureDetailsBean.setMEMBER_ID(resultset.getString("Member_ID"));
				appClosureDetailsBean.setMSE_NAME(resultset.getString("Unit_Name"));
				appClosureDetailsBean.setCGPAN(resultset.getString("CGPAN"));
				
			
				//npaDetailsList.add(npaDetailsBean);
			}
			
			}
			resultset.close();
			stmt.close();
			stmt = null;
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

		return appClosureDetailsBean;
	}

	public List<ApplicationStatusDetailsBean> getCgpanStatus(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ApplicationStatusDetailsBean appClosureDetailsBean = null;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = new ArrayList<ApplicationStatusDetailsBean>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FuncAppClosureValidation(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
			getReceivedPaymentsStmt.setString(3, userId);
			getReceivedPaymentsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(5, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(5);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {
				Date npaDt = null;
				Date npaCreatedDate = null;
				DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(4);
				exception = getReceivedPaymentsStmt.getString(5);

				while (resultSet.next()) {

					//result1 = 1;
					appClosureDetailsBean = new ApplicationStatusDetailsBean();
					
					  //npaDetailsBean.setNpaId(resultSet.getString(1));
					appClosureDetailsBean.setCGPAN(resultSet.getString(2));
				
					
					appClosureDetailsList.add(appClosureDetailsBean);

				}

			}

			resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

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

		// return npaDetailsBean;
		return appClosureDetailsList;

}
	
	public int saveAppClosureDetails(ApplicationStatusDetailsBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ApplicationStatusDetailsBean asBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement ct = null;

			ResultSet resultSet = null;
			String exception = "";
			ct = conn.prepareCall("{?=call FuncSaveAppClosure(?,?,?,?,?,?,?,?,?,?,?)}");
			ct.registerOutParameter(1, Types.INTEGER);
			ct.setString(2,bean.getMEMBER_ID());
			ct.setString(3,bean.getCGPAN());
			//ct.setString(4,bean.getAppClosureDate());
			
			
			
		//	ct.setString(3,bean.getNpaUpgradationDt());
			ct.setString(4,bean.getAppClosureDate());
			if (bean.getAppClosureDate() != null) {
				// sqlDate = new java.sql.Date(utilDateNpa.getTime());

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getAppClosureDate()
						.toString());
				java.sql.Date sqlNpaUpgradationDate = new java.sql.Date(
						parsednpadate.getTime());
				ct.setDate(4, sqlNpaUpgradationDate);
				// System.out.println("npa date:"+npaDetails.getNpaDate()+" npa date in sql:"+new
				// java.sql.Date(npaDetails.getNpaDate().getTime()));
			} else {
				ct.setDate(4, null);
			}
			
			ct.setString(5,bean.getAppClosureRemarks());
			ct.setString(6,usrId);
			ct.setString(7,bean.getCURRENT_SFEE_AMT());
			ct.setString(8,bean.getSFEE_RATE());
			ct.setString(9,bean.getEST_SFEE_DAYS());
			ct.setString(10,bean.getEST_SFEE_AMT());
			ct.setString(11,bean.getDAN_ID());
		ct.registerOutParameter(12, Types.VARCHAR);
		ct.execute();

			functionReturnValue = ct.getInt(1);

			if (functionReturnValue == 1) {

				String error = ct.getString(12);
				System.out.println(error);
				ct.close();
				ct = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

			}

			// resultSet.close();
			ct.close();
			ct = null;

			conn.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return functionReturnValue;
	}
	
	public List<ApplicationStatusDetailsBean> getAppClosureDetailsForApproval(String useId,String status) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getAppClosureDetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getAppClosureDetailsStatement = conn
					.prepareCall("{?=call GetAppClosureDtlApproval(?,?,?,?)}");
			getAppClosureDetailsStatement.registerOutParameter(1, Types.INTEGER);
			getAppClosureDetailsStatement.setString(2, useId);
			getAppClosureDetailsStatement.setString(3, status);
			getAppClosureDetailsStatement.registerOutParameter(4, OracleTypes.CURSOR);
			getAppClosureDetailsStatement.registerOutParameter(5, Types.VARCHAR);
			getAppClosureDetailsStatement.execute();

			int functionReturnValue = getAppClosureDetailsStatement.getInt(1);

			if (functionReturnValue == 1) {

				String error = getAppClosureDetailsStatement.getString(5);
				// System.out.println("Error:" + error);

				getAppClosureDetailsStatement.close();
				getAppClosureDetailsStatement = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getAppClosureDetailsStatement.getObject(4);
				exception = getAppClosureDetailsStatement.getString(5);

				String CGPAN = "";
				Date appClosureDt = null;
				String appClosureRemarks = "";
				//Date npaUpgradationDt=null;
				//String upgradationRemarks = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						appClosureDetailsList = new ArrayList();
					}
					// npaId = resultSet.getString(1);
					CGPAN = resultSet.getString(2);
					appClosureDt = resultSet.getDate(3);
					appClosureRemarks = resultSet.getString(4);

					ApplicationStatusDetailsBean na = new ApplicationStatusDetailsBean();
				//	na.setNpaId(resultSet.getString(1));
					na.setCGPAN(CGPAN);
					na.setAppClosureDate(dateFormat.format(appClosureDt));
					na.setAppClosureRemarks(appClosureRemarks);

					appClosureDetailsList.add(na);

					++noOfRecords;
				}
				// na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getAppClosureDetailsStatement.close();
			getAppClosureDetailsStatement = null;

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

		return appClosureDetailsList;

	}
	
	public ApplicationStatusDetailsBean getAppClosureDetails(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ApplicationStatusDetailsBean appClosureDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultset = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call getAppClosureDetails(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, cgpan);
			// stmt.setString(3, userId);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();
			int result = stmt.getInt(1);
			
			if (result == 1) {

				String error = stmt.getString(4);
				stmt.close();
				stmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) stmt.getObject(3);
			while (resultset.next()) {
				appClosureDetailsBean = new ApplicationStatusDetailsBean();

				appClosureDetailsBean.setBankName(resultset.getString("Bank_Name"));
				appClosureDetailsBean.setMEMBER_ID(resultset.getString("Member_ID"));
				appClosureDetailsBean.setMSE_NAME(resultset.getString("Unit_Name"));
				appClosureDetailsBean.setCGPAN(resultset.getString("CGPAN"));
				appClosureDetailsBean.setStatus(resultset.getString("Status"));
				appClosureDetailsBean.setAppClosureDate(resultset.getString("CLOSURE_DATE"));
				appClosureDetailsBean.setAppClosureRemarks(resultset.getString("CLOSURE_REMARKS"));
			
				//npaDetailsList.add(npaDetailsBean);
			}
			}
			resultset.close();
			stmt.close();
			stmt = null;
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

		return appClosureDetailsBean;
}
	
	public int SaveAppClosureReject(ApplicationStatusDetailsBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		ArrayList<ApplicationStatusDetailsBean> appClosureBean = null;

		Connection conn = session.connection();

		try {
				List arr=bean.getChcktbl();
			  Connection connection = null; CallableStatement ct = null;
			  
			  ResultSet resultSet = null; String exception = ""; 
			  for (int i = 0; i < arr.size(); i++) {
			  ct =conn.prepareCall("{?=call FunSaveAppClosureReject(?,?,?,?)}");
			  ct.registerOutParameter(1, Types.INTEGER);
			  //ct.setString(2,bean.getChcktbl()));
			  ct.setString(2,(String) arr.get(i));
			  ct.setString(3,bean.getAppClosureReturnRemarks());
			  ct.setString(4,usrId );
			  ct.registerOutParameter(5, Types.VARCHAR); ct.execute();
			  }
			  functionReturnValue = ct.getInt(1);
			  
			  if (functionReturnValue == 1) {
			  
			  String error = ct.getString(5); System.out.println(error); ct.close(); ct =
			  null;
			  
			  conn.rollback();
			  
			  throw new CustomExceptionHandler(error);
			  
			  }
			  
			  // resultSet.close(); 
			   ct.close(); 
			   ct = null;
			  
			  conn.commit();
			 
		} catch (Exception exception) {
			exception.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return functionReturnValue;
	}

	public List<ApplicationStatusDetailsBean> getAppClosureEditDetails(String userId,
			String status) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getAppClosureDetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getAppClosureDetailsStatement = conn
					.prepareCall("{?=call FunGetAppClosureEdit(?,?,?,?)}");
			getAppClosureDetailsStatement.registerOutParameter(1, Types.INTEGER);
			getAppClosureDetailsStatement.setString(2, userId);
			getAppClosureDetailsStatement.setString(3, status);
			getAppClosureDetailsStatement.registerOutParameter(4, OracleTypes.CURSOR);
			getAppClosureDetailsStatement.registerOutParameter(5, Types.VARCHAR);
			getAppClosureDetailsStatement.execute();

			int functionReturnValue = getAppClosureDetailsStatement.getInt(1);

			if (functionReturnValue == 1) {

				String error = getAppClosureDetailsStatement.getString(5);
				// System.out.println("Error:" + error);

				getAppClosureDetailsStatement.close();
				getAppClosureDetailsStatement = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getAppClosureDetailsStatement.getObject(4);
				exception = getAppClosureDetailsStatement.getString(5);

				String CGPAN = "";
				Date appClosureDt = null;
				String appClosureReturnRemarks = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						appClosureDetailsList = new ArrayList();
					}
					// npaId = resultSet.getString(1);
					CGPAN = resultSet.getString(2);
					appClosureDt = resultSet.getDate(3);
					appClosureReturnRemarks = resultSet.getString(15);

					ApplicationStatusDetailsBean na = new ApplicationStatusDetailsBean();
				//	na.setNpaId(resultSet.getString(1));
					na.setCGPAN(CGPAN);
					na.setAppClosureDate(dateFormat.format(appClosureDt));
					na.setAppClosureReturnRemarks(appClosureReturnRemarks);

					appClosureDetailsList.add(na);

					++noOfRecords;
				}
				// na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getAppClosureDetailsStatement.close();
			getAppClosureDetailsStatement = null;

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

		return appClosureDetailsList;

	}
	
	public ApplicationStatusDetailsBean getEditAppClosureDetails(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ApplicationStatusDetailsBean appClosureDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<ApplicationStatusDetailsBean> appClosureDetailsList = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultset = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call getAppClosureDetails(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, cgpan);
			// stmt.setString(3, userId);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();
			int result = stmt.getInt(1);
			
			if (result == 1) {

				String error = stmt.getString(4);
				stmt.close();
				stmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) stmt.getObject(3);
			while (resultset.next()) {
				appClosureDetailsBean = new ApplicationStatusDetailsBean();

				appClosureDetailsBean.setBankName(resultset.getString("Bank_Name"));
				appClosureDetailsBean.setMEMBER_ID(resultset.getString("Member_ID"));
				appClosureDetailsBean.setMSE_NAME(resultset.getString("Unit_Name"));
				appClosureDetailsBean.setCGPAN(resultset.getString("CGPAN"));
				appClosureDetailsBean.setStatus(resultset.getString("Status"));
				appClosureDetailsBean.setAppClosureDate(resultset.getString("CLOSURE_DATE"));
				appClosureDetailsBean.setAppClosureRemarks(resultset.getString("CLOSURE_REMARKS"));
			
				//npaDetailsList.add(npaDetailsBean);
			}
			}
			resultset.close();
			stmt.close();
			stmt = null;
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

		return appClosureDetailsBean;
	}
	
	public int SaveAppClosureEdit(ApplicationStatusDetailsBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement ct = null;

			ResultSet resultSet = null;
			String exception = "";
			ct = conn.prepareCall("{?=call FuncSaveEditAppClosure(?,?,?,?,?)}");
			ct.registerOutParameter(1, Types.INTEGER);
			ct.setString(2,bean.getCGPAN());
			//ct.setString(3,bean.getNpaUpgradationDt());
			if (bean.getAppClosureDate() != null) {
				// sqlDate = new java.sql.Date(utilDateNpa.getTime());

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getAppClosureDate()
						.toString());
				java.sql.Date sqlAppClosureDate = new java.sql.Date(
						parsednpadate.getTime());
				ct.setDate(3, sqlAppClosureDate);
				// System.out.println("npa date:"+npaDetails.getNpaDate()+" npa date in sql:"+new
				// java.sql.Date(npaDetails.getNpaDate().getTime()));
			} else {
				ct.setDate(3, null);
			}
		ct.setString(4,bean.getAppClosureRemarks());
		ct.setString(5,usrId );
			
		ct.registerOutParameter(6, Types.VARCHAR);
		ct.execute();

			functionReturnValue = ct.getInt(1);

			if (functionReturnValue == 1) {

				String error = ct.getString(6);
				System.out.println(error);
				ct.close();
				ct = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

			}

			// resultSet.close();
			ct.close();
			ct = null;

			conn.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return functionReturnValue;
	}
	
	public int SaveAppClosureApprove(ApplicationStatusDetailsBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		
		
		int functionReturnValue = 0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			List arr=bean.getChcktbl();
		  Connection connection = null; CallableStatement ct = null;
		  
		  ResultSet resultSet = null; String exception = ""; 
		  for (int i = 0; i < arr.size(); i++) {
			  
		  ct =conn.prepareCall("{?=call FuncSaveAppClosureApprove(?,?,?)}");
		  ct.registerOutParameter(1, Types.INTEGER);
		  //ct.setString(2,bean.getChcktbl()));
		  ct.setString(2,(String) arr.get(i));
		  ct.setString(3,usrId );
		  ct.registerOutParameter(4, Types.VARCHAR); ct.execute();
		  }
		  functionReturnValue = ct.getInt(1);
		  
		  if (functionReturnValue == 1) {
		  
		  String error = ct.getString(4); 
		  System.out.println(error); 
		  ct.close(); 
		  ct = null;
		  
		 // conn.rollback();
		  
		  throw new CustomExceptionHandler(error);
		  
		  
		  }
		 

			// resultSet.close();
			ct.close();
			ct = null;

			//conn.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			
			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		return functionReturnValue;
	}
	
	@Override
	public List<Map<String, Object>> getAppClosureRequestReport(String userId, Date toDate,
			Date fromDate, String memberId,String role) {
		// TODO Auto-generated method stub
		ResultSet resultset ;
		ResultSetMetaData resultSetMetaData;
	ClaimLodgementBean claimLodgementBean;
		List<Map<String, Object>> ClaimDetailsReportData = new ArrayList<Map<String, Object>>();
		try {
			Session session4 = sessionFactory.openSession();
             System.out.println("memberId :"+memberId);
         //    System.out.println("claimStatus=="+claimStatus);
			/* Transaction tn = session4.beginTransaction(); */
             
             System.out.println("Util==toDate=="+toDate);
             System.out.println("Util==fromDate=="+fromDate);
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call FunGetAppClosureDetailsData(?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setDate(2, new java.sql.Date(toDate.getTime()));
			System.out.println("Sql==toDate=="+toDate.getTime());
			cs.setDate(3, new java.sql.Date(fromDate.getTime()));
			System.out.println("Sql==fromDate=="+fromDate.getTime());
			//cs.setDate(2,toDate);
			//cs.setDate(3,new java.sql.Date (fromDate));
			cs.setString(4, memberId);
			cs.setString(5, role);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			cs.registerOutParameter(7, Types.VARCHAR);
			
			cs.execute();
			int result = cs.getInt(1);

			String pouterror = cs.getString(7);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (result != 0) {
				throw new CustomExceptionHandler("Exception occured  :"+ pouterror);
			}else{
				 // Procedure execution
				resultset = (ResultSet) cs.getObject(6);
					resultSetMetaData = resultset.getMetaData();
					int coulmnCount = resultSetMetaData.getColumnCount();
					System.out.println("coulmnCount=="+coulmnCount);
					while (resultset.next()) {
					    Map<String, Object> columns = new LinkedHashMap<String, Object>();

					    for (int i = 1; i <= coulmnCount; i++) {
					        columns.put(resultSetMetaData.getColumnLabel(i), resultset.getObject(i));
					    }

					    ClaimDetailsReportData.add(columns);
					}
			}

				
				
		

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error=="+e);
		}
		return ClaimDetailsReportData;

	}
}
