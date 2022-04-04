package com.nbfc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.model.CGTMSEExposureMasterGetDetailsOfMemberInfo;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nbfc.model.PortFolioDetailsInChildTBL;
import com.nbfc.model.PortFolioDetailsInParentTBL;
import org.hibernate.SQLQuery;



@Repository("cgtmseExposureMasterMakerDao")
public class CGTMSECreateExposureLimitMakerDaoImpl  implements CGTMSECreateExposureLimitMakerDao{
	@Autowired
	private SessionFactory sessionFactory;
	ArrayList<String> arrayListObj=null;
	Map<String ,String> mapObj1=new HashMap<String ,String>();
	ArrayList<CGTMSECreateExposureLimitByMaker> arrayListObj1=null;
	public Map<String,String> getMliLongNameInDropDown() {
		Map<String ,String> mapObj=new HashMap<String ,String>();
		//String hql = "from CGTMSEExposureMasterMLIName cemm where cemm.memBnkId not in(select celm.memBnkId from CGTMSECreateExposureLimitByMaker celm) and cemm.memBrnId not in(select celm.memBrnId from CGTMSECreateExposureLimitByMaker celm) and cemm.memZneId not in(select celm.memZneId from CGTMSECreateExposureLimitByMaker celm) and cemm.status='CCA'";
		String hql = "from CGTMSEExposureMasterMLIName cemm where cemm.status='CCA'"; 
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("hiiiiiiiiiiiiiiiiiiiii............."+query);
		List<CGTMSEExposureMasterMLIName> listCategories3 = query.list();
		java.util.Iterator<CGTMSEExposureMasterMLIName> itr= listCategories3.iterator();
		while(itr.hasNext()){
			Object o= itr.next();
			CGTMSEExposureMasterMLIName s1=(CGTMSEExposureMasterMLIName)o;
			mapObj.put(s1.getMliLongName(), s1.getMliLongName());
		}
		return mapObj;
	}

