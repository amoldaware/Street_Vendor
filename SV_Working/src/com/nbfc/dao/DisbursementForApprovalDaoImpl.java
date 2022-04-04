package com.nbfc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.bcel.generic.NEW;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.model.DisbursementForApprovalModel;

@Repository("disbursementForApprovalDao")
public class DisbursementForApprovalDaoImpl implements DisbursementForApprovalDao{
@Autowired
  private SessionFactory sessionFactory;
	List<DisbursementForApprovalModel> resultList=new ArrayList<DisbursementForApprovalModel>();
	static Logger log = Logger.getLogger(CGTMSECheckerForBatchApprovalAndRejectionDaoImpl.class.getName());

	/*
	public List<DisbursementForApprovalModel> getPortfolioNumber() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(DisbursementForApprovalModel.class).list();
	}*/

	
	public List<DisbursementForApprovalModel> getDisburseDataForApproval(
			String portfolioNo, String loanAccountNumber) {			
		log.info("Enter in getDisburseDataForApproval() method in DisbursementForApprovalDaoImpl class ");
		log.info("portfolioNo :"+portfolioNo+"loanAccountNumber: "+loanAccountNumber);

		Query queryObject = sessionFactory.getCurrentSession().createQuery("FROM DisbursementForApprovalModel di WHERE di.whetherDisbursed=:DISBURSEMENT_STATUS and di.status=:status and di.portfolioNo LIKE :PORTFOLIO_NO and di.loanAccountNumber LIKE :LOAN_ACCOUNT_NO");
		queryObject.setParameter("PORTFOLIO_NO", "%" + portfolioNo + "%");
		queryObject.setParameter("LOAN_ACCOUNT_NO", "%" + loanAccountNumber + "%");
		//queryObject.setParameter("status", "CCA");
		queryObject.setParameter("status", "NCA");

		queryObject.setParameter("DISBURSEMENT_STATUS", "N");
		resultList=queryObject.list();
		log.info("Exit from getDisburseDataForApproval() method in DisbursementForApprovalDaoImpl class ");

		return resultList;
	}

	
	public int updateDisbursedData(List<DisbursementForApprovalModel> disbursedData,String userId) {
		// TODO Auto-generated method stub
		int result=0;
		try{
		log.info("Enter in updateDisbusedData() method in DisbursementForApprovalDaoImpl class ");
		Session session = sessionFactory.openSession();
		Transaction tn = session.beginTransaction();
		if(disbursedData.size()>0 && userId!=null){
			for(DisbursementForApprovalModel dm:disbursedData){
				
		Query query = sessionFactory.getCurrentSession().createQuery("Update DisbursementForApprovalModel " +
				"set status=:statusValue," +
				"dateOfDisbursement=:DisbursedDate," +
				"disbMakerDate=:disbMakerDate," +
				"disbMakerId=:disbMakerId," +
				"whetherDisbursed=:DISBURSEMENT_STATUS " +
				"where loanAccountNumber = :LOAN_ACCOUNT_NO");
		query.setParameter("statusValue", "CDE");
		query.setParameter("DisbursedDate", dm.getDateOfDisbursement());
		query.setParameter("LOAN_ACCOUNT_NO", dm.getLoanAccountNumber());
		query.setParameter("DISBURSEMENT_STATUS", "N");
		query.setParameter("disbMakerId", userId);
		query.setParameter("disbMakerDate", new Date());
		
		
		result = query.executeUpdate();
			}
		}
		tn.commit();
		sessionFactory.close();
		log.info("Exit from updateDisbusedData() method in DisbursementForApprovalDaoImpl Java class ");
		}catch(Exception e){
			
		}
		return result;
	}

	

	

	

}
