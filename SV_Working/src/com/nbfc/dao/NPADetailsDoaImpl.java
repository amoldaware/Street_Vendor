package com.nbfc.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.raistudies.domain.CustomExceptionHandler;
import java.text.DecimalFormat;

@Repository("NPADetailsDao")
public class NPADetailsDoaImpl implements NPADetailsDao {

	@Autowired
	SessionFactory sessionFactory;

	// ArrayList<NPADetailsBean> npaDetailList = null;
	public String getMemberId(String userId) {

		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		String memberId = null;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();
		try {

			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FuncNBFCGetMemberId(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, userId);
			getReceivedPaymentsStmt
					.registerOutParameter(3, OracleTypes.VARCHAR);
			getReceivedPaymentsStmt.registerOutParameter(4, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(4);
				// System.out.println("Error:" + error);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				memberId = getReceivedPaymentsStmt.getString(3);
				System.out.println("memberId :" + memberId);
				exception = getReceivedPaymentsStmt.getString(4);

			}

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

		// return receivedPayments;

		return memberId;
	}
	public NPADetailsBean getCGPANDetails(String cgpan, String usrId) {
		///The below one line of code Added by Parmanand on 17-09-2019 
		DecimalFormat decimalFormat=new DecimalFormat("0.00");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FuncNBFCGetNPAlistforMark(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
			getReceivedPaymentsStmt.setString(3, usrId);
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

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(4);
				exception = getReceivedPaymentsStmt.getString(5);

				Date sansanDate = null;
				Date gurStartDate = null;
				String intrestRate="";
				Date disbusDate=null;
				Double osAmt=0.00;
				String tolGuarAmtStrVal="";
				String latestOSGuarAmtStrVal="";
				Double sanctionAmt=0.00;
				
				
				int dateCount=0;
				/*
				 * String paymentId = ""; Date instrumentDt = null; String
				 * instrumentNo = ""; int payAmount = 0; int noOfRecords = 0;
				 * int allocatedAmt = 0; int cases = 0; int inwardAmount = 0;
				 */
				npaBean = new NPADetailsBean();
				if(resultSet!=null){
				while (resultSet.next()) {
					flag = 1;
					dateCount=resultSet.getInt(5);
					if(dateCount<90){
						intrestRate=resultSet.getString(1);
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						disbusDate = resultSet.getDate(2);
						sansanDate = resultSet.getDate(3);
						gurStartDate = resultSet.getDate(4);
						osAmt=resultSet.getDouble(6);
						sanctionAmt=resultSet.getDouble(7);
						npaBean.setGuarStartDt1(dateFormat.format(gurStartDate));
						npaBean.setSanctionDt1(dateFormat.format(sansanDate));
						npaBean.setFirstDisbDt1(dateFormat.format(disbusDate));
						npaBean.setIntrestRate(Double.parseDouble(intrestRate));
						npaBean.setTolGuarAmtBD(new BigDecimal((resultSet.getDouble(6))));
						npaBean.setLatestOSGuarAmtBD(new BigDecimal((resultSet.getDouble(7))));
						npaBean.setDayCount(0);//not apple
						
					}
					else{
						intrestRate=resultSet.getString(1);
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						disbusDate = resultSet.getDate(2);
						sansanDate = resultSet.getDate(3);
						gurStartDate = resultSet.getDate(4);
						osAmt=resultSet.getDouble(6);
						sanctionAmt=resultSet.getDouble(7);
						npaBean.setGuarStartDt1(dateFormat.format(gurStartDate));
						npaBean.setSanctionDt1(dateFormat.format(sansanDate));
						npaBean.setFirstDisbDt1(dateFormat.format(disbusDate));
						npaBean.setIntrestRate(Double.parseDouble(intrestRate));
						npaBean.setTolGuarAmtBD(new BigDecimal((resultSet.getDouble(6))));
						npaBean.setLatestOSGuarAmtBD(new BigDecimal((resultSet.getDouble(7))));
						npaBean.setDayCount(1);//yes
					}
					
				}
				// na.setChcktbl(receivedPayments);
				}else{
				npaBean.setDayCount(2);	
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

		return npaBean;
	}

	public int saveNPADetails(NPAMarkBean npaMarkBean,
			NPATCDetailsBean npaTCDetailsBean,
			NPAWCDetailsBean npaWCDetailsBean, String usrId) {

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
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn.prepareCall("{?=call FUNCNBFCNPASaving(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, "Cgbid");
			if (npaMarkBean!=null && npaMarkBean.getNpaDt()!="") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(npaMarkBean.getNpaDt().toString());
				java.sql.Date sqlNpaDate = new java.sql.Date(parsednpadate.getTime());
				getReceivedPaymentsStmt.setDate(3, sqlNpaDate);
			} else {
				getReceivedPaymentsStmt.setDate(3, null);
			}

			getReceivedPaymentsStmt.setString(4, npaMarkBean.getIsAsPerRBI());
			getReceivedPaymentsStmt.setString(5, npaMarkBean.getNpaConfirm());
			getReceivedPaymentsStmt.setString(6, npaMarkBean.getNpaReason());
			getReceivedPaymentsStmt.setString(7, npaMarkBean.getEffortsTaken());
			getReceivedPaymentsStmt.setString(8,npaMarkBean.getIsAcctReconstructed());
			getReceivedPaymentsStmt.setString(9, npaMarkBean.getSubsidyFlag());
			getReceivedPaymentsStmt.setString(10,npaMarkBean.getIsSubsidyRcvd());
			getReceivedPaymentsStmt.setString(11,npaMarkBean.getIsSubsidyAdjusted());
			getReceivedPaymentsStmt.setDouble(12,npaMarkBean.getSubsidyLastRcvdAmt());

			/*
			 * 
			 * Commented By Parmanand
			 if (npaMarkBean!=null && npaMarkBean.getSubsidyLastRcvdDt() !="") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(npaMarkBean.getSubsidyLastRcvdDt().toString());
				java.sql.Date SubsidyLRcvdDt = new java.sql.Date(parsednpadate.getTime());
				getReceivedPaymentsStmt.setDate(13, SubsidyLRcvdDt);
			} else {
				getReceivedPaymentsStmt.setDate(13, null);
			}*/
			getReceivedPaymentsStmt.setDate(13,null);
			
			if (npaMarkBean!=null && npaMarkBean.getLasInspDt() != "") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				//PANDIT
				Date parsednpadate = format.parse(npaMarkBean.getLasInspDt().toString());
				java.sql.Date LasInspDt = new java.sql.Date(parsednpadate.getTime());
				getReceivedPaymentsStmt.setDate(14, LasInspDt);
			} else {
				getReceivedPaymentsStmt.setDate(14, null);
			}

			getReceivedPaymentsStmt.setString(15, npaMarkBean.getCGPAN());
			getReceivedPaymentsStmt.setString(16, npaMarkBean.getLoanType());
			getReceivedPaymentsStmt.setString(17, usrId);
			getReceivedPaymentsStmt.setDouble(18, npaMarkBean.getLandValue());
			getReceivedPaymentsStmt.setDouble(19,
					npaMarkBean.getBuildingValue());
			getReceivedPaymentsStmt.setDouble(20, npaMarkBean.getPlanetValue());
			getReceivedPaymentsStmt.setDouble(21,
					npaMarkBean.getOtherAssetValue());
			getReceivedPaymentsStmt.setDouble(22, npaMarkBean.getOthersValue());
			getReceivedPaymentsStmt.setDouble(23,
					npaMarkBean.getNetworthOfPromotor());
			getReceivedPaymentsStmt.setDouble(24,
					npaMarkBean.getTotalSecuritydetails());
			getReceivedPaymentsStmt.setDouble(25, npaMarkBean.getLandValue1());
			getReceivedPaymentsStmt.setDouble(26,
					npaMarkBean.getBuildingValue1());
			getReceivedPaymentsStmt
					.setDouble(27, npaMarkBean.getPlanetValue1());
			getReceivedPaymentsStmt.setDouble(28,
					npaMarkBean.getOtherAssetValue1());
			getReceivedPaymentsStmt
					.setDouble(29, npaMarkBean.getOthersValue1());
			getReceivedPaymentsStmt.setDouble(30,
					npaMarkBean.getNetworthOfPromotor1());
			getReceivedPaymentsStmt.setDouble(31,
					npaMarkBean.getTotalSecuritydetails1());
			getReceivedPaymentsStmt.setString(32,
					npaMarkBean.getReductionReason1());
			if (npaMarkBean!=null && npaMarkBean.getFirstDisbDt1() !="") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date FirstDisbDt1 = format.parse(npaMarkBean.getFirstDisbDt1()
						.toString());
				java.sql.Date FirstDisbDt = new java.sql.Date(
						FirstDisbDt1.getTime());
				getReceivedPaymentsStmt.setDate(33, FirstDisbDt);
			} else {
				getReceivedPaymentsStmt.setDate(33, null);
			}
			getReceivedPaymentsStmt.setDate(34,null);
			/*
			 *Commented By Parmanand 
			 * if (npaMarkBean!=null && npaMarkBean.getLastDisbDt1()!="") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsegLastDisbDt1 = format.parse(npaMarkBean
						.getLastDisbDt1().toString());
				java.sql.Date LastDisbDt1 = new java.sql.Date(
						parsegLastDisbDt1.getTime());
				getReceivedPaymentsStmt.setDate(34, LastDisbDt1);
			} else {
				getReceivedPaymentsStmt.setDate(34, null);
			}*/
			getReceivedPaymentsStmt.setDate(35,null);
		/* Commented By Parmanand
			if (npaMarkBean!=null && npaMarkBean.getLastDisbDt1()!="") {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date FirstInstDt1 = format.parse(npaMarkBean.getFirstInstDt1()
						.toString());
				java.sql.Date FirstInstDt = new java.sql.Date(
						FirstInstDt1.getTime());
				getReceivedPaymentsStmt.setDate(35, FirstInstDt);
			} else {
				getReceivedPaymentsStmt.setDate(35, null);
			}*/

			// getReceivedPaymentsStmt.setString(33,
			// npaMarkBean.getFirstDisbDt1());
			// getReceivedPaymentsStmt.setString(34,
			// npaMarkBean.getLastDisbDt1());
			// getReceivedPaymentsStmt.setString(35,
			// npaMarkBean.getFirstInstDt1());
			getReceivedPaymentsStmt.setDouble(36,
					npaMarkBean.getTotalDisbAmt1());
			getReceivedPaymentsStmt.setDouble(37,
					npaMarkBean.getRepayPrincipal());
			getReceivedPaymentsStmt.setDouble(38,
					npaMarkBean.getRepayInterest());
			getReceivedPaymentsStmt.setDouble(39,
					npaMarkBean.getOutstandingPrincipal1());
			getReceivedPaymentsStmt.setDouble(40,
					npaMarkBean.getOutstandingInterest1());
			
			
			getReceivedPaymentsStmt.setDouble(41,
					npaMarkBean.getTotalGuaranteeAmt());
			
			getReceivedPaymentsStmt.setDouble(42,
					npaMarkBean.getLatestOsAmt());
			
			getReceivedPaymentsStmt.setDouble(43,
					npaMarkBean.getClaimEligibityAmt());
			getReceivedPaymentsStmt.setDouble(44,npaMarkBean.getCurrentAssetValue());
			getReceivedPaymentsStmt.setDouble(45,npaMarkBean.getCurrentAssetValue1());
			// getReceivedPaymentsStmt.setDouble(41, 0.0);

			getReceivedPaymentsStmt.registerOutParameter(46, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(46);
				System.out.println(error);
				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

			}

			// resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

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

	public List<NPADetailsBean> getNPADetails(String cgpan) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		NPADetailsBean npaDetailsBean = null;
		ArrayList<NPADetailsBean> npadetailsList = new ArrayList<NPADetailsBean>();

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
					npaDetailsBean = new NPADetailsBean();
					npaDetailsBean.setNpaId(resultSet.getString(1));
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

	public List<NPADetailsBean> getNPADetailsForApproval(String useId,
			String status) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NPADetailsBean> npaDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getNPADetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getNPADetailsStatement = conn
					.prepareCall("{?=call FUNGetNPADelForCKApproval(?,?,?,?)}");
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

				String cgpanNum = "";
				String npaId = "";
				Date npaEftDt = null;
				String npaResionTurningNPA = "";
				String npaReturnremark = "";
				String loanType = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						npaDetailsList = new ArrayList();
					}
					// npaId = resultSet.getString(1);
					cgpanNum = resultSet.getString(3);
					npaEftDt = resultSet.getDate(4);
					npaResionTurningNPA = resultSet.getString(10);
					npaReturnremark = resultSet.getString(73);
					loanType = resultSet.getString(38);

					NPADetailsBean na = new NPADetailsBean();
					na.setNpaId(resultSet.getString(1));
					na.setCGPAN(cgpanNum);
					na.setNpaDt(dateFormat.format(npaEftDt));
					na.setNpaReason(npaResionTurningNPA);
					na.setLoanType(loanType);
					na.setRemarks("");

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

	public int updateNPAApproveReject(String cgpanList, String useId,
			String status, String userType,String Remark) {
		int flag = 0;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		Connection con = session.connection();
		System.out.println("cgpanList" + cgpanList);

		try {
			CallableStatement callable = (CallableStatement) con
					.prepareCall("{ call ProNpaMarkAppRej(?,?,?,?,?,?) } ");
			callable.setString(1, useId);
			callable.setString(2, userType);
			callable.setString(3, cgpanList);
			callable.setString(4, status);
			callable.setString(5, Remark);
			callable.registerOutParameter(6, Types.VARCHAR);
			callable.execute();
			String pouterror1 = callable.getString(6);
			if (pouterror1 != null) {
				flag = 1;
				throw new CustomExceptionHandler("Error Occured  :"
						+ pouterror1);

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
				session.close();
				sessionFactory.close();
			} catch (Exception e2) {

			}
		}
		return flag;

	}

	public List<NPADetailsBean> getNPADetailsForCGTMSE(String status,
			String usrId, String cgpan, String sDate, String eDate) {

		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date sDateTest = null;
		Date eDateTest = null;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NPADetailsBean> npaDetailsList = null;

		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getNPADetailsStatement = null;

			ResultSet resultSet = null;
			String exception = "";
			getNPADetailsStatement = conn
					.prepareCall("{?=call FunGetNPADetailsForCgtmse(?,?,?,?,?,?,?)}");
			getNPADetailsStatement.registerOutParameter(1, Types.INTEGER);
			getNPADetailsStatement.setString(2, status);
			getNPADetailsStatement.setString(3, usrId);
			getNPADetailsStatement.setDate(4, (java.sql.Date) sDateTest);
			getNPADetailsStatement.setDate(5, (java.sql.Date) eDateTest);
			getNPADetailsStatement.setString(6, cgpan);
			getNPADetailsStatement.registerOutParameter(7, OracleTypes.CURSOR);
			getNPADetailsStatement.registerOutParameter(8, Types.VARCHAR);
			getNPADetailsStatement.execute();

			int functionReturnValue = getNPADetailsStatement.getInt(1);

			if (functionReturnValue == 1) {

				String error = getNPADetailsStatement.getString(8);
				// System.out.println("Error:" + error);

				getNPADetailsStatement.close();
				getNPADetailsStatement = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getNPADetailsStatement.getObject(7);
				exception = getNPADetailsStatement.getString(8);

				String cgpanNum = "";
				Date npaEftDt = null;
				String npaResionTurningNPA = "";
				String npaEffortStaken = "";
				String loanType = "";

				int noOfRecords = 0;

				// NbfcAppropriationBean na = null;//new
				// NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						npaDetailsList = new ArrayList();
					}

					cgpanNum = resultSet.getString(3);
					npaEftDt = resultSet.getDate(4);
					npaResionTurningNPA = resultSet.getString(10);
					npaEffortStaken = resultSet.getString(29);
					loanType = resultSet.getString(38);

					NPADetailsBean na = new NPADetailsBean();

					na.setCGPAN(cgpanNum);
					na.setNpaDt(dateFormat.format(npaEftDt));
					na.setNpaReason(npaEffortStaken);
					na.setLoanType(loanType);
					na.setEffortsTaken(npaResionTurningNPA);

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

	public NPADetailsBean getModifyNPADetails(String cgpan, String userId) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaDetailsBean = null;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		// ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement stmt = null;

			ResultSet resultSet = null;
			String exception = "";
			stmt = conn
					.prepareCall("{?=call FUNGetNPADetailsForModify(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, cgpan);
			// stmt.setString(3, userId);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();

			int functionReturnValue = stmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = stmt.getString(4);

				stmt.close();
				stmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {
				Date npaDt = null;
				Date fisrtDisDt = null;
				Date lastDisDt = null;
				Date fisrstinstDt = null;
				Date npaCreatedDate = null;
				Date LastInspectionDate = null;
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				resultSet = (ResultSet) stmt.getObject(3);
				exception = stmt.getString(4);

				while (resultSet.next()) {

					result1 = 1;
					npaDetailsBean = new NPADetailsBean();
					npaDetailsBean.setNpaId(resultSet.getString(1));
					npaDetailsBean.setCGPAN(resultSet.getString(3));
					npaDetailsBean.setNpaReason(resultSet.getString(10));
					npaDt = resultSet.getDate(4);
					npaCreatedDate = resultSet.getDate(37);
					npaDetailsBean.setNpaDt(dateFormat.format(npaDt));
					npaDetailsBean.setNpaCreatedDate(dateFormat.format(npaCreatedDate));
					npaDetailsBean.setLoanType(resultSet.getString(38));
					npaDetailsBean.setCGPAN(resultSet.getString(3));
					fisrtDisDt = resultSet.getDate(62);
					fisrstinstDt = resultSet.getDate(64);
					npaDetailsBean.setFirstDisbDt1(dateFormat.format(fisrtDisDt));
					if(fisrstinstDt!=null){
					npaDetailsBean.setFirstInstDt1(dateFormat
							.format(fisrstinstDt));
					}else{
						
						npaDetailsBean.setFirstInstDt1("");	
					}
					npaDetailsBean.setIsAsPerRBI(resultSet.getString(27));
					npaDetailsBean.setLandValue(resultSet.getDouble(47));
					npaDetailsBean.setBuildingValue(resultSet.getDouble(48));
					npaDetailsBean.setOtherAssetValue(resultSet.getDouble(50));
					npaDetailsBean.setPlanetValue(resultSet.getDouble(49));
					npaDetailsBean.setOthersValue(resultSet.getDouble(51));
					npaDetailsBean.setNetworthOfPromotor(resultSet.getDouble(52));
					npaDetailsBean.setTotalSecuritydetails(resultSet.getDouble(53));
					npaDetailsBean.setLandValue1(resultSet.getDouble(54));
					npaDetailsBean.setBuildingValue1(resultSet.getDouble(55));
					npaDetailsBean.setPlanetValue1(resultSet.getDouble(56));
					npaDetailsBean.setOtherAssetValue1(resultSet.getDouble(57));
					npaDetailsBean.setOthersValue1(resultSet.getDouble(58));
					npaDetailsBean.setNetworthOfPromotor1(resultSet.getDouble(59));
					npaDetailsBean.setTotalSecuritydetails1(resultSet.getDouble(60));
					npaDetailsBean.setReductionReason1(resultSet.getString(61));	
					LastInspectionDate = resultSet.getDate(36);
					if(LastInspectionDate!=null){
				
					System.out.println("LDate--------" + LastInspectionDate);
					System.out.println("LDate1--------"
							+ dateFormat.format(LastInspectionDate));
					npaDetailsBean.setLasInspDt(dateFormat
							.format(LastInspectionDate));
					}else{
						npaDetailsBean.setLasInspDt("");	
					}
					npaDetailsBean.setTotalDisbAmt1(resultSet.getDouble(65));
					npaDetailsBean.setRepayPrincipal(resultSet.getDouble(66));
					npaDetailsBean.setRepayInterest(resultSet.getDouble(67));
					npaDetailsBean.setOutstandingPrincipal1(resultSet
							.getDouble(68));
					npaDetailsBean.setOutstandingInterest1(resultSet
							.getDouble(69));
					npaDetailsBean.setTotalGuaranteeAmt(resultSet
							.getDouble(70));
					npaDetailsBean.setLatestOsAmt(resultSet
							.getDouble(71));
					npaDetailsBean.setClaimEligibityAmt(resultSet
							.getDouble(72));
					npaDetailsBean.setCurrentAssetValue(resultSet
							.getDouble(74));
					npaDetailsBean.setCurrentAssetValue1(resultSet
							.getDouble(75));

				}

			}
			resultSet.close();
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

	public int updateNPADetails(NPADetailsBean bean, String cgpan,String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		NPADetailsBean npaBean;
		int flag = 0;
		int result1 = 0;
		Double total = 0.0;
		int functionReturnValue = 0;
		System.out.println("Saurav");
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNCNBFC_NPA_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			// getReceivedPaymentsStmt.setInt(2, 1);
			getReceivedPaymentsStmt.setString(2, "Cgbid");
			if (bean.getNpaDt() != null) {
				// sqlDate = new java.sql.Date(utilDateNpa.getTime());

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getNpaDt()
						.toString());
				java.sql.Date sqlNpaDate = new java.sql.Date(
						parsednpadate.getTime());
				getReceivedPaymentsStmt.setDate(3, sqlNpaDate);

			} else {
				getReceivedPaymentsStmt.setDate(3, null);
			}

			getReceivedPaymentsStmt.setString(4, bean.getIsAsPerRBI());
			getReceivedPaymentsStmt.setString(5, bean.getNpaReason());
			if (bean.getLasInspDt() != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsednpadate = format.parse(bean.getLasInspDt()
						.toString());
				java.sql.Date LasInspDt = new java.sql.Date(
						parsednpadate.getTime());
				getReceivedPaymentsStmt.setDate(6, LasInspDt);
			} else {
				getReceivedPaymentsStmt.setDate(6, null);
			}

			getReceivedPaymentsStmt.setString(7, bean.getCGPAN());
			getReceivedPaymentsStmt.setDouble(8, bean.getLandValue());
			getReceivedPaymentsStmt.setDouble(9,bean.getBuildingValue());
			getReceivedPaymentsStmt.setDouble(10, bean.getPlanetValue());
			getReceivedPaymentsStmt.setDouble(11,bean.getOtherAssetValue());
			getReceivedPaymentsStmt.setDouble(12, bean.getOthersValue());
			getReceivedPaymentsStmt.setDouble(13,bean.getNetworthOfPromotor());
			getReceivedPaymentsStmt.setDouble(14,bean.getTotalSecuritydetails());
			getReceivedPaymentsStmt.setDouble(15, bean.getLandValue1());
			getReceivedPaymentsStmt.setDouble(16,bean.getBuildingValue1());
			getReceivedPaymentsStmt.setDouble(17, bean.getPlanetValue1());
			getReceivedPaymentsStmt.setDouble(18,bean.getOtherAssetValue1());
			getReceivedPaymentsStmt.setDouble(19, bean.getOthersValue1());
			getReceivedPaymentsStmt.setDouble(20,bean.getNetworthOfPromotor1());
			getReceivedPaymentsStmt.setDouble(21,bean.getTotalSecuritydetails1());
			getReceivedPaymentsStmt.setString(22,bean.getReductionReason1());
			if (!bean.getFirstInstDt1().isEmpty()) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date FirstInstDt1 = format.parse(bean.getFirstInstDt1()
						.toString());
				java.sql.Date FirstInstDt = new java.sql.Date(
						FirstInstDt1.getTime());
				getReceivedPaymentsStmt.setDate(23, FirstInstDt);
			} else {
				getReceivedPaymentsStmt.setDate(23, null);
			}
			getReceivedPaymentsStmt.setDouble(24,bean.getTotalDisbAmt1());
			getReceivedPaymentsStmt.setDouble(25,bean.getRepayPrincipal());
			getReceivedPaymentsStmt.setDouble(26,bean.getRepayInterest());
			getReceivedPaymentsStmt.setDouble(27,bean.getOutstandingPrincipal1());
			getReceivedPaymentsStmt.setDouble(28,bean.getOutstandingInterest1());
			getReceivedPaymentsStmt.setDouble(29,bean.getTotalGuaranteeAmt());
			getReceivedPaymentsStmt.setDouble(30,bean.getLatestOsAmt());
			getReceivedPaymentsStmt.setDouble(31,bean.getClaimEligibityAmt());
			getReceivedPaymentsStmt.setDouble(32,bean.getCurrentAssetValue());
			getReceivedPaymentsStmt.setDouble(33,bean.getCurrentAssetValue1());
			getReceivedPaymentsStmt.registerOutParameter(34, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(34);
				System.out.println(error);
				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

			}
			
			// resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

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

	public String getCGPAN() {// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		String CGPAN = null;
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;

		Connection conn = session.connection();
		try {

			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUN_GET_CGPAN()}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.VARCHAR);
			// getReceivedPaymentsStmt.setString(2, userId);
			// getReceivedPaymentsStmt
			// .registerOutParameter(3, OracleTypes.VARCHAR);
			// getReceivedPaymentsStmt.registerOutParameter(4, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			// int functionReturnValue = getReceivedPaymentsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			CGPAN = getReceivedPaymentsStmt.getString(1);
			System.out.println("CGPAN :" + CGPAN);
			// exception = getReceivedPaymentsStmt.getString(4);

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

		// return receivedPayments;

		return CGPAN;

	}

	public PortfolioDetailsBean getExposureDetails(String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		String portfolioName = null;
		String ExpId = null;
		String ExpLmt = null;
		String payCap = null;
		String portfolioNum = null;

		ResultSet resultSet1 = null;
		String exception1 = "";

		ArrayList<NbfcAppropriationBean> receivedPayments = null;
		PortfolioDetailsBean portfolioDetailsBean = new PortfolioDetailsBean();
		Connection conn = session.connection();
		try {

			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNGetExposureDetail(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, userId);
			getReceivedPaymentsStmt.registerOutParameter(3, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(4, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(4);
				// System.out.println("Error:" + error);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet1 = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception1 = getReceivedPaymentsStmt.getString(4);
			
				while (resultSet1.next()) {

					portfolioDetailsBean.setExpLmt(resultSet1.getDouble(2));
					portfolioDetailsBean.setGURANTEE_FEE(resultSet1
							.getDouble(5));
					portfolioDetailsBean.setPayCap((long) resultSet1.getDouble(6));
					portfolioDetailsBean.setGURANTEE_COVERAGE(resultSet1
							.getString(7));
					portfolioDetailsBean.setEXP_NO(resultSet1.getString(10));
					portfolioDetailsBean.setUTIL_EXP(resultSet1.getDouble(11));
					portfolioDetailsBean.setPENDING_EXP(resultSet1
							.getDouble(12));
					
					
				}
				
				
			}

			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

			conn.commit();

		} catch (Exception exception) {

			try {
				conn.rollback();
			} catch (SQLException ignore) {
				ignore.printStackTrace();
				throw new CustomExceptionHandler(ignore.getMessage());
			}

			throw new CustomExceptionHandler(exception.getMessage());
		} finally {
			session.close();

		}

		// return receivedPayments;

		return portfolioDetailsBean;
	}

	@Override
	public List<PortfolioBatchBean> getPortfolioName(String userId) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;

		ArrayList<PortfolioBatchBean> protfolioName = new ArrayList<PortfolioBatchBean>();
		String portfolioName = null;
		Connection conn = session.connection();
		PortfolioBatchBean portfolioDetailsBean;
		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNGetPortfolioNameDetails(?,?,?)}");
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
				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception = getReceivedPaymentsStmt.getString(4);

				while (resultSet.next()) {
					portfolioDetailsBean = new PortfolioBatchBean();
					result1 = 1;

					portfolioDetailsBean.setArp_ref_no(resultSet.getString(1));

					protfolioName.add(portfolioDetailsBean);

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
		return protfolioName;
	}

	@Override
	public List<ITPANSearchHistoryBean> getITpanSearchDetails(String itPan) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;

		List<ITPANSearchHistoryBean> itpanSearch = new ArrayList<ITPANSearchHistoryBean>();
		String portfolioName = null;
		Connection conn = session.connection();
		ITPANSearchHistoryBean itpanSearchHistoryBean;
		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FuncNBFCGetITPANHistory(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, itPan);
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
				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception = getReceivedPaymentsStmt.getString(4);

				while (resultSet.next()) {
					itpanSearchHistoryBean = new ITPANSearchHistoryBean();
					result1 = 1;

					itpanSearchHistoryBean.setMemBankName(resultSet
							.getString(1));
					itpanSearchHistoryBean.setSsiUnitName(resultSet
							.getString(2));
					itpanSearchHistoryBean.setSsiCinstititupan(resultSet
							.getString(3));
					itpanSearchHistoryBean.setMliId(resultSet.getString(4));
					itpanSearchHistoryBean.setCgpanNumber(resultSet
							.getString(5));
					itpanSearchHistoryBean.setGuaranteeAmount(resultSet
							.getInt(6));
					itpanSearchHistoryBean.setAppStatus(resultSet.getString(7));
					itpanSearchHistoryBean.setNpaDate(resultSet.getString(8));
					itpanSearchHistoryBean
							.setScheamName(resultSet.getString(9));
					itpanSearch.add(itpanSearchHistoryBean);
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
		return itpanSearch;
	}

	@Override
	public int NPASaveInAuditTable(NPADetailsBean bean, String cgpan, String userId) {
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
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNC_NPA_AUDIT_SAVE(?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, cgpan);
			getReceivedPaymentsStmt.registerOutParameter(3, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();
			functionReturnValue = getReceivedPaymentsStmt.getInt(1);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(3);
				System.out.println(error);
				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

			}

			// resultSet.close();
			getReceivedPaymentsStmt.close();
			getReceivedPaymentsStmt = null;

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
	
	@Override
	
	public List<NPADetailsBean> getNPAReportDetail(String userId, Date toDate,
			Date fromDate, String memberId,String role) {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		ResultSetMetaData resultSetMetaData = null;
		NPADetailsBean npaDetailBean = null;
		List<NPADetailsBean> npaReportDetailList = new ArrayList<NPADetailsBean>();
		try {
			Session session4 = sessionFactory.openSession();
             System.out.println("memberId :"+memberId);
			/* Transaction tn = session4.beginTransaction(); */
			Connection conn = session4.connection();
			CallableStatement cs = conn
					.prepareCall("{? = call NBFC_PACKREPORT.Fun_get_NPA_Data(?,?,?,?,?,?)}");

			// register input parameters
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setDate(2, new java.sql.Date(toDate.getTime()));
			cs.setDate(3, new java.sql.Date(fromDate.getTime()));
			cs.setString(4, memberId);
			cs.setString(5, role);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			cs.registerOutParameter(7, Types.VARCHAR);
			
			cs.execute();
			int result = cs.getInt(1);

			String pouterror = cs.getString(7);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			resultset = (ResultSet) cs.getObject(6);
			while (resultset.next()) {
				npaDetailBean = new NPADetailsBean();

				npaDetailBean.setBankName(resultset.getString("Bank_Name"));
				npaDetailBean.setMLIID(resultset.getString("Member_ID"));
				npaDetailBean.setBorrowerName(resultset.getString("Unit_Name"));
				npaDetailBean.setCGPAN(resultset.getString("CGPAN"));
				npaDetailBean.setStatus(resultset.getString("STATUS"));
				Date npa_date = resultset.getDate("NPA_DATE");
				npaDetailBean.setNpaDt(dateFormat.format(npa_date));
				
				npaDetailBean.setNpaReason(resultset.getString("NPA_Reason"));
				npaDetailBean.setLatestOsAmt(resultset
						.getDouble("OUTSTANDING_AMT"));
				npaDetailBean.setTotalGuaranteeAmt(resultset
						.getDouble("TOTAL_GUARANTEE_AMT"));
				npaDetailBean.setClaimEligibityAmt(resultset
						.getDouble("CLAIM_ELIGABLE_AMT"));
			
				npaDetailBean.setLoanAccountNo(resultset
						.getString("Loan_Account_No"));
				Date expity_date=resultset.getDate("Expiry_Date");
				npaDetailBean.setExpiryDate(dateFormat.format(expity_date));
						

				npaReportDetailList.add(npaDetailBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return npaReportDetailList;

	}
}
