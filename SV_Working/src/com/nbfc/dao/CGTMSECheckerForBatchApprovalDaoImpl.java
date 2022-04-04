package com.nbfc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.CGTMSECheckerForBatchApprovalGetStatus;

@Repository("cgmseCheckerForBatchApprovalDao")
public class CGTMSECheckerForBatchApprovalDaoImpl implements CGTMSECheckerForBatchApprovalDao{
	@Autowired
	private SessionFactory sessionFactory;
	 static Logger log = Logger.getLogger(CGTMSECheckerForBatchApprovalDaoImpl.class.getName());
	@SuppressWarnings("unchecked")
	public List<Object> getCMAStatusCount(CGTMSECheckerForBatchApprovalGetStatus cgtmseCheckerForBatchApprovalGetStatus) {
		log.info("getCMAStatusCount method call as part of CGTMSECheckerForBatchApprovalDaoImpl=====");
		String CMAStatus=cgtmseCheckerForBatchApprovalGetStatus.getStatus();
		log.info("CMAStatus=="+CMAStatus);
		//String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSECheckerForBatchApprovalGetStatus where STATUS =:CMAStatus group by subPortfolioNo,status,filePath";
//		String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSECheckerForBatchApprovalGetStatus where disbursement_status='Y' AND STATUS =:CMAStatus group by subPortfolioNo,status,filePath";
	
		String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSECheckerForBatchApprovalGetStatus where STATUS =:CMAStatus group by subPortfolioNo,status,filePath";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("CMAStatus", CMAStatus);
		log.info("QUERY1==="+query);
		List<Object> listCategories5 = query.list();
		log.info("listCategories5 size==="+listCategories5.size());
		log.info("listCategories5 Data==="+listCategories5);
		/*Iterator<Object> itr= listCategories5.iterator();
		while(itr.hasNext()){
			System.out.println(" Inside While Loop#############");
			Object[] obj1 = (Object[]) itr.next();
			System.out.println("QuaterNo==="+obj1[0]);
			System.out.println("No.of File==="+obj1[1]);
			System.out.println("Status==="+obj1[2]);
		}*/
		return listCategories5;
	}
}
