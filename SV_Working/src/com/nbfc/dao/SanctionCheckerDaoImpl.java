
package com.nbfc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.controller.NBFCController;
import com.nbfc.model.SanctionCheckerModel;
import com.nbfc.model.SanctionCheckerModelAud;
import com.raistudies.domain.CustomExceptionHandler;
@Repository("sanctionCheckerDao")
public class SanctionCheckerDaoImpl implements SanctionCheckerDao{
	@Autowired
	private SessionFactory sessionFactory;

	static Logger log = Logger.getLogger(SanctionCheckerDaoImpl.class.getName());

	

	
	public List<SanctionCheckerBean> getSanctionCheckerDataForApproval(
			String portfolioNo, String loanAccountNumber) {
		log.info("Enter in getSanctionCheckerDataForApproval() method in SanctionCheckerDaoImpl class ");
		log.info("portfolioNo :" + portfolioNo + "loanAccountNumber: "
				+ loanAccountNumber);
		List<SanctionCheckerBean> resultList=new ArrayList<SanctionCheckerBean>();

		// Query queryObject =
		// sessionFactory.getCurrentSession().createQuery("FROM DisbursementForApprovalModel di WHERE di.whetherDisbursed=:DISBURSEMENT_STATUS and di.status=:status and di.portfolioNo LIKE :PORTFOLIO_NO and di.loanAccountNumber LIKE :LOAN_ACCOUNT_NO");
		try {
			Query queryObject = sessionFactory
					.getCurrentSession()
					.createQuery(
							"FROM SanctionCheckerModel di inner join di.sanctionCheckerModelAud as sanctionAud WHERE  di.status=:status " +
							"and di.portfolioNo LIKE :PORTFOLIO_NO " +
							"and di.loanAccountNo LIKE :LOAN_ACCOUNT_NO");

			queryObject.setParameter("PORTFOLIO_NO", "%" + portfolioNo + "%");
			queryObject.setParameter("LOAN_ACCOUNT_NO", "%" + loanAccountNumber
					+ "%");
			queryObject.setParameter("status", "SDE");
			//queryObject.setParameter("DISBURSEMENT_STATUS", "N");
			List<?> list = queryObject.list();
			
			
			
			//Session session = sessionFactory.openSession();

			//String hql = "from SanctionCheckerModel as sn inner join sn.sanctionCheckerModelAud as sanctionAud";
			//List<?> list = session.createQuery(hql).list();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			for(int i=0; i<list.size(); i++) {
				SanctionCheckerBean cb=new SanctionCheckerBean();
				Object[] row = (Object[]) list.get(i);
				SanctionCheckerModel sanction = (SanctionCheckerModel)row[0];
				SanctionCheckerModelAud audit = (SanctionCheckerModelAud)row[1];
				
				cb.setLoanAccountNumber(sanction.getLoanAccountNo());
				cb.setAmountOfDisbursement(audit.getSanctionedAmount());
				cb.setDateOfDisbursement(df.format(sanction.getFirstDisbursementDate()));
				cb.setModifySanctionAmount(sanction.getSanctionedAmount());
				resultList.add(cb);	
				
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Exit from getDisburseDataForApproval() method in SanctionCheckerDaoImpl class ");

		return resultList;
	}

	
	public int approveDisbursedData(List disbursedData, String userId)  {
		// TODO Auto-generated method stub
		int result = 0;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();

		try {
	
		log.info("Enter in approveDisbursedData() method in SanctionCheckerDaoImpl class ");
		if (disbursedData.size() > 0 && userId!=null) {
			for (int i = 0; i < disbursedData.size(); i++) {
				Query query = sessionFactory
						.getCurrentSession()
						.createQuery(
								"Update SanctionCheckerModel set status=:statusValue,sanCheckerDate=:sanCheckerDate,sanCheckerId=:sanCheckerId where loanAccountNo = :LOAN_ACCOUNT_NO");
				System.out.println(query);
				query.setParameter("statusValue", "CCA");
				query.setParameter("sanCheckerId", userId);
				query.setParameter("sanCheckerDate", new Date());
				query.setParameter("LOAN_ACCOUNT_NO", disbursedData.get(i));

				result = query.executeUpdate();

			}
		}
		tn.commit();

		log.info("Exit from approveDisbursedData() method in SanctionCheckerDaoImpl Java class ");
		} catch (Exception e) {
	        if (tn!=null) tn.rollback();
			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}
		return result;
	}

	
	public List<SanctionCheckerModel> getPortfolioNoForCheckerApproval() {
		log.info("Engter in getPortfolioNoForCheckerApproval() method in SanctionCheckerDaoImpl");
		List<SanctionCheckerModel> portfolioNo = new ArrayList<SanctionCheckerModel>();

		Query queryObject = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM SanctionCheckerModel di WHERE  di.status=:status");

		queryObject.setParameter("status", "CDE");
		//queryObject.setParameter("DISBURSEMENT_STATUS", "N");

		if(queryObject.list()==null){
			try {
				throw new CustomExceptionHandler("Portfolio No is null");
			} catch (CustomExceptionHandler e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			portfolioNo = queryObject.list();

		}
		log.info("Exit from getPortfolioNoForCheckerApproval() method in SanctionCheckerDaoImpl class ");

		return portfolioNo;
	}

	
	
	public int rejectDisbursedData(List rejectDataList, String remark, String userId) {
		// TODO Auto-generated method stub
		int result = 0;
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		try {

			
			log.info("Enter in rejectDisbusedData() method in SanctionCheckerDaoImpl class ");
			
			if (rejectDataList.size() > 0 && userId!=null && remark!=null) {
				for (int i = 0; i < rejectDataList.size(); i++) {
					Query query = sessionFactory
							.getCurrentSession()
							.createQuery("Update SanctionCheckerModel set status=:statusValue,remarks=:remark,sanCheckerDate=:sanCheckerDate,sanCheckerId=:sanCheckerId where loanAccountNo = :LOAN_ACCOUNT_NO");
					query.setParameter("statusValue", "SDR");
					query.setParameter("remark", remark);
					query.setParameter("LOAN_ACCOUNT_NO", rejectDataList.get(i));
					query.setParameter("sanCheckerId", userId);
					query.setParameter("sanCheckerDate",
							new Date());

					result = query.executeUpdate();
				}
			}

			tn.commit();
			log.info("Exit from rejectDisbusedData() method in SanctionCheckerDaoImpl Java class ");
			
		} catch (Exception e) {
			if (tn != null)
				tn.rollback();
			e.printStackTrace();
			log.info("Enter in try catch block in rejectDisbusedData() method in SanctionCheckerDaoImpl Java class ");

		} finally {
			sessionFactory.close();
		}
		return result;
	}


	

}
