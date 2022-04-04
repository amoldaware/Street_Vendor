package com.nbfc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbission;
import com.nbfc.model.CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave;
import com.raistudies.domain.CustomExceptionHandler;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Repository("CGTMSECheckerForBatchApprovalAndRejectionDao")
public class CGTMSECheckerForBatchApprovalAndRejectionDaoImpl implements CGTMSECheckerForBatchApprovalAndRejectionDao{
	@Autowired
	private SessionFactory sessionFactory;
	static Logger log = Logger.getLogger(CGTMSECheckerForBatchApprovalAndRejectionDaoImpl.class.getName());
	public boolean storeData(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave) {
		try{
		log.info("storeData method call as part of CGTMSECheckerForBatchApprovalAndRejectionDaoImpl=====");
		Integer portfolioNo=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getPortfolioNo();
		String statusValue="CCA";//CGTMSE Maker Approved
		log.info("statusValue For CCA=="+statusValue);
		Session session3 = sessionFactory.openSession();
		Transaction tx3 = session3.beginTransaction();
		Query query = sessionFactory.getCurrentSession().createQuery("Update CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave set status=:statusValue" + " where portfolio_no = :portfolioNo");
		query.setParameter("statusValue", statusValue);
		query.setParameter("portfolioNo", portfolioNo);
		log.info("Query1=="+query);
		int result = query.executeUpdate();
		log.info("NoofRecords Updated Successfully=="+result);
		tx3.commit();
		sessionFactory.close();
		return false;
		}catch(Exception e){
			System.out.println("Exception ==="+e);
			log.info("Exception ==="+e);
	}
		return false;
}
	
	//File downLoad
	public List<CGTMSECheckerForBatchApprovalAndRejectionSumbission> getDataForNBFCCheckerFileDownLoad(CGTMSECheckerForBatchApprovalAndRejectionSumbission cgtmseCheckerForBatchApprovalAndRejectionSumbission) {
	 try{	
		log.info("getDataForNBFCCheckerFileDownLoad method call as part of CGTMSECheckerForBatchApprovalAndRejectionDaoImpl=====");
		String fFileName=cgtmseCheckerForBatchApprovalAndRejectionSumbission.getFilePath();
		String statusCMA="CMA";
		log.info("statusCMA ==="+statusCMA);
		log.info("fFileName=="+fFileName);
	
		String hql = " FROM CGTMSECheckerForBatchApprovalAndRejectionSumbission where status = :statusCMA AND FILE_ID=:fFileName";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fFileName", fFileName);
		query.setParameter("statusCMA", statusCMA);
		log.info("Query2=="+query);
		int counter=0;
		List<CGTMSECheckerForBatchApprovalAndRejectionSumbission> listCategories3 = query.list();
		log.info("listCategories3 size=="+listCategories3.size());
		log.info("listCategories3 Data=="+listCategories3);
		/*Iterator<CGTMSECheckerForBatchApprovalAndRejectionSumbission> itr= listCategories3.iterator();
		while(itr.hasNext()){
			Object o= itr.next();
			CGTMSECheckerForBatchApprovalAndRejectionSumbission s1=(CGTMSECheckerForBatchApprovalAndRejectionSumbission)o;
			System.out.println("FilePath Inside DAO=="+s1.getFilePath()+"" +"PortfolioNo Inside DAO=="+s1.getPortfolioNo());
			counter++;
			System.out.println("No of Rows=="+counter);
		}*/
		
		return listCategories3;
	}catch(Exception e){
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}
	return null;
}
	
	@SuppressWarnings("deprecation")
	public boolean checkerApprove(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave,String userId) {
	 	
		log.info("checkerApprove method call as part of CGTMSECheckerForBatchApprovalAndRejectionDaoImpl=====");
		String checkerId=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getCgtmseCheckerId();
		String fullFileName=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getFilePath();
		log.info("checkerId==="+checkerId);
		log.info("fullFileName==="+fullFileName);
		//String File_id = fullFileName.substring(fullFileName.lastIndexOf("/")+1,fullFileName.lastIndexOf("."));
		
		//System.out.println("File_id : "+File_id);
		

		String statusValue="CCA";//CGTMSE Checker Rejected
		log.info("statusValue For CCA==="+statusValue);
		
		Session session4 = sessionFactory.openSession();
		Transaction tn = session4.beginTransaction();
		Connection conn = session4.connection();
		 try{
	
			 CallableStatement call1;
			 call1 = (CallableStatement) conn
						.prepareCall("{ call nbfc.ProcAutoGenNBFCDAN(?,?,?)}");
																// whatever
																// it is
				call1.setString(1, fullFileName);
				call1.setString(2,userId);
				call1.registerOutParameter(3, Types.VARCHAR);
				call1.execute();
				String pouterror1 = call1.getString(3);
				if(pouterror1!=null){
					throw new CustomExceptionHandler("Exception occured :"+pouterror1);
				}
		
	
		tn.commit();
		//return false;
	}catch(Exception e){
		if (tn != null)
			tn.rollback();
		e.printStackTrace();
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}finally{
		session4.close();
		sessionFactory.close();
	}
	  
	return false;
	}
	@SuppressWarnings("deprecation")
	public boolean checkerRejected(CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave) {
	   try{	
		log.info("checkerRejected method call as part of CGTMSECheckerForBatchApprovalAndRejectionDaoImpl=====");
		Integer portfolioNo=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getPortfolioNo();
		String remarksForRejection=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getRejection_reason();
		log.info("portfolioNo==="+portfolioNo);
		String checkerId=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getCgtmseCheckerId();
		String fileName=cgtmseCheckerForBatchApprovalAndRejectionSumbissionSave.getFilePath();
		log.info("checkerId==="+checkerId);
		log.info("fileName==="+fileName);
		
		String statusValue="CCR";//CGTMSE Checker Rejected
		log.info("statusValue For CCR =="+statusValue);
		Session session4 = sessionFactory.openSession();
		Transaction tx4 = session4.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSECheckerForBatchApprovalAndRejectionSumbissionSave set status=:statusValue,cgtmseCheckerId=:checkerId,remarks=:remarksForRejection" + " where fileId = :fileName");
		query1.setParameter("statusValue", statusValue);
		query1.setParameter("remarksForRejection", remarksForRejection);
		query1.setParameter("checkerId", checkerId);
		query1.setParameter("fileName", fileName);
		log.info("Query4==="+query1);
		int result = query1.executeUpdate();
		log.info("Record rejected successfully By CGTMSE Checker==="+result);
		tx4.commit();
		sessionFactory.close();
		return false;
	}catch(Exception e){
		System.out.println("Exception ==="+e);
		log.info("Exception ==="+e);
	}
	return false;
	}
}
