package com.nbfc.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPmsDetail;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;
import com.nbfc.model.ClaimUploadSTG;
import com.nbfc.model.ClaimUploadTracker;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Repository
@Transactional
public class ClaimUploadDaoImpl implements ClaimUploadDao {
	final static Logger logger = Logger.getLogger(ClaimUploadDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@Override
	public ClaimUploadDetailsBO findClaimUploadByMemberBankId(String memberBnkId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		ClaimUploadDetailsBO bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetClaim_Lodgement_Details(?,?)}");
			callableStmt.setString(1, memberBnkId);
			callableStmt.registerOutParameter(2, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(2);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new ClaimUploadDetailsBO();
				bo.setFinanceYears(rs.getString(1));
				bo.setTotalExposure(rs.getString(2));
				bo.setMaximumClaimAmount(rs.getString(3));
				bo.setClaimSubmitted(rs.getString(4));
				bo.setRecoveryAmount(rs.getString(5));
				bo.setBalanceClaimAmount(rs.getString(6));
				bo.setActualClaimSetteled(rs.getString(7));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public boolean saveClaimUploadSTG(List<ClaimUploadSTG> boList, ClaimUploadTracker claimTracker)
			throws BusinessException {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.getCurrentSession();
			if (boList != null && !boList.isEmpty()) {
				for (ClaimUploadSTG bo : boList) {
					session.save(bo);
				}
				session.save(claimTracker);
				isSuccess = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LodgeFreshClaimBO findLodgeFreshClaimByMliId(String mliId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		LodgeFreshClaimBO bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetClaim_Upload_Details(?,?)}");
			callableStmt.setString(1, mliId);
			callableStmt.registerOutParameter(2, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(2);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				bo = new LodgeFreshClaimBO();
				bo.setClaimNumber(rs.getString(1));
				bo.setMliName(rs.getString(2));
				bo.setFreshClaimDate(rs.getString(3));
				bo.setTotalRecordCount(rs.getString(4));
				bo.setTotalAmount(rs.getString(5));
				bo.setElgibilityCheck(rs.getString(6));
				bo.setApprovalStatus(rs.getString(7));
				bo.setRemark(rs.getString(8));
				bo.setApprovalCode(rs.getString(9));
				bo.setCurrentQuater(rs.getString("Current_Quarter"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return bo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean saveLodgeFreshClaimData(LodgeFreshClaimBO bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_INSERT_Claim_Header(?,?,?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getClaimNumber());
			callableStmt.setString(3, bo.getFreshClaimDate());
			callableStmt.setString(4, bo.getUserId());
			callableStmt.setString(5, bo.getTotalRecordCount());
			callableStmt.setString(6, bo.getTotalAmount());
			callableStmt.setString(7, bo.getMliId());
			callableStmt.registerOutParameter(8, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(8);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean updateLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.getCurrentSession();

			Criteria cr = session.createCriteria(ClaimUploadHeader.class);
			cr.add(Restrictions.eq("claimNumber", bo.getClaimNumber())).setMaxResults(1);
			ClaimUploadHeader claimHeader = (ClaimUploadHeader) cr.uniqueResult();

			if (claimHeader != null) {
				Date now = new Date();
				claimHeader.setUploadedDate(now);
				claimHeader.setMgmtCertificateName(bo.getMgmtCertificatefileName());
				claimHeader.setApprovalStatus(bo.getApprovalStatus());
				claimHeader.setApprovalRemark(bo.getRemark());
				claimHeader.setApprovedBy(bo.getUserId());
				claimHeader.setApprovedDate(now);
				claimHeader.setMgmtFileDate(bo.getFileData());
				claimHeader.setChecker_Submit_Date(now);
				session.update(claimHeader);
				isSuccess = true;

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean updateHistoryLodgeFreshClaimChecker(LodgeFreshClaimBO bo) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call FUNC_Update_Claim_Header(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, bo.getClaimNumber());
			callableStmt.setString(3, bo.getApprovalStatus());
			callableStmt.setString(4, bo.getRemark());
			callableStmt.setString(5, bo.getUserId());
			callableStmt.registerOutParameter(6, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(6);
			tx.commit();
			isSuccess = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public String findPmsNumberById(String pmsNo) throws BusinessException {
		Session session = null;
		String pmsNumber = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "select AadharNo from  nbfc_street_vendor_master where AadharNo='" + pmsNo + "'";
			SQLQuery sql = session.createSQLQuery(query);
			pmsNumber = (String) sql.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pmsNumber;
	}

	/*
	 * @Override public String findQuarterFileUploadByUserId(String userId,String
	 * quater,String fy) throws BusinessException { Session session = null; String
	 * quarter = null; try { session = sessionFactory.getCurrentSession(); String
	 * query =
	 * "SELECT CASE WHEN MONTH(Created_Date) BETWEEN 1 AND 3 THEN 'Q4' WHEN MONTH(Created_Date) BETWEEN 4 AND 6 THEN 'Q1' WHEN MONTH(Created_Date) BETWEEN 7 AND 9 THEN 'Q2' WHEN MONTH(Created_Date) BETWEEN 10 AND 12 THEN 'Q3' END AS 'Current_Quarter' from  claim_uploaded_details where CREATE_BY= '"
	 * + userId + "' GROUP BY Created_Date HAVING COUNT(1) > 1"; SQLQuery sql =
	 * session.createSQLQuery(query); quarter = (String) sql.uniqueResult(); } catch
	 * (Exception e) { logger.error(e.getMessage()); } return quarter; }
	 */

	@Override
	public String findQuarterFileUploadByUserId(String userId, String quater, String fy) throws BusinessException {
		Session session = null;
		String quarter = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT Quartly from  claim_uploaded_details where CREATE_BY= '" + userId + "' and Quartly='"
					+ quater + "' AND Financial_Year='" + fy + "' GROUP BY Quartly HAVING COUNT(1) > 1";
			SQLQuery sql = session.createSQLQuery(query);
			quarter = (String) sql.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return quarter;
	}

	@Override
	public List<ClaimPmsDetail> findClaimPmsDetailByMemAndClaimId(String memberBnkId, String claimId)
			throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		ClaimPmsDetail claimPmsdt = null;
		List<ClaimPmsDetail> claimPmsDetailList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_PMS_Details(?,?,?)}");
			callableStmt.setString(1, memberBnkId);
			callableStmt.setString(2, claimId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			String error = callableStmt.getString(3);
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				claimPmsdt = new ClaimPmsDetail();
				claimPmsdt.setSno(rs.getInt(1));
				claimPmsdt.setPms(rs.getString(2));
				claimPmsdt.setLoanAccount(rs.getString(3));
				claimPmsdt.setBorrowerName(rs.getString(4));
				claimPmsdt.setSanctionAmt(rs.getDouble(5));
				claimPmsdt.setSanctionDate(rs.getString(6));

				claimPmsdt.setClaimDate(rs.getString(7));
				claimPmsdt.setClaimAmt(rs.getDouble(8));
				claimPmsDetailList.add(claimPmsdt);

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return claimPmsDetailList;
	}

	@Override
	public void getPdfManagementCertificateData(String memberBnkId, String claimId, HttpServletResponse response,
			HttpServletRequest request) throws BusinessException {
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();

		Connection connection = ((SessionImpl) session).connection();
		JasperPrint jasperPrint1 = null;
		if (connection == null) {
			System.out.println("connection is null.........");
		}

		HashMap map = new HashMap();
		map.put("user_id", memberBnkId);
		map.put("claim_id", claimId);

		try {
			String filename = "MnagementCertificate23";
			String reportfileName = "MnagementCertificate" + memberBnkId + ".pdf";
			String tempFolderPath = "/WEB-INF/JReport/" + filename;
			System.out.println("tempFolderPath.............................................." + tempFolderPath);
			File tempFolder = new File(tempFolderPath);
			if (!tempFolder.exists()) {
				tempFolder.mkdirs();
			}
			String jasperFilePath = tempFolderPath + ".jasper";
			System.out.println("jasperFilePath......................" + jasperFilePath);
			File reportFile = new File(jasperFilePath);
			if (!reportFile.exists()) {
			//if (reportFile.exists()) {
				InputStream jRXmlStream = request.getSession().getServletContext()
						.getResourceAsStream("/WEB-INF/JReport/" + filename + ".jrxml");
				JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
				JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
				System.out.println(jasperFilePath);
			}
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			try {
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;filename=" + reportfileName);
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tn.commit();

		}
	}

	@Override
	public boolean findByClaimNumber(String claimNumber) throws BusinessException {
		Session session = null;
		boolean isSuccess = false;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(ClaimUploadHeader.class);
			cr.add(Restrictions.eq("claimNumber", claimNumber)).setMaxResults(1);
			ClaimUploadHeader claimHeader = (ClaimUploadHeader) cr.uniqueResult();
			if (claimHeader != null) {
				isSuccess = true;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public UploadFileSuccessErrorCount findSuccessErrorUploadFile(BigInteger uploadId, String userId) {
		Session session = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "select distinct  UPLOAD_ID, CASE WHEN PROCESS_FLAG ='S' THEN 'Success' ELSE 'Failure' END AS Status, (SELECT COUNT(1) FROM claim_uplaod_failur WHERE PROCESS_FLAG='S' AND  UPLOAD_ID='"
					+ uploadId + "' and  CREATE_BY='" + userId
					+ "') as 'Success_Count',(SELECT COUNT(1) FROM claim_uplaod_failur WHERE PROCESS_FLAG='E' AND UPLOAD_ID='"
					+ uploadId + "' and  CREATE_BY='" + userId
					+ "') AS 'UnSuccess_Count' from claim_uplaod_failur where UPLOAD_ID='" + uploadId
					+ "' and  CREATE_BY='" + userId + "' ";
			SQLQuery sql = session.createSQLQuery(query);
			Object obj[] = (Object[]) sql.setMaxResults(1).uniqueResult();
			if (obj != null && obj.length > 0) {
				bo = new UploadFileSuccessErrorCount();
				bo.setUploadId(String.valueOf(obj[0]));
				bo.setStatus(String.valueOf(obj[1]));
				bo.setSuccessCount(String.valueOf(obj[2]));
				bo.setUnSuccessCount(String.valueOf(obj[3]));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return bo;
	}

	/*
	 * @Override public List<ClaimErrorFileExcelDataBO>
	 * downloadErrorUploadedFileList(String uploadId, String userId) throws
	 * BusinessException { Session session = null; List<ClaimErrorFileExcelDataBO>
	 * boList = new ArrayList<>(); Object obj[][] = null; try { session =
	 * sessionFactory.getCurrentSession(); String query =
	 * "select UPLOAD_ID,PMS_No,Loan_Account_Number,Date_of_NPA,Outstanding_Amount,PROCESS_FLAG,PROCESS_DESC from Claim_uplaod_failur where UPLOAD_ID='"
	 * + uploadId + "' and  CREATE_BY='" + userId + "' and PROCESS_FLAG='E' ";
	 * SQLQuery sql = session.createSQLQuery(query); List l = sql.list(); if
	 * (l.size() > 0) { obj = new Object[l.size()][7]; } Iterator itr =
	 * l.iterator(); while (itr.hasNext()) { ClaimErrorFileExcelDataBO bo = new
	 * ClaimErrorFileExcelDataBO(); Object[] o = (Object[]) itr.next();
	 * bo.setUploadId(String.valueOf(o[0])); bo.setPmsNumber(String.valueOf(o[1]));
	 * bo.setLoanAcountNo(String.valueOf(o[2]));
	 * bo.setDateOfNpa(String.valueOf(o[3]));
	 * bo.setOutstandingAmountNpa(String.valueOf(o[4]));
	 * bo.setProcessingFlag(String.valueOf(o[5]));
	 * bo.setProcessingDesc(String.valueOf(o[6])); boList.add(bo); } } catch
	 * (Exception e) { logger.error(e.getMessage()); } return boList; }
	 */
	@Override
	public String checkDuplicatePmsNoByPmsNo(String pmsNo) throws BusinessException {
		Session session = null;
		String pmsN = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT PMS_No FROM claim_uploaded_details UD WHERE UD.PMS_No = '" + pmsNo
					+ "' GROUP BY PMS_No HAVING COUNT(1) >1";
			SQLQuery sql = session.createSQLQuery(query);
			pmsN = (String) sql.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pmsN;
	}

	@Override
	public List<ClaimUploadDetailsBO> findFinancialYears() throws BusinessException {
		Session session = null;
		List<ClaimUploadDetailsBO> fYList = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT ID,F_Year FROM tbl_financial";
			SQLQuery sql = session.createSQLQuery(query);
			List list = sql.list();

			if (list != null && list.size() > 0) {
				Iterator itr = list.iterator();
				if (itr.hasNext()) {
					Object obj[] = (Object[]) itr.next();
					ClaimUploadDetailsBO bo = new ClaimUploadDetailsBO();
					bo.setId(Integer.valueOf(String.valueOf(obj[0])));
					bo.setFinanceYears(String.valueOf(obj[1]));
					fYList.add(bo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return fYList;
	}

	@Override
	public String findApprovalStatusCheckerByMliIdAndQuaterFy(String mliId, String quater, String financialYear)
			throws BusinessException {

		Session session = null;
		String approveStatus = null;

		try {
			session = sessionFactory.getCurrentSession();
			String query = "select Approval_Status from claim_upload_header where MLI_ID= '" + mliId + "' and Quartly='"
					+ quater + "' AND Financial_Year='" + financialYear + "' ";
			SQLQuery sql = session.createSQLQuery(query);
			approveStatus = (String) sql.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return approveStatus;

	}

	@Override
	public String findCurrentQuarter() throws BusinessException {
		Session session = null;
		String quarter = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT CASE WHEN MONTH(NOW()) BETWEEN 1 AND 3 THEN 'Q4' WHEN MONTH(NOW()) BETWEEN 4 AND 6 THEN 'Q1' WHEN MONTH(NOW()) BETWEEN 7 AND 9 THEN 'Q2' WHEN MONTH(NOW()) BETWEEN 10 AND 12 THEN 'Q3' END AS 'Current_Quarter' ";
			SQLQuery sql = session.createSQLQuery(query);
			quarter = (String) sql.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return quarter;
	}

	@Override
	public ClaimUploadTracker getJobFileProccessStatus(String quater, String fy, String MliId)
			throws BusinessException {
		Session session = null;
		ClaimUploadTracker claimTracker = null;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(ClaimUploadTracker.class);
			cr.add(Restrictions.eq("quater", quater));
			cr.add(Restrictions.eq("financialYear", fy));
			cr.add(Restrictions.eq("member_id", MliId));
			cr.setMaxResults(1);
			claimTracker = (ClaimUploadTracker) cr.uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return claimTracker;
	}

	@Override
	public String findLoanAcNumberByLoanNo(String loanNo) throws BusinessException {
		Session session = null;
		String pmsNumber = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "select Loan_Account_No from  nbfc_street_vendor_master where Loan_Account_No='" + loanNo
					+ "'";
			SQLQuery sql = session.createSQLQuery(query);
			pmsNumber = (String) sql.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pmsNumber;
	}

	@Override
	public UploadFileSuccessErrorCount findProgressStatusFile(String userId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Uploaded_Progress_Data(?)}");
			callableStmt.setString(1, userId);
			callableStmt.execute();
			rs = callableStmt.getResultSet();
			if (rs.next()) {
				bo = new UploadFileSuccessErrorCount();
				bo.setUploadId(rs.getString(1));
				bo.setStatus(rs.getString(2));
				bo.setCout(rs.getString(3));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return bo;
	}

	/*
	 * @Override public UploadFileSuccessErrorCount
	 * findUploadedRecordStatusBar(String userId) throws BusinessException { Session
	 * session = null; Connection conn = null; CallableStatement callableStmt =
	 * null; ResultSet rs = null; UploadFileSuccessErrorCount bo = null; try {
	 * session = sessionFactory.openSession(); conn = session.connection();
	 * callableStmt = conn.prepareCall("{call Get_Uploaded_Record_Status(?)}");
	 * callableStmt.setString(1, userId); callableStmt.execute(); rs =
	 * callableStmt.getResultSet(); if (rs.next()) { bo = new
	 * UploadFileSuccessErrorCount(); bo.setUploadId(rs.getString(1));
	 * bo.setSuccessCount(rs.getString(2)); bo.setUnSuccessCount(rs.getString(3));
	 * bo.setStatus(rs.getString(4)); bo.setCout(rs.getString(5)); }
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage()); } return bo; }
	 */

	@Override
	public UploadFileSuccessErrorCount findUploadedRecordStatusBar(String userId, String mliId)
			throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		UploadFileSuccessErrorCount bo = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_Uploaded_Record_Status(?,?)}");
			callableStmt.setString(1, userId);
			callableStmt.setString(2, mliId);
			callableStmt.execute();
			rs = callableStmt.getResultSet();
			if (rs.next()) {
				bo = new UploadFileSuccessErrorCount();
				bo.setUploadId(rs.getString(1));
				bo.setSuccessCount(rs.getString(2));
				bo.setUnSuccessCount(rs.getString(3));
				bo.setStatus(rs.getString(4));
				bo.setCout(rs.getString(5));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public String fingFileProcessStatusByUserId(String userId) throws BusinessException {
		Session session = null;
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		String recordCount = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call Get_MLI_Record_count(?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, userId);
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.execute();
			recordCount = callableStmt.getString(1);
			String error = callableStmt.getString(3);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return recordCount;
	}

	@Override
	public boolean deleteFailureDataByUserIdAndQuater(String userId, String quater) throws BusinessException {
		Session session = null;
		boolean isSuccess = false;
		try {

			session = sessionFactory.getCurrentSession();
			String query = "delete from claim_uploaded_details where CREATE_BY='" + userId + "' and Quartly='" + quater
					+ "' ";
			SQLQuery sql = session.createSQLQuery(query);
			int executeUpdate = sql.executeUpdate();
			if (executeUpdate > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public List<ClaimErrorFileExcelDataBO> downloadErrorUploadedFileList(String uploadId, String userId, String mliId,
			String flag) throws BusinessException {
		Session session = null;
		List<ClaimErrorFileExcelDataBO> boList = new ArrayList<>();
		Connection conn = null;
		CallableStatement callableStmt = null;
		ResultSet rs = null;
		try {
			session = sessionFactory.openSession();
			conn = session.connection();
			callableStmt = conn.prepareCall("{call GetUploadedFile_data(?,?,?,?,?,?)}");
			callableStmt.registerOutParameter(1, Types.INTEGER);
			callableStmt.setString(2, uploadId);
			callableStmt.setString(3, userId);
			callableStmt.setString(4, mliId);
			callableStmt.setString(5, flag);
			callableStmt.registerOutParameter(6, Types.VARCHAR);

			callableStmt.execute();
			rs = callableStmt.getResultSet();
			while (rs.next()) {
				ClaimErrorFileExcelDataBO bo = new ClaimErrorFileExcelDataBO();
				bo.setUploadId(rs.getString(1));
				bo.setPmsNumber(rs.getString(2));
				bo.setLoanAcountNo(rs.getString(3));
				bo.setDateOfNpa(rs.getString(4));
				bo.setOutstandingAmountNpa(rs.getString(5));
				bo.setProcessingFlag(rs.getString(6));
				bo.setProcessingDesc(rs.getString(7));
				bo.setBorrowerName(rs.getString(8));
				//Added by Sarita 27012022 [START]
				bo.setLoanTerm(rs.getString(9));
				//Added by Sarita 27012022 [END]
				boList.add(bo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return boList;
	}

	@Override
	public String findMakerByMliIdandStatus(String status, String mliId) throws BusinessException {
		Session session = null;
		String makerName = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT uploaded_by  FROM claim_upload_tracker WHERE STATUS='" + status + "' AND member_id='"
					+ mliId + "' ORDER BY uploaded_date DESC LIMIT 1";
			SQLQuery sql = session.createSQLQuery(query);
			makerName = (String) sql.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return makerName;
	}

}
