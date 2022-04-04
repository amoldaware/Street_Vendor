package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.APIClaimDataBO;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;

@Repository
@Transactional

public class ClaimUploadCgtmseDaoImpl implements ClaimUploadCgtmseDao {
	final static Logger logger = Logger.getLogger(ClaimUploadCgtmseDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	//public List<ClaimUploadCgtmseBO> findAllCGTMSE() throws BusinessException {
	public List<ClaimUploadCgtmseBO> findAllCGTMSE(String apprvStatus) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		ClaimUploadCgtmseBO bo = null;
		List<ClaimUploadCgtmseBO> list = new ArrayList<>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd-MM-yyyy");
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			//callableStmt = conn.prepareCall("{call GET_CGTMSE_DATA(?)}");
			callableStmt = conn.prepareCall("{call GET_CGTMSE_DATA(?,?)}");
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, apprvStatus);
			callableStmt.execute();
			String error = callableStmt.getString(1);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new ClaimUploadCgtmseBO();
				bo.setMliId(rs.getString(1));
				bo.setMliName(rs.getString(2));
				bo.setFinancialYears(rs.getString(3));
				bo.setClaimNumber(rs.getString(4));
				bo.setClaimUploadDt(sdf1.format(sdf.parse(rs.getString(5))));
				bo.setRecordCount(rs.getString(6));
				bo.setUploadedOSAmmount(rs.getString(7));
				bo.setCrystallisedPortfolioAmount(rs.getString(8));
				bo.setEligibleClaimAmount(rs.getString(9));
				bo.setUploadId(rs.getString(10));
				bo.setManagementCertificate(rs.getString(11));
				bo.setRecovery(rs.getString(12));
				bo.setApprovalStatus(rs.getString(13));
				bo.setRemark(rs.getString(14));
				list.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean updateMliCgtmse(ClaimUploadCgtmseBO bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Update_MLI_CGTMSE_DATA(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getApprovalStatus());
			callableStmt.setString(3, bo.getRemark());
			callableStmt.setString(4, bo.getMliId());
			callableStmt.setString(5, bo.getUserId());
			callableStmt.registerOutParameter(6, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(6);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return isSuccess;
	}

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadUploadedFileList(String uploadId, String flag)
			throws BusinessException {
		Session session = null;
		List<ClaimErrorFileExcelDataBO> boList = new ArrayList<>();
		Object obj[][] = null;
		try {
			session = sessionFactory.getCurrentSession();
			//String query = "select UPLOAD_ID,PMS_No,Loan_Account_Number,Date_of_NPA,Outstanding_Amount,PROCESS_FLAG,PROCESS_DESC from Claim_uplaod_failur where UPLOAD_ID='"SARITA06122021
			//String query = "select UPLOAD_ID,PMS_No,Loan_Account_Number,Date_of_NPA,Outstanding_Amount,PROCESS_FLAG,PROCESS_DESC,borrowerName from Claim_uplaod_failur where UPLOAD_ID='"//SARITA19122021
			String query = "select UPLOAD_ID,PMS_No,Loan_Account_Number,Date_of_NPA,Outstanding_Amount,PROCESS_FLAG,PROCESS_DESC,borrowerName,loanterm from claim_uploaded_details where UPLOAD_ID='"
			                + uploadId + "' and PROCESS_FLAG='" + flag + "' ";
			SQLQuery sql = session.createSQLQuery(query);
			List l = sql.list();
			if (l.size() > 0) {
				obj = new Object[l.size()][7];
			}
			Iterator itr = l.iterator();
			while (itr.hasNext()) {
				ClaimErrorFileExcelDataBO bo = new ClaimErrorFileExcelDataBO();
				Object[] o = (Object[]) itr.next();
				bo.setUploadId(String.valueOf(o[0]));
				bo.setPmsNumber(String.valueOf(o[1]));
				bo.setLoanAcountNo(String.valueOf(o[2]));
				bo.setDateOfNpa(String.valueOf(o[3]));
				bo.setOutstandingAmountNpa(String.valueOf(o[4]));
				bo.setProcessingFlag(String.valueOf(o[5]));
				bo.setProcessingDesc(String.valueOf(o[6]));
				bo.setBorrowerName(String.valueOf(o[7]));//Added by Sarita06122021
				bo.setLoanTerm(String.valueOf(o[8]));//Added by Sarita28012022
				boList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return boList;
	}

	/*@Override
	public ClaimUploadHeader downloadFileMgmtCertificate(String mliId, String approvalStatus) throws BusinessException {
		Session session = null;
		ClaimUploadHeader claimHeader = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(ClaimUploadHeader.class);
			cr.add(Restrictions.eq("mliId", mliId));
			cr.add(Restrictions.eq("approvalStatus", approvalStatus)).setMaxResults(1);
			claimHeader = (ClaimUploadHeader) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimHeader;
	}*/
	
	@Override
	public ClaimUploadHeader downloadFileMgmtCertificate(String claimNumber, String approvalStatus) throws BusinessException {
		Session session = null;
		ClaimUploadHeader claimHeader = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(ClaimUploadHeader.class);
			cr.add(Restrictions.eq("claimNumber", claimNumber));
			claimHeader = (ClaimUploadHeader) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimHeader;
	}

	@Override
	public List<ClaimUploadCgtmseBO> getApprovedSubmittedData(String submitStatus) throws BusinessException 
	{
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		ClaimUploadCgtmseBO bo = null;
		List<ClaimUploadCgtmseBO> allApprovedSubmitata = new ArrayList<>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GET_CGTMSE_APPROVED_DATA(?,?)}");
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, submitStatus);
			callableStmt.execute();
			String error = callableStmt.getString(1);
			rs = callableStmt.getResultSet();
			while (rs.next()) 
			{
				bo = new ClaimUploadCgtmseBO();
				bo.setMliId(rs.getString(1));
				bo.setMliName(rs.getString(2));
				bo.setRecordCount(rs.getString(3));
				bo.setQuaterly(rs.getString(4));
				bo.setFinancialYears(rs.getString(5));
				bo.setClaimNumber(rs.getString(6));
				bo.setUploadId(rs.getString(7));
				bo.setClaimSubmitToCkrDt(sdf1.format(sdf.parse(rs.getString(8))));
				bo.setClaimPaymentDt(sdf1.format(sdf.parse(rs.getString(9))));
				allApprovedSubmitata.add(bo);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return allApprovedSubmitata;
	}

	@Override
	public List<APIClaimDataBO> getRequiredDataToSendThroughAPI(String uploadId) throws BusinessException 
	{
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		List<APIClaimDataBO> ClaimAPIData = new ArrayList<>();
		APIClaimDataBO apiData = null;
		try
		{
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetClaim_Settled_Data(?,?)}");
			callableStmt.registerOutParameter(1, Types.VARCHAR);
			callableStmt.setString(2, uploadId);
			callableStmt.execute();
			String error = callableStmt.getString(1);
			rs = callableStmt.getResultSet();
			while (rs.next()) 
			{
				apiData = new APIClaimDataBO();
				apiData.setpMSNumber(rs.getString(1));
				apiData.setBorrowerName(rs.getString(2));
				apiData.setClaimStatus(rs.getString(3));
				apiData.setLoanAccountNumber(rs.getString(4));
				apiData.setDateOfNPA(rs.getString(5));
				apiData.setOutstandingLoanAmount(rs.getString(6));
				apiData.setDateOfClaimSubmission(rs.getString(7));
				apiData.setDateOfClaimSettlement(rs.getString(8));
				apiData.setClaimSettledByCGTMSE(rs.getString(9));
				apiData.setLoanTerm(rs.getString(10));
				ClaimAPIData.add(apiData);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		System.out.println("Data Size is ::::" + ClaimAPIData.size());
		return ClaimAPIData;
	}

	@Override
	public String updateClaimSentData(Map<String, String> apiRtnData) throws BusinessException 
	{
		String updateAPIData = "";
		Session session = null;
		String finQryStr = "" , dtaQry = "";
		try
		{
			session = sessionFactory.getCurrentSession();
			
			for(String pmsNo : apiRtnData.keySet())
			{
				dtaQry = "update claim_uploaded_details set APIDataSubmission = 'SUBMITTED' where PMS_No = '"+pmsNo+"' and loanterm = '"+apiRtnData.get(pmsNo)+"';";	
				sessionFactory.getCurrentSession().createSQLQuery(dtaQry).executeUpdate();
				
			}
			/*int rcdcnt = sql.executeUpdate();
			sessionFactory.getCurrentSession().beginTransaction().commit();
			System.out.println("Final Query is ::::" + finQryStr + "Count is :::" + rcdcnt);*/
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return updateAPIData;
	}

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadUploadedAPIClaimFile(String uploadId, String flag)
			throws BusinessException {
		Session session = null;
		List<ClaimErrorFileExcelDataBO> boList = new ArrayList<>();
		Object obj[][] = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "select UPLOAD_ID,PMS_No,Loan_Account_Number,Date_of_NPA,Outstanding_Amount,PROCESS_FLAG,PROCESS_DESC,borrowerName,loanterm,APIDataSubmission from claim_uploaded_details where UPLOAD_ID='"
			                + uploadId + "' and PROCESS_FLAG='" + flag + "' ";
			SQLQuery sql = session.createSQLQuery(query);
			List l = sql.list();
			if (l.size() > 0) {
				obj = new Object[l.size()][7];
			}
			Iterator itr = l.iterator();
			while (itr.hasNext()) {
				ClaimErrorFileExcelDataBO bo = new ClaimErrorFileExcelDataBO();
				Object[] o = (Object[]) itr.next();
				bo.setUploadId(String.valueOf(o[0]));
				bo.setPmsNumber(String.valueOf(o[1]));
				bo.setLoanAcountNo(String.valueOf(o[2]));
				bo.setDateOfNpa(String.valueOf(o[3]));
				bo.setOutstandingAmountNpa(String.valueOf(o[4]));
				bo.setProcessingFlag(String.valueOf(o[5]));
				bo.setProcessingDesc(String.valueOf(o[6]));
				bo.setBorrowerName(String.valueOf(o[7]));
				bo.setLoanTerm(String.valueOf(o[8]));
				bo.setAPIDataSubmission(String.valueOf(o[9]));
				boList.add(bo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return boList;
	}
	
}
