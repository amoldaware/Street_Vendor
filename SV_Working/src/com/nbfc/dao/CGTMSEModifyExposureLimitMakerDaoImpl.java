package com.nbfc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.CGTMSEMemberInfoDetails;
import com.nbfc.bean.CGTMSEExposureMasterMakerModifyExposureLimitBean;


@Repository("cgtmseExposureMasterMakerModifyExposureLimitDao")
public class CGTMSEModifyExposureLimitMakerDaoImpl implements CGTMSEModifyExposureLimitMakerDao{

	@Autowired
	private SessionFactory sessionFactory;
	ArrayList<String> arrayListObj=null;
	Map<String ,String> mapObj1=new HashMap<String ,String>();
	
	public List<Object[]> getMliLongNameInDropDown(){
		String approvedStatus="CEMCA";
		String rejectedStatus="CEMCR";
		Map<String ,String> mapObj=new HashMap<String ,String>();
		String hql = "SELECT  nmf.mliLongName FROM CGTMSEExposureMasterMLIName nmf where nmf.status=:approvedStatus OR nmf.status=:rejectedStatus";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("approvedStatus", approvedStatus);
		query.setParameter("rejectedStatus", rejectedStatus);
		List<Object[]> listCategories51 = query.list();
		return listCategories51;
	}
	
	public void updateStatusModifyExposerLimit(CGTMSEMemberInfoDetails cgtmseExposureMasterObj){
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(cgtmseExposureMasterObj);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
	}
	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterMakerModifyExposureLimitBean cgtmseExposureMasterMakerModifyExposureLimitBeanObj) {
		try{
			String mliLName=cgtmseExposureMasterMakerModifyExposureLimitBeanObj.getMliLongName();
			Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterMLIName where long_name = :mliLName");
			query.setParameter("mliLName", cgtmseExposureMasterMakerModifyExposureLimitBeanObj.getMliLongName());
			mapObj1.clear();
			@SuppressWarnings("unchecked")
			List<CGTMSEExposureMasterMLIName> list = query.list();
			Iterator<CGTMSEExposureMasterMLIName> itr= list.iterator();
			while(itr.hasNext()){
				CGTMSEExposureMasterMLIName o1= (CGTMSEExposureMasterMLIName)itr.next();
				arrayListObj=new ArrayList<String>();
				arrayListObj.add(o1.getMliShortName());
				arrayListObj.add(o1.getMemBnkId());
				arrayListObj.add(o1.getMemBrnId());
				arrayListObj.add(o1.getMemZneId());
			}  
			return arrayListObj;
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
		return arrayListObj;
	}
	
	public List<CGTMSEMemberInfoDetails>getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj) {
			String mliLName=cgtmseMemberInfoDetailsObj.getMliLongName();
			Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEMemberInfoDetails where mliLongName = :mliLName");
			query.setParameter("mliLName", mliLName);
			List<CGTMSEMemberInfoDetails> CGTMSEExposureMasterGetDetailsOfMemberInfoListObj =  query.list();
		 return  CGTMSEExposureMasterGetDetailsOfMemberInfoListObj;
	}
	
	public CGTMSEExposureMasterDetails getMliExposureLimitInfo(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj){
		String memBnkId=cgtmseExposureMasterDetailsObj.getMemBnkId();
		String memBrnId=cgtmseExposureMasterDetailsObj.getMemBrnId();
		String memZneId=cgtmseExposureMasterDetailsObj.getMemZneId();
		Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterDetails where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query.setParameter("memBnkId", memBnkId);
		query.setParameter("memBrnId", memBrnId);
		query.setParameter("memZneId", memZneId);
		@SuppressWarnings("unchecked")
		List<CGTMSEExposureMasterDetails> listObj = query.list();
		CGTMSEExposureMasterDetails o1=null;
		Iterator<CGTMSEExposureMasterDetails> itr= listObj.iterator();
		while(itr.hasNext()){
			o1= (CGTMSEExposureMasterDetails)itr.next();
		}  
		return  o1;
	}
	
	public int saveModifyExposureLimitByMakerAndSendForApprovalToChecker(CGTMSEExposureMasterDetails cgtmseExposureMasterDetailsObj1) {
		try {
			System.out.println("cgtmseExposureMasterDetailsObj1............................."+cgtmseExposureMasterDetailsObj1);
		 	sessionFactory.getCurrentSession().saveOrUpdate(cgtmseExposureMasterDetailsObj1);
		}catch(Exception e){
			System.out.println("Exception Here1==="+e);
		}
		return 0;
	}
	
	public int saveModifyExposureLimitDataAuditTable(CGTMSEAuditExposureLimit cgtmseExposureMasterDetailsObj1){
		try{
			sessionFactory.getCurrentSession().save(cgtmseExposureMasterDetailsObj1);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
  		return 0;
	}
	
	public List<Object[]> getNoOfExposureMasterPendingForApproval(){
		String status="CEMMA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:status AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		System.out.println("Query=="+query);
		List<Object[]> listCategories4 = query.list();
		return listCategories4;
	}
	
	public List<Object[]> getExposureMasterDetailsPendingForApproval(){
		String status="CEMMA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:status AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		List<Object[]> listCategories4 = query.list();
		return listCategories4;
	}
	
	public List<Object[]> getExposureMasterCheckerApprovalDetails(){
		String checkerStatus="CEMCA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerStatus", checkerStatus);
		System.out.println("Query1=="+query);
		List<Object[]> listCategories4 = query.list();
		return listCategories4;
	}
	
	public List<Object[]> getExposureMasterCheckerRejectionDetails(){
		String checkerRejectionStatus="CEMCR";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerRejectionStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerRejectionStatus", checkerRejectionStatus);
		List<Object[]> listCategories5 = query.list();
		return listCategories5;
	}
}
