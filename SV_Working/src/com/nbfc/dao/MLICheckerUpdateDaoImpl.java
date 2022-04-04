package com.nbfc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Repository("MLICheckerUpdateDao")
public class MLICheckerUpdateDaoImpl implements MLICheckerUpdateDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean updateStatusMLIChkAppLodge(String usrId, String status,
			String prtfoliNum) {

		System.out.println("step 1");
		/*
		 * Query query = sessionFactory .getCurrentSession() .createQuery(
		 * "update MLICGFeeDetails set status=:status where FILE_ID in(select distinct FILE_ID from User u,"
		 * +
		 * "CGTMSECreateExposureLimitByMaker e,MLICGFeeDetails n,PortfolioNumberMaster p where usr_id=:usrId"
		 * +
		 * " and e.memBnkId=u.mem_bnk_id and e.memZneId=u.mem_zne_id and e.memBrnId=u.mem_brn_id and"
		 * +
		 * " e.exposureId = p.exposureId	and P.portfolioNumber = N.PORTFOLIO_NO)"
		 * );
		 */

		//log.info("storeData method call as part of CGTMSECheckerForBatchApprovalAndRejectionDaoImpl=====");
		//Integer portfolioNo=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getPortfolioNo();
		//String statusValue="CCA";//CGTMSE Maker Approved
		//log.info("statusValue For CCA=="+statusValue);
		Session session3 = sessionFactory.openSession();
		Transaction tx3 = session3.beginTransaction();
		Query query = sessionFactory.getCurrentSession().createQuery(
				"update MLICGFeeDetails set STATUS = :status where FILE_PATH='12345705'");
		query.setParameter("status", "hello");
		//query.setParameter("portfolioNo", portfolioNo);
		//log.info("Query1=="+query);
		int result = query.executeUpdate();
		//log.info("NoofRecords Updated Successfully=="+result);
		tx3.commit();
		sessionFactory.close();
		//return false;
		
		
		
		/*Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update MLICGFeeDetails set STATUS = :status where FILE_ID='12345705'");
		System.out.println("step 2");
		// query.setParameter("usrId", usrId);
		query.setParameter("status", "hello");
		// query.setParameter("portfolioNum", prtfoliNum);
		System.out.println("step 3");
		int result = query.executeUpdate();
		System.out.println("step 4");
		System.out.println("result : " + result);

		sessionFactory.getCurrentSession().getTransaction().commit();
		// sessionFactory.getCurrentSession().getTransaction().commit();
*/		return false;

	}

}
