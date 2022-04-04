package com.nbfc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.controller.NBFCController;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.nbfc.model.DisbursementForApprovalModel;
import com.raistudies.domain.CustomExceptionHandler;
@Repository("disbursementCheckerDao")
public class DisbursementCheckerDaoImpl implements DisbursementCheckerDao{
	@Autowired
	private SessionFactory sessionFactory;
	List<DisbursementCheckerApprovalModel> resultList=new ArrayList<DisbursementCheckerApprovalModel>();

	static Logger log = Logger.getLogger(DisbursementCheckerDaoImpl.class.getName());
    
	

	
	public List<DisbursementCheckerApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		log.info("Enter in getDisburseDataForApproval() method in DisbursementCheckerDaoImpl class ");
		log.info("portfolioNo :" + portfolioNo + "loanAccountNumber: "
				+ loanAccountNumber);

		// Query queryObject =
		// sessionFactory.getCurrentSession().createQuery("FROM DisbursementForApprovalModel di WHERE di.whetherDisbursed=:DISBURSEMENT_STATUS and di.status=:status and di.portfolioNo LIKE :PORTFOLIO_NO and di.loanAccountNumber LIKE :LOAN_ACCOUNT_NO");
		Query queryObject = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM DisbursementCheckerApprovalModel di " +
						"WHERE  di.status=:status " +
						"and whetherDisbursed=:DISBURSEMENT_STATUS " +
						"and di.portfolioNo LIKE :PORTFOLIO_NO " +
						"and di.loanAccountNumber LIKE :LOAN_ACCOUNT_NO");

		queryObject.setParameter("PORTFOLIO_NO", "%" + portfolioNo + "%");
		queryObject.setParameter("LOAN_ACCOUNT_NO", "%" + loanAccountNumber
				+ "%");
		queryObject.setParameter("status", "CDE");
		queryObject.setParameter("DISBURSEMENT_STATUS", "N");
		resultList = queryObject.list();
		log.info("Exit from getDisburseDataForApproval() method in DisbursementCheckerDaoImpl class ");

		return resultList;
	}

	
	public int approveDisbursedData(List disbursedData, String userId)  {
		// TODO Auto-generated method stub
		int result = 0;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();

		try {	
		log.info("Enter in updateDisbusedData() method in DisbursementCheckerDaoImpl class ");
		if (disbursedData.size() > 0 && userId!=null) {
			for (int i = 0; i < disbursedData.size(); i++) {
				Query query = sessionFactory
						.getCurrentSession()
						.createQuery(
								"Update DisbursementCheckerApprovalModel " +
								"set status=:statusValue," +
								"disbCheckerDate=:disbCheckerDate," +
								"disbCheckerId=:disbCheckerId," +
								"whetherDisbursed=:DISBURSEMENT_STATUS " +
								"where loanAccountNumber = :LOAN_ACCOUNT_NO");
				System.out.println(query);
				//query.setParameter("statusValue", "CCA");
				query.setParameter("statusValue", "NCA");
				query.setParameter("disbCheckerId", userId);
				query.setParameter("disbCheckerDate", new Date());
				query.setParameter("LOAN_ACCOUNT_NO", disbursedData.get(i));
				query.setParameter("DISBURSEMENT_STATUS", "Y");

				result = query.executeUpdate();

			}
		} 
		tn.commit();
		sessionFactory.close();
		log.info("Exit from updateDisbusedData() method in DisbursementCheckerDaoImpl Java class ");
		} catch (Exception e) {
			tn.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public List<DisbursementCheckerApprovalModel> getPortfolioNoForCheckerApproval() {log.info("Enter in getDisburseDataForApproval() method in DisbursementCheckerDaoImpl class ");
		log.info("Engter in getPortfolioNoForCheckerApproval() method in DisbursementCheckerDaoImpl");
		List<DisbursementCheckerApprovalModel> portfolioNo = new ArrayList<DisbursementCheckerApprovalModel>();

		Query queryObject = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM DisbursementCheckerApprovalModel di " +
						"WHERE  di.status=:status " +
						"and whetherDisbursed=:DISBURSEMENT_STATUS");

		queryObject.setParameter("status", "CDE");
		queryObject.setParameter("portfolioNo", portfolioNo);
		queryObject.setParameter("DISBURSEMENT_STATUS", "N");

		portfolioNo = queryObject.list();
		log.info("Exit from getDisburseDataForApproval() method in DisbursementCheckerDaoImpl class ");

		return portfolioNo;
	}

	
	
	public int rejectDisbursedData(List rejectDataList, String remark, String userId) {
		// TODO Auto-generated method stub
		int result = 0;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		try {			
			log.info("Enter in rejectDisbusedData() method in DisbursementCheckerDaoImpl class ");
			
			if (rejectDataList.size() > 0 && userId!=null && remark!=null) {
				for (int i = 0; i < rejectDataList.size(); i++) {
					Query query = sessionFactory
							.getCurrentSession()
							.createQuery(
									"Update DisbursementCheckerApprovalModel " +
									"set status=:statusValue," +
									"remark=:remark," +
									"disbCheckerDate=:disbCheckerDate," +
									"disbCheckerId=:disbCheckerId," +
									"whetherDisbursed=:DISBURSEMENT_STATUS " +
									"where loanAccountNumber = :LOAN_ACCOUNT_NO");
					System.out.println(query);
					query.setParameter("statusValue", "CDR");
					query.setParameter("remark", remark);
					query.setParameter("LOAN_ACCOUNT_NO", rejectDataList.get(i));
					query.setParameter("disbCheckerId", userId);
					query.setParameter("disbCheckerDate",
							new Date());
					query.setParameter("DISBURSEMENT_STATUS", "N");

					result = query.executeUpdate();
				}
			}else{
				throw new CustomExceptionHandler(
				"Data is not available for rejection");
			}

			tn.commit();
			sessionFactory.close();
			log.info("Exit from rejectDisbusedData() method in DisbursementCheckerDaoImpl Java class ");
			
		} catch (Exception e) {
			tn.rollback();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	

}
