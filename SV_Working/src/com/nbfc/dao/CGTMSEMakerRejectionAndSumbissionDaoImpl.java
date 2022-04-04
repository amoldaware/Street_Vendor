package com.nbfc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.model.CGTMSEMakerRejectionAndSumbission;
import com.nbfc.model.CGTMSEMakerRejectionAndSumbissionSave;

import org.hibernate.Session;
import org.hibernate.Transaction;



@Repository("cgtmseMakerRejectionAndSumbissionDao")
public class CGTMSEMakerRejectionAndSumbissionDaoImpl implements CGTMSEMakerRejectionAndSumbissionDao{
	@Autowired
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(CGTMSEMakerRejectionAndSumbissionDaoImpl.class.getName());
	
	@SuppressWarnings("deprecation")
	
	public boolean storeData(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSave) {
	try{
		log.info("storeData method was called as part of CGTMSEMakerRejectionAndSumbissionDaoImpl class==");
		log.info("fPath1==="+cgtmseMakerRejectionAndSumbissionSave.getFilePath());
		log.info("cgtmseMakerNo==="+cgtmseMakerRejectionAndSumbissionSave.getCgtmseMakerId());
		log.info("portNumner1==="+cgtmseMakerRejectionAndSumbissionSave.getPortfolioNo());
		log.info("QuaterNumber1==="+cgtmseMakerRejectionAndSumbissionSave.getSubPortfolioNo());
		log.info("Status==="+cgtmseMakerRejectionAndSumbissionSave.getStatus());
		System.out.println("Status111111-------------" +cgtmseMakerRejectionAndSumbissionSave.getStatus());
		System.out.println("portNumner1-------------" +cgtmseMakerRejectionAndSumbissionSave.getPortfolioNo());
		
		String fPath=cgtmseMakerRejectionAndSumbissionSave.getPortfolioNo();
		String cgtmseMakerNumber=cgtmseMakerRejectionAndSumbissionSave.getCgtmseMakerId();
		
		
		
	
		String statusValue="CMA";//CGTMSE Maker Rejected
		log.info("statusValue==="+statusValue);
		String status="NCA";
		Session session4 = sessionFactory.openSession();
		Transaction tx4 = session4.beginTransaction();
		
		//Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSEMakerRejectionAndSumbissionSave set status=:statusValue,cgtmseMakerId=:cgtmseMakerNumber" + " where filePath = :fPath and DISBURSEMENT_STATUS = :DISBURSEMENT_STATUS and status=:StatusNca  ");
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSEMakerRejectionAndSumbissionSave set status=:statusValue,cgtmseMakerId=:cgtmseMakerNumber" + " where portfolioNo = :fPath");

		query1.setParameter("statusValue", statusValue);
		query1.setParameter("cgtmseMakerNumber", cgtmseMakerNumber);
		query1.setParameter("fPath", fPath);
		//query1.setParameter("DISBURSEMENT_STATUS", "Y");

		//query1.setParameter("StatusNca", status);
		
		int result = query1.executeUpdate();
		log.info("Record Updated successfully==="+result);
		tx4.commit();
		sessionFactory.close();
		return false;
	}catch(Exception e){
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}
	return false;
}
	public List<CGTMSEMakerRejectionAndSumbission> getDataForNBFCMakerFileDownLoad(CGTMSEMakerRejectionAndSumbission cgtmseMakerRejectionAndSumbission) {
	  try{
		log.info("getDataForNBFCMakerFileDownLoad  method was called as part of CGTMSEMakerRejectionAndSumbissionDaoImpl class==");
		String filePath1=cgtmseMakerRejectionAndSumbission.getFilePath();
		log.info("filePath1==="+filePath1);
		log.info("status==="+cgtmseMakerRejectionAndSumbission.getStatus());
		String statusNca=cgtmseMakerRejectionAndSumbission.getStatus();
		log.info("statusNca==="+statusNca);
		String hql = " FROM CGTMSEMakerRejectionAndSumbission where FILE_ID =:filePath1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("filePath1", filePath1);
		//query.setParameter("statusNca", statusNca);
		log.info("Query2==="+query);
		List<CGTMSEMakerRejectionAndSumbission> listCategories3 = query.list();
		log.info("listCategories3 size==="+listCategories3.size());
		log.info("listCategories3 Data==="+listCategories3);
		return listCategories3;
	}catch(Exception e){
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}
	return null;
}
	
	@SuppressWarnings("deprecation")
	
	public boolean cgtmseMakerRejected(CGTMSEMakerRejectionAndSumbissionSave cgtmseMakerRejectionAndSumbissionSaveObj4) {
	 try{
		log.info("cgtmseMakerRejected method call as part of CGTMSEMakerRejectionAndSumbissionDaoImpl=====");
		String portfolioNo=cgtmseMakerRejectionAndSumbissionSaveObj4.getPortfolioNo();
		String remarksValue=cgtmseMakerRejectionAndSumbissionSaveObj4.getRejection_reason();
		Integer quaterNo=cgtmseMakerRejectionAndSumbissionSaveObj4.getSubPortfolioNo();
		String cgtmseMakerRejectionId=cgtmseMakerRejectionAndSumbissionSaveObj4.getCgtmseMakerId();
		String fileName=cgtmseMakerRejectionAndSumbissionSaveObj4.getFilePath();
		
		log.info("portfolioNo=="+portfolioNo);
		log.info("remarksValue=="+remarksValue);
		log.info("quaterNo=="+quaterNo);
		log.info("cgtmseMakerRejectionId=="+cgtmseMakerRejectionId);
		log.info("fileName=="+fileName);
		
		String statusValue="CMR";//CGTMSE Maker Rejected
		log.info("statusValue For CMR=="+statusValue);
		
		Session session5 = sessionFactory.openSession();
		Transaction tx5 = session5.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSEMakerRejectionAndSumbissionSave set status=:statusValue,remarks=:remarksValue,cgtmseMakerId=:cgtmseMakerRejectionId" + " where fileId = :fileName");
		query1.setParameter("cgtmseMakerRejectionId", cgtmseMakerRejectionId);
		query1.setParameter("statusValue", statusValue);
		query1.setParameter("remarksValue", remarksValue);
		query1.setParameter("fileName", fileName);
		log.info("Update Query=="+query1);
		int result = query1.executeUpdate();
		log.info("Record  rejected successfully==="+result);
		tx5.commit();
		sessionFactory.close();
		return false;
	}catch(Exception e){
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}
	return false;
}
}
