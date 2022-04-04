package com.nbfc.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbfc.bean.CGTMSECreatedExposureLimitCheckerBean;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETMemberInfoDetails;
import com.nbfc.model.CGTMSEExposureMasterCheckerMLIName;
import com.nbfc.model.CGTMSERejectExposureMasterCheckerDetailsData;
import com.nbfc.model.CGTMSESaveExposureMasterCheckerDetailsData;

@Repository("cgtmseCreatedExposureLimitCheckerDao")

public class CGTMSECreatedExposureLimitCheckerDaoImpl implements CGTMSECreatedExposureLimitCheckerDao{
	@Autowired
	private SessionFactory sessionFactory;
	ArrayList<String> arrayListObj=null;
	Map<String ,String> mapObj1=new HashMap<String ,String>();
	/*@Override
	public Map<String, String> getMliLongNameInDropDown() {
		Map<String ,String> mapObj=new HashMap<String ,String>();
		String cgtmseExposureMasterMakerStatus="CEMMA";
		String hql = "FROM CGTMSEExposureMasterCheckerMLIName";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query==="+query);
		List<CGTMSEExposureMasterCheckerMLIName> listCategories3 = query.list();
		System.out.println("listCategories3 size==="+listCategories3.size());
		java.util.Iterator<CGTMSEExposureMasterCheckerMLIName> itr= listCategories3.iterator();
		while(itr.hasNext()){
			Object o= itr.next();
			CGTMSEExposureMasterCheckerMLIName s1=(CGTMSEExposureMasterCheckerMLIName)o;
			System.out.println("MLIName=="+s1.getMliLongName());
			mapObj.put(s1.getMliLongName(), s1.getMliLongName());
		}
		return mapObj;
	}*/
	
