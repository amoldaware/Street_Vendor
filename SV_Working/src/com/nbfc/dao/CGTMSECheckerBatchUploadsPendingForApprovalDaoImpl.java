package com.nbfc.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.helper.CGTMSECheckerBatchUploadsPendingForApprovalHelper;
import com.nbfc.model.CGTMSECheckerBatchUploadsPendingForApproval;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("cgtmseCheckerBatchUploadsPendingForApprovalDao")
public class CGTMSECheckerBatchUploadsPendingForApprovalDaoImpl implements CGTMSECheckerBatchUploadsPendingForApprovalDao {

	@Autowired
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(CGTMSECheckerBatchUploadsPendingForApprovalDaoImpl.class.getName());
	
	public List<Object> getBatchUploadsPendingForApprovalDetails(CGTMSECheckerBatchUploadsPendingForApproval cgtmseCheckerBatchUploadsPendingForApproval) {
		try {
			String CMAStatus = cgtmseCheckerBatchUploadsPendingForApproval.getSTATUS();
			String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.STATUS, UP.PORTFOLIONO,SUM(UP.outstandingAmount),SUM(UP.TOTALSANCTIONEDAMOUNT),COUNT (UP.filePath),psd.portfolioQuarter,max(UP.DATEOFUPLOAD) FROM CGTMSECheckerBatchUploadsPendingForApproval UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.PORTFOLIONO = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId and UP.STATUS = :CMAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.STATUS, UP.PORTFOLIONO,psd.portfolioQuarter order by max(UP.DATEOFUPLOAD) desc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("CMAStatus", CMAStatus);
			List<Object> listCategories3 = query.list();
			return listCategories3;
	
		} catch (Exception e) {
			System.out.println("Exception===" + e);
		}
		return null;
	}


	@Override
	public List<CGTMSECheckerBatchUploadsPendingForApprovalHelper> getUploadedData(String status1,String status2) {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		CGTMSECheckerBatchUploadsPendingForApprovalHelper cgtmseCheckerBatchUploadsPendingForApprovalHelper = null;
		ArrayList<CGTMSECheckerBatchUploadsPendingForApprovalHelper> batchApprovalDataBeanList = new ArrayList<CGTMSECheckerBatchUploadsPendingForApprovalHelper>();
		Connection conn = session.connection();
		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;
			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn.prepareCall("{?=call FUNGetApprovalData(?,?,?,?)}");
			getReceivedPaymentsStmt.registerOutParameter(1, Types.INTEGER);
			getReceivedPaymentsStmt.setString(2, status1);
			getReceivedPaymentsStmt.setString(3, status2);
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
				while (resultSet.next()) {
					result1 = 1;
					cgtmseCheckerBatchUploadsPendingForApprovalHelper = new CGTMSECheckerBatchUploadsPendingForApprovalHelper();
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setUploadedExcelFileId(resultSet.getString(1));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date Uploaddate1=resultSet.getDate(2);
					String UploadDate = df.format(Uploaddate1);
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setExcelUploadedDate(UploadDate);
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setPortFolioName(resultSet.getString(3));
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setMliName(resultSet.getString(4));
					///cgtmseCheckerBatchUploadsPendingForApprovalHelper.setOutstandingAmount(resultSet.getString(5));
					//Modified By Parmanand 16/Jan/2020 Start
					BigDecimal outAmtinBig=new BigDecimal((double) Math.round(resultSet.getDouble(5)));
					BigDecimal finalOutAmtBigRound=outAmtinBig.setScale(2, RoundingMode.HALF_UP);
					String finalOutAmtInBigInString=finalOutAmtBigRound.toPlainString();
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setOutstandingAmount(finalOutAmtInBigInString);
					//Modified By Parmanand 16/Jan/2020 End
					cgtmseCheckerBatchUploadsPendingForApprovalHelper.setTotalNoOfRecordsUploadedInExcelFile(Long.parseLong(resultSet.getString(6)));
					batchApprovalDataBeanList.add(cgtmseCheckerBatchUploadsPendingForApprovalHelper);
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
		return batchApprovalDataBeanList;
	}
}
