package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.NBFCAppropriationModel;
import com.nbfc.model.NBFCAppropriationModelUpdate;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("nbfcAppropriationDao")
public class NbfcAppropriationDaoImpl implements NbfcAppropriationDao {

	@Autowired
	SessionFactory sessionFactory;
	public static final String SMILE_DELIMITER = String.copyValueOf(new char[]{ 1 });

	
	public List<NbfcAppropriationBean> getDataForAppropriation(
			Date dateofRealisation) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;
		//ArrayList<NbfcAppropriationBean> chcklist = new ArrayList<NbfcAppropriationBean>();
		Connection conn = session.connection();
		/*
		 * for (DanAllocationForNbfcMakerUsingVWModel dd : allocationList) {
		 * total += Double.parseDouble(dd.getAmount()); }
		 */
		// System.out.println(dd.getDanId()+" "+dd.getAmount()+" "+dd.getPortfolioRate());
		try { // Double amount=Double.parseDouble(dd.getAmount());
				// CallableStatement getReceivedPaymentsStmt;
			/*
			 * CallableStatement call1; CallableStatement call2;
			 */

			/*
			 * call = (CallableStatement) conn
			 * .prepareCall("{ ? = call FUNCINSERTPAYMENTDETAIL_ONLINE(?,?,?,?) }"
			 * ); call.registerOutParameter(1, Types.INTEGER); // or whatever it
			 * is
			 * 
			 * call.setDouble(2, total); call.setString(3, "Y");
			 * call.registerOutParameter(4, Types.VARCHAR);
			 * call.registerOutParameter(5, Types.VARCHAR); call.execute(); int
			 * result = call.getInt(1); // propagate this back to enclosing //
			 * class //String payid = call.getString(4); String pouterror =
			 * call.getString(5);
			 */

			// --------------------------
			// PaymentList paymentList = null;
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call PackNBFCGetGFListforBatAppr.FuncNBFCGetGFListforBatAppr(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setDate(2, new java.sql.Date(
					dateofRealisation.getTime()));
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

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception = getReceivedPaymentsStmt.getString(4);

				String memberId = "";
				String memberName = "";
				String paymentId = "";
				Date instrumentDt = null;
				String instrumentNo = "";
				int payAmount = 0;
				int noOfRecords = 0;
				int allocatedAmt = 0;
				int cases = 0;
				int inwardAmount = 0;
				//NbfcAppropriationBean na = null;//new NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						receivedPayments = new ArrayList();
					}
					memberId = resultSet.getString(2);
					memberName = resultSet.getString(3);
					paymentId = resultSet.getString(1);
					instrumentNo = resultSet.getString(4);
					instrumentDt = resultSet.getDate(5);
					payAmount = resultSet.getInt(6);
					allocatedAmt = resultSet.getInt(7);
					//cases = resultSet.getInt(8);
					inwardAmount = resultSet.getInt(8);
					
					NbfcAppropriationBean	na = new NbfcAppropriationBean();
					na.setMemberId(memberId);
					na.setMemberName(memberName);
					na.setRpNumber(paymentId);
					na.setAllocatedAmount(allocatedAmt);
					na.setInstrumentAmount(payAmount);
					na.setVirtualAccountNumber(instrumentNo);
					na.setInstrumentDate(instrumentDt);
					na.setInwardAmount(inwardAmount);
					
					//chcklist.add(na);
					//na.setChcktbl(chcklist);
					receivedPayments.add(na);
					
					++noOfRecords;
				}
				//na.setChcktbl(receivedPayments);
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

		
		return receivedPayments; 
	}

	
	 @Transactional
	public int updateAppropriateStatus(List<String> checkedData,
			Date dateOfReconsilation, String userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=	session.beginTransaction();
		if(checkedData.size()>0){
			for(String na:checkedData){

				java.util.Date utilDate = new java.util.Date();
				System.out.println(dateOfReconsilation+"Util date in Java : " + utilDate); // contains only date information without time 
				java.sql.Date sqlDate1 = new java.sql.Date(dateOfReconsilation.getTime()); 
				System.out.println("SQL date in Java : " + sqlDate1);

				Session session3 = sessionFactory.openSession();
				Transaction tn = session3.beginTransaction();

				Connection con = session3.connection();
				try {
					CallableStatement callable = (CallableStatement) con
							.prepareCall("{ call PROC_NBFC_APPROPRIATION(?,?,?,?) } ");
					callable.setString(1, na);
					callable.setString(2, userId);
					System.out.println("sqlDate1----" +sqlDate1);
					callable.setDate(3, sqlDate1);
					callable.registerOutParameter(4, Types.VARCHAR);
					callable.execute();
					String pouterror1 = callable.getString(4);
					if (pouterror1 != null) {
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
						// session.close();
						session3.close();
						sessionFactory.close();
					} catch (Exception e2) {

					}
				}
			
			}
				
				
				
				
				
				
//				NBFCAppropriationModel onlinePayment=new NBFCAppropriationModel();
//				onlinePayment.setAppropriationStatus("A");
//				onlinePayment.setRpNumber(na);
//				session.update(onlinePayment);
				
				/*NBFCAppropriationModelUpdate danInfo=new NBFCAppropriationModelUpdate();
				danInfo.setApprovedBy(userId);
				danInfo.setRpNumber(na);
				danInfo.setGuaranteeStartDate(new java.sql.Date(
						dateOfReconsilation.getTime()));
				danInfo.setDciAppropriationFlag("Y");
				//danInfo.setNbfcAppropriate(onlinePayment);
			
				//Set<NBFCAppropriationModelUpdate> set=onlinePayment.getNbfcAppropriationModelUpdate();
				//set.add(danInfo);
				Set<NBFCAppropriationModelUpdate> danDetails=new HashSet<NBFCAppropriationModelUpdate>();
				danDetails.add(danInfo);
				onlinePayment.setNbfcAppropriationModelUpdate(danDetails);
				
				session.saveOrUpdate(danInfo);*/
				
			}
		return 0;
		
		}
	
	

	
	public List getAppropriatePaymentDetails(String paymentId) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ArrayList paymentDataList=new ArrayList();
		ArrayList<com.nbfc.bean.DemandAdvice> danDetails=new ArrayList<com.nbfc.bean.DemandAdvice>();

		Connection conn = session.connection();
		com.nbfc.bean.PaymentDetails paymentDetails = new com.nbfc.bean.PaymentDetails();

		try { 
			Connection connection = null;
			CallableStatement getInstrumentDetailsStmt = null;
			ResultSet resultSet,resultSet1 = null;
			String modeOfDelivery = "";
			String modeOfPayment = "";
			String instrumentNumber = "";
			Date instrumentDate = null;
			double instrumentAmount = 0;
			String drawnAtBank = "";
			String drawnAtBranch = "";
			String payeeBankOrBranch = "";
			String payableAt = "";
			String collectingBank = "";
			String collectingBranch = "";
			int allocatedAmount = 0;
			String exception = "";
			getInstrumentDetailsStmt = conn
					.prepareCall("{?=call Packgetdansforappropriation.funcGetDansforAppropriation(?,?,?,?)}");
			getInstrumentDetailsStmt.registerOutParameter(1, Types.INTEGER);
			getInstrumentDetailsStmt.setString(2, paymentId);
			//getReceivedPaymentsStmt.setDate(2, new java.sql.DadateofRealisation.getTime()));
			getInstrumentDetailsStmt.registerOutParameter(3, OracleTypes.CURSOR);
			getInstrumentDetailsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getInstrumentDetailsStmt.registerOutParameter(5, Types.VARCHAR);
			
			getInstrumentDetailsStmt.execute();

			int functionReturnValue = getInstrumentDetailsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getInstrumentDetailsStmt.getString(4);
				// System.out.println("Error:" + error);

				getInstrumentDetailsStmt.close();
				getInstrumentDetailsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				
				resultSet = (ResultSet) getInstrumentDetailsStmt.getObject(3);			
				com.nbfc.bean.DemandAdvice demandAdvice=null;
				while (resultSet.next()) {
					demandAdvice = new com.nbfc.bean.DemandAdvice();					
					
					demandAdvice.setDanNo(resultSet.getString(1));				
					demandAdvice.setCgpan(resultSet.getString(2));					
					demandAdvice.setAmountRaised(resultSet.getDouble(3));
					demandAdvice.setPenalty(resultSet.getDouble(4));						
					demandAdvice.setAllocated(resultSet.getString(5));		
					/*if(resultSet.getString(5).equals("Y")){
						demandAdvice.setChcktbl(true);
					}else{
						demandAdvice.setChcktbl(false);
	
					}*/
					String reasons = resultSet.getString(6);				
					demandAdvice.setClaimRevoverAmount(resultSet.getDouble(7));
					/*if (reasons != null) {
						reasons = reasons.substring(
								reasons.indexOf(SMILE_DELIMITER) + 1,
								reasons.lastIndexOf(SMILE_DELIMITER));
					}*/
					demandAdvice.setReason(reasons);			
					
					
					//demandAdvice.setAppropriated(resultSet.getString(8));					
					demandAdvice.setDanType(resultSet.getString(9));				
					demandAdvice.setBankId(resultSet.getString(10));					
					demandAdvice.setZoneId(resultSet.getString(11));				
					demandAdvice.setBranchId(resultSet.getString(12));					
					demandAdvice.setStatus("");	
					demandAdvice.setRpNumber(resultSet.getString(13));
					danDetails.add(demandAdvice);
				}
				if(danDetails.size()!=0){
				paymentDataList.add(danDetails);
				}else{
					throw new CustomExceptionHandler("Data not available for given PayID");
				}
				resultSet.close();
				resultSet=null;
				
				resultSet = (ResultSet) getInstrumentDetailsStmt.getObject(4);			
				if (resultSet.next()) {
					modeOfDelivery = resultSet.getString(1);
					modeOfPayment = resultSet.getString(2);
					instrumentNumber = resultSet.getString(3);
					instrumentDate = resultSet.getDate(4);
					//instrumentFlag=resultSet.getString(5);
					instrumentAmount = resultSet.getDouble(6);
					//payeeBank = resultSet.getDouble(7);
					payableAt = resultSet.getString(8);
					drawnAtBank = resultSet.getString(9);
					drawnAtBranch = resultSet.getString(10);
					collectingBank = resultSet.getString(11);
					collectingBranch = resultSet.getString(12);
					allocatedAmount= resultSet.getInt(13);
					//allocatedAmount+= allocatedAmount + resultSet.getDouble(13);
					System.out.println("amount------"+allocatedAmount);
					Date	paymentDate = resultSet.getDate(14);
					String	payCgtsiAccountNo=resultSet.getString(15);
					
					paymentDetails.setModeOfDelivery(modeOfDelivery);
					paymentDetails.setModeOfPayment(modeOfPayment);
					paymentDetails.setInstrumentNo(instrumentNumber);
					paymentDetails.setInstrumentDate(instrumentDate);
					paymentDetails.setInstrumentAmount(instrumentAmount);
					paymentDetails.setPayableAt(payableAt);
					paymentDetails.setDrawnAtBank(drawnAtBank);
					paymentDetails.setDrawnAtBranch(drawnAtBranch);
					paymentDetails.setCollectingBank(collectingBank);
					paymentDetails.setCollectingBankBranch(collectingBranch);
					paymentDetails.setAllocatedAmount(allocatedAmount);
					paymentDetails.setPaymentDate(paymentDate);
					paymentDetails.setCgtsiAccNumber(payCgtsiAccountNo);
					paymentDataList.add(paymentDetails);
				}
				//na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getInstrumentDetailsStmt.close();
			getInstrumentDetailsStmt = null;

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

		
		return paymentDataList;
	}

	public List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriation(
			Date fromDateHidden1, Date toDateHidden1) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;
		//ArrayList<NbfcAppropriationBean> chcklist = new ArrayList<NbfcAppropriationBean>();
		Connection conn = session.connection();
		/*
		 * for (DanAllocationForNbfcMakerUsingVWModel dd : allocationList) {
		 * total += Double.parseDouble(dd.getAmount()); }
		 */
		// System.out.println(dd.getDanId()+" "+dd.getAmount()+" "+dd.getPortfolioRate());
		try { // Double amount=Double.parseDouble(dd.getAmount());
				// CallableStatement getReceivedPaymentsStmt;
			/*
			 * CallableStatement call1; CallableStatement call2;
			 */

			/*
			 * call = (CallableStatement) conn
			 * .prepareCall("{ ? = call FUNCINSERTPAYMENTDETAIL_ONLINE(?,?,?,?) }"
			 * ); call.registerOutParameter(1, Types.INTEGER); // or whatever it
			 * is
			 * 
			 * call.setDouble(2, total); call.setString(3, "Y");
			 * call.registerOutParameter(4, Types.VARCHAR);
			 * call.registerOutParameter(5, Types.VARCHAR); call.execute(); int
			 * result = call.getInt(1); // propagate this back to enclosing //
			 * class //String payid = call.getString(4); String pouterror =
			 * call.getString(5);
			 */

			// --------------------------
			// PaymentList paymentList = null;
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call PackNBFCGetGFListforBatAppr.FuncNBFCGetListforBatApprDt(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setDate(2, new java.sql.Date(
					fromDateHidden1.getTime()));
			getReceivedPaymentsStmt.setDate(3, new java.sql.Date(
					toDateHidden1.getTime()));
			getReceivedPaymentsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(5, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(5);
				// System.out.println("Error:" + error);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(4);
				exception = getReceivedPaymentsStmt.getString(5);

				String memberId = "";
				String memberName = "";
				String paymentId = "";
				Date instrumentDt = null;
				String instrumentNo = "";
				int payAmount = 0;
				int noOfRecords = 0;
				int allocatedAmt = 0;
				int cases = 0;
				int inwardAmount = 0;
				//NbfcAppropriationBean na = null;//new NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						receivedPayments = new ArrayList();
					}
					memberId = resultSet.getString(1);
					memberName = resultSet.getString(2);
					paymentId = resultSet.getString(3);
					instrumentNo = resultSet.getString(4);
					instrumentDt = resultSet.getDate(5);
					payAmount = resultSet.getInt(6);
					allocatedAmt = resultSet.getInt(7);
					//cases = resultSet.getInt(8);
					inwardAmount = resultSet.getInt(8);
					
					NbfcAppropriationBean	na = new NbfcAppropriationBean();
					na.setMemberId(memberId);
					na.setMemberName(memberName);
					na.setRpNumber(paymentId);
					na.setAllocatedAmount(allocatedAmt);
					na.setInstrumentAmount(payAmount);
					na.setVirtualAccountNumber(instrumentNo);
				//	na.setInstrumentDate(instrumentDt);
					na.setInwardAmount(inwardAmount);
					
					//chcklist.add(na);
					//na.setChcktbl(chcklist);
					receivedPayments.add(na);
					
					++noOfRecords;
				}
				//na.setChcktbl(receivedPayments);
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

		
		return receivedPayments;
	}

	
	public int updateApprocationDate(String pay_id, String user_id,
			Date app_date) {
		int status = 0;
		System.out.println("pay_id :" + pay_id);
		System.out.println("user_id :" + user_id);
		System.out.println("app_date :" + app_date);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
	//	java.util.Date date = sdf.parse(app_date);		
		//java.sql.Date sqlDate = (java.sql.Date) new Date(app_date.getDate());
		
		
		
		java.util.Date utilDate = new java.util.Date();
		System.out.println(app_date+"Util date in Java : " + utilDate); // contains only date information without time 
		java.sql.Date sqlDate1 = new java.sql.Date(app_date.getTime()); 
		System.out.println("SQL date in Java : " + sqlDate1);
	//	System.out.printf("Time : %s:%s:%s",sqlDate1.getHours(),sqlDate1.getMinutes(),sqlDate1.getSeconds()); 


	
//		SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		Date d1 = null;
//		String formattedDate = targetFormat.format(app_date);
//		
//	    System.out.println(formattedDate);
		Session session3 = sessionFactory.openSession();
		Transaction tn = session3.beginTransaction();

		Connection con = session3.connection();
		try {
			CallableStatement callable = (CallableStatement) con
					.prepareCall("{ call PROC_NBFC_APPROPRIATION(?,?,?,?) } ");
			callable.setString(1, pay_id);
			callable.setString(2, user_id);
			System.out.println("sqlDate1----" +sqlDate1);
			callable.setDate(3, sqlDate1);
			callable.registerOutParameter(4, Types.VARCHAR);
			callable.execute();
			String pouterror1 = callable.getString(4);
			if (pouterror1 != null) {
				throw new CustomExceptionHandler("Error Occured  :"+ pouterror1);

			}
			status = 1;
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
		return status;
	}


	@Override
	public List<NbfcAppropriationBean> getDataForAppropriationASF(Date date1) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;
		//ArrayList<NbfcAppropriationBean> chcklist = new ArrayList<NbfcAppropriationBean>();
		Connection conn = session.connection();
		
		try { 
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call PackNBFCGetGFListforBatAppr.FuncNBFCGetGFListforBatApprASF(?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setDate(2, new java.sql.Date(date1.getTime()));
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

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(3);
				exception = getReceivedPaymentsStmt.getString(4);

				String memberId = "";
				String memberName = "";
				String paymentId = "";
				Date instrumentDt = null;
				String instrumentNo = "";
				int payAmount = 0;
				int noOfRecords = 0;
				int allocatedAmt = 0;
				int cases = 0;
				int inwardAmount = 0;
				//NbfcAppropriationBean na = null;//new NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						receivedPayments = new ArrayList();
					}
					memberId = resultSet.getString(1);
					memberName = resultSet.getString(2);
					paymentId = resultSet.getString(3);
					instrumentNo = resultSet.getString(4);
					instrumentDt = resultSet.getDate(5);
					payAmount = resultSet.getInt(6);
					allocatedAmt = resultSet.getInt(7);
					//cases = resultSet.getInt(8);
					inwardAmount = resultSet.getInt(8);
					
					NbfcAppropriationBean	na = new NbfcAppropriationBean();
					na.setMemberId(memberId);
					na.setMemberName(memberName);
					na.setRpNumber(paymentId);
					na.setAllocatedAmount(allocatedAmt);
					na.setInstrumentAmount(payAmount);
					na.setVirtualAccountNumber(instrumentNo);
					na.setInstrumentDate(instrumentDt);
					na.setInwardAmount(inwardAmount);
					
					//chcklist.add(na);
					//na.setChcktbl(chcklist);
					receivedPayments.add(na);
					
					++noOfRecords;
				}
				//na.setChcktbl(receivedPayments);
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

		
		return receivedPayments; 
		
	}


	@Override
	public List<NbfcAppropriationBean> getDataBetweenTwoDateForAppropriationASF(Date fromDateHidden1,
			Date toDateHidden1) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		ArrayList<NbfcAppropriationBean> receivedPayments = null;
		//ArrayList<NbfcAppropriationBean> chcklist = new ArrayList<NbfcAppropriationBean>();
		Connection conn = session.connection();
		
		try { 
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call PackNBFCGetGFListforBatAppr.FuncNBFCGetListforBatApprDtASF(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setDate(2, new java.sql.Date(
					fromDateHidden1.getTime()));
			getReceivedPaymentsStmt.setDate(3, new java.sql.Date(
					toDateHidden1.getTime()));
			getReceivedPaymentsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getReceivedPaymentsStmt.registerOutParameter(5, Types.VARCHAR);
			getReceivedPaymentsStmt.execute();

			int functionReturnValue = getReceivedPaymentsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getReceivedPaymentsStmt.getString(5);
				// System.out.println("Error:" + error);

				getReceivedPaymentsStmt.close();
				getReceivedPaymentsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				resultSet = (ResultSet) getReceivedPaymentsStmt.getObject(4);
				exception = getReceivedPaymentsStmt.getString(5);

				String memberId = "";
				String memberName = "";
				String paymentId = "";
				Date instrumentDt = null;
				String instrumentNo = "";
				int payAmount = 0;
				int noOfRecords = 0;
				int allocatedAmt = 0;
				int cases = 0;
				int inwardAmount = 0;
				//NbfcAppropriationBean na = null;//new NbfcAppropriationBean();
				while (resultSet.next()) {
					if (noOfRecords == 0) {
						receivedPayments = new ArrayList();
					}
					memberId = resultSet.getString(1);
					memberName = resultSet.getString(2);
					paymentId = resultSet.getString(3);
					instrumentNo = resultSet.getString(4);
					instrumentDt = resultSet.getDate(5);
					payAmount = resultSet.getInt(6);
					allocatedAmt = resultSet.getInt(7);
					//cases = resultSet.getInt(8);
					inwardAmount = resultSet.getInt(8);
					
					NbfcAppropriationBean	na = new NbfcAppropriationBean();
					na.setMemberId(memberId);
					na.setMemberName(memberName);
					na.setRpNumber(paymentId);
					na.setAllocatedAmount(allocatedAmt);
					na.setInstrumentAmount(payAmount);
					na.setVirtualAccountNumber(instrumentNo);
				//	na.setInstrumentDate(instrumentDt);
					na.setInwardAmount(inwardAmount);
					
					//chcklist.add(na);
					//na.setChcktbl(chcklist);
					receivedPayments.add(na);
					
					++noOfRecords;
				}
				//na.setChcktbl(receivedPayments);
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

		
		return receivedPayments;
	}


	
	public List getAppropriatePaymentDetailsASF(String paymentId) {
		// TODO Auto-generated method stub
System.out.println("helooo");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		ArrayList paymentDataList=new ArrayList();
		ArrayList<com.nbfc.bean.DemandAdvice> danDetails=new ArrayList<com.nbfc.bean.DemandAdvice>();

		Connection conn = session.connection();
		com.nbfc.bean.PaymentDetails paymentDetails = new com.nbfc.bean.PaymentDetails();

		try { 
			Connection connection = null;
			CallableStatement getInstrumentDetailsStmt = null;
			ResultSet resultSet,resultSet1 = null;
			String modeOfDelivery = "";
			String modeOfPayment = "";
			String instrumentNumber = "";
			Date instrumentDate = null;
			double instrumentAmount = 0;
			String drawnAtBank = "";
			String drawnAtBranch = "";
			String payeeBankOrBranch = "";
			String payableAt = "";
			String collectingBank = "";
			String collectingBranch = "";
			int allocatedAmount = 0;
			String exception = "";
			getInstrumentDetailsStmt = conn
					.prepareCall("{?=call Packgetdansforappropriation.funcGetDansforAppropriationASF(?,?,?,?)}");
			getInstrumentDetailsStmt.registerOutParameter(1, Types.INTEGER);
			getInstrumentDetailsStmt.setString(2, paymentId);
			//getReceivedPaymentsStmt.setDate(2, new java.sql.DadateofRealisation.getTime()));
			getInstrumentDetailsStmt.registerOutParameter(3, OracleTypes.CURSOR);
			getInstrumentDetailsStmt.registerOutParameter(4, OracleTypes.CURSOR);
			getInstrumentDetailsStmt.registerOutParameter(5, Types.VARCHAR);
			
			getInstrumentDetailsStmt.execute();

			int functionReturnValue = getInstrumentDetailsStmt.getInt(1);
			// System.out.println("functionReturnValue:"+functionReturnValue);

			if (functionReturnValue == 1) {

				String error = getInstrumentDetailsStmt.getString(4);
				// System.out.println("Error:" + error);

				getInstrumentDetailsStmt.close();
				getInstrumentDetailsStmt = null;

				conn.rollback();

				throw new CustomExceptionHandler(error);

			} else {

				
				resultSet = (ResultSet) getInstrumentDetailsStmt.getObject(3);			
				com.nbfc.bean.DemandAdvice demandAdvice=null;
				while (resultSet.next()) {
					demandAdvice = new com.nbfc.bean.DemandAdvice();					
					
					demandAdvice.setDanNo(resultSet.getString(1));				
					demandAdvice.setCgpan(resultSet.getString(2));					
					demandAdvice.setAmountRaised(resultSet.getDouble(3));
					demandAdvice.setPenalty(resultSet.getDouble(4));						
					demandAdvice.setAllocated(resultSet.getString(5));		
					/*if(resultSet.getString(5).equals("Y")){
						demandAdvice.setChcktbl(true);
					}else{
						demandAdvice.setChcktbl(false);
	
					}*/
					String reasons = resultSet.getString(6);				
					demandAdvice.setClaimRevoverAmount(resultSet.getDouble(7));
					/*if (reasons != null) {
						reasons = reasons.substring(
								reasons.indexOf(SMILE_DELIMITER) + 1,
								reasons.lastIndexOf(SMILE_DELIMITER));
					}*/
					demandAdvice.setReason(reasons);			
					
					
					//demandAdvice.setAppropriated(resultSet.getString(8));					
					demandAdvice.setDanType(resultSet.getString(9));				
					demandAdvice.setBankId(resultSet.getString(10));					
					demandAdvice.setZoneId(resultSet.getString(11));				
					demandAdvice.setBranchId(resultSet.getString(12));					
					demandAdvice.setStatus("");	
					demandAdvice.setRpNumber(resultSet.getString(13));
					danDetails.add(demandAdvice);
				}
				if(danDetails.size()!=0){
				paymentDataList.add(danDetails);
				}else{
					throw new CustomExceptionHandler("Data not available for given PayID");
				}
				resultSet.close();
				resultSet=null;
				
				resultSet = (ResultSet) getInstrumentDetailsStmt.getObject(4);			
				if (resultSet.next()) {
					modeOfDelivery = resultSet.getString(1);
					modeOfPayment = resultSet.getString(2);
					instrumentNumber = resultSet.getString(3);
					instrumentDate = resultSet.getDate(4);
					//instrumentFlag=resultSet.getString(5);
					instrumentAmount = resultSet.getDouble(6);
					//payeeBank = resultSet.getDouble(7);
					payableAt = resultSet.getString(8);
					drawnAtBank = resultSet.getString(9);
					drawnAtBranch = resultSet.getString(10);
					collectingBank = resultSet.getString(11);
					collectingBranch = resultSet.getString(12);
					allocatedAmount= resultSet.getInt(13);
					//allocatedAmount+= allocatedAmount + resultSet.getDouble(13);
					System.out.println("amount------"+allocatedAmount);
					Date	paymentDate = resultSet.getDate(14);
					String	payCgtsiAccountNo=resultSet.getString(15);
					
					paymentDetails.setModeOfDelivery(modeOfDelivery);
					paymentDetails.setModeOfPayment(modeOfPayment);
					paymentDetails.setInstrumentNo(instrumentNumber);
					paymentDetails.setInstrumentDate(instrumentDate);
					paymentDetails.setInstrumentAmount(instrumentAmount);
					paymentDetails.setPayableAt(payableAt);
					paymentDetails.setDrawnAtBank(drawnAtBank);
					paymentDetails.setDrawnAtBranch(drawnAtBranch);
					paymentDetails.setCollectingBank(collectingBank);
					paymentDetails.setCollectingBankBranch(collectingBranch);
					paymentDetails.setAllocatedAmount(allocatedAmount);
					paymentDetails.setPaymentDate(paymentDate);
					paymentDetails.setCgtsiAccNumber(payCgtsiAccountNo);
					paymentDataList.add(paymentDetails);
				}
				//na.setChcktbl(receivedPayments);
			}

			resultSet.close();
			getInstrumentDetailsStmt.close();
			getInstrumentDetailsStmt = null;

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

		
		return paymentDataList;
	}

}