	public List<Object[]> getMliLongNameInDropDown(){
		System.out.println("getMliLongNameInDropDown method called as part of CGTMSECreatedExposureLimitCheckerDaoImpl class===");
		String status="CEMMA";
		Map<String ,String> mapObj=new HashMap<String ,String>();
		String hql = "SELECT  nmf.mliLongName FROM CGTMSEExposureMasterMLIName nmf,CGTMSEExposureMasterDetails nel where nel.status=:status and nmf.memBnkId=nel.memBnkId and nmf.memZneId=nel.memZneId AND nmf.memBrnId=nel.memBrnId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		System.out.println("Query1=="+query);
		List<Object[]> listCategories51 = query.list();
		
		return listCategories51;
	}
	public ArrayList getMliShortNameOnChangeOfMliLongName(CGTMSECreatedExposureLimitCheckerBean cgtmseExposureMasterCheckerBean) {
		try{
			
			String mliLName=cgtmseExposureMasterCheckerBean.getMliLongName();
			System.out.println("mliLName in DAO==="+mliLName);
			Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterCheckerMLIName where long_name = :mliLName");
			//SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate FROM  CGTMSEExposureMasterCheckerMLIName NMI,CGTMSEExposureMasterCheckerGETExposureDetails  NEL WHERE NMI.mliLongName = 'State Bank of India' AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId; 
			//Query query = sessionFactory.getCurrentSession().createQuery("SELECT NMI.mliShortName,NMI.mliLongName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate FROM  CGTMSEExposureMasterCheckerMLIName NMI,CGTMSEExposureMasterCheckerGETExposureDetails  NEL WHERE mliLongName = :mliLName AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId");
			query.setParameter("mliLName", cgtmseExposureMasterCheckerBean.getMliLongName());
			mapObj1.clear();
			@SuppressWarnings("unchecked")
			List<CGTMSEExposureMasterCheckerMLIName> list = query.list();
			System.out.println("list=="+list.size());
			Iterator<CGTMSEExposureMasterCheckerMLIName> itr= list.iterator();
			while(itr.hasNext()){
				CGTMSEExposureMasterCheckerMLIName o1= (CGTMSEExposureMasterCheckerMLIName)itr.next();
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
	
	public List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> getDetailsOfMemberInfo(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls)
	{
		String mliLName=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMliLongName();
		//String CSV = "Google,Apple,Microsoft"; 
		//String[] values = CSV.split(","); 
		//System.out.println(Arrays.toString(values));
		String[] separatedMliName=mliLName.split(",");
		System.out.println("aa Index Value=="+Arrays.toString(separatedMliName));
		System.out.println("0 Index Value=="+separatedMliName[0]);
		mliLName=separatedMliName[0];
		System.out.println("1==="+mliLName);
	
		Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterCheckerGETMemberInfoDetails where mliLongName =:mliLName");
		query.setParameter("mliLName",mliLName);
		System.out.println("Query==="+query);
		List<CGTMSEExposureMasterCheckerGETMemberInfoDetails> CGTMSEExposureMasterGetDetailsOfMemberInfoListObj = query.list();
		return CGTMSEExposureMasterGetDetailsOfMemberInfoListObj;

	}
	
	
	public List<CGTMSESaveExposureMasterCheckerDetailsData> getDetailsOfExposer(CGTMSESaveExposureMasterCheckerDetailsData cgtmseExposureMasterGetDetails)
	{
			
		 	String exposerMemBnkId=cgtmseExposureMasterGetDetails.getMemBnkId();
		 	String exposerMemBrnId=cgtmseExposureMasterGetDetails.getMemBrnId();
		 	String exposerMemZneId=cgtmseExposureMasterGetDetails.getMemZneId();
		 	String hql = "from CGTMSESaveExposureMasterCheckerDetailsData celm where celm.memBnkId=:exposerMemBnkId and celm.memBrnId=:exposerMemBrnId and celm.memZneId=:exposerMemZneId";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("exposerMemBnkId", exposerMemBnkId);
			query.setParameter("exposerMemBrnId", exposerMemBrnId);
			query.setParameter("exposerMemZneId", exposerMemZneId);
			
			List<CGTMSESaveExposureMasterCheckerDetailsData> CGTMSEExposureMasterGetDetails= query.list();
		    return CGTMSEExposureMasterGetDetails;
	}
	
	
	
	
	

	public CGTMSEExposureMasterCheckerGETExposureDetails getExposureLimitInfo(CGTMSEExposureMasterCheckerGETExposureDetails cgtmseExposureMasterCheckerGETExposureLimitInfoDetailsObj) {
			System.out.println("getMliCheckerExposureLimitInfo method called as part of CGTMSEExposureMasterCheckerDaoImpl class===");
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
			System.out.println("listObj=="+listObj.size());
			Iterator<CGTMSEExposureMasterCheckerGETExposureDetails> itr= listObj.iterator();
			while(itr.hasNext()){
				o1= (CGTMSEExposureMasterCheckerGETExposureDetails)itr.next();
				System.out.println("ExposureLimit=="+o1.getExposureLimit());
				System.out.println("ExposureSanctionDate=="+o1.getExposureSanctionDate());
				System.out.println("FromDate=="+o1.getFromDate());
				System.out.println("tOdATE=="+o1.getToDate());
				
			}  
			return  o1;
	}
	//Approve

	public int approveCreatedExposureLimit(
			CGTMSESaveExposureMasterCheckerDetailsData cgtmseSaveExposureMasterCheckerDetailsDataObj) {
		
		String status=cgtmseSaveExposureMasterCheckerDetailsDataObj.getCheckerStatus();
		String memBnkId=cgtmseSaveExposureMasterCheckerDetailsDataObj.getMemBnkId();
		String memBrnId=cgtmseSaveExposureMasterCheckerDetailsDataObj.getMemBrnId();
		String memZneId=cgtmseSaveExposureMasterCheckerDetailsDataObj.getMemZneId();
		String checkId=cgtmseSaveExposureMasterCheckerDetailsDataObj.getCheckerId();
		Date checkDate=cgtmseSaveExposureMasterCheckerDetailsDataObj.getCheckerDate();
		String statusDescription="Approve";
		
		Session session4 = sessionFactory.openSession();
		Transaction tx4 = session4.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSESaveExposureMasterCheckerDetailsData set checkerStatus=:status,checkerId=:checkId,checkerDate=:checkDate,statusDescription=:statusDescription" + " where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query1.setParameter("status", status);
		query1.setParameter("checkId", checkId);
		query1.setParameter("checkDate", checkDate);
		query1.setParameter("statusDescription", statusDescription);
		
		query1.setParameter("memBnkId", memBnkId);
		query1.setParameter("memBrnId", memBrnId);
		query1.setParameter("memZneId", memZneId);
		
		int result = query1.executeUpdate();
		tx4.commit();
		sessionFactory.close();
		return 0;
	}
	
	/*Added by Akhilesh Tiwari
	
	@Override
	public void updateStatusExposerLimitCheakerAprroval(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls) {
		
		String status=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getStatus();
		String memBnkId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemBnkId();
		String memBrnId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemBrnId();
		String memZneId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemZneId();
		String mliLongName=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMliLongName();
		String mliShortName=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMliShortName();
		
		Session session5 = sessionFactory.openSession();
		Transaction tx5 = session5.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSEExposureMasterCheckerGETMemberInfoDetails set status=:status,mliLongName=:mliLongName,mliShortName=:mliShortName" + " where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query1.setParameter("status", status);
		query1.setParameter("mliLongName", mliLongName);
		query1.setParameter("mliShortName", mliShortName);
		
		
		query1.setParameter("memBnkId", memBnkId);
		query1.setParameter("memBrnId", memBrnId);
		query1.setParameter("memZneId", memZneId);
		
		int result = query1.executeUpdate();
		tx5.commit();
		sessionFactory.close();
	}
	
	public void updateStatusExposerLimitCheakerRejection(CGTMSEExposureMasterCheckerGETMemberInfoDetails cgtmseExposureMasterGetDetailsOfMemberInfoDtls) {
		
		String status=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getStatus();
		String memBnkId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemBnkId();
		String memBrnId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemBrnId();
		String memZneId=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMemZneId();
		String mliLongName=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMliLongName();
		String mliShortName=cgtmseExposureMasterGetDetailsOfMemberInfoDtls.getMliShortName();
		
		Session session5 = sessionFactory.openSession();
		Transaction tx5 = session5.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSEExposureMasterCheckerGETMemberInfoDetails set status=:status,mliLongName=:mliLongName,mliShortName=:mliShortName" + " where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query1.setParameter("status", status);
		query1.setParameter("mliLongName", mliLongName);
		query1.setParameter("mliShortName", mliShortName);
		
		
		query1.setParameter("memBnkId", memBnkId);
		query1.setParameter("memBrnId", memBrnId);
		query1.setParameter("memZneId", memZneId);
		
		int result = query1.executeUpdate();
		System.out.println("No of Updated Records:"+result);
		tx5.commit();
		sessionFactory.close();
	}
	
	End*/
	
	
	//Rejected

	public int rejectCreatedExposureLimit(CGTMSERejectExposureMasterCheckerDetailsData cgtmseRejectExposureMasterCheckerDetailsDataObj) {
		
		String rejectStatus=cgtmseRejectExposureMasterCheckerDetailsDataObj.getCheckerStatus();
		System.out.println("status==="+rejectStatus);
		String memBnkId=cgtmseRejectExposureMasterCheckerDetailsDataObj.getMemBnkId();
		String memBrnId=cgtmseRejectExposureMasterCheckerDetailsDataObj.getMemBrnId();
		String memZneId=cgtmseRejectExposureMasterCheckerDetailsDataObj.getMemZneId();
		String checkId=cgtmseRejectExposureMasterCheckerDetailsDataObj.getCheckerId();
		Date checkDate=cgtmseRejectExposureMasterCheckerDetailsDataObj.getCheckerDate();
		String checkerStatusDescription=cgtmseRejectExposureMasterCheckerDetailsDataObj.getCheckerStatusDescription();
		String Remark=cgtmseRejectExposureMasterCheckerDetailsDataObj.getRemarks();
		
		Session session4 = sessionFactory.openSession();
		Transaction tx4 = session4.beginTransaction();
		Query query1 = sessionFactory.getCurrentSession().createQuery("Update CGTMSERejectExposureMasterCheckerDetailsData set checkerStatus=:rejectStatus,checkerId=:checkId,REMARKS=:Remark,checkerDate=:checkDate,checkerStatusDescription=:checkerStatusDescription" + " where memBnkId = :memBnkId AND memBrnId = :memBrnId AND memZneId = :memZneId");
		query1.setParameter("rejectStatus", rejectStatus);
		query1.setParameter("checkId", checkId);
		query1.setParameter("checkDate", checkDate);
		query1.setParameter("checkerStatusDescription", checkerStatusDescription);
		query1.setParameter("memBnkId", memBnkId);
		query1.setParameter("memBrnId", memBrnId);
		query1.setParameter("memZneId", memZneId);
		query1.setParameter("Remark", Remark);
		
		int result = query1.executeUpdate();
		System.out.println("No of Updated Records:"+result);
		tx4.commit();
		sessionFactory.close();
		return 0;
	}
	
	
	

	
	
	
	 
	public List<Object[]> getExposureLimitDetailsPendingForApproval(){
		String status="CEMMA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:status AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		System.out.println("Query=="+query);
		List<Object[]> listCategories4 = query.list();
		
		return listCategories4;
	}
 
	
	public List<Object[]> getExposureLimitApprovedDetails(){
		System.out.println("getExposureMasterApprovalDetailsApprovedByChecker method called as part of Dao===");
		String checkerStatus="CEMCA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerStatus", checkerStatus);
		System.out.println("Query1=="+query);
		List<Object[]> listCategories4 = query.list();
		
		return listCategories4;
	}
	
	
	public List<Object[]> getExposureLimitRejectedDetails(){
		System.out.println("getExposureMasterApprovalDetailsApprovedByChecker method called as part of Dao===");
		System.out.println("Original Rejection status===");
		String checkerStatus="CEMCR";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerStatus", checkerStatus);
		System.out.println("Query1=="+query);
		List<Object[]> listCategories4 = query.list();
		
		return listCategories4;
	}
	

	public List<Object[]> getAllExposureLimitDetails() 
	{
		String Status="CEMMA";
		String AuditStatus="CEMME";
		String hql = "from CGTMSEExposureMasterCheckerGETExposureDetails celm,CGTMSEExposureMasterMLIName cemm where cemm.memBnkId=celm.memBnkId and cemm.memZneId=celm.memZneId and cemm.memBrnId=celm.memBrnId and ((celm.status=:Status) or (celm.status=:AuditStatus)) order by celm.exposureId desc"; 
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("Status", Status);
		query.setParameter("AuditStatus", AuditStatus);
		System.out.println("Query11111111Anand==="+query);
        List<Object[]> listCategories3 = query.list();
		return listCategories3;
		
	}
	
}
