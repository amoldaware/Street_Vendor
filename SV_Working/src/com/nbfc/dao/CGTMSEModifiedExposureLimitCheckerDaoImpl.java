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
import com.nbfc.bean.CGTMSEModifiedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSEAuditExposureLimit;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.CGTMSEMemberInfoDetails;

@Repository("cgtmseModifiedExposureLimitCheckerDao")
public class CGTMSEModifiedExposureLimitCheckerDaoImpl implements CGTMSEModifiedExposureLimitCheckerDao{

	@Autowired
	private SessionFactory sessionFactory;
	ArrayList<String> arrayListObj=null;
	Map<String ,String> mapObj1=new HashMap<String ,String>();
	public Map<String, String> getMliLongNameInDropDown() {
		Map<String ,String> mapObj=new HashMap<String ,String>();
		String cgtmseExposureMasterMakerStatus="CEMMA";
		String hql = "FROM CGTMSEExposureMasterMLIName";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<CGTMSEExposureMasterMLIName> listCategories3 = query.list();
		java.util.Iterator<CGTMSEExposureMasterMLIName> itr= listCategories3.iterator();
		while(itr.hasNext()){
			Object o= itr.next();
			CGTMSEExposureMasterMLIName s1=(CGTMSEExposureMasterMLIName)o;
			mapObj.put(s1.getMliLongName(), s1.getMliLongName());
		}
		return mapObj;
	}
	
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSEModifiedExposureLimitCheckerBean cgtmseModifiedExposureLimitCheckerBeanBeanObj) {
		try{
			String mliLName=cgtmseModifiedExposureLimitCheckerBeanBeanObj.getMliLongName();
			Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterMLIName where long_name = :mliLName");
			query.setParameter("mliLName", cgtmseModifiedExposureLimitCheckerBeanBeanObj.getMliLongName());
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
	
	public CGTMSEMemberInfoDetails getMliMemberInfo(CGTMSEMemberInfoDetails cgtmseMemberInfoDetailsObj) {
		return null;
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
	
	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
		String memBnkId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemBnkId();
		String memBrnId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemBrnId();
		String memZneId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemZneId();
		Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterCheckerGETExposureDetails where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query.setParameter("memBnkId", memBnkId);
		query.setParameter("memBrnId", memBrnId);
		query.setParameter("memZneId", memZneId);
		@SuppressWarnings("unchecked")
		List<CGTMSEExposureMasterCheckerGETExposureDetails> listObj = query.list();
		CGTMSEExposureMasterCheckerGETExposureDetails o1=null;
		Iterator<CGTMSEExposureMasterCheckerGETExposureDetails> itr= listObj.iterator();
		while(itr.hasNext()){
			o1= (CGTMSEExposureMasterCheckerGETExposureDetails)itr.next();
		}  
		return  o1;
	}
	
	public CGTMSEAuditExposureLimit getAllExposureAuditLimitDetails(CGTMSEAuditExposureLimit cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj){
		String memBnkId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemBnkId();
		String memBrnId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemBrnId();
		String memZneId=cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj.getMemZneId();
		Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEAuditExposureLimit where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query.setParameter("memBnkId", memBnkId);
		query.setParameter("memBrnId", memBrnId);
		query.setParameter("memZneId", memZneId);
		@SuppressWarnings("unchecked")
		List<CGTMSEAuditExposureLimit> listObj = query.list();
		CGTMSEAuditExposureLimit o1=null;
		Iterator<CGTMSEAuditExposureLimit> itr= listObj.iterator();
		while(itr.hasNext()){
			o1= (CGTMSEAuditExposureLimit)itr.next();
		}  
		return  o1;
	}
}
