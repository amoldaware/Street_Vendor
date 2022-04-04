package com.nbfc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.CGTMSEMakerForBatchApprovalGetStatus;

@Repository("cgtmseMakerForBatchApprovalGetStatusDao")
public class CGTMSEMakerForBatchApprovalGetStatusDaoImpl implements CGTMSEMakerForBatchApprovalGetStatusDao{
	@Autowired
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(CGTMSEMakerForBatchApprovalGetStatusDaoImpl.class.getName());
	
	@SuppressWarnings("unchecked")
	public Integer getNCAStatusCountBasedOnNCAStatus(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus) {
		log.info("getNCAStatusCountBasedOnNCAStatus method called as part of  CGTMSEMakerForBatchApprovalGetStatusDaoImpl class==");
		String NCAStatus=cgtmseMakerForBatchApprovalGetStatus.getStatus();
		String CCRStatus="CCR";
		Integer count=0;
		log.info("NCAStatus====="+NCAStatus);
		//String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSEMakerForBatchApprovalGetStatus where STATUS =:NCAStatus group by subPortfolioNo,status,filePath";
		//String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSEMakerForBatchApprovalGetStatus where disbursement_status='Y' AND STATUS =:NCAStatus group by subPortfolioNo,status,filePath";
		//String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status,count(distinct filePath) from CGTMSEMakerForBatchApprovalGetStatus where STATUS =:NCAStatus group by subPortfolioNo,status,filePath";
		String hql = "select count(distinct n.fileId)  from CGTMSEMakerForBatchApprovalGetStatus n where status in(:NCAStatus,:CCRStatus) ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("NCAStatus", NCAStatus);
		query.setParameter("CCRStatus", CCRStatus);
		log.info("QUERY1====="+query);
		//List<Object> listCategories4 = query.list();
		count=((Number) query.uniqueResult()).intValue();
		System.out.println("-----------"+count);
		/*
		 *The below code for testing purpose 
		 * Iterator<Object> itr1= listCategories4.iterator();
		while(itr1.hasNext()){
			log.info("Inside While Loop=====");
			Object[] obj1 = (Object[]) itr1.next();
			log.info("QuaterNo==="+obj1[0]);
			log.info("NoOfFile==="+obj1[1]);
			log.info("Status==="+obj1[2]);
			log.info("NumberOfFile==="+obj1[3]);
		}*/
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getNoOfBatchRejectedByCGTMSE(CGTMSEMakerForBatchApprovalGetStatus cgtmseMakerForBatchApprovalGetStatus) {
		log.info("getNoOfBatchRejectedByCGTMSE method called as part of CGTMSEMakerForBatchApprovalGetStatusDaoImpl class=====");
		String CCRStatus=cgtmseMakerForBatchApprovalGetStatus.getStatus();
		log.info("CCRStatus====="+CCRStatus);
		String hql = "select subPortfolioNo,count(distinct subPortfolioNo),status from CGTMSEMakerForBatchApprovalGetStatus where STATUS =:CCRStatus group by subPortfolioNo,status";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("CCRStatus", CCRStatus);
		log.info("QUERY2====="+query);
		List<Object> listCategories4 = query.list();
		log.info("listCategories4 size======="+listCategories4.size());
		log.info("Got Data From DB ====="+listCategories4);
		
		/*
		 * The below code for testing purpose
		 * Iterator<Object> itr1= listCategories4.iterator();
	
		while(itr1.hasNext()){
			System.out.println(" Inside While Loop#############");
			Object[] obj1 = (Object[]) itr1.next();
			System.out.println("1111112==="+obj1[0]);
			System.out.println("2222222==="+obj1[1]);
			System.out.println("3333332==="+obj1[2]);
			
		}*/
		return listCategories4;
	}
}
