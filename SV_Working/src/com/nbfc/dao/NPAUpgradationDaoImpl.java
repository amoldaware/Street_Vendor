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
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("NPAUpgradationDao")
public class NPAUpgradationDaoImpl implements NPAUpgradationDao{
	
	@Autowired
	SessionFactory sessionFactory;
	public List<NpaUpgradationBean> getCgpanNpaDetails(String cgpan) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		NpaUpgradationBean npaDetailsBean = null;
		ArrayList<NpaUpgradationBean> npadetailsList = new ArrayList<NpaUpgradationBean>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNGetNPADetails(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
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
					npaDetailsBean = new NpaUpgradationBean();
					npaDetailsBean.setCGPAN(resultSet.getString(3));
					npaDetailsBean.setNpaReason(resultSet.getString(10));
					npaDt = resultSet.getDate(4);
					//npaCreatedDate = resultSet.getDate(37);

					npaDetailsBean.setNpaDt(dateFormat.format(npaDt));
					//npaDetailsBean.setNpaCreatedDate(dateFormat
					//		.format(npaCreatedDate));
					//npaDetailsBean.setLoanType(resultSet.getString(38));

					npadetailsList.add(npaDetailsBean);

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
		return npadetailsList;

	
	}
	
	
	public List<NpaUpgradationBean>getCgpanNpaUpgradationDetails(String cgpan) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		NpaUpgradationBean npaDetailsBean = null;
		ArrayList<NpaUpgradationBean> npadetailsList = new ArrayList<NpaUpgradationBean>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FunGetNPAUpgradationDetails(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
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
					npaDetailsBean = new NpaUpgradationBean();
					npaDetailsBean.setCGPAN(resultSet.getString(3));
					//npaCreatedDate = resultSet.getDate(37);
					//npaDetailsBean.setNpaCreatedDate(dateFormat
					//		.format(npaCreatedDate));
					//npaDetailsBean.setLoanType(resultSet.getString(38));

					npadetailsList.add(npaDetailsBean);

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
		return npadetailsList;

	
	}
	public List<NpaUpgradationBean> getNPAUpgradationDetailsForApproval(String useId,String status) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NpaUpgradationBean> npaDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getNPADetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getNPADetailsStatement = conn
					.prepareCall("{?=call GetNPAUpgradationDtlCK(?,?,?,?)}");
			getNPADetailsStatement.registerOutParameter(1, Types.INTEGER);
			getNPADetailsStatement.setString(2, useId);
			getNPADetailsStatement.setString(3, status);
			getNPADetailsStatement.registerOutParameter(4, OracleTypes.CURSOR);
			getNPADetailsStatement.registerOutParameter(5, Types.VARCHAR);
			getNPADetailsStatement.execute();

			int functionReturnValue = getNPADetailsStatement.getInt(1);

			if (functionReturnValue == 1) {

				String error = getNPADetailsStatement.getString(5);
				// System.out.println("Error:" + error);

				getNPADetailsStatement.close();
				getNPADetailsStatement = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getNPADetailsStatement.getObject(4);
				exception = getNPADetailsStatement.getString(5);

				String CGPAN = "";
				Date npaDt = null;
				String npaReason = "";
				Date npaUpgradationDt=null;
				String upgradationRemarks = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						npaDetailsList = new ArrayList();
					}
					// npaId = resultSet.getString(1);
					CGPAN = resultSet.getString(3);
					npaDt = resultSet.getDate(7);
					//npaReason = resultSet.getString(10);
					npaUpgradationDt = resultSet.getDate(8);
					upgradationRemarks = resultSet.getString(9);

					NpaUpgradationBean na = new NpaUpgradationBean();
				//	na.setNpaId(resultSet.getString(1));
					na.setCGPAN(CGPAN);
					na.setNpaDt(dateFormat.format(npaDt));
					na.setNpaUpgradationDt(dateFormat.format(npaUpgradationDt));
					na.setUpgradationRemarks(upgradationRemarks);

