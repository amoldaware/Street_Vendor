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

import com.nbfc.bean.SanctionDetailBean;
import com.nbfc.model.PortfolioMSTModel;
import com.nbfc.model.SanctionDetailModel;
import com.nbfc.model.SanctionDetailsChild;
import com.nbfc.model.SanctionDetailsExposureLimitModel;
import com.nbfc.model.SanctionDetailsSave;

@Repository("sanctionDetailDao")
public class SanctionDetailDaoImpl  implements SanctionDetailDao{
	public SanctionDetailDaoImpl(){
		System.out.println("Inside  SanctionDetailDaoImpl class===");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	Map<String ,String> mapObj=new HashMap<String ,String>();
	Map<String ,String> mapObj1=new HashMap<String ,String>();
	Map<String ,String> mapObj2=new HashMap<String ,String>();
	
	ArrayList<String> arrayListObj1=null;
	
	ArrayList<String> arrayListObj=null;
	ArrayList<Integer> arrayListObj2=null;
	ArrayList arrayListObj4=null;
	
	
	public List<Object[]>getLongName() {
	    String masterStatus="CCA";
	    String exposerStatus="CEMCA";
		System.out.println("Inside getLongName method as part of SanctionDetailDaoImpl class==");
		String hql = "SELECT sdm.longName FROM SanctionDetailModel sdm,SanctionDetailsExposureLimitModel sdel where sdm.status=:masterStatus and sdel.status=:exposerStatus and sdm.memBnkId=sdel.memBnkId and sdm.memZneId=sdel.memZneId and sdm.memBrnId=sdel.memBrnId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("Query=="+query);
		query.setParameter("masterStatus", masterStatus);
		query.setParameter("exposerStatus", exposerStatus);
		List<Object[]> listCategories3 = query.list();
		
		
		return listCategories3;
		
	}
	
	
	public ArrayList getShortNameOnChangeOfLongName(SanctionDetailBean sanctionDetailBean) {
		
		System.out.println("###########inside getShortNameOnChangeOfLongName method of SanctionDetailsDaoImpl class ##########################");
		System.out.println("Long_name_code inside DAO======"+sanctionDetailBean.getLong_name());
		String long_name=sanctionDetailBean.getLong_name();
		
		Query query = sessionFactory.getCurrentSession().createQuery("from SanctionDetailModel where long_name = :long_name");
		System.out.println("Query==="+sanctionDetailBean);
		query.setParameter("long_name", sanctionDetailBean.getLong_name());
		mapObj1.clear();
		@SuppressWarnings("unchecked")
		List<SanctionDetailModel> list = query.list();
		Iterator<SanctionDetailModel> itr= list.iterator();
		while(itr.hasNext()){
			SanctionDetailModel o1= (SanctionDetailModel)itr.next();
			 arrayListObj=new ArrayList<String>();
			arrayListObj.add(o1.getShortName());
			arrayListObj.add(o1.getMEM_BNK_ID());
			arrayListObj.add(o1.getMEM_ZNE_ID());
			arrayListObj.add(o1.getMEM_BRN_ID());
		
		}  
		
		return arrayListObj;
	
	}
	
	
	//Get Exposure Limit
	

	
	public List<SanctionDetailsExposureLimitModel> getExposureLimit(SanctionDetailsExposureLimitModel sanctionDetailsExposureLimitModelObj) {
		
		System.out.println("###########inside getExposureLimit method of SanctionDetailsDaoImpl class ##########################");
		System.out.println("memBankId=="+sanctionDetailsExposureLimitModelObj.getMemBnkId());
		System.out.println("ZNE=="+sanctionDetailsExposureLimitModelObj.getMemZneId());
		System.out.println("Branch=="+sanctionDetailsExposureLimitModelObj.getMemBrnId());
		Query query = sessionFactory.getCurrentSession().createQuery("from SanctionDetailsExposureLimitModel where MEM_BNK_ID = :memBnkId");
		query.setParameter("memBnkId",sanctionDetailsExposureLimitModelObj.getMemBnkId());
		mapObj2.clear();
		@SuppressWarnings("unchecked")
		List<SanctionDetailsExposureLimitModel> list = query.list();
		/*Iterator<SanctionDetailsExposureLimitModel> itr= list.iterator();
		while(itr.hasNext()){
			SanctionDetailsExposureLimitModel o2= (SanctionDetailsExposureLimitModel)itr.next();
			
			System.out.println("MemBnkId####AAA#####="+o2.getMemBnkId());
			System.out.println("MemZneId####BBBB#####="+o2.getMemZneId());
			System.out.println("MemBrnId####CCCC#####="+o2.getMemBrnId());
			System.out.println("Exposure limit##DDDD#######="+o2.getExposureLimit());
			System.out.println("Exposure limit##DDDD#######="+o2.getToDate());
			
			Integer expLimit=o2.getExposureLimit();
			Integer expId=o2.getExposureId();
			String eLimit=String.valueOf(expLimit);
			String exposureId=String.valueOf(expId);
			 //Integer current_Year=Integer.parseInt(o1.getExposureLimit());
			arrayListObj1=new ArrayList<String>();
			
			arrayListObj1.add(o2.getMemBnkId());
			arrayListObj1.add(o2.getMemZneId());
			arrayListObj1.add(o2.getMemBrnId());
			arrayListObj1.add(eLimit);
			arrayListObj1.add(exposureId);
			arrayListObj1.add(o2.getToDate());
			
			
		}  */
		
		//return arrayListObj1;
		return list;
	}
	
	//Anand
	
	
	
	public long getUtilisiedExposureLimit(String longName) {
		try{
			System.out.println("###########inside getUtilisiedExposureLimit method of SanctionDetailsDaoImpl class ##########################");
			String lName=longName;
			System.out.println("UEL==="+lName);
			String hql="select sum(max_portfolio_size) from SanctionDetailsSave where EXPOSURE_ID = :lName";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("lName", lName);
			long n=((Number) query.uniqueResult()).longValue();
			if(n>0){
				return n;
			}else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("You have not created Exposoure for Particular MLI==="+e);
			return 0;
		}
	}
	
	public void addSanctionDetails(PortfolioMSTModel portfolioMSTModel) {
		sessionFactory.getCurrentSession().save(portfolioMSTModel);
	}
	
	
	public void addSanctionDetails1(SanctionDetailsChild sanctionDetailsChild) {
		sessionFactory.getCurrentSession().save(sanctionDetailsChild);
	}
	
	public int getGeneratedPortfolioCount(){
		System.out.println("Inside getGeneratedPortfolioCount method as part of SanctionDetailDaoImpl class==");
		String hql = "SELECT COUNT(PORTFOLIO_NO) FROM SanctionDetailsSave ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}

	//Get SumOfMaxPortfolio
	
	
	public Double getSumOfMaxPortfolio(Double expID){
		Query query = null;
		try{
			Double exposureId=expID;
			System.out.println("Inside getSumOfMaxPortfolio method as part of SanctionDetailDaoImpl class==");
			String hql = "select  sum(max_portfolio_size) from SanctionDetailsSave where EXPOSURE_ID=:exposureId";
			query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("exposureId", exposureId);
			System.out.println("query==="+query);
			System.out.println("You are creating portfolio first time==");
			long n=((Number) query.uniqueResult()).longValue();
			if(n>0){
				return (double) n;
			}else{
				return (double) 0;
			}
		}catch(Exception e){
			System.out.println("Exception==="+e);
			return (double) 0;
		}
}
}
