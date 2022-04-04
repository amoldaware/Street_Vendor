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

import com.nbfc.bean.BatchApprovalDataBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.helper.CGTMSEMakerBatchUploadDetails;
import com.nbfc.model.CGTMSEMakerForBatchApprovalUploadedBatchFile;
import com.raistudies.domain.CustomExceptionHandler;

@Repository("cgtmseMakerForBatchApprovalUploadedBatchFileServiceDao")
public class CGTMSEMakerForBatchApprovalUploadedBatchFileDaoImpl implements CGTMSEMakerForBatchApprovalUploadedBatchFileDao{
	@Autowired
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(CGTMSEMakerForBatchApprovalUploadedBatchFileDaoImpl.class.getName());
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getUploadedBatchFileDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj) {
		log.info("getUploadedBatchFileDetails method called as part of  CGTMSEMakerForBatchApprovalUploadedBatchFileDaoImpl.");
		String NCAStatus="NCA";
		log.info("NCAStatus===="+NCAStatus);
		//String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,UP.dateOfUpload FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId AND UP.status = :NCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter,UP.dateOfUpload";
		//10/07/2018 //String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId AND UP.disbursement_status='Y' AND UP.status = :NCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter";

		//String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId AND UP.disbursement_status='Y' AND UP.status = :NCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter";
		//String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId AND UP.status = :NCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter ";
		//String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.outstandingAmount),SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId  AND UP.status = :NCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter,UP.dateOfUpload order by trunc(UP.dateOfUpload) desc";
		//String hql = "SELECT UP.filePath,MAX(UP.subPortfolioNo),MAX(mi.shortName), COUNT (distinct UP.filePath) ,MAX(UP.status), MAX(UP.portfolioNo),SUM(UP.outstandingAmount),SUM(UP.sanctionAmount),COUNT(UP.filePath),MAX(psd.portfolioQuarter),MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId  AND UP.status = :NCAStatus GROUP BY UP.filePath ,UP.dateOfUpload ORDER BY UP.dateOfUpload DESC";
		
		
		//String hql = "SELECT COUNT(N.fileId), N.fileId,SUM (N.outstandingAmount),MAX( N.dateOfUpload),MAX(M.shortName) from CGTMSEMakerForBatchApprovalUploadedBatchFile n,SanctionDetailModel m where n.status=:NCAStatus and M.MEM_BNK_ID||M.MEM_ZNE_ID||M.MEM_BRN_ID = N.MEM_BNK_ID||N.MEM_ZNE_ID||N.MEM_BRN_ID  group by N.fileId,N.dateOfUpload order by N.dateOfUpload";
		String hql="SELECT i.fileId from CGTMSEMakerForBatchApprovalUploadedBatchFile i  where i.status=:NCAStatus group by i.fileId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("NCAStatus", NCAStatus);
		log.info("Query1=="+query);
		List<Object> listCategories4 = query.list();
		log.info("listCategories4 size===="+listCategories4.size());
		log.info("listCategories4 Data===="+listCategories4);
		/*Iterator<Object> itr1= listCategories4.iterator();
		
		while(itr1.hasNext()){
			System.out.println(" Inside While Loop#############");
			 Object[] obj1 = (Object[]) itr1.next();
			System.out.println("subId==="+obj1[0]);
			System.out.println("Status==="+obj1[1]);
		}*/
		return listCategories4;
		
	}

	public List<Object> getUploadedBatchFileCCRDetails(CGTMSEMakerForBatchApprovalUploadedBatchFile cgtmseMakerForBatchApprovalUploadedBatchFileObj) {
		log.info("getUploadedBatchFileCCRDetails method called as part of  CGTMSEMakerForBatchApprovalUploadedBatchFileDaoImpl.");
		String CCAStatus="CCR";
		log.info("CCAStatus ===="+CCAStatus);
		String hql = "SELECT UP.filePath,UP.subPortfolioNo,MAX (mi.shortName), COUNT (distinct UP.filePath) , UP.status, UP.portfolioNo,SUM(UP.sanctionAmount),COUNT (UP.filePath),psd.portfolioQuarter,MAX(UP.dateOfUpload) FROM CGTMSEMakerForBatchApprovalUploadedBatchFile UP,SanctionDetailsSave pm, SanctionDetailsExposureLimitModel el, SanctionDetailModel mi,SanctionDetailsChild psd WHERE UP.subPortfolioNo=psd.subPortfolioDtlNo AND UP.portfolioNo = pm.portfolio_no AND pm.exposure_id = el.exposureId AND el.memBnkId =mi.memBnkId and el.memZneId =mi.memZneId and el.memBrnId =mi.memBrnId AND UP.status = :CCAStatus GROUP BY UP.filePath, UP.subPortfolioNo, UP.status, UP.portfolioNo,psd.portfolioQuarter";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("CCAStatus", CCAStatus);
		log.info("Query2==="+query);
		List<Object> listCategories5 = query.list();
		log.info("listCategories5 size==="+listCategories5.size());
		log.info("listCategories5 Date==="+listCategories5);
		/*Iterator<Object> itr1= listCategories4.iterator();
		
		while(itr1.hasNext()){
			System.out.println(" Inside While Loop#############");
			 Object[] obj1 = (Object[]) itr1.next();
			System.out.println("subId==="+obj1[0]);
			System.out.println("Status==="+obj1[1]);
		}*/
		return listCategories5;
		
	}

	@Override
	public List<CGTMSEMakerBatchUploadDetails> getUploadedData(String status1,String status2) {

		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		int result1 = 0;
		Double total = 0.0;
		CGTMSEMakerBatchUploadDetails batchApprovalDataBean = null;
		ArrayList<CGTMSEMakerBatchUploadDetails> batchApprovalDataBeanList = new ArrayList<CGTMSEMakerBatchUploadDetails>();

		Connection conn = session.connection();

		try {
			Connection connection = null;
			CallableStatement getReceivedPaymentsStmt = null;

			ResultSet resultSet = null;
			String exception = "";
			getReceivedPaymentsStmt = conn
					.prepareCall("{?=call FUNGetApprovalData(?,?,?,?)}");
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
					batchApprovalDataBean = new CGTMSEMakerBatchUploadDetails();
					batchApprovalDataBean.setFileName(resultSet.getString(1));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date Uploaddate1=resultSet.getDate(2);
					String UploadDate = df.format(Uploaddate1);
					batchApprovalDataBean.setUploadDate(UploadDate);
					batchApprovalDataBean.setPortFolioName(resultSet.getString(3));
					batchApprovalDataBean.setShortName(resultSet.getString(4));
					//Modified By Parmanand 16/Jan/2020 Start
					///batchApprovalDataBean.setOutstandingAmount(resultSet.getString(5));
					BigDecimal outAmtinBig=new BigDecimal((double) Math.round(resultSet.getDouble(5)));
					BigDecimal finalOutAmtBigRound=outAmtinBig.setScale(2, RoundingMode.HALF_UP);
					String finalOutAmtInBigInString=finalOutAmtBigRound.toPlainString();
					batchApprovalDataBean.setOutstandingAmount(finalOutAmtInBigInString);
					//Modified By Parmanand 16/Jan/2020 End
					
					batchApprovalDataBean.setNoOfRecords(Long.parseLong(resultSet.getString(6)));
					batchApprovalDataBeanList.add(batchApprovalDataBean);

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
		return batchApprovalDataBeanList;

	}

	
	

	
}