	public ArrayList<String> getMliShortNameOnChangeOfMliLongName(CGTMSEExposureMasterBean cgtmseExposureMasterBean) {
		try{
			String mliLName=cgtmseExposureMasterBean.getMliLongName();
			Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterMLIName where mliLongName = :mliLName");
			query.setParameter("mliLName", cgtmseExposureMasterBean.getMliLongName());
			mapObj1.clear();
			@SuppressWarnings("unchecked")
			List<CGTMSEExposureMasterMLIName> list = query.list();
			Iterator<CGTMSEExposureMasterMLIName> itr= list.iterator();
			while(itr.hasNext()){
				CGTMSEExposureMasterMLIName o1= (CGTMSEExposureMasterMLIName)itr.next();
				arrayListObj=new ArrayList<String>();
				arrayListObj.add(o1.getMliShortName());
			}  
			return arrayListObj;
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
		return arrayListObj;
	}
	
	public List<CGTMSEExposureMasterGetDetailsOfMemberInfo> getDetailsOfMemberInfo(CGTMSEExposureMasterGetDetailsOfMemberInfo cgtmseExposureMasterGetDetailsOfMemberInfoObj2) {
		String mliLName=cgtmseExposureMasterGetDetailsOfMemberInfoObj2.getMliLongName();
		Query query = sessionFactory.getCurrentSession().createQuery("from CGTMSEExposureMasterGetDetailsOfMemberInfo where mliLongName = :mliLName");
		query.setParameter("mliLName", mliLName);
		List<CGTMSEExposureMasterGetDetailsOfMemberInfo> CGTMSEExposureMasterGetDetailsOfMemberInfoListObj =  query.list();
		return CGTMSEExposureMasterGetDetailsOfMemberInfoListObj;
	}
	
	public CGTMSECreateExposureLimitByMaker getDetailsOfExposer(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterGetDetails,String fyYear){
	 	String exposerMemBnkId=cgtmseExposureMasterGetDetails.getMemBnkId();
	 	String exposerMemBrnId=cgtmseExposureMasterGetDetails.getMemBrnId();
	 	String exposerMemZneId=cgtmseExposureMasterGetDetails.getMemZneId();
	 	String hql = "from CGTMSECreateExposureLimitByMaker celm where celm.memBnkId=:exposerMemBnkId and celm.memBrnId=:exposerMemBrnId and celm.memZneId=:exposerMemZneId and celm.financial_year = :FYYEAR";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("exposerMemBnkId", exposerMemBnkId);
		query.setParameter("exposerMemBrnId", exposerMemBrnId);
		query.setParameter("exposerMemZneId", exposerMemZneId);
		query.setParameter("FYYEAR", fyYear);
		CGTMSECreateExposureLimitByMaker CGTMSEExposureMasterGetDetails= (CGTMSECreateExposureLimitByMaker) query.uniqueResult();
	    return CGTMSEExposureMasterGetDetails;
	}

	public Integer getMaxExposureIdCount(){
		try{
			String hql = "SELECT MAX(EXPOSURE_ID)+1 FROM CGTMSECreateExposureLimitByMaker ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return ((Number) query.uniqueResult()).intValue();
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
		return 0;
	}
   
	public void createNewExposureLimit(CGTMSECreateExposureLimitByMaker cgtmseExposureMasterSaveDetailsObj) {
		try{
			sessionFactory.getCurrentSession().save(cgtmseExposureMasterSaveDetailsObj);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
	}
	
	public List<Object[]> getExposureLimitDetailsPendingForApproval(){
		String status="CEMMA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:status AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		List<Object[]> listCategories4 = query.list();
		return listCategories4;
	}
	
	public List<Object[]> getExposureLimitApprovedDetails(){
		String checkerStatus="CEMCA";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerStatus", checkerStatus);
		List<Object[]> listCategories4 = query.list();
		return listCategories4;
	}
	
	public List<Object[]> getExposureLimitRejectedDetails(){
		String checkerRejectionStatus="CEMCR";
		String hql = "SELECT NMI.mliLongName,NMI.mliShortName,NEL.exposureLimit,NEL.exposureSanctionDate,NEL.fromDate,NEL.toDate,NEL.statusDescription,NEL.checkerId,NEL.checkerDate from CGTMSEMemberInfoDetails NMI,CGTMSEExposureMasterDetails NEL WHERE NEL.status=:checkerRejectionStatus AND NMI.memBnkId=NEL.memBnkId AND NMI.memBrnId=NEL.memBrnId AND NMI.memZneId=NEL.memZneId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("checkerRejectionStatus", checkerRejectionStatus);
		List<Object[]> listCategories5 = query.list();
		return listCategories5;
	}
	
	public List<Object[]> getAllExposureLimitDetails() {
		String hql = "from CGTMSEExposureMasterCheckerGETExposureDetails celm,CGTMSEExposureMasterMLIName cemm where cemm.memBnkId=celm.memBnkId and cemm.memZneId=celm.memZneId and cemm.memBrnId=celm.memBrnId order by celm.exposureId desc)"; 
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query=="+query);
        List<Object[]> listCategories3 = query.list();
		return listCategories3;
	}
	
	public void savePortFolioDetailsInParentTBL(PortFolioDetailsInParentTBL objPortFolioDetailsInParentTBL) {
		try{
			sessionFactory.getCurrentSession().save(objPortFolioDetailsInParentTBL);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
	}
	
	public void savePortFolioDetailsInChildTBL(PortFolioDetailsInChildTBL objPortFolioDetailsInChildTBL) {
		try{
			sessionFactory.getCurrentSession().save(objPortFolioDetailsInChildTBL);
		}catch(Exception e){
			System.out.println("Exception==="+e);
		}
	}

	public String getFyBasedOnStartAndEndDate(String strDate, String endDate){
		String finalcialY="";
		String output_strDate="";
		String output_endDate="";
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy"); 
		DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");  
		try{
			Date strDateConvertedInDate = df1.parse(strDate);
			output_strDate = df2.format(strDateConvertedInDate);
			Date endDateConvertedInDate = df1.parse(endDate);
			output_endDate = df2.format(endDateConvertedInDate);
		} catch (ParseException e){
		}
		org.hibernate.classic.Session session = sessionFactory.openSession();
		SQLQuery slqQuery = session.createSQLQuery("select  To_CHAR(FYSTARTDATE('"+output_strDate+"'),'RRRR') ||'-' ||(To_CHAR(FYSTARTDATE('"+output_endDate+"'),'RRRR')+1) as fyyear from dual");
		//SQLQuery q = session.reateSQLQuery("select  To_char(FYSTARTDATE('01-Apr-2018'),'RRRR') ||--' ||(To_char(FYSTARTDATE('01-Mar-2019'),'RRRR')+1) as fyyear from dual");
		ArrayList	fyList = (ArrayList) slqQuery.list();
		if(fyList.size()>0){
			Iterator itr=fyList.iterator();
			while(itr.hasNext()){
				finalcialY=(String) itr.next();
			}
		}	
		return finalcialY;
	}
}