					npaDetailsList.add(na);

					++noOfRecords;
				}
				// na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getNPADetailsStatement.close();
			getNPADetailsStatement = null;

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

		return npaDetailsList;

	}
	
	public NpaUpgradationBean getNPADetailsForUpgradation(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NpaUpgradationBean npaDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NpaUpgradationBean> npaDetailsList = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultset = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call getNPADetailsForUpgradation(?,?,?)}");
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
				npaDetailsBean = new NpaUpgradationBean();

				npaDetailsBean.setBankName(resultset.getString("Bank_Name"));
				npaDetailsBean.setMLIID(resultset.getString("Member_ID"));
				npaDetailsBean.setBorrowerName(resultset.getString("Unit_Name"));
				npaDetailsBean.setCGPAN(resultset.getString("CGPAN"));
				npaDetailsBean.setStatus(resultset.getString("Status"));
				npaDetailsBean.setNpaDt(resultset.getString("NPA_DATE"));
				npaDetailsBean.setNpaReason(resultset.getString("NPA_REASON"));
			
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

		return npaDetailsBean;
}
	
	public NpaUpgradationBean getNPAUpgradationDetails(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NpaUpgradationBean npaDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NpaUpgradationBean> npaDetailsList = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultset = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call getNPAUpgradationDetails(?,?,?)}");
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
				npaDetailsBean = new NpaUpgradationBean();

				npaDetailsBean.setBankName(resultset.getString("Bank_Name"));
				npaDetailsBean.setMLIID(resultset.getString("Member_ID"));
				npaDetailsBean.setBorrowerName(resultset.getString("Unit_Name"));
				npaDetailsBean.setCGPAN(resultset.getString("CGPAN"));
				npaDetailsBean.setStatus(resultset.getString("Status"));
				npaDetailsBean.setNpaDt(resultset.getString("NPA_DATE"));
				npaDetailsBean.setNpaReason(resultset.getString("NPA_REASON"));
				npaDetailsBean.setNpaUpgradationDt(resultset.getString("NPA_UPGRADE_DT"));
				npaDetailsBean.setUpgradationRemarks(resultset.getString("NUD_USER_REMARKS"));
			
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

		return npaDetailsBean;
}

	public List<NpaUpgradationBean> getCgpanStatus(String cgpan, String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		NpaUpgradationBean npaDetailsBean = null;
		ArrayList<NpaUpgradationBean> npadetailsList = new ArrayList<NpaUpgradationBean>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call NpaUpgradeValidation(?,?,?,?)}");
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
					npaDetailsBean = new NpaUpgradationBean();
					
					  //npaDetailsBean.setNpaId(resultSet.getString(1));
					 npaDetailsBean.setCGPAN(resultSet.getString(2));
					//  npaDetailsBean.setNpaReason(resultSet.getString(10)); npaDt =
					//  resultSet.getDate(4); //npaCreatedDate = resultSet.getDate(37);
					 
					//  npaDetailsBean.setNpaDt(dateFormat.format(npaDt));
					// //npaDetailsBean.setNpaCreatedDate(dateFormat // .format(npaCreatedDate));
					  //npaDetailsBean.setLoanType(resultSet.getString(38));
					
					npadetailsList.add(npaDetailsBean);

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
		return npadetailsList;

}
	
	public List<NpaUpgradationBean> getNPADetailsForUpgradationEdit(String userId,
			String status) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NpaUpgradationBean> npaDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getNPADetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getNPADetailsStatement = conn
					.prepareCall("{?=call FunGetNPAUpgradationEdit(?,?,?,?)}");
			getNPADetailsStatement.registerOutParameter(1, Types.INTEGER);
			getNPADetailsStatement.setString(2, userId);
			getNPADetailsStatement.setString(3, status);
			getNPADetailsStatement.registerOutParameter(4, OracleTypes.CURSOR);
			getNPADetailsStatement.registerOutParameter(5, Types.VARCHAR);
			getNPADetailsStatement.execute();

			int functionReturnValue = getNPADetailsStatement.getInt(1);

			if (functionReturnValue == 1) {

				String error = getNPADetailsStatement.getString(5);
				// System.out.println("Error:" + error);

				getNPADetailsStatement.close();
				getNPADetailsStatement = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getNPADetailsStatement.getObject(4);
				exception = getNPADetailsStatement.getString(5);

				String CGPAN = "";
				Date npaDt = null;
				String npaReason = "";
				Date npaUpgradationDt=null;
				String upgradationReturnRemarks = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						npaDetailsList = new ArrayList();
					}
					// npaId = resultSet.getString(1);
					CGPAN = resultSet.getString(3);
					npaDt = resultSet.getDate(7);
					npaUpgradationDt = resultSet.getDate(8);
					//npaReason = resultSet.getString(10);
					upgradationReturnRemarks = resultSet.getString(10);

					NpaUpgradationBean na = new NpaUpgradationBean();
				//	na.setNpaId(resultSet.getString(1));
					na.setCGPAN(CGPAN);
					na.setNpaDt(dateFormat.format(npaDt));
					na.setUpgradationReturnRemarks(upgradationReturnRemarks);

					npaDetailsList.add(na);

					++noOfRecords;
				}
				// na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getNPADetailsStatement.close();
			getNPADetailsStatement = null;

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

		return npaDetailsList;

	}
	
	public int saveNPAUpgradationDetails(NpaUpgradationBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
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
			ct = conn.prepareCall("{?=call FuncSaveNpaUpgradation(?,?,?,?,?)}");
			ct.registerOutParameter(1, Types.INTEGER);
			ct.setString(2,bean.getCGPAN());
		//	ct.setString(3,bean.getNpaUpgradationDt());
			if (bean.getNpaUpgradationDt() != null) {
				// sqlDate = new java.sql.Date(utilDateNpa.getTime());

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getNpaUpgradationDt()
						.toString());
				java.sql.Date sqlNpaUpgradationDate = new java.sql.Date(
						parsednpadate.getTime());
				ct.setDate(3, sqlNpaUpgradationDate);
				// System.out.println("npa date:"+npaDetails.getNpaDate()+" npa date in sql:"+new
				// java.sql.Date(npaDetails.getNpaDate().getTime()));
			} else {
				ct.setDate(3, null);
			}
		ct.setString(4,bean.getUpgradationRemarks());
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
	
	public int saveNPAUpgradationEditDetails(NpaUpgradationBean bean,String usrId) {

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
			ct = conn.prepareCall("{?=call FuncSaveEditNpaUpgradation(?,?,?,?,?)}");
			ct.registerOutParameter(1, Types.INTEGER);
			ct.setString(2,bean.getCGPAN());
			//ct.setString(3,bean.getNpaUpgradationDt());
			if (bean.getNpaUpgradationDt() != null) {
				// sqlDate = new java.sql.Date(utilDateNpa.getTime());

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getNpaUpgradationDt()
						.toString());
				java.sql.Date sqlNpaUpgradationDate = new java.sql.Date(
						parsednpadate.getTime());
				ct.setDate(3, sqlNpaUpgradationDate);
				// System.out.println("npa date:"+npaDetails.getNpaDate()+" npa date in sql:"+new
				// java.sql.Date(npaDetails.getNpaDate().getTime()));
			} else {
				ct.setDate(3, null);
			}
		ct.setString(4,bean.getUpgradationRemarks());
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
	
	public int SaveNPAUpgradationApprove(NpaUpgradationBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			List arr=bean.getChcktbl();
		  Connection connection = null; CallableStatement ct = null;
		  
		  ResultSet resultSet = null; String exception = ""; 
		  for (int i = 0; i < arr.size(); i++) {
			  
		  ct =conn.prepareCall("{?=call FunSaveNPAUpgradationApprove(?,?,?)}");
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
	
	public int SaveNPAUpgradationReject(NpaUpgradationBean bean,String usrId) {

		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		ArrayList<NpaUpgradationBean> npaUpgradationBean = null;

		Connection conn = session.connection();

		try {
				List arr=bean.getChcktbl();
			  Connection connection = null; CallableStatement ct = null;
			  
			  ResultSet resultSet = null; String exception = ""; 
			  for (int i = 0; i < arr.size(); i++) {
			  ct =conn.prepareCall("{?=call FunSaveNPAUpgradationReject(?,?,?,?)}");
			  ct.registerOutParameter(1, Types.INTEGER);
			  //ct.setString(2,bean.getChcktbl()));
			  ct.setString(2,(String) arr.get(i));
			  ct.setString(3,bean.getUpgradationReturnRemarks());
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
			 
			
			/*List arr=bean.getChcktbl();
			
			int checkKey = (Integer) null;
			String commCgpanVal = null;
			for (int i = 0; i < arr.size(); i++) {
				checkKey = arr.indexOf(i);
				commCgpanVal = (String) commentCgpan.get(checkKey);
				
				CallableStatement callable = conn.prepareCall("{?=call "
						+ "FunSaveNPAUpgradationReject(?,?,?,?,?)}");
				callable.registerOutParameter(1, Types.INTEGER);
				callable.setString(2, memberId);
				callable.setString(3, checkKey);
				callable.setString(4, commCgpanVal);
				callable.setString(5, userId);
				callable.registerOutParameter(6, Types.VARCHAR);
				callable.execute();
				int errorCode = callable.getInt(1);
				String error = callable.getString(6);
				// System.out.println("Error:"+error);

				Log.log(Log.DEBUG, "GMDAO", "submitUpgradationDetails",
						"error code and error" + errorCode + "," + error);

				if (errorCode == Constants.FUNCTION_FAILURE) {
					Log.log(Log.ERROR, "GMDAO", "submitUpgradationDetails", error);

					callable.close();
					callable = null;
					throw new DatabaseException(error);
				}

				callable.close();
				callable = null;
			}*/

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
	
	@Override
	public List<NpaUpgradationBean> getNpaUpgradationReport(String userId, Date toDate,
			Date fromDate, String memberId,String mliLongName,String role) {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		ResultSetMetaData resultSetMetaData = null;
		NpaUpgradationBean npaUpgradationBean = null;
		List<NpaUpgradationBean> NpaUpgradationReport = new ArrayList<NpaUpgradationBean>();
		try {
			Session session4 = sessionFactory.openSession();
             System.out.println("memberId :"+memberId);
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call FunGetNPAUpgradationReport(?,?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setDate(2, new java.sql.Date(toDate.getTime()));
			cs.setDate(3, new java.sql.Date(fromDate.getTime()));
			cs.setString(4, memberId);
			cs.setString(5, mliLongName);
			cs.setString(6, role);
			cs.registerOutParameter(7, OracleTypes.CURSOR);
			cs.registerOutParameter(8, Types.VARCHAR);
			
			cs.execute();
			int result = cs.getInt(1);

			String pouterror = cs.getString(8);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) cs.getObject(7);
			while (resultset.next()) {
				npaUpgradationBean = new NpaUpgradationBean();

				npaUpgradationBean.setBankName(resultset.getString("Bank_Name"));
				npaUpgradationBean.setMLIID(resultset.getString("Member_ID"));
				npaUpgradationBean.setBorrowerName(resultset.getString("Unit_Name"));
				npaUpgradationBean.setCGPAN(resultset.getString("CGPAN"));
				npaUpgradationBean.setStatus(resultset.getString("STATUS"));
				npaUpgradationBean.setNpaDt(resultset.getString("NPA_EFFECTIVE_DT"));
				npaUpgradationBean.setNpaUpgradationDt(resultset.getString("NPA_UPGRADE_DT"));
				npaUpgradationBean.setUpgradationRemarks(resultset.getString("NUD_USER_REMARKS"));
			

				NpaUpgradationReport.add(npaUpgradationBean);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return NpaUpgradationReport;

}
	
}
